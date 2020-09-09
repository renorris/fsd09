package com.reese.fsd;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FSDUsers {

    // Instantiate main user hash
    private Map<String, FSDUserInfo> users = Collections.synchronizedMap(new HashMap<>());

    // Define getters and setters for user hash
    public Map<String, FSDUserInfo> getUsers() {
        return users;
    }

    public FSDUserInfo addUser(String callsign) {
        FSDUserInfo newUserInfo = new FSDUserInfo(callsign);
        this.users.put(callsign, newUserInfo);
        return newUserInfo;
    }

    public void removeUser(String callsign) {
        this.users.remove(callsign);
    }

}
