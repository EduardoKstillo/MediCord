package com.unsapp.medicord.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// create the user entity
@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String username;
    public String email;
    public String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
