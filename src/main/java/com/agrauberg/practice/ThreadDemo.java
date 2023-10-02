package com.agrauberg.practice;

class ThreadPractice extends Thread {
    public void run() {
        System.out.println("Running on Thread --> " + Thread.currentThread().getName());
        System.out.println("Classes that extend Thread can not extend anything else");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println("START!!!   From thread " + Thread.currentThread().getName());
        new ThreadPractice().start();

        System.out.println("RUN!!!   From thread " + Thread.currentThread().getName());
        new ThreadPractice().run();
    }
}
