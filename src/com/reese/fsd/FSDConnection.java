package com.reese.fsd;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

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
    public void start() throws InterruptedException {
        while (this.socket != null && this.socket.isConnected()) {

            // Get all available lines from buffer
            String[] linesToProcess = (String[]) in.lines().toArray();

            // Process all available lines
            for (String line : linesToProcess) {

            }

            Thread.sleep(100);
        }

    }

}
