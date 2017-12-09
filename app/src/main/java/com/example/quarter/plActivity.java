package com.example.quarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quarter.mdata.AtData;
import com.example.quarter.presenter.PlPresenter;

import base.BaseActivity;
import base.BaseBean;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class plActivity extends BaseActivity<PlPresenter> implements AtData{

private EditText ed;
    @Override
    public void chenggong(Response<BaseBean> msg) {
        Toast.makeText(this,msg.body().msg, LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void shibai(String msg) {
        Toast.makeText(this,msg, LENGTH_SHORT).show();
    }

    @Override
    public void cuowu(String msg) {
        Toast.makeText(this,msg,LENGTH_SHORT).show();
    }

    @Override
    public PlPresenter p() {
        return new PlPresenter(this);
    }

    @Override
    public int LayoutId() {
        return R.layout.activity_pl;
    }

    @Override
    public void InitView() {
        ed = (EditText) findViewById(R.id.et_pl);
        Button bt = (Button) findViewById(R.id.bt);
        Intent intent = getIntent();
        final String uid = intent.getStringExtra("uid");
        final String wid = intent.getStringExtra("wid");
        final String token = intent.getStringExtra("token");
        System.out.println("uid+wid+token = " + uid+wid+token);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                precenter.review(uid,wid,ed.getText().toString(),token);
            }
        });

    }
}
