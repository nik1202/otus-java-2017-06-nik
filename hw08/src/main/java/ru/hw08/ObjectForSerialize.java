package ru.hw08;

import java.util.List;

/**
 * Created by nikolay on 04/08/17.
 */
public class ObjectForSerialize {
    /**
     * Поля для проверки сериализации.
     */
    private int integer;
    private String string;
    private double[] doubles;
    private List<Integer> list;
    /**
     * Поле которое не должно сериализоваться.
     */
    private transient String transField;
    /**
     * Конструктор для инициализации полей.
     */
    public ObjectForSerialize(int integer, String string, double[] doubles, List<Integer> list, String transField) {
        this.integer = integer;
        this.string = string;
        this.doubles = doubles;
        this.list = list;
        this.transField = transField;
    }
}
