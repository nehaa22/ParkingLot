package com.thoughtworks;

import com.thoughtworks.Consumer.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test // TODO - test is incomplete - there is not parking done.
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() throws Exception {
        Owner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1,owner);

        Object vehicle = new Object();
        Assertions.assertDoesNotThrow(()->parkingLot.park(vehicle));
    }


    @Test
    void givenParkingLotWithSameObject_WhenWePark_ThenShouldThrowException() throws Exception {
        Owner owner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(2,owner);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        assertThrows(VehicleAlreadyAvailableException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithFullcapacity_WhenPark_ThenShouldNotBeAbleToPark() throws Exception {
        Owner owner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(1,owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws Exception {
        Owner owner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(1,owner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle,parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLot_WhenUnParkOneVehicleWithoutPark_ThenShouldNotBeAbleToUnParkIt() throws ParkingLotException {
        Owner owner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(1,owner);
        Object vehicle = new Object();
        assertThrows(VehicleAlreadyAvailableException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenParkingLotWithFullCapacity_WhenPark_ThenShouldNotifyOwner() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        Assertions.assertEquals(1,dummyOwner.counter);
    }

    @Test
    void givenParkingLotWithFullCapacity_WhenParkOneCar_ThenShouldNotifyOwnerThatSpaceIsAvailable() throws Exception {
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        Assertions.assertEquals(1,dummyOwner.counter);

        parkingLot.unPark(vehicleOne);
        Assertions.assertEquals(1,dummyOwner.freeSpace);
    }

}
