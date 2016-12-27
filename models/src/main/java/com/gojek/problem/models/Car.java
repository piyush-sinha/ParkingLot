package com.gojek.problem.models;

/**
 * Created by piyushsinha.c on 27/12/16.
 */
public class Car {
    private int slotNumber;
    private String registrationNumber;
    private String color;

    public Car(int slotNumber, String registrationNumber, String color) {
        this.slotNumber = slotNumber;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
