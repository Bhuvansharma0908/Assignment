package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
EditText mob,name,pass;
Button signup;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://spotifylike-3eda4-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mob=findViewById(R.id.mobileNoregis);
        pass=findViewById(R.id.passwordregis);
        name=findViewById(R.id.nameregis);
        signup=findViewById(R.id.register);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=mob.getText().toString().trim();
                String pas=pass.getText().toString().trim();
                String nam=name.getText().toString().trim();
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(num)) {

                                Toast.makeText(register.this, "Already Exists", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                databaseReference.child("users").child(num).child("password").setValue(pas);
                                databaseReference.child("users").child(num).child("fullname").setValue(nam);
                                Toast.makeText(register.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(register.this, MainActivity.class);
                                startActivity(i);
                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}