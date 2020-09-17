package com.reese.fsd.pdu;

public class PDUAddATC extends PDUBase {

    public String from;
    public String realName;
    public String cid;
    public String password;
    public NetworkRating rating;
    public ProtocolRevision proto;

    public PDUAddATC(String from, String realName, String cid, String password, NetworkRating rating, ProtocolRevision proto) {
        this.from = from;
        this.realName = realName;
        this.cid = cid;
        this.password = password;
        this.rating = rating;
        this.proto = proto;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#AA");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(SERVER_CALLSIGN);
        msg.append(DELIMITER);
        msg.append(this.realName);
        msg.append(DELIMITER);
        msg.append(this.cid);
        msg.append(DELIMITER);
        msg.append(this.password);
        msg.append(DELIMITER);
        msg.append(this.rating.ordinal());
        msg.append(DELIMITER);
        msg.append(this.proto.num);
        return msg.toString();
    }

    public static PDUAddATC parse(String[] fields) throws PDUFormatException {
        if (fields.length < 6) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUAddATC(
                    fields[0],
                    fields[2],
                    fields[3],
                    "",
                    NetworkRating.values()[Integer.parseInt(fields[5])],
                    ProtocolRevision.UNKNOWN
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

