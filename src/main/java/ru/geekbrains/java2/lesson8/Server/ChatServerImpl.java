package ru.geekbrains.java2.lesson8.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServerImpl implements ChatServer {
    private ServerSocket serverSocket;
    private Map<String, ClientHandler> clients = new HashMap<>();

    @Override
    public void start() {
        final int PORT = 8189;
        try {
            serverSocket = new ServerSocket(PORT);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("Client is connected.");
                new ClientHandler(socket, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restart() {
        stop();
        start();
    }

    @Override
    protected void finalize() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
