package com.unsapp.medicord.data.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medicine")
public class Medicine {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String nameMedicine;
    public String description;
    public String imageUri;
    public String reminderDays;
    public String reminderHours;
    public boolean status;
}
