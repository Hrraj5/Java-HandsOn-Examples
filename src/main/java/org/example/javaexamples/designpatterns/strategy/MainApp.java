package org.example.javaexamples.designpatterns.strategy;

public class MainApp {
    public static void main(String[] args) {

        Vehicle vehicle = new SportsVehicle();
        vehicle.drive();

        Vehicle vehicle1 = new PassengerVehicle();
        vehicle1.drive();
    }
}
