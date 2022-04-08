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
    public void incrementSubject(){
        //TODO: BM:implmemnt increment by update
        return;
    }
    public void editSubject(){
        //TODO: BM:implmemnt edit Subject by update
        return;
    }
    public void clearSubject(){
        //TODO: BM:implmemnt clearSubject by delete
        return;
    }
    public ArrayList<BunkItem> getAllSubjects(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+BunkEntries.TABLE_NAME;
        Cursor c = db.rawQuery(query,null);
        ArrayList<BunkItem> result = new ArrayList<>();
        do {
            result.add(new BunkItem(c.getString(1),c.getString(2),c.getString(3)));

        }while(c.moveToNext());
        return result;
    }

    public void open(){
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public void close(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
    }



}
