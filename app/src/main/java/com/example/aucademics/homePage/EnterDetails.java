package com.example.aucademics.homePage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aucademics.R;
import com.example.aucademics.bunkFragment.BunkItem;
import com.example.aucademics.cgpaFragment.gpaItem;
import com.example.aucademics.databases.CGPA_DB.BigBadCGPATableDBHelper;
import com.example.aucademics.databases.DepartmentResourceDatabase.DepartmentDetailsAccess;
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
    ArrayList<gpaItem> gpaItemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_details);
        findViewById(R.id.enterDetailsLoadingPanel).setVisibility(View.GONE);
        regulationGet = findViewById(R.id.s_regulations);
        departmentGet = findViewById(R.id.s_department);
        semesterGet = findViewById(R.id.s_semester);
        submitDetails = findViewById(R.id.submit_details);
        //startBlank = findViewById(R.id.start_blank);
        tokenSP = PreferenceManager.getDefaultSharedPreferences(this);
        tokenSPEditor = tokenSP.edit();
        tokenSPEditor.putBoolean("token",false);

        semesterGet = findViewById(R.id.s_semester);
        departmentGet = findViewById(R.id.s_department);
        regulationGet = findViewById(R.id.s_regulations);
        BunkManagerDBHelper db = new BunkManagerDBHelper(EnterDetails.this,"userBunkDB",null,1);
        db.close();
        boolean completedDetails = tokenSP.getBoolean("token",false);
        //System.out.println("compDet: "+completedDetails);

        AlertDialog.Builder alert3 = new AlertDialog.Builder(this);
        alert3.setTitle("T&C");
        alert3.setMessage("The Aucademics team is not responsible or liable for any loss suffered by the user due to the usage of this app.\n" +
                "However we promise that the functionality of the app is refined to the best of our knowledge.\n" +
                "Aucademics team is not affiliated with Anna University ");


        alert3.setPositiveButton("ACKNOWLEDGE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do nothing
            }
        });

        alert3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        alert3.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                finish();
            }
        });
        if(!completedDetails){
        alert3.show();}
        if(completedDetails){
            finish();
            startActivity(new Intent(EnterDetails.this, bunkNcgpa.class));
        }


        submitDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                findViewById(R.id.enterDetailsLoadingPanel).setVisibility(View.VISIBLE);
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
                detailsAccess.refresh(EnterDetails.this);

                //do it here
                detailsAccess.open();
                bunkItemArrayList = detailsAccess.getSubjectsForBunk(userRegulation,userDepartment,userSemester);
                gpaItemArrayList = detailsAccess.getSubjectsForBigBadTable(userRegulation,userDepartment,userSemester);
                detailsAccess.close();
                //System.out.println("list: "+bunkItemArrayList);
                BunkManagerDBHelper db = new BunkManagerDBHelper(EnterDetails.this,"userBunkDB",null,1);
                db.upgrade();
                db.initialize(bunkItemArrayList);
                db.close();
                BigBadCGPATableDBHelper db2 = new BigBadCGPATableDBHelper(EnterDetails.this);
                //System.out.println("gpa item array list in enter details: "+ gpaItemArrayList);
                db2.deleteTable();
                db2.initialize(gpaItemArrayList);
                db2.close();

                //db.open();
                findViewById(R.id.enterDetailsLoadingPanel).setVisibility(View.GONE);
                startActivity(new Intent(EnterDetails.this, bunkNcgpa.class));
                finish();
            }
        });

    }

}