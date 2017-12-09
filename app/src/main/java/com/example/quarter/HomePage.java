package com.example.quarter;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.mfragment.CrossTalkFragment;
import com.example.quarter.mfragment.RecommendFragment;
import com.example.quarter.mfragment.VideoFragment;
import com.example.quarter.slidingmenufragment.SlidingmenuFragment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kson.slidingmenu.SlidingMenu;

public class HomePage extends AppCompatActivity  {
    private LinearLayout recommend_ll,cross_talk,video_ll;
    private ImageView recommend_iv,cross_talk_iv , video_iv,compile_iv;
    private TextView recommend_tv,cross_talk_tv,video_tv,headline_tv;
    private SlidingMenu sm;
    private SimpleDraweeView my_img;
    private RecommendFragment recommendFragment;
    private CrossTalkFragment crossTalkFragment;
    private VideoFragment videoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initView();

        Uri uri =  Uri.parse("res://"+this.getPackageName()+"/"+R.drawable.tou);
        my_img.setImageURI(uri);
        sm = new SlidingMenu(this);
        sm.setMenu(R.layout.slidingmenu_item);
        getSupportFragmentManager().beginTransaction().replace(R.id.seliding_f,new SlidingmenuFragment()).commit();
        sm.setMode(SlidingMenu.LEFT);
        sm.setBehindOffsetRes(R.dimen.YK);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        sm.attachToActivity(this, SlidingMenu.LEFT);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl,recommendFragment)
                .add(R.id.fl,crossTalkFragment)
                .add(R.id.fl,videoFragment)
                .commit();
        getSupportFragmentManager().beginTransaction().hide(crossTalkFragment)
                .hide(videoFragment)
                .show(recommendFragment).commit();
        recommend_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getSupportFragmentManager().beginTransaction().hide(crossTalkFragment)
                       .hide(videoFragment)
                       .show(recommendFragment).commit();
                recommend_iv.setImageResource(R.drawable.raw_select);
                cross_talk_iv.setImageResource(R.drawable.cro_select);
                video_iv.setImageResource(R.drawable.video_unselect);
                recommend_tv.setTextColor(Color.parseColor("#03A9F4"));
                cross_talk_tv.setTextColor(Color.GRAY);
                video_tv.setTextColor(Color.GRAY);
                headline_tv.setText("推荐");

            }
        });
        cross_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction()
                        .hide(recommendFragment)
                        .hide(videoFragment)
                        .show(crossTalkFragment)
                        .commit();
                recommend_iv.setImageResource(R.drawable.raw_unselect);
                cross_talk_iv.setImageResource(R.drawable.cro_unselect);
                video_iv.setImageResource(R.drawable.video_unselect);
                recommend_tv.setTextColor(Color.GRAY);
                cross_talk_tv.setTextColor(Color.parseColor("#03A9F4"));
                video_tv.setTextColor(Color.GRAY);
                headline_tv.setText("段子");
            }
        });
        video_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .hide(recommendFragment)
                        .hide(crossTalkFragment)
                        .show(videoFragment)
                        .commit();
                recommend_iv.setImageResource(R.drawable.raw_unselect);
                cross_talk_iv.setImageResource(R.drawable.cro_select);
                video_iv.setImageResource(R.drawable.video_select);
                recommend_tv.setTextColor(Color.GRAY);
                cross_talk_tv.setTextColor(Color.GRAY);
                video_tv.setTextColor(Color.parseColor("#03A9F4"));
                headline_tv.setText("视频");
            }
        });
        my_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.showMenu();
            }
        });
        compile_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this,Compile.class);
                startActivity(in);
            }
        });
    }

    private void initView() {
        recommend_ll = (LinearLayout) findViewById(R.id.recommend_ll);
        cross_talk = (LinearLayout) findViewById(R.id.cross_talk);
        video_ll = (LinearLayout) findViewById(R.id.video_ll);
        recommend_iv = (ImageView) findViewById(R.id.recommend_iv);
        cross_talk_iv = (ImageView) findViewById(R.id.cross_talk_iv);
        video_iv = (ImageView) findViewById(R.id.video_iv);
        recommend_tv = (TextView) findViewById(R.id.recommend_tv);
        cross_talk_tv = (TextView) findViewById(R.id.cross_talk_tv);
        video_tv = (TextView) findViewById(R.id.video_tv);
        headline_tv = (TextView) findViewById(R.id.headline_tv);
        my_img = (SimpleDraweeView) findViewById(R.id.my_image_view);
        compile_iv = (ImageView) findViewById(R.id.compile_iv);
        recommendFragment = new RecommendFragment();
        crossTalkFragment = new CrossTalkFragment();
        videoFragment = new VideoFragment();
    }


}
