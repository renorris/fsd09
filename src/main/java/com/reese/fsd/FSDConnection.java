package com.reese.fsd;

import com.reese.fsd.line.Line;
import com.reese.fsd.line.LineFactory;
import com.reese.fsd.line.handlers.HandlerDispatcher;
import com.reese.fsd.line.handlers.LineHandlerArgs;
import com.reese.fsd.pdu.PDUBase;
import com.reese.fsd.pdu.PDUServerIdentification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FSDConnection {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private UserAPI userAPI;
    private UserData userData;
    private Boolean shouldDisconnect;

    public FSDConnection(Socket socket, PrintWriter out, BufferedReader in, UserAPI UserAPI) {
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.userAPI = UserAPI;
        this.userData = new UserData();
        this.shouldDisconnect = false;
    }

    // Main connection event loop
    public void start() throws InterruptedException, IOException {

        String initialServerChallengeKey = SafeKey.generate(22);
        PDUServerIdentification serverIdentPDU = new PDUServerIdentification (
                PDUBase.SERVER_CALLSIGN,
                "CLIENT",
                "VATSIM FSD V3.14",
                initialServerChallengeKey
        );
        this.out.write(serverIdentPDU.serialize() + PDUBase.PACKET_DELIMITER);
        this.out.flush();

        while (this.socket != null && this.socket.isConnected()) {
            // Get all available lines from buffer
            List<String> linesToProcess = new ArrayList<>();
            while (this.in.ready()) {
                linesToProcess.add(this.in.readLine());
            }

            // Process all available lines
            for (String lineStr : linesToProcess) {
                System.out.println("Got line -> " + lineStr);
                Line lineObj = LineFactory.createFromString(lineStr);
                LineHandlerArgs args = new LineHandlerArgs(lineObj, this.userAPI, this.userData, this.shouldDisconnect);
                String[] returnedLines = HandlerDispatcher.dispatch(args).process();
                for (String line : returnedLines) {
                    this.out.write(line + PDUBase.PACKET_DELIMITER);
                }
            }

            this.out.flush();

            if (this.shouldDisconnect) {
                this.socket.close();
                break;
            }

            Thread.sleep(100);
        }
    }

}
