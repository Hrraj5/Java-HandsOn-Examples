package org.example.javaexamples.lld.parkinglot;

import lombok.Getter;
import lombok.Setter;
import org.example.javaexamples.lld.parkinglot.parkingspot.ParkingSpot;
import org.example.javaexamples.lld.parkinglot.parkingspot.manager.ParkingSpotManager;
import org.example.javaexamples.lld.parkinglot.price.PriceStrategy;
import org.example.javaexamples.lld.parkinglot.price.VehicleBasedParkingStrategy;
import org.example.javaexamples.lld.parkinglot.ticket.Ticket;
import org.example.javaexamples.lld.parkinglot.vehicle.Vehicle;

import java.time.Instant;

@Setter
@Getter
public class ParkingLot {

    private static ParkingLot parkingLotInstance;
    private PriceStrategy priceStrategy;
    private ParkingSpotManager parkingSpotManager;
    private ParkingLot(){}

    public static ParkingLot getParkingLotInstance() {
        if(parkingLotInstance == null){
            return new ParkingLot();
        }
        return parkingLotInstance;
    }

    public void parkVehicle(Vehicle vehicle){
        ParkingSpot parkingSpot = this.parkingSpotManager.getParkingSpot();
        parkingSpot.setEmpty(false);
        parkingSpot.setVehicle(vehicle);
        double defaultPrice = parkingSpot.getPrice();
        Ticket ticket = new Ticket(vehicle, Instant.now(),null,parkingSpot);

        if(priceStrategy instanceof VehicleBasedParkingStrategy){
            System.out.println("Price for two wheeler parking spot is : "+ priceStrategy.calculatePrice(ticket));
        }
    }
}
