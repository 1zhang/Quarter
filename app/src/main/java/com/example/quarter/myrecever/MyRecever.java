package com.example.quarter.myrecever;

import com.example.quarter.bean.BBbean;
import com.example.quarter.bean.CrossBean;
import com.example.quarter.bean.HotVideo;
import com.example.quarter.bean.Loginbean;
import com.example.quarter.bean.ReBean;

import java.util.List;
import java.util.Map;

import base.BaseBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by 设计风格 on 2017/11/23.
 */

public interface MyRecever {
    /**
     * 登录
     * @param name
     * @param pas
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Call<BaseBean<Loginbean>> login(@Field("mobile") String name, @Field("password") String pas);

    /**
     * 发表段子
     * @return
     */
     @Multipart
    @POST("quarter/publishJoke")
    //@FormUrlEncoded

    Call<BaseBean> corss(@Part MultipartBody.Part uid,@Part MultipartBody.Part token,@Part MultipartBody.Part content,@Part MultipartBody.Part source,@Part MultipartBody.Part appVer,@Part List<MultipartBody.Part> f);


    /**
     * 段子
     * @param page
     * @return
     */

    @GET("quarter/getJokes")
    @Headers("cache:20")
    //@FormUrlEncoded
    Call<BaseBean<List<CrossBean>>> corssContent(@Query("page") String page);

    /**
     * 关注
     * @return
     */
    @POST("quarter/follow")
    @FormUrlEncoded
    Call<BaseBean> attention(@Field("uid") String uid,@Field("followId") String followId , @Field("token") String token);
/**
 * 获取关注列表
 */
    @POST("quarter/getFollowUsers")
    @FormUrlEncoded
    Call<BaseBean> attentions(@Field("uid")String uid,@Field("token")String token);

    /**
     * 点赞
     * @return
     */
    @POST("quarter/praise")
    @FormUrlEncoded
    Call<BaseBean> praise(@Field("uid") String uid,@Field("wid") String wid,@Field("token") String token);
    /**
     * 评论
     */
    @POST("quarter/commentJoke")
    @FormUrlEncoded
    Call<BaseBean> review(@Field("uid") String uid , @Field("jid")String w,@Field("content") String c,@Field("token") String t);
    @POST("quarter/getVideos")
    @FormUrlEncoded
    Call<BaseBean<List<ReBean>>> tuijian(@Field("uid")String uid,@Field("type")String type,@Field("page")String page,@Field("token")String token);

    /**
     * 上传视频
     * @param uid
     * @param token
     * @param longitude
     * @param latitude
     * @param source
     * @param appVer
     * @param file1
     * @param file2
     * @return
     */
    @Multipart
    @POST("quarter/publishVideo")
    Call<BaseBean> corss1(@Part MultipartBody.Part uid, @Part MultipartBody.Part token,
                          @Part MultipartBody.Part longitude, @Part MultipartBody.Part latitude,
                          @Part MultipartBody.Part source, @Part MultipartBody.Part appVer,
                          @Part MultipartBody.Part file1, @Part MultipartBody.Part file2);
    @POST("quarter/getHotVideos")
    @FormUrlEncoded
    Call<BaseBean<List<HotVideo>>> hotvideo(@Field("page")String page,@Field("token")String token);
@POST("quarter/getVersion")
    Call<BaseBean<BBbean>> bb();
}
