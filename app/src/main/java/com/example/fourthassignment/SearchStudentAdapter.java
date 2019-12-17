package com.example.fourthassignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fourthassignment.R;
import com.example.fourthassignment.database.DbHelper;
import com.example.fourthassignment.model.Student;

import java.util.ArrayList;
import java.util.List;

public class SearchStudentAdapter extends RecyclerView.Adapter<SearchStudentAdapter.MyStudentHolder>  {

    List<Student> mystudentlist = new ArrayList<>();
    Context context;
    DbHelper dbHelper;
    SearchStudentAdapter.MyStudentHolder myStudentHolder;

    public SearchStudentAdapter(List<Student> mystudentlist, Context context) {
        this.mystudentlist = mystudentlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyStudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.studentlayout,parent,false);
        myStudentHolder = new SearchStudentAdapter.MyStudentHolder(view);
        return myStudentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyStudentHolder holder, final int position) {
        final  Student student = mystudentlist.get(position);

        holder.studentname.setText(student.getName());
        holder.studentemail.setText(student.getEmail());
        holder.studentphone.setText(student.getPhone());

        holder.studentedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStu(student.getId());

            }
        });




        holder.studentdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteStu(student.getId(),position);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return mystudentlist.size();
    }

    public class MyStudentHolder extends RecyclerView.ViewHolder{

        TextView studentname,studentemail,studentphone;
        Button studentdelete,studentedit;
        public MyStudentHolder(@NonNull View itemView) {
            super(itemView);
            studentname = itemView.findViewById(R.id.name);
            studentemail=itemView.findViewById(R.id.email);
            studentphone=itemView.findViewById(R.id.phone);
            studentdelete=itemView.findViewById(R.id.deletestudent);
            studentedit=itemView.findViewById(R.id.editstudent);

        }
    }


    private void deleteStu(int deleteid,int positionnum) {
        DbHelper db = new DbHelper(context);

        if(db.deleteData(deleteid)){
            mystudentlist.remove(positionnum);
            notifyDataSetChanged();
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();

        }


    }


    private void updateStu(int id){
        Intent update = new Intent(context, UpdateActivity.class);
        update.putExtra("Student_ID", id);
        context.startActivity(update);
    }




}