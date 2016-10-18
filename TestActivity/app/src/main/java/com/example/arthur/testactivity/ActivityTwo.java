package com.example.arthur.testactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Animation anim = null;
        tv = (TextView) findViewById(R.id.tv);
        anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
        tv.startAnimation(anim);
    }
}
