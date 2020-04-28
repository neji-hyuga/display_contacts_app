package com.alura.displaycontactsapp.dao;

import com.alura.displaycontactsapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final static List<Student> studentList = new ArrayList<>();

    public void saveStudent(Student student) {
        studentList.add(student);

    }

    public List<Student> allStudents() {
        return new ArrayList<>(studentList);
    }
}
