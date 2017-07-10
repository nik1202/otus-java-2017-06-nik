package ru.hw05.tests;

import ru.hw05.annotations.After;
import ru.hw05.annotations.Before;
import ru.hw05.annotations.Test;

/**
 * Created by nikolay on 10/07/17.
 */
public class ClassForTest01 {

    @Test
    public void testerTest01() {
        System.out.println("Test 01 " + getClass());
    }

    @Before
    public void testerBefore() {
        System.out.println("Before " + getClass());
    }

    @After
    public void testerAfter() {
        System.out.println("After " + getClass());
    }

    @Test
    public void testerTest02() {
        System.out.println("Test 02 " + getClass());
    }

    public void testerTest03() {
        System.out.println("Test 03 " + getClass());
    }
}
