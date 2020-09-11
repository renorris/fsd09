package com.reese.fsd.pdu;

public class PDUVersionRequest extends PDUBase {

    public String from;
    public String to;

    public PDUVersionRequest(String from, String to) {
        this.from = from;
        this.to = to;
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
        msg.append("VER");
        return msg.toString();
    }

    public static PDUVersionRequest parse(String[] fields) throws PDUFormatException {
        if (fields.length < 4) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUVersionRequest(
                    fields[0],
                    fields[1]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

