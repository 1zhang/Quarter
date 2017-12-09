package com.example.quarter.bean;

import java.util.List;

import base.BaseBean;

/**
 * Created by 设计风格 on 2017/12/7.
 */

public class HotVideo extends BaseBean{

    /**
     * commentNum : 0
     * comments : []
     * cover : https://www.zhaoapi.cn/images/quarter/15124600880061.jpg
     * createTime : 2017-12-05T15:48:08
     * favoriteNum : 0
     * latitude : 40
     * localUri : null
     * longitude : 116
     * playNum : null
     * praiseNum : 0
     * uid : 170
     * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/15124630726151.jpg","nickname":"beautiful","praiseNum":"null"}
     * videoUrl : https://www.zhaoapi.cn/images/quarter/1512460088006_https___1251964405_vod2_myqcloud_com_vodtransgzp1251964405_4564972818487746947_v_f30.mp4
     * wid : 32
     * workDesc : try
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
         * icon : https://www.zhaoapi.cn/images/15124630726151.jpg
         * nickname : beautiful
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
