package com.reese.fsd;

import java.util.HashMap;

public class FSDData {

    private final HashMap<String, HashMap> connectedUsers;

    public FSDData() {
        this.connectedUsers = new HashMap<>();
    }

    private HashMap<String, HashMap> getConnectedUsersSync() {
        synchronized(this.connectedUsers) {
            return this.connectedUsers;
        }
    }

    public HashMap<String, Object> getUser(String callsign) {
        return getConnectedUsersSync().get(callsign);
    }

}
