package com.example.quarter.App;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 设计风格 on 2017/11/23.
 */

public class App extends com.example.mvpmodel.App {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

    }
}
