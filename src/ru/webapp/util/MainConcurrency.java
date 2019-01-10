package ru.webapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainConcurrency {
    public static final int THREAD_NUMBER = 10000;
    private static int counter = 0;
    //    private static final Object LOCK = new Object();
    private static final Lock LOCK = new ReentrantLock();
    private static final AtomicInteger atomicCounter = new AtomicInteger();
    private static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private static final Lock writeLock = READ_WRITE_LOCK.writeLock();
    private static final Lock readLock = READ_WRITE_LOCK.readLock();
    private static final ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));

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
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService completionService = new ExecutorCompletionService(executorService);

        //List<Thread> threads = new ArrayList<>(THREAD_NUMBER);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mc.incr();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mc.incr();
//                }
//                latch.countDown();
//            });
//            thread.start();
//           threads.add(thread);
        }
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        threads.forEach(thread -> {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(counter);
        System.out.println(atomicCounter.get());
    }

    private void incr() {
//        double a = Math.sin(1.324);
//        synchronized (MainConcurrency.class) { The same as written in method declaration
//        synchronized (MainConcurrency.LOCK) {
//        synchronized (this) {  //if a method isn't static
//        LOCK.lock();
//        try {
//            counter++;
//        } finally {
//            LOCK.unlock();
//        }
        atomicCounter.incrementAndGet();
    }
}
