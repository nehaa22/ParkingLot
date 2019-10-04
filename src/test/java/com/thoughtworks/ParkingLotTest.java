package com.thoughtworks;

import com.thoughtworks.Consumer.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    class DummyOwner extends Owner{

        private boolean notifyMessage =false;
        int counter = 0;
        int freeSpace = 0;

        @Override
        public  void informFullSpace(){
            notifyMessage = true;
            counter++;
        }
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() throws ParkingLotException {
        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(1,owner);

        Object object1 = new Object();
        assertTrue(parkingLot.park(object1));
    }


    @Test
    void givenParkingLotWithSameObject_WhenWePark_ThenShouldThrowException() throws ParkingLotException {
        Owner owner = new Owner();

        ParkingLot parkingLot = new ParkingLot(1,owner);
        Object vehicle = new Object();

        parkingLot.park(vehicle);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithFullcapacity_WhenPark_ThenShouldNotBeAbleToPark() throws ParkingLotException {
        Owner owner = new Owner();

        ParkingLot parkingLot = new ParkingLot(1,owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws ParkingLotException {
        Owner owner = new Owner();

        ParkingLot parkingLot = new ParkingLot(1,owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle,parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLot_WhenUnParkOneVehicleWithoutPark_ThenShouldNotBeAbleToUnParkIt() throws ParkingLotException {
        Owner owner = new Owner();

        ParkingLot parkingLot = new ParkingLot(1,owner);
        Object vehicle = new Object();
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenParkingLotWithFullCapacity_WhenPark_ThenShouldNotifyOwner() throws ParkingLotException {
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertTrue(dummyOwner.notifyMessage);
        Assertions.assertEquals(1,dummyOwner.counter);
    }

    @Test
    void givenParkingLotWithFullCapacity_WhenParkOneCar_ThenShouldNotifyOwnerThatSpaceIsAvailable() throws ParkingLotException {
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertTrue(dummyOwner.notifyMessage);
        Assertions.assertEquals(1,dummyOwner.counter);
    }


}
