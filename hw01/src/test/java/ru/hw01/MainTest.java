package ru.hw01;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nik on 6/5/2017.
 */
public class MainTest {
    /**
     * Test.
     */
    @Test
    public void whenThen() {
        Main main = new Main();
        String result = main.sayHello();
        String testData = "Hello, world!";

        assertThat(testData, is(result));
    }

}
