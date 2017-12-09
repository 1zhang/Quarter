package retorfit;



import loginterceptor.MyInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 设计风格 on 2017/11/14.
 */

public class MyRetorfit2 {
    private MyRetorfit2 my;
    public static Retrofit re;
    public MyRetorfit2() {

    }
public Retrofit re(String url){
    OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
    builder.addInterceptor(new MyInterceptor());
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

}
