package com.example.appquanli12.Model;

public class Bill {

    String billId;
    String date;
    String total;

    public Bill() {
    }

    public Bill(String billId, String date, String total) {
        this.billId = billId;
        this.date = date;
        this.total = total;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
