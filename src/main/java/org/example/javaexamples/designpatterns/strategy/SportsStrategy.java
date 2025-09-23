package org.example.javaexamples.designpatterns.strategy;

public class SportsStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Sports Drive enabled");
    }
}
