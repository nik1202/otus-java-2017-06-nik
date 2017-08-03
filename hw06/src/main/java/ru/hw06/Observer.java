package ru.hw06;

/**
 * Created by nikolay on 03/08/17.
 */
public interface Observer {
    /**
     * Обновить состояние ATM до начального.
     */
    void update();
    /**
     * Получить баланс ATM.
     * @return - баланс.
     */
    int getBalance();
}
