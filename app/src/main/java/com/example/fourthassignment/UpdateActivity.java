package com.example.fourthassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fourthassignment.R;

import com.example.fourthassignment.database.DbHelper;
import com.example.fourthassignment.model.Student;

public class UpdateActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPhoneEditText;
    private Button mUpdateBtn;

    private DbHelper dbHelper;
    private int receivedStudentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        mNameEditText = findViewById(R.id.studentNameUpdate);
        mEmailEditText = findViewById(R.id.studentEmailUpdate);
        mPhoneEditText = findViewById(R.id.studentPhoneUpdate);
        mUpdateBtn = findViewById(R.id.updateStudentButton);

        dbHelper = new DbHelper(this);

        try {

            receivedStudentId = getIntent().getIntExtra("Student_ID", 1);
            Log.d("id",String.valueOf(receivedStudentId));


        } catch (Exception e) {
            e.printStackTrace();
        }

        Student queriedStudent = dbHelper.getStudentById(receivedStudentId);

        mNameEditText.setText(queriedStudent.getName());
        mEmailEditText.setText(queriedStudent.getEmail());
        mPhoneEditText.setText(queriedStudent.getPhone());
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateStudent();
            }
        });





    }

    private void updateStudent(){
        String name = mNameEditText.getText().toString().trim();
        String email = mEmailEditText.getText().toString().trim();
        String phone = mPhoneEditText.getText().toString().trim();


        if(name.isEmpty()){
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }

        if(email.isEmpty()){
            Toast.makeText(this, "You must enter an email", Toast.LENGTH_SHORT).show();
        }

        if(phone.isEmpty()){
            Toast.makeText(this, "You must enter phone number", Toast.LENGTH_SHORT).show();
        }



        Student updated = new Student(receivedStudentId,name,email,phone);

        if(dbHelper.updatStudent(updated)){
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }


        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(this, AllStudentActivity.class));
    }

}
