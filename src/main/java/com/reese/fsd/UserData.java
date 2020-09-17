package com.reese.fsd;

import com.reese.fsd.pdu.NetworkRating;
import com.reese.fsd.pdu.ProtocolRevision;

public class UserData {

    private Boolean shouldDisconnect = false;
    private LoginStage loginStage = LoginStage.PRE_IDENTIFICATION;
    private Boolean loggedIn = false;
    private String callsign;
    private String cid;
    private String realName;
    private NetworkRating networkRating;
    private Double latitude;
    private Double longitude;
    private Integer visibilityRange;
    private String initialChallengeKey;
    private String currentChallengeKey;
    private Integer clientID;
    private String clientName;
    private Integer majorVersion;
    private Integer minorVersion;
    private String sysUID;
    private ProtocolRevision protocolRevision;

    public Boolean getShouldDisconnect() {
        return shouldDisconnect;
    }

    public void setShouldDisconnect(Boolean shouldDisconnect) {
        this.shouldDisconnect = shouldDisconnect;
    }

    public LoginStage getLoginStage() {
        return loginStage;
    }

    public void setLoginStage(LoginStage loginStage) {
        this.loginStage = loginStage;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public NetworkRating getNetworkRating() {
        return networkRating;
    }

    public void setNetworkRating(NetworkRating networkRating) {
        this.networkRating = networkRating;
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

    public String getInitialChallengeKey() {
        return initialChallengeKey;
    }

    public void setInitialChallengeKey(String initialChallengeKey) {
        this.initialChallengeKey = initialChallengeKey;
    }

    public String getCurrentChallengeKey() {
        return currentChallengeKey;
    }

    public void setCurrentChallengeKey(String currentChallengeKey) {
        this.currentChallengeKey = currentChallengeKey;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    public Integer getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }

    public String getSysUID() {
        return sysUID;
    }

    public void setSysUID(String sysUID) {
        this.sysUID = sysUID;
    }

    public ProtocolRevision getProtocolRevision() {
        return protocolRevision;
    }

    public void setProtocolRevision(ProtocolRevision protocolRevision) {
        this.protocolRevision = protocolRevision;
    }
}
