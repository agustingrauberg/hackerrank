package com.agrauberg.practice;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsThreadPool {

    private static final ThreadPoolExecutor etp = new ThreadPoolExecutor(
            2,
            Runtime.getRuntime().availableProcessors(),
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    private static int sharedCounter = 0;

    public static void increment() {
        for (int i = 0; i < 1000; i++) {
            sharedCounter++;
            System.out.printf("THREAD %s : %s", Thread.currentThread().getName(), sharedCounter);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();
        etp.execute(ExecutorsThreadPool::increment);
        etp.shutdown();

        if (etp.awaitTermination(1, TimeUnit.MINUTES)) {
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double elapsedTimeInMilliseconds = (double) elapsedTime / 1_000_000; // 1 millisecond = 1,000,000 nanoseconds
            System.out.printf("---------------------- TOOK %s MS-----------------------------------------------------", elapsedTimeInMilliseconds);
        }
    }
}
