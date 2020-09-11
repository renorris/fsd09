package com.reese.fsd.pdu;

public class PDUTemperatureData extends PDUBase {

    public String from;
    public String to;
    public Integer layer1Ceiling;
    public Integer layer1Temperature;
    public Integer layer2Ceiling;
    public Integer layer2Temperature;
    public Integer layer3Ceiling;
    public Integer layer3Temperature;
    public Integer layer4Ceiling;
    public Integer layer4Temperature;
    public Integer pressure;

    public PDUTemperatureData(String from, String to, Integer layer1Ceiling, Integer layer1Temperature, Integer layer2Ceiling, Integer layer2Temperature, Integer layer3Ceiling, Integer layer3Temperature, Integer layer4Ceiling, Integer layer4Temperature, Integer pressure) {
        this.from = from;
        this.to = to;
        this.layer1Ceiling = layer1Ceiling;
        this.layer1Temperature = layer1Temperature;
        this.layer2Ceiling = layer2Ceiling;
        this.layer2Temperature = layer2Temperature;
        this.layer3Ceiling = layer3Ceiling;
        this.layer3Temperature = layer3Temperature;
        this.layer4Ceiling = layer4Ceiling;
        this.layer4Temperature = layer4Temperature;
        this.pressure = pressure;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#TD");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append(this.layer1Ceiling);
        msg.append(delimiter);
        msg.append(this.layer1Temperature);
        msg.append(delimiter);
        msg.append(this.layer2Ceiling);
        msg.append(delimiter);
        msg.append(this.layer2Temperature);
        msg.append(delimiter);
        msg.append(this.layer3Ceiling);
        msg.append(delimiter);
        msg.append(this.layer3Temperature);
        msg.append(delimiter);
        msg.append(this.layer4Ceiling);
        msg.append(delimiter);
        msg.append(this.layer4Temperature);
        msg.append(delimiter);
        msg.append(this.pressure);
        return msg.toString();
    }

    public static PDUTemperatureData parse(String[] fields) throws PDUFormatException {
        if (fields.length < 11) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUTemperatureData(
                    fields[0],
                    fields[1],
                    Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]),
                    Integer.parseInt(fields[4]),
                    Integer.parseInt(fields[5]),
                    Integer.parseInt(fields[6]),
                    Integer.parseInt(fields[7]),
                    Integer.parseInt(fields[8]),
                    Integer.parseInt(fields[9]),
                    Integer.parseInt(fields[10])
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

