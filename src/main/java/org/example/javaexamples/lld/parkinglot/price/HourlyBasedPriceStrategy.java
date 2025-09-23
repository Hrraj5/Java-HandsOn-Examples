package org.example.javaexamples.lld.parkinglot.price;

import org.example.javaexamples.lld.parkinglot.ParkingLotFactory;
import org.example.javaexamples.lld.parkinglot.parkingspot.ParkingSpot;
import org.example.javaexamples.lld.parkinglot.ticket.Ticket;

public class HourlyBasedPriceStrategy implements PriceStrategy{

    private static final double perHourPrice = 5d;
    private final ParkingSpot parkingspot;

    HourlyBasedPriceStrategy(ParkingSpot parkingSpot){
        this.parkingspot = parkingSpot;
    }
    @Override
    public Double calculatePrice(Ticket ticket) {
        // logic to get the hour from exit time - entry time
        return this.parkingspot.getPrice() * 1;
    }
}
