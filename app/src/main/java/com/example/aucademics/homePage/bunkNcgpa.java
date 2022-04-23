package com.example.aucademics.homePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aucademics.R;
import com.example.aucademics.bunkFragment.BunkItem;
import com.example.aucademics.databases.DepartmentResourceDatabase.DepartmentDetailsAccess;
import com.example.aucademics.databases.bunkManagerDB.BunkManagerDBHelper;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

//2)implement navbar
//3)create shared tables
//4)create FAQ page

public class bunkNcgpa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar navToolBar;
    SharedPreferences tokenSP;
    SharedPreferences.Editor tokenSPEditor;
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_Bar);
        navToolBar = findViewById(R.id.tool_bar);
        navToolBar.setBackgroundColor(Color.parseColor("#00004d"));
        navToolBar.setTitleTextColor(Color.parseColor("#00004d"));
        navigationView.bringToFront();
        setSupportActionBar(navToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        navToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, navToolBar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        tabLayout = findViewById(R.id.login_tab_layout);
        viewPager = findViewById(R.id.login_viewPager);
        tabLayout.setBackgroundColor(Color.parseColor("#00004d"));


        tabLayout.addTab(tabLayout.newTab().setText("Attendance"));
        tabLayout.addTab(tabLayout.newTab().setText("CGPA"));

        HomepageFragmentAdapter adapter = new HomepageFragmentAdapter(this);
        viewPager.setAdapter(adapter);
        String[] titles = new String[]{"Attendance","CGPA"};
        new TabLayoutMediator(tabLayout,viewPager,((tab,position) -> tab.setText(titles[position]))).attach();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.wipe_data:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("CONFIRM WIPE DATA");
                alert.setMessage("This will clear all subjects, subjects will not be recoverable");
                final CheckBox cBoxConfirm = new CheckBox(this);
                cBoxConfirm.setText("Confirm Action");
                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                cBoxConfirm.setText("Confirm Action");
                layout.addView(cBoxConfirm);
                layout.setPadding(50, 40, 50, 10);
                alert.setView(layout);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    if(cBoxConfirm.isChecked()){
                        tokenSP = PreferenceManager.getDefaultSharedPreferences(bunkNcgpa.this);
                        tokenSPEditor = tokenSP.edit();
                        tokenSPEditor.putBoolean("token",false);
                        tokenSPEditor.commit();
                        finish();
                        startActivity(new Intent(bunkNcgpa.this, EnterDetails.class));
                    }
                    else{
                        Toast.makeText(bunkNcgpa.this,"Check CONFIRM",Toast.LENGTH_SHORT).show();
                    }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                alert.show();
                break;
            case R.id.custom_subject:
                startActivity(new Intent(bunkNcgpa.this, AddCustomSubject.class));
                break;
            case R.id.prev_semester:
                AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
                alert2.setTitle("CONFIRM");
                alert2.setMessage("This will clear all current bunk data, data will not be recoverable");
                LinearLayout layout2 = new LinearLayout(this);
                layout2.setOrientation(LinearLayout.HORIZONTAL);
                CheckBox cBoxConfirm2 = new CheckBox(this);
                cBoxConfirm2.setText("Confirm Action");
                layout2.addView(cBoxConfirm2);
                layout2.setPadding(50, 40, 50, 10);
                alert2.setView(layout2);

                alert2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(cBoxConfirm2.isChecked()){
                            tokenSP = PreferenceManager.getDefaultSharedPreferences(bunkNcgpa.this);
                            ArrayList<BunkItem> newDataList;

                            String userRegulation = tokenSP.getString("regulations","none");
                            String userDepartment = tokenSP.getString("department","none");
                            String userSemTemp = tokenSP.getString("semester","2");
                            String userSemester = String.valueOf(Integer.parseInt(userSemTemp.substring(userSemTemp.length() - 1))-1);
                            if(Integer.parseInt(userSemester)>0) {
                                DepartmentDetailsAccess detailsAccess = DepartmentDetailsAccess.getInstance(getBaseContext());
                                detailsAccess.open();
                                newDataList = detailsAccess.getSubjectsForBunk(userRegulation, userDepartment, userSemester);
                                detailsAccess.close();

                                tokenSPEditor = tokenSP.edit();
                                tokenSPEditor.putString("semester",userSemester);
                                tokenSPEditor.commit();

                                BunkManagerDBHelper db = new BunkManagerDBHelper(bunkNcgpa.this);
                                db.upgrade();
                                db.initialize(newDataList);
                                db.close();

                                finish();
                                startActivity(new Intent(bunkNcgpa.this, bunkNcgpa.class));
                            }
                            else{
                                Toast.makeText(bunkNcgpa.this,"illegal semester data",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(bunkNcgpa.this,"Check CONFIRM",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert2.setNegativeButton("Cancel", (dialog, whichButton) -> {
                    // Canceled.
                });
                alert2.show();
                break;

            case R.id.next_semester:
                AlertDialog.Builder alert3 = new AlertDialog.Builder(this);
                alert3.setTitle("CONFIRM");
                alert3.setMessage("This will clear all current bunk data, data will not be recoverable");
                LinearLayout layout3 = new LinearLayout(this);
                layout3.setOrientation(LinearLayout.HORIZONTAL);
                CheckBox cBoxConfirm3 = new CheckBox(this);
                cBoxConfirm3.setText("Confirm Action");

                layout3.addView(cBoxConfirm3);
                layout3.setPadding(50, 40, 50, 10);
                alert3.setView(layout3);
                alert3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(cBoxConfirm3.isChecked()){
                            tokenSP = PreferenceManager.getDefaultSharedPreferences(bunkNcgpa.this);
                            ArrayList<BunkItem> newDataList;

                            String userRegulation = tokenSP.getString("regulations","none");
                            String userDepartment = tokenSP.getString("department","none");
                            String userSemTemp = tokenSP.getString("semester","2");
                            //System.out.println("user semTemp= " +userSemTemp.substring(userSemTemp.length() - 1));
                            String userSemester = String.valueOf(Integer.parseInt(userSemTemp.substring(userSemTemp.length() - 1))+1);
                            //System.out.println("user semester "+userSemester);
                            if(Integer.parseInt(userSemester)<=8) {
                                DepartmentDetailsAccess detailsAccess = DepartmentDetailsAccess.getInstance(getBaseContext());
                                detailsAccess.open();
                                newDataList = detailsAccess.getSubjectsForBunk(userRegulation, userDepartment, userSemester);
                                detailsAccess.close();

                                tokenSPEditor = tokenSP.edit();
                                String putString = "semester "+userSemester;
                                tokenSPEditor.putString("semester",putString);
                                tokenSPEditor.commit();

                                BunkManagerDBHelper db = new BunkManagerDBHelper(bunkNcgpa.this);
                                db.upgrade();
                                db.initialize(newDataList);
                                db.close();
                                finish();
                                startActivity(new Intent(bunkNcgpa.this, bunkNcgpa.class));
                            }
                            else{
                                Toast.makeText(bunkNcgpa.this,"illegal semester data",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(bunkNcgpa.this,"Check CONFIRM",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert3.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                alert3.show();
                break;

            case R.id.playstore_redirect:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.aucadevs.aucademics&reviewId=0"));
                startActivity(intent);
                //TODO: redirect to playstore app via explicit intent
                break;
            case R.id.help_nav:
                startActivity(new Intent(bunkNcgpa.this, HelpNFAQ.class));
                break;
    }
    return true;

}}