package com.example.enterpriseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserAccount extends AppCompatActivity {

    Button ViewMenu, CovidHygenicCheck,OrderNow,menuChnageRequest,cancelOrder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        ViewMenu = findViewById(R.id.user_account_viewmenu);
        CovidHygenicCheck = findViewById(R.id.user_account_covidcheck);
        OrderNow = findViewById(R.id.user_account_ordernow);
        menuChnageRequest = findViewById(R.id.user_account_menuchange);
        cancelOrder = findViewById(R.id.user_account_cancelorder);

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