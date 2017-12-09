package com.example.quarter.retorfit;

import com.example.quarter.Api.Api;
import com.example.quarter.myrecever.MyRecever;

import retorfit.MyRetorfit2;
import retrofit2.Retrofit;

/**
 * Created by 设计风格 on 2017/12/1.
 */

public class Retor {
    private MyRetorfit2 my ;
    public MyRecever recever(){
        my = new MyRetorfit2();
        Retrofit re = my.re(Api.login);
        MyRecever recever = re.create(MyRecever.class);
        return recever;
    }
}
