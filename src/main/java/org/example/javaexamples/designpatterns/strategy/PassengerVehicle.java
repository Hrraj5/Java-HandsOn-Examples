package org.example.javaexamples.designpatterns.strategy;

public class PassengerVehicle extends Vehicle{

    PassengerVehicle( ) {
        super(new PassengerStrategy());
    }
}
