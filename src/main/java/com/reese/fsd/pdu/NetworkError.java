package com.reese.fsd.pdu;

public enum NetworkError {

    OK,
    CALLSIGN_IN_USE,
    CALLSIGN_INVALID,
    ALREADY_REGISTERED,
    SYNTAX_ERROR,
    PDUSOURCE_INVALID,
    INVALID_LOGON,
    NO_SUCH_CALLSIGN,
    NO_FLIGHT_PLAN,
    NO_WEATHER_PROFILE,
    INVALID_PROTOCOL_REVISION,
    REQUESTED_LEVEL_TOO_HIGH,
    SERVER_FULL,
    CERTIFICATE_SUSPENDED,
    INVALID_CONTROL,
    INVALID_POSITION_FOR_RATING,
    UNAUTHORIZED_SOFTWARE;

}