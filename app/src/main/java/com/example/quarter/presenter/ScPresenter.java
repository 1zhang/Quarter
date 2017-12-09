package com.example.quarter.presenter;

import android.content.Context;

import com.example.quarter.mdata.AtData;
import com.example.quarter.mdata.ReData;
import com.example.quarter.model.MyModel;

import java.io.File;

import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/12/4.
 */

public class ScPresenter {
    private Context context;
    private AtData atData;
    private MyModel myModel;

    public ScPresenter(Context context, AtData atData) {
        this.context = context;
        this.atData = atData;
        myModel = new MyModel();
    }

    public void shang(String uid, File vodeofile, File coverFile, String videoDesc,String token ,String latitude, String longitude) {
myModel.scvideo(uid, vodeofile, coverFile, videoDesc, longitude, latitude, token, new ModelCallBack() {
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
