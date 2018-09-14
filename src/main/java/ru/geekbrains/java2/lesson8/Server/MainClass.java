package ru.geekbrains.java2.lesson8.Server;

public class MainClass {
    public static void main(String[] args) {
        ChatServerImpl chatServer = new ChatServerImpl();
        chatServer.start();
    }
}
