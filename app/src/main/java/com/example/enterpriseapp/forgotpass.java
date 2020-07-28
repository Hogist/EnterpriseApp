package com.example.enterpriseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpass extends AppCompatActivity {
    Button send;
    EditText Enteremail;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        send=findViewById(R.id.sendmypassword);
        Enteremail=findViewById(R.id.enter);

        fAuth=FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.sendPasswordResetEmail(Enteremail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(forgotpass.this,"password send to your email",Toast.LENGTH_LONG).show();
                      }else {
                          Toast.makeText(forgotpass.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                      }
                    }
                });
            }
        });


    }
}