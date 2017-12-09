package com.example.quarter.mdata;


import base.BaseBean;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/29.
 */

public interface AtData {
    void chenggong(Response<BaseBean> msg);
    void shibai(String msg);
    void cuowu(String msg);

}
