package com.example.aucademics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BunkManagerDBHelper extends SQLiteOpenHelper {
    public BunkManagerDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String tableString = "CREATE TABLE "+
                BunkEntries.TABLE_NAME +" (" +
                BunkEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BunkEntries.COLUMN_SUBJECT + " TEXT NOT NULL, "+
                BunkEntries.COLUMN_CREDITS + "INTEGER NOT NULL, "+
                BunkEntries.COLUMN__TOTAL_HOURS + " INTEGER NOT NULL, "+
                BunkEntries.COLUMN_BUNKED_HOURS +" INTEGER NOT NULL, "+
                BunkEntries.COLUMN_BUNKS_LEFT + "INTEGER NOT NULL, "+
                BunkEntries.COLUMN_ATTENDANCE_PERCENT + "FLOAT NOT NULL"+
                ");";
        db.execSQL(tableString);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BunkEntries.TABLE_NAME);
        onCreate(db);
    }
}
