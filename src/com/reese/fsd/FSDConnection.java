package com.reese.fsd;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FSDConnection {

    Socket socket;
    PrintWriter out;
    BufferedReader in;

    public FSDConnection(Socket socket, PrintWriter out, BufferedReader in) {
        this.socket = socket;
        this.out = out;
        this.in = in;
    }

    public void start() {

    }

}
