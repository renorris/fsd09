package com.reese.fsd.pdu;

import com.reese.fsd.NetworkRating;
import com.reese.fsd.ProtocolRevision;

public class PDUAddATC extends PDUBase {

    public String callsign;
    public String realName;
    public String cid;
    public String password;
    public NetworkRating rating;
    public ProtocolRevision proto;

    public PDUAddATC(String callsign, String realName, String cid, String password, NetworkRating rating, ProtocolRevision proto) {
        this.callsign = callsign;
        this.realName = realName;
        this.cid = cid;
        this.password = password;
        this.rating = rating;
        this.proto = proto;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#AA");
        msg.append(this.callsign);
        msg.append(delimiter);
        msg.append(serverCallsign);
        msg.append(delimiter);
        msg.append(this.realName);
        msg.append(delimiter);
        msg.append(this.cid);
        msg.append(delimiter);
        msg.append(this.password);
        msg.append(delimiter);
        msg.append(this.rating.ordinal());
        msg.append(delimiter);
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

