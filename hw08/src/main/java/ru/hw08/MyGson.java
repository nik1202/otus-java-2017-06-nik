package ru.hw08;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

/**
 * Created by nikolay on 04/08/17.
 */
public class MyGson implements IMyGson  {

    @Override
    public String toJson(Object o) {
        try {
            return serializeFieldValue(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * Сериализация объекта.
     * @param object - объект для сериализации.
     * @return - строка.
     * @throws IllegalAccessException - .
     */
    private String serializeObject(Object object) throws IllegalAccessException {
        if (object == null) {
            return "null";
        }
        StringBuilder resultString = new StringBuilder();
        resultString.append("{");
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }

            if (field.get(object) != null) {
                resultString.append(serializeFieldName(field));
                resultString.append(":");
                resultString.append(serializeFieldValue(field.get(object)));
                resultString.append(",");
            }
        }
        if (resultString.length() > 0) {
            resultString.deleteCharAt(resultString.length() - 1);
        }
        resultString.append("}");
        return resultString.toString();
    }
    /**
     * Сериализация имени поля.
     * @param field - поле.
     * @return - строка.
     */
    private String serializeFieldName(Field field) {
        return "\"" + field.getName() + "\"";
    }
    /**
     * Сериализация значения поля.
     * @param fieldValue - объект для сериализации.
     * @return - строка.
     * @throws IllegalAccessException - .
     */
    private String serializeFieldValue(Object fieldValue) throws IllegalAccessException {
        StringBuilder resultString = new StringBuilder();
        if (fieldValue == null) {
            resultString.append("null");
            return resultString.toString();
        }
        Class clazz = fieldValue.getClass();

        if (clazz.equals(String.class) || clazz.equals(char.class) || clazz.equals(Character.class)) {
            resultString.append("\"").append(fieldValue).append("\"");
        } else if (clazz.isPrimitive() || Number.class.isInstance(fieldValue)) {
            resultString.append(fieldValue);
        } else if (clazz.isArray()) {
            resultString.append(serializeArray(fieldValue));
        } else if (Collection.class.isInstance(fieldValue)) {
            resultString.append(serializeCollection(fieldValue));
        } else {
            resultString.append(serializeObject(fieldValue));
        }
        return resultString.toString();
    }
    /**
     * Сериализация коллекции.
     * @param fieldValue - объект для сериализации.
     * @return - строка.
     * @throws IllegalAccessException - .
     */
    private String serializeCollection(Object fieldValue) throws IllegalAccessException {
        StringBuilder resultString = new StringBuilder();
        resultString.append("[");
        Collection collection = (Collection) fieldValue;
        for (Object object : collection) {
            resultString.append(serializeFieldValue(object));
            resultString.append(",");
        }
        resultString.deleteCharAt(resultString.length() - 1);
        resultString.append("]");
        return resultString.toString();
    }
    /**
     * Сериализация массива.
     * @param fieldValue - объект для сериализации.
     * @return - строка.
     * @throws IllegalAccessException - .
     */
    private String serializeArray(Object fieldValue) throws IllegalAccessException {
        StringBuilder resultString = new StringBuilder();
        resultString.append("[");
        int length = Array.getLength(fieldValue);
        for (int i = 0; i < length; i++) {
            Object element = Array.get(fieldValue, i);
            resultString.append(serializeFieldValue(element));
            resultString.append(",");
        }
        resultString.deleteCharAt(resultString.length() - 1);
        resultString.append("]");
        return resultString.toString();
    }
}
