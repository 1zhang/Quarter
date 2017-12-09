package com.example.quarter.mdata;

import com.example.quarter.bean.ReBean;

import java.util.List;

import base.BaseBean;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/12/4.
 */

public interface ReData {
    void chenggong(Response<BaseBean<List<ReBean>>> msg);
    void shibai(String msg);
    void cuowu(String msg);
}
