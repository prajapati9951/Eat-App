package com.example.example.ViewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.Interface.ItemClickListener;
import com.example.example.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textView_menu;
    public ImageView imageView_menu;

    private ItemClickListener itemClickListener;
    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_menu=(TextView) itemView.findViewById(R.id.textView_menu);
        imageView_menu=(ImageView) itemView.findViewById(R.id.image_menu);
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
