package com.reese.fsd.pdu;

public class PDUDeleteATC extends PDUBase {

    public String from;
    public String cid;

    public PDUDeleteATC(String from, String cid) {
        this.from = from;
        this.cid = cid;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#DA");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.cid);
        return msg.toString();
    }

    public static PDUDeleteATC parse(String[] fields) throws PDUFormatException {
        if (fields.length < 1) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUDeleteATC(
                    fields[0],
                    (fields.length >= 2) ? fields[1] : ""
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

