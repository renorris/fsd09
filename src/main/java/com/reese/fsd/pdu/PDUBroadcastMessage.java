package com.reese.fsd.pdu;

public class PDUBroadcastMessage extends PDUBase {

    public String from;
    public String to;
    public String message;

    public PDUBroadcastMessage(String from, String message) {
        this.from = from;
        this.to = "*";
        this.message = message;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#TM");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(this.message);
        return msg.toString();
    }

    public static PDUBroadcastMessage parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            StringBuilder msg = new StringBuilder(fields[2]);
            for (int i = 3; i < fields.length; i++) {
                msg.append(":");
                msg.append(fields[i]);
            }
            return new PDUBroadcastMessage(
                    fields[0],
                    msg.toString()
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

