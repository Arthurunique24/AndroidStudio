package com.example.arthur.tab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;


        tabSpec = tabHost.newTabSpec("tag1");//указываем тэг вкладки
        tabSpec.setIndicator("Вкладка 1");//укзываем название
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);//добавляем в корневой элемент

        tabSpec = tabHost.newTabSpec("tag2");//указываем тэг вкладки
        tabSpec.setIndicator("Вкладка 2");//укзываем название
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);//добавляем в корневой элемент

        tabSpec = tabHost.newTabSpec("tag2");//указываем тэг вкладки
        tabSpec.setIndicator("Вкладка 2");//укзываем название
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);//добавляем в корневой элемент

        tabHost.setCurrentTabByTag("tag2");//вкладка по умолчанию

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
