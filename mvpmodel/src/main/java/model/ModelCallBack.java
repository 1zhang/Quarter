package model;


import base.BaseBean;
import retrofit2.Response;

/**
 * Created by 设计风格 on 2017/11/14.
 */

public interface ModelCallBack<T> {
    void success(Response<BaseBean<T>> msg);
    void fail(String msg);
    void wrong(String s);
}
