//package com.thoughtworks.Consumer;
//
//import com.thoughtworks.ParkingLot;
//import com.thoughtworks.ParkingLotException;
//import com.thoughtworks.VehicleAlreadyAvailableException;
//
//public class Sanjay {
//
//    public static void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) throws Exception {
//
//        try {
//            System.out.println("park to parkingLotOne");
//            final Object A = new Object();
//
//            parkingLotOne.park(A);
//            parkingLotOne.park(A);
//
//        } catch (ParkingLotException e) {
//            System.out.println("exception  " + e.getMessage());
//        }
//
//        try {
//            System.out.println("park to parkingLotOne");
//            final Object A = new Object();
//            final Object B = new Object();
//            final Object C = new Object();
//
//
//            parkingLotOne.park(A);
//            parkingLotOne.park(B);
//            parkingLotOne.park(C);
//        } catch (ParkingLotException e) {
//            System.out.println("exception  " + e.getMessage());
//
//        }
//        System.out.println("create Car C object");
//
//        final Object C = new Object();
//        System.out.println("Created ");
//
//        parkingLotTwo.park(C);
//        System.out.println("Added car c in two " + C);
//
//      Object vehical= parkingLotTwo.unPark(C);
//      System.out.println("Unparked " + C);
//
////      try {
////          Object vehical2 = parkingLotTwo.unPark(C);
////      }catch VehicleAlreadyAvailableException() {
////
////      }
//
//    }
//
////    public static void main(String[] args) throws Exception {
////        Owner owner = new DummyOwner();
////
////        park(new ParkingLot(2,owner), new ParkingLot(3,owner));
////    }
//}
