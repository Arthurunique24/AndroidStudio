package com.example.arthur.mycalc;

import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNum1;
    EditText etNum2;
    Button BtnAdd;
    Button BtnSub;
    Button BtnMult;
    Button BtnDiv;
    TextView textView;
    Button BtnClear;

    String oper = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        BtnAdd = (Button) findViewById(R.id.BtnAdd);
        BtnSub = (Button) findViewById(R.id.BtnSub);
        BtnMult = (Button) findViewById(R.id.BtnMult);
        BtnDiv = (Button) findViewById(R.id.BtnDiv);
        textView = (TextView) findViewById(R.id.textView);
        BtnClear = (Button) findViewById(R.id.BtnClear);

        BtnAdd.setOnClickListener(this);
        BtnSub.setOnClickListener(this);
        BtnMult.setOnClickListener(this);
        BtnDiv.setOnClickListener(this);
        BtnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        float num1 = 0;
        float num2 = 0;
        float result = 0;

        if (TextUtils.isEmpty(etNum1.getText().toString()) || TextUtils.isEmpty((etNum2.getText().toString()))){
            return;
        }

        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        switch (v.getId()){
            case R.id.BtnAdd:
                oper = "+";
                result = num1 + num2;
                break;
            case R.id.BtnSub:
                oper = "-";
                result = num1 - num2;
                break;
            case R.id.BtnMult:
                oper = "*";
                result = num1 * num2;
                break;
            case R.id.BtnDiv:
                oper = "/";
                result = num1 / num2;
                break;
            default:
                break;
        }
        textView.setText(num1+ " " + oper + " " + num2 + "=" + result);

        switch (v.getId()){
            case R.id.BtnClear:
                etNum1.setText("");
                etNum2.setText("");
                textView.setText("");
                break;
        }
    }
}
