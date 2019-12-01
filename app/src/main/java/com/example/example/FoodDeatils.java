package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.example.Model.Food;
import com.example.example.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDeatils extends AppCompatActivity {


    TextView food_Name,food_Price,food_Description;
    ImageView food_Image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ElegantNumberButton idnumber;
    FloatingActionButton btnCart;
    String FoodId="";
    DatabaseReference food;
    FirebaseDatabase database;
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_deatils);

        food_Name=findViewById(R.id.food_name);
        food_Price=findViewById(R.id.food_price);
        food_Description=findViewById(R.id.food_description);
        idnumber=findViewById(R.id.number_btn);
        btnCart=findViewById(R.id.fabCart);

        food_Image=findViewById(R.id.food_image);
        collapsingToolbarLayout=findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);

        database=FirebaseDatabase.getInstance();
        food=database.getReference().child("Foods");


        if(getIntent()!=null)
        {

            FoodId=getIntent().getStringExtra("FoodId");
            if(!FoodId.isEmpty()) {
                loadFood(FoodId);
            }
        }
    }

    private void loadFood(String foodId) {

        food.child(FoodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Food food=dataSnapshot.getValue(Food.class);
                Picasso.with(getApplicationContext()).load(food.getImage())
                        .into(food_Image);
                collapsingToolbarLayout.setTitle(food.getName());
                food_Price.setText(food.getPrice());
                food_Description.setText(food.getDescription());
                food_Name.setText(food.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
