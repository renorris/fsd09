package com.reese.fsd.pdu;

public class PDUATCPosition extends PDUBase {

    public String from;
    public int frequency;
    public NetworkFacility facility;
    public Integer visibilityRange;
    public NetworkRating rating;
    public Double lat;
    public Double lon;

    public PDUATCPosition(String from, int frequency, NetworkFacility facility, int visibilityRange, NetworkRating rating, double lat, double lon) {
        this.from = from;
        this.frequency = frequency;
        this.facility = facility;
        this.visibilityRange = visibilityRange;
        this.rating = rating;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("%");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.frequency);
        msg.append(DELIMITER);
        msg.append(this.facility.ordinal());
        msg.append(DELIMITER);
        msg.append(this.visibilityRange);
        msg.append(DELIMITER);
        msg.append(this.rating.ordinal());
        msg.append(DELIMITER);
        msg.append(this.lat.toString());
        msg.append(DELIMITER);
        msg.append(this.lon.toString());
        msg.append(DELIMITER);
        msg.append("0");
        return msg.toString();
    }

    public static PDUATCPosition parse(String[] fields) throws PDUFormatException {
        if (fields.length < 7) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUATCPosition(
                    fields[0],
                    Integer.parseInt(fields[1]),
                    NetworkFacility.values()[Integer.parseInt(fields[2])],
                    Integer.parseInt(fields[3]),
                    NetworkRating.values()[Integer.parseInt(fields[4])],
                    Double.parseDouble(fields[5]),
                    Double.parseDouble(fields[6])
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

