package com.reese.fsd.pdu;

import java.util.ArrayList;
import java.util.List;

public class PDURadioMessage extends PDUBase {

    public String from;
    public Integer[] frequencies;
    public String message;

    public PDURadioMessage(String from, Integer[] frequencies, String message) {
        this.from = from;
        this.frequencies = frequencies;
        this.message = message;
    }

    @Override
    public String serialize() {
        StringBuilder freqs = new StringBuilder();
        for (Integer freq : this.frequencies) {
            if (freqs.length() > 0) { freqs.append("&"); }
            freqs.append("@").append(freq.toString());
        }
        StringBuilder msg = new StringBuilder("#TM");
        msg.append(this.from);
        msg.append(DELIMITER);
        msg.append(freqs.toString());
        msg.append(DELIMITER);
        msg.append(this.message);
        return msg.toString();
    }

    public static PDURadioMessage parse(String[] fields) throws PDUFormatException {
        if (fields.length < 3) {
            throw new PDUFormatException("Invalid field count.", reassemble(fields));
        }
        try {
            String[] freqsArray = fields[1].split("&");
            List<Integer> freqsList = new ArrayList<>();
            for (String str : freqsArray) {
                if (!str.equals("")) {
                    freqsList.add(Integer.parseInt(str));
                }
            }

            StringBuilder msg = new StringBuilder(fields[2]);
            for (int i = 3; i < fields.length; i++) {
                msg.append(":").append(fields[i]);
            }

            return new PDURadioMessage(
                    fields[0],
                    (Integer[]) freqsList.toArray(),
                    msg.toString()
            );
        }
        catch (Exception e) {
            throw new PDUFormatException("Parse error -> " + e.toString(), reassemble(fields));
        }
    }
}

