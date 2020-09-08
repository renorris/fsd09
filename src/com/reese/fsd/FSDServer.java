package com.reese.fsd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FSDServer {

    private ServerSocket serverSocket;

    public static void main() throws IOException {
        FSDServer fsdServer = new FSDServer();
        fsdServer.start();
    }

    private void start() throws IOException {
        this.serverSocket = new ServerSocket(6809);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
        }
    }
}
