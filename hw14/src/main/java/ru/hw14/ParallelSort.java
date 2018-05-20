package ru.hw14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelSort<T> {

    private List<T> target;
    private Comparator<T> comparator;
    private Integer numberOfThreads;
    private ExecutorService executor;

    public ParallelSort(List<T> target, Comparator<T> comparator, Integer numberOfThreads) {
        this.target = target;
        this.comparator = comparator;
        this.numberOfThreads = numberOfThreads;
        executor = Executors.newFixedThreadPool(numberOfThreads);
    }


    public List<T> sort () {
        List<T> result = merge(prepare());
        executor.shutdown();
        return result;
    }

    private List<T> merge(List<Future<List<T>>> futures)  {
        List<T> result = new ArrayList<>();
        for (Future<List<T>> future : futures) {
            try {
                result = merge2Lists(result, future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private List<T> merge2Lists(List<T> x, List<T> y) {
        List<T> result = new ArrayList<>();
        int indexX = 0;
        int indexY = 0;
        for (int i = 0; i < x.size() + y.size(); i++) {
            if (indexX == x.size()) {
                result.addAll(y.subList(indexY, y.size()));
                break;
            }
            if (indexY == y.size()) {
                result.addAll(x.subList(indexX, x.size()));
                break;
            }
            if (comparator.compare(x.get(indexX), y.get(indexY)) < 0) {
                result.add(x.get(indexX));
                indexX++;
                continue;
            }
            if (comparator.compare(x.get(indexX), y.get(indexY)) > 0) {
                result.add(y.get(indexY));
                indexY++;
            }
        }
        return result;
    }

    private List<Future<List<T>>> prepare() {
        int numberOfItemsInOnePart = target.size() / numberOfThreads;
        int begin = 0;
        int end = numberOfItemsInOnePart;

        List<Future<List<T>>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            futures.add(executor.submit(new Part(target.subList(begin, end))));
            begin = end;
            end = (numberOfThreads - i) == 2 ? target.size() : end + numberOfItemsInOnePart;
        }
        return futures;
    }

    private class Part implements Callable<List<T>> {
        private List<T> part;

        Part(List<T> part) {
            this.part = part;
        }

        @Override
        public List<T> call() {
            part.sort(comparator);
            return part;
        }
    }
}
