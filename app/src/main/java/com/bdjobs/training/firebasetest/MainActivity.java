package com.bdjobs.training.firebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText username, password;

    Button save, show;
    DatabaseReference database;
    ListView itemLV;
    String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry",
            "WebOS", "Ubuntu", "Windows7", "Max OS X", "bla bla bla", "kla kla kla"};
    ArrayList<Users> myList = new ArrayList<Users>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        database = FirebaseDatabase.getInstance().getReference();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = String.valueOf(username.getText());
                String passwordValue = String.valueOf(password.getText());
                writeNewUser(usernameValue, passwordValue);

            }
        });
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, myList);
        itemLV.setAdapter(arrayAdapter);

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Users users = dataSnapshot.getValue(Users.class);
             //   String value = String.valueOf(dataSnapshot.getValue(Users.class));
                myList.add(users);
                Log.d("valval", "onChildAdded: " + users);

//                passwordTV2.setText((CharSequence) dataSnapshot.child("users").getValue(Users.class).getPassword());

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

    private void initialize() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        save = findViewById(R.id.save);
        itemLV = findViewById(R.id.itemLV);

    }

    public void writeNewUser(String userName, String passWord) {
//        HashMap<String, String> dataHashMap = new HashMap<String, String>();
//        dataHashMap.put("USERNAME", userName);
//        dataHashMap.put("PASSWORD", passWord);
//        database.push().setValue(dataHashMap);
        Users users = new Users(userName, passWord);
        database.push().setValue(users);

    }
}
