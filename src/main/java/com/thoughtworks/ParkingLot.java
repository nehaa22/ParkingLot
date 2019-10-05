package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List<Object> parkObject;
    private int capacity;
    private IOwner IOwner;
    private  IOwner securityGuard;


    public ParkingLot(int capacity,IOwner IOwner) {
        this.capacity = capacity;
        parkObject = new ArrayList<>();
        this.IOwner = IOwner;
    }

    public ParkingLot(int capacity,IOwner IOwner,IOwner securityGuard){
        this.capacity = capacity;
        this.IOwner  = IOwner;
        this.securityGuard = securityGuard;
    }

    public void park(Object object) throws VehicleAlreadyAvailableException, ParkingLotException {

        if (isFreeSpace()) {
            if (isAlreadyParked(object)) {
                throw new VehicleAlreadyAvailableException();
            }
            parkObject.add(object);
            if (isFull()) {
                IOwner.informFullSpace();
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
                IOwner.informFreeSpace();
            }
            return object;
        }
        throw new VehicleAlreadyAvailableException();
    }
}





