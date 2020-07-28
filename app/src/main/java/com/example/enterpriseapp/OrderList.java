package com.example.enterpriseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {


    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<OrderListDataModel> data;
    static View.OnClickListener myOnclickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        myOnclickListener = new MyOnClickListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.orderlist_recyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<OrderListDataModel>();
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));
        data.add(new OrderListDataModel("Order ID","Vendor Name","Quantity","Order Status"));

        adapter = new OrderlistAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    static class MyOnClickListener implements View.OnClickListener{
        private final Context context;

        public MyOnClickListener(Context context) {
            this.context = context;
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Item Clicked!!", Toast.LENGTH_SHORT).show();
        }
    }

}