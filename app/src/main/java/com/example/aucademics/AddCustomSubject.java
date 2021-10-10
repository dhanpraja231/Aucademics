package com.example.aucademics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCustomSubject extends AppCompatActivity {
    private EditText subjectName;
    private EditText credits;
    private EditText totalHours;
    private EditText bunkedHours;
    private Button confirmCustomAdd;
    private Button cancelCustomAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_subject);
        subjectName = findViewById(R.id.ET_Custom_Subject);
        credits = findViewById(R.id.ET_Custom_Credits);
        totalHours = findViewById(R.id.ET_Custom_T_Hours);
        bunkedHours = findViewById(R.id.ET_Custom_B_Hours);
        confirmCustomAdd = findViewById(R.id.custom_sub_okay);
        cancelCustomAdd = findViewById(R.id.custom_sub_cancel);

        cancelCustomAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(AddCustomSubject.this,BunkManager.class));
            }
        });
    }
}