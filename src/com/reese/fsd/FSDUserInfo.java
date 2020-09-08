package com.reese.fsd;

public class FSDUserInfo {

    private final String x;
    private final String y;
    private final String z;

    public FSDUserInfo(String x, String y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public FSDUserInfo operation(String x, String y) {
        return new FSDUserInfo(x, y, this.z);
    }

}
