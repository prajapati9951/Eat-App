package com.example.example;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;

import com.example.example.Common.Common;
import com.example.example.Interface.ItemClickListener;
import com.example.example.Model.Category;
import com.example.example.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtFullName;
    RecyclerView recyler_menu;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference category;
    FirebaseDatabase database;
     FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
//Init firebase

        database=FirebaseDatabase.getInstance();
        category=database.getReference().child("Category");
        //SetNAme

        View headerView=navigationView.getHeaderView(0);
        txtFullName=headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentUser.getName());

        recyler_menu=findViewById(R.id.recycler_menu);
        recyler_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyler_menu.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadMenu();
    }

    private void loadMenu() {
/*

        FirebaseRecyclerOptions<Category> options =
                new FirebaseRecyclerOptions.Builder<Category>()
                        .setQuery(category, Category.class)
                        .build();

        FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter=new FirebaseRecyclerAdapter<Category, MenuViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i, @NonNull Category category) {
                Picasso.with(getBaseContext()).load(category.getImage())
                        .into(menuViewHolder.imageView_menu);
                final Category clickItem=category;

                menuViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(HomeActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();

                    }
                });
                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClcik) {
                        Toast.makeText(HomeActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @Override
            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
                MenuViewHolder holder = new MenuViewHolder(view);
                return holder;
            }
        };

        recyler_menu.setAdapter(adapter);
        adapter.startListening();;*/
      adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class, R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, Category category, int i) {
                Picasso.with(getBaseContext()).load(category.getImage())
                        .into(menuViewHolder.imageView_menu);
                menuViewHolder.textView_menu.setText(category.getName());
                final Category clickItem = category;
        /*        menuViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent foodList= new Intent(HomeActivity.this,FoodListActivity.class);
                       // foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                        Toast.makeText(HomeActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                });*/

                menuViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClcik) {
                        Intent foodList= new Intent(HomeActivity.this,FoodListActivity.class);
                        foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                        Toast.makeText(HomeActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                   }
                });
            }

        };

        recyler_menu.setAdapter(adapter);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            // Handle the camera action
     /*   } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {*/

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
