package com.gojek.problem.service.client;

import com.gojek.problem.service.jdbi.DBI;

import java.io.BufferedReader;

/**
 * Created by piyushsinha.c on 28/12/16.
 */
public class CLIClientImpl extends Client {
    public CLIClientImpl(DBI dbi, BufferedReader bufferedReader) {
       super(dbi, bufferedReader);
    }
}
