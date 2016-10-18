package com.example.arthur.intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        tv = (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        tv.setText("Hello" + " " + name);
    }
}
