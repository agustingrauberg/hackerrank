package com.agrauberg.practice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class CallablePractice implements Callable<Integer> {

    int num;

    public CallablePractice(int num) {
        this.num = num;
    }

    @Override
    public Integer call() {
        System.out.println("Executing CALLABLE on thread " + Thread.currentThread().getName());
        int fact = 1;
        while (num > 1) {
            fact *= num;
            num--;
        }
        System.out.println("Waiting on CALLABLE ---------> 2s");
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException ie) {
            System.out.println("Waiting interrupted...");
        }
        return fact;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        CallablePractice demo = new CallablePractice(10);
        System.out.println("Running CALLABLE from thread " + Thread.currentThread().getName());
        System.out.println("Callable output is: " + demo.call());

        System.out.println("Running CALLABLE on executor!!");
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executor.submit(demo);

        executor.shutdown();
    }
}