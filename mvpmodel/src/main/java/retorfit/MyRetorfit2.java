package retorfit;



import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import loginterceptor.MyInterceptor;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 设计风格 on 2017/11/14.
 */

public class MyRetorfit2 {
    private MyRetorfit2 my;
    public static Retrofit re;
    private static boolean b = false;
    public MyRetorfit2() {

    }
public Retrofit re(String url){
    File httpCacheDirectory = new File(Environment.getExternalStorageDirectory(), "HttpCache");//这里为了方便直接把文件放在了SD卡根目录的HttpCache中，一般放在context.getCacheDir()中
    int cacheSize = 10 * 1024 * 1024;//设置缓存文件大小为10M
    Cache cache = new Cache(httpCacheDirectory, cacheSize);

    OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
    builder.addInterceptor(new MyInterceptor())
            .addNetworkInterceptor(in)
    .cache(cache)
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(5,TimeUnit.SECONDS)
    .writeTimeout(5,TimeUnit.SECONDS);
    OkHttpClient client = builder.build();
    if(re==null){
        synchronized (this){
            if(re==null){
                re = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(client)
                        .baseUrl(url)
                        .build();
            }
        }
    }
    return re;
}

static Interceptor in  = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
            int maxAge = 60*60*24*2;//缓存失效时间，单位为秒
             response.newBuilder()
                    .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .header("Cache-Control", "max-age=" + maxAge)
                    .build();
        System.out.println("maxAge = " + maxAge);
        return response;
    }
};

}
