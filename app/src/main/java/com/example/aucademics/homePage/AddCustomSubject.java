package com.example.aucademics.homePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
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
    //private EditText mCredits;
    private EditText mTotalHours;
    //private EditText mBunkedHours;
    private Button confirmCustomAdd;
    Toolbar tBar;
    //private Button cancelCustomAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_subject);
        mSubjectName = findViewById(R.id.ET_Custom_Subject);
        //mCredits = findViewById(R.id.ET_Custom_Credits);
        mTotalHours = findViewById(R.id.ET_Custom_T_Hours);
        confirmCustomAdd = findViewById(R.id.custom_sub_okay);
        tBar = findViewById(R.id.custom_sub_toolbar);
        tBar.setBackgroundColor(Color.parseColor("#00004d"));
        tBar.setNavigationIcon(getDrawable(R.drawable.ic_baseline_arrow_back_24));
        tBar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tBar.setTitle("ADD NEW SUBJECT");
        tBar.setTitleTextColor(Color.parseColor("#868113"));
        tBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();

                //What to do on back clicked
            }
        });


        confirmCustomAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subName = mSubjectName.getText().toString();
                String totalHours = mTotalHours.getText().toString();
                //String credits = mCredits.getText().toString();
                String regex = "[0-9]";
                if(subName.isEmpty()){
                    Toast.makeText(AddCustomSubject.this,"invalid subject name",Toast.LENGTH_SHORT).show();
                }
                else if(!totalHours.matches(regex)){
                    Toast.makeText(AddCustomSubject.this,"invalid total hours",Toast.LENGTH_SHORT).show();
                }

                else if(Integer.parseInt( totalHours)<30 && Integer.parseInt((totalHours))%15!=0){
                    Toast.makeText(AddCustomSubject.this,"hours must be >=30 and multiple of 15",Toast.LENGTH_SHORT).show();
                }

                else{
                BunkManagerDBHelper db = new BunkManagerDBHelper(AddCustomSubject.this);
                db.addSubject(new BunkItem(subName,totalHours));

                startActivity(new Intent(AddCustomSubject.this, bunkNcgpa.class));
                finish();
                }
            }
        });


    }
}