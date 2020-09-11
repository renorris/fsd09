package com.reese.fsd.pdu;

public class PDUPushToDepartureList extends PDUBase {

    public String from;
    public String to;
    public String target;

    public PDUPushToDepartureList(String from, String to, String target) {
        this.from = from;
        this.to = to;
        this.target = target;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#PC");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append("CCP");
        msg.append(delimiter);
        msg.append("DP");
        msg.append(delimiter);
        msg.append(this.target);
        return msg.toString();
    }

    public static PDUPushToDepartureList parse(String[] fields) throws PDUFormatException {
        if (fields.length < 5) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUPushToDepartureList(
                    fields[0],
                    fields[1],
                    fields[4]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

