package com.alura.displaycontactsapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Entity
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String name;
    private String email;
    private Calendar startDay = Calendar.getInstance();

    public Calendar getStartDay() {
        return startDay;
    }

    public void setStartDay(Calendar startDay) {
        this.startDay = startDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean hasValidId() {
        return id > 0;
    }

    public String getCompleteName() {
        return name;
    }

    public String formattedDate() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(startDay.getTime());
    }

}
