package com.reese.fsd.pdu;

public class PDUClientIdentification extends PDUBase {

    public String from;
    public String to;
    public Integer clientID;
    public String clientName;
    public Integer majorVersion;
    public Integer minorVersion;
    public String cid;
    public String sysUID;
    public String initialChallenge;

    public PDUClientIdentification(String from, Integer clientID, String clientName, Integer majorVersion, Integer minorVersion, String cid, String sysUID, String initialChallenge) {
        this.from = from;
        this.to = SERVER_CALLSIGN;
        this.clientID = clientID;
        this.clientName = clientName;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.cid = cid;
        this.sysUID = sysUID;
        this.initialChallenge = initialChallenge;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$ID");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(Integer.toHexString(this.clientID));
        msg.append(DELIMITER);
        msg.append(this.clientName);
        msg.append(DELIMITER);
        msg.append(this.majorVersion);
        msg.append(DELIMITER);
        msg.append(this.minorVersion);
        msg.append(DELIMITER);
        msg.append(this.cid);
        msg.append(DELIMITER);
        msg.append(this.sysUID);
        if (!this.initialChallenge.isEmpty()) {
            msg.append(DELIMITER);
            msg.append(this.initialChallenge);
        }
        return msg.toString();
    }

    public static PDUClientIdentification parse(String[] fields) throws PDUFormatException {
        if (fields.length < 8) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            String challenge = fields.length > 8 ? fields[8] : "";
            return new PDUClientIdentification(
                    fields[0],
                    Integer.parseInt(fields[2], 16),
                    fields[3],
                    Integer.parseInt(fields[4]),
                    Integer.parseInt(fields[5]),
                    fields[6],
                    fields[7],
                    challenge
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

