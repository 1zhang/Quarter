package util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by 设计风格 on 2017/11/28.
 */

public class MyUtils {
    private Context context;
    private Map<String,String> map;
    private String tag;
    private SharedPreferences sp;
    public MyUtils(Context context ,String tag,Map<String,String> map){
        this.context=context;
       this.map = map;
        this.tag = tag;
    }
    public void Sp(){
        sp = context.getSharedPreferences(tag,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            edit.putString(entry.getKey(),entry.getValue());
        }
       edit.commit();
    }

}
