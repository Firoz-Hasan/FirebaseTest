package com.bdjobs.training.firebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    TextView usernameTV2, passwordTV2;
    Button save, show;
    DatabaseReference database;
    DatabaseReference userRef, passwordRef;

//    myRef.setValue("Hello, World!");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

         database = FirebaseDatabase.getInstance().getReference();
      //  userRef = database.getReference("username");
    //    passwordRef = database.getReference("password");

         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String usernameValue= String.valueOf(username.getText());
                 String passwordValue = String.valueOf(password.getText());
//                 userRef.setValue(usernameValue);
//                 passwordRef.setValue(passwordValue);
                 writeNewUser(usernameValue, passwordValue);

             }
         });
         show.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });

         database.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                Users users = new Users();
                usernameTV2.setText((CharSequence) dataSnapshot.child("users").getValue(Users.class).getUsername());
                passwordTV2.setText((CharSequence) dataSnapshot.child("users").getValue(Users.class).getPassword());
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });

//         userRef.addValueEventListener(new ValueEventListener() {
//             @Override
//             public void onDataChange(DataSnapshot dataSnapshot) {
//                 String uservalue = dataSnapshot.getValue(String.class);
//               usernameTV2.setText(uservalue);
//             }
//
//             @Override
//             public void onCancelled(DatabaseError databaseError) {
//                 Log.w("userLOG", "Failed to read value.", databaseError.toException());
//             }
//         });
//         passwordRef.addValueEventListener(new ValueEventListener() {
//             @Override
//             public void onDataChange(DataSnapshot dataSnapshot) {
//                 String passvalue = dataSnapshot.getValue(String.class);
//                 passwordTV2.setText(passvalue);
//             }
//
//             @Override
//             public void onCancelled(DatabaseError databaseError) {
//                 Log.w("passLOG", "Failed to read value.", databaseError.toException());
//             }
//         });
    }

    private void initialize() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        save = findViewById(R.id.save);
        show = findViewById(R.id.show);
        usernameTV2 = findViewById(R.id.usernameTV2);
        passwordTV2 =  findViewById(R.id.passwordTV2);

    }
    public void writeNewUser(String userName, String passWord){

        Users users = new Users(userName, passWord);
        database.child("users").setValue(users);
       // database.child("Users").child("Password").setValue(passWord);
    }
}
