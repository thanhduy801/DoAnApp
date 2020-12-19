package com.example.appquanli12.Model;

public class Bill {

    String billId;
    String date;
    String total;
    String foods;

    public Bill() {
    }

    public Bill(String billId, String date, String total, String foods) {
        this.billId = billId;
        this.date = date;
        this.total = total;
        this.foods = foods;
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

    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }
}
