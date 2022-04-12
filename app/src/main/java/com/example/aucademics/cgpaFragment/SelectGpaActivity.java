package com.example.aucademics.cgpaFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.aucademics.R;
import com.example.aucademics.databases.CGPA_DB.BigBadCGPATableDBHelper;

import java.util.ArrayList;

public class SelectGpaActivity extends AppCompatActivity {
    RecyclerView selectGpaList;
    //TODO: make dedicated rv adapter and select subjects from big bad table
    SharedPreferences sp;
    selectGpaRVAdapter rvAdapter;
    ArrayList<gpaItem> dataList;
    BigBadCGPATableDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gpa);
        selectGpaList = findViewById(R.id.rv_select_cgpa_list);
        sp = PreferenceManager.getDefaultSharedPreferences(
                this);
        Integer semesterRequested = sp.getInt("semesterClicked",0);
        if(semesterRequested==0){
            System.out.println("semester not found");
            finish();
        }
        System.out.println("semester received "+semesterRequested);
        db = new BigBadCGPATableDBHelper(this);
        dataList = db.getSubjectsOf(semesterRequested);
        System.out.println("subjects received "+dataList);
        rvAdapter = new selectGpaRVAdapter(dataList);
        selectGpaList.setAdapter(rvAdapter);
        selectGpaList.setLayoutManager(new LinearLayoutManager(this));

//            this.mConfirmButton =itemView.findViewById(R.id.select_gpa_confirm_button);
//            mConfirmButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String selectedGpa = mSelectGpa.getSelectedItem().toString();
//                    Integer correspondingGP;
//                    System.out.println("selected gpa = "+selectedGpa);
//                    switch (selectedGpa){
//                        case "O":
//                            correspondingGP = 10;
//                            break;
//                        case "A+":
//                            correspondingGP = 9;
//                            break;
//                        case "A":
//                            correspondingGP = 8;
//                            break;
//                        case "B+":
//                            correspondingGP = 7;
//                            break;
//                        case "B":
//                            correspondingGP = 6;
//                            break;
//                        default:
//                            correspondingGP = 0;
//                    }
//
//                    //TODO: update big bad table and main cgpa
//                }
//            });

    }
}