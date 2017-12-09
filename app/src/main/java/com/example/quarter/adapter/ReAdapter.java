package com.example.quarter.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.IjkVideoView;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.quarter.R;
import com.example.quarter.bean.ReBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext;

/**
 * Created by 设计风格 on 2017/12/4.
 */

public class ReAdapter extends XRecyclerView.Adapter<ReAdapter.ViewHolder> {
    private  Context context;
    private List<ReBean> list;
    PlayerView playerView;
    private String s;
    private Bitmap bitmap;
    private byte[] bytes;

    public ReAdapter(Context context , List<ReBean> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public ReAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.readapter_item, null);
        ViewHolder h = new ViewHolder(view);
        return h;
    }

    @Override
    public void onBindViewHolder(ReAdapter.ViewHolder holder, final int position) {
        String url = list.get(position).videoUrl;
        s = url.replaceAll("https://www.zhaoapi.cn", "http://120.27.23.105");


        playerView = new PlayerView((Activity) context,holder.itemView)
        .setTitle("短视频")
        .setScaleType(PlayStateParams.fitparent)
        .setPlaySource(s)
        .showThumbnail(new OnShowThumbnailListener() {
            @Override
            public void onShowThumbnail(final ImageView ivThumbnail) {
                   if(list.get(position).cover!=null&&!list.get(position).cover.endsWith(".mp4")){
                       Glide.with(context)
                               .load(list.get(position).cover)
                               .into(ivThumbnail);
                   }







            }
        });
        System.out.println(s);
        if(list.get(position).user.nickname!=null){
            holder.tv.setText(list.get(position).user.nickname);
        }

        String icon = list.get(position).user.icon;
        if(icon!=null){
            Uri parse = Uri.parse(icon);
            holder.sp.setImageURI(parse);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends XRecyclerView.ViewHolder{
private TextView tv;
        private SimpleDraweeView sp;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.nk_tv);
            sp = itemView.findViewById(R.id.sp);
        }
    }
}
