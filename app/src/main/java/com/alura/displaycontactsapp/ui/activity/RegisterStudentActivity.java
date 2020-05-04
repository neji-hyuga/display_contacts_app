package com.alura.displaycontactsapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.displaycontactsapp.R;
import com.alura.displaycontactsapp.dao.StudentDAO;
import com.alura.displaycontactsapp.model.Student;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import static com.alura.displaycontactsapp.ui.activity.Constants.STUDENT_KEY;

public class RegisterStudentActivity extends AppCompatActivity {

    private static final String APPBAR_TITLE_NEW_STUDENT = "new student ; )";
    private static final String APPBAR_TITLE_EDIT_STUDENT = "edit student info";
    private TextInputEditText studentName;
    private TextInputEditText studentPhone;
    private TextInputEditText studentEmail;
    private final StudentDAO dao = new StudentDAO();
    private Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        initializesFields();
        loadStudentInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_student_register_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_student_register_menu_save_id) {
            endsForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadStudentInfo() {
        Intent intent = getIntent();
        if (intent.hasExtra(STUDENT_KEY)) {
            setTitle(APPBAR_TITLE_EDIT_STUDENT);
            student = (Student) intent.getSerializableExtra(STUDENT_KEY);
            fillsFields();
        } else {
            setTitle(APPBAR_TITLE_NEW_STUDENT);
            student = new Student();
        }
    }

    private void fillsFields() {
        studentName.setText(student.getName());
        studentPhone.setText(student.getPhone());
        studentEmail.setText(student.getEmail());
    }

    private void endsForm() {
        completesStudentForm();
        if (student.hasValidId()) {
            dao.editStudent(student);
        } else {
            dao.saveStudent(student);
        }
        finish();
    }

    private void initializesFields() {
        studentName = findViewById(R.id.activity_register_student_name_id);
        studentPhone = findViewById(R.id.activity_register_student_phone_number_id);
        studentEmail = findViewById(R.id.activity_register_student_email_id);
    }

    private void completesStudentForm() {
        String name = Objects.requireNonNull(studentName.getText()).toString();
        String phone = Objects.requireNonNull(studentPhone.getText()).toString();
        String email = Objects.requireNonNull(studentEmail.getText()).toString();

        student.setName(name);
        student.setPhone(phone);
        student.setEmail(email);
    }

}
