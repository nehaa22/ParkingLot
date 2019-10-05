package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Object> parkObject;
    private int capacity;
    private Subscribers IOwner;
    private Subscribers securityGuard;
    private List<Subscribers> observer;

    public ParkingLot(int capacity, List<Subscribers> observer){
        parkObject = new ArrayList<>();
        this.capacity = capacity;
        this.observer = observer;
    }

    public void park(Object object) throws VehicleAlreadyAvailableException, ParkingLotException {

        if (isFreeSpace()) {
            if (isAlreadyParked(object)) {
                throw new VehicleAlreadyAvailableException();
            }
            parkObject.add(object);
            if (isFull()) {
                for (Subscribers type: observer) {
                    type.informFullSpace();
                }
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
                for (Subscribers type: observer) {
                    type.informFreeSpace();
                }
            }
            return object;
        }
        throw new VehicleAlreadyAvailableException();
    }
}





