package com.example.appquanli12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.appquanli12.Common.Common;
import com.example.appquanli12.ViewHolder.OrderDetailAdapter;

public class OrderDetail extends AppCompatActivity {

    TextView order_id,order_date,order_total;
    String order_id_value="";
    RecyclerView lstFoods;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        //back toolbar
        Toolbar toolbar=findViewById(R.id.toolbar_category);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chi tiết hoá đơn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        order_id=(TextView)findViewById(R.id.order_id);
        order_date=(TextView)findViewById(R.id.order_date);
        order_total=(TextView)findViewById(R.id.order_total);

        lstFoods=(RecyclerView)findViewById(R.id.lstFoods);
        lstFoods.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        lstFoods.setLayoutManager(layoutManager);

        if(getIntent()!=null){
            order_id_value=getIntent().getStringExtra("OrderId");

            order_id.setText(order_id_value);
            order_date.setText(com.example.appquanli12.Common.Common.currentRequest.getDate());
            order_total.setText(com.example.appquanli12.Common.Common.currentRequest.getTotal());

            OrderDetailAdapter adapter=new OrderDetailAdapter(Common.currentRequest.getFoods());
            adapter.notifyDataSetChanged();
            lstFoods.setAdapter(adapter);
        }
    }
}