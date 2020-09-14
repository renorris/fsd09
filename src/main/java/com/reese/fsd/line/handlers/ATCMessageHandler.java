package com.reese.fsd.line.handlers;

import com.reese.fsd.PUCManager;
import com.reese.fsd.line.Line;
import com.reese.fsd.pdu.PDUATCMessage;

public class ATCMessageHandler extends LineHandler {

    ATCMessageHandler(Line line, PUCManager pucManager) {
        super(line, pucManager);
    }

    @Override
    public String[] process() {
        PDUATCMessage pdu = PDUATCMessage.parse(this.line.fields);

        return new String[0];
    }
}
