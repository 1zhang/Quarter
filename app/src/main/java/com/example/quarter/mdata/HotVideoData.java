package com.example.quarter.mdata;

import com.example.quarter.bean.CrossBean;
import com.example.quarter.bean.HotVideo;

import java.util.List;

import base.BaseBean;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/12/7.
 */

public interface HotVideoData {
    void chenggong(Response<BaseBean<List<HotVideo>>> msg);
    void shibai(String msg);
    void cuowu(String msg);
}
