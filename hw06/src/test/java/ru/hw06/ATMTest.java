package ru.hw06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ATMTest {

    private ATMDepartment department;

    @Before
    public void init() {
        department = new ATMDepartment();
    }

    @Test
    public void balanceSimple() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(5, 10));
        ATM atm = new ATM(cells, department);
        Assert.assertEquals(50, atm.getBalance());
    }

    @Test
    public void withdrawSimple() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(1, 10));
        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));

        ATM atm = new ATM(cells, department);
        int balance = atm.getBalance();

        Assert.assertTrue(atm.withdraw(25));
        Assert.assertEquals(balance - 25, atm.getBalance());
    }

    @Test
    public void withdrawFail() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(5, 10));
        cells.add(new Cell(10, 10));

        ATM atm = new ATM(cells, department);
        int balance = atm.getBalance();

        Assert.assertFalse(atm.withdraw(27));
        Assert.assertEquals(balance, atm.getBalance());
    }

    @Test
    public void withdrawEdgeCase() {
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(20, 3));
        cells.add(new Cell(50, 1));

        ATM atm = new ATM(cells, department);
        Assert.assertTrue(atm.withdraw(60));
        Assert.assertEquals(50, atm.getBalance());
    }
}
