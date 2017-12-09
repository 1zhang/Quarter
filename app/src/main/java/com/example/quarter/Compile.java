package com.example.quarter;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.quarter.mdata.AtData;
import com.example.quarter.presenter.ScPresenter;

import java.io.File;

import base.BaseBean;
import retrofit2.Response;

public class Compile extends AppCompatActivity {
    private RelativeLayout myvideo,mycross_talk;
    private TextView abolish_tv;
    private SharedPreferences sp;
    private String videoPath;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private double latitude;
    private double longitude;
    private SharedPreferences sp1;
    private AlertDialog.Builder ad;
    private String token;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile);
        initView();
        sp=  getSharedPreferences("Token",MODE_PRIVATE);
        sp1 = getSharedPreferences("ll",MODE_PRIVATE);

        token = sp.getString("token", null);
        uid = sp.getString("uid", null);
        abolish_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mycross_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(token !=null&& uid !=null){
                    Intent in = new Intent(Compile.this,Article.class);
                    in.putExtra("token", token);
                    in.putExtra("uid", uid);
                    startActivity(in);

                }else{
                    Toast.makeText(Compile.this,"请先登录",Toast.LENGTH_SHORT).show();
                }

            }
        });
        myvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(Compile.this, R.layout.xiang_item, null);
                ad = new AlertDialog.Builder(Compile.this).setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.create();
                    }
                });
                ad.create().show();
                view.findViewById(R.id.tv_b).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 200);


                    }
                });
                view.findViewById(R.id.tv_x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Compile.this,"相机1",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                        startActivityForResult(intent, 1);
                    }
                });
            }
        });
    }

    private void initView() {
        myvideo = (RelativeLayout) findViewById(R.id.myvideo_iv);
        mycross_talk = (RelativeLayout) findViewById(R.id.mycross_talk);
        abolish_tv = (TextView) findViewById(R.id.abolish_tv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200) {
            initLocation();
            startLocation();
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                /** 数据库查询操作。
                 * 第一个参数 uri：为要查询的数据库+表的名称。
                 * 第二个参数 projection ： 要查询的列。
                 * 第三个参数 selection ： 查询的条件，相当于SQL where。
                 * 第三个参数 selectionArgs ： 查询条件的参数，相当于 ？。
                 * 第四个参数 sortOrder ： 结果排序。
                 */
                Cursor cursor = cr.query(uri, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        // 视频ID:MediaStore.Audio.Media._ID
                        int videoId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                        // 视频名称：MediaStore.Audio.Media.TITLE
                        String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                        // 视频路径：MediaStore.Audio.Media.DATA
                        videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                        // 视频时长：MediaStore.Audio.Media.DURATION
                        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                        // 视频大小：MediaStore.Audio.Media.SIZE
                        long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));

                        // 视频缩略图路径：MediaStore.Images.Media.DATA
                        String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        // 缩略图ID:MediaStore.Audio.Media._ID
                        int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                        // 方法一 Thumbnails 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
                        // 第一个参数为 ContentResolver，第二个参数为视频缩略图ID， 第三个参数kind有两种为：MICRO_KIND和MINI_KIND 字面意思理解为微型和迷你两种缩略模式，前者分辨率更低一些。
                        Bitmap bitmap1 = MediaStore.Video.Thumbnails.getThumbnail(cr, imageId, MediaStore.Video.Thumbnails.MICRO_KIND, null);

                        // 方法二 ThumbnailUtils 利用createVideoThumbnail 通过路径得到缩略图，保持为视频的默认比例
                        // 第一个参数为 视频/缩略图的位置，第二个依旧是分辨率相关的kind
                        Bitmap bitmap2 = ThumbnailUtils.createVideoThumbnail(imagePath, MediaStore.Video.Thumbnails.MICRO_KIND);
                        // 如果追求更好的话可以利用 ThumbnailUtils.extractThumbnail 把缩略图转化为的制定大小
//                        ThumbnailUtils.extractThumbnail(bitmap, width,height ,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
                         File f= new File(videoPath);
                        ScPresenter sp = new ScPresenter(Compile.this, new AtData() {
                            @Override
                            public void chenggong(Response<BaseBean> msg) {
                                Intent in = new Intent(Compile.this,MyShare.class);
                                startActivity(in);
                            }

                            @Override
                            public void shibai(String msg) {
                                System.out.println("msg = " + msg);
                            }

                            @Override
                            public void cuowu(String msg) {
                                System.out.println("msg = " + msg);
                            }
                        });
                        String aLong = sp1.getString("long", "0.0");
                        String lati = sp1.getString("lati", "0.0");

                        sp.shang(uid,f,null,"nihao",token,aLong,lati);
                    }
                    cursor.close();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);

        }
        if(requestCode == 1){
            if (null != data) {
 Uri uri = data.getData();
 if (uri == null) {
 return;
 } else {
 Cursor c = getContentResolver().query(uri,
                             new String[] { MediaStore.MediaColumns.DATA }, null,
 null, null);//根据返回的URI，查找数据库，获取视频的路径
 if (c != null && c.moveToFirst()) {
 String filPath = c.getString(0);
 //Log.d("test", filPath);
     System.out.println("filPath = " + filPath);
     File f= new File(filPath);
     ScPresenter sp = new ScPresenter(Compile.this, new AtData() {
         @Override
         public void chenggong(Response<BaseBean> msg) {
             Intent in = new Intent(Compile.this,MyShare.class);
             startActivity(in);
         }

         @Override
         public void shibai(String msg) {
             System.out.println("msg = " + msg);
         }

         @Override
         public void cuowu(String msg) {
             System.out.println("msg = " + msg);
         }
     });
     String aLong = sp1.getString("long", "0.0");
     String lati = sp1.getString("lati", "0.0");
     sp.shang(uid,f,null,"nihao",token,aLong,lati);
 }
 }
 }
        }
    }
    private void initLocation(){
        locationOption = new AMapLocationClientOption();
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(5000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }
    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            int code = location.getErrorCode();
            System.out.println("code = " + code);

            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    sb.append("定位成功" + "\n");

                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    System.out.println("longitude = " + longitude+latitude);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("long",longitude+"");
                    edit.putString("lati",latitude+"");
                    edit.commit();
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    //定位完成的时间
                    //sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");
                }
                //解析定位结果，
                String result = sb.toString();
                //tvResult.setText(result);
                System.out.println("result = " + result);
            } else {
                System.out.println(" 定位失败 " );
            }
        }
    };



    private void startLocation(){
        //根据控件的选择，重新设置定位参数
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }
}
