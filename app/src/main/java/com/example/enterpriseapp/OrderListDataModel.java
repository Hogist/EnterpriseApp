package com.example.enterpriseapp;

import android.widget.TextView;

public class OrderListDataModel {

    String OrderID;
    String VendorName;
    String Quantity;
    String OrderStatus;

    public OrderListDataModel(String orderID, String vendorName, String quantity, String orderStatus) {
        OrderID = orderID;
        VendorName = vendorName;
        Quantity = quantity;
        OrderStatus = orderStatus;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
}
