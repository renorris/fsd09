package com.reese.fsd.pdu;

public class PDUAuthChallenge extends PDUBase {

    public String from;
    public String to;
    public String challenge;

    public PDUAuthChallenge(String from, String to, String challenge) {
        this.from = from;
        this.to = to;
        this.challenge = challenge;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$ZC");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(this.challenge);
        return msg.toString();
    }

    public static PDUAuthChallenge parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUAuthChallenge (
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

