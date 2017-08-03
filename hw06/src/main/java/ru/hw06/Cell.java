package ru.hw06;

import java.util.Iterator;

public class Cell implements Comparable<Cell>, Iterable<Cell> {
    /**
     * Номинал.
     */
    private final int nominal;
    /**
     * Кол-во банкнот.
     */
    private int count;
    /**
     * Ссылка не следующую ячейку.
     */
    private Cell next;
    /**
     * Конструктор.
     * @param nominal - номинал.
     * @param count - кол-во.
     */
    public Cell(int nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }
    /**
     * Конструктор копирования.
     * @param cell - ячейка для копирования.
     */
    public Cell(Cell cell) {
        this.nominal = cell.nominal;
        this.count = cell.count;
        this.next = cell.next;
    }
    /**
     * Снятие суммы.
     * @param requested - запрашиваемая сумма.
     * @return - результат.
     */
    public boolean withdraw(int requested) {
        int expectedCount = Math.min(requested / nominal, count);
        int expectedCash = expectedCount * nominal;
        boolean nextCellResult = true;
        if (requested != expectedCash) {
            nextCellResult = next != null && next.withdraw(requested - expectedCash);
        }
        if(nextCellResult) {
            count = count - expectedCount;
            return true;
        }
        return false;
    }
    /**
     * Геттер для номинала.
     * @return - номинал.
     */
    public int getNominal() {
        return nominal;
    }
    /**
     * Геттер для кол-ва.
     * @return - кол-во.
     */
    public int getCount() {
        return count;
    }
    /**
     * Сеттер для следующей ячейки.
     * @param next - следующая ячейка.
     */
    public void setNext(Cell next) {
        this.next = next;
    }
    /**
     * Получить баланс.
     * @return - баланс.
     */
    public int getBalance() {
        return count * nominal;
    }
    /**
     * Копировать ячейку.
     * @return - копия ячейки.
     */
    public Cell copy() {
        return new Cell(this);
    }

    @Override
    public int compareTo(Cell o) {
        if (nominal > o.getNominal())
            return 1;
        if (nominal < o.getNominal())
            return -1;
        return 0;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            Cell current = Cell.this;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Cell next() {
                Cell before = current;
                current = current.next;
                return before;
            }
        };
    }
}
