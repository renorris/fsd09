package com.reese.fsd.pdu;

import java.util.ArrayList;
import java.util.List;

public class PDUClientQuery extends PDUBase {

    public String from;
    public String to;
    public ClientQueryType queryType;
    public List<String> payload;

    public PDUClientQuery(String from, String to, ClientQueryType queryType, List<String> payload) {
        this.from = from;
        this.to = to;
        this.queryType = queryType;
        this.payload = payload;
    }

    @Override
    public String serialize() {
        StringBuilder msg = new StringBuilder("$CQ");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(this.to);
        msg.append(DELIMITER);
        msg.append(ClientQueryType.getQueryTypeID(this.queryType));
        if (!payload.isEmpty()) {
            for (String payloadItem : this.payload) {
                msg.append(DELIMITER);
                msg.append(payloadItem);
            }
        }
        return msg.toString();
    }

    public static PDUClientQuery parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            List<String> payload = new ArrayList<>();
            if (fields.length > 3) {
                for (int i = 3; i < fields.length; i++) {
                    payload.add(fields[i]);
                }
            }
            return new PDUClientQuery(
                    fields[0],
                    fields[1],
                    ClientQueryType.fromString(fields[2]),
                    payload
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

