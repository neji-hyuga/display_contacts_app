package com.alura.displaycontactsapp.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alura.displaycontactsapp.model.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
    void saveStudent(Student student); //Create

    @Query("SELECT * FROM student") //Read
    List<Student> allStudents();

    @Update
    void editStudent(Student student); //Update


    @Delete
    void deleteStudent(Student student); //Delete
}
