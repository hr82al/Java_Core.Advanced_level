package ru.geekbrains.java2.lesson8;

import java.util.Objects;

public class Message {

    private MessageType type;
    private String body;

    public Message(MessageType type, String msg) {
        this.type = type;
        this.body = msg;
    }

    public static Message authMsg(String msg) {
        return new Message(MessageType.AUTH_MESSAGE, msg);
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        switch (type) {
            case AUTH_MESSAGE:
            case EXIT_COMMAND:
            case PRIVATE_MESSAGE:
            case BROADCAST_CHAT:
            default:
                return type + " # " + body;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return type == message.type &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, body);
    }
}
