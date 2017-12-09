package com.example.quarter.mfragment.erfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quarter.R;
import com.example.quarter.adapter.ReAdapter;
import com.example.quarter.bean.ReBean;
import com.example.quarter.mdata.AtData;
import com.example.quarter.mdata.Mydata;
import com.example.quarter.mdata.ReData;
import com.example.quarter.parameters.Parameter;
import com.example.quarter.presenter.MPresenter;
import com.example.quarter.presenter.TjPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import base.BaseBean;
import base.BaseFragment;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/25.
 */

public class HotFragment extends Fragment {
    private List<String> list;
    private XBanner mybanner;
    private XRecyclerView re;
    private List<ReBean> list1;
    private List<ReBean> data;
    private int page=1;
    private TjPresenter t;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.item_hot, null);
        View view1 = View.inflate(getActivity(), R.layout.hot_head_item, null);
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        mybanner = view1.findViewById(R.id.mybanner);
        re = view.findViewById(R.id.hotre);
        re.addHeaderView(view1);
        re.setLoadingMoreEnabled(true);
        re.setPullRefreshEnabled(true);
        re.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                t.tuijian("551","1",page+"","AD7D76CB63F7A5E17C64A14A4FE588D1");
                //re.loadMoreComplete();
            }

            @Override
            public void onLoadMore() {
               /* page++;
                t.tuijian("551","1",page+"","AD7D76CB63F7A5E17C64A14A4FE588D1");
                re.loadMoreComplete();*/
            }
        });
        list.add("http://p5.so.qhimgs1.com/bdr/_240_/t012ebf748a1cd14c0b.jpg");
        list.add("http://p1.so.qhimgs1.com/bdr/_240_/t01c5ffffd4bb2dc12d.jpg");
        list.add("http://p0.so.qhmsg.com/bdr/_240_/t010430133b755158ff.jpg");
        mybanner.setData(list,null);
        mybanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }
        });
        t = new TjPresenter(getActivity(), new ReData() {
            @Override
            public void chenggong(Response<BaseBean<List<ReBean>>> msg) {

                System.out.println("msg.body().msg = " +msg.body().msg );
                System.out.println("msg.body().msg = " +msg.body().data.get(0).videoUrl );
                data = msg.body().data;
                LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                re.setLayoutManager(lm);
                re.setAdapter(new ReAdapter(getActivity(),data));
                re.loadMoreComplete();
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
        t.tuijian("551","1",page+"","AD7D76CB63F7A5E17C64A14A4FE588D1");
        return view;
    }

}