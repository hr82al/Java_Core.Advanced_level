package ru.geekbrains.java2.lesson8;

import java.util.HashMap;
import java.util.Map;

public class BaseAuthService implements AuthService{

    private Map<String, User> users = new HashMap<>();

    public BaseAuthService() {
        users.put("nick1", new User("login1", "pass1", "nick1"));
        users.put("nick2", new User("login2", "pass2", "nick2"));
        users.put("nick3", new User("login3", "pass3", "nick3"));
    }

    @Override
    public String authByLoginAndPassword(String login, String password) {
        for (User user : users.values()) {
            if (login.equals(user.getLogin())
                    && password.equals(user.getPassword())
                    && user.isActive())
                return user.getNickname();
        }
        return null;
    }

    @Override
    public User createOrActivateUser(String login, String password, String nick) {
        User user = new User(login, password, nick);
        if (users.containsKey(nick)) {
            users.get(nick).setActive(true);
            System.out.println("User with nick " + nick + "already exist");
        } else {
            users.put(nick, user);
            persist();
        }
        return user;
    }

    private void persist() {
        //do some logic...
//        new File("users.txt");
    }

    @Override
    public boolean deactivateUser(String nick) {
        User user = users.get(nick);
        if (user != null) {
            user.setActive(false);
            return true;
        }
        return false;
    }
}
