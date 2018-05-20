package ru.hw14;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelSortTest {
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, Integer.MAX_VALUE / 1000).forEach(list::add);
        Collections.shuffle(list);

        long before = System.currentTimeMillis();
        ParallelSort<Integer> parallelSort = new ParallelSort<>(list, Integer::compareTo, 4);
        parallelSort.sort();
        long after = System.currentTimeMillis();
        System.out.println("4 threads = " + (after - before));

        Collections.shuffle(list);
        long before1 = System.currentTimeMillis();
        ParallelSort<Integer> parallelSort1 = new ParallelSort<>(list, Integer::compareTo, 1);
        parallelSort1.sort();
        long after1 = System.currentTimeMillis();
        System.out.println("1 thread = " + (after1 - before1));

        Collections.shuffle(list);
        long before2 = System.currentTimeMillis();
        list.sort(Integer::compareTo);
        long after2 = System.currentTimeMillis();
        System.out.println("collections.sort = " + (after2 - before2));

        Assert.assertTrue((after - before) < (after1 - before1));
        Assert.assertTrue((after - before) < (after2 - before2));
    }
}
