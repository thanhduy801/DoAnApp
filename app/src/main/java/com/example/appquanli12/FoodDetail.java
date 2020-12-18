package com.example.appquanli12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.appquanli12.Database.Database;
import com.example.appquanli12.Model.Food;
import com.example.appquanli12.Model.Order;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    TextView food_name,food_price;
    ImageView food_image,imageView7;
    CounterFab shopping;
    Button btnCart;
    ElegantNumberButton numberButton;

    String foodId="";

    FirebaseDatabase database;
    DatabaseReference foods;
    Food currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        shopping=(CounterFab)findViewById(R.id.imageView2);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent= new Intent(FoodDetail.this,Cart.class);
                startActivity(cartIntent);
            }
        });

        //toolbar
        Toolbar toolbar=findViewById(R.id.toolbar_food_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi tiết");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Firebase

        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Foods");

        imageView7=(ImageView) findViewById(R.id.imageView7);
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FoodDetail.this, "Đã yêu thích!!!", Toast.LENGTH_SHORT).show();

            }
        });


        //Init view
        numberButton=(ElegantNumberButton)findViewById(R.id.number_button);
        btnCart=(Button) findViewById(R.id.btnCart);

        //final Database helper=new Database(this);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscount()

                ));
                Toast.makeText(FoodDetail.this, "Đã thêm món.", Toast.LENGTH_SHORT).show();
            }
        });

        shopping.setCount(new Database(this).getCountCart());


        food_name=(TextView)findViewById(R.id.food_name);
        food_price=(TextView)findViewById(R.id.food_price);
        food_image=(ImageView)findViewById(R.id.img_food);

        //Get Food id from Intent
        if(getIntent()!= null)
            foodId=getIntent().getStringExtra("FoodId");
        if(!foodId.isEmpty()){
        getDetailFood(foodId);
        }


    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                currentFood=snapshot.getValue(Food.class);
                //set image
                //Picasso.with(getBaseContext()).load(currentFood.getImg()).into(food_image);
                food_price.setText(currentFood.getPrice());
                food_name.setText(currentFood.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}