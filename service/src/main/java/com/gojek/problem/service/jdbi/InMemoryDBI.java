package com.gojek.problem.service.jdbi;

import com.gojek.problem.models.Car;
import com.gojek.problem.service.utils.Constants;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Created by piyushsinha.c on 28/12/16.
 */
public class InMemoryDBI implements DBI {
    private Map<Integer, Car> slotToCarMapping;
    private Map<String, Car> registrationToCarMapping;
    private Multimap<String,Car> colorToCarMapping;
    private PriorityQueue<Integer> vacantSlots;
    private int maxNoOfVehicles;

    public InMemoryDBI() {
        this(-1);
    }

    public InMemoryDBI(int maxNoOfVehicles) {
        this.slotToCarMapping = Maps.newTreeMap();
        this.registrationToCarMapping = Maps.newTreeMap();
        this.colorToCarMapping = ArrayListMultimap.create();
        this.maxNoOfVehicles = maxNoOfVehicles;
    }

    public String parkCar(String registrationNo, String color) {
        if (!isParkingLotCreated()) {
            return Constants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (vacantSlots.isEmpty()) {
            return Constants.PARKING_FULL_MSG;
        } else {
            int slotNum = vacantSlots.remove();
            Car newCar = new Car(slotNum, registrationNo, color);
            colorToCarMapping.put(color, newCar);
            registrationToCarMapping.put(registrationNo, newCar);
            slotToCarMapping.put(slotNum, newCar);
            return String.format(Constants.PARKING_ALLOT_MSG, slotNum);
        }
    }

    public String leaveParkingLot(int parkingSlotNo) {
        if (!isParkingLotCreated()) {
            return Constants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (slotToCarMapping.containsKey(parkingSlotNo)) {
            Car carToLeave = slotToCarMapping.remove(parkingSlotNo);
            registrationToCarMapping.remove(carToLeave.getRegistrationNumber());
            List<Car> colorList = (List<Car>) colorToCarMapping.get(carToLeave.getColor());
            colorList.remove(carToLeave);
            vacantSlots.add(carToLeave.getSlotNumber());
            return String.format(Constants.PARKING_LEAVE_MSG, parkingSlotNo);
        } else {
            return Constants.NOT_FOUND_MSG;
        }
    }

    public void parkingStatus() {

    }

    public String registrationNosofCarsWithColor(String color) {
        if (!isParkingLotCreated()) {
            return Constants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (colorToCarMapping.containsKey(color)) {
            List<Car> colorList = (List<Car>) colorToCarMapping.get(color);
            List<String> registrationNumbers = colorList.stream().
                    map(Car::getRegistrationNumber).
                    collect(Collectors.toList());
            return StringUtils.join(registrationNumbers, ", ");
        } else {
            return Constants.NOT_FOUND_MSG;
        }
    }

    public String slotNosOfCarsWithColor(String color) {
        if (!isParkingLotCreated()) {
            return Constants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (colorToCarMapping.containsKey(color)) {
            List<Car> colorList = (List<Car>) colorToCarMapping.get(color);
            List<Integer> slotNums = colorList.stream().
                    map(Car::getSlotNumber).
                    collect(Collectors.toList());
            return StringUtils.join(slotNums, ", ");
        } else {
            return Constants.NOT_FOUND_MSG;
        }

    }

    public String slotNoBasedOnRegistrationNo(String registrationNo) {
        if (!isParkingLotCreated()) {
            return Constants.PARKING_LOT_NOT_CREATED_MSG;
        } else if (registrationToCarMapping.containsKey(registrationNo)) {
            return String.valueOf(registrationToCarMapping.get(registrationNo).getSlotNumber());
        } else {
            return Constants.NOT_FOUND_MSG;
        }
    }

    @Override
    public String createParkingLot(int maxNoOfVehicles) {
        this.maxNoOfVehicles = maxNoOfVehicles;
        this.vacantSlots = new PriorityQueue(maxNoOfVehicles);
        for (int i = 1 ; i <= maxNoOfVehicles; i++) {
            this.vacantSlots.add(i);
        }

        return String.format(Constants.PARKING_LOT_CREATION_MSG, maxNoOfVehicles);
    }

    private boolean isParkingLotCreated() {
        return maxNoOfVehicles >= 0;
    }
}
