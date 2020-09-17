package com.reese.fsd;

import com.reese.fsd.line.DistanceCalculator;

import java.util.*;

public class UserAPI { // PublicUserCache manager

    // Instantiate main user hash
    private Map<String, UserData> users = Collections.synchronizedMap(new HashMap<>());

    // Define getters and setters for user hash
    public Map<String, UserData> getUsers() {
        return users;
    }

    public UserData getUser(String callsign) {
        return this.users.get(callsign);
    }

    public void addUser(UserData userData) {
        this.users.put(userData.getCallsign(), userData);
    }

    public void removeUser(String callsign) {
        this.users.remove(callsign);
    }

    public UserData[] usersWithinRangeOf(UserData userData1) {
        double lat1 = userData1.getLatitude();
        double lon1 = userData1.getLongitude();
        int visRange1 = userData1.getVisibilityRange();

        List<UserData> usersWithinRange = new ArrayList<>();

        for (UserData userData2 : this.users.values()) {
            double lat2 = userData2.getLatitude();
            double lon2 = userData2.getLongitude();
            int totalVisRange = visRange1 + userData2.getVisibilityRange();

            if (DistanceCalculator.distanceInMiles(lat1, lon1, lat2, lon2) <= totalVisRange) {
                usersWithinRange.add(userData2);
            }
        }

        return (UserData[]) usersWithinRange.toArray();
    }
}
