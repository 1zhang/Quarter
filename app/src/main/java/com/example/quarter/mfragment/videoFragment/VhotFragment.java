package com.example.quarter.mfragment.videoFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quarter.R;
import com.example.quarter.adapter.VhotAdapter;
import com.example.quarter.bean.HotVideo;
import com.example.quarter.mdata.HotVideoData;
import com.example.quarter.presenter.HotVideoPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import base.BaseBean;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/12/7.
 */

public class VhotFragment extends Fragment {
    private XRecyclerView hxre;
    private SharedPreferences sp;
    private int page = 1;
    private VhotAdapter vhot;
    private List<HotVideo> list;
    private List<HotVideo> adlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.vhotfragment_item, null);
        hxre = view.findViewById(R.id.hot_xre);
        list = new ArrayList<>();
        adlist = new ArrayList<>();
        sp = getActivity().getSharedPreferences("Token", Context.MODE_PRIVATE);
        hxre.setLoadingMoreEnabled(true);
        hxre.setPullRefreshEnabled(true);

        final HotVideoPresenter hot = new HotVideoPresenter(getContext(), new HotVideoData() {
            @Override
            public void chenggong(Response<BaseBean<List<HotVideo>>> msg) {
                List<HotVideo> data = msg.body().data;
                for (int i = 0; i <data.size() ; i++) {
                    list.add(data.get(i));
                }
                adlist.addAll(list);
                list.clear();
if(vhot==null){
    vhot = new VhotAdapter(getActivity(),adlist);
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
    //3.为recyclerView设置布局管理器
    hxre.setLayoutManager(staggeredGridLayoutManager);
    hxre.setAdapter(vhot);

}else{
    vhot.notifyDataSetChanged();
    hxre.loadMoreComplete();
}
            }

            @Override
            public void shibai(String msg) {
                System.out.println("msg = " + msg);
            }

            @Override
            public void cuowu(String msg) {
                System.out.println("msg = " + msg);
            }
        });
        final String token = sp.getString("token", "");
        hot.hotvideo(page+"",token);

        hxre.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //page=1;
                adlist.clear();
                hot.hotvideo("1",token);
                System.out.println("\"shangla\" = " + "下拉刷新");
                hxre.loadMoreComplete();

            }

            @Override
            public void onLoadMore() {
                page++;
                hot.hotvideo(page+"",token);
                hxre.loadMoreComplete();
                System.out.println("\"shangla\" = " + "上拉加载");
                System.out.println("page = " + page);
            }
        });
        return view;
    }
}
