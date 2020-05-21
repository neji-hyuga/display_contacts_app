package com.alura.displaycontactsapp.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Phone {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String number;
    private TypePhoneNumber typePhone;
    @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId",
    onUpdate = ForeignKey.CASCADE,
    onDelete = ForeignKey.CASCADE)
    private int studentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TypePhoneNumber getTypePhone() {
        return typePhone;
    }

    public void setTypePhone(TypePhoneNumber typePhone) {
        this.typePhone = typePhone;
    }
}
