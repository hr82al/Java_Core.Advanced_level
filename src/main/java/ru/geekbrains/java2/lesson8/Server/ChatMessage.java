package ru.geekbrains.java2.lesson8.Server;


public class ChatMessage {
    private ChatMessageType type;
    private String body;

    public ChatMessage(String str) {
        str = str.trim();
        if (str.charAt(0) == '/') { // if command
            str = str.substring(1);
            System.out.println("first word: " + getFirstWord(str));//FIXME
            switch (getFirstWord(str)) {
                case "auth":
                    type = ChatMessageType.AUTH_MESSAGE;
                    body = str.substring(str.indexOf(' '));
                    break;
                case "exit":
                    type = ChatMessageType.EXIT_COMMAND;
                    break;
                case "w":
                    type = ChatMessageType.PRIVATE_MESSAGE;
                    break;
                default:
                    type = ChatMessageType.UNKNOWN_COMMAND;
            }
        } else {
            type = ChatMessageType.BROADCAST_CHAT;
            body = str;
        }
        System.out.println("body: " + body); //FIXME
    }

    private String getFirstWord(String str) {
        final int firstSpace = str.indexOf(' ');
        return str.substring(0, firstSpace - 1);
    }

    public boolean isType(ChatMessageType authMessage) {
        return type == authMessage;
    }

    public String getBody() {
        return body;
    }
}
