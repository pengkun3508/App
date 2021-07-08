package com.vinnlook.www.surface.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dm.lib.core.adapter.vp.FixedFragmentPagerAdapter;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.mvp.presenter.CollectionTotalPresenter;
import com.vinnlook.www.surface.mvp.view.CollectionTotalView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.NoScrollViewPager;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:收藏列表
 * @Time:2021/7/8$
 * @Author:pk$
 */
public class CollectionTotalActivity extends BaseActivity<CollectionTotalPresenter> implements CollectionTotalView, ViewPager.OnPageChangeListener {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.shop_text)
    TextView shopText;
    @BindView(R.id.shop_line)
    TextView shopLine;
    @BindView(R.id.shop_layout_btn)
    LinearLayout shopLayoutBtn;
    @BindView(R.id.wenzhang_text)
    TextView wenzhangText;
    @BindView(R.id.wenzhang_line)
    TextView wenzhangLine;
    @BindView(R.id.wenzhang_layout_btn)
    LinearLayout wenzhangLayoutBtn;
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;
    List<Fragment> fragmentList;
    private CollectionFragment mCollectionFragment;
    private Collection2Fragment mCollection2Fragment;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, CollectionTotalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection_total;
    }


    @Override
    protected CollectionTotalPresenter initPresenter() {
        return new CollectionTotalPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        fragmentList = new ArrayList<>();

        mCollectionFragment = new CollectionFragment();
        mCollection2Fragment = new Collection2Fragment();

        fragmentList.add(mCollectionFragment);
        fragmentList.add(mCollection2Fragment);
        FixedFragmentPagerAdapter mainPagerAdapter = new FixedFragmentPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.setFragmentList(fragmentList);
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.setCurrentItem(0);
        setSelectedBottomBar(0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void loadData() {

    }

    @OnClick({ R.id.shop_layout_btn, R.id.wenzhang_layout_btn})
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @OnClick({R.id.shop_layout_btn, R.id.wenzhang_layout_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_layout_btn:
                viewPager.setCurrentItem(0);
                break;
            case R.id.wenzhang_layout_btn:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setSelectedBottomBar(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setSelectedBottomBar(int position) {
        if (position == 0) {//首页
            shopText.setTextColor(getResources().getColor(R.color.black));
            shopLine.setVisibility(View.VISIBLE);

            wenzhangText.setTextColor(getResources().getColor(R.color.grey));
            wenzhangLine.setVisibility(View.GONE);
        } else if (position == 1) {//分类
            shopText.setTextColor(getResources().getColor(R.color.grey));
            shopLine.setVisibility(View.GONE);

            wenzhangText.setTextColor(getResources().getColor(R.color.black));
            wenzhangLine.setVisibility(View.VISIBLE);
        }
    }

    private void doBottomBarIconAnim(View target) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, "scaleX", 1.0F, 0.9F, 1.0F, 1.1F, 1.0F);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, "scaleY", 1.0F, 0.9F, 1.0F, 1.1F, 1.0F);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY);
        set.setDuration(350);
        set.start();
    }


}
