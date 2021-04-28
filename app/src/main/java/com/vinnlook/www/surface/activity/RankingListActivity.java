package com.vinnlook.www.surface.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.RankingTtileAdapter;
import com.vinnlook.www.surface.adapter.RankingTtileAdapter1;
import com.vinnlook.www.surface.bean.RankingTabBean;
import com.vinnlook.www.surface.bean.ReBangListBean;
import com.vinnlook.www.surface.fragment.RankFragment;
import com.vinnlook.www.surface.mvp.presenter.RankingListPresenter;
import com.vinnlook.www.surface.mvp.view.RankingListView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:排行榜列表
 * @Time:2020/12/22$
 * @Author:pk$
 */
public class RankingListActivity extends BaseActivity<RankingListPresenter> implements RankingListView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.rank_title_recyclerv)
    RecyclerView rankTitleRecyclerv;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

//    RankingTtileAdapter adapter;
    RankingTtileAdapter1 adapter;
    List<RankingTabBean> rankingTabBean;

    //Fragment集合
    private ArrayList<Fragment> mFagments = new ArrayList<>();

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, RankingListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ranking_list_activity;
    }

    @Override
    protected RankingListPresenter initPresenter() {
        return new RankingListPresenter();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        adapter = new RankingTtileAdapter1(this);
        GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
        rankTitleRecyclerv.setLayoutManager(manager1);
        rankTitleRecyclerv.setAdapter(adapter);
        initListener();
        viewPager.setOffscreenPageLimit(3);


    }

    private void initListener() {
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                //显示相应的item界面
                viewPager.setCurrentItem(position);
                int size = rankingTabBean.size();
                if (size > 2) {
                    if (position > 1 && position < size - 2) {
                        moveToCenter(view);
                    } else if (position >= 0 && position < 2) {
                        rankTitleRecyclerv.smoothScrollToPosition(0);
                    } else {
                        rankTitleRecyclerv.smoothScrollToPosition(size - 1);
                    }
                }
            }
        });
        //ViewPager监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                rankTitleRecyclerv.smoothScrollToPosition(i);
                int size = rankingTabBean.size() - 1;
                if (i <= size) {
                    adapter.setPosion(i);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    //点击条目条目居中

    private void moveToCenter(View itemView) {
        int[] locationView = new int[2];
        itemView.getLocationOnScreen(locationView);
        int viewWidth = itemView.getWidth();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int centerX = dm.widthPixels / 2;
        int distance = locationView[0] - centerX + viewWidth / 2;
        rankTitleRecyclerv.smoothScrollBy(distance, 0);
    }

    @Override
    protected void loadData() {
        presenter.getRankingTabData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return rankingTabBean.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return rankingTabBean.get(position).getName();
        }

        @Override
        public Fragment getItem(int i) {
            return RankFragment.getInstance(String.valueOf(rankingTabBean.get(i).getId()), rankingTabBean.get(i).getName());
        }
    }


    /**
     * 排行榜title 数据--成功
     *
     * @return
     */
    @Override
    public void getRankingTabDataSuccess(int code, List<RankingTabBean> data) {
        rankingTabBean = data;
        adapter.setData(data);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0); //显示相应的item界面
        adapter.setPosion(0);
//        adapter.notifyDataSetChanged();



    }

    /**
     * 排行榜title 数据--失败
     *
     * @return
     */
    @Override
    public void getRankingTabDataFail(int code, String msg) {

    }

    @Override
    public void getTypeReBangListSuccess(int code, List<ReBangListBean> data) {

    }

    @Override
    public void getTypeReBangListFail(int code, String msg) {

    }
}
