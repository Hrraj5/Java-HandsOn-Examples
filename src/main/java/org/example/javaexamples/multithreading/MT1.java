package org.example.javaexamples.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MT1 {
    public static class Counter{
        private int count;
        //private final AtomicInteger atomicInteger =  new AtomicInteger(0);

//        public int getAtomicCount(){
//            return atomicInteger.get();
//        }
//
//        public void incrementCount(){
//            atomicInteger.incrementAndGet();
//        }
        public int getCount(){
            return count;
        }

        public synchronized void incrementCount(){
            count++;
        }
    }
    public static void main(String[] args) {
        Counter counter = new Counter();
        Lock lock1 = new ReentrantLock();
        Thread t1 = new Thread(()->{
            lock1.lock();
            try{
                for(int i=1;i<=1000;i++){
                    counter.incrementCount();
                }
            }finally {
                lock1.unlock();
            }

        });
        Thread t2 = new Thread(()->{
            lock1.lock();
            try{
                for(int i=1;i<=1000;i++){
                    counter.incrementCount();
                }
            }finally {
                
                lock1.unlock();
            }
        });
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch(Exception e){

        }
        System.out.println(counter.getCount());
    }
}
