package com.example.aucademics;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class EnterDetails extends AppCompatActivity {
    Spinner regulationGet;
    Spinner departmentGet;
    Spinner semesterGet;
    Button submitDetails;
    Button startBlank;
    SharedPreferences tokenSP;
    SharedPreferences.Editor tokenSPEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_details);
        regulationGet = findViewById(R.id.s_regulations);
        departmentGet = findViewById(R.id.s_department);
        semesterGet = findViewById(R.id.s_semester);
        submitDetails = findViewById(R.id.submit_details);
        startBlank = findViewById(R.id.start_blank);
        tokenSP = PreferenceManager.getDefaultSharedPreferences(this);
        boolean completedDetails = tokenSP.getBoolean("token",false);
        if(completedDetails){
            finish();
            startActivity(new Intent(EnterDetails.this, BunkManager.class));
        }

        startBlank.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tokenSPEditor = tokenSP.edit();
                tokenSPEditor.putBoolean("token",true);
                tokenSPEditor.commit();
                startActivity(new Intent(EnterDetails.this, BunkManager.class));
                finish();
            }
        });

        submitDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // get regulation, department and semester
                //query regulation table
                //
            }
        });
    }
}