package com.example.fourthassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fourthassignment.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCRUD,buttonWordGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCRUD = findViewById(R.id.btnCrud);
        buttonWordGame = findViewById(R.id.btnGame);

        buttonWordGame.setOnClickListener(this);
        buttonCRUD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.btnCrud)
        {
            Intent intent = new Intent(this,CrudActivity.class);
            startActivity(intent);
        }

        if (view.getId()== R.id.btnGame)
        {
            Intent intent = new Intent(this,WordGameActivity.class);
            startActivity(intent);
        }
    }
}
