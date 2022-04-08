package com.example.aucademics.homePage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.aucademics.R;
import com.example.aucademics.bunkFragment.BunkItem;
import com.example.aucademics.databases.DepartmentDetailsAccess;
import com.example.aucademics.databases.bunkManagerDB.BunkManagerDBHelper;

import java.util.ArrayList;

public class EnterDetails extends AppCompatActivity {
    Spinner regulationGet;
    Spinner departmentGet;
    Spinner semesterGet;
    String userSemester;
    String userDepartment;
    String userRegulation;
    Button submitDetails;
    Button startBlank;
    SharedPreferences tokenSP;
    SharedPreferences.Editor tokenSPEditor;
    ArrayList<BunkItem> bunkItemArrayList;
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
        tokenSPEditor = tokenSP.edit();
        tokenSPEditor.putBoolean("token",false);

        boolean completedDetails = tokenSP.getBoolean("token",false);
        System.out.println("compDet: "+completedDetails);
//        if(completedDetails){
//            finish();
//            startActivity(new Intent(EnterDetails.this, bunkNcgpa.class));
//        }

        startBlank.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tokenSPEditor = tokenSP.edit();
                tokenSPEditor.putBoolean("token",true);
                tokenSPEditor.commit();
                startActivity(new Intent(EnterDetails.this, bunkNcgpa.class));
                finish();
            }
        });
        semesterGet = findViewById(R.id.s_semester);
        departmentGet = findViewById(R.id.s_department);
        regulationGet = findViewById(R.id.s_regulations);
        BunkManagerDBHelper db = new BunkManagerDBHelper(EnterDetails.this,"userBunkDB",null,1);
        db.open();
        submitDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userSemester = semesterGet.getSelectedItem().toString();
                userRegulation = regulationGet.getSelectedItem().toString();
                userDepartment = departmentGet.getSelectedItem().toString();
                tokenSPEditor = tokenSP.edit();
                tokenSPEditor.putBoolean("token",true);
                // get regulation, department and semester
                tokenSPEditor.putString("regulations",userRegulation);
                tokenSPEditor.putString("department",userDepartment);
                tokenSPEditor.putString("semester",userSemester);
                tokenSPEditor.commit();

                DepartmentDetailsAccess detailsAccess = DepartmentDetailsAccess.getInstance(getBaseContext());
                detailsAccess.open();
                bunkItemArrayList = detailsAccess.getSubjectsForBunk(userRegulation,userDepartment,userSemester);
                //detailsAccess.close();
                System.out.println("list: "+bunkItemArrayList);
                BunkManagerDBHelper db = new BunkManagerDBHelper(EnterDetails.this,"userBunkDB",null,1);
                db.upgrade();
                db.initialize(bunkItemArrayList);
                db.close();
                db.open();

                startActivity(new Intent(EnterDetails.this, bunkNcgpa.class));
                finish();
            }
        });
    }
}