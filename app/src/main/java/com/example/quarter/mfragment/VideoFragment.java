package com.example.quarter.mfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quarter.R;
import com.example.quarter.mfragment.erfragment.AttentionFragment;
import com.example.quarter.mfragment.erfragment.HotFragment;
import com.example.quarter.mfragment.videoFragment.VattentionFragment;
import com.example.quarter.mfragment.videoFragment.VhotFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 设计风格 on 2017/11/23.
 */

public class VideoFragment extends Fragment {
    private TabLayout tb;
    private ViewPager vp;
    private List<Fragment> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.video_item, null);

        tb = view.findViewById(R.id.tb_layout1);
        vp = view.findViewById(R.id.vp1);
        list = new ArrayList<>();
        list.add(new VhotFragment());
        list.add(new VattentionFragment());
        vp.setAdapter(new pagerfragment(getActivity().getSupportFragmentManager()));
        tb.setupWithViewPager(vp);
        return view;
    }
    class pagerfragment extends FragmentPagerAdapter {

        public pagerfragment(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            Fragment fragment=  null;
            switch (position){
                case 0:
                    fragment = new VhotFragment();
                    break;
                case 1:
                    fragment = new VattentionFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0){
                return "热门";
            }else
                return "关注";
        }
    }
}
