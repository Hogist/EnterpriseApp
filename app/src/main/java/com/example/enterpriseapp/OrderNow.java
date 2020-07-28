package com.example.enterpriseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderNow extends AppCompatActivity {

    Button viewOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);

        viewOrderList = findViewById(R.id.order_now_vieworderlist);
        viewOrderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderNow.this,OrderList.class);
                startActivity(intent);
            }
        });
    }
}