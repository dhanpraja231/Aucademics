package com.example.aucademics;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

//2)implement navbar
//3)create shared tables
//4)create FAQ page

public class BunkManager extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar navToolBar;
    SharedPreferences tokenSP;
    SharedPreferences.Editor tokenSPEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bunk_manager);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_Bar);
        navToolBar = findViewById(R.id.tool_bar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, navToolBar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
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

                alert.setView(cBoxConfirm);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    if(cBoxConfirm.isChecked()){
                        tokenSP = PreferenceManager.getDefaultSharedPreferences(BunkManager.this);
                        tokenSPEditor = tokenSP.edit();
                        tokenSPEditor.putBoolean("token",false);
                        tokenSPEditor.commit();
                        //TODO clear SQLlite
                        finish();
                        startActivity(new Intent(BunkManager.this,EnterDetails.class));
                    }
                    else{
                        Toast.makeText(BunkManager.this,"Check CONFIRM",Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(BunkManager.this,AddCustomSubject.class));
                //show activity
                //Enter subject name, credits, hours
                //press confirm -> add to private SQLlite and display in recycler view
                break;
            case R.id.clear_subject:
                //pop up
                //display subject names from SQLlite with dropdown list/spinner
                //select subject, click confirm checkbox and enter
                //remove SQLlite data row
                //reinstate display loop for recycler view
                break;
            case R.id.playstore_redirect:
                //redirect to playstore app via explicit intent
                break;
            case R.id.help_nav:
                //intent to FAQ page via explicit intent
                break;
    }
    return true;

}}