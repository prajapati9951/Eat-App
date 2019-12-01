package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.example.Interface.ItemClickListener;
import com.example.example.Model.Category;
import com.example.example.Model.Food;
import com.example.example.ViewHolder.FoodViewHolder;
import com.example.example.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodListActivity extends AppCompatActivity {

    RecyclerView recyler_food;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference foodlist;
    FirebaseDatabase database;
    String CategoryId="";
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        database=FirebaseDatabase.getInstance();
        foodlist=database.getReference().child("Foods");

        recyler_food=findViewById(R.id.recycler_food);
        recyler_food.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyler_food.setLayoutManager(layoutManager);
        if(getIntent()!=null)
        {

            CategoryId=getIntent().getStringExtra("CategoryId");
            if(!CategoryId.isEmpty()) {
                loadFood(CategoryId);
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getIntent()!=null)
        {
            CategoryId=getIntent().getStringExtra("CategoryId");
            loadFood(CategoryId);
        }



    }

    private void loadFood(String categoryId) {

        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.food_item,FoodViewHolder.class,
                foodlist.orderByChild("menuId").equalTo(categoryId))
                //select * from Foods where menuid=---
        {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(foodViewHolder.imageView_food);
                foodViewHolder.textView_food.setText(food.getName());
                final Food clickItem = food;
                foodViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClcik) {

                        Intent i=new Intent(FoodListActivity.this,FoodDeatils.class);
                        i.putExtra("FoodId",adapter.getRef(position).getKey());
                        startActivity(i);
                    }
                });
            }

        };

        recyler_food.setAdapter(adapter);


    }
}


