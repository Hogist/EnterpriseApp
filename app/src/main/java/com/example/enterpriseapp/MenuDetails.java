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

public class MenuDetails extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnclickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        myOnclickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.menu_details_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for(int i =0;i<DataSet.FoodItem.length;i++){
            data.add(new DataModel(
                    DataSet.FoodItem[i],DataSet.VendorName[i],DataSet.EnterprisePrice[i]
            ));
        }

        adapter = new MenuAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    static class MyOnClickListener implements View.OnClickListener{
        private final Context context;
        MyOnClickListener(Context context){
            this.context = context;
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "item Clicked!!", Toast.LENGTH_SHORT).show();
        }
    }

}