package com.example.arthur.sqliteupgrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";
    private static final String DB_NAME = "MyDB";
    private static final String TABLE_NAME = "MyTable";
    final int DB_VERSION = 1;
    private SQLiteDatabase database;
    ContentValues contentValues = new ContentValues();

    String[] people_name = { "Иван", "Марья", "Петр", "Антон", "Даша",
            "Борис", "Костя", "Игорь" };
    String[] people_positions = { "Программер", "Бухгалтер",
            "Программер", "Программер", "Бухгалтер", "Директор",
            "Программер", "Охранник" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, " --- Staff db v." + database.getVersion() + " --- ");
        writeStaff(database);
    }



    class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, " --- onCreate database --- ");
            initDB();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    private void initDB(){
        database = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id integer primary key autoincrement, name text, position text);");
        database.delete(TABLE_NAME, null, null);

        for (int i = 0; i < people_name.length; i++) {
            contentValues.clear();
            contentValues.put("name", people_name[i]);
            contentValues.put("position", people_positions[i]);
            database.insert(TABLE_NAME, null, contentValues);
        }
    }

    private void writeStaff(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from  TABLE_NAME", null);
        logCursor(cursor, "Table TABLE_NAME");
        cursor.close();
    }

    void logCursor(Cursor cursor, String title){
        if(cursor != null){
            if(cursor.moveToFirst()){
                Log.d(LOG_TAG, title + ". " + cursor.getCount() + " raws");
                StringBuilder sb = new StringBuilder();
                do{
                    sb.setLength(0);
                    for (String cn : cursor.getColumnNames()) {
                        sb.append(cn + " = "
                                + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    } Log.d(LOG_TAG, sb.toString());
                } while (cursor.moveToNext());
            } else Log.d(LOG_TAG, title + ". Cursor is null");
        }
    }

}
