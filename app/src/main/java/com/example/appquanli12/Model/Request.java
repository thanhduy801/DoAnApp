package com.example.appquanli12.Model;

import java.util.List;

public class Request {
    private String total;
    private List<Order> foods;

    public Request() {
    }

    public Request(String total, List<Order> foods) {
        this.total = total;
        this.foods = foods;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
