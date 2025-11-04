package org.example.javaexamples.multithreading;

import java.util.concurrent.locks.ReentrantLock;

class SharedResource{
    private String name;
    SharedResource(String s){
        this.name = s;
    }
    public ReentrantLock getLock(){
        return new ReentrantLock();
    }
}
public class  Deadlock1 {

    public static void main(String[] args) {
        SharedResource spoon = new SharedResource("spoon");
        SharedResource fork = new SharedResource("fork");


        Runnable task1 = ()->{
            try {
                if (spoon.getLock().tryLock()) {
                    System.out.println("Thread name :" + Thread.currentThread() + " spoon acquired ");
                    Thread.sleep(2000);
                    try {
                        if (fork.getLock().tryLock()) {
                            System.out.println("Thread name :" + Thread.currentThread() + " fork acquired ");
                        }
                    } finally {
                        if (fork.getLock().isHeldByCurrentThread()) {
                            fork.getLock().unlock();
                            ;
                        }
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
               if(spoon.getLock().isHeldByCurrentThread()){
                   spoon.getLock().unlock();
               }
            }
        };


//        Runnable task1 = ()->{
//            synchronized (spoon){
//                try {
//                    Thread.sleep(2000);
//                    synchronized (fork){
//                        System.out.println("Thread name :" + Thread.currentThread() + " fork acquired ");
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("Thread name :" + Thread.currentThread() + " spoon acquired ");
//            }
//        };
//
//        Runnable task2 = ()->{
//            synchronized (spoon){
//                try {
//                    Thread.sleep(2000);
//                    synchronized (fork){
//                        System.out.println("Thread name :" + Thread.currentThread() + " fork acquired ");
//                    }
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("Thread name :" + Thread.currentThread() + " spoon acquired ");
//            }
//        };

        Thread t1 = new Thread(task1,"Thread - 1");
        Thread t2 = new Thread(task1,"Thread - 2");

        t1.start();
        t2.start();
    }
}
