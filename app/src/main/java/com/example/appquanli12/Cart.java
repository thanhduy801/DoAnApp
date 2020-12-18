package com.example.appquanli12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanli12.Common.Common;
import com.example.appquanli12.Database.Database;
import com.example.appquanli12.Model.Order;
import com.example.appquanli12.Model.Request;
import com.example.appquanli12.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {
    private static DateFormat simpleDate;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference reference;

    TextView txtTotalPrice,datetime;
    Button btnPlace;

    ImageView add_new;

    List<Order> cart=new ArrayList<>();
    CartAdapter adapter;


    //time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //back toolbar
        Toolbar toolbar=findViewById(R.id.toolbar_cart);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đặt món ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      database=FirebaseDatabase.getInstance();
      reference=database.getReference("Requests");

      recyclerView =(RecyclerView) findViewById(R.id.listCart);
      recyclerView.setHasFixedSize(true);
      layoutManager=new LinearLayoutManager(this);
      recyclerView.setLayoutManager(layoutManager);

      txtTotalPrice=(TextView)findViewById(R.id.total);
      datetime=(TextView)findViewById(R.id.date);
      btnPlace=(Button)findViewById(R.id.btnPlaceOrder);
      add_new=(ImageView) findViewById(R.id.add_new);
        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent= new Intent(Cart.this,food_category.class);
                startActivity(cartIntent);
            }
        });

      btnPlace.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Request request=new Request(
                      txtTotalPrice.getText().toString(),
                      datetime.getText().toString(),
                      cart
              );
              reference.child(String.valueOf(System.currentTimeMillis()))
                      .setValue(request);
              new Database(getBaseContext()).cleanCart();
              Toast.makeText(Cart.this, "Đã thanh toán.", Toast.LENGTH_SHORT).show();
              finish();

          }
      });
      
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

        /*Calendar c = Calendar.getInstance();
        int date = c.get(Calendar.MONTH);
        String a=String.valueOf(date);
        datetime.setText(a);*/

        DateFormat df = new SimpleDateFormat("dd:MM:yyyy");
        String strDate = df.format(new Date());
        datetime.setText(strDate);
    }
   
}