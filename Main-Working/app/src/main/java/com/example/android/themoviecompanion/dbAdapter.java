package com.example.android.themoviecompanion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbAdapter {
    // TODO 1 Define the functions of the database
    String DATABASE_NAME = "test";
    int DATABASE_Version = 1;


    public class dbHelper extends SQLiteOpenHelper {

        public dbHelper(Context context) {
            //TODO Implement
            super(context, DATABASE_NAME, null, DATABASE_Version);
//            this.context=context;
            Log.i("UPDATE","NULL");

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Implement onCreate
            Log.i("update","Database Created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Implement onCreate
            Log.i("UPDATE","Database updated");
        }
    }

}
