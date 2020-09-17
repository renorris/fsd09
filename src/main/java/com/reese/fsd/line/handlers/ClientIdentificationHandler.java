package com.reese.fsd.line.handlers;

import com.reese.fsd.UserAPI;
import com.reese.fsd.UserData;
import com.reese.fsd.line.Line;
import com.reese.fsd.pdu.*;

public class ClientIdentificationHandler extends LineHandler {

    ClientIdentificationHandler(LineHandlerArgs args) {
        super(args);
    }

    @Override
    public String[] process() {
        PDUClientIdentification pdu;
        try {
            pdu = PDUClientIdentification.parse(this.line.fields);
        }
        catch (PDUFormatException e) {
            System.out.println(e.toString());
            return new String[] { PDUProtocolError.generateSyntaxError("Syntax error (param)", "Syntax error (message)").serialize() };
        }

        if (this.userData.getLoginStage() == 0) {
            this.userData.setCallsign(pdu.from);
            this.userData.setCid(pdu.cid);
            this.userData.setClientID(pdu.clientID);
            this.userData.setClientName(pdu.clientName);
            this.userData.setMajorVersion(pdu.majorVersion);
            this.userData.setMinorVersion(pdu.minorVersion);
            this.userData.setSysUID(pdu.sysUID);
            this.userData.setInitialChallengeKey(pdu.initialChallenge);
            this.userData.setLoginStage(1);
        }
        else {
            this.shouldDisconnect = true;
        }

        return new String[0];
    }
}
