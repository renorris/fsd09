package com.reese.fsd.pdu;

import java.util.ArrayList;
import java.util.List;

public class PDUFlightStrip extends PDUBase {

    public String from;
    public String to;
    public String target;
    public String formatID;
    public List<String> annotations;

    public PDUFlightStrip(String from, String to, String target, String formatID, List<String> annotations) {
        this.from = from;
        this.to = to;
        this.target = target;
        this.formatID = formatID;
        this.annotations = annotations;
    }

    public PDUFlightStrip(String from, String to, String target) {
        this.from = from;
        this.to = to;
        this.target = target;
        this.formatID = "";
        this.annotations = new ArrayList<>();
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("#PC");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append("CCP");
        msg.append(DELIMITER);
        msg.append("ST");
        msg.append(DELIMITER);
        msg.append(this.target);
        if (!this.formatID.isEmpty() || this.annotations.size() > 0) {
            msg.append(DELIMITER);
            msg.append(this.formatID);
            for (String annotation : this.annotations) {
                msg.append(DELIMITER);
                msg.append(annotation);
            }
        }
        return msg.toString();
    }

    public static PDUFlightStrip parse(String[] fields) throws PDUFormatException {
        if (fields.length < 5) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            if (fields.length > 5) {
                List<String> annotations = new ArrayList<>();
                if (fields.length > 6) {
                    for (int i = 6; i < fields.length; i++) {
                        annotations.add(fields[i]);
                    }
                }
                return new PDUFlightStrip(
                        fields[0],
                        fields[1],
                        fields[4],
                        fields[5],
                        annotations
                );
            } else {
                return new PDUFlightStrip (
                        fields[0],
                        fields[1],
                        fields[4]
                );
            }
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

