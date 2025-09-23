package org.example.javaexamples.lld.parkinglot.parkingspot.manager;

import org.example.javaexamples.lld.parkinglot.parkingspot.ParkingSpot;

import java.util.List;

public class FourWheelerParkingSpotManager extends ParkingSpotManager{

    public FourWheelerParkingSpotManager(List<ParkingSpot> parkingSpotList){
        super(parkingSpotList);
    }
    @Override
    ParkingSpot findParkingSpot() {
        // Implement the logic to find the parking spot for four wheeler.
        return null;
    }
}
