package com.example.arthur.sqlitetransaction;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String DB_NAME = "MyDB";
    private static final String TABLE_NAME = "MyTable";
    private SQLiteDatabase database;

    TextView tvTime;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);

    }

    private void initDB(){
        database = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(FirstNumber INT, SecondNumber INT, Result INT);");
        database.delete(TABLE_NAME, null, null);
    }

    @Override
    public void onClick(View v) {
        database.delete(TABLE_NAME, null, null);
        long startTime = System.currentTimeMillis();
        insertRecords();
        long diff = System.currentTimeMillis() - startTime;
        tvTime.setText("Time: " + Long.toString(diff) + "ms");
    }

    private void insertRecords(){
        /*database.beginTransaction();
        try {
            for (int i = 0; i < 1000; i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("FirstNumber", i);
                contentValues.put("SecondNumber", i);
                contentValues.put("Result", i * i);
                database.insert(TABLE_NAME, null, contentValues);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        } */

        String str = "INSERT INTO " + TABLE_NAME + " VALUES(?, ?, ?);";
        SQLiteStatement statement = database.compileStatement(str);
        database.beginTransaction();
        try{
            for(int i = 0; i < 1000; i++){
                statement.clearBindings();
                statement.bindLong(1 , i);
                statement.bindLong(2 , i);
                statement.bindLong(3 , i*i);
            } database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
