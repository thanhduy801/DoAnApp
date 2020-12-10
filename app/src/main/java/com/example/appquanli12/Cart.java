package com.example.appquanli12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.appquanli12.Database.Database;
import com.example.appquanli12.Model.Order;
import com.example.appquanli12.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference reference;

    TextView txtTotalPrice;
    Button btnPlace;

    List<Order> cart=new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


      database=FirebaseDatabase.getInstance();
      reference=database.getReference("Requests");

      recyclerView =(RecyclerView) findViewById(R.id.listCart);
      recyclerView.setHasFixedSize(true);
      layoutManager=new LinearLayoutManager(this);
      recyclerView.setLayoutManager(layoutManager);

      txtTotalPrice=(TextView)findViewById(R.id.total);
      btnPlace=(Button)findViewById(R.id.btnPlaceOrder);
      
      loadListFood();
    }

    private void loadListFood() {
        cart=new Database(this).getCarts();
        adapter=new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

        int total =0;
        for(Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        Locale locale=new Locale("en","US");
        NumberFormat fmt =NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));
    }
}