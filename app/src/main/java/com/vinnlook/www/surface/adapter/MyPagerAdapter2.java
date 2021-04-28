package com.vinnlook.www.surface.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vinnlook.www.surface.bean.CommodityTitleBean;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter2 extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<CommodityTitleBean> mTitles;

    public MyPagerAdapter2(FragmentManager fm, List<Fragment> mFragments, List<CommodityTitleBean> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}