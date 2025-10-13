package org.example.javaexamples.multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchClass {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Runnable task = ()->{
            System.out.println("Task started by "+ Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            countDownLatch.countDown();
            System.out.println("Task completed by "+ Thread.currentThread().getName());
        };

        Thread t1 = new Thread(task,"Thread - 1");
        Thread t2 = new Thread(task,"Thread - 2");
        Thread t3 = new Thread(task,"Thread - 3");

        t1.start();
        t2.start();
        t3.start();
        try{
            System.out.println("Waiting for tasks to complete...");
            countDownLatch.await();
            System.out.println("All tasks completed!" );
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
