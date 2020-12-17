package com.example.appquanli12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appquanli12.Interface.ItemClickListener;
import com.example.appquanli12.Model.Bill;
import com.example.appquanli12.Model.Category;
import com.example.appquanli12.Model.Order;
import com.example.appquanli12.Model.SpacesItemDecoration;
import com.example.appquanli12.ViewHolder.BillViewHolder;
import com.example.appquanli12.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class BillDateTime extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference bill;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Bill, BillViewHolder> adapter;

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


        FirebaseRecyclerOptions<Bill> options= new FirebaseRecyclerOptions.Builder<Bill>().setQuery(bill,Bill.class).build();
        adapter = new FirebaseRecyclerAdapter<Bill, BillViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BillViewHolder holder, int position, @NonNull Bill model) {
                holder.txtDate.setText(model.getDate());
                holder.txtTotal.setText(model.getTotal());
                final Bill clickItem = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

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