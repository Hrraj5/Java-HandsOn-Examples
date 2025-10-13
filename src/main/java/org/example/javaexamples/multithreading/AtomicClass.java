package org.example.javaexamples.multithreading;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String name;
    int score;

    public User(String name, int score){
        this.name = name;
        this.score = score;
    }
}
class LeaderBoard{
    private final AtomicReference<User> atomicReference = new AtomicReference<>( new User("Alice",0));
    public void incrementScore(){
        while(true){
            User prevUser = this.atomicReference.get();
            User currUser = new User(prevUser.name, prevUser.score+1);

            if(atomicReference.compareAndSet(prevUser,currUser)){
                System.out.println("Score updated successfully");
                break;
            }
        }
    }
}
public class AtomicClass {
    public static void main(String[] args) {
        LeaderBoard lb = new LeaderBoard();
        Runnable task = lb::incrementScore;

        for(int i=1;i<=5;i++){
            new Thread(task,"Thread - " + i).start();
        }
    }
}
