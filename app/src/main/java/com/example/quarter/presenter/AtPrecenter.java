package com.example.quarter.presenter;

import android.content.Context;

import com.example.quarter.mdata.AtData;
import com.example.quarter.model.MyModel;

import base.BasePresenter;
import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/29.
 */

public class AtPrecenter  {
    private Context context;
    private MyModel myModel;
    private AtData atData;
    public AtPrecenter(Context context ,AtData atData){
        this.atData = atData;
        this.context = context;
        myModel = new MyModel();
    }
    public void getatdata(String uid,String followId,String token){
        myModel.attention(uid, followId, token, new ModelCallBack() {
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
