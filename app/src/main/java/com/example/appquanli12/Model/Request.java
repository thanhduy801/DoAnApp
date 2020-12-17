package com.example.appquanli12.Model;

import java.util.List;

public class Request {
    private String total;
    private String date;
    private List<Order> foods;

    public Request() {
    }

    public Request(String total, String date, List<Order> foods) {
        this.total = total;
        this.date = date;
        this.foods = foods;
    }

    public Request(String total, List<Order> cart, String toString) {
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
