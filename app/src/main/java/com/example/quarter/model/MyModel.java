package com.example.quarter.model;

import android.os.Environment;
import android.widget.Toast;

import com.example.quarter.Api.Api;
import com.example.quarter.bean.CrossBean;
import com.example.quarter.bean.HotVideo;
import com.example.quarter.bean.Loginbean;
import com.example.quarter.bean.ReBean;
import com.example.quarter.myrecever.MyRecever;
import com.example.quarter.presenter.DzPresenter;
import com.example.quarter.retorfit.Retor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.BaseActivity;
import base.BaseBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import model.ModelCallBack;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retorfit.MyRetorfit2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 设计风格 on 2017/11/23.
 */

public class MyModel implements Imodel {
    @Override
    public void login(String name, String pas, final ModelCallBack callBack) {
        MyRetorfit2 my = new MyRetorfit2();
        Retrofit re = my.re(Api.login);
        MyRecever recever = re.create(MyRecever.class);
        recever.login(name,pas).enqueue(new Callback<BaseBean<Loginbean>>() {
            @Override
            public void onResponse(Call<BaseBean<Loginbean>> call, Response<BaseBean<Loginbean>> response) {
                if(response.body().code.equals("0")) {
                    callBack.success(response);
                    System.out.println(response);
                }else{
                    callBack.fail(response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean<Loginbean>> call, Throwable t) {
                callBack.wrong("错误");
            }
        });

    }

    @Override
    public void corss(String token, String uid, String mycontent,List<File> list, final ModelCallBack callBack) {


        MyRetorfit2 my = new MyRetorfit2();
        Retrofit re = my.re(Api.login);
        List<MultipartBody.Part> list1 = new ArrayList<>();
         /*File file = new File(Environment.getExternalStorageDirectory() + "/z/hi.jpg");//filePath 图片地址  
        */
       /*  System.out.println("file = " + f);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image*//*"),f);

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("jokeFiles", f.getName(), requestBody);
  */
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                File file = list.get(i);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("jokeFiles", file.getName(), requestBody);
                list1.add(filePart);
            }
        }
        MultipartBody.Part uidPart = MultipartBody.Part.createFormData("uid", uid);
        MultipartBody.Part tokenPart = MultipartBody.Part.createFormData("token", token);
        MultipartBody.Part mycontentPart = MultipartBody.Part.createFormData("content", mycontent);
        MultipartBody.Part souPart = MultipartBody.Part.createFormData("source", "android");
        MultipartBody.Part apPart = MultipartBody.Part.createFormData("appVersion", "1");
        MyRecever recever = re.create(MyRecever.class);
        recever.corss(uidPart ,tokenPart ,mycontentPart,souPart,apPart,list1).enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                String code = response.body().code;
                System.out.println("code = "+code );
                if(code.equals("0")){
                    callBack.success(response);
                }else{
                    callBack.fail(response.body().msg);
                    System.out.println("response.body().msg = " + response.body().msg);

                }
            }
            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                   callBack.fail("错误");
            }
        });

    }

    @Override
    public void corssContent(String page, final ModelCallBack callBack) {
        MyRetorfit2 my = new MyRetorfit2();
        Retrofit re = my.re(Api.login);
        MyRecever recever = re.create(MyRecever.class);
        recever.corssContent(page).enqueue(new Callback<BaseBean<List<CrossBean>>>() {
            @Override
            public void onResponse(Call<BaseBean<List<CrossBean>>> call, Response<BaseBean<List<CrossBean>>> response) {
                String code = response.body().code;
                if(code.equals("0")){
                    callBack.success(response);
                }else{
                    callBack.fail(response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean<List<CrossBean>>> call, Throwable t) {
                    callBack.wrong("错误");
            }
        });
    }

    @Override
    public void attention(String uid, String followId, String token, final ModelCallBack callBack) {
        MyRetorfit2 my = new MyRetorfit2();
        Retrofit re = my.re(Api.login);
        MyRecever recever = re.create(MyRecever.class);
        recever.attention(uid,followId,token).enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                String code = response.body().code;
                if(code.equals("0")){
                    callBack.success(response);
                }else{
                    callBack.fail(response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                   callBack.wrong("请求错误");
            }
        });
    }

    @Override
    public void praice(String uid, String wid, String token, final ModelCallBack callBack) {
        MyRetorfit2 my = new MyRetorfit2();
        Retrofit re = my.re(Api.login);
        MyRecever recever = re.create(MyRecever.class);
        recever.praise(uid,wid,token).enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                String code = response.body().code;
                if(code.equals("0")){
                    callBack.success(response);
                }else{
                    callBack.fail(response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                    callBack.wrong("请求错误");
            }
        });
    }

    @Override
    public void review(String uid, String wid, String content, String tiken, final ModelCallBack callBack) {
       new  Retor().recever().review(uid,wid,content,tiken).enqueue(new Callback<BaseBean>() {
           @Override
           public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
               String code = response.body().code;
               if(code.equals("0")){
                   callBack.success(response);
               }else{
                   callBack.fail(response.body().msg);
               }
           }
           @Override
           public void onFailure(Call<BaseBean> call, Throwable t) {
                   callBack.wrong("错误");
           }
       });
    }

    @Override
    public void tuijian(String uid, String type, String page, String token, final ModelCallBack callBack) {
    new Retor().recever().tuijian(uid,type,page,token).enqueue(new Callback<BaseBean<List<ReBean>>>() {
        @Override
        public void onResponse(Call<BaseBean<List<ReBean>>> call, Response<BaseBean<List<ReBean>>> response) {
            String code = response.body().code;
            if(code.equals("0")){
                callBack.success(response);
            }else{
                callBack.fail(response.body().msg);
            }
        }

        @Override
        public void onFailure(Call<BaseBean<List<ReBean>>> call, Throwable t) {

        }
    });
    }

    @Override
    public void scvideo(String uid, File videoFile, File coverFile, String videoDesc, String latitude, String longitude, String token, final ModelCallBack callBack) {
        MultipartBody.Part uidPart = MultipartBody.Part.createFormData("uid", uid);
        MultipartBody.Part uidPart1 = MultipartBody.Part.createFormData("source", "android");
        MultipartBody.Part uidPart2 = MultipartBody.Part.createFormData("appVersion", "1");
        MultipartBody.Part token1 = MultipartBody.Part.createFormData("token", token);
        MultipartBody.Part la = MultipartBody.Part.createFormData("latitude", "0.0");
        System.out.println("token = " + token);
        MultipartBody.Part longt = MultipartBody.Part.createFormData("longitude", "0.0");
        File f = new File("/storage/emulated/0/截屏/截屏_20170710_235930.jpg");
        File f1 = videoFile;
        //File ff = new File("/storage/emulated/0/相机/video_20171205_143921.mp4");
        System.out.println("f1 = " + f1);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),f);
        RequestBody request = RequestBody.create(MediaType.parse("video/*"),f1);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("coverFile", f.getName(), requestBody);
        MultipartBody.Part filePart1 = MultipartBody.Part.createFormData("videoFile", f1.getName(), request);

        new Retor().recever().corss1(uidPart,token1,longt,la,uidPart1,uidPart2,filePart1,filePart).enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                if(response.body().code.equals("0")){
                    callBack.success(response);
                }else{
                    callBack.fail(response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                    callBack.wrong(t+"");
            }
        });
    }

    @Override
    public void hotvideo(String page,String token, final ModelCallBack callBack) {
        new Retor().recever().hotvideo(page,token).enqueue(new Callback<BaseBean<List<HotVideo>>>() {
            @Override
            public void onResponse(Call<BaseBean<List<HotVideo>>> call, Response<BaseBean<List<HotVideo>>> response) {
                if(response.body().code.equals("0")){
                    callBack.success(response);
                }else{
                    callBack.fail(response.body().msg);
                }
            }

            @Override
            public void onFailure(Call<BaseBean<List<HotVideo>>> call, Throwable t) {
                   callBack.fail("请求错误");
                System.out.println("t = " + t+"");
            }
        });
    }


}
