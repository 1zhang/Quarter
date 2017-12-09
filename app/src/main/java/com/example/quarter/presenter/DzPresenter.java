package com.example.quarter.presenter;

import android.content.Context;

import com.example.quarter.mdata.MyDzdata;
import com.example.quarter.model.MyModel;
import com.example.quarter.parameters.FbCrossTalk;

import base.BasePresenter;
import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/28.
 */

public class DzPresenter extends BasePresenter<FbCrossTalk> {
private FbCrossTalk mdata;
    private Context context;
    private MyModel myModel;
    private MyDzdata myDzdata;
    public DzPresenter(Context context,FbCrossTalk mdata,MyDzdata myDzdata) {
        super(mdata);
        this.context = context;
        this.mdata = mdata;
        this.myDzdata = myDzdata;
        myModel = new MyModel();
    }
    public void cross(){
        myModel.corss(mdata.token(), mdata.uid(), mdata.mycontent(),mdata.file(),new ModelCallBack() {
            @Override
            public void success(Response response) {
                myDzdata.chenggong(response);
            }

            @Override
            public void fail(String msg) {
               myDzdata.shibai(msg);
            }

            @Override
            public void wrong(String s) {
               myDzdata.cuowu(s);
            }
        });
    }
}
