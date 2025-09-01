package org.example.javaexamples;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalLeakExample {
    public static void main(String[] args) {
        final ThreadLocal<byte []> local = new ThreadLocal<>();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(()->{
            for(int i=1;i<=5;i++){
                local.set(new byte [1024*1024]);
                System.out.println("New Task id " +  i);
            }
        });

        executorService.shutdown();
    }
}
