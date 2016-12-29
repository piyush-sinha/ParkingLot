package com.gojek.problem.service.application;

import com.gojek.problem.service.client.Client;
import com.gojek.problem.service.client.ClientFactory;
import com.gojek.problem.service.jdbi.DBI;
import com.gojek.problem.service.jdbi.DBIFactory;

import java.io.FileNotFoundException;

/**
 * Created by piyushsinha.c on 27/12/16.
 */
public class ParkingLotApplication {
    private DBIFactory dbiFactory;
    private ClientFactory clientFactory;
    private Client client;
    private DBI dbi;

    public ParkingLotApplication(String[] args) throws FileNotFoundException {
        dbiFactory = new DBIFactory();
        clientFactory = new ClientFactory();
        dbi = dbiFactory.build();
        client = clientFactory.build(args, dbi);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ParkingLotApplication parkingLotApplication = new ParkingLotApplication(args);
        parkingLotApplication.client.processInput();
    }
}
