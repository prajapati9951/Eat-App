package com.example.example.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.Interface.ItemClickListener;
import com.example.example.R;




public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView_food;
    public ImageView imageView_food;

    private ItemClickListener itemClickListener;
    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_food=(TextView) itemView.findViewById(R.id.textView_food);
        imageView_food=(ImageView) itemView.findViewById(R.id.image_food);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

