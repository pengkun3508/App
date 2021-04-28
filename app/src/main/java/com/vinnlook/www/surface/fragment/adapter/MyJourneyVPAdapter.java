package com.vinnlook.www.surface.fragment.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/15
 */
public class MyJourneyVPAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> mFragments;



    public MyJourneyVPAdapter(FragmentManager fm, ArrayList<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;

    }


    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }


}
