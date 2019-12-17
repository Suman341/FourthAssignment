package com.example.fourthassignment;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fourthassignment.R;
import com.example.fourthassignment.database.DbHelper;
import com.example.fourthassignment.model.Student;

import java.util.ArrayList;
import java.util.List;

public class AllStudentActivity extends AppCompatActivity {


    public static   RecyclerView recyclerview;
    List<Student> editstudent= new ArrayList<>();
    DbHelper dbHelper;
    RecyclerView.LayoutManager layoutManager;
    public static SearchStudentAdapter searchStudentAdapter;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student);

        recyclerview = findViewById(R.id.student_edit);
        btnHome = findViewById(R.id.btnHome);

        dbHelper = new DbHelper(this);
        editstudent = dbHelper.getStudents();



        searchStudentAdapter = new SearchStudentAdapter(editstudent,this);
        layoutManager= new LinearLayoutManager(this);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(searchStudentAdapter);




    }



}