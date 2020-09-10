package com.reese.fsd.pdu;

public enum FlightRules {
    IFR,
    VFR,
    DVFR,
    SVFR;

    public static FlightRules fromString(String rulesString) {
        return switch (rulesString.toUpperCase()) {
            case "I", "IFR" -> FlightRules.IFR;
            case "V", "VFR" -> FlightRules.VFR;
            case "D", "DVFR" -> FlightRules.DVFR;
            case "S", "SVFR" -> FlightRules.SVFR;
            default -> throw new IllegalArgumentException("Unknown flight rules: " + rulesString);
        };
    }
}
