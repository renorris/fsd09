package com.reese.fsd.pdu;

public class PDUHandoffCancelled extends PDUBase {

    public String from;
    public String to;
    public String target;

    public PDUHandoffCancelled(String from, String to, String target) {
        this.from = from;
        this.to = to;
        this.target = target;
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
        msg.append("HC");
        msg.append(DELIMITER);
        msg.append(this.target);
        return msg.toString();
    }

    public static PDUHandoffCancelled parse(String[] fields) throws PDUFormatException {
        if (fields.length < 5) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUHandoffCancelled(
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

