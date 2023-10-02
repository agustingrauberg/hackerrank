package com.agrauberg.practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

    public static void executeFuture() throws ExecutionException, InterruptedException {
        CallablePractice callable = new CallablePractice(3);
        try (ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {

            System.out.println("Calling CALLABLE to Future...");
            Future<Integer> future = executorService.submit(callable);
            System.out.println("Executed");

            System.out.println("Now I'm doing some other stuff.........");

            System.out.println("Future completed!!!! Returned: " + future.get());
            executorService.shutdown();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        executeFuture();
    }
}
