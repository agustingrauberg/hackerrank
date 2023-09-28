package com.agrauberg.exercises;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class TaskCompletionRace {
    public static void main(String[] args) {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> sleepRandomSeconds("Task 1"));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> sleepRandomSeconds("Task 2"));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> sleepRandomSeconds("Task 3"));

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(task1, task2, task3);

        try {
            anyOf.get(); // Wait for any of the tasks to complete
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Cancel the other tasks if anyOf completes
        task1.cancel(true);
        task2.cancel(true);
        task3.cancel(true);

        System.out.println("One of the tasks completed.");
    }

    private static void sleepRandomSeconds(String taskName) {
        int randomSeconds = ThreadLocalRandom.current().nextInt(1, 6); // Random sleep between 1 to 5 seconds
        try {
            Thread.sleep(randomSeconds * 1000);
            System.out.println(taskName + " completed after " + randomSeconds + " seconds.");
        } catch (InterruptedException e) {
            System.out.println(taskName + " was interrupted.");
        }
    }
}