package com.unsapp.medicord.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unsapp.medicord.data.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE email = :email")
    boolean findByEmail(String email);

    @Query("SELECT * FROM user WHERE email = :email AND  password =:password")
    User findByEmailAndPassword(String email, String password);

    @Insert
    void insertUser(User user);
}
