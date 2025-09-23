package org.example.javaexamples.designpatterns.strategy;

public class SportsVehicle extends Vehicle{


    SportsVehicle() {
        super(new SportsStrategy());
    }
}
