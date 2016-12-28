package com.gojek.problem.service.jdbi;

/**
 * Created by piyushsinha.c on 27/12/16.
 */
public interface DBI {
    String parkCar(String registrationNo, String color);
    String leaveParkingLot(int parkingSlotNo);
    void parkingStatus();
    String registrationNosofCarsWithColor(String color);
    String slotNosOfCarsWithColor(String color);
    String slotNoBasedOnRegistrationNo(String registrationNo);
    String createParkingLot(int maxNoOfVehicles);
}
