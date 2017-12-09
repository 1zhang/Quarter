package com.example.quarter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.quarter.R;

import java.util.List;

/**
 * Created by 设计风格 on 2017/11/30.
 */

public class Gadapter extends RecyclerView.Adapter<Gadapter.ViewHolder>{
    private List<String> list2;
    private Context con;
    public Gadapter(Context con,List<String> list2){
        this.con = con;
        this.list2 = list2;
    }

    @Override
    public Gadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.gridview, null);
        Gadapter.ViewHolder h = new Gadapter.ViewHolder(view);
        return h;
    }

    @Override
    public void onBindViewHolder(Gadapter.ViewHolder holder, int position) {
             Glide.with(con).load(list2.get(position)).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
private ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.dz_iv);
        }
    }
}

