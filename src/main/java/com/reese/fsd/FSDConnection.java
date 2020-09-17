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

    public FSDConnection(Socket socket, PrintWriter out, BufferedReader in, UserAPI UserAPI) {
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.userAPI = UserAPI;
        this.userData = new UserData();
    }

    public void start() throws InterruptedException, IOException {

        PDUServerIdentification serverIdentPDU = new PDUServerIdentification (
                PDUBase.SERVER_CALLSIGN,
                "CLIENT",
                "VATSIM FSD V3.14",
                SafeKey.generate(22)
        );
        this.out.write(serverIdentPDU.serialize() + PDUBase.PACKET_DELIMITER);
        this.out.flush();

        eventloop:
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
                LineHandlerArgs args = new LineHandlerArgs(lineObj, this.userAPI, this.userData);
                String[] returnedLines = HandlerDispatcher.dispatch(args).process();

                for (String line : returnedLines) {
                    this.out.write(line + PDUBase.PACKET_DELIMITER);
                }

                if (this.userData.getShouldDisconnect()) {
                    this.out.flush();
                    this.socket.close();
                    break eventloop;
                }
            }

            this.out.flush();

            Thread.sleep(100);
        }
        Thread.sleep(10000);
    }

}
