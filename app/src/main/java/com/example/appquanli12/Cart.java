package com.example.appquanli12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference reference;

    TextView txtTotalPrice;
    Button btnPlace;

    String year,month,day;

    List<Order> cart=new ArrayList<>();
    CartAdapter adapter;
    int tien;
    int doanhthu[];
    //time


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //time




      database=FirebaseDatabase.getInstance();
      reference=database.getReference("Requests");

      recyclerView =(RecyclerView) findViewById(R.id.listCart);
      recyclerView.setHasFixedSize(true);
      layoutManager=new LinearLayoutManager(this);
      recyclerView.setLayoutManager(layoutManager);

      txtTotalPrice=(TextView)findViewById(R.id.total);
      btnPlace=(Button)findViewById(R.id.btnPlaceOrder);

      btnPlace.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Request request=new Request(
                      txtTotalPrice.getText().toString(),
                      cart
              );
              reference.child(String.valueOf(System.currentTimeMillis()))
                      .setValue(request);
              new Database(getBaseContext()).cleanCart();
              Toast.makeText(Cart.this, "Thank you. Order placed", Toast.LENGTH_SHORT).show();
              //finish();

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
    }

    private void timeday(){

        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DATE);

        year = String.valueOf(y);
        month = String.valueOf(m);
        day = String.valueOf(d);

    }
  /*  private void Doanhthu()
    {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DATE);
        String y1 = getString(y);
        String m1 = getString(m);
        String d1 = getString(d);
        timeday();
    for (int i=1; i<=12 ; i++)
    {
        if ((y1==year) && (m == i)&&(d1 == day)) {doanhthu[i] +=tien;
    }

    }
}*/
}