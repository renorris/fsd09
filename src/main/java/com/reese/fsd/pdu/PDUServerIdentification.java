package com.reese.fsd.pdu;

public class PDUServerIdentification extends PDUBase {

    public String from;
    public String to;
    public String version;
    public String initialChallengeKey;

    public PDUServerIdentification(String from, String to, String version, String initialChallengeKey) {
        this.from = from;
        this.to = to;
        this.version = version;
        this.initialChallengeKey = initialChallengeKey;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$DI");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(this.version);
        msg.append(DELIMITER);
        msg.append(this.initialChallengeKey);
        return msg.toString();
    }

    public static PDUServerIdentification parse(String[] fields) throws PDUFormatException {
        if (fields.length < 4) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUServerIdentification (
                    fields[0],
                    fields[1],
                    fields[2],
                    fields[3]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

