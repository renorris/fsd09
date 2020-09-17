package com.reese.fsd.pdu;

public class PDUModernClientCheck extends PDUBase {

    public String from;
    public String to;

    public PDUModernClientCheck(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#PC");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append("CCP");
        msg.append(DELIMITER);
        msg.append("ID");
        return msg.toString();
    }

    public static PDUModernClientCheck parse(String[] fields) throws PDUFormatException {
        if (fields.length < 4) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUModernClientCheck(
                    fields[0],
                    fields[1]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

