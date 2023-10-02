package com.agrauberg.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Practice {

    @FunctionalInterface
    interface DummyFunc { Object doDummyStuff(Object p1, Object p2); }

    public static void main(String[] args) {
        parallelCounterNTS();
        parallelCounterTS();
    }

    private static void doDummy() {
        DummyFunc dummy = (p1, p2) -> {
            int sum = (int) p1 + (int) p2;
            System.out.println("Dummy functions don't do dummy things! - Sum: " + sum);
            return sum;
        };
        dummy.doDummyStuff(10,2);
    }

    private static void compare() {
        // Create and populate
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 5_000_000; i++) {
            items.add(String.valueOf(i));
        }

        long start = System.nanoTime();
        double total = items.parallelStream().mapToDouble(Double::parseDouble).map(d -> d/2).sum();
        long end = System.nanoTime();
        long elapsed = end - start;
        System.out.println("Parallel\t----> total is:\t%s\t|\tTook\t%s ".formatted(total, elapsed));


        start = System.nanoTime();
        total = items.stream().mapToDouble(Double::parseDouble).map(d -> d/2).sum();
        end = System.nanoTime();
        elapsed = end - start;
        System.out.println("Secuential\t----> total is:\t%s\t|\tTook\t%s ".formatted(total, elapsed));
    }

    private static void parallelCounterNTS() {
        // Create and populate
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            items.add(i);
        }

        // Not thread safe counter
        int[] counter = {0};

        // Run in parallel
        items.parallelStream().forEach(x -> counter[0]++);

        System.out.println("Non-Thread-Safe\t|\tExpected 10000\t|\tGot: " + counter[0]);
    }

    private static void parallelCounterTS() {
        // Create and populate
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            items.add(i);
        }

        // Non thread safe counter
        int[] counter = {0};

        // Run in parallel
        items.parallelStream().forEach(x -> {
            synchronized (counter) { //Can synchronize or either use an atomic counter
                counter[0]++;
            }
        });

        System.out.println("Thread-Safe\t|\tExpected 10000\t|\tGot: " + counter[0]);
    }

    private static void threadPool() {
        int availableCores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(availableCores);

    }
}
