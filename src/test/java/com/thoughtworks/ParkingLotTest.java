package com.thoughtworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1,dummySecurityGuard,dummyOwner);

        Object vehicle = new Object();
        Assertions.assertDoesNotThrow(()->parkingLot.park(vehicle));
    }


    @Test
    void givenParkingLotWithSameObject_WhenWePark_ThenShouldThrowException() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, dummySecurityGuard,dummyOwner);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        assertThrows(VehicleAlreadyAvailableException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithFullcapacity_WhenPark_ThenShouldNotBeAbleToPark() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1, dummySecurityGuard,dummyOwner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(1, dummySecurityGuard,dummyOwner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle,parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLot_WhenUnParkOneVehicleWithoutPark_ThenShouldNotBeAbleToUnParkIt() throws ParkingLotException {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1,dummySecurityGuard,dummyOwner);
        Object vehicle = new Object();
        assertThrows(VehicleAlreadyAvailableException.class, () -> {
            parkingLot.unPark(vehicle);
        });
    }

    @Test
    void givenParkingLotWithFullCapacity_WhenPark_ThenShouldNotifyOwner() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummySecurityGuard,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        Assertions.assertEquals(1,dummyOwner.counter);
    }

    @Test
    void givenParkingLotWithFullCapacity_WhenParkOneCar_ThenShouldNotifyOwnerThatSpaceIsAvailable() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummySecurityGuard,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        Assertions.assertEquals(1,dummyOwner.counter);

        parkingLot.unPark(vehicleOne);
        Assertions.assertEquals(1,dummyOwner.freeSpace);
    }

    @Test
    void givenParkingLotWithFullCapacity_WhenPark_ThenShouldNotifySecurityGuard() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummySecurityGuard,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        Assertions.assertEquals(1,dummySecurityGuard.counter);
    }

    @Test
    void givenParkingLotWithFullCapacity_WhenParkOneCar_ThenShouldNotifySecurityGuardThatSpaceIsAvailable() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummySecurityGuard,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        Assertions.assertEquals(1,dummySecurityGuard.counter);

        parkingLot.unPark(vehicleOne);
        Assertions.assertEquals(1,dummySecurityGuard.freeSpace);
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotifyOwnerAndSecurityGuardThatSpaceIsAvailable() throws Exception {
        DummySecurityGuard dummySecurityGuard = new DummySecurityGuard();
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2,dummySecurityGuard,dummyOwner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        Assertions.assertEquals(1,dummySecurityGuard.counter);
        Assertions.assertEquals(1,dummyOwner.counter);

        parkingLot.unPark(vehicleOne);
        Assertions.assertEquals(1,dummySecurityGuard.freeSpace);
        Assertions.assertEquals(1,dummyOwner.freeSpace);
    }


}
