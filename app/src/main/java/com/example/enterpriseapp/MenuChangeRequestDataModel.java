package com.example.enterpriseapp;

public class MenuChangeRequestDataModel {

    String RequestId;
    String VendorName;
    String Reason;
    String NewMenu;
    String Status;

    public MenuChangeRequestDataModel(String requestId, String vendorName, String reason, String newMenu, String status) {
        RequestId = requestId;
        VendorName = vendorName;
        Reason = reason;
        NewMenu = newMenu;
        Status = status;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getNewMenu() {
        return NewMenu;
    }

    public void setNewMenu(String newMenu) {
        NewMenu = newMenu;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
