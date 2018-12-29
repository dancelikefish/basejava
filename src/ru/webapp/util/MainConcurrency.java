package ru.webapp.util;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREAD_NUMBER = 10000;
    private static int counter = 0;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        Thread thread1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + ", "
                + Thread.currentThread().getState()));
        thread1.start();
        System.out.println(thread0.getState());
        MainConcurrency mc = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREAD_NUMBER);

        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mc.incr();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private void incr() {
        double a = Math.sin(1.324);
//        synchronized (MainConcurrency.class) { The same as written in method declaration
//        synchronized (MainConcurrency.LOCK) {
        synchronized (this) {  //if a method isn't static
            counter++;
        }
    }
}