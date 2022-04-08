package com.example.aucademics.databases;

import android.content.Context;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DepartmentDetailsOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "department_details.db";
    private static final int DATABASE_VERSION=1;

    public DepartmentDetailsOpenHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
