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
}
