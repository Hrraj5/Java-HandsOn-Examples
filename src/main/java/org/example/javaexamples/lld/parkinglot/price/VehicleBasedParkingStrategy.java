package org.example.javaexamples.lld.parkinglot.price;


import org.example.javaexamples.lld.parkinglot.ticket.Ticket;
import org.example.javaexamples.lld.parkinglot.vehicle.Vehicle;
import org.example.javaexamples.lld.parkinglot.vehicle.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class VehicleBasedParkingStrategy implements PriceStrategy {
    private final static Map<VehicleType,Double> vehicleTypeDoubleMap = new HashMap<>();
    static {
        vehicleTypeDoubleMap.put(VehicleType.TWO_WHEELER,20d);
        vehicleTypeDoubleMap.put(VehicleType.FOUR_WHEELER,40d);
    }
    @Override
    public Double calculatePrice(Ticket ticket) {
        Vehicle vehicle = ticket.getVehicle();
        return vehicleTypeDoubleMap.get(vehicle.getVehicleType());
    }
}
