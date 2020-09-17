package com.reese.fsd.pdu;

public class PDUHandoffAccept extends PDUBase {

    public String from;
    public String to;
    public String target;

    public PDUHandoffAccept(String from, String to, String target) {
        this.from = from;
        this.to = to;
        this.target = target;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$HA");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(this.target);
        return msg.toString();
    }

    public static PDUHandoffAccept parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUHandoffAccept (
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

