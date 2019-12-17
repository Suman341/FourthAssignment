package com.example.fourthassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fourthassignment.R;

import com.example.fourthassignment.database.DbHelper;
import com.example.fourthassignment.model.Student;

public class CrudActivity extends AppCompatActivity {
    EditText editTextName,editTextEmail,editTextPhone;
    Button btnSave,btnallstudent;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_operation);


        editTextName = findViewById(R.id.stname);
        editTextEmail = findViewById(R.id.stemail);
        editTextPhone = findViewById(R.id.stphone);

        btnSave = findViewById(R.id.btnStSave);
        btnallstudent = findViewById(R.id.btnAllStudent);


        btnallstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CrudActivity.this, AllStudentActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DbHelper(this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = editTextName.getText().toString();
                String e = editTextEmail.getText().toString();
                String p = editTextPhone.getText().toString();

                Student student = new Student(0,n,e,p);

                if(dbHelper.addStudents(student)){
                    Toast.makeText(CrudActivity.this, "Successfully saved", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
