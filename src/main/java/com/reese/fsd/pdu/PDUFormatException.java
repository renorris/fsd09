package com.reese.fsd.pdu;

public class PDUFormatException extends Exception {

    public String rawMessage;

    public PDUFormatException(String error, String rawMessage) {
        super(error);
        this.rawMessage = rawMessage;
    }


}
