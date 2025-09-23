package org.example.javaexamples.lld.parkinglot;

import org.example.javaexamples.lld.parkinglot.parkingspot.ParkingSpot;
import org.example.javaexamples.lld.parkinglot.parkingspot.manager.FourWheelerParkingSpotManager;
import org.example.javaexamples.lld.parkinglot.parkingspot.manager.ParkingSpotManager;
import org.example.javaexamples.lld.parkinglot.parkingspot.manager.TwoWheelerSpotParkingManager;
import org.example.javaexamples.lld.parkinglot.price.PriceStrategy;
import org.example.javaexamples.lld.parkinglot.vehicle.VehicleType;

import java.util.List;

public class ParkingLotFactory {

    private static ParkingLotFactory parkingLotFactoryInstance;

    private ParkingLotFactory(){}

    public static ParkingLotFactory getParkingLotFactoryInstance(){
        if(parkingLotFactoryInstance == null){
            return new ParkingLotFactory();
        }
        return parkingLotFactoryInstance;
    }

    public ParkingSpotManager getTypeOfParkingLot(VehicleType vehicleType, List<ParkingSpot> parkingSpotList){
        return switch (vehicleType) {
            case VehicleType.FOUR_WHEELER -> new FourWheelerParkingSpotManager(parkingSpotList);
            case VehicleType.TWO_WHEELER -> new TwoWheelerSpotParkingManager(parkingSpotList);
        };
    }

}
