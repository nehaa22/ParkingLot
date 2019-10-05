package com.thoughtworks;

import com.thoughtworks.Consumer.Owner;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Object> parkObject;
    private int capacity;
    private Owner owner;

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        parkObject = new ArrayList<>();
        this.owner = owner;
    }

    public void park(Object object) throws VehicleAlreadyAvailableException, ParkingLotException {

        if (isFreeSpace()) {
            if (isAlreadyParked(object)) {
                throw new VehicleAlreadyAvailableException();
            }
            parkObject.add(object);
            if (isFull()) {
                owner.informFullSpace();
            }
        } else {
            throw new ParkingLotException();
        }
    }

    private boolean isAlreadyParked(Object object) {
        return parkObject.contains(object);
    }

    private boolean isFreeSpace() {
        return parkObject.size() < capacity;
    }


    private boolean isFull() {
        return parkObject.size() == capacity;
    }

    public Object unPark(Object object) throws VehicleAlreadyAvailableException {
        if (isAlreadyParked(object)) {
            parkObject.remove(object);
            if (parkObject.size() == capacity - 1) {
                owner.informFreeSpace();
            }
            return object;
        }
        throw new VehicleAlreadyAvailableException();
    }
}





