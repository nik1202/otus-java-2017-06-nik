package ru.hw05;

import ru.hw05.tests.ClassForTest01;
import ru.hw05.tests.ClassForTest02;

/**
 * Created by nikolay on 10/07/17.
 */
public class Main {
    /**
     * Точка входа.
     * @param args - аргументы.
     */
    public static void main(String[] args) {
        Class[] array = {ClassForTest01.class, ClassForTest02.class};
        try {
            Analyzer.start(array);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
