package com.example.apkmelali_test.repository;

import com.example.apkmelali_test.dao.UserDao;
import com.example.apkmelali_test.entity.User;

public class UserRepository {

    private final UserDao userDao;

    // Constructor injection for UserDao (no dependency on Android)
    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void registerUser(User user) {
        // Simulate inserting user to the database
        userDao.insert(user);
    }
}

