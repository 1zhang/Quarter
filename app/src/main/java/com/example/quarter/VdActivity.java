package com.example.quarter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

public class VdActivity extends AppCompatActivity {
private PlayerView playerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vd);
        Intent intent = getIntent();
        String videourl = intent.getStringExtra("videourl");
        final String imgurl = intent.getStringExtra("imgurl");
        String s = videourl.replaceAll("https://www.zhaoapi.cn", "http://120.27.23.105");
        playerView = new PlayerView(this)
                .setTitle("短视频")
                .setScaleType(PlayStateParams.fitparent)
                .setPlaySource(s)    .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(final ImageView ivThumbnail) {

                            Glide.with(VdActivity.this)
                                    .load(imgurl)
                                    .into(ivThumbnail);
                    }
                });;
        playerView.startPlay();

    }
}
