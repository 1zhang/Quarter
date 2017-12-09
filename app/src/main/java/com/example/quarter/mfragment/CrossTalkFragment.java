package com.example.quarter.mfragment;


import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quarter.R;
import com.example.quarter.adapter.MyAdapter;
import com.example.quarter.bean.CrossBean;
import com.example.quarter.mdata.CrossContent;
import com.example.quarter.parameters.CrossPresenter;
import com.example.quarter.presenter.CtPrecenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.ArrayList;
import java.util.List;
import base.BaseBean;
import base.BaseFragment;
import retrofit2.Response;

import static android.R.attr.duration;

/**
 * Created by 设计风格 on 2017/11/23.
 */

public class CrossTalkFragment extends BaseFragment<CtPrecenter> implements CrossPresenter,CrossContent {


    private XRecyclerView xrlv;
    private View view;
private int page = 1;
private List<CrossBean> list;
    private List<CrossBean> alList;
    private MyAdapter myAdapter;
private LinearLayout mm;
    private int tempY = 0;
    private float duration = 150.0f;
    private ArgbEvaluator evaluator = new ArgbEvaluator();
    private View view1;
   // private Toolbar toolbar;

    @Override
    public CtPrecenter f() {
        return new CtPrecenter(getActivity(),this,this);
    }

    @Override
    public View view() {

        view=View.inflate(getActivity(),R.layout.crosstalk_item,null);
        /*view1 = View.inflate(getActivity(), R.layout.activity_home_page,null);
        toolbar = view1.findViewById(R.id.toolbar);*/

        xrlv=view.findViewById(R.id.xrclv);
        mm = view.findViewById(R.id.mHeaderContent);
        xrlv.setLoadingMoreEnabled(true);
        xrlv.setPullRefreshEnabled(true);
        xrlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //page=1;
                myfragment.CrossContent();

            }

            @Override
            public void onLoadMore() {
                page++;
                myfragment.CrossContent();
                xrlv.loadMoreComplete();

            }
        });
        xrlv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                tempY += dy;
                System.out.println("temY-----"+tempY);
                System.out.println("dy-----"+dy);
                // 滚动的总距离相对0-150之间有一个百分比，头部的透明度也是从初始值变动到不透明，通过距离的百分比，得到透明度对应的值
                // 如果小于0那么透明度为初始值，如果大于150为不透明状态

                int bgColor = 0x00ffffff;
                if (tempY <= 0) {
                    bgColor = 0xffffffff;

                } else if (tempY > 150) {
                    bgColor = 0x00ffffff;

                } else {
                    bgColor = (int) evaluator.evaluate(tempY / duration, 0xffffffff, 0x00ffffff);

                }
                //toolbar.setBackgroundColor(bgColor);
            }
        });
        return view;
    }


    @Override
    public void initview() {
    list = new ArrayList<>();
    alList = new ArrayList<>();
    myfragment.CrossContent();

    }

    @Override
    public void showing() {

    }

    @Override
    public void hideing() {

    }

    @Override
    public void faile() {

    }

    @Override
    public int page() {

        return page;
    }

    @Override
    public String token() {
        return null;
    }

    @Override
    public void chenggong(Response<BaseBean<List<CrossBean>>> msg) {
        List<CrossBean> data = msg.body().data;
        for (int i = 0; i <data.size() ; i++) {
            list.add(data.get(i));
        }
          alList.addAll(list);
        list.clear();
        if(myAdapter==null){
            myAdapter = new MyAdapter(getActivity(),alList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            xrlv.setLayoutManager(layoutManager);
            xrlv.setAdapter(myAdapter);
        }else{
            myAdapter.notifyDataSetChanged();
            xrlv.refreshComplete();
        }
       // System.out.println("msg.body().data.content = " + msg.body().data.content);
    }

    @Override
    public void shibai(String msg) {

    }

    @Override
    public void cuowu(String msg) {

    }


}
