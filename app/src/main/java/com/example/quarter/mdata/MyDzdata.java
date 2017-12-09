package com.example.quarter.mdata;

import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/28.
 */

public interface MyDzdata {
    void chenggong(Response msg);
    void shibai(String msg);
    void cuowu(String msg);
}
