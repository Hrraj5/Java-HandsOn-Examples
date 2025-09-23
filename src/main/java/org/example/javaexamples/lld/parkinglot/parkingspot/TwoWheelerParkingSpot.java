package org.example.javaexamples.lld.parkinglot.parkingspot;

import org.example.javaexamples.lld.parkinglot.vehicle.Vehicle;

public class TwoWheelerParkingSpot extends ParkingSpot {

    public TwoWheelerParkingSpot(int id, boolean isEmpty, Vehicle vehicle){
        super(id,isEmpty,vehicle);
    }

    @Override
    public double getPrice() {
        return 10;
    }
}
