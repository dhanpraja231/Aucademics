package com.example.aucademics.cgpaFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.example.aucademics.R;
import com.example.aucademics.databases.CGPA_DB.BigBadCGPATableDBHelper;
import com.example.aucademics.homePage.bunkNcgpa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SelectGpaActivity extends AppCompatActivity {
    RecyclerView selectGpaList;
    SharedPreferences sp;
    selectGpaRVAdapter rvAdapter;
    ArrayList<gpaItem> dataList;
    ArrayList<gpaItem> cbDataList;
    BigBadCGPATableDBHelper db;
    FloatingActionButton mConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gpa);
        selectGpaList = findViewById(R.id.rv_select_cgpa_list);
        sp = PreferenceManager.getDefaultSharedPreferences(
                this);
        Integer semesterRequested = sp.getInt("semesterClicked",0);
        if(semesterRequested==0){
            //System.out.println("semester not found");
            finish();
        }
        //System.out.println("semester received "+semesterRequested);
        db = new BigBadCGPATableDBHelper(this);
        dataList = db.getSubjectsOf(semesterRequested);
        //System.out.println("subjects received "+dataList);
        rvAdapter = new selectGpaRVAdapter(dataList,this);
        selectGpaList.setAdapter(rvAdapter);
        selectGpaList.setLayoutManager(new LinearLayoutManager(this));
        mConfirmButton = findViewById(R.id.select_gpa_confirm_button);

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbDataList = rvAdapter.getDataList();
                BigBadCGPATableDBHelper db = new BigBadCGPATableDBHelper(SelectGpaActivity.this);
                db.updateGradeValues(cbDataList);
                finish();
                Intent i = new Intent(SelectGpaActivity.this, bunkNcgpa.class);


                startActivity(i);
                finish();
            }
        });


    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(SelectGpaActivity.this, bunkNcgpa.class);
        startActivity(i);
        finish();

    }
}