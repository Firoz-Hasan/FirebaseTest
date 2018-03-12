package com.bdjobs.training.firebasetest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class HandlerActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    Random random;
    DatabaseReference db;
    int latitude, longitude;
    LatLong latLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initilization();
        handlerPostDelayed();



    }

    private void addValueDB(LatLong value) {
        db.push().setValue(value);
    }

    private void initilization() {
        handler = new Handler();
        random = new Random();
        db = FirebaseDatabase.getInstance().getReference();
    }

    private void handlerPostDelayed() {

        runnable = new Runnable() {
            @Override
            public void run() {
                latitude = random.nextInt(500);
                longitude= random.nextInt(500);

                latLong = new LatLong(latitude,longitude);

                Log.d("khkz", "run: "+ latitude);
                handler.postDelayed(this, 3000);
                addValueDB(latLong);
            }
        };
        handler.postDelayed(runnable, 3000);//h
    }
}
