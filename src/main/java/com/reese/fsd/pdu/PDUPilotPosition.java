package com.reese.fsd.pdu;

public class PDUPilotPosition extends PDUBase {

    public String from;
    public Integer squawkCode;
    public boolean isSquawkingModeC;
    public boolean isIdenting;
    public NetworkRating rating;
    public Double lat;
    public Double lon;
    public Integer trueAltitude;
    public Integer pressureAltitude;
    public Integer groundSpeed;
    public Integer pitch;
    public Integer bank;
    public Integer heading;

    public PDUPilotPosition(String from, Integer squawkCode, boolean isSquawkingModeC, boolean isIdenting, NetworkRating rating, Double lat, Double lon, Integer trueAltitude, Integer pressureAltitude, Integer groundSpeed, Integer pitch, Integer bank, Integer heading) {
        this.from = from;
        this.squawkCode = squawkCode;
        this.isSquawkingModeC = isSquawkingModeC;
        this.isIdenting = isIdenting;
        this.rating = rating;
        this.lat = lat;
        this.lon = lon;
        this.trueAltitude = trueAltitude;
        this.pressureAltitude = pressureAltitude;
        this.groundSpeed = groundSpeed;
        this.pitch = pitch;
        this.bank = bank;
        this.heading = heading;
    }

    @Override
    public String serialize() {
        // Convert PBH values into MSFS format.
        Double p = (double) this.pitch / -360.0;
        if (p < 0) p += 1.0;
        p *= 1024.0;
        Double b = (double) this.bank / -360.0;
        if (b < 0) b += 1.0;
        b *= 1024.0;
        Double h = (double) this.heading / 360.0 * 1024.0;

        // Shift the values into a 32 bit integer.
        Integer pbh = (p.intValue() << 22) | (b.intValue() << 12) | (h.intValue() << 2);

        // Assemble the PDU.
        StringBuilder msg = new StringBuilder("@");
        msg.append(this.isIdenting ? "Y" : (this.isSquawkingModeC ? "N" : "S"));
        msg.append(DELIMITER);
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.squawkCode.toString());
        msg.append(DELIMITER);
        msg.append(this.rating);
        msg.append(DELIMITER);
        msg.append(this.lat);
        msg.append(DELIMITER);
        msg.append(this.lon);
        msg.append(DELIMITER);
        msg.append(this.trueAltitude);
        msg.append(DELIMITER);
        msg.append(this.groundSpeed);
        msg.append(DELIMITER);
        msg.append(pbh);
        msg.append(DELIMITER);
        msg.append(this.pressureAltitude - this.trueAltitude);
        return msg.toString();
    }

    public static PDUPilotPosition parse(String[] fields) throws PDUFormatException {
        if (fields.length < 10) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            Integer pbh = Integer.parseInt(fields[8]);
            Integer pitch = pbh >> 22;
            Integer bank = (pbh >> 12) & 0x3FF;
            Integer hdg = (pbh >> 2) & 0x3FF;
            Double pitchDbl = (double) pitch / 1024.0 * -360.0;
            Double bankDbl = (double) bank / 1024.0 * -360.0;
            Double hdgDbl = (double) hdg / 1024.0 * 360.0;
            
            if (pitchDbl > 180.0) {
                pitchDbl -= 360.0;
            } else if (pitchDbl <= -180.0) {
                pitchDbl += 360.0;
            }
            if (bankDbl > 180.0) {
                bankDbl -= 360.0;
            } else if (bankDbl <= -180.0) {
                bankDbl += 360.0;
            }
            if (hdgDbl < 0.0) {
                hdgDbl += 360.0;
            } else if (hdgDbl >= 360.0) {
                hdgDbl -= 360.0;
            }
            
            Boolean identing = false;
            Boolean charlie = false;
            
            switch (fields[0].toUpperCase()) {
                case "S":
                    break;
                case "N":
                    charlie = true;
                    break;
                case "Y":
                    charlie = true;
                    identing = true;
                    break;
            }
            
            return new PDUPilotPosition(
                    fields[1],
                    Integer.parseInt(fields[2]),
                    charlie,
                    identing,
                    NetworkRating.values()[Integer.parseInt(fields[3])],
                    Double.parseDouble(fields[4]), // lat
                    Double.parseDouble(fields[5]), // lon
                    (int) Math.round(Double.parseDouble(fields[6])), // trueAlt
                    (int) Math.round(Double.parseDouble(fields[6]) + Double.parseDouble(fields[9])), // pressureAlt
                    (int) Math.round(Double.parseDouble(fields[7])),
                    pitchDbl.intValue(),
                    bankDbl.intValue(),
                    hdgDbl.intValue()
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

