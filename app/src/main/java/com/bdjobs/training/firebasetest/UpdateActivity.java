package com.bdjobs.training.firebasetest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class UpdateActivity extends AppCompatActivity {
    TextView lat, lon;
    DatabaseReference db;
    String value;
    Handler handler;
    Random random;
    Runnable runnable;
    int latitude, longitude;
    Object lat1;
    Object lon1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initialization();
        handlerPostDelayed();
        retrieveDB();


    }

    private void retrieveDB() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                lat1 =  dataSnapshot.child("Latitude").getValue();
                lon1 =  dataSnapshot.child("Longitude").getValue();

                Log.d("blabla", "onChildAdded: " + lat1 + " --" + lon1);
                // lat.setText(Integer.toString((Integer) dataSnapshot.child("Latitude").getValue()));
                // lon.setText(Integer.toString((Integer) dataSnapshot.child("Longitude").getValue()));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeDB(int latitude, int longitude) {
        String key = db.child("Latitude").getKey();
        Log.d("key", "writeDB: " + key);
        //  db.child("Latitude").setValue(value);
        db.child("map").child("Latitude").setValue(latitude);
        db.child("map").child("Longitude").setValue(longitude);
    }

    private void handlerPostDelayed() {

        runnable = new Runnable() {
            @Override
            public void run() {
                latitude = random.nextInt(500);
                longitude = random.nextInt(500);

                // latLong = new LatLong(latitude,longitude);

                Log.d("khkz", "run: " + latitude);
                handler.postDelayed(this, 3000);

                writeDB(latitude, longitude);
            }
        };
        handler.postDelayed(runnable, 3000);//h
    }

    private void initialization() {

        lat = findViewById(R.id.latitudeTV);
        lon = findViewById(R.id.longitudeTV);
        handler = new Handler();
        random = new Random();
        db = FirebaseDatabase.getInstance().getReference();
    }
}
