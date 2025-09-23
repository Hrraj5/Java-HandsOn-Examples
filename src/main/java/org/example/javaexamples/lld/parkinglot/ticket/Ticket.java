package org.example.javaexamples.lld.parkinglot.ticket;

import org.example.javaexamples.lld.parkinglot.parkingspot.ParkingSpot;
import org.example.javaexamples.lld.parkinglot.vehicle.Vehicle;

import java.time.Instant;

public class Ticket {

    private Vehicle vehicle;
    private Instant entryTime;
    private Instant exitTime;
    private ParkingSpot parkingSpot;

    public Ticket(Vehicle vehicle, Instant entryTime, Instant exitTime, ParkingSpot parkingSpot) {
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.parkingSpot = parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Instant getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Instant entryTime) {
        this.entryTime = entryTime;
    }

    public Instant getExitTime() {
        return exitTime;
    }

    public void setExitTime(Instant exitTime) {
        this.exitTime = exitTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
