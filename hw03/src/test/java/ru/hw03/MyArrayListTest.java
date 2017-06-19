package ru.hw03;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nik on 6/19/2017.
 */
public class MyArrayListTest {
    /**
     * Тест на копирование листа.
     */
    @Test
    public void whenCopyArraysThenResult() {
        List<Integer> mList = new MyArrayList<>();

        mList.add(1);
        mList.add(2);
        mList.add(3);
        mList.add(4);
        //ArrayList т.к. не определен конструктор public MyArrayList(Collection<? extends E> c).
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[mList.size()]));
        Collections.copy(list, mList);
        assertThat(mList, is(list));
    }

    /**
     * Тест на сортировку листа.
     */
    @Test
    public void whenSortArrayThenResult() {
        List<Integer> mList = new MyArrayList<>();

        mList.add(2);
        mList.add(1);
        mList.add(4);
        mList.add(3);

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.equals(o2))
                    return 0;
                if ((o1 - o2) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        List<Integer> testData = Arrays.asList(1, 2, 3, 4);
        Collections.sort(mList, comp);

        assertThat(mList, is(testData));
    }

    /**
     * Тест на добавление коллекции в коллекцию.
     */
    @Test
    public void whenAddAllToArrayThenResult() {
        List<Integer> mList1 = new MyArrayList<>();
        mList1.add(1);
        mList1.add(2);
        mList1.add(3);

        List<Integer> mList2 = new MyArrayList<>();
        mList2.add(4);
        mList2.add(5);

        mList1.addAll(mList2);

        List<Integer> testData = Arrays.asList(1, 2, 3, 4, 5);
        assertThat(mList1, is(testData));
    }

    /**
     * Тест на добавление произвольного числа значений в коллекцию.
     */
    @Test
    public void whenAddAllToArray2ThenResult() {
        List<Integer> mList = new MyArrayList<>();
        mList.add(1);
        mList.add(2);
        mList.add(3);

        Collections.addAll(mList, 4, 5);

        List<Integer> testData = Arrays.asList(1, 2, 3, 4, 5);
        assertThat(mList, is(testData));
    }
}
