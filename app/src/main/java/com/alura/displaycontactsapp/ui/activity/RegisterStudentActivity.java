package com.alura.displaycontactsapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alura.displaycontactsapp.R;
import com.alura.displaycontactsapp.model.Student;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        final TextInputEditText studentName = findViewById(R.id.activity_register_student_name_id);
        final TextInputEditText studentPhone = findViewById(R.id.activity_register_student_phone_number_id);
        final TextInputEditText studentEmail = findViewById(R.id.activity_register_student_email_id);

        View registerButton = findViewById(R.id.activity_register_student_save_button_id);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = studentName.getText().toString();
                String phone = studentPhone.getText().toString();
                String email = studentEmail.getText().toString();

                Student studentOne =  new Student(name, phone, email);
                Toast.makeText(RegisterStudentActivity.this,
                        studentOne.getName() + "-"
                                + studentOne.getPhone() + "-"
                                + studentOne.getEmail(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
