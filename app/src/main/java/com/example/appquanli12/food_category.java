package com.example.appquanli12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andremion.counterfab.CounterFab;
import com.example.appquanli12.Database.Database;
import com.example.appquanli12.Interface.ItemClickListener;
import com.example.appquanli12.Model.Category;
import com.example.appquanli12.Model.SpacesItemDecoration;
import com.example.appquanli12.ViewHolder.CategoryViewHolder;
import com.example.appquanli12.ViewHolder.SlidingImage_Adapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class food_category extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference category;

    RecyclerView recyclercategory;

    CounterFab fab2;

    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    //banner
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.fourt,R.drawable.fire,R.drawable.six};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);

        //back toolbar
        Toolbar toolbar=findViewById(R.id.toolbar_category);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Lựa chọn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab2 =(CounterFab) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent= new Intent(food_category.this,Cart.class);
                startActivity(cartIntent);
            }
        });

        fab2.setCount(new Database(this).getCountCart());


        //Init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        //Load menu
        recyclercategory = (RecyclerView) findViewById(R.id.recycler_category);
        recyclercategory.setHasFixedSize(true);
        recyclercategory.setLayoutManager(new GridLayoutManager(this,2));
        recyclercategory.addItemDecoration(new SpacesItemDecoration(15));


        //banner
        init();

        FirebaseRecyclerOptions<Category> options= new FirebaseRecyclerOptions.Builder<Category>().setQuery(category,Category.class).build();
        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {
                holder.txtCategoryName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImg()).into(holder.imgcategory);
                final Category clickItem = model;
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent foodList=new Intent(food_category.this,Food_Menu.class);
                        foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                    }
                });

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent, false);
                CategoryViewHolder viewHolder=  new CategoryViewHolder(view);
                return viewHolder;
            }
        };


        recyclercategory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.startListening();

    }


    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(food_category.this,ImagesArray));
        ;

        final float density = getResources().getDisplayMetrics().density;

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fab2.setCount(new Database(this).getCountCart());

        if (adapter!=null){
            adapter.startListening();
        }
    }
}
