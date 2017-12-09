package base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 设计风格 on 2017/11/24.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    public T myfragment;
    public abstract T f();
    public abstract View view();
    public abstract void initview();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myfragment = f();
        initview();
        return view();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myfragment.dicth();
    }
}
