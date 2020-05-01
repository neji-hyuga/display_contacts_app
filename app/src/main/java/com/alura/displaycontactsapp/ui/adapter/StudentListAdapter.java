package com.alura.displaycontactsapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alura.displaycontactsapp.R;
import com.alura.displaycontactsapp.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends BaseAdapter {

    private final List<Student> students = new ArrayList<>();
    private final Context context;

    public StudentListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return students.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View createdView = createsView(viewGroup);
        Student studentSelected = students.get(i);
        joinsInformation(createdView, studentSelected);
        return createdView;
    }

    private void joinsInformation(View view, Student studentSelected) {
        TextView name = view.findViewById(R.id.item_student_name_id);
        TextView phone = view.findViewById(R.id.item_student_phone_id);
        name.setText(studentSelected.getName());
        phone.setText(studentSelected.getPhone());
    }

    private View createsView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_student, viewGroup, false);
    }

    public void remove(Student student) {
        students.remove(student);
        notifyDataSetChanged();
    }

    public void refresh(List<Student> students) {
        this.students.clear();
        this.students.addAll(students); // example to refresh list
        notifyDataSetChanged();

    }
}
