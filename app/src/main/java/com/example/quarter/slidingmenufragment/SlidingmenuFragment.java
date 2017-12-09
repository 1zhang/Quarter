package com.example.quarter.slidingmenufragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quarter.Login;
import com.example.quarter.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by 设计风格 on 2017/11/25.
 */

public class SlidingmenuFragment extends Fragment {
    private SimpleDraweeView my2_iv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.slidingmenu_left_item,container,false);
        my2_iv=view.findViewById(R.id.my1_iv);
        Uri uri =  Uri.parse("res://"+getActivity().getPackageName()+"/"+R.drawable.tou);
        my2_iv.setImageURI(uri);
        my2_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),Login.class);
                startActivity(in);
            }
        });
        return view;
    }
}
