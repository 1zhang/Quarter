package com.example.quarter.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import com.example.quarter.R;
import com.example.quarter.VdActivity;
import com.example.quarter.bean.HotVideo;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 设计风格 on 2017/11/30.
 */

public class VhotAdapter extends XRecyclerView.Adapter<VhotAdapter.ViewHolder>{
    private List<HotVideo> list2;
    private Context con;
    private List<Integer> heightList;//装产出的随机数

    public VhotAdapter(Context con, List<HotVideo> list2){
        this.con = con;
        this.list2 = list2;
        heightList = new ArrayList<>();
    }

    @Override
    public VhotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.grid1view, null);
        VhotAdapter.ViewHolder h = new VhotAdapter.ViewHolder(view);
        return h;
    }

    @Override
    public void onBindViewHolder(VhotAdapter.ViewHolder holder, final int position) {
        heightList.clear();
        for (int i = 0; i < list2.size(); i++) {
            int height = new Random().nextInt(400) + 200;//[100,300)的随机数
            heightList.add(height);
        }

        ViewGroup.LayoutParams params = holder.iv.getLayoutParams();
        params.height=heightList.get(position);
        holder.iv.setLayoutParams(params);
        Uri u = Uri.parse(list2.get(position).cover);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(u)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(holder.iv.getController())
                .build();
holder.iv.setController(controller);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(con, VdActivity.class);
                in.putExtra("videourl",list2.get(position).videoUrl);
                in.putExtra("imgurl",list2.get(position).cover);
                con.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }
    public class ViewHolder extends XRecyclerView.ViewHolder{
        private SimpleDraweeView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.dz_iv);
        }
    }
}

