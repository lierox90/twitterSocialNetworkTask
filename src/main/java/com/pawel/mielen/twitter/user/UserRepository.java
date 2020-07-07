package com.pawel.mielen.twitter.user;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository {
    private Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    public void add(User user) {
        users.put(user.getLogin(), user);
    }

    public User get(String login) {
        return users.get(login);
    }
}
