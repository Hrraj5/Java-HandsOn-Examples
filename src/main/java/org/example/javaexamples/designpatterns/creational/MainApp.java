package org.example.javaexamples.designpatterns.creational;

public class MainApp {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);

        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton);
    }
}
