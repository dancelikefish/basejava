package ru.webapp.util;

public class ThreadDeadlock {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        deadlock(lock1, lock2);
        deadlock(lock2, lock1);
    }

    private static void deadlock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("waiting for lock 1...");
            synchronized (lock1) {
                System.out.println("holding lock 1...");
            }
            try {
                Thread.sleep(45);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waiting for lock 2...");
            synchronized (lock2) {
                System.out.println("holding lock 1 & 2...");
            }
        }).start();
    }
}
