package ru.geekbrains.java2.lesson8;

import java.util.HashMap;
import java.util.Map;

public class TimeoutChecker {
    private static TimeoutChecker timeoutChecker = new TimeoutChecker();
    private static Map<ClientHandler, Long> nonAuthorizedSockets = new HashMap<>();
    private final static long TIME_LIMIT = 120 * 1000;

    private TimeoutChecker() {
        new Thread(() -> {
            while (true) {
                nonAuthorizedSockets.forEach(((client, aLong) -> {
                    if ((System.currentTimeMillis() - aLong) > TIME_LIMIT) {
                        client.sendMessage("Время на авторизацию истекло.\n Соединение разорвано.");
                        client.closeSocket();
                    }
                }));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void set(ClientHandler client) {
        nonAuthorizedSockets.put(client, System.currentTimeMillis());
    }

    public static void unset(ClientHandler client) {
        nonAuthorizedSockets.remove(client);
    }
}
