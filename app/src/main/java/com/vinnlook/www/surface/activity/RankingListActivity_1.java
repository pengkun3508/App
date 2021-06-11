package com.vinnlook.www.surface.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.RankingTtileAdapter1;
import com.vinnlook.www.surface.bean.RankingTabBean;
import com.vinnlook.www.surface.bean.ReBangListBean;
import com.vinnlook.www.surface.fragment.RankFragment;
import com.vinnlook.www.surface.mvp.presenter.RankingListPresenter;
import com.vinnlook.www.surface.mvp.view.RankingListView;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:首页--new--排行榜
 * @Time:2021/3/12$
 * @Author:pk$
 */
public class RankingListActivity_1 extends BaseActivity<RankingListPresenter> implements RankingListView {


    @BindView(R.id.rank_title_recyclerv)
    RecyclerView rankTitleRecyclerv;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    //    RankingTtileAdapter adapter;
    RankingTtileAdapter1 adapter;
    List<RankingTabBean> rankingTabBean;
    @BindView(R.id.msg_title_back)
    ImageView msgTitleBack;
    @BindView(R.id.img_list_bg)
    ImageView imgListBg;

    //Fragment集合
    private ArrayList<Fragment> mFagments = new ArrayList<>();

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, RankingListActivity_1.class);
        context.startActivity(intent);
    }

//    @Override
//    protected boolean isRegisterEventBus() {
//        return true;
//    }


    @Override
    protected int getLayoutId() {
        return R.layout.ranking_list_activity_1;
    }

    @Override
    protected RankingListPresenter initPresenter() {
        return new RankingListPresenter();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), false);

        adapter = new RankingTtileAdapter1(this);
        GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
        rankTitleRecyclerv.setLayoutManager(manager1);
        rankTitleRecyclerv.setAdapter(adapter);
        initListener();
        viewPager.setOffscreenPageLimit(3);

        msgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void initListener() {
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                //显示相应的item界面
                viewPager.setCurrentItem(position);
                presenter.getTypeReBangList(String.valueOf(adapter.getData().get(position).getId()));//下载列表数据
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
                int size = adapter.getData().size();
                if (i < size) {
                    adapter.setPosion(i);
                    adapter.notifyDataSetChanged();
                    presenter.getTypeReBangList(String.valueOf(adapter.getData().get(i).getId()));//下载列表数据
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
            return RankFragment.getInstance(String.valueOf(adapter.getData().get(i).getId()), adapter.getData().get(i).getName());
        }
    }


    /**
     * 排行榜title 数据--成功
     *
     * @return；
     */
    @Override
    public void getRankingTabDataSuccess(int code, List<RankingTabBean> data) {
        rankingTabBean = data;
        adapter.setData(data);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0); //显示相应的item界面
        adapter.setPosion(0);

        presenter.getTypeReBangList(String.valueOf(data.get(0).getId()));//下载列表数据
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

//        Matrix matrix = new Matrix();           //创建一个单位矩阵
//        matrix.setTranslate(0, 0);          //平移x和y各100单位
//        matrix.preRotate(0);                   //顺时针旋转30度
        imgListBg.setScaleType(ImageView.ScaleType.FIT_XY);
//        imgListBg.setImageMatrix(matrix);

        ImageLoader.image(this, imgListBg, data.get(0).getGoods_thumb());
    }

    @Override
    public void getTypeReBangListFail(int code, String msg) {

    }


    //图片
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventMainThread(ReBangImageBgEvent event) {
//        Log.e("接收消息", "==NO1--图片==" + event.getImg());
//        ImageLoader.image(this, imgListBg, event.getImg());
//    }
}
