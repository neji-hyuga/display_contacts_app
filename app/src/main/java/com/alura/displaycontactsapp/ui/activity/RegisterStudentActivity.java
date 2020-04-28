package com.alura.displaycontactsapp.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.alura.displaycontactsapp.R;
import com.alura.displaycontactsapp.dao.StudentDAO;
import com.alura.displaycontactsapp.model.Student;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterStudentActivity extends AppCompatActivity {

    public static final String APPBAR_TITLE = "new student ; )";
    private TextInputEditText studentName;
    private TextInputEditText studentPhone;
    private TextInputEditText studentEmail;
    private final StudentDAO dao = new StudentDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        setTitle(APPBAR_TITLE);
        initializesFields();
        buttonConfiguration();
    }

    private void buttonConfiguration() {
        View registerButton = findViewById(R.id.activity_register_student_save_button_id);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = createsStudent();
                savesStudent(student);
            }
        });
    }

    private void initializesFields() {
        studentName = findViewById(R.id.activity_register_student_name_id);
        studentPhone = findViewById(R.id.activity_register_student_phone_number_id);
        studentEmail = findViewById(R.id.activity_register_student_email_id);
    }

    private Student createsStudent() {
        String name = studentName.getText().toString();
        String phone = studentPhone.getText().toString();
        String email = studentEmail.getText().toString();

        return new Student(name, phone, email);
    }

    private void savesStudent(Student student) {
        dao.saveStudent(student);

        finish();
    }
}
