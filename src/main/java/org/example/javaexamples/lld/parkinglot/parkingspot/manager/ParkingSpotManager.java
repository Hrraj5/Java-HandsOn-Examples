package org.example.javaexamples.lld.parkinglot.parkingspot.manager;

import lombok.Getter;
import org.example.javaexamples.lld.parkinglot.parkingspot.ParkingSpot;

import java.util.List;

public abstract class ParkingSpotManager {

    @Getter
    private final List<ParkingSpot> parkingSpotList;

    ParkingSpotManager(List<ParkingSpot> parkingSpotList){
        this.parkingSpotList = parkingSpotList;
    }
    abstract ParkingSpot findParkingSpot();

    public void addParking(ParkingSpot parkingSpot){
        this.parkingSpotList.add(parkingSpot);
    }

    public void removeParking(ParkingSpot parkingSpot){
        this.parkingSpotList.remove(parkingSpot);
    }

    public int availableParkingSpots(){
        return this.parkingSpotList.stream().filter(ParkingSpot::isEmpty).toList().size();
    }
    public int nonAvailableParkingSpots() {
        return this.parkingSpotList.stream().filter(parkingSpot -> !parkingSpot.isEmpty()).toList().size();
    }
    public int totalParkingSpots(){
        return this.parkingSpotList.size();
    }

    public ParkingSpot getParkingSpot(){
        return this.parkingSpotList.stream().filter(ParkingSpot::isEmpty).toList().getFirst();
    }
}
