package ru.geekbrains.java2.lesson8.Server;


public interface ChatChannel {
    void sendMessage(ChatMessage msg);
    ChatMessage getMessage();
    boolean hasNextMessage();
}
