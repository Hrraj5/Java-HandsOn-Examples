package org.example.javaexamples.multithreading;
class Resource{
    private int count = 0;

    public synchronized void incrementCount(){
        count++;
    }
    public int getCount(){
        return count;
    }
}
public class AtomicExample {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Runnable task = ()->{
            for(int i=1;i<=1000;i++){
                resource.incrementCount();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);
        Thread t5 = new Thread(task);
        Thread t6 = new Thread(task);
        Thread t7 = new Thread(task);
        Thread t8 = new Thread(task);
        Thread t9 = new Thread(task);
        Thread t10 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();
        }catch (Exception e){

        }
        System.out.println(resource.getCount());
    }
}
