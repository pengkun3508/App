package com.vinnlook.www.surface.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @Description:
 * @Time:2020/10/16$
 * @Author:pk$
 */
public class MyPagerAdapters extends FragmentPagerAdapter {

    private ArrayList<Fragment> fs;

    public MyPagerAdapters(FragmentManager fm, ArrayList<Fragment> fs) {
        super(fm);
        this.fs = fs;
    }

    @Override
    public Fragment getItem(int i) {
        return fs.get(i);
    }

    @Override
    public int getCount() {
        return fs.size();
    }
}