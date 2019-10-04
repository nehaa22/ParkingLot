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

    public boolean park(Object object) throws ParkingLotException {

        if (isAlreadyFull()) {
            if (isAlreadyParked(object)) {
                throw new ParkingLotException("Vehical already parked");
            }
            parkObject.add(object);
            if(isFull())
            {owner.informFullSpace();}
            return true;
        }
        throw new ParkingLotException("Parking lot full");
    }

    private boolean isAlreadyParked(Object object) {
        return parkObject.contains(object);
    }

    private boolean isAlreadyFull() {
        return parkObject.size() < capacity;
    }

    private boolean isFull()
    {return parkObject.size() == capacity;}

    public Object unPark(Object object) throws ParkingLotException {
        if (isAlreadyParked(object)) {
            parkObject.remove(object);
            if(parkObject.size() == capacity-1) {
                owner.informFreeSpace();
            }
            return object;
        }
        throw new ParkingLotException("vehical not found");
    }
}





