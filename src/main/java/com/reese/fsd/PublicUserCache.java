package com.reese.fsd;

import com.reese.fsd.pdu.NetworkRating;

public class PublicUserCache {

    // public user cache that all user threads can access

    private String callsign;
    private String cid;
    private String realName;
    private NetworkRating networkRating;
    private Double latitude;
    private Double longitude;
    private Integer visibilityRange;

    public PublicUserCache(String callsign, String cid, String realName, NetworkRating networkRating, Double latitude, Double longitude) {
        this.callsign = callsign;
        this.cid = cid;
        this.realName = realName;
        this.networkRating = networkRating;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCallsign() {
        return callsign;
    }

    public String getCid() {
        return cid;
    }

    public String getRealName() {
        return realName;
    }

    public NetworkRating getNetworkRating() {
        return networkRating;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getVisibilityRange() {
        return visibilityRange;
    }

    public void setVisibilityRange(Integer visibilityRange) {
        this.visibilityRange = visibilityRange;
    }
}
