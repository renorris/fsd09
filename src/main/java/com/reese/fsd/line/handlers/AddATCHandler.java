package com.reese.fsd.line.handlers;

import com.reese.fsd.LoginStage;
import com.reese.fsd.pdu.*;

public class AddATCHandler extends LineHandler {

    AddATCHandler(LineHandlerArgs args) {
        super(args);
    }

    @Override
    public String[] process() {
        PDUAddATC pdu;
        try {
            pdu = PDUAddATC.parse(this.line.fields);
        }
        catch (PDUFormatException e) {
            System.out.println(e.toString());
            return new String[] { PDUProtocolError.generateSyntaxError().serialize() };
        }

        if (this.userData.getLoginStage() == LoginStage.IDENTIFICATION_COMPLETE) {
            this.userData.setRealName(pdu.realName);
            this.userData.setNetworkRating(pdu.rating);
            this.userData.setProtocolRevision(pdu.proto);
        }
        else {
            this.userData.setShouldDisconnect(true);
        }

        if (!pdu.password.equals("bruh")) {
            PDUProtocolError pduError = PDUProtocolError.generateError(this.userData.getCallsign(), NetworkError.INVALID_LOGON, "Invalid credentials", "Invalid credentials");
            this.userData.setShouldDisconnect(true);
            return new String[] { pduError.serialize() };
        }

        this.userData.setLoginStage(LoginStage.ADD_COMPLETE);
        return new String[0];
    }
}
