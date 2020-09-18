package com.reese.fsd.pdu;

public enum NetworkRating {

    OBS("Observer", 1),
    S1("Student 1", 2),
    S2("Student 2",3),
    S3("Student 3", 4),
    C1("Controller 1", 5),
    C2("Controller 2", 6),
    C3("Controller 3", 7),
    I1("Instructor 1", 8),
    I2("Instructor 2", 9),
    I3("Instructor 3", 10),
    SUP("Supervisor", 11),
    ADM("Administrator", 12);

    public String name;
    public int id;

    NetworkRating(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public boolean validATC() {
        return this != NetworkRating.OBS;
    }

    public static NetworkRating fromID(int id) {
        return switch(id) {
            case 1 -> NetworkRating.OBS;
            case 2 -> NetworkRating.S1;
            case 3 -> NetworkRating.S2;
            case 4 -> NetworkRating.S3;
            case 5 -> NetworkRating.C1;
            case 6 -> NetworkRating.C2;
            case 7 -> NetworkRating.C3;
            case 8 -> NetworkRating.I1;
            case 9 -> NetworkRating.I2;
            case 10 -> NetworkRating.I3;
            case 11 -> NetworkRating.SUP;
            case 12 -> NetworkRating.ADM;
            default -> NetworkRating.OBS;
        };
    }
}
