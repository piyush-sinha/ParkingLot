package com.gojek.problem.service.application;

import com.gojek.problem.service.jdbi.DBI;
import com.gojek.problem.service.jdbi.DBIFactory;

/**
 * Created by piyushsinha.c on 27/12/16.
 */
public class ParkingLotApplication {
    private DBIFactory dbiFactory;
    private DBI dbi;

    public ParkingLotApplication() {
        dbiFactory = new DBIFactory();
        dbi = dbiFactory.build();
    }

    public static void main(String[] args) {
        ParkingLotApplication parkingLotApplication = new ParkingLotApplication();
        DBI dbi = parkingLotApplication.dbi;

        System.out.println(dbi.createParkingLot(6));
        System.out.println(dbi.parkCar("KA­01­HH­1234", "White"));
        System.out.println(dbi.parkCar("KA­01­HH­9999", "White"));
        System.out.println(dbi.parkCar("KA­01­BB­0001", "Black"));
        System.out.println(dbi.parkCar("KA­01­HH­7777", "Red"));
        System.out.println(dbi.parkCar("KA­01­HH­2701", "Blue"));
        System.out.println(dbi.parkCar("KA­01­HH­3141", "Black"));

        System.out.println(dbi.leaveParkingLot(4));
        System.out.println(dbi.leaveParkingLot(2));

        System.out.println(dbi.parkCar("KA­01­P­333", "White"));
        System.out.println(dbi.parkCar("DL­12­AA­9999", "Red"));

        System.out.println(dbi.registrationNosofCarsWithColor("White"));
        System.out.println(dbi.slotNosOfCarsWithColor("White"));
        System.out.println(dbi.slotNoBasedOnRegistrationNo("KA­01­HH­3141"));
        System.out.println(dbi.slotNoBasedOnRegistrationNo("MH­04­AY­1111"));

    }
}
