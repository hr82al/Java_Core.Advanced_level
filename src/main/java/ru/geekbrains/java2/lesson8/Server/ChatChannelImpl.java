package ru.geekbrains.java2.lesson8.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatChannelImpl implements ChatChannel{
    private PrintWriter pw;
    private Scanner sc;

    private ChatChannelImpl(Socket socket) {
        try {
            pw = new PrintWriter(socket.getOutputStream());
            sc = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FIXME
        pw.println("pw created");
        System.out.println("pw created");
    }

    public static ChatChannelImpl of(Socket socket){
        //FIXME
        System.out.println("create chanel");
        return new ChatChannelImpl(socket);
    }

    @Override
    public void sendMessage(ChatMessage msg) {
        pw.println(msg.getBody());
    }

    public void sendMessage(String str) {
        pw.println(str);
    }
    @Override
    public ChatMessage getMessage() {
        return new ChatMessage(sc.nextLine());
    }

    @Override
    public boolean hasNextMessage() {
        return sc.hasNext();
    }
}
