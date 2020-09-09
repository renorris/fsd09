package com.reese.fsd.pdu;

import com.reese.fsd.NetworkRating;
import com.reese.fsd.ProtocolRevision;
import com.reese.fsd.SimulatorType;

public class PDUAddPilot extends PDUBase {

    public String callsign;
    public String cid;
    public String password;
    public NetworkRating rating;
    public ProtocolRevision proto;
    public SimulatorType simulatorType;
    public String realName;

    public PDUAddPilot(String callsign, String cid, String password, NetworkRating rating, ProtocolRevision proto, SimulatorType simType, String realName) {
        this.callsign = callsign;
        this.cid = cid;
        this.password = password;
        this.rating = rating;
        this.proto = proto;
        this.simulatorType = simType;
        this.realName = realName;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#AP");
        msg.append(this.callsign);
        msg.append(delimiter);
        msg.append(serverCallsign);
        msg.append(delimiter);
        msg.append(this.cid);
        msg.append(delimiter);
        msg.append(this.password);
        msg.append(delimiter);
        msg.append(this.rating.ordinal());
        msg.append(delimiter);
        msg.append(this.proto.num);
        msg.append(delimiter);
        msg.append(this.simulatorType.ordinal());
        msg.append(delimiter);
        msg.append(this.realName);
        return msg.toString();
    }

    public static PDUAddPilot parse(String[] fields) throws PDUFormatException {
        if (fields.length < 8) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUAddPilot(
                    fields[0],
                    fields[2],
                    fields[3],
                    NetworkRating.values()[Integer.parseInt(fields[4])],
                    ProtocolRevision.values()[Integer.parseInt(fields[5])],
                    SimulatorType.values()[Integer.parseInt(fields[6])],
                    fields[7]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

