package ru.hw05;

import ru.hw05.annotations.After;
import ru.hw05.annotations.Before;
import ru.hw05.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by nikolay on 10/07/17.
 */
public class Analyzer {
    /**
     * Анализ и запуск тестов.
     * @param clazz - массив классов для анализа.
     */
    public static void start(Class[] clazz) throws NoSuchMethodException {

        for (Class cl : clazz) {
            Method[] methods = cl.getMethods();

            Method before = Analyzer.class.getDeclaredMethod("empty");
            Method after = Analyzer.class.getDeclaredMethod("empty");

            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class)) {
                    before = method;
                }
                if (method.isAnnotationPresent(After.class)) {
                    after = method;
                }
            }

            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    try {
                        before.invoke(before.getDeclaringClass().newInstance());
                        method.invoke(method.getDeclaringClass().newInstance());
                        after.invoke(after.getDeclaringClass().newInstance());
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("---------------------------------");
        }
    }
    /**
     * метод заглушка на случай отсутствия в классе аннотаций Before и After.
     */
    private void empty() {}
}
