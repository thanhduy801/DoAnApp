package com.example.appquanli12.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanli12.Interface.ItemClickListener;
import com.example.appquanli12.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtFoodName;
    public TextView txtFoodMoney;
    public ImageView imgfood,quick_cart;
    private ItemClickListener itemClickListener;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        txtFoodName =(TextView)itemView.findViewById(R.id.food_name);
        txtFoodMoney =(TextView)itemView.findViewById(R.id.food_money);
        imgfood =(ImageView) itemView.findViewById(R.id.food_img);
        quick_cart =(ImageView) itemView.findViewById(R.id.btn_add_food);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}

