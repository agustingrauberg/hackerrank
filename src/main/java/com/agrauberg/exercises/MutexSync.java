package com.agrauberg.exercises;

public class MutexSync {

    private static int sharedCounter = 0;

    public static synchronized void syncedIncrement() {
        for (int i = 0; i < 1000; i++) {
            sharedCounter++;
            System.out.printf("SYNC %s : %s%n", Thread.currentThread().getName(), sharedCounter);
        }
    }

    public static void nonSyncedIncrement() {
        for (int i = 0; i < 1000; i++) {
            sharedCounter++;
            System.out.printf("UN-SYNC %s : %s%n", Thread.currentThread().getName(), sharedCounter);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();

        /** FORCE ORDERED PRINTING  **/
        Thread thread1 = new Thread(MutexSync::syncedIncrement);
        Thread thread2 = new Thread(MutexSync::syncedIncrement);

        thread1.setName("T1");
        thread2.setName("T2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        double elapsedTimeInMilliseconds = (double) elapsedTime / 1_000_000; // 1 millisecond = 1,000,000 nanoseconds
        System.out.printf("----------------------SYNC TOOK %s MS-----------------------------------------------------%n", elapsedTimeInMilliseconds);

        System.out.println("---------------------------------------------------------------------------");
        /** RUN WITHOUT ORDER  **/
        Thread thread3 = new Thread(MutexSync::nonSyncedIncrement);
        Thread thread4 = new Thread(MutexSync::nonSyncedIncrement);

        startTime = System.nanoTime();

        thread3.setName("T3");
        thread4.setName("T4");
        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        elapsedTimeInMilliseconds = (double) elapsedTime / 1_000_000;
        System.out.printf("----------------------NON-SYNC TOOK %s MS-----------------------------------------------------%n", elapsedTimeInMilliseconds);

    }
}
