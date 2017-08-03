package ru.hw06;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 01/08/17.
 */
public class ATMDepartment implements Subject {
    /**
     * Слушатели.
     */
    private List<Observer> observers;
    /**
     * Конструктор.
     */
    public ATMDepartment() {
        this.observers = new ArrayList<>();
    }
    /**
     * Получить баланс всех ATM.
     * @return - итоговый баланс.
     */
    public int getTotalBalance() {
        int result = 0;
        for(Observer o : observers) {
            result += o.getBalance();
        }
        return result;
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer o : observers) {
            o.update();
        }
    }
}
