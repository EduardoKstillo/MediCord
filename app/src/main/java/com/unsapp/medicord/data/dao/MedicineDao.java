package com.unsapp.medicord.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unsapp.medicord.data.models.Medicine;

import java.util.List;

@Dao
public interface MedicineDao {

    @Query("SELECT * FROM medicine")
    List<Medicine> findAll();

    @Query("SELECT * FROM medicine WHERE id =:id")
    Medicine findById(long id);

    @Insert
    void insertMedicine(Medicine medicine);
}
