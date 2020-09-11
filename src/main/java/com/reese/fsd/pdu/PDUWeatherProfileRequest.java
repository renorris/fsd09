package com.reese.fsd.pdu;

public class PDUWeatherProfileRequest extends PDUBase {

    public String from;
    public String to;
    public String station;

    public PDUWeatherProfileRequest(String from, String station) {
        this.from = from;
        this.to = serverCallsign;
        this.station = station;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#WX");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append(this.station);
        return msg.toString();
    }

    public static PDUWeatherProfileRequest parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUWeatherProfileRequest(
                    fields[0],
                    fields[2]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

