package com.alura.displaycontactsapp.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.alura.displaycontactsapp.model.Phone;

@Dao
public interface PhoneDAO {
    @Query("SELECT * FROM Phone WHERE studentId = :studentId LIMIT 1")
    Phone searchFirstStudentPhone(int studentId);
}
