package base;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mvpmodel.R;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by 设计风格 on 2017/11/14.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public  T precenter;
    public abstract  T p();
    public abstract int LayoutId();
    public abstract void InitView();

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutId());
        precenter = p();
        InitView();

    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        precenter.dicth();

    }
}
