package com.alura.displaycontactsapp.dao;

import com.alura.displaycontactsapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final static List<Student> studentList = new ArrayList<>();
    private static int idCounter = 1;

    public void saveStudent(Student student) {
        student.setId(idCounter);
        studentList.add(student);
        createsNewIdNumber();
    }

    private void createsNewIdNumber() {
        idCounter++;
    }

    //example od edition, first you need to find your object, than you can edit it
    public void editStudent(Student student) {
        Student studentToBeEdited = getByStudentId(student);
        if (studentToBeEdited != null) {
            int studentPosition = studentList.indexOf(studentToBeEdited);
            studentList.set(studentPosition, student);
        }
    }

    private Student getByStudentId(Student student) {
        for (Student std :
                studentList) {
            if (std.getId() == student.getId()) {
                return std;

            }
        }
        return null;
    }

    public List<Student> allStudents() {
        return new ArrayList<>(studentList);
    }

    public void deleteStudent(Student student) {
        Student chosenStudent = getByStudentId(student);
        if (chosenStudent != null) {
            studentList.remove(chosenStudent);
        }

    }
}
