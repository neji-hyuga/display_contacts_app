package com.alura.displaycontactsapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alura.displaycontactsapp.dao.StudentDAO;
import com.alura.displaycontactsapp.model.Student;
import com.alura.displaycontactsapp.ui.adapter.StudentListAdapter;

public class StudentListView {

    private final StudentListAdapter adapter;
    private final StudentDAO dao;
    private final Context context;

    public StudentListView(Context context) {
        this.context = context;
        this.adapter = new StudentListAdapter(this.context);
        dao = new StudentDAO();

    }

    public void checkDelete(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("dou you want to remove this contact?")
                .setMessage("for sure?")
                .setPositiveButton("yes", (dialogInterface, i) -> { // listener with lambda example
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student chosenStudent = adapter.getItem(menuInfo.position);
                    removeStudent(chosenStudent);
                })
                .setNegativeButton("no", null)
                .show();
    }

    public void refreshStudentList() {
        adapter.refresh(dao.allStudents());
    }

    private void removeStudent(Student student) {
        dao.deleteStudent(student);
        adapter.remove(student);
    }

    public void configuresAdapter(final ListView studentList) {
        studentList.setAdapter(adapter);
    }


}
