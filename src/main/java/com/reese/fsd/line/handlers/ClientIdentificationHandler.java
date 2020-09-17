package com.reese.fsd.line.handlers;

import com.reese.fsd.LoginStage;
import com.reese.fsd.pdu.PDUClientIdentification;
import com.reese.fsd.pdu.PDUFormatException;
import com.reese.fsd.pdu.PDUProtocolError;

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
            return new String[] { PDUProtocolError.generateSyntaxError().serialize() };
        }

        if (this.userData.getLoginStage() == LoginStage.PRE_IDENTIFICATION) {
            this.userData.setCallsign(pdu.from);
            this.userData.setCid(pdu.cid);
            this.userData.setClientID(pdu.clientID);
            this.userData.setClientName(pdu.clientName);
            this.userData.setMajorVersion(pdu.majorVersion);
            this.userData.setMinorVersion(pdu.minorVersion);
            this.userData.setSysUID(pdu.sysUID);
            this.userData.setInitialChallengeKey(pdu.initialChallenge);
            this.userData.setLoginStage(LoginStage.IDENTIFICATION_COMPLETE);
        }
        else {
            this.userData.setShouldDisconnect(true);
        }

        return new String[0];
    }
}
