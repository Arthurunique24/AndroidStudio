package com.example.arthur.firebasetest;

import com.firebase.client.Firebase;

/**
 * Created by Arthur on 22.10.16.
 */

public class Database extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
