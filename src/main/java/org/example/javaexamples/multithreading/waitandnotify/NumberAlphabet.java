package org.example.javaexamples.multithreading.waitandnotify;

public class NumberAlphabet {

    private Number number;
    private Alphabets alphabets;
    private boolean isAlpha = true;

    NumberAlphabet(Number number, Alphabets alphabets){
        this.alphabets = alphabets;
        this.number = number;
    }

    public synchronized void printNumber(int num) throws InterruptedException {
        while(isAlpha){
            wait();
        }
        number.printNumber(num);
        isAlpha = true;
        notifyAll();
    }

    public synchronized void printAlphabets(String s) throws InterruptedException {
        while(!isAlpha){
            wait();
        }
        alphabets.printAlphabets(s);
        isAlpha = false;
        notifyAll();
    }
}
