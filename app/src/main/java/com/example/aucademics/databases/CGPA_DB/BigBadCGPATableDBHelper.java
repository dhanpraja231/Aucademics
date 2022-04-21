package com.example.aucademics.databases.CGPA_DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.aucademics.bunkFragment.BunkEntries;
import com.example.aucademics.bunkFragment.BunkItem;
import com.example.aucademics.cgpaFragment.BigBadCgpaEntries;
import com.example.aucademics.cgpaFragment.gpaItem;

import java.security.SecureRandomSpi;
import java.util.ArrayList;

public class BigBadCGPATableDBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getWritableDatabase();

    public BigBadCGPATableDBHelper(@Nullable Context context) {
        super(context, "bigBadCGPATable", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String tableString = "CREATE TABLE "+
                BigBadCgpaEntries.TABLE_NAME +" (" +
                BigBadCgpaEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BigBadCgpaEntries.COLUMN_SUBJECT + " TEXT NOT NULL, "+
                BigBadCgpaEntries.COLUMN_SUBJECT_CODE + " TEXT NOT NULL, "+
                BigBadCgpaEntries.COLUMN_CREDITS +" INTEGER NOT NULL, "+
                BigBadCgpaEntries.COLUMN_GRADE + " INTEGER, "+
                BigBadCgpaEntries.COLUMN_SEMESTER + " INTEGER NOT NULL"+
                ");";
        db.execSQL(tableString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + BigBadCgpaEntries.TABLE_NAME);
        onCreate(db);
    }

    public void initialize(ArrayList<gpaItem> ar){
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("data list in big bad helper class "+ ar);
        for(gpaItem i: ar){
            ContentValues cv = new ContentValues();
            cv.put(BigBadCgpaEntries.COLUMN_SUBJECT,i.getSubjectName());
            cv.put(BigBadCgpaEntries.COLUMN_SUBJECT_CODE,i.getSubjectCode());
            cv.put(BigBadCgpaEntries.COLUMN_CREDITS,i.getCredits());
            cv.put(BigBadCgpaEntries.COLUMN_GRADE,i.getGradeAchieved());
            cv.put(BigBadCgpaEntries.COLUMN_SEMESTER,String.valueOf(i.getSemOffered()));
            db.insert(BigBadCgpaEntries.TABLE_NAME,null,cv);
        }
        db.close();
    }

    public ArrayList<gpaItem> getSubjectsOf(Integer semester){

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<gpaItem> result = new ArrayList<>();

        String query = "SELECT * FROM "+BigBadCgpaEntries.TABLE_NAME+" WHERE "+ BigBadCgpaEntries.COLUMN_SEMESTER+" = "+semester;
        Cursor c = db.rawQuery(query,null);
        while(c.moveToNext()){
            if(c.getString(4)!=null){
            result.add(new gpaItem(Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),Integer.parseInt(c.getString(3)),Integer.parseInt(c.getString(4)),Integer.parseInt(c.getString(5))));}
            else{

                result.add(new gpaItem(Integer.parseInt(c.getString(0)), c.getString(1),c.getString(2),Integer.parseInt(c.getString(3)),Integer.parseInt(c.getString(5))));
            }
        }
        System.out.println("result set from get semester data:  "+result);

        db.close();
        c.close();

        return result;
    }

    public void deleteTable(){
        db.execSQL("DROP TABLE IF EXISTS " + BigBadCgpaEntries.TABLE_NAME);
        onCreate(db);
        db.close();
    }


    public void updateGradeValues(ArrayList<gpaItem> dataList) {
        SQLiteDatabase db = this.getWritableDatabase();

        for(gpaItem item:dataList){
            ContentValues cv = new ContentValues();
            cv.put(BigBadCgpaEntries.COLUMN_GRADE, item.getGradeAchieved());
            db.update(BigBadCgpaEntries.TABLE_NAME,cv, "_id=?",new String[]{String.valueOf(item.getId())});
            //db.update(BunkEntries.TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(id)});
        }


    }

    public String getGradeOfid(Integer id) {
        String query = "SELECT grade FROM "+BigBadCgpaEntries.TABLE_NAME+" WHERE "+ BigBadCgpaEntries._ID+" = "+id;
        Cursor c = db.rawQuery(query,null);
        c.moveToNext();
        String s= c.getString(0);
        c.close();
        return s;
    }

    public Double[] calculateGpaOfSemester(Integer semester){
        Double[] mResult = new Double[2];
        String query = "SELECT grade,credits FROM "+BigBadCgpaEntries.TABLE_NAME+" WHERE "+ BigBadCgpaEntries.COLUMN_SEMESTER+" = "+ semester;
        Cursor c = db.rawQuery(query,null);
        Integer cumulativeGrade=0;
        Integer cumulativeCredits=0;

        Integer currentGrade;
        Integer currentCredits;
        while(c.moveToNext()){
            if(c.getString(0)!=null){
            currentGrade = Integer.parseInt(c.getString(0));}
            else {
                currentGrade = null;
            }

            currentCredits = Integer.parseInt(c.getString(1));
            if(currentGrade!=null){
                if( currentGrade!=0){
                    cumulativeGrade += (currentGrade*currentCredits);
                    cumulativeCredits += currentCredits;
                }
            }
        }

        if(cumulativeCredits==0){
            mResult[1] = 0.0;
            return mResult;
        }
        double result = (double)cumulativeGrade/(double)cumulativeCredits;
        System.out.println("sem "+semester+" gpa ="+ result);
        mResult[0] = result;
        mResult[1] = Double.parseDouble(String.valueOf(cumulativeCredits));

        return mResult;

    }
}
