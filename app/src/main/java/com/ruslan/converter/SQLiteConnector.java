package com.ruslan.converter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteConnector extends SQLiteOpenHelper {

    public String LOG_TAG = "MY_LOG_TAG1";

    public SQLiteConnector(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table Valutes2022 (_id integer primary key, title text, kol integer, kurs float)");
            db.execSQL("create table Valutes2021 (_id integer primary key, title text, kol integer, kurs float)");
            db.execSQL("create table Valutes2020 (_id integer primary key, title text, kol integer, kurs float)");
            db.execSQL("create table Valutes2019 (_id integer primary key, title text, kol integer, kurs float)");
            db.execSQL("create table Valutes2018 (_id integer primary key, title text, kol integer, kurs float)");


        } catch (Exception e) {
            Log.d(LOG_TAG, "Exception creating table 'Valutes2022'");
            Log.d(LOG_TAG, "Exception creating table 'Valutes2021'");
            Log.d(LOG_TAG, "Exception creating table 'Valutes2020'");
            Log.d(LOG_TAG, "Exception creating table 'Valutes2019'");
            Log.d(LOG_TAG, "Exception creating table 'Valutes2018'");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

