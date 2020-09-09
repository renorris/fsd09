package com.reese.fsd.pdu;

public abstract class PDUBase {

    public static String clientQueryBroadcastRecipient = "@94835";
    public static String clientQueryBroadcastRecipientPilots = "@94836";
    public static Character delimiter = ':';
    public static String packetDelimiter = "\r\n";
    public static String serverCallsign = "SERVER";

    public abstract String serialize();

    public static String reassemble(String[] fields) {
        return String.join(delimiter.toString(), fields);
    }
}
