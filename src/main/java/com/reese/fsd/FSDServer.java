package com.reese.fsd;

import com.reese.fsd.user.UserAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FSDServer {

    private ServerSocket serverSocket;

    // Instantiate new FSDUsers for cross-thread data transfer
    private com.reese.fsd.user.UserAPI UserAPI = new UserAPI();

    public static void main(String[] args) throws IOException {
        // Start server
        FSDServer fsdServer = new FSDServer();
        fsdServer.start();
    }

    private void start() throws IOException {
        // Listen on port 6809
        this.serverSocket = new ServerSocket(6809);

        System.out.println("Listening for connections");
        while (true) {
            // Wait for a connection
            Socket clientSocket = serverSocket.accept();
            clientSocket.setSoTimeout(5000);
            System.out.println("New connection -> " + clientSocket.getInetAddress());

            // Configure and instantiate new FSDConnection
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), false);
            BufferedReader in = new BufferedReader(new InputStreamReader((clientSocket.getInputStream())));
            FSDConnection fsdConnection = new FSDConnection(clientSocket, out, in, this.UserAPI);

            // Spin up a new thread for the connection
            Thread newThread = new Thread() {
                public void run() {
                    try {
                        fsdConnection.start();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            newThread.start();
        }
    }
}
