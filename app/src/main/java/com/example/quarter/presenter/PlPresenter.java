package com.example.quarter.presenter;

import com.example.quarter.mdata.AtData;
import com.example.quarter.model.MyModel;

import base.BasePresenter;
import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/12/1.
 */

public class PlPresenter extends BasePresenter {
    private AtData atData;
    private MyModel myModel;

    public PlPresenter(AtData mdata) {
        super(mdata);
        this.atData = mdata;
        myModel = new MyModel();
    }
    public void review(String uid,String wid,String content,String token){
        myModel.review(uid, wid, content, token, new ModelCallBack() {
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
