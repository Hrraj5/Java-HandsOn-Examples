package org.example.javaexamples.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bank {
    private final Lock bankLock = new ReentrantLock();
    private int balance;

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (this.balance > amount) {
            this.balance -= amount;
        }
    }

    public void getBalance() {
        System.out.println("Balance is " + this.balance);
    }

    public ReentrantLock getLock() {
        return (ReentrantLock) bankLock;
    }
}

public class BankService1 {
    public static void main(String[] args) {
        Bank tx = new Bank();
        Thread t1 = new Thread(() -> {
            tx.getLock().lock();
            System.out.println(tx);
            try {
                System.out.println("Money credited : " + 100);
                tx.deposit(100);
            } finally {
                tx.getLock().unlock();
            }
        });
        Thread t2 = new Thread(() -> {
            tx.getLock().lock();
            System.out.println(tx);
            try {
                tx.withdraw(20);
                System.out.println("Money debited : " + 20);
            } finally {
                tx.getLock().unlock();
            }
        });
        Thread t3 = new Thread(() -> {
            tx.getLock().lock();
            System.out.println(tx);
            try {
                tx.deposit(70);
                System.out.println("Money credited : " + 70);
            } finally {
                tx.getLock().unlock();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {

        }
        tx.getBalance();
    }

}
