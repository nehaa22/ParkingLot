package com.thoughtworks;

import com.thoughtworks.Consumer.IOwner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void givenParkingLotWithOneCapacity_WhenWePark_ThenShouldBeAbleToPark() throws Exception {
        IOwner IOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(1, IOwner);

        Object vehicle = new Object();
        Assertions.assertDoesNotThrow(()->parkingLot.park(vehicle));
    }


    @Test
    void givenParkingLotWithSameObject_WhenWePark_ThenShouldThrowException() throws Exception {
        IOwner IOwner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(2, IOwner);
        Object vehicle = new Object();

        parkingLot.park(vehicle);

        assertThrows(VehicleAlreadyAvailableException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithFullcapacity_WhenPark_ThenShouldNotBeAbleToPark() throws Exception {
        IOwner IOwner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(1, IOwner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(vehicle);
        });
    }

    @Test
    void givenParkingLotWithOneCapacity_WhenParkOneVehicleAndUnParkIt_ThenShouldBeAbleToUnParkIt() throws Exception {
        IOwner IOwner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(1, IOwner);

        Object vehicle = new Object();
        parkingLot.park(vehicle);

        assertEquals(vehicle,parkingLot.unPark(vehicle));
    }

    @Test
    void givenParkingLot_WhenUnParkOneVehicleWithoutPark_ThenShouldNotBeAbleToUnParkIt() throws ParkingLotException {
        IOwner IOwner = new DummyOwner();

        ParkingLot parkingLot = new ParkingLot(1, IOwner);
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
