package com.reese.fsd;

public enum NetworkRating {

    OBS("Observer"),
    S1("Student 1"),
    S2("Student 2"),
    S3("Student 3"),
    C1("Controller 1"),
    C2("Controller 2"),
    C3("Controller 3"),
    I1("Instructor 1"),
    I2("Instructor 2"),
    I3("Instructor 3"),
    SUP("Supervisor"),
    ADM("Administrator");

    public String name;

    NetworkRating(String name) {
        this.name = name;
    }
}
