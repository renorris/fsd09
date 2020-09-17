package com.reese.fsd.pdu;

public class PDUPing extends PDUBase {

    public String from;
    public String to;
    public String timeStamp;

    public PDUPing(String from, String to, String timeStamp) {
        this.from = from;
        this.to = to;
        this.timeStamp = timeStamp;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$PI");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(this.timeStamp);
        return msg.toString();
    }

    public static PDUPing parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUPing(
                    fields[0],
                    fields[1],
                    fields[2]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

