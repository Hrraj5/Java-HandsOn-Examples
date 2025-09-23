package org.example.javaexamples.lld.parkinglot.parkingspot.manager;

import org.example.javaexamples.lld.parkinglot.parkingspot.ParkingSpot;

import java.util.List;

public class TwoWheelerSpotParkingManager extends ParkingSpotManager {

    public TwoWheelerSpotParkingManager(List<ParkingSpot> parkingSpotList){
        super(parkingSpotList);
    }
    @Override
    ParkingSpot findParkingSpot() {
        // implementation to finding the parking spot for two wheeler;
        return null;
    }
}
