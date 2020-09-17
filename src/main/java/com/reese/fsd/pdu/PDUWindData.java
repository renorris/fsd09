package com.reese.fsd.pdu;

public class PDUWindData extends PDUBase {

    public String from;
    public String to;

    public Integer layer1Ceiling;
    public Integer layer1Floor;
    public Integer layer1Direction;
    public Integer layer1Speed;
    public Boolean layer1Gusting;
    public Integer layer1Turbulence;
    public Integer layer2Ceiling;
    public Integer layer2Floor;
    public Integer layer2Direction;
    public Integer layer2Speed;
    public Boolean layer2Gusting;
    public Integer layer2Turbulence;
    public Integer layer3Ceiling;
    public Integer layer3Floor;
    public Integer layer3Direction;
    public Integer layer3Speed;
    public Boolean layer3Gusting;
    public Integer layer3Turbulence;
    public Integer layer4Ceiling;
    public Integer layer4Floor;
    public Integer layer4Direction;
    public Integer layer4Speed;
    public Boolean layer4Gusting;
    public Integer layer4Turbulence;

    public PDUWindData(String from, String to, Integer layer1Ceiling, Integer layer1Floor, Integer layer1Direction, Integer layer1Speed, Boolean layer1Gusting, Integer layer1Turbulence, Integer layer2Ceiling, Integer layer2Floor, Integer layer2Direction, Integer layer2Speed, Boolean layer2Gusting, Integer layer2Turbulence, Integer layer3Ceiling, Integer layer3Floor, Integer layer3Direction, Integer layer3Speed, Boolean layer3Gusting, Integer layer3Turbulence, Integer layer4Ceiling, Integer layer4Floor, Integer layer4Direction, Integer layer4Speed, Boolean layer4Gusting, Integer layer4Turbulence) {
        this.from = from;
        this.to = to;
        this.layer1Ceiling = layer1Ceiling;
        this.layer1Floor = layer1Floor;
        this.layer1Direction = layer1Direction;
        this.layer1Speed = layer1Speed;
        this.layer1Gusting = layer1Gusting;
        this.layer1Turbulence = layer1Turbulence;
        this.layer2Ceiling = layer2Ceiling;
        this.layer2Floor = layer2Floor;
        this.layer2Direction = layer2Direction;
        this.layer2Speed = layer2Speed;
        this.layer2Gusting = layer2Gusting;
        this.layer2Turbulence = layer2Turbulence;
        this.layer3Ceiling = layer3Ceiling;
        this.layer3Floor = layer3Floor;
        this.layer3Direction = layer3Direction;
        this.layer3Speed = layer3Speed;
        this.layer3Gusting = layer3Gusting;
        this.layer3Turbulence = layer3Turbulence;
        this.layer4Ceiling = layer4Ceiling;
        this.layer4Floor = layer4Floor;
        this.layer4Direction = layer4Direction;
        this.layer4Speed = layer4Speed;
        this.layer4Gusting = layer4Gusting;
        this.layer4Turbulence = layer4Turbulence;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#WD");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(this.layer1Ceiling);
        msg.append(DELIMITER);
        msg.append(this.layer1Floor);
        msg.append(DELIMITER);
        msg.append(this.layer1Direction);
        msg.append(DELIMITER);
        msg.append(this.layer1Speed);
        msg.append(DELIMITER);
        msg.append(this.layer1Gusting ? "1" : "0");
        msg.append(DELIMITER);
        msg.append(this.layer1Turbulence);
        msg.append(DELIMITER);
        msg.append(this.layer2Ceiling);
        msg.append(DELIMITER);
        msg.append(this.layer2Floor);
        msg.append(DELIMITER);
        msg.append(this.layer2Direction);
        msg.append(DELIMITER);
        msg.append(this.layer2Speed);
        msg.append(DELIMITER);
        msg.append(this.layer2Gusting ? "2" : "0");
        msg.append(DELIMITER);
        msg.append(this.layer2Turbulence);
        msg.append(DELIMITER);
        msg.append(this.layer3Ceiling);
        msg.append(DELIMITER);
        msg.append(this.layer3Floor);
        msg.append(DELIMITER);
        msg.append(this.layer3Direction);
        msg.append(DELIMITER);
        msg.append(this.layer3Speed);
        msg.append(DELIMITER);
        msg.append(this.layer3Gusting ? "3" : "0");
        msg.append(DELIMITER);
        msg.append(this.layer3Turbulence);
        msg.append(DELIMITER);
        msg.append(this.layer4Ceiling);
        msg.append(DELIMITER);
        msg.append(this.layer4Floor);
        msg.append(DELIMITER);
        msg.append(this.layer4Direction);
        msg.append(DELIMITER);
        msg.append(this.layer4Speed);
        msg.append(DELIMITER);
        msg.append(this.layer4Gusting ? "4" : "0");
        msg.append(DELIMITER);
        msg.append(this.layer4Turbulence);
        return msg.toString();
    }

    public static PDUWindData parse(String[] fields) throws PDUFormatException {
        if (fields.length < 26) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUWindData(
                    fields[0],
                    fields[1],
                    Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]),
                    Integer.parseInt(fields[4]),
                    Integer.parseInt(fields[5]),
                    fields[6].equals("1"),
                    Integer.parseInt(fields[7]),
                    Integer.parseInt(fields[8]),
                    Integer.parseInt(fields[9]),
                    Integer.parseInt(fields[10]),
                    Integer.parseInt(fields[11]),
                    fields[12].equals("1"),
                    Integer.parseInt(fields[13]),
                    Integer.parseInt(fields[14]),
                    Integer.parseInt(fields[15]),
                    Integer.parseInt(fields[16]),
                    Integer.parseInt(fields[17]),
                    fields[18].equals("1"),
                    Integer.parseInt(fields[19]),
                    Integer.parseInt(fields[20]),
                    Integer.parseInt(fields[21]),
                    Integer.parseInt(fields[22]),
                    Integer.parseInt(fields[23]),
                    fields[24].equals("1"),
                    Integer.parseInt(fields[25])
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

