package com.reese.fsd.pdu;

public class PDUCloudData extends PDUBase {

    public String from;
    public String to;
    public Integer layer1Ceiling;
    public Integer layer1Floor;
    public Integer layer1Coverage;
    public Boolean layer1Icing;
    public Integer layer1Turbulence;
    public Integer layer2Ceiling;
    public Integer layer2Floor;
    public Integer layer2Coverage;
    public Boolean layer2Icing;
    public Integer layer2Turbulence;
    public Integer stormLayerCeiling;
    public Integer stormLayerFloor;
    public Integer stormLayerDeviation;
    public Integer stormLayerCoverage;
    public Integer stormLayerTurbulence;

    public PDUCloudData(String from, String to, Integer layer1Ceiling, Integer layer1Floor, Integer layer1Coverage, Boolean layer1Icing, Integer layer1Turbulence, Integer layer2Ceiling, Integer layer2Floor, Integer layer2Coverage, Boolean layer2Icing, Integer layer2Turbulence, Integer stormLayerCeiling, Integer stormLayerFloor, Integer stormLayerDeviation, Integer stormLayerCoverage, Integer stormLayerTurbulence) {
        this.from = from;
        this.to = to;
        this.layer1Ceiling = layer1Ceiling;
        this.layer1Floor = layer1Floor;
        this.layer1Coverage = layer1Coverage;
        this.layer1Icing = layer1Icing;
        this.layer1Turbulence = layer1Turbulence;
        this.layer2Ceiling = layer2Ceiling;
        this.layer2Floor = layer2Floor;
        this.layer2Coverage = layer2Coverage;
        this.layer2Icing = layer2Icing;
        this.layer2Turbulence = layer2Turbulence;
        this.stormLayerCeiling = stormLayerCeiling;
        this.stormLayerFloor = stormLayerFloor;
        this.stormLayerDeviation = stormLayerDeviation;
        this.stormLayerCoverage = stormLayerCoverage;
        this.stormLayerTurbulence = stormLayerTurbulence;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#CD");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(this.layer1Ceiling);
        msg.append(DELIMITER);
        msg.append(this.layer1Floor);
        msg.append(DELIMITER);
        msg.append(this.layer1Coverage);
        msg.append(DELIMITER);
        msg.append(this.layer1Icing ? "1" : "0");
        msg.append(DELIMITER);
        msg.append(this.layer1Turbulence);
        msg.append(DELIMITER);
        msg.append(this.layer2Ceiling);
        msg.append(DELIMITER);
        msg.append(this.layer2Floor);
        msg.append(DELIMITER);
        msg.append(this.layer2Coverage);
        msg.append(DELIMITER);
        msg.append(this.layer2Icing ? "1" : "0");
        msg.append(DELIMITER);
        msg.append(this.layer1Turbulence);
        msg.append(DELIMITER);
        msg.append(this.stormLayerCeiling);
        msg.append(DELIMITER);
        msg.append(this.stormLayerFloor);
        msg.append(DELIMITER);
        msg.append(this.stormLayerDeviation);
        msg.append(DELIMITER);
        msg.append(this.stormLayerCoverage);
        msg.append(DELIMITER);
        msg.append(this.stormLayerTurbulence);
        return msg.toString();
    }

    public static PDUCloudData parse(String[] fields) throws PDUFormatException {
        if (fields.length < 17) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDUCloudData(
                    fields[0],
                    fields[1],
                    Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]),
                    Integer.parseInt(fields[4]),
                    fields[5].equals("1"),
                    Integer.parseInt(fields[6]),
                    Integer.parseInt(fields[7]),
                    Integer.parseInt(fields[8]),
                    Integer.parseInt(fields[9]),
                    fields[10].equals("1"),
                    Integer.parseInt(fields[11]),
                    Integer.parseInt(fields[12]),
                    Integer.parseInt(fields[13]),
                    Integer.parseInt(fields[14]),
                    Integer.parseInt(fields[15]),
                    Integer.parseInt(fields[16])
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

