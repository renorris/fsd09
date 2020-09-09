package com.reese.fsd;

public enum NetworkFacility {
    OBS("Observer"),
    FSS("Flight Service Station"),
    DEL("Clearance Delivery"),
    GND("Ground"),
    TWR("Tower"),
    APP("Radar"),
    CTR("Center");

    public String name;

    NetworkFacility(String name) {
        this.name = name;
    }
}
