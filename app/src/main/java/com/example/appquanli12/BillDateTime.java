package com.example.appquanli12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appquanli12.Common.Common;
import com.example.appquanli12.Interface.ItemClickListener;
import com.example.appquanli12.Model.Request;
import com.example.appquanli12.ViewHolder.BillViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BillDateTime extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference bill;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request,BillViewHolder > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_date_time);

        //back toolbar
        Toolbar toolbar=findViewById(R.id.toolbar_bill);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hoá đơn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Chèn một kẻ ngang giữa các phần tử
        /*DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerHorizontal);*/

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        bill = database.getReference("Requests");

        //Load menu
        recyclerView = (RecyclerView) findViewById(R.id.recycler_bill);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        FirebaseRecyclerOptions<Request> options= new FirebaseRecyclerOptions.Builder<Request>().setQuery(bill, Request.class).build();
        adapter = new FirebaseRecyclerAdapter<Request, BillViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BillViewHolder holder, int position, @NonNull final Request model) {
                holder.txtbillid.setText(adapter.getRef(position).getKey());
                holder.txtDate.setText(model.getDate());
                holder.txtTotal.setText(model.getTotal());
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent orderDetail =new Intent(BillDateTime.this,OrderDetail.class);
                        Common.currentRequest =model;
                        orderDetail.putExtra("OrderId",adapter.getRef(position).getKey());
                        startActivity(orderDetail);
                    }
                });

            }

            @NonNull
            @Override
            public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item,parent, false);
                BillViewHolder viewHolder=  new BillViewHolder(view);
                return viewHolder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.startListening();

    }

}