package com.reese.fsd.pdu;

public class PDUTextMessage extends PDUBase {

    public String from;
    public String to;
    public String message;

    public PDUTextMessage(String from, String to, String message) {
        this.from = from;
        this.to = to;
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

    public static PDUTextMessage parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            StringBuilder msg = new StringBuilder(fields[2]);
            for (int i = 3; i < fields.length; i++) {
                msg.append(":").append(fields[i]);
            }

            return new PDUTextMessage(
                    fields[0],
                    fields[1],
                    msg.toString()
            );

        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

