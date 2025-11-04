package org.example.javaexamples.multithreading.waitandnotify;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        List<Integer> oddNumber = List.of(1,3,5,7,9,11,13,15,17,19);
        List<Integer> evenNumber = List.of(2,4,6,8,10,12,14,16,18,20);
        List<String> alphabets = List.of("A","B","C","D","E","F");
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        PrintOddEven printOddEven = new PrintOddEven(new OddNumber(), new EvenNumber());
        NumberAlphabet numberAlphabet = new NumberAlphabet(new Number(),new Alphabets());
        Thread t1 = new Thread(()->{
            for(int num : oddNumber){
                try {
                    printOddEven.printOddNumber(num);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(()->{
            for(int num : evenNumber){
                try {
                    printOddEven.printEvenNumber(num);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

//        t1.start();
//        t2.start();

        Thread t11 = new Thread(()->{
            for(String s : alphabets){
                try {
                    numberAlphabet.printAlphabets(s);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t12 = new Thread(()->{
            for(int num: numbers){
                try {
                    numberAlphabet.printNumber(num);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t11.start();
        t12.start();
    }
}
