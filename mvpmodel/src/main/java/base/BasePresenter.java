package base;

/**
 * Created by 设计风格 on 2017/11/14.
 */

public class BasePresenter<T> {
    private T mdata;

    public BasePresenter(T mdata) {
        this.mdata = mdata;
    }
    public void dicth(){
        this.mdata = null;
    }
}
