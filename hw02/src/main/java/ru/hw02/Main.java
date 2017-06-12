package ru.hw02;

import java.util.function.Supplier;

/**
 * Created by nik on 6/12/2017.
 *
 * Запускать с опцией -XX:-UseTLAB.
 */

public class Main {
    /**
     * Объект исследования.
     */
    private Object o;
    /**
     * Вычисление кол-ва свободной памяти.
     * @return - кол-во свободной памяти.
     */
    private long free() {
        return Runtime.getRuntime().freeMemory();
    }
    /**
     * Получение кол-ва занимаемой памяти объектом.
     * @param sup - supplier.
     * @return - кол-во занимаемой паняти.
     */
    public long test(Supplier<Object> sup) {
        long free1 = free();
        o = sup.get();
        long free2 = free();
        return free1 - free2;
    }

}
