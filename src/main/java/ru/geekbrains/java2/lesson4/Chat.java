package ru.geekbrains.java2.lesson_4;

public class Chat {
    public static void main(String[] args) {
        ChatWindow chatWindow = new ChatWindow();
        chatWindow.init();
        Messages messages = new Messages();
        Sender sender = new Sender(chatWindow, messages);
        sender.init();
    }
}
