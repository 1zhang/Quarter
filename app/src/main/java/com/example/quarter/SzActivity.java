package com.example.quarter;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quarter.bean.BBbean;
import com.example.quarter.myrecever.MyRecever;
import com.example.quarter.retorfit.Retor;

import java.io.File;
import java.text.DecimalFormat;

import base.BaseBean;
import luo.library.base.base.BaseAndroid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SzActivity extends AppCompatActivity {
private TextView tv,v;
    private long len=0;
    private TextView c,banben;
    private String apkUrl;
    private int code;
    private int versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sz);
        tv = (TextView) findViewById(R.id.clear_tv);
        banben = (TextView) findViewById(R.id.banben);
        c = (TextView) findViewById(R.id.c);
        v = (TextView) findViewById(R.id.v_tv);
        PackageManager manager = getPackageManager();
        PackageInfo  info = null;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        versionCode = info.versionCode;
        String name = info.versionName;
        System.out.println("name ======= " + name);
        v.setText("V"+name);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog ad = new AlertDialog.Builder(SzActivity.this).setTitle("清除缓存").setMessage("是否清除缓存")
                        .setNegativeButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mclear(getFilesDir());
                                recreate();
                            }
                        }).setPositiveButton("否",null).create();
                ad.show();
            }
        });
        String s = gainFileSize(getFilesDir());
        c.setText(s);
        banben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banben1();
            }
        });
    }
    private void banben1() {
        Retor re = new Retor();
        MyRecever recever = re.recever();
        recever.bb().enqueue(new Callback<BaseBean<BBbean>>() {
            @Override
            public void onResponse(Call<BaseBean<BBbean>> call, Response<BaseBean<BBbean>> response) {
                apkUrl = response.body().data.apkUrl;
                code = response.body().data.versionCode;

                    if(code<=versionCode){
                        Toast.makeText(SzActivity.this,"当前已是最大版本",Toast.LENGTH_SHORT).show();
                    }else{
                        BaseAndroid.checkUpdate(SzActivity.this,code,
                                apkUrl,
                                "更新了...\n修复...", false);
                    }
            }

            @Override
            public void onFailure(Call<BaseBean<BBbean>> call, Throwable t) {

            }
        });


    }

    private void mclear(File file) {

        if(file.isFile()){
            file.delete();
        }else{
            if(file!=null){
                File[] files = file.listFiles();
                for (File file1 : files) {
                    if(file1.isFile()){
                        file1.delete();
                    }else{
                        digui(file1);
                    }
                }
            }
        }
    }
    public String gainFileSize(final File file){
        String length="";

        long clear = digui(file);
        //转化
        length = formatFileSize(clear);

        return length;
    }


    private long digui(File f) {
        if(f.isFile()){
            len+=f.length();
        }else{
             if(f!=null){
                 File[] files = f.listFiles();
                 for (File file : files) {
                     if(file.isFile()){
                         len+=file.length();
                         System.out.println("len = " + len);
                     }else{
                         digui(file);
                     }
                 }
             }
        }
        return  len;
            }

    private String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");//保留两位小数
        String fileSizeString = "";
        if(fileS!=0) {
            if (fileS < 1024) {
                fileSizeString = df.format((double) fileS) + "B";
            } else if (fileS < 1048576) {
                fileSizeString = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                fileSizeString = df.format((double) fileS / 1048576) + "MB";
            } else {
                fileSizeString = df.format((double) fileS / 1073741824) + "G";
            }
        }
        else
        {
            fileSizeString="0";
        }
        return fileSizeString;
    }

}
