package com.example.enterpriseapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CancleOrder extends AppCompatActivity {

    public static final String LOG = "LOG";
    private Button submit, viewlist;
    private  EditText reason, newmenu, nextmeal;
    FirebaseFirestore firestore;
    FirebaseUser user;
    FirebaseAuth mAuth;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancle_order);
        reason = findViewById(R.id.Reason);
        newmenu = findViewById(R.id.Newmenu);
        nextmeal = findViewById(R.id.NextMeal);
        submit = findViewById(R.id.cancel_order_request_submit);
        viewlist = findViewById(R.id.cancel_order_request_viewlist);
        user=mAuth.getInstance().getCurrentUser();

        if(user!=null)
        {
            email=user.getEmail();
        }
        firestore= FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String sReason = reason.getText().toString().trim();
                final String sNewMenu = newmenu.getText().toString().trim();
                final String sNextMeal = nextmeal.getText().toString().trim();
                if (TextUtils.isEmpty(sReason)) {
                    reason.setError("Reason is required");
                    return;
                }
                if (TextUtils.isEmpty(sNewMenu)) {
                    newmenu.setError("new menu is required");
                    return;
                }
                if (TextUtils.isEmpty(sNextMeal)) {
                    nextmeal.setError("next meal is required");
                    return;
                }
                DocumentReference requestRef = firestore.collection("CancelOrderRequest").document();
                final Map<String, Object> newMenu = new HashMap<>();

                newMenu.put("Reason", sReason);
                newMenu.put("NewMenu", sNewMenu);
                newMenu.put("NextMeal", sNextMeal);
                newMenu.put("EEmail",email);

                requestRef.set(newMenu).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(LOG, "onSuccess: Menu Change Request successfully sent");
                        Toast.makeText(CancleOrder.this, "Submitted!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(LOG, "onFailure:" + e.toString());
                        Toast.makeText(CancleOrder.this, "Cannot Send Request", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CancleOrder.this, CancelOrderDetails.class);
                startActivity(intent);
            }
        });
    }
}