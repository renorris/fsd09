package com.reese.fsd.pdu;

public class PDUSecondaryVisCenter extends PDUBase {

    public String from;
    public Integer index;
    public Double lat;
    public Double lon;

    public PDUSecondaryVisCenter(String from, Integer index, Double lat, Double lon) {
        this.from = from;
        this.index = index;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("'");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.index);
        msg.append(DELIMITER);
        msg.append(this.lat);
        msg.append(DELIMITER);
        msg.append(this.lon);
        return msg.toString();
    }

    public static PDUSecondaryVisCenter parse(String[] fields) throws PDUFormatException {
        if (fields.length < 4) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUSecondaryVisCenter(
                    fields[0],
                    Integer.parseInt(fields[1]),
                    Double.parseDouble(fields[2]),
                    Double.parseDouble(fields[3])
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

