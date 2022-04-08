package com.example.aucademics.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;

import com.example.aucademics.bunkFragment.BunkItem;

import java.util.ArrayList;

public class DepartmentDetailsAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DepartmentDetailsAccess instance;


    private DepartmentDetailsAccess(Context context){
        this.openHelper = new DepartmentDetailsOpenHelper(context);
    }

    public static DepartmentDetailsAccess getInstance(Context context){
        if(instance== null){
            instance = new DepartmentDetailsAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }
    public void close(){
        if(db!= null){
            this.db.close();
        }
    }

    //initial table creation
    public ArrayList<BunkItem> getSubjectsForBunk(String userRegulations, String userDepartment, String userSemester) {
        int userSemester1 = Integer.parseInt(String.valueOf(userSemester.charAt(userSemester.length()-1)));
        Cursor c1 = db.rawQuery("select subject_name,no_of_hours from '"+userDepartment +"' where sem_offered" +
                " = '"+userSemester1 +"'",new String[]{});
        ArrayList<BunkItem> result = new ArrayList<>(10);
        while(c1.moveToNext()){
            result.add(new BunkItem(c1.getString(0),c1.getString(1)));
        }
        return result;
    }
}
