package ru.hw02;

import org.junit.Ignore;
import org.junit.Test;
import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nik on 6/12/2017.
 *
 * Запускать с опцией -XX:-UseTLAB.
 */
@Ignore
public class MainTest {
    /**
     * Тест не пустой строки.
     */
    @Test
    public void whenStringThenResult() {
        Main main = new Main();
        long result = main.test(() -> new String("Hello, world !"));
        long testData = 96;
        assertThat(testData, is(result));
    }

    /**
     * Тест объекта.
     */
    @Test
    public void whenObjectThenResult() {
        Main main = new Main();
        long result = main.test(() -> new Object());
        long testData = 16;
        assertThat(testData, is(result));
    }

    /**
     * Тест пустой строки.
     */
    @Test
    public void whenEmptyStringThenResult() {
        Main main = new Main();
        long result = main.test(() -> new String());
        long testData = 24;
        assertThat(testData, is(result));
    }

    /**
     * Тест пустого ArrayList.
     */
    @Test
    public void whenArrayThenResult() {
        Main main = new Main();
        long result = main.test(() -> new ArrayList());
        long testData = 24;
        assertThat(testData, is(result));
    }
}
