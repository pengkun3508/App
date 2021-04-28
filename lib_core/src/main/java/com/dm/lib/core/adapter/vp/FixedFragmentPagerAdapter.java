package com.dm.lib.core.adapter.vp;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.Arrays;
import java.util.List;

/**
 * 描述：固定小数量的FragmentPagerAdapter
 *
 * @author Cuizhen
 * @date 2018/9/19
 */
public class FixedFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public FixedFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        mFragmentList = fragmentList;
    }

    public void setTitleList(List<String> titleList) {
        mTitleList = titleList;
    }

    public void setTitleList(String[] titles) {
        mTitleList = Arrays.asList(titles);
    }

    public List<Fragment> getFragmentList() {
        return mFragmentList;
    }

    public List<String> getTitleList() {
        return mTitleList;
    }

    public Fragment getFragment(int position) {
        if (mFragmentList == null || mFragmentList.size() == 0){
            return null;
        }
        if (position < 0 || position >= mFragmentList.size()){
            return null;
        }
        return mFragmentList.get(position);
    }

    public String getTitle(int position) {
        if (mTitleList == null || mTitleList.size() == 0){
            return null;
        }
        if (position < 0 || position >= mTitleList.size()){
            return null;
        }
        return mTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getTitle(position);
    }
}
