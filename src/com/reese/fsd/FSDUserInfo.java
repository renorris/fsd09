package com.reese.fsd;

public class FSDUserInfo {

    private String callsign;
    private Integer cid;
    private Integer atcRating;
    private String fullName;
    private Boolean loggedIn = false;

    public FSDUserInfo(String callsign) {
        this.callsign = callsign;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getAtcRating() {
        return atcRating;
    }

    public void setAtcRating(Integer atcRating) {
        this.atcRating = atcRating;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
