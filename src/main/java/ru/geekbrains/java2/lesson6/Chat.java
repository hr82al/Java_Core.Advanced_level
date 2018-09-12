package ru.geekbrains.java2.lesson6;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Chat {
    private  Scanner in;
    private Scanner input;
    private  PrintWriter out;
    private String name;

    public Chat(Scanner in, Scanner input, PrintWriter out, String name) {
            this.in = in;
            this.input = input;
            this.out = out;
            this.name = name;
    }

    public void run() {
        sender();
        receiver();
    }

    private void sender() {
        new Thread(() -> {
            while (input.hasNext()) {
                final String str = input.nextLine();
                checkEnd(str);
                out.println(str);
            }
        }).start();
    }

    private String prefix() {
        DateFormat df = new SimpleDateFormat(" (HH:mm:ss): ");
        return name + df.format(new Date());
    }

    private void receiver() {
        while (in.hasNext()){
            final String str = in.nextLine();
            System.out.println(str);
        }
    }

    private void checkEnd(String str) {
        if (str.trim().endsWith("end")) {
            out.println(prefix() + "Отключился от сети!");
            System.out.println("Завеншение работы " + name.toLowerCase());
            System.exit(0);
        }
    }
}
