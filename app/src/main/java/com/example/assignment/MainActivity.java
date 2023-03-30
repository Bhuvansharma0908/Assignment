package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
TextView regis;
EditText number,passs;
Button login;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://spotifylike-3eda4-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regis=findViewById(R.id.dontacc);
        number=findViewById(R.id.mobileNo);
        passs=findViewById(R.id.password);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=number.getText().toString().trim();
                String pass=passs.getText().toString().trim();
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(num)) {
                                String getpass = snapshot.child(num).child("password").getValue(String.class);
                                if (getpass.equals(pass)) {
                                    Toast.makeText(MainActivity.this, "Logged in Sucessfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this, dashboard.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Password Don't Matched", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Not Registered", Toast.LENGTH_SHORT).show();

                            }
                        }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, register.class);
                startActivity(i);
            }
        });

    }
}