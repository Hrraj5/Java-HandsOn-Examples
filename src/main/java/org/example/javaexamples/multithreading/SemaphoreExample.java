package org.example.javaexamples.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        int numberOfAtms = 3;
        int customers = 10;
        int rounds = 2;
        Semaphore atmSemaphore = new Semaphore(numberOfAtms);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(customers, ()->
            System.out.println("All customers finished the round!\n"));


        Runnable task = ()->{
            for(int i=1;i<=rounds;i++){
                try{
                    atmSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " starts using ATM in round " + i);

                    Thread.sleep((long) (Math.random() * 2000)); // simulate withdrawal

                    System.out.println(Thread.currentThread().getName() + " finishes using ATM in round " + i);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                 atmSemaphore.release();
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        for(int i=1;i<=customers;i++){
            new Thread(task," Customer- " + i).start();
        }
    }
}
