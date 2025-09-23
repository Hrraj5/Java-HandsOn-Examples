package org.example.javaexamples.lld.parkinglot.parkingspot;

import org.example.javaexamples.lld.parkinglot.vehicle.Vehicle;

public class ParkingSpot {

    private int id;
    private boolean isEmpty;
    private Vehicle vehicle;
    private double price;

    public ParkingSpot(){}

    public ParkingSpot(int id, boolean isEmpty, Vehicle vehicle) {
        this.id = id;
        this.isEmpty = true;
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
