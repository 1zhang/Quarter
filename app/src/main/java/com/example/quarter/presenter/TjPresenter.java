package com.example.quarter.presenter;

import android.content.Context;

import com.example.quarter.mdata.AtData;
import com.example.quarter.mdata.ReData;
import com.example.quarter.model.MyModel;

import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/12/4.
 */

public class TjPresenter {
    private Context context;
    private ReData atData;
    private MyModel myModel;
    public TjPresenter(Context context,ReData atData){
        this.context = context;
        this.atData = atData;
        myModel = new MyModel();
    }
    public void tuijian(String uid,String type,String page,String token){
        myModel.tuijian(uid, type, page, token, new ModelCallBack() {
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
