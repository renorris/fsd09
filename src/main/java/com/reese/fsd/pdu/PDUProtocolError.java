package com.reese.fsd.pdu;

public class PDUProtocolError extends PDUBase {

    public String from;
    public String to;
    public NetworkError errorType;
    public String param;
    public String message;
    public Boolean fatal;

    public PDUProtocolError(String from, String to, NetworkError errorType, String param, String message, Boolean fatal) {
        this.from = from;
        this.to = to;
        this.errorType = errorType;
        this.param = param;
        this.message = message;
        this.fatal = fatal;
    }

    @Override
    public String serialize() {
        Integer errorTypeInt = this.errorType.ordinal();
        StringBuilder errorTypeStr = new StringBuilder(errorTypeInt.toString());
        while (errorTypeStr.length() < 3) {
            errorTypeStr.insert(0, "0");
        }

        StringBuilder msg = new StringBuilder("$ER");
        msg.append(this.from);
        msg.append(delimiter);
        msg.append(this.to);
        msg.append(delimiter);
        msg.append(errorTypeStr.toString());
        msg.append(delimiter);
        msg.append(this.param);
        msg.append(delimiter);
        msg.append(this.message);
        return msg.toString();
    }

    public static PDUProtocolError parse(String[] fields) throws PDUFormatException {
        if (fields.length < 5) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            NetworkError err = NetworkError.values()[Integer.parseInt(fields[2])];
            Boolean fatal = ((err == NetworkError.CALLSIGN_IN_USE) || (err == NetworkError.CALLSIGN_INVALID) || (err == NetworkError.ALREADY_REGISTERED) || (err == NetworkError.INVALID_LOGON) || (err == NetworkError.INVALID_PROTOCOL_REVISION) || (err == NetworkError.REQUESTED_LEVEL_TOO_HIGH) || (err == NetworkError.SERVER_FULL) || (err == NetworkError.CERTIFICATE_SUSPENDED) || (err == NetworkError.INVALID_POSITION_FOR_RATING) || (err == NetworkError.UNAUTHORIZED_SOFTWARE));
            return new PDUProtocolError(
                    fields[0],
                    fields[1],
                    err,
                    fields[3],
                    fields[4],
                    fatal
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

