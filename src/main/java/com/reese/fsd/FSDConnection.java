package com.reese.fsd;

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
    private FSDUserInfo info;
    private FSDUsers fsdUsers;

    public FSDConnection(Socket socket, PrintWriter out, BufferedReader in, FSDUsers fsdUsers) {
        this.socket = socket;
        this.out = out;
        this.in = in;
        this.fsdUsers = fsdUsers;
    }

    // Main connection event loop
    public void start() throws InterruptedException, IOException {

        this.out.write("$DISERVER:CLIENT:VATSIM FSD V3.13:519312befee841f793b5fa4070f45ce8\r\n");
        this.out.flush();

        while (this.socket != null && this.socket.isConnected()) {

            // Get all available lines from buffer
            List<String> linesToProcess = new ArrayList<>();

            while (true) {
                if (this.in.ready()) {
                    linesToProcess.add(this.in.readLine());
                }
                else {
                    break;
                }
            }

            // Process all available lines
            for (String lineStr : linesToProcess) {
                System.out.println("Got line -> " + lineStr);
                Line lineObj = LineFactory.createFromString(lineStr);

            }

            Thread.sleep(100);
        }

    }

}
