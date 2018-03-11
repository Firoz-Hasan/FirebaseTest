package com.bdjobs.training.firebasetest;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class HashmapAcitivity extends AppCompatActivity {

    EditText user, pass;
    ListView hashmapList;
    Button save, exitBTN;
    DatabaseReference db;
    ArrayList<HashMap<String, String>> userList;
    HashMap<String, String> dataHash;
    HashMap<String, String> dataHashAgain;
    HashMapListAdapter hashMapListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashmap_acitivity);
        initialization();
        onclicklistener();
        retrieveData();

        hashMapListAdapter = new HashMapListAdapter(getApplicationContext(),userList);
        hashmapList.setAdapter(hashMapListAdapter);


    }

    private void retrieveData() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               dataHashAgain = (HashMap<String, String>) dataSnapshot.getValue();
               userList.add(dataHashAgain);
               hashMapListAdapter.notifyDataSetChanged();
                Log.d("ddddd", "onChildAdded: "+userList.size());
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

    private void onclicklistener() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = String.valueOf(user.getText());
                String passwordValue = String.valueOf(pass.getText());
                writeNewUser(usernameValue, passwordValue);
            }
        });
    }

    private void writeNewUser(String usernameValue, String passwordValue) {

        dataHash.put("UserName", usernameValue);
        dataHash.put("Password", passwordValue);
        db.push().setValue(dataHash);


    }

    @SuppressLint("WrongViewCast")
    private void initialization() {
        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        hashmapList = findViewById(R.id.itemLV);
        save = findViewById(R.id.save);
        exitBTN = findViewById(R.id.exitBTN);
        db = FirebaseDatabase.getInstance().getReference();
        userList = new ArrayList<HashMap<String, String>>();
        dataHash = new HashMap<String, String>();
        dataHashAgain = new HashMap<String, String>();

    }
}
