package com.reese.fsd.pdu;

public class PDUKillRequest extends PDUBase {

    public String from;
    public String victim;
    public String reason;

    public PDUKillRequest(String from, String victim, String reason) {
        this.from = from;
        this.victim = victim;
        this.reason = reason;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$!!");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.victim);
        msg.append(DELIMITER);
        msg.append(this.reason);
        return msg.toString();
    }

    public static PDUKillRequest parse(String[] fields) throws PDUFormatException {
        if (fields.length < 2) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUKillRequest(
                    fields[0],
                    fields[1],
                    (fields.length > 2) ? fields[2] : ""
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

