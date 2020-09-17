package com.reese.fsd.pdu;

public abstract class PDUBase {

    public static String CLIENT_QUERY_BROADCAST_RECIPIENT = "@94835";
    public static String CLIENT_QUERY_BROADCAST_RECIPIENT_PILOTS = "@94836";
    public static Character DELIMITER = ':';
    public static String PACKET_DELIMITER = "\r\n";
    public static String SERVER_CALLSIGN = "SERVER";

    public abstract String serialize();

    public static String reassemble(String[] fields) {
        return String.join(DELIMITER.toString(), fields);
    }
}
