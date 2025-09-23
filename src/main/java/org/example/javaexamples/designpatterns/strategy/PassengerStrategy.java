package org.example.javaexamples.designpatterns.strategy;

public class PassengerStrategy implements DriveStrategy{
    @Override
    public void drive() {
        System.out.println("Passenger vehicle enabled");
    }
}
