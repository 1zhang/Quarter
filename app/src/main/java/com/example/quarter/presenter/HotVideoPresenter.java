package com.example.quarter.presenter;

import android.content.Context;

import com.example.quarter.bean.HotVideo;
import com.example.quarter.mdata.HotVideoData;
import com.example.quarter.model.MyModel;

import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/12/7.
 */

public class HotVideoPresenter {
    private Context context;
    private MyModel myModel;
    private HotVideoData hotVideo;
    public HotVideoPresenter (Context context,HotVideoData hotVideo){
        this.context = context;
        this.hotVideo= hotVideo;
        myModel = new MyModel();
    }
    public void hotvideo(String page,String token){
        myModel.hotvideo(page,token, new ModelCallBack() {
            @Override
            public void success(Response msg) {
                hotVideo.chenggong(msg);
            }

            @Override
            public void fail(String msg) {
              hotVideo.shibai(msg);
            }

            @Override
            public void wrong(String s) {
              hotVideo.cuowu(s);
            }
        });
    }
}
