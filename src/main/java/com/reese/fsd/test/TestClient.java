package com.reese.fsd.test;

import com.reese.fsd.SafeKey;
import com.reese.fsd.pdu.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestClient {

    public static void main(String[] args) throws IOException, InterruptedException{
        Socket socket = new Socket("127.0.0.1", 6809);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), false);
        BufferedReader in = new BufferedReader(new InputStreamReader((socket.getInputStream())));

        String callsign = "TEST_ATC";
        String realName = "Bob";
        String cid = "123";
        String password = "s3cr3t";
        NetworkRating rating = NetworkRating.S1;
        ProtocolRevision proto = ProtocolRevision.VATSIM_AUTH;
        NetworkFacility facility = NetworkFacility.GND;

        PDUClientIdentification identPDU = new PDUClientIdentification(
                callsign,
                69,
                "VRC 1.2.6",
                1,
                2,
                "12345",
                "-69",
                SafeKey.generate(10)
        );
        out.write(identPDU.serialize() + PDUBase.PACKET_DELIMITER);

        PDUAddATC addPDU = new PDUAddATC(
                callsign,
                realName,
                cid,
                password,
                rating,
                proto
        );
        out.write(addPDU.serialize() + PDUBase.PACKET_DELIMITER);

        PDUATCPosition positionPDU = new PDUATCPosition (
                callsign,
                23900,
                facility,
                20,
                rating,
                32.824632,
                -116.972460
        );
        out.write(positionPDU.serialize() + PDUBase.PACKET_DELIMITER);

        out.flush();
    }

}
