package com.example.fourthassignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fourthassignment.R;

import java.util.ArrayList;
import java.util.Collections;

public class WordGameActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] words = {"management", "science", "math", "material", "speaker", "kollywood"};
    private EditText editText;
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    TextView textView;
    private int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);
        textView =findViewById(R.id.lText);
        Button btnOK = findViewById(R.id.btnOK);
        Button btnClear = findViewById(R.id.btnClr);
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);


        SharedPreferences savedata = getSharedPreferences("Game", Context.MODE_PRIVATE);
        if (savedata.getInt("Level",0)==0) {
            showWord(level);
        }
        else {
            level=savedata.getInt("Level",0);
            showWord(level);
        }


        btnOK.setOnClickListener(this);
        btnClear.setOnClickListener(this);


    }

    private Character[] shuffleWords(int level){
        char[] word = words[level].toCharArray();


        ArrayList<Character> chars = new ArrayList<>(word.length);
        for(char c: word){
            chars.add(c);
        }

        Collections.shuffle(chars);
        Character[] shuffledWord = new Character[chars.size()];

        for(int i=0; i<shuffledWord.length; i++ ){
            shuffledWord[i] = chars.get(i);
        }
        return shuffledWord;
    }

    private void showWord(int i){
        CharacterAdapter charactersAdapter = new CharacterAdapter(WordGameActivity.this, shuffleWords(i),editText);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setAdapter(charactersAdapter);
        recyclerView.setLayoutManager(layoutManager);
        textView.setText("GAME LEVEL : " + (i+1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOK:
                String usr_word = editText.getText().toString();
                if(level<words.length) {
                    if (usr_word.equals(words[level])) {
                        level++;
                        showWord(level);
                        sharedPreferences = getSharedPreferences("Game",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("Level",level);
                        editor.commit();
                        editText.setText("");
                        Toast.makeText(WordGameActivity.this, "Next Level", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(WordGameActivity.this, "Wrong Word", Toast.LENGTH_SHORT).show();
                        editText.setText("");
                        showWord(level);
                    }
                }

                else {
                    level=0;
                    Toast.makeText(WordGameActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnClr:
                editText.getText().clear();
                showWord(level);
                break;
        }
    }
}
