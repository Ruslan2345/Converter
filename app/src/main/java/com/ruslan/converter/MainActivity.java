package com.ruslan.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tvkurs;
    EditText Input, Output;
    Button btnRead, btnCreate, btnConv;

    SQLiteDatabase db;
    SQLiteConnector connector;
    Cursor result, result1, result2, result3, result4;

    String s1 = "Курс: 2022", s2 = "USD", s3 = "USD", s4, s5, s6, s7;


    public String LOG_TAG = "MY_LOG_TAG1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        tv1.setOnCreateContextMenuListener(this);

        tv2 = findViewById(R.id.tv2);
        tv2.setOnCreateContextMenuListener(this);

        tvkurs = findViewById(R.id.tvkurs);
        tvkurs.setOnCreateContextMenuListener(this);

        Input = findViewById(R.id.Input);
        Output = findViewById(R.id.Output);

        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        btnConv = findViewById(R.id.btnConv);
        btnConv.setOnClickListener(this);

    }

    private class SQLiteConnector extends SQLiteOpenHelper
    {
        public SQLiteConnector(Context context, String name, int version)
        {
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
            }
            catch (Exception e) {
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.tv1)
            getMenuInflater().inflate(R.menu.context1, menu);
        if(v.getId() == R.id.tv2)
            getMenuInflater().inflate(R.menu.context2, menu);
        if(v.getId() == R.id.tvkurs)
            getMenuInflater().inflate(R.menu.context3, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context3item1:
                tvkurs.setText(R.string.kurs1);
                s1 = "Курс: 2022";
                break;
            case R.id.context3item2:
                tvkurs.setText(R.string.kurs2);
                s1 = "Курс: 2021";
                break;
            case R.id.context3item3:
                tvkurs.setText(R.string.kurs3);
                s1 = "Курс: 2020";
                break;
            case R.id.context3item4:
                tvkurs.setText(R.string.kurs4);
                s1 = "Курс: 2019";
                break;
            case R.id.context3item5:
                tvkurs.setText(R.string.kurs5);
                s1 = "Курс: 2018";
                break;
            case R.id.context1item1:
                tv1.setText(R.string.usd);
                s2 = "USD";
                break;
            case R.id.context1item2:
                tv1.setText(R.string.eur);
                s2 = "EUR";
                break;
            case R.id.context1item3:
                tv1.setText(R.string.aud);
                s2 = "AUD";
                break;
            case R.id.context1item4:
                tv1.setText(R.string.byn);
                s2 = "BYN";
                break;
            case R.id.context1item5:
                tv1.setText(R.string.bgn);
                s2 = "BGN";
                break;
            case R.id.context1item6:
                tv1.setText(R.string.hkd);
                s2 = "HKD";
                break;
            case R.id.context1item7:
                tv1.setText(R.string.dkk);
                s2 = "DKK";
                break;
            case R.id.context1item8:
                tv1.setText(R.string.uah);
                s2 = "UAH";
                break;
            case R.id.context2item1:
                tv2.setText(R.string.usd1);
                s3 = "USD";
                break;
            case R.id.context2item2:
                tv2.setText(R.string.eur1);
                s3 = "EUR";
                break;
            case R.id.context2item3:
                tv2.setText(R.string.aud1);
                s3 = "AUD";
                break;
            case R.id.context2item4:
                tv2.setText(R.string.byn1);
                s3 = "BYN";
                break;
            case R.id.context2item5:
                tv2.setText(R.string.bgn1);
                s3 = "BGN";
                break;
            case R.id.context2item6:
                tv2.setText(R.string.hkd1);
                s3 = "HKD";
                break;
            case R.id.context2item7:
                tv2.setText(R.string.dkk1);
                s3 = "DKK";
                break;
            case R.id.context2item8:
                tv2.setText(R.string.uah1);
                s3 = "UAH";
                break;
        }
        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                connector = new SQLiteConnector(MainActivity.this, "Valutes", 1);
                try {
                    db = connector.getWritableDatabase();
                    ContentValues ContentValues1 = new ContentValues();
                    ContentValues1.put("title", "USD");
                    ContentValues1.put("kol", 1);
                    ContentValues1.put("kurs", 27.7372);
                    long rowID1 = db.insert("Valutes2022", null, ContentValues1);
                    if(rowID1!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID1);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues2 = new ContentValues();
                    ContentValues2.put("title", "EUR");
                    ContentValues2.put("kol", 1);
                    ContentValues2.put("kurs", 31.7813);
                    long rowID2 = db.insert("Valutes2022", null, ContentValues2);
                    if(rowID2!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID2);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues3 = new ContentValues();
                    ContentValues3.put("title", "AUD");
                    ContentValues3.put("kol", 1);
                    ContentValues3.put("kurs", 20.5077);
                    long rowID3 = db.insert("Valutes2022", null, ContentValues3);
                    if(rowID3!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID3);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues4 = new ContentValues();
                    ContentValues4.put("title", "BGN");
                    ContentValues4.put("kol", 1);
                    ContentValues4.put("kurs", 16.4252);
                    long rowID4 = db.insert("Valutes2022", null, ContentValues4);
                    if(rowID4!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID4);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues5 = new ContentValues();
                    ContentValues5.put("title", "BYN");
                    ContentValues5.put("kol", 1);
                    ContentValues5.put("kurs", 11.0183);
                    long rowID5 = db.insert("Valutes2022", null, ContentValues5);
                    if(rowID5!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID5);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues6 = new ContentValues();
                    ContentValues6.put("title", "HKD");
                    ContentValues6.put("kol", 1);
                    ContentValues6.put("kurs", 3.6379);
                    long rowID6 = db.insert("Valutes2022", null, ContentValues6);
                    if(rowID6!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID6);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues7 = new ContentValues();
                    ContentValues7.put("title", "DKK");
                    ContentValues7.put("kol", 1);
                    ContentValues7.put("kurs", 4.3151);
                    long rowID7 = db.insert("Valutes2022", null, ContentValues7);
                    if(rowID7!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID7);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues8 = new ContentValues();
                    ContentValues8.put("title", "UAH");
                    ContentValues8.put("kol", 1);
                    ContentValues8.put("kurs", 1);
                    long rowID8 = db.insert("Valutes2022", null, ContentValues8);
                    if(rowID8 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID8);
                    else Log.d(LOG_TAG,"insertion error");

                    ContentValues ContentValues11 = new ContentValues();
                    ContentValues11.put("title", "USD");
                    ContentValues11.put("kol", 1);
                    ContentValues11.put("kurs", 28.0524);
                    long rowID11 = db.insert("Valutes2021", null, ContentValues11);
                    if(rowID11!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID11);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues12 = new ContentValues();
                    ContentValues12.put("title", "EUR");
                    ContentValues12.put("kol", 1);
                    ContentValues12.put("kurs", 34.0121);
                    long rowID12 = db.insert("Valutes2021", null, ContentValues12);
                    if(rowID12!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID12);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues13 = new ContentValues();
                    ContentValues13.put("title", "AUD");
                    ContentValues13.put("kol", 1);
                    ContentValues13.put("kurs", 21.7957);
                    long rowID13 = db.insert("Valutes2021", null, ContentValues13);
                    if(rowID13!=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID13);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues14 = new ContentValues();
                    ContentValues14.put("title", "BGN");
                    ContentValues14.put("kol", 1);
                    ContentValues14.put("kurs", 17.4656);
                    long rowID14 = db.insert("Valutes2021", null, ContentValues14);
                    if(rowID14 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID14);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues15 = new ContentValues();
                    ContentValues15.put("title", "BYN");
                    ContentValues15.put("kol", 1);
                    ContentValues15.put("kurs", 11.1125);
                    long rowID15 = db.insert("Valutes2021", null, ContentValues15);
                    if(rowID15 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID15);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues16 = new ContentValues();
                    ContentValues16.put("title", "HKD");
                    ContentValues16.put("kol", 1);
                    ContentValues16.put("kurs", 3.6383);
                    long rowID16 = db.insert("Valutes2021", null, ContentValues16);
                    if(rowID16 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID16);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues17 = new ContentValues();
                    ContentValues17.put("title", "DKK");
                    ContentValues17.put("kol", 1);
                    ContentValues17.put("kurs", 4.5907);
                    long rowID17 = db.insert("Valutes2021", null, ContentValues17);
                    if(rowID17 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID17);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues18 = new ContentValues();
                    ContentValues18.put("title", "UAH");
                    ContentValues18.put("kol", 1);
                    ContentValues18.put("kurs", 1);
                    long rowID18 = db.insert("Valutes2021", null, ContentValues18);
                    if(rowID18 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID18);
                    else Log.d(LOG_TAG,"insertion error");

                    ContentValues ContentValues21 = new ContentValues();
                    ContentValues21.put("title", "USD");
                    ContentValues21.put("kol", 1);
                    ContentValues21.put("kurs", 24.3257);
                    long rowID21 = db.insert("Valutes2020", null, ContentValues21);
                    if(rowID21 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID21);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues22 = new ContentValues();
                    ContentValues22.put("title", "EUR");
                    ContentValues22.put("kol", 1);
                    ContentValues22.put("kurs", 26.9565);
                    long rowID22 = db.insert("Valutes2020", null, ContentValues22);
                    if(rowID22 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID22);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues23 = new ContentValues();
                    ContentValues23.put("title", "AUD");
                    ContentValues23.put("kol", 1);
                    ContentValues23.put("kurs", 16.6911);
                    long rowID23 = db.insert("Valutes2020", null, ContentValues23);
                    if(rowID23 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID23);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues24 = new ContentValues();
                    ContentValues24.put("title", "BGN");
                    ContentValues24.put("kol", 1);
                    ContentValues24.put("kurs", 13.783);
                    long rowID24 = db.insert("Valutes2020", null, ContentValues24);
                    if(rowID24 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID24);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues25 = new ContentValues();
                    ContentValues25.put("title", "BYN");
                    ContentValues25.put("kol", 1);
                    ContentValues25.put("kurs", 11.4711);
                    long rowID25 = db.insert("Valutes2020", null, ContentValues25);
                    if(rowID25 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID25);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues26 = new ContentValues();
                    ContentValues26.put("title", "HKD");
                    ContentValues26.put("kol", 1);
                    ContentValues26.put("kurs", 3.1313);
                    long rowID26 = db.insert("Valutes2020", null, ContentValues26);
                    if(rowID26 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID26);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues27 = new ContentValues();
                    ContentValues27.put("title", "DKK");
                    ContentValues27.put("kol", 1);
                    ContentValues27.put("kurs", 3.6074);
                    long rowID27 = db.insert("Valutes2020", null, ContentValues27);
                    if(rowID27 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID27);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues28 = new ContentValues();
                    ContentValues28.put("title", "UAH");
                    ContentValues28.put("kol", 1);
                    ContentValues28.put("kurs", 1);
                    long rowID28 = db.insert("Valutes2020", null, ContentValues28);
                    if(rowID28 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID28);
                    else Log.d(LOG_TAG,"insertion error");

                    ContentValues ContentValues31 = new ContentValues();
                    ContentValues31.put("title", "USD");
                    ContentValues31.put("kol", 1);
                    ContentValues31.put("kurs", 27.9808);
                    long rowID31 = db.insert("Valutes2019", null, ContentValues31);
                    if(rowID31 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID31);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues32 = new ContentValues();
                    ContentValues32.put("title", "EUR");
                    ContentValues32.put("kol", 1);
                    ContentValues32.put("kurs", 31.9037);
                    long rowID32 = db.insert("Valutes2019", null, ContentValues32);
                    if(rowID32 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID32);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues33 = new ContentValues();
                    ContentValues33.put("title", "AUD");
                    ContentValues33.put("kol", 1);
                    ContentValues33.put("kurs", 20.1285);
                    long rowID33 = db.insert("Valutes2019", null, ContentValues33);
                    if(rowID33 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID33);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues34 = new ContentValues();
                    ContentValues34.put("title", "BGN");
                    ContentValues34.put("kol", 1);
                    ContentValues34.put("kurs", 16.3124);
                    long rowID34 = db.insert("Valutes2019", null, ContentValues34);
                    if(rowID34 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID34);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues35 = new ContentValues();
                    ContentValues35.put("title", "BYN");
                    ContentValues35.put("kol", 1);
                    ContentValues35.put("kurs", 13.015);
                    long rowID35 = db.insert("Valutes2019", null, ContentValues35);
                    if(rowID35 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID35);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues36 = new ContentValues();
                    ContentValues36.put("title", "HKD");
                    ContentValues36.put("kol", 1);
                    ContentValues36.put("kurs", 3.5670);
                    long rowID36 = db.insert("Valutes2019", null, ContentValues36);
                    if(rowID36 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID36);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues37 = new ContentValues();
                    ContentValues37.put("title", "DKK");
                    ContentValues37.put("kol", 1);
                    ContentValues37.put("kurs", 4.2738);
                    long rowID37 = db.insert("Valutes2019", null, ContentValues37);
                    if(rowID37 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID37);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues38 = new ContentValues();
                    ContentValues38.put("title", "UAH");
                    ContentValues38.put("kol", 1);
                    ContentValues38.put("kurs", 1);
                    long rowID38 = db.insert("Valutes2019", null, ContentValues38);
                    if(rowID38 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID38);
                    else Log.d(LOG_TAG,"insertion error");

                    ContentValues ContentValues41 = new ContentValues();
                    ContentValues41.put("title", "USD");
                    ContentValues41.put("kol", 1);
                    ContentValues41.put("kurs", 28.7774);
                    long rowID41 = db.insert("Valutes2018", null, ContentValues41);
                    if(rowID41 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID41);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues42 = new ContentValues();
                    ContentValues42.put("title", "EUR");
                    ContentValues42.put("kol", 1);
                    ContentValues42.put("kurs", 35.2092);
                    long rowID42 = db.insert("Valutes2018", null, ContentValues42);
                    if(rowID42 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID42);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues43 = new ContentValues();
                    ContentValues43.put("title", "AUD");
                    ContentValues43.put("kol", 1);
                    ContentValues43.put("kurs", 22.9960);
                    long rowID43 = db.insert("Valutes2018", null, ContentValues43);
                    if(rowID43 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID43);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues44 = new ContentValues();
                    ContentValues44.put("title", "BGN");
                    ContentValues44.put("kol", 1);
                    ContentValues44.put("kurs", 18.0024);
                    long rowID44 = db.insert("Valutes2018", null, ContentValues44);
                    if(rowID44 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID44);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues45 = new ContentValues();
                    ContentValues45.put("title", "BYN");
                    ContentValues45.put("kol", 1);
                    ContentValues45.put("kurs", 14.4371);
                    long rowID45 = db.insert("Valutes2018", null, ContentValues45);
                    if(rowID45 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID45);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues46 = new ContentValues();
                    ContentValues46.put("title", "HKD");
                    ContentValues46.put("kol", 1);
                    ContentValues46.put("kurs", 3.6811);
                    long rowID46 = db.insert("Valutes2018", null, ContentValues46);
                    if(rowID46 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID46);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues47 = new ContentValues();
                    ContentValues47.put("title", "DKK");
                    ContentValues47.put("kol", 1);
                    ContentValues47.put("kurs", 4.7276);
                    long rowID47 = db.insert("Valutes2018", null, ContentValues47);
                    if(rowID47 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID47);
                    else Log.d(LOG_TAG,"insertion error");
                    ContentValues ContentValues48 = new ContentValues();
                    ContentValues48.put("title", "UAH");
                    ContentValues48.put("kol", 1);
                    ContentValues48.put("kurs", 1);
                    long rowID48 = db.insert("Valutes2018", null, ContentValues48);
                    if(rowID48 !=-1) Log.d(LOG_TAG,"insertion complete. inserted row ID "+ rowID48);
                    else Log.d(LOG_TAG,"insertion error");

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                btnCreate.setVisibility(View.GONE);
                break;
            case R.id.btnConv:
                if (s1 == "Курс: 2022") {
                    if (s2 == "USD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=1", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "EUR") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=2", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "AUD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=3", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "BGN") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=4", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "BYN") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=5", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "HKD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=6", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "DKK") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=7", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=8", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2022 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        }
                    }
                } else
                if (s1 == "Курс: 2021") {
                    if (s2 == "USD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=1", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "EUR") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=2", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "AUD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=3", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "BGN") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=4", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "BYN") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=5", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "HKD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=6", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "DKK") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=7", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=8", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2021 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        }
                    }
                } else
                if (s1 == "Курс: 2020") {
                    if (s2 == "USD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=1", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "EUR") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=2", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "AUD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=3", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "BGN") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=4", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "BYN") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=5", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "HKD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=6", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "DKK") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=7", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=8", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2020 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        }
                    }
                } else
                if (s1 == "Курс: 2019") {
                    if (s2 == "USD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=1", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "EUR") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=2", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "AUD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=3", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "BGN") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=4", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "BYN") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=5", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "HKD") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=6", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else
                    if (s2 == "DKK") {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=7", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        } else {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=8", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        }
                    } else {
                        Cursor query = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=8", null);
                        while(query.moveToNext())
                        {
                            int kursIndex = query.getColumnIndex("kurs");
                            s4 = query.getString(kursIndex);
                            Log.d(LOG_TAG,"kurs = "+ s4);
                        }
                        if (s3 == "USD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=1", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "EUR") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=2", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "AUD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=3", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BGN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=4", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "BYN") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=5", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "HKD") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=6", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else
                        if (s3 == "DKK") {
                            s5 = Input.getText().toString();
                            double res1 =
                                    (Double.parseDouble(s4))*(Double.parseDouble(s5));
                            Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2019 WHERE _id=7", null);
                            while(query1.moveToNext())
                            {
                                int kursIndex = query1.getColumnIndex("kurs");
                                s6 = query1.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s6);
                            }
                            double res2 =
                                    (res1)/(Double.parseDouble(s6));
                            String res = String.format("%.3f", res2);
                            Output.setText(res);
                        } else {
                            s5 = Input.getText().toString();
                            Output.setText(s5);
                        }
                    }
                } else {
                    if (s2 == "USD") {
                            Cursor query = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=1", null);
                            while(query.moveToNext())
                            {
                                int kursIndex = query.getColumnIndex("kurs");
                                s4 = query.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s4);
                            }
                            if (s3 == "USD") {
                                s5 = Input.getText().toString();
                                Output.setText(s5);
                            } else
                            if (s3 == "EUR") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=2", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "AUD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=3", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BGN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=4", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BYN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=5", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "HKD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=6", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "DKK") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=7", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=8", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            }
                        } else
                    if (s2 == "EUR") {
                            Cursor query = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=2", null);
                            while(query.moveToNext())
                            {
                                int kursIndex = query.getColumnIndex("kurs");
                                s4 = query.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s4);
                            }
                            if (s3 == "USD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=1", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "EUR") {
                                s5 = Input.getText().toString();
                                Output.setText(s5);
                            } else
                            if (s3 == "AUD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=3", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BGN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=4", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BYN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=5", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "HKD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=6", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "DKK") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=7", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=8", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            }
                        } else
                    if (s2 == "AUD") {
                            Cursor query = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=3", null);
                            while(query.moveToNext())
                            {
                                int kursIndex = query.getColumnIndex("kurs");
                                s4 = query.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s4);
                            }
                            if (s3 == "USD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=1", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "EUR") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=2", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "AUD") {
                                s5 = Input.getText().toString();
                                Output.setText(s5);
                            } else
                            if (s3 == "BGN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=4", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BYN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=5", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "HKD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=6", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "DKK") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=7", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=8", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            }
                        } else
                    if (s2 == "BGN") {
                            Cursor query = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=4", null);
                            while(query.moveToNext())
                            {
                                int kursIndex = query.getColumnIndex("kurs");
                                s4 = query.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s4);
                            }
                            if (s3 == "USD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=1", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "EUR") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=2", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "AUD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=3", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BGN") {
                                s5 = Input.getText().toString();
                                Output.setText(s5);
                            } else
                            if (s3 == "BYN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=5", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "HKD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=6", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "DKK") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=7", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=8", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            }
                        } else
                    if (s2 == "BYN") {
                            Cursor query = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=5", null);
                            while(query.moveToNext())
                            {
                                int kursIndex = query.getColumnIndex("kurs");
                                s4 = query.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s4);
                            }
                            if (s3 == "USD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=1", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "EUR") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=2", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "AUD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=3", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BGN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=4", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BYN") {
                                s5 = Input.getText().toString();
                                Output.setText(s5);
                            } else
                            if (s3 == "HKD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=6", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "DKK") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=7", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=8", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            }
                        } else
                    if (s2 == "HKD") {
                            Cursor query = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=6", null);
                            while(query.moveToNext())
                            {
                                int kursIndex = query.getColumnIndex("kurs");
                                s4 = query.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s4);
                            }
                            if (s3 == "USD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=1", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "EUR") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=2", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "AUD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=3", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BGN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=4", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BYN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=5", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "HKD") {
                                s5 = Input.getText().toString();
                                Output.setText(s5);
                            } else
                            if (s3 == "DKK") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=7", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=8", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            }
                        } else
                    if (s2 == "DKK") {
                            Cursor query = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=7", null);
                            while(query.moveToNext())
                            {
                                int kursIndex = query.getColumnIndex("kurs");
                                s4 = query.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s4);
                            }
                            if (s3 == "USD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=1", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "EUR") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=2", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "AUD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=3", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BGN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=4", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BYN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=5", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "HKD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=6", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "DKK") {
                                s5 = Input.getText().toString();
                                Output.setText(s5);
                            } else {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=8", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            }
                        } else {
                            Cursor query = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=8", null);
                            while(query.moveToNext())
                            {
                                int kursIndex = query.getColumnIndex("kurs");
                                s4 = query.getString(kursIndex);
                                Log.d(LOG_TAG,"kurs = "+ s4);
                            }
                            if (s3 == "USD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=1", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "EUR") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=2", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "AUD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=3", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BGN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=4", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "BYN") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=5", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "HKD") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=6", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else
                            if (s3 == "DKK") {
                                s5 = Input.getText().toString();
                                double res1 =
                                        (Double.parseDouble(s4))*(Double.parseDouble(s5));
                                Cursor query1 = db.rawQuery("SELECT kurs FROM Valutes2018 WHERE _id=7", null);
                                while(query1.moveToNext())
                                {
                                    int kursIndex = query1.getColumnIndex("kurs");
                                    s6 = query1.getString(kursIndex);
                                    Log.d(LOG_TAG,"kurs = "+ s6);
                                }
                                double res2 =
                                        (res1)/(Double.parseDouble(s6));
                                String res = String.format("%.3f", res2);
                                Output.setText(res);
                            } else {
                                s5 = Input.getText().toString();
                                Output.setText(s5);
                            }
                        }
                }
                break;
        }
    }
    @Override
    protected void onDestroy()
    {
    if(db!=null) db.close();
    super.onDestroy();
    }
}