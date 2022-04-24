package com.example.aucademics.databases.DepartmentResourceDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DepartmentDetailsOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "department_details.db";
    private static final int DATABASE_VERSION=2;

    public DepartmentDetailsOpenHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }

}
