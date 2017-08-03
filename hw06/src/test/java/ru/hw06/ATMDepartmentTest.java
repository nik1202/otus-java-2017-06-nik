package ru.hw06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 03/08/17.
 */
public class ATMDepartmentTest {

    private ATMDepartment department;
    private List<Cell> cells1;
    private List<Cell> cells2;
    private ATM atm1;
    private ATM atm2;

    @Before
    public void init() {
        department = new ATMDepartment();

        cells1 = new ArrayList<>();
        cells1.add(new Cell(1, 10)); // 10
        cells1.add(new Cell(5, 10)); // 50
        cells1.add(new Cell(10, 10)); // 100

        cells2 = new ArrayList<>();
        cells2.add(new Cell(2, 10)); // 20
        cells2.add(new Cell(5, 10)); // 50
        cells2.add(new Cell(15, 10)); // 150

        atm1 = new ATM(cells1, department);
        atm2 = new ATM(cells2, department);
    }

    @Test
    public void updateAllATMs() {
        int balance1 = atm1.getBalance();
        Assert.assertTrue(atm1.withdraw(25));
        Assert.assertEquals(balance1 - 25, atm1.getBalance());

        int balance2 = atm2.getBalance();
        Assert.assertTrue(atm2.withdraw(50));
        Assert.assertEquals(balance2 - 50, atm2.getBalance());

        department.notifyObserver();

        Assert.assertEquals(balance1, atm1.getBalance());
        Assert.assertEquals(balance2, atm2.getBalance());
    }

    @Test
    public void getBalance() {
        int balance1 = atm1.getBalance();
        int balance2 = atm2.getBalance();

        Assert.assertEquals(balance1 + balance2, department.getTotalBalance());

    }
}
