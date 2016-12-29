package com.gojek.problem.service.client;

import com.gojek.problem.service.jdbi.DBI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by piyushsinha.c on 28/12/16.
 */
public class ClientFactory {

    public Client build(String[] args, DBI dbi) throws FileNotFoundException {
        //read from file
        if (args.length > 0) {
            return new FileClientImpl(dbi, new BufferedReader(new FileReader(args[0])));
        } else {
            //read from command line
            return new CLIClientImpl(dbi, new BufferedReader(new InputStreamReader(System.in)));
        }
    }
}
