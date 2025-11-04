package org.example.javaexamples.multithreading.waitandnotify;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProducerAndConsumer {

    Queue<Integer> queue = new LinkedList<>();

    ProducerAndConsumer(){
    }

    public synchronized void produce(int num) throws InterruptedException {
        while(!queue.isEmpty()){
            wait();
        }
        System.out.println("Thread name :  "+ Thread.currentThread().getName() +" has produced the item :" + num);
        queue.add(num);
        notifyAll();;
    }
    public synchronized void consumer() throws InterruptedException {
        while(queue.isEmpty()){
            wait();
        }
        System.out.println("Thread name :  "+ Thread.currentThread().getName() +" has consumed the item :" + queue.poll());
        notifyAll();
    }
}
