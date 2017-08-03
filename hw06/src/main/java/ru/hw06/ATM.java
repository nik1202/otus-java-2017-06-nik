package ru.hw06;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ATM implements Observer {
    /**
     * Первый элемент ячеек с деньгами.
     */
    private Cell first;
    /**
     * Снимок начального состояния.
     */
    private final ATMMemento memento;
    /**
     * Департамент к которому относиться ATM.
     */
    private final ATMDepartment department;
    /**
     * Конструктор.
     * @param cells - список ячеек с деньгами.
     * @param department - департамент.
     */
    public ATM(List<Cell> cells, ATMDepartment department) {
        Collections.sort(cells);
        first = cells.get(0);
        linkCells(cells);
        memento = new ATMMemento(first);
        this.department = department;
        this.department.addObserver(this);
    }
    /**
     * Снятие денег.
     * @param requested - запрашиваемая сумма.
     * @return - успешен ли результат.
     */
    public boolean withdraw(int requested) {
        return first.withdraw(requested);
    }
    /**
     * Получить баланс.
     * @return - баланс.
     */
    public int getBalance() {
        Iterator<Cell> iterator = first.iterator();
        int balance = 0;
        while (iterator.hasNext()) {
            balance += iterator.next().getBalance();
        }
        return balance;
    }
    /**
     * Составить цепочку селлов.
     * @param cells - лист селлов.
     */
    private void linkCells(List<Cell> cells) {
        Iterator<Cell> iterator = cells.iterator();
        Cell cellA = iterator.next();
        while (iterator.hasNext()) {
            Cell cellB = iterator.next();
            cellA.setNext(cellB);
            cellA = cellB;
        }
    }
    /**
     * Установить состояние в начальное.
     */
    private void resetState() {
        first = memento.list.get(0);
        linkCells(memento.list);
    }

    @Override
    public void update() {
        resetState();
    }
    /**
     * Снимок состояния.
     */
    class ATMMemento {
        /**
         * Лист ячеек навчального состояния.
         */
        private final List<Cell> list;
        /**
         * Конструктор.
         * @param first - первый элемент в цепочке ячеек.
         */
        public ATMMemento(final Cell first) {
            list = new LinkedList<>();
            Iterator<Cell> iterator = first.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().copy());
            }
        }
    }
}
