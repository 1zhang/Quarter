package com.example.quarter.mdata;

import com.example.quarter.bean.Loginbean;

import base.BaseBean;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/23.
 */

public interface Mydata {
    void chenggong(Response<BaseBean<Loginbean>> msg);
    void shibai(String msg);
    void cuowu(String msg);
}
