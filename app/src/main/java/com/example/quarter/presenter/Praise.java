package com.example.quarter.presenter;

import android.content.Context;

import com.example.quarter.mdata.AtData;
import com.example.quarter.model.MyModel;

import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/29.
 */

public class Praise {
    private Context context;
    private AtData atData;
    private MyModel my ;
    public Praise(Context context, AtData atData){
        this.atData = atData;
        this.context = context;
        my = new MyModel();
    }
    public void praise(String uid,String wid,String token){
        my.praice(uid, wid, token, new ModelCallBack() {
            @Override
            public void success(Response msg) {
                atData.chenggong(msg);
            }

            @Override
            public void fail(String msg) {
                atData.shibai(msg);
            }

            @Override
            public void wrong(String s) {
                atData.cuowu(s);
            }
        });
    }
}
