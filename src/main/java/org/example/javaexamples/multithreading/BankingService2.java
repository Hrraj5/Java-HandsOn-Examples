package org.example.javaexamples.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bank1 {
    private int accountNumber;
    private int balance;

    public Bank1(int accountNumber, int balance){
        this.accountNumber =  accountNumber;
        this.balance = balance;
    }

    private final Lock lock = new ReentrantLock();

    public void deposit(int amount){
        this.balance+=amount;
    }

    public boolean withdraw(int amount){
        if(this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
    public ReentrantLock getLock(){
        return (ReentrantLock) lock;
    }
    public void getBalance(){
        System.out.println("Account Number : "+ this.accountNumber + " has balance " + this.balance);
    }
}
public class BankingService2 {

    public static void main(String[] args) {
        Bank1 account1 = new Bank1(1,100);
        Bank1 account2 = new Bank1(2,100);

        Runnable task =  ()->{
            account1.getLock().lock();
            account2.getLock().lock();
            try{
                if(account1.withdraw(100))
                    account2.deposit(100);
            }finally {
                account2.getLock().unlock();
                account1.getLock().unlock();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        t1.start();
        t2.start();
        t3.start();
        try{
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        account1.getBalance();
        account2.getBalance();
    }
}
