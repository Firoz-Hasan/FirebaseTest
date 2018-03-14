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
    Handler handler;
    Random random;
    Runnable runnable;
    int latitude, longitude;
    LatLong latLong;

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
//                lat1 = dataSnapshot.child("Latitude").getValue();
//                lon1 = dataSnapshot.child("Longitude").getValue();


                // lat.setText(Integer.toString((Integer) dataSnapshot.child("Latitude").getValue()));
                // lon.setText(Integer.toString((Integer) dataSnapshot.child("Longitude").getValue()));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
              //  Integer latitudeValue = dataSnapshot.child("Latitude").getValue(Integer.class);
              //  Integer longitudeValue = dataSnapshot.child("Longitude").getValue(Integer.class);
                LatLong latLongvalue = dataSnapshot.getValue(LatLong.class);

                Log.d("cla", "onChildAdded: " + latLongvalue.getLatitude()+"--"+latLongvalue.getLongitude() );

                lat.setText(Integer.toString(latLongvalue.getLatitude()));
                lon.setText(Integer.toString(latLongvalue.getLongitude()));
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
//        String key = db.child("Latitude").getKey();
//        Log.d("key", "writeDB: " + key);
        //  db.child("Latitude").setValue(value);
        db.child("map").child("Latitude").setValue(latitude);
        db.child("map").child("Longitude").setValue(longitude);
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
    private void addValueDB(LatLong value) {
        db.child("maps").setValue(value);
        //db.child("Latitude").setValue(value.getLatitude());
        //db.child("Longitude").setValue(value.getLongitude());
    }

    private void initialization() {

        lat = findViewById(R.id.latitudeTV);
        lon = findViewById(R.id.longitudeTV);
        handler = new Handler();
        random = new Random();
        db = FirebaseDatabase.getInstance().getReference();
    }
}
