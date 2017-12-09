package com.example.quarter.bean;

import java.util.List;

import base.BaseBean;

/**
 * Created by 设计风格 on 2017/12/4.
 */

public class ReBean extends BaseBean {


    /**
     * commentNum : 0
     * comments : []
     * cover : https://www.zhaoapi.cn/images/quarter/1512355016990oppo2.3gp
     * createTime : 2017-12-04T10:36:56
     * favoriteNum : 0
     * latitude : 1212
     * localUri : null
     * longitude : 12121
     * playNum : null
     * praiseNum : 0
     * uid : 169
     * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512120820443cropped_1512120819548.jpg","nickname":"不熟","praiseNum":"null"}
     * videoUrl : https://www.zhaoapi.cn/images/quarter/1512355016990oppo2.3gp
     * wid : 17
     * workDesc : OPPO音乐手机
     */

    public int commentNum;
    public String cover;
    public String createTime;
    public int favoriteNum;
    public String latitude;
    public Object localUri;
    public String longitude;
    public Object playNum;
    public int praiseNum;
    public int uid;
    public UserBean user;
    public String videoUrl;
    public int wid;
    public String workDesc;
    public List<?> comments;

    public static class UserBean {
        /**
         * age : null
         * fans : null
         * follow : null
         * icon : https://www.zhaoapi.cn/images/1512120820443cropped_1512120819548.jpg
         * nickname : 不熟
         * praiseNum : null
         */

        public Object age;
        public String fans;
        public String follow;
        public String icon;
        public String nickname;
        public String praiseNum;
    }
}
