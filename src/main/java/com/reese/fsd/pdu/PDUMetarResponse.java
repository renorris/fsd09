package com.reese.fsd.pdu;

public class PDUMetarResponse extends PDUBase {

    public String from;
    public String to;
    public String metar;

    public PDUMetarResponse(String to, String metar) {
        this.from = serverCallsign;
        this.to = to;
        this.metar = metar;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$AR");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append(this.metar);
        return msg.toString();
    }

    public static PDUMetarResponse parse(String[] fields) throws PDUFormatException {
        if (fields.length < 4) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUMetarResponse (
                    fields[1],
                    fields[3]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

