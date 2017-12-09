package com.example.quarter.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quarter.Article;
import com.example.quarter.MyShare;
import com.example.quarter.R;
import com.example.quarter.bean.CrossBean;
import com.example.quarter.mdata.AtData;
import com.example.quarter.plActivity;
import com.example.quarter.presenter.AtPrecenter;
import com.example.quarter.presenter.PlPresenter;
import com.example.quarter.presenter.Praise;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.BaseBean;
import retrofit2.Response;


/**
 * Created by 设计风格 on 2017/11/28.
 */

public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<CrossBean> list;
    private Context context;
    private AtPrecenter atprecenter;
    private Praise p;
    private List<String> list1 ;
    Map<Integer,Boolean> map = new HashMap<>();
    private SharedPreferences sp;
    public MyAdapter(Context context,List<CrossBean> list) {

        this.list = list;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.cross_adapter_item, null);
        ViewHolder h = new ViewHolder(view);
        return h;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        final int jid = list.get(position).jid;
        if(map.get(position)==null){
          map.put(position,true) ;
        }
        sp = context.getSharedPreferences("Token",Context.MODE_PRIVATE);
        final String token = sp.getString("token", "");
        final String uid = sp.getString("uid", "");
        atprecenter = new AtPrecenter(context, new AtData() {
            @Override
            public void chenggong(Response<BaseBean> msg) {
                if(msg.body().msg.equals("token超时")){
                    Toast.makeText(context,"请登录后关注",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void shibai(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cuowu(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
        p = new Praise(context, new AtData() {
            @Override
            public void chenggong(Response<BaseBean> msg) {
             Toast.makeText(context,msg.body().msg,Toast.LENGTH_SHORT).show();
                holder.xin_tv.setText(list.get(position).praiseNum+"");
                System.out.println("list.get(position).user.fans = " + list.get(position).user.fans);

                map.put(position,true);
                notifyDataSetChanged();
            }

            @Override
            public void shibai(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cuowu(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv.setText(list.get(position).content);
        CrossBean crossBean = list.get(position);
        String s = crossBean.user.nickname;
        holder.tv1.setText(s);
        String icon = crossBean.user.icon;
        if(icon!=null) {
            Uri uri = Uri.parse(icon);
            holder.iv.setImageURI(uri);
        }

        holder.tv2.setText(list.get(position).createTime);
          holder.add.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (map.get(position)) {
                      ObjectAnimator animator = ObjectAnimator.ofFloat(holder.add, "rotation", 0f, 360f);
                      animator.setDuration(800);
                      animator.start();
                      holder.add.setImageResource(R.drawable.subtract);
                      float x = holder.lping.getTranslationX();
                      float x1 = holder.lshar.getTranslationX();
                      float x2 = holder.lxin.getTranslationX();
                      ObjectAnimator om =  ObjectAnimator.ofFloat(holder.lping, "translationX", x, -100f);
                      ObjectAnimator om1 = ObjectAnimator.ofFloat(holder.lshar, "translationX", x1, -200f);
                      ObjectAnimator om2 = ObjectAnimator.ofFloat(holder.lxin, "translationX", x2, -300f);
                      om.setDuration(800);
                      om1.setDuration(800);
                      om2.setDuration(800);
                      holder.ping_tv.setVisibility(View.VISIBLE);
                      holder.shar_tv.setVisibility(View.VISIBLE);
                      holder.xin_tv.setVisibility(View.VISIBLE);
                      om.start();
                      om1.start();
                      om2.start();
                      map.put(position,false);
                  }
                  else if(map.get(position)==false){
                      ObjectAnimator animator = ObjectAnimator.ofFloat(holder.add, "rotation", 0f, 360f);
                      animator.setDuration(800);
                      animator.start();
                      holder.add.setImageResource(R.drawable.add);
                      float x = holder.lping.getTranslationX();
                      float x1 = holder.lshar.getTranslationX();
                      float x2 = holder.lxin.getTranslationX();
                      ObjectAnimator om = ObjectAnimator.ofFloat(holder.lping, "translationX", x, 0f);
                      ObjectAnimator om1 = ObjectAnimator.ofFloat(holder.lshar, "translationX", x1, 0f);
                      ObjectAnimator om2 = ObjectAnimator.ofFloat(holder.lxin, "translationX", x2, 0f);
                      om.setDuration(800);
                      om1.setDuration(800);
                      om2.setDuration(800);
                      holder.ping_tv.setVisibility(View.GONE);
                      holder.shar_tv.setVisibility(View.GONE);
                      holder.xin_tv.setVisibility(View.GONE);
                      om.start();
                      om1.start();
                      om2.start();
                      map.put(position,true);
                  }
              }
          });
        holder.shar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, MyShare.class);
                context.startActivity(in);
            }
        });

        holder.xin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            atprecenter.getatdata(uid,list.get(position).uid+"",token);
               // p.praise(uid,list.get(position).jid+"",token);
            }
        });

        String imgUrls = (String) list.get(position).imgUrls;
        GridLayoutManager gm = new GridLayoutManager(context,3);
        holder.gv.setLayoutManager(gm);
        if(imgUrls!=null) {
            String[] split = imgUrls.split("\\|");
            list1 = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                list1.add(split[i]);
            }
            System.out.println("split = " + split);
            holder.gv.setAdapter(new Gadapter(context,list1));
        }

          holder.ping.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent in = new Intent(context,plActivity.class);
                  in.putExtra("uid",uid);
                  in.putExtra("wid",jid+"");
                  in.putExtra("token",token);
                  context.startActivity(in);
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends XRecyclerView.ViewHolder{
        private TextView tv,tv1,add_tv,ping_tv,shar_tv,xin_tv,tv2,tv_p;
        private ImageView iv,add,ping,shar,xin,b;
        private LinearLayout ladd,lping,lshar,lxin;
        private RecyclerView gv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.sdv);
            add = itemView.findViewById(R.id.addd_iv);
            shar = itemView.findViewById(R.id.shar_iv);
            xin = itemView.findViewById(R.id.xin_iv);
            ping = itemView.findViewById(R.id.ping_iv);
            ping_tv = itemView.findViewById(R.id.ping_tv);
            add_tv = itemView.findViewById(R.id.add_tv);
            shar_tv = itemView.findViewById(R.id.shar_tv);
            xin_tv = itemView.findViewById(R.id.xin_tv);
            lxin = itemView.findViewById(R.id.xin);
            lshar = itemView.findViewById(R.id.shar);
            lping = itemView.findViewById(R.id.ping);
            ladd = itemView.findViewById(R.id.add);
            tv=itemView.findViewById(R.id.cross_content);
            tv1=itemView.findViewById(R.id.nickname_tv);
            tv2=itemView.findViewById(R.id.tv);
            gv = itemView.findViewById(R.id.gridView1);
            tv_p = itemView.findViewById(R.id.tv_p);

        }
    }


}
