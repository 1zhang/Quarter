package com.example.quarter.parameters;

import java.io.File;
import java.util.List;

import base.BaseView;
import okhttp3.MultipartBody;

/**
 * Created by 设计风格 on 2017/11/28.
 */

public interface FbCrossTalk extends BaseView {
    String token();
    String uid();
    String mycontent();
    List<File> file();
}
