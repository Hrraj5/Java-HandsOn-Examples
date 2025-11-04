package org.example.javaexamples.multithreading.waitandnotify;

public class PrintOddEven {
    private OddNumber oddNumber;
    private EvenNumber evenNumber;
    private boolean oddFlag;

    PrintOddEven(OddNumber oddNumber,EvenNumber evenNumber){
        this.oddNumber = oddNumber;
        this.evenNumber = evenNumber;
        this.oddFlag = true;
    }

    public synchronized void printOddNumber(int num) throws InterruptedException {
        while(!oddFlag){
            wait();
        }
        this.oddNumber.printOddNumber(num);
        oddFlag = false;
        notifyAll();
    }

    public synchronized void printEvenNumber(int num) throws InterruptedException {
        while(oddFlag){
            wait();
        }
        this.evenNumber.printEvenNumber(num);
        oddFlag = true;
        notifyAll();
    }
}
