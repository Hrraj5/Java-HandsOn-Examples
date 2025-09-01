package org.example.javaexamples;


public class DeadlockExample {
    static class SharedResource{
        private String name;

        SharedResource(String name){
            this.name = name;
        }
    }

    public static void main(String[] args) {
        SharedResource spoon = new SharedResource("spoon");
        SharedResource fork  = new SharedResource("fork");

        Thread t1 = new Thread(()->{
            synchronized (spoon){
                System.out.println(Thread.currentThread().getName() + " Asking for spoon");
                try{
                    Thread.sleep(3000);
                    synchronized (fork){
                        System.out.println(Thread.currentThread().getName() + " Asking for fork");
                    }
                }catch(Exception e){

                }
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (fork){
                System.out.println(Thread.currentThread().getName() + " Asking for fork");
                try{
                    Thread.sleep(3000);
                    synchronized (spoon){
                        System.out.println(Thread.currentThread().getName() + " Asking for spoon");
                    }
                }catch(Exception e){

                }
            }
        });
        t1.start();
        t2.start();

    }
}
