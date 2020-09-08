package com.reese.fsd;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FSDConnection {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private FSDUserInfo info;

    public FSDConnection(Socket socket, PrintWriter out, BufferedReader in) {
        this.socket = socket;
        this.out = out;
        this.in = in;
    }

    public void start() {

    }

}
