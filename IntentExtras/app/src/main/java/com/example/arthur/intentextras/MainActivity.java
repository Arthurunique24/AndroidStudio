package com.example.arthur.intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editText;
    Button btnSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        btnSub = (Button) findViewById(R.id.BtnSub);

        btnSub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
                Intent intent = new Intent(this, ActivityTwo.class);
                intent.putExtra("name", editText.getText().toString());
                startActivity(intent);
    }
}
