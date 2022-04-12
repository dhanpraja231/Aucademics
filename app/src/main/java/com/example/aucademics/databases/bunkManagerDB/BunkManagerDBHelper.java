package com.example.aucademics.databases.bunkManagerDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import com.example.aucademics.bunkFragment.BunkEntries;
import com.example.aucademics.bunkFragment.BunkItem;

import java.util.ArrayList;

public class BunkManagerDBHelper extends SQLiteOpenHelper {
    public BunkManagerDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public BunkManagerDBHelper(@Nullable Context context, int version) {
        super(context, "userBunkDB", null, version);
    }

    public BunkManagerDBHelper(Context context) {
        super(context, "userBunkDB", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String tableString = "CREATE TABLE "+
                BunkEntries.TABLE_NAME +" (" +
                BunkEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BunkEntries.COLUMN_SUBJECT + " TEXT NOT NULL, "+
                BunkEntries.COLUMN_TOTAL_HOURS + " INTEGER NOT NULL, "+
                BunkEntries.COLUMN_BUNKED_HOURS +" INTEGER NOT NULL, "+
                BunkEntries.COLUMN_BUNKS_LEFT + " INTEGER NOT NULL, "+
                BunkEntries.COLUMN_ATTENDANCE_PERCENT + " TEXT NOT NULL"+
                ");";
        db.execSQL(tableString);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BunkEntries.TABLE_NAME);
        onCreate(db);
    }

    public void initialize(ArrayList<BunkItem> ar){
        SQLiteDatabase db = this.getWritableDatabase();
        for(BunkItem i: ar){
            ContentValues cv = new ContentValues();
            cv.put(BunkEntries.COLUMN_SUBJECT,i.getSubject_name());
            cv.put(BunkEntries.COLUMN_TOTAL_HOURS,i.getTotalHours());
            cv.put(BunkEntries.COLUMN_BUNKED_HOURS,i.getBunkedHours());
            cv.put(BunkEntries.COLUMN_BUNKS_LEFT,i.getBunkHoursLeft());
            cv.put(BunkEntries.COLUMN_ATTENDANCE_PERCENT,String.valueOf(i.getAttendancePercent()));
            db.insert(BunkEntries.TABLE_NAME,null,cv);

        }
        db.close();
    }
    public void addSubject(BunkItem i){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BunkEntries.COLUMN_SUBJECT,i.getSubject_name());
        cv.put(BunkEntries.COLUMN_TOTAL_HOURS,i.getTotalHours());
        cv.put(BunkEntries.COLUMN_BUNKED_HOURS,i.getBunkedHours());
        cv.put(BunkEntries.COLUMN_BUNKS_LEFT,i.getBunkHoursLeft());
        cv.put(BunkEntries.COLUMN_ATTENDANCE_PERCENT,i.getAttendancePercent());
        db.insert(BunkEntries.TABLE_NAME,null,cv);

        db.close();
    }
    public void incrementSubject(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        id++;
        Cursor c = db.rawQuery("SELECT "+BunkEntries.COLUMN_TOTAL_HOURS+", "+BunkEntries.COLUMN_BUNKED_HOURS+", "+BunkEntries.COLUMN_BUNKS_LEFT+ " FROM "+BunkEntries.TABLE_NAME+ " WHERE _id = "+"'"+String.valueOf(id)+"'",null);
        c.moveToNext();
        Integer oldTotalHours =Integer.parseInt(c.getString(0));
        Integer oldBunkCount =Integer.parseInt(c.getString(1));
        Integer oldBunksLeft =Integer.parseInt(c.getString(2));
        ContentValues cv = new ContentValues();
        cv.put("Bunked_hours",oldBunkCount+1);
        cv.put("Bunks_left",oldBunksLeft-1);
        float attendancePercent = (float)((oldTotalHours-oldBunkCount)/(float)(oldTotalHours))*100.0f;
        cv.put("Attendance_percent",attendancePercent);
        db.update(BunkEntries.TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(id)});
        c.close();
        db.close();
        return;
    }
    public void decrementSubject(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        id++;
        Cursor c = db.rawQuery("SELECT "+BunkEntries.COLUMN_TOTAL_HOURS+", "+BunkEntries.COLUMN_BUNKED_HOURS+", "+BunkEntries.COLUMN_BUNKS_LEFT+ " FROM "+BunkEntries.TABLE_NAME+ " WHERE _id = "+"'"+id+"'",null);
        c.moveToNext();
        Integer oldTotalHours =Integer.parseInt(c.getString(0));
        Integer oldBunkCount =Integer.parseInt(c.getString(1));
        Integer oldBunksLeft =Integer.parseInt(c.getString(2));
        c.close();
        if(oldBunkCount-1 >= 0) {
            ContentValues cv = new ContentValues();
            cv.put("Bunked_hours", oldBunkCount - 1);
            cv.put("Bunks_left", oldBunksLeft + 1);
            float attendancePercent = (float) ((oldTotalHours - oldBunkCount) / (float) (oldTotalHours)) * 100.0f;
            cv.put("Attendance_percent", attendancePercent);
            db.update(BunkEntries.TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(id)});
            return;
        }
        db.close();
    }

    public ArrayList<BunkItem> getAllSubjects(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+BunkEntries.TABLE_NAME;
        Cursor c = db.rawQuery(query,null);
        ArrayList<BunkItem> result = new ArrayList<>();
        while(c.moveToNext()){
            result.add(new BunkItem(c.getString(1),c.getString(2),c.getString(3)));
        }
        System.out.println("GATE "+result);
        db.close();
        c.close();
        return result;
    }

    public void open(){
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public void close(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
    }

    public void upgrade(){
        onUpgrade(this.getReadableDatabase(),1,1);
    }



}
