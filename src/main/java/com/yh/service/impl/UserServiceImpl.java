package com.yh.service.impl;

import com.yh.entity.User;
import com.yh.exceptions.UserNotFoundException;
import com.yh.service.UserService;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final List<User> users = new ArrayList<>();

    public User setUser(int balance) {
        User created = User.createUser(balance);
        users.add(created);
        return created;
    }

    public User getUser(long id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAll() { return users; }
}
