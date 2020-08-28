package com.example.enterpriseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class UserAccount extends AppCompatActivity {

    Button ViewMenu, CovidHygenicCheck,OrderNow,menuChnageRequest,cancelOrder;

    ImageView logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        ViewMenu = findViewById(R.id.user_account_viewmenu);
        CovidHygenicCheck = findViewById(R.id.user_account_covidcheck);
        OrderNow = findViewById(R.id.user_account_ordernow);
        menuChnageRequest = findViewById(R.id.user_account_menuchange);
        cancelOrder = findViewById(R.id.user_account_cancelorder);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UserAccount.this)
                        .setTitle("Exit")
                        .setMessage("Do you want to exit")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(UserAccount.this,MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();

            }
        });

        ViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccount.this,MenuDetails.class);
                startActivity(intent);
            }
        });

        CovidHygenicCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccount.this, CovidHygieneCheck.class);
                startActivity(intent);
            }
        });
        OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccount.this,OrderNow.class);
                startActivity(intent);
            }
        });
        menuChnageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccount.this,MenuChangeRequest.class);
                startActivity(intent);
            }
        });

        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccount.this,CancleOrder.class);
                startActivity(intent);
            }
        });


    }
}