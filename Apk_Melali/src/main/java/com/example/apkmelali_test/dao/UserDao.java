package com.example.apkmelali_test.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apkmelali_test.entity.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    User findByUsernameAndPassword(String username, String password);
}
