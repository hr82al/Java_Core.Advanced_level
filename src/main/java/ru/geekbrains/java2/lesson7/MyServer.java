package ru.geekbrains.java2.lesson7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void start() {
        final int PORT = 8189;
        ServerSocket serverSocket = null;
        try {
             serverSocket = new ServerSocket(PORT);
             while (true) {
                 Socket socket = serverSocket.accept();
                 new ClientHandler(socket);
             }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
