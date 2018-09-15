package ru.geekbrains.java2.lesson8;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server server;
    private String nick;
    private Channel channel;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;

        try {
            channel = ChannelBase.of(socket);
            new Thread(() -> {
                auth();
                System.out.println(nick + " handler waiting for new massages");
                while (socket.isConnected()) {
                    Message msg = channel.getMessage();
                    if (msg == null) continue;
                    switch (msg.getType()) {
                        case EXIT_COMMAND:
                            server.unsubscribe(this);
                            break;
                        case PRIVATE_MESSAGE:
                            sendPrivateMessage(msg.getBody());
                            break;
                        case BROADCAST_CHAT:
                            server.sendBroadcastMessage(nick + " : " + msg.getBody());
                        default:
                            System.out.println("invalid message type");
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPrivateMessage(String messageWithNickTo) {
        int firstSpaceIndex = messageWithNickTo.indexOf(" ");
        final String nickTo = messageWithNickTo.substring(0, firstSpaceIndex);
        final String message = messageWithNickTo.substring(firstSpaceIndex).trim();
        if (server.isNickTaken(nickTo)) {
            server.sendPrivateMessage(nick, nickTo, nick + " -> " + nickTo + " : " + message);
        } else {
            sendMessage(nickTo + " not taken!");
        }
    }

    /**
     * Wait for command: "/auth login1 pass1"
     */
    private void auth() {
        while (true) {
            if (!channel.hasNextLine()) continue;
            Message message = channel.getMessage();
            if (MessageType.AUTH_MESSAGE.equals(message.getType())) {
                String[] commands = message.getBody().split(" ");// /login1 pass1
                if (commands.length >= 2) {
                    String login = commands[0];
                    String password = commands[1];
                    System.out.println("Try to login with " + login + " and " + password);
                    String nick = server.getAuthService()
                            .authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        channel.sendMessage(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        channel.sendMessage(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);
                        channel.sendMessage(msg);
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                channel.sendMessage("Invalid command!");
            }
        }
    }

    public void sendMessage(String msg) {
        channel.sendMessage(msg);
    }

    public String getNick() {
        return nick;
    }
}
