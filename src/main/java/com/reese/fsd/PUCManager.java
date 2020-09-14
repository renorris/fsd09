package com.reese.fsd;

import com.reese.fsd.line.DistanceCalculator;

import java.util.*;

public class PUCManager { // PublicUserCache manager

    // Instantiate main user hash
    private Map<String, PublicUserCache> users = Collections.synchronizedMap(new HashMap<>());

    // Define getters and setters for user hash
    public Map<String, PublicUserCache> getUsers() {
        return users;
    }

    public PublicUserCache getUser(String callsign) {
        return this.users.get(callsign);
    }

    public void addUser(PublicUserCache publicUserCache) {
        this.users.put(publicUserCache.getCallsign(), publicUserCache);
    }

    public void removeUser(String callsign) {
        this.users.remove(callsign);
    }

    public PublicUserCache[] usersWithinRangeOf(PublicUserCache publicUserCache1) {
        double lat1 = publicUserCache1.getLatitude();
        double lon1 = publicUserCache1.getLongitude();
        int visRange1 = publicUserCache1.getVisibilityRange();

        List<PublicUserCache> usersWithinRange = new ArrayList<>();

        for (PublicUserCache publicUserCache2 : this.users.values()) {
            double lat2 = publicUserCache2.getLatitude();
            double lon2 = publicUserCache2.getLongitude();
            int totalVisRange = visRange1 + publicUserCache2.getVisibilityRange();

            if (DistanceCalculator.distanceInMiles(lat1, lon1, lat2, lon2) <= totalVisRange) {
                usersWithinRange.add(publicUserCache2);
            }
        }

        return (PublicUserCache[]) usersWithinRange.toArray();
    }
}
