package com.example.quarter;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quarter.bean.Loginbean;
import com.example.quarter.mdata.Mydata;
import com.example.quarter.parameters.Parameter;
import com.example.quarter.presenter.MPresenter;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import base.BaseActivity;
import base.BaseBean;
import retrofit2.Response;

import util.MyUtils;

public class Login extends BaseActivity<MPresenter> implements Parameter,Mydata {
private EditText et1,et2;
    private Button bt1;
    private Map<String,String> map;
    @Override
    public MPresenter p() {
        return new MPresenter(this,this,this);
    }

    @Override
    public int LayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void InitView() {
        et1 = (EditText) findViewById(R.id.edit_user);
        et2= (EditText) findViewById(R.id.edit_pas);
        bt1 = (Button) findViewById(R.id.bt_login);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precenter.login();
            }
        });
    }

    @Override
    public void showing() {

    }

    @Override
    public void hideing() {

    }

    @Override
    public void faile() {

    }

    @Override
    public String getname() {
        return et1.getText().toString();
    }

    @Override
    public String getpas() {
        return et2.getText().toString();
    }

    @Override
    public void chenggong(Response<BaseBean<Loginbean>> msg) {
        Toast.makeText(this,msg.body().data.token,Toast.LENGTH_SHORT).show();
        System.out.println("msg.body().data.uid = " + msg.body().msg);
        map = new HashMap<>();
        map.put("token",msg.body().data.token);
        map.put("uid",msg.body().data.uid);
        MyUtils my = new MyUtils(this,"Token",map);
        my.Sp();
        Intent in = new Intent(this,HomePage.class);
        startActivity(in);
    }

    @Override
    public void shibai(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cuowu(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
