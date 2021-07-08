package com.vinnlook.www.surface.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.surface.fragment.adapter.MyJourneyVPAdapter;
import com.vinnlook.www.surface.mvp.presenter.GuangFragmentPresenter;
import com.vinnlook.www.surface.mvp.view.GuangFragmentView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: 逛逛
 * @Time:2021/6/25$
 * @Author:pk$
 */
public class GuangFragment extends BaseFragment<GuangFragmentPresenter> implements GuangFragmentView {


    @BindView(R.id.guang_theme_text)
    TextView guangThemeText;
    @BindView(R.id.guang_selected_text)
    TextView guangSelectedText;
    @BindView(R.id.guang_theme_table)
    TextView guangThemeTable;
    @BindView(R.id.guang_theme_layout)
    LinearLayout guangThemeLayout;
    @BindView(R.id.guang_selected_table)
    TextView guangSelectedTable;
    @BindView(R.id.guang_selected_layout)
    LinearLayout guangSelectedLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private Guang_1Fragment mGuang1Fragment;
    private Guang_2Fragment mGuang2Fragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guang_home;
    }

    @Override
    protected GuangFragmentPresenter initPresenter() {
        return new GuangFragmentPresenter();
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        mGuang1Fragment = new Guang_1Fragment();
        mGuang2Fragment = new Guang_2Fragment();
        fragmentList.add(mGuang1Fragment);
        fragmentList.add(mGuang2Fragment);
        MyJourneyVPAdapter vp = new MyJourneyVPAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(vp);
        viewPager.setCurrentItem(0);
        setSelectedBottomBar(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int position;
            private int oldPositon;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                this.position = position;
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 1) {
                    oldPositon = position;
                }
                if (state == 0) {

                    if (position == oldPositon) {
                        if (position == 0) {
                            //滑动到第一页，继续向右滑
                            viewPager.setCurrentItem(position);
                            setSelectedBottomBar(position);
                        } else if (position == viewPager.getAdapter().getCount() - 1) {
                            //滑动到最后一页，继续向左滑
                            viewPager.setCurrentItem(position);
                            setSelectedBottomBar(position);
                        } else {
                            //滑动到一半时停止滑动，当前停留在第position页
                            viewPager.setCurrentItem(position);
                            setSelectedBottomBar(position);
                        }
                    } else {
                        if (position < oldPositon) {
                            //从左向右
                            viewPager.setCurrentItem(position);
                            setSelectedBottomBar(position);
                        } else {
                            //从右向左
                            viewPager.setCurrentItem(position);
                            setSelectedBottomBar(position);
                        }
                    }
                }

            }
        });
    }

    @Override
    protected void loadData() {
    }


    @OnClick({R.id.guang_theme_layout, R.id.guang_selected_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guang_theme_layout://主题乐园
                viewPager.setCurrentItem(0);
                setSelectedBottomBar(0);
                break;
            case R.id.guang_selected_layout://精选眼图
                viewPager.setCurrentItem(1);
                setSelectedBottomBar(1);
                break;
        }
    }

    private void setSelectedBottomBar(int position) {
        if (position == 0) {//首页
            guangThemeText.setTextColor(getResources().getColor(R.color.black));
            guangSelectedText.setTextColor(getResources().getColor(R.color.guang_title_text));
            guangThemeTable.setVisibility(View.VISIBLE);
            guangSelectedTable.setVisibility(View.GONE);
        } else if (position == 1) {//分类
            guangThemeText.setTextColor(getResources().getColor(R.color.guang_title_text));
            guangSelectedText.setTextColor(getResources().getColor(R.color.black));
            guangThemeTable.setVisibility(View.GONE);
            guangSelectedTable.setVisibility(View.VISIBLE);
        }
    }

}
