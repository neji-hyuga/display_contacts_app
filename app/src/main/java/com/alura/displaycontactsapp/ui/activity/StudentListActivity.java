package com.alura.displaycontactsapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.displaycontactsapp.R;
import com.alura.displaycontactsapp.dao.StudentDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentListActivity extends AppCompatActivity {

    // example of new DAO (data access object), class was created before
    private final StudentDAO dao = new StudentDAO();
    public static final String APPBAR_TITLE = "display contact app";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list); // layout xml reference

        // app bar text set example
        setTitle(APPBAR_TITLE);
        fabConfiguration();

    }

    private void fabConfiguration() {
        // example of FAB action, start new activity
        FloatingActionButton newStudentButton = findViewById(R.id.activity_student_list_fab_id);
        newStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensRegisterStudentActivity();
            }
        });
    }

    private void opensRegisterStudentActivity() {
        startActivity(new Intent(this, RegisterStudentActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        configureList();
    }

    // new adapter have been created
    private void configureList() {
        ListView studentList = findViewById(R.id.activity_student_list_list_view_id);
        studentList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.allStudents()));
    }
}
