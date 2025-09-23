package org.example.javaexamples.lld.parkinglot;

import org.example.javaexamples.lld.parkinglot.parkingspot.ParkingSpot;
import org.example.javaexamples.lld.parkinglot.parkingspot.manager.ParkingSpotManager;
import org.example.javaexamples.lld.parkinglot.price.VehicleBasedParkingStrategy;
import org.example.javaexamples.lld.parkinglot.vehicle.Vehicle;
import org.example.javaexamples.lld.parkinglot.vehicle.VehicleType;

import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        List<ParkingSpot> twoWheelerParkingSpotList = Arrays.asList(
                new ParkingSpot(1,false,null),
                new ParkingSpot(2,false,null)
        );

        List<ParkingSpot> fourWheelerParkingSpotList = Arrays.asList(
                new ParkingSpot(3,false,null),
                new ParkingSpot(4,false,null),
                new ParkingSpot(5,false,null)
        );

       ParkingLotFactory parkingLotFactory = ParkingLotFactory.getParkingLotFactoryInstance();
       ParkingLot parkingLotInstance = ParkingLot.getParkingLotInstance();

       ParkingSpotManager parkingSpotManager = parkingLotFactory.getTypeOfParkingLot(VehicleType.TWO_WHEELER,twoWheelerParkingSpotList);
       parkingLotInstance.setParkingSpotManager(parkingSpotManager);
       parkingLotInstance.setPriceStrategy(new VehicleBasedParkingStrategy());

       // park the vehicle:
        parkingLotInstance.parkVehicle(new Vehicle("AD-8989", VehicleType.TWO_WHEELER));
        parkingLotInstance.parkVehicle(new Vehicle("AD-9909", VehicleType.TWO_WHEELER));

        System.out.println("Total Two Wheeler Parking Spots : "+  parkingLotInstance.getParkingSpotManager().totalParkingSpots());
        System.out.println("Available Two Wheeler Parking Spots : "+  parkingLotInstance.getParkingSpotManager().availableParkingSpots());
        System.out.println("Non available Two Wheeler Parking Spots :" + parkingLotInstance.getParkingSpotManager().nonAvailableParkingSpots());
    }
}
