package com.example.enterpriseapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CancelOrderDetailsAdapter  extends RecyclerView.Adapter<CancelOrderDetailsAdapter.MyViewHolder> {

    private ArrayList<CancelOrderRequestDataModel> dataModels;

    public CancelOrderDetailsAdapter(ArrayList<CancelOrderRequestDataModel> dataModels) {
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public CancelOrderDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cancel_order_request_cardview,parent,false);
        view.setOnClickListener(MenuRequestDetails.myOnclickListener);
        CancelOrderDetailsAdapter.MyViewHolder myViewHolder = new CancelOrderDetailsAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CancelOrderDetailsAdapter.MyViewHolder holder, int position) {


        TextView RequestID = holder.Requestid;
        TextView VendorName = holder.Vendorname;
        TextView Reason  = holder.Reason;
        TextView NewMenu = holder.Newmenu;
        TextView Status = holder.Status;

        RequestID.setText(dataModels.get(position).getRequestId());
        VendorName.setText(dataModels.get(position).getVendorName());
        Reason.setText(dataModels.get(position).getReason());
        NewMenu.setText(dataModels.get(position).getNewMenu());
        Status.setText(dataModels.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Requestid, Vendorname, Reason, Newmenu, Status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.Requestid = (TextView)itemView.findViewById(R.id.menu_change_cardview_Requestid);
            this.Vendorname = (TextView)itemView.findViewById(R.id.menu_change_cardview_vendorname);
            this.Reason = (TextView)itemView.findViewById(R.id.menu_change_cardview_reason);
            this.Newmenu = (TextView)itemView.findViewById(R.id.menu_change_cardview_newmenu);
            this.Status = (TextView)itemView.findViewById(R.id.menu_change_cardview_status);

        }
    }
}
