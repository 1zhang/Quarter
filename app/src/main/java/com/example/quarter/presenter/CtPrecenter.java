package com.example.quarter.presenter;

import android.content.Context;

import com.example.quarter.mdata.CrossContent;
import com.example.quarter.model.MyModel;
import com.example.quarter.parameters.CrossPresenter;

import base.BasePresenter;
import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/28.
 */

public class CtPrecenter extends BasePresenter<CrossPresenter> {
    private CrossPresenter mdata;
    private Context context;
    private MyModel myModel;
    private CrossContent content;
    public CtPrecenter(Context context, CrossPresenter mdata, CrossContent content) {
        super(mdata);
        this.context = context;
        this.mdata = mdata;
        this.content = content;
        myModel = new MyModel();
    }
    public void CrossContent(){
        myModel.corssContent(mdata.page()+"", new ModelCallBack() {
            @Override
            public void success(Response msg) {

                content.chenggong(msg);
            }

            @Override
            public void fail(String msg) {
                 content.shibai(msg);
            }

            @Override
            public void wrong(String s) {
                 content.cuowu("错误");
            }
        });
    }
}
