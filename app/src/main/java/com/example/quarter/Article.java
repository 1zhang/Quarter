package com.example.quarter;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.bean.Loginbean;
import com.example.quarter.mdata.MyDzdata;
import com.example.quarter.mdata.Mydata;
import com.example.quarter.parameters.FbCrossTalk;
import com.example.quarter.parameters.Parameter;
import com.example.quarter.presenter.DzPresenter;
import com.example.quarter.presenter.MPresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import base.BaseActivity;
import base.BaseBean;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import static android.R.attr.data;

public class Article extends BaseActivity<DzPresenter> implements FbCrossTalk,MyDzdata {

private TextView publish_tv,ab_tv;
    private EditText my_cross_content;
    private String token;
    private String uid;
private ImageView addIv;
    private List<File> list;
    private File file;
    private int t;

    @Override
    public DzPresenter p() {
        return new DzPresenter(this,this,this);
    }

    @Override
    public int LayoutId() {
        return R.layout.activity_article;
    }

    @Override
    public void InitView() {
        publish_tv = (TextView) findViewById(R.id.publish_tv);
       ab_tv = (TextView) findViewById(R.id.abolish_tv);
        my_cross_content = (EditText) findViewById(R.id.my_cross_content);
        addIv = (ImageView) findViewById(R.id.add_iv);


        list = new ArrayList<>();
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        uid = intent.getStringExtra("uid");
        System.out.println("token = " + token+uid);
        publish_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* RxView.clickEvents(publish_tv) //RxBinding代码
                        .throttleFirst(500, TimeUnit.MILLISECONDS) //设置防抖间隔为500ms
                        .subscribe(subscriber);*/
                precenter.cross();

            }
        });
        ab_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");//相片类型  
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    public String token() {
        return token;
    }

    @Override
    public String uid() {
        return uid;
    }

    @Override
    public String mycontent() {
        return my_cross_content.getText().toString();
    }

    @Override
    public List<File> file() {
        return list;
    }


    @Override
    public void showing() {

    }

    @Override
    public void hideing() {

    }

    @Override
    public void faile() {

    }

    @Override
    public void chenggong(Response msg) {
        Toast.makeText(this,"发表成功",Toast.LENGTH_SHORT).show();
        Intent in = new Intent(this,MyShare.class);
        startActivity(in);
    }

    @Override
    public void shibai(String msg) {
        if(msg.equals("token超时")){
            Toast.makeText(this,"登录过期,请重新登录",Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cuowu(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if(resultCode != RESULT_OK){
 Log.e("TAG--->onresult","ActivityResult resultCode error");
 return;
 }
//获得图片  
 Bitmap bitmap = null;
 ContentResolver resolver = getContentResolver();
 if(requestCode == 100){
 Uri uri = data.getData();
 try {
 bitmap = MediaStore.Images.Media.getBitmap(resolver,uri);//获得图片  
 } catch (IOException e) {
 e.printStackTrace();
 }
 }

 //获得路径  
  if(requestCode == 100){
 Uri uri = data.getData();
 //uri = geturi(data);//解决方案  
 String[] pro = {MediaStore.Images.Media.DATA};
 //好像是android多媒体数据库的封装接口，具体的看Android文档  
 Cursor cursor = managedQuery(uri,pro,null,null,null);
 Cursor cursor1 = getContentResolver().query(uri,pro,null,null,null);
 //拿到引索  
 int index = cursor1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
 //移动到光标开头  
 cursor.moveToFirst();
 //最后根据索引值获取图片路径  
 String path = cursor.getString(index);
      System.out.println("path = " + path);

      file = new File(path);

 Log.d("Tag--->path",path);
      t++;
      if(t>=10){
         Toast.makeText(Article.this,"最多添加九张图片",Toast.LENGTH_SHORT).show();
      }else {
          addIv.setImageBitmap(bitmap);
          list.add(file);
      }


    }
    }
}
