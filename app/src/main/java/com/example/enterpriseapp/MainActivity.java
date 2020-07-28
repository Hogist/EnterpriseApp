package com.example.enterpriseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button loginButton;
    EditText email, password;
    TextView forgot;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String LoginEnterprise;
    @ServerTimestamp
    Date Time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.login_button);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgot = findViewById(R.id.forgot);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Email = email.getText().toString().trim();
                final String Password = password.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    email.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    password.setError("password is required");
                    return;
                }


                fAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, UserAccount.class);
                            startActivity(intent);
                            LoginEnterprise = fAuth.getCurrentUser().getUid();


                            DocumentReference documentReference = fstore.collection("Enterprise").document(LoginEnterprise);
                            final Map<String, Object> LEnterprise = new HashMap<>();
                            LEnterprise.put("CreatedDate", FieldValue.serverTimestamp());
                            LEnterprise.put("email", Email);
                            LEnterprise.put("password", Password);

                            documentReference.set(LEnterprise).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onsucess: user profile is created for" + LoginEnterprise);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure:" + e.toString());
                                }
                            });

                        } else {
                            Toast.makeText(MainActivity.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        }
    });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,forgotpass.class));

            }
        });

}


}





