package ru.geekbrains.java2.lesson8.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static Socket socket;
    private static Scanner sc;
    private static PrintWriter pw;

    public static void main(String[] args) {
        chatConsole();
        start();
    }

    private static void receiveMessages() {
        //if (socket == null || socket.isClosed()) return;
        new Thread(() -> {
            int i = 0;
            for (i = 0; i < 1000; i++) {
                if (socket == null || socket.isClosed()) break;
                if (sc.hasNext()) {
                    String str = sc.nextLine();
                    System.out.println("form serv: " + str);
                } else {//server has broken down connection
                    stop();
                    break;
                }
            }
            System.out.println("Получено строк: " + i);
        }).start();
    }

    private static void chatConsole() {
        Scanner input = new Scanner(System.in);
        new Thread(() -> {
            int i = 0;
            for (i = 0; i < 1000; i++) {
                if (true) {
                    String str = input.nextLine();
                    if (!hasText(str)) continue;
                    consoleCommand(str);
                }
            }
            System.out.println("Отправлено строк: " + i);
        }).start();
    }

    private static void consoleCommand(String str) {
        switch (firstWord(str)) {
            case "start":
                start();
                break;
            case "restart":
                restart();
                break;
            case "ston":
                stop();
                break;
            default:
                send(str);
        }
    }

    private static void send(String str) {
        pw.println(str);
    }

    private static void restart() {
        System.out.println("Перезапуск клиента.");
        stop();
        start();
    }

    private static void stop() {
        if (socket != null && socket.isConnected()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void start() {
        final String ADDRESS = "localhost";
        final int PORT = 8189;
        System.out.println("Клиент подлючается");
        try {
            socket = new Socket(ADDRESS, PORT);
            sc = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream());
            System.out.println("Клинет подлкючился");
        } catch (IOException e) {
            System.out.println("Ошибка подключения к серверу.");
        }
        receiveMessages();
    }

    private static String firstWord(String str) {
        int firstSpace = str.indexOf(" ");
        if (firstSpace == -1) firstSpace = 0;
        return str.substring(firstSpace).trim();
    }

    private static boolean hasText(String str){
        if (str == null) return false;
        if(str.trim().isEmpty()) return false;
        return true;
    }
}
