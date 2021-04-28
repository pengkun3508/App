package com.vinnlook.www.surface.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vinnlook.www.surface.bean.CommodityTitleBean;
import com.vinnlook.www.surface.bean.RankingTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2021/2/3$
 * @Author:pk$
 */
public class MyPagerAdapter3 extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<RankingTabBean> mTitles;

    public MyPagerAdapter3(FragmentManager fm, List<Fragment> mFragments, List<RankingTabBean> mTitles) {
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
        return mTitles.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}