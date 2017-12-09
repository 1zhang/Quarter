package com.example.quarter.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.quarter.bean.Loginbean;
import com.example.quarter.mdata.Mydata;
import com.example.quarter.model.MyModel;
import com.example.quarter.parameters.Parameter;

import base.BaseBean;
import base.BasePresenter;
import model.ModelCallBack;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/23.
 */

public class MPresenter extends BasePresenter<Parameter> {
    private Parameter mdata;
    private MyModel myModel;
    private Mydata mydata;
    private Context context;
    public MPresenter(Context context,Parameter mdata,Mydata mydata) {
        super(mdata);
        this.context = context;
        this.mdata=mdata;
        this.mydata = mydata;
        myModel = new MyModel();
    }

    //暴露方法
    public void login(){
        if(mdata.getname()==null||mdata.getpas()==null){
            Toast.makeText(context,"用户名或密码为空",Toast.LENGTH_SHORT).show();
        }else {
            myModel.login(mdata.getname(), mdata.getpas(), new ModelCallBack() {
                @Override
                public void success(Response msg) {
                    mydata.chenggong(msg);
                }

                @Override
                public void fail(String msg) {
                    mydata.shibai(msg);
                }

                @Override
                public void wrong(String s) {
                    mydata.shibai("请求失败");
                }
            });
        }
    }

}
