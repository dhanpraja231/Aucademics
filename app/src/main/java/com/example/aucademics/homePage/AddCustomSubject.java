package com.example.aucademics.homePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aucademics.R;
import com.example.aucademics.bunkFragment.BunkItem;
import com.example.aucademics.databases.bunkManagerDB.BunkManagerDBHelper;

public class AddCustomSubject extends AppCompatActivity {
    private EditText mSubjectName;
    private EditText mCredits;
    private EditText mTotalHours;
    private EditText mBunkedHours;
    private Button confirmCustomAdd;
    private Button cancelCustomAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_subject);
        mSubjectName = findViewById(R.id.ET_Custom_Subject);
        mCredits = findViewById(R.id.ET_Custom_Credits);
        mTotalHours = findViewById(R.id.ET_Custom_T_Hours);
        confirmCustomAdd = findViewById(R.id.custom_sub_okay);
        cancelCustomAdd = findViewById(R.id.custom_sub_cancel);



        confirmCustomAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subName = mSubjectName.getText().toString();
                String totalHours = mTotalHours.getText().toString();
                String credits = mCredits.getText().toString();
                if(Integer.parseInt( totalHours)<30 ||Integer.parseInt(credits)<2 ){
                    Toast.makeText(AddCustomSubject.this,"hours must be >=30 and 10>credits>2",Toast.LENGTH_SHORT).show();
                }
                else{
                BunkManagerDBHelper db = new BunkManagerDBHelper(AddCustomSubject.this);
                db.addSubject(new BunkItem(subName,totalHours));

                startActivity(new Intent(AddCustomSubject.this, bunkNcgpa.class));
                finish();
                }
            }
        });

        cancelCustomAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}