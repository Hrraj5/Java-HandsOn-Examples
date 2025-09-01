package org.example.javaexamples;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    static class SharedResource{
        private Lock lock = new ReentrantLock();
        private String name;

        SharedResource(String name){
            this.name =  name;
        }

        public ReentrantLock getLock(){
            return (ReentrantLock) this.lock;
        }
    }

    public static void main(String[] args) {
        SharedResource fork = new SharedResource("fork");
        SharedResource spoon = new SharedResource("spoon");

        Runnable task1 = ()->{
            while(true) {
                try {
                    if (spoon.getLock().tryLock(100, TimeUnit.MILLISECONDS)) {
                        try {
                            if (fork.getLock().tryLock(100, TimeUnit.MILLISECONDS)) {
                                System.out.println(Thread.currentThread().getName() + " Trying to acquire both the locks.");
                                Thread.sleep(1000);
                                break; // If it's success
                            }
                        } catch (Exception e) {

                        } finally {
                            if (fork.getLock().isHeldByCurrentThread())
                                fork.getLock().unlock();
                        }
                    }
                } catch (Exception e) {

                } finally {
                    if (spoon.getLock().isHeldByCurrentThread())
                        spoon.getLock().unlock();
                }
                try {
                    System.out.println(Thread.currentThread().getName() + " Retrying....");
                    Thread.sleep(50);
                } catch (Exception e) {

                }
            }

        };



        Thread t1 = new Thread(task1,"Thread - 1");
        Thread t2 = new Thread(task1, "Thread - 2");
        t1.start();
        t2.start();
    }
}
