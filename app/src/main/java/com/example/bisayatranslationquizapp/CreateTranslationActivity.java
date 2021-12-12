package com.example.bisayatranslationquizapp;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CreateTranslationActivity  extends AppCompatActivity {
    private EditText editText_word;
    private EditText editText_option1;
    private EditText editText_option2;
    private EditText editText_option3;

    private Spinner spinner_create_category;
    private Spinner spinner_create_difficulty;
    private Spinner spinner_correct_answer;

    private Button button_create;
    private Button button_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_translation);

        spinner_create_category = findViewById(R.id.spinner_create_category);
        spinner_create_difficulty = findViewById(R.id.spinner_create_difficulty);
        spinner_correct_answer = findViewById(R.id.spinner_correct);

        editText_word = findViewById(R.id.editText_question);
        editText_option1 = findViewById(R.id.editText_word1);
        editText_option2 = findViewById(R.id.editText_word2);
        editText_option3 = findViewById(R.id.editText_word3);

        loadCategories();
        loadDifficultyLevels();
        loadCorrectAnswer();

        button_create = findViewById(R.id.button_create);
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitEntry();
            }
        });

        button_back = findViewById(R.id.button_back);
    }

    private void submitEntry(){
        String question = editText_word.getText().toString();
        String option1 = editText_option1.getText().toString();
        String option2 = editText_option2.getText().toString();
        String option3 = editText_option1.getText().toString();

        Toast.makeText(CreateTranslationActivity.this, "Word: "+question+"\n"+
                "Option 1: "+option1+"\n"+
                "Option 2: "+option2+"\n"+
                "Option 3: "+option3, Toast.LENGTH_SHORT).show();
    }

    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_create_category.setAdapter(adapterCategories);
    }
    private void loadDifficultyLevels() {
        String[] difficultyLevels = Question.getAllDifficultyLevels();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_create_difficulty.setAdapter(adapterDifficulty);
    }
    private void loadCorrectAnswer() {
        Integer[] correctAnswer = Question.getCorrectAnswer();

        ArrayAdapter <Integer> dataAdapter = new ArrayAdapter<Integer>( this,
                android.R.layout.simple_spinner_item,correctAnswer);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_correct_answer.setAdapter(dataAdapter);
    }

}
