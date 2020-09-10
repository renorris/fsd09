package com.reese.fsd.pdu;

public enum ClientQueryType {
    UNKNOWN,
    IS_VALID_ATC,
    CAPABILITIES,
    COM1FREQ,
    REAL_NAME,
    SERVER,
    ATIS,
    PUBLIC_IP,
    INF,
    FLIGHT_PLAN,
    IPC,
    REQUEST_RELIEF,
    CANCEL_REQUEST_RELIEF,
    REQUEST_HELP,
    CANCEL_REQUEST_HELP,
    WHO_HAS,
    INITIATE_TRACK,
    ACCEPT_HANDOFF,
    DROP_TRACK,
    SET_FINAL_ALTITUDE,
    SET_TEMP_ALTITUDE,
    SET_BEACON_CODE,
    SET_SCRATCHPAD,
    SET_VOICE_TYPE,
    AIRCRAFT_CONFIGURATION,
    NEW_INFO,
    NEW_ATIS;

    public static ClientQueryType fromString(String typeID) {
        return switch (typeID.toUpperCase()) {
            case "ATC" -> ClientQueryType.IS_VALID_ATC;
            case "CAPS" -> ClientQueryType.CAPABILITIES;
            case "C?" -> ClientQueryType.COM1FREQ;
            case "RN" -> ClientQueryType.REAL_NAME;
            case "SV" -> ClientQueryType.SERVER;
            case "ATIS" -> ClientQueryType.ATIS;
            case "IP" -> ClientQueryType.PUBLIC_IP;
            case "INF" -> ClientQueryType.INF;
            case "FP" -> ClientQueryType.FLIGHT_PLAN;
            case "IPC" -> ClientQueryType.IPC;
            case "BY" -> ClientQueryType.REQUEST_RELIEF;
            case "HI" -> ClientQueryType.CANCEL_REQUEST_RELIEF;
            case "HLP" -> ClientQueryType.REQUEST_HELP;
            case "NOHLP" -> ClientQueryType.CANCEL_REQUEST_HELP;
            case "WH" -> ClientQueryType.WHO_HAS;
            case "IT" -> ClientQueryType.INITIATE_TRACK;
            case "HT" -> ClientQueryType.ACCEPT_HANDOFF;
            case "DR" -> ClientQueryType.DROP_TRACK;
            case "FA" -> ClientQueryType.SET_FINAL_ALTITUDE;
            case "TA" -> ClientQueryType.SET_TEMP_ALTITUDE;
            case "BC" -> ClientQueryType.SET_BEACON_CODE;
            case "SC" -> ClientQueryType.SET_SCRATCHPAD;
            case "VT" -> ClientQueryType.SET_VOICE_TYPE;
            case "ACC" -> ClientQueryType.AIRCRAFT_CONFIGURATION;
            case "NEWINFO" -> ClientQueryType.NEW_INFO;
            case "NEWATIS" -> ClientQueryType.NEW_ATIS;
            default -> ClientQueryType.UNKNOWN;
        };
    }

    public static String getQueryTypeID(ClientQueryType cq) {
        return switch (cq) {
            case IS_VALID_ATC -> "ATC";
            case CAPABILITIES -> "CAPS";
            case COM1FREQ -> "C?";
            case REAL_NAME -> "RN";
            case SERVER -> "SV";
            case ATIS -> "ATIS";
            case PUBLIC_IP -> "IP";
            case INF -> "INF";
            case FLIGHT_PLAN -> "FP";
            case IPC -> "IPC";
            case REQUEST_RELIEF -> "BY";
            case CANCEL_REQUEST_RELIEF -> "HI";
            case REQUEST_HELP -> "HLP";
            case CANCEL_REQUEST_HELP -> "NOHLP";
            case WHO_HAS -> "WH";
            case INITIATE_TRACK -> "IT";
            case ACCEPT_HANDOFF -> "HT";
            case DROP_TRACK -> "DR";
            case SET_FINAL_ALTITUDE -> "FA";
            case SET_TEMP_ALTITUDE -> "TA";
            case SET_BEACON_CODE -> "BC";
            case SET_SCRATCHPAD -> "SC";
            case SET_VOICE_TYPE -> "VT";
            case AIRCRAFT_CONFIGURATION -> "ACC";
            case NEW_INFO -> "NEWINFO";
            case NEW_ATIS -> "NEWATIS";
            default -> "";
        };
    }
}
