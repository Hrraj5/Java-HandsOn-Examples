package org.example.javaexamples;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ThreadLocalLeakExample {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("Japan","Tokyo");
        map.put("India","Delhi");
        map.put("USA","Washington DC");
        map.entrySet().stream().sorted((a,b)-> a.getKey().compareTo(b.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1, LinkedHashMap::new));
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
