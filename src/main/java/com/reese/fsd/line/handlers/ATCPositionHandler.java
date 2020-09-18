package com.reese.fsd.line.handlers;

import com.reese.fsd.LoginStage;
import com.reese.fsd.pdu.*;

public class ATCPositionHandler extends LineHandler {

    ATCPositionHandler(LineHandlerArgs args) {
        super(args);
    }

    @Override
    public String[] process() {
        PDUATCPosition pdu;
        try {
            pdu = PDUATCPosition.parse(this.line.fields);
        }
        catch (PDUFormatException e) {
            System.out.println(e.toString());
            return new String[] { PDUProtocolError.generateSyntaxError().serialize() };
        }

        this.userData.setFrequency(pdu.frequency);
        this.userData.setFacility(pdu.facility);
        this.userData.setVisibilityRange(pdu.visibilityRange);
        // Skip network rating because we only want to write it once during the login phase
        this.userData.setLatitude(pdu.lat);
        this.userData.setLongitude(pdu.lon);

        if (this.userData.getLoginStage() == LoginStage.ADD_COMPLETE) {
            if (this.userData.getNetworkRating().validATC()) {
                this.userData.setValidATC(true);
            }
            this.userData.setLoggedIn(true);
            this.userData.setLoginStage(LoginStage.COMPLETE);
            PDUTextMessage welcomeMessage = new PDUTextMessage(
                    PDUBase.SERVER_CALLSIGN,
                    this.userData.getCallsign(),
                    "Logged in"
            );
            return new String[] { welcomeMessage.serialize() };
        }

        return new String[0];
    }
}
