package com.example.appquanli12;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanli12.Interface.ItemClickListener;
import com.example.appquanli12.Model.Category;
import com.example.appquanli12.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.common.collect.Table;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;

    RelativeLayout layout_order, layout_food,layout_profile,layout_revenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anhsa
        layout_food=(RelativeLayout)findViewById(R.id.layout_food);
        layout_profile=(RelativeLayout)findViewById(R.id.layout_profile);
        layout_order=(RelativeLayout)findViewById(R.id.layout_order);
        layout_revenue=(RelativeLayout)findViewById(R.id.layout_revenue);

        //bat su kien
        layout_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodList=new Intent(MainActivity.this,food_category.class);
                startActivity(foodList);
            }
        });
        layout_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodList=new Intent(MainActivity.this,SettingCategory.class);
                startActivity(foodList);
            }
        });
        layout_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foodList=new Intent(MainActivity.this,Profile.class);
                startActivity(foodList);
            }
        });
        layout_revenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent foodList=new Intent(MainActivity.this,BillDateTime.class);
                startActivity(foodList);

            }
        });

        /*---------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Nhà");
        /*---------------------*/
        setSupportActionBar(toolbar);
        /*---------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                 R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);



    }

//button meu mang hinh trai
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }
    /*---mang hinh trai--*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:

                break;

            case R.id.nav_order:
                Intent intent0 = new Intent(MainActivity.this,food_category.class);
                startActivity(intent0);
                break;

            case R.id.nav_list_food:
                Intent intent = new Intent(MainActivity.this,SettingCategory.class);
                startActivity(intent);
                break;

            case R.id.nav_list_danhthu:
                Intent intent2 = new Intent(MainActivity.this,BillDateTime.class);
                startActivity(intent2);
                break;

            case R.id.nav_profile:
                Intent intent1 = new Intent(MainActivity.this,Profile.class);
                startActivity(intent1);
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}