package ru.hw06;

/**
 * Created by nikolay on 03/08/17.
 */
public interface Subject {
    /**
     * Добавить слушалетя.
     * @param o - слушатель.
     */
    void addObserver(Observer o);
    /**
     * Запустить обновление.
     */
    void notifyObserver();
}
