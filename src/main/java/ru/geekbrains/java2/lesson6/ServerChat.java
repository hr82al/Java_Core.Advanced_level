package ru.geekbrains.java2.lesson6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerChat {
    public static void main(String[] args) {
        System.out.println("Запуск сервера.");
        try (ServerSocket serverSocket = new ServerSocket(8189);
             Socket socket = serverSocket.accept()) {

            Scanner in = new Scanner(socket.getInputStream());
            Scanner input = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Клиент подлключился к серверу.");
            out.println("Успешное подключение к серверу.");
            new Chat(in, input, out, "Server").run();
        } catch (IOException e) {
            System.out.println("Произошла ошибка");
        }
    }
}