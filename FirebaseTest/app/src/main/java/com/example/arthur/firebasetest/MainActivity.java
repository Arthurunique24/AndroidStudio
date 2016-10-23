package com.example.arthur.firebasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    Button btnDonut, btnNougout;
    Firebase firebaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById (R.id.textView);
        btnDonut = (Button) findViewById (R.id.btnDonut);
        btnNougout = (Button) findViewById (R.id.btnNougat);

        btnDonut.setOnClickListener(this);
        btnNougout.setOnClickListener(this);

        firebaseReference = new Firebase("https://fir-test-b8ba9.firebaseio.com/data/type");
        //Add a listener for changes in the data at this location.
        firebaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textView.setText(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDonut:
                firebaseReference.setValue("Donut");
                break;
            case R.id.btnNougat:
                firebaseReference.setValue("Nougat");
                break;
        }
        
    }
}
