package com.example.appquanli12.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanli12.Model.Order;
import com.example.appquanli12.R;

import java.util.List;

class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView name,quantity,price,discount;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.productName);
        quantity=(TextView)itemView.findViewById(R.id.productQuantity);
        price=(TextView)itemView.findViewById(R.id.productPrice);
        discount=(TextView)itemView.findViewById(R.id.productDiscount);
    }
}
public class OrderDetailAdapter extends RecyclerView.Adapter<MyViewHolder>{

    List<Order> myOrders;

    public OrderDetailAdapter(List<Order> myOrders) {
        this.myOrders = myOrders;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Order order=myOrders.get(position);
        holder.name.setText(order.getProductName());
        holder.quantity.setText(order.getQuantity());
        holder.price.setText(order.getPrice());
        holder.discount.setText(order.getDiscount());
    }

    @Override
    public int getItemCount() {
        return myOrders.size();
    }
}
