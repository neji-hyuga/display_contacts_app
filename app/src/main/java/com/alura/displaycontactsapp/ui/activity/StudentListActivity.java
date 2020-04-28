package com.alura.displaycontactsapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.displaycontactsapp.R;
import com.alura.displaycontactsapp.dao.StudentDAO;
import com.alura.displaycontactsapp.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

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

        dao.saveStudent(new Student("Alex", "1122223333", "alex@alura.com.br"));
        dao.saveStudent(new Student("Fran", "1122223333", "fran@gmail.com"));

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
        final List<Student> students = dao.allStudents();
        studentList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                students));
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Student chosenStudent = students.get(i);
                Intent intent = new Intent(StudentListActivity.this, RegisterStudentActivity.class);
                intent.putExtra("student", chosenStudent);

                startActivity(intent);
            }
        });
    }
}
