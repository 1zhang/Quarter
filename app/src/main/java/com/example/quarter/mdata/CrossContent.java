package com.example.quarter.mdata;

import com.example.quarter.bean.CrossBean;
import com.example.quarter.bean.Loginbean;

import java.util.List;

import base.BaseBean;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/28.
 */

public interface CrossContent {
    void chenggong(Response<BaseBean<List<CrossBean>>> msg);
    void shibai(String msg);
    void cuowu(String msg);
}
