package com.example.appquanli12.Model;

public class Bill {
    String date;
    String total;

    public Bill() {
    }

    public Bill(String date, String total) {
        this.date = date;
        this.total = total;
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
