package com.reese.fsd.pdu;

public class PDULegacyPlaneInfoResponse extends PDUBase {

    public String from;
    public String to;
    public EngineType engineType;
    public String csl;

    public PDULegacyPlaneInfoResponse(String from, String to, EngineType engineType, String csl) {
        this.from = from;
        this.to = to;
        this.engineType = engineType;
        this.csl = csl;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#SB");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append("PI");
        msg.append(delimiter);
        msg.append("X");
        msg.append(delimiter);
        msg.append("0");
        msg.append(delimiter);
        msg.append(this.engineType.ordinal());
        msg.append(delimiter);
        msg.append("CSL=" + this.csl);
        return msg.toString();
    }

    public static PDULegacyPlaneInfoResponse parse(String[] fields) throws PDUFormatException {
        if (fields.length < 6) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            return new PDULegacyPlaneInfoResponse(
                    fields[0],
                    fields[1],
                    EngineType.valueOf(fields[5].toUpperCase()),
                    fields[6]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

