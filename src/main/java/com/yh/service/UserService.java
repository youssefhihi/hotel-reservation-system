package com.yh.service;

import com.yh.entity.User;

import java.util.List;

public interface UserService {
    User setUser(int balance);
    User getUser(long id);
    List<User> getAll();
}
