package com.example.oryossipof.securitymanagement;

/**
 * Created by or yossipof on 21/09/2017.
 */

class DataBase {
    private static final DataBase ourInstance = new DataBase();

    static DataBase getInstance() {
        return ourInstance;
    }

    private DataBase() {
    }
}
