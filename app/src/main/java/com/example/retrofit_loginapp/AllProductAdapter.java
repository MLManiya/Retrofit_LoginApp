package com.example.retrofit_loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_loginapp.Modal.Productdatum;
import com.example.retrofit_loginapp.ui.home.HomeFragment;

import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter {
    HomeFragment homeFragment;
    List<Productdatum> productdata;
    public AllProductAdapter(HomeFragment homeFragment, List<Productdatum> productdata) {
        this.homeFragment = homeFragment;
        this.productdata = productdata;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_slideshow_itemfile,parent,false);
        adapter holder = new adapter(view);
        holder.more.setVisibility(View.INVISIBLE);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }
    public class adapter extends RecyclerView.ViewHolder {
        ImageView imageView,more;
        TextView item,price,category;

        public adapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rec_img);
            item = itemView.findViewById(R.id.rec_itemname);
            price = itemView.findViewById(R.id.rec_price);
            category = itemView.findViewById(R.id.rec_desc);
            more = itemView.findViewById(R.id.rec_more);
        }
    }
}
