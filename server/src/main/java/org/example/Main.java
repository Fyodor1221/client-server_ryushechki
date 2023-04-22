package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 8090;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port: %d", clientSocket.getPort());
                    out.println("Enter your name");
                    final String name = in.readLine();
                    out.println("Are you child?\n" + "yes / no");
                    switch(in.readLine()) {
                        case "yes": out.printf("Welcome to the kids area, %s! Let's play!", name);
                        break;
                        case "no": out.printf("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name);
                        break;
                        default: out.println("Invalid response. Go write typos anywhere else!");
                        break;
                    }
                }
            }
        }
    }
}