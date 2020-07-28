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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MenuChangeRequest extends AppCompatActivity {
    public static  final String LOG = "LOG";
    private Button Submit, ViewRequestList;
    private EditText reason,newmenu;
    private FirebaseFirestore firestore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_change_request);
        reason=findViewById(R.id.Reason);
        newmenu=findViewById(R.id.newmenu);
        Submit = findViewById(R.id.cancel_order_request_submit);
        ViewRequestList = findViewById(R.id.cancel_order_request_viewlist);
        firestore=FirebaseFirestore.getInstance();




        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String sReason = reason.getText().toString().trim();
                final String sNewMenu = newmenu.getText().toString().trim();
                if(TextUtils.isEmpty(sReason)){
                    reason.setError("Reason is required");
                    return;
                }
                if(TextUtils.isEmpty(sNewMenu)){
                    newmenu.setError("new menu is required");
                    return;
                }
                DocumentReference requestRef= firestore.collection("MenuChangeRequest").document();
                final Map<String,Object> newMenu= new HashMap<>();
                newMenu.put("RequestTime", FieldValue.serverTimestamp());
                newMenu.put("Reason",sReason);
                newMenu.put("NewMenu",sNewMenu);

                requestRef.set(newMenu).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(LOG,"onSuccess: Menu Change Request successfully sent");
                        Toast.makeText(MenuChangeRequest.this, "Submitted!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(LOG, "onFailure:" + e.toString());
                        Toast.makeText(MenuChangeRequest.this, "Cannot Send Request", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        ViewRequestList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuChangeRequest.this,MenuRequestDetails.class);
                startActivity(intent);

            }
        });
    }
}