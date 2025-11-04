package org.example.javaexamples.multithreading.waitandnotify;

public class MainAppPC {
    public static void main(String[] args) {
        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();

        Thread t1 = new Thread(()->{
            for(int i=1;i<=5;i++){
                try {
                    producerAndConsumer.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=1;i<=6;i++){
                try {
                    producerAndConsumer.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
