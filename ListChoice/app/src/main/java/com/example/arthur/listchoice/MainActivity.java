package com.example.arthur.listchoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView listView;
    String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        //listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_single_choice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_multiple_choice);
        listView.setAdapter(adapter);

        Button btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);

        names = getResources().getStringArray(R.array.names);
    }

    @Override
    public void onClick(View v) {
        //Log.d("MyLog", "checked: " + names[listView.getCheckedItemPosition()]);

        Log.d("MyLog", "checked: ");
        //SparseBooleanArray редставляет собой Map(int, boolean).
        //boolean - выделен или нет
        SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            int key = sparseBooleanArray.keyAt(i);
            if (sparseBooleanArray.get(key))
                Log.d("MyLog", names[key]);
        }
    }
}
