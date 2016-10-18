package com.example.arthur.testactivity_intent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        Intent intent = getIntent();
        String action = intent.getAction();

        String format = "", info = "";


        if (action.equals("com.example.intent.time")){
            info = "Time: ";
            format = "HH:mm:ss";
        } else if (action.equals("com.example.intent.date")){
            info = "Date: ";
            format = "dd.mm.yyyy";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        Animation anim = null;
        anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);

        TextView tvTime = (TextView) findViewById(R.id.tvTime);
        tvTime.startAnimation(anim);
        tvTime.setText(info + dateTime);

    }
}
