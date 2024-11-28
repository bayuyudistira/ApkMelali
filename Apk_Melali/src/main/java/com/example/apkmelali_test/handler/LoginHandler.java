package com.example.apkmelali_test.handler;

import android.content.SharedPreferences;

import com.example.apkmelali_test.dao.UserDao;
import com.example.apkmelali_test.entity.User;

public class LoginHandler {
    private UserDao userDao;
    private SharedPreferences preferences;

    public LoginHandler(UserDao userDao, SharedPreferences preferences) {
        this.userDao = userDao;
        this.preferences = preferences;
    }

    public boolean login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLoggedIn", true);
            editor.apply();
            return true;
        }
        return false;
    }
}

