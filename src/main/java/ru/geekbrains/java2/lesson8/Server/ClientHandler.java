package ru.geekbrains.java2.lesson8.Server;

import java.net.Socket;

public class ClientHandler {
    private ChatServer chatServer;
    private ChatChannelImpl chatChannel;
    private Socket socket;


    public ClientHandler(Socket socket, ChatServerImpl chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
        System.out.println("client hadler is start");
        new Thread(() -> {
            chatChannel = ChatChannelImpl.of(socket);
            auth();

        }).start();
    }

    private void auth() {
        while (socket.isConnected()) {
            if (chatChannel.hasNextMessage()) {
                ChatMessage msg = chatChannel.getMessage();
                chatChannel.sendMessage(msg);
                if (msg.isType(ChatMessageType.AUTH_MESSAGE)) {
                    String[] authData = msg.getBody().split(" "); //login pass
                    if (authData.length >= 2) {
                        String login = authData[0];
                        String password = authData[1];
                        System.out.println("Try to login with " + login + " and " + password);

                    }
                }
            }
        }
    }
}
