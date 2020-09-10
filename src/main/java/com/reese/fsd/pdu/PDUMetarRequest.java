package com.reese.fsd.pdu;

public class PDUMetarRequest extends PDUBase {

    public String from;
    public String to;
    public String station;

    public PDUMetarRequest(String from, String station) {
        this.from = from;
        this.to = serverCallsign;
        this.station = station;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$AX");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append("METAR");
        msg.append(delimiter);
        msg.append(this.station);
        return msg.toString();
    }

    public static PDUMetarRequest parse(String[] fields) throws PDUFormatException {
        if (fields.length < 4) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUMetarRequest(
                    fields[0],
                    fields[3]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

