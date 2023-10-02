package com.agrauberg.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RunnablePractice implements Runnable {
    @Override
    public void run() {
        System.out.println("RUNNABLE executes without returning anything");
        System.out.println("RUNNABLE executing on thread: " + Thread.currentThread().getName());
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        RunnablePractice demo = new RunnablePractice();

        System.out.println("Called RUNNABLE from thread " + Thread.currentThread().getName());
        demo.run();

        System.out.println("Called RUNNABLE on Executor");
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.submit(demo);

        executorService.shutdown();
    }
}
