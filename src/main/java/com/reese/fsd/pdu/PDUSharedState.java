package com.reese.fsd.pdu;

public class PDUSharedState extends PDUBase {

    public String from;
    public String to;
    public SharedStateType sharedStateType;
    public String target;
    public String value;

    public PDUSharedState(String from, String to, SharedStateType sharedStateType, String target, String value) {
        this.from = from;
        this.to = to;
        this.sharedStateType = sharedStateType;
        this.target = target;
        this.value = value;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#PC");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append("CCP");
        msg.append(delimiter);
        switch (this.sharedStateType) {
            case SCRATCHPAD -> msg.append("SC");
            case BEACON_CODE -> msg.append("BC");
            case VOICE_TYPE -> msg.append("VT");
            case TEMP_ALT -> msg.append("TA");
        }
        msg.append(delimiter);
        msg.append(this.target);
        msg.append(delimiter);
        msg.append(this.value);
        return msg.toString();
    }

    public static PDUSharedState parse(String[] fields) throws PDUFormatException {
        if (fields.length < 6) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            SharedStateType sharedStateType;
            switch (fields[3]) {
                case "SC" -> sharedStateType = SharedStateType.SCRATCHPAD;
                case "BC" -> sharedStateType = SharedStateType.BEACON_CODE;
                case "VT" -> sharedStateType = SharedStateType.VOICE_TYPE;
                case "TA" -> sharedStateType = SharedStateType.TEMP_ALT;
                default -> sharedStateType = SharedStateType.UNKNOWN;
            }

            return new PDUSharedState(
                    fields[0],
                    fields[1],
                    sharedStateType,
                    fields[4],
                    fields[5]
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

