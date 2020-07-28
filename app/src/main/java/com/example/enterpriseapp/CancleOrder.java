package com.example.enterpriseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CancleOrder extends AppCompatActivity {

    Button submit, viewlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancle_order);

        submit = findViewById(R.id.cancel_order_request_submit);
        viewlist = findViewById(R.id.cancel_order_request_viewlist);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CancleOrder.this, "Submitted!!", Toast.LENGTH_SHORT).show();
            }
        });

        viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CancleOrder.this,CancelOrderDetails.class);
                startActivity(intent);
            }
        });
    }
}