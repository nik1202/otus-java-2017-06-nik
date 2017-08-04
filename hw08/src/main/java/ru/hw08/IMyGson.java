package ru.hw08;

/**
 * Created by nikolay on 04/08/17.
 */
public interface IMyGson {
    /**
     * Сериализация объекта в строку.
     * @param o - объект.
     * @return - строка формата json.
     */
    String toJson(Object o);
}
