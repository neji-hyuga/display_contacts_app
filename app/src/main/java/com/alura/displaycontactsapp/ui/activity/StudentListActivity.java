package com.alura.displaycontactsapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.displaycontactsapp.R;
import com.alura.displaycontactsapp.dao.StudentDAO;
import com.alura.displaycontactsapp.model.Student;
import com.alura.displaycontactsapp.ui.adapter.StudentListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.content.ContentValues.TAG;
import static com.alura.displaycontactsapp.ui.activity.Constants.STUDENT_KEY;

public class StudentListActivity extends AppCompatActivity {

    // example of new DAO (data access object), class was created before
    private final StudentDAO dao = new StudentDAO();
    public static final String APPBAR_TITLE = "display contact app";
    private StudentListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list); // layout xml reference

        // app bar text set example
        setTitle(APPBAR_TITLE);
        fabConfiguration();
        configureList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_student_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        CharSequence menuTitle = item.getTitle();
        if (itemId == R.id.activity_student_list_menu_remove_id) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Student chosenStudent = adapter.getItem(menuInfo.position);
            removeStudent(chosenStudent);
        }
        return super.onContextItemSelected(item);
    }

    private void fabConfiguration() {
        // example of FAB action, start new activity
        FloatingActionButton newStudentButton = findViewById(R.id.activity_student_list_fab_id);
        newStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensRegisterStudent();
            }
        });
    }

    private void opensRegisterStudent() {
        startActivity(new Intent(this, RegisterStudentActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshStudentList();
    }

    private void refreshStudentList() {
        adapter.refresh(dao.allStudents());
    }

    // new adapter have been created
    private void configureList() {
        ListView studentList = findViewById(R.id.activity_student_list_list_view_id);
        configuresAdapter(studentList);
        configuresListenerClick(studentList);
        registerForContextMenu(studentList);
    }

    private void removeStudent(Student student) {
        dao.deleteStudent(student);
        adapter.remove(student);
    }

    private void configuresListenerClick(ListView studentList) {
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Student chosenStudent = (Student) adapterView.getItemAtPosition(i);
                Log.i(TAG, "editStudent: " + i);
                opensEditStudent(chosenStudent);
            }
        });
    }

    private void opensEditStudent(Student student) {
        Intent intent = new Intent(StudentListActivity.this, RegisterStudentActivity.class);
        intent.putExtra(STUDENT_KEY, student);
        startActivity(intent);
    }

    private void configuresAdapter(final ListView studentList) {
        adapter = new StudentListAdapter(this);
        studentList.setAdapter(adapter);
    }
}
