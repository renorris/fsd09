package com.reese.fsd.pdu;

public class PDUDeletePilot extends PDUBase {

    public String from;
    public String cid;

    public PDUDeletePilot(String from, String cid) {
        this.from = from;
        this.cid = cid;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#DP");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.cid);
        return msg.toString();
    }

    public static PDUDeletePilot parse(String[] fields) throws PDUFormatException {
        if (fields.length < 1) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUDeletePilot (
                    fields[0],
                    (fields.length >= 2) ? fields[1] : ""
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

