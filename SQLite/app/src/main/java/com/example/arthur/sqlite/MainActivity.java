package com.example.arthur.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClean, btnUpdate, btnDel;
    EditText edName, edEmail, edId;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button)findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClean = (Button)findViewById(R.id.btnClean);
        btnClean.setOnClickListener(this);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        edName = (EditText)findViewById(R.id.edName);
        edEmail = (EditText)findViewById(R.id.edEmail);
        edId = (EditText) findViewById(R.id.edId);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = edName.getText().toString();
        String email = edEmail.getText().toString();
        String id = edId.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        //Добавляем
        ContentValues contentValues = new ContentValues();

        switch (v.getId()){
            case R.id.btnAdd:
                if(name.equalsIgnoreCase("") || email.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Incorrect name or email ", Toast.LENGTH_SHORT).show();
                    break;
                }
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_EMAIL, email);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
                if(cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);
                        do {
                            Log.d("mLog", "ID = " + cursor.getInt(idIndex)
                                    + ", Name = " + cursor.getString(nameIndex)
                                    + ", email = " + cursor.getString(emailIndex));
                        } while (cursor.moveToNext());
                } else Log.d("mLog", "0 rows");

                cursor.close();
                break;

            case R.id.btnClean:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;

            case R.id.btnUpdate:
                if(id.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "incorrect id", Toast.LENGTH_SHORT).show();
                    break;
                }
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_EMAIL, email);
                int updCount = database.update(DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_ID + "= ?", new String[] {id});
                Log.d("mLog", "Updates rows count = " + updCount);
                break;

            case R.id.btnDel:
                if (id.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "incorrect id", Toast.LENGTH_SHORT).show();
                    break;
                }
                //int delCount = database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "=" + id, null);
                int delCount = database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "= ?", new String[] {id});
                Log.d("mLog", "Deleted rows count = " + delCount);
        }

    }
}
