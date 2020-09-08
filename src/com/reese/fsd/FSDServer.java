package com.reese.fsd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FSDServer {

    private ServerSocket serverSocket;

    // Instantiate new FSDUsers for cross-thread data transfer
    private FSDUsers fsdUsers = new FSDUsers();

    public static void main() throws IOException {
        // Start server
        FSDServer fsdServer = new FSDServer();
        fsdServer.start();
    }

    private void start() throws IOException {
        // Listen on port 6809
        this.serverSocket = new ServerSocket(6809);

        while (true) {
            // Wait for a connection
            Socket clientSocket = serverSocket.accept();

            // Configure and instantiate new FSDConnection
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
            FSDConnection fsdConnection = new FSDConnection(clientSocket, out, in, this.fsdUsers);

            // Spin up a new thread for the connection
            Thread newThread = new Thread() {
                public void run() {
                    try {
                        fsdConnection.start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            newThread.start();
        }
    }
}
