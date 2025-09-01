package org.example.javaexamples;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition2Example {
    static class SharedResource{
        private final AtomicInteger atomicInteger = new AtomicInteger(0);
        public void increment(){
           if(atomicInteger.get() <100){
               atomicInteger.incrementAndGet();
           }
        }

        public int getCount(){
            return atomicInteger.get();
        }
    }

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(()->{
            for(int i=1;i<=1000;i++){
                sharedResource.increment();
            }
        });

        Thread t2= new Thread(()->{
            for(int i=1;i<=1000;i++){
                sharedResource.increment();
            }
        });
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch(InterruptedException exception){

        }
        System.out.println(sharedResource.getCount());

    }
}
