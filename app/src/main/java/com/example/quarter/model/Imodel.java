package com.example.quarter.model;

import java.io.File;
import java.util.List;

import model.ModelCallBack;
import okhttp3.MultipartBody;

/**
 * Created by 设计风格 on 2017/11/23.
 */

public interface Imodel {
    //登录
    void login(String name, String pas, ModelCallBack callBack);
    //发表段子
    void corss(String token, String uid, String mycontent, List<File> files, ModelCallBack callBack);
    //获取段子
    void corssContent(String page,ModelCallBack callBack);
    //关注
    void attention(String uid,String followId,String token,ModelCallBack callBack);
    //点赞
    void praice(String uid,String wid,String token,ModelCallBack callBack);
    //评论
    void review(String uid,String wid,String content,String tiken,ModelCallBack callBack);
    //推荐
    void tuijian(String uid,String type,String page,String token,ModelCallBack callBack);
    //上传视频
    void scvideo(String uid,File videoFile,File coverFile,
                 String videoDesc,String latitude,String longitude, String token,ModelCallBack callBack);
    //热门视频
    void hotvideo(String page,String token,ModelCallBack callBack);
}
