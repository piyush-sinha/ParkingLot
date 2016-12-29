package com.gojek.problem.service.client;

import com.gojek.problem.service.jdbi.DBI;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by piyushsinha.c on 28/12/16.
 */
public abstract class Client {
    private DBI dbi;
    private BufferedReader bufferedReader;

    public Client(DBI dbi, BufferedReader bufferedReader) {
        this.dbi = dbi;
        this.bufferedReader = bufferedReader;
    }

    public void processInput() {
        boolean status = true;

        while (status) {
            try {
                String inputString = this.bufferedReader.readLine();
                if (StringUtils.isEmpty(inputString)) {
                    status = false;
                    continue;
                }
                String[] inputs = inputString.split("\\s+");

                if (!inputs[0].isEmpty()) {
                    switch (inputs[0]) {
                        case "create_parking_lot": {
                            System.out.println(dbi.createParkingLot(Integer.parseInt(inputs[1])));
                            break;
                        }
                        case "park": {
                            System.out.println(dbi.parkCar(inputs[1], inputs[2]));
                            break;
                        }
                        case "leave": {
                            System.out.println(dbi.leaveParkingLot(Integer.parseInt(inputs[1])));
                            break;
                        }
                        case "registration_numbers_for_cars_with_colour" : {
                            System.out.println(dbi.registrationNosofCarsWithColor(inputs[1]));
                            break;
                        }
                        case "slot_numbers_for_cars_with_colour" : {
                            System.out.println(dbi.slotNosOfCarsWithColor(inputs[1]));
                            break;
                        }
                        case "slot_number_for_registration_number" : {
                            System.out.println(dbi.slotNoBasedOnRegistrationNo(inputs[1]));
                            break;
                        }
                        default:
                            status = false;
                            break;
                    }
                }
            } catch (IOException e) {
                status = false;
            }
        }
    }
}
