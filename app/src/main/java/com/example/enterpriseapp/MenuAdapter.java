package com.example.enterpriseapp;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataModels;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView BreakfastItem, VendorName, EnterprisePrice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.BreakfastItem= (TextView)itemView.findViewById(R.id.menu_cardview_breakfastitem);
            this.VendorName = (TextView)itemView.findViewById(R.id.menu_cardview_vendorname);
            this.EnterprisePrice = (TextView)itemView.findViewById(R.id.menu_cardview_enterpriseprice);
        }
    }

    public MenuAdapter(ArrayList<DataModel> data){
        this.dataModels = data;
    }

    @NonNull
    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_details_cardview,parent,false);
        view.setOnClickListener(MenuDetails.myOnclickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {

        TextView FoodItem = holder.BreakfastItem;
        TextView VendorName= holder.VendorName;
        TextView EnterprisePrice = holder.EnterprisePrice;


        FoodItem.setText(dataModels.get(position).getFoodItem());
        VendorName.setText(dataModels.get(position).getVendorName());
        EnterprisePrice.setText(dataModels.get(position).getEnterprisePrice());

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}
