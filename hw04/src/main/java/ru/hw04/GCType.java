package ru.hw04;

/**
 * Created by nik on 6/27/2017.
 */
public class GCType {
    /**
     * Название GC.
     */
    private String name;
    /**
     * Кол-во сборок.
     */
    private int count;
    /**
     * Время сборок.
     */
    private long time;
    /**
     * Конструктор.
     * @param name - название GC.
     */
    public GCType(String name) {
        this.name = name;
        count = 0;
        time = 0;
    }
    /**
     * Добавляет единицу к кол-ву сборок.
     */
    public void countInc() {
        this.count++;
    }
    /**
     * Прибавляет время очередной сборки к общему.
     * @param t - время сборки.
     */
    public void timeAdd(long t) {
        this.time += t;
    }

    /**
     * Геттер названия CG.
     * @return - название CG.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Геттер кол-ва сборок.
     * @return - кол-во сборок.
     */
    public int getCount() {
        return this.count;
    }
    /**
     * Геттер времени сборок.
     * @return - время сборок.
     */
    public double getTime() {
        return this.time;
    }
}
