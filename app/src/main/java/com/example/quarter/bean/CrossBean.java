package com.example.quarter.bean;

import base.BaseBean;

/**
 * Created by 设计风格 on 2017/11/28.
 */

public  class CrossBean extends BaseBean {


    /**
     * commentNum : null
     * content : 庭有枇杷树，吾妻死之年所手植也，今已亭亭如盖矣.今伐之,为博小娘子一笑
     * createTime : 2017-11-27T19:26:45
     * imgUrls : null
     * jid : 37
     * praiseNum : null
     * shareNum : null
     * uid : 100
     * user : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-28T10:54:00","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/100.jpg","latitude":null,"longitude":null,"mobile":"13775854299","money":0,"nickname":"节能君","password":"123123","praiseNum":null,"token":"2169FEE6F3AC42D662EFF44250A0BD87","uid":100,"userId":null,"username":"13775854299"}
     */

    public Object commentNum;
    public String content;
    public String createTime;
    public Object imgUrls;
    public int jid;
    public Object praiseNum;
    public Object shareNum;
    public int uid;
    public UserBean user;

    public static class UserBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-28T10:54:00
         * email : null
         * fans : null
         * follow : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/100.jpg
         * latitude : null
         * longitude : null
         * mobile : 13775854299
         * money : 0
         * nickname : 节能君
         * password : 123123
         * praiseNum : null
         * token : 2169FEE6F3AC42D662EFF44250A0BD87
         * uid : 100
         * userId : null
         * username : 13775854299
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public Object fans;
        public Object follow;
        public int gender;
        public String icon;
        public Object latitude;
        public Object longitude;
        public String mobile;
        public int money;
        public String nickname;
        public String password;
        public Object praiseNum;
        public String token;
        public int uid;
        public Object userId;
        public String username;
    }
}
