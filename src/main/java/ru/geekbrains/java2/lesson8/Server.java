package ru.geekbrains.java2.lesson8;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    private AuthService authService;
    private Map<String, ClientHandler> clients = new HashMap<>();

    public Server(AuthService authService) {
        this.authService = authService;
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
            close();
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        AuthService baseAuthService = new BaseAuthService();
        Server server = new Server(baseAuthService);
        server.start();
    }

    private void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendBroadcastMessage(String msg) {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(msg);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickTaken(String nick) {
        return clients.containsKey(nick);
    }

    public void subscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " подключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.put(clientHandler.getNick(), clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " отключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.remove(clientHandler.getNick());
    }

    public void sendPrivateMessage(String nickFrom, String nickTo, String message) {
        ClientHandler fromClient = clients.get(nickFrom);
        if (fromClient != null)
            fromClient.sendMessage(message);

        if (clients.containsKey(nickTo))
            clients.get(nickTo).sendMessage(message);
    }
}
