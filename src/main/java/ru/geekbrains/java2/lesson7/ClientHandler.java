package ru.geekbrains.java2.lesson7;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler {
    private Scanner in;
    private PrintWriter out;
    private AuthService authService = new BaseAuthService();
    private static List<ClientHandler> clients = new ArrayList<>();
    private String nick;
    private Socket socket;


    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run(ClientHandler clientHandler) {
        new Thread(() -> {
            System.out.println("Новый клиент подлючился");
            auth(clientHandler);
            System.out.println(nick + " handler waiting for new massages");
            while (socket.isConnected()) {
                String s;
                if (in.hasNext()) {
                    s = in.nextLine();
                    if (s.startsWith("/w")) {
                        sendPrivateMessage(s);
                    } else {
                        if (s != null && s.equals("/exit"))
                            unsubscribe(this);
                        if (s != null && !s.isEmpty())
                            sendBroadcastMessage(nick + " : " + s);
                    }
                }
            }
        }).start();
    }

    private void sendPrivateMessage(String msg) {
        String[] commands = msg.split(" ");
        String msgForNick = commands[1];
        if (hasNick(msgForNick)) {
            int length = commands.length - 2;
            String[]  message = new String[length];
            System.arraycopy(commands, 2, message, 0, length);
            sendToNick(msgForNick, String.join(" ", message));
        }
    }

    private void sendToNick(String nick, String msg) {
        for (ClientHandler client : clients) {
            if (client.nick.equals(nick)) {
                String str = "From " + nick + ": " + String.join(" ", msg);
                client.out.println(str);
            }
        }
    }

    private boolean hasNick(String msgForNick) {
        for (ClientHandler client : clients) {
            if (client.nick.equals(msgForNick)) {
                return true;
            }
        }
        return  false;
    }

    /**
     * Wait for command: "/auth login1 pass1"
     */
    private void auth(ClientHandler clientHandler) {
        while (true) {
            if (!in.hasNextLine()) continue;
            String s = in.nextLine();
            if (s.startsWith("/auth")) {
                String[] commands = s.split(" ");// /auth login1 pass1
                if (commands.length >= 3) {
                    String login = commands[1];
                    String password = commands[2];
                    System.out.println("Try to login with " + login + " and " + password);
                    String nick = authService.authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        out.println(msg);
                    } else if (isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        out.println(msg);
                    } else {
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);
                        out.println(msg);
                        subscribe(clientHandler);
                        break;
                    }
                }
            } else {
                out.println("Invalid command!");
            }
        }
    }

    public boolean isNickTaken(String nick) {
        for (ClientHandler client : clients) {
            if (nick.equals(client.getNick()))
                return true;
        }
        return false;
    }

    public void subscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " подключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.add(clientHandler);
    }

    public String getNick() {
        return nick;
    }

    public void sendBroadcastMessage(String msg) {
        for (ClientHandler client : clients) {
            client.out.println(msg);
        }
    }


    public void unsubscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " отключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.remove(clientHandler);
    }
}