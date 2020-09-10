package com.reese.fsd.pdu;

public class PDUAuthResponse extends PDUBase {

    public String from;
    public String to;
    public String response;

    public PDUAuthResponse(String from, String to, String response) {
        this.from = from;
        this.to = to;
        this.response = response;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$ZR");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append(this.response);
        return msg.toString();
    }

    public static PDUAuthResponse parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUAuthResponse (
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

