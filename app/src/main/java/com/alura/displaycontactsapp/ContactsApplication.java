package com.alura.displaycontactsapp;

import android.app.Application;

import com.alura.displaycontactsapp.dao.StudentDAO;
import com.alura.displaycontactsapp.model.Student;

@SuppressWarnings("WeakerAccess")
public class ContactsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        createsTestStudent();
    }

    private void createsTestStudent() {
        StudentDAO dao = new StudentDAO();
        dao.saveStudent(new Student("ewandro", "4372352811", "wandilhos@uol.com.br"));
        dao.saveStudent(new Student("tâninha", "4372352811", "wandilhos@uol.com.br"));
    }
}
