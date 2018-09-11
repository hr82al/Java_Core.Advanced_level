package ru.geekbrains.java2.lesson_4;

public class Messages {
    private String messages = "";

    public String getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        messages += message + "\n\n";
    }
}
