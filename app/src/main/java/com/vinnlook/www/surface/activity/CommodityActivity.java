package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.dm.lib.utils.StatusBarUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.CommodityListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.MyPagerAdapter2;
import com.vinnlook.www.surface.bean.CommodityTitleBean;
import com.vinnlook.www.surface.fragment.FragementItem;
import com.vinnlook.www.surface.mvp.presenter.CommodityPresenter;
import com.vinnlook.www.surface.mvp.view.CommodityView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.NoScrollViewPager;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 品牌列表进入的商品页面
 * @Time:2020/5/8$
 * @Author:pk$
 */
public class CommodityActivity extends BaseActivity<CommodityPresenter> implements CommodityView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;

    @BindView(R.id.title_tablayout)
    SlidingTabLayout titleTablayout;
    @BindView(R.id.pager)
    NoScrollViewPager viewPager;

    private static String getBrand_id;
    private static String actId;
    private static String name;
    private static String type;
    String getFlood;
    String getPieces;
    String iD;


    //Fragment集合
    private ArrayList<Fragment> mFagments = new ArrayList<>();

    public static void startSelf(Context context, String getBrand_ids, String id, String names, String types) {
        Intent intent = new Intent(context, CommodityActivity.class);
        context.startActivity(intent);
        getBrand_id = getBrand_ids;
        actId = id;
        name = names;
        type = types;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_1;
    }

    @Override
    protected CommodityPresenter initPresenter() {
        return new CommodityPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(CommodityActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        actionBar.setTitle(name);
    }

    @Override
    protected void loadData() {
        presenter.getCommodityTitle(getBrand_id, actId, type);

    }

    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {


    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:下载列表数据成功
     * @Time:2020/5/8 17:34
     * @Author:pk
     */
    @Override
    public void getCommodityListSuccess(int code, CommodityListBean data) {
    }

    /**
     * @Description:下载列表数据失败
     * @Time:2020/5/8 17:34
     * @Author:pk
     */
    @Override
    public void getCommodityListFail(int code, String msg) {
        Log.e("CommodityActivity", "==code==" + code);
    }

    /**
     * @Description:下载Title 成功
     * @Time:2020/5/8 17:34
     * @Author:pk
     */
    @Override
    public void getCommodityTitleSuccess(int code, List<CommodityTitleBean> data) {
        getFlood = data.get(0).getFlood();
        getPieces = data.get(0).getPieces();
        for (int i = 0; i < data.size(); i++) {
            //添加Fragment
            mFagments.add(new FragementItem(this, getBrand_id, data.get(i).getFlood(), data.get(i).getPieces(), type));
        }
        if (data.size() > 3) {
            titleTablayout.setTabSpaceEqual(false);
        } else {
            titleTablayout.setTabSpaceEqual(true);
        }
        titleTablayout.setIndicatorColor(getResources().getColor(R.color.them));
        titleTablayout.setBackgroundColor(getResources().getColor(R.color.black));
        titleTablayout.setPadding(0, 15, 0, 15);
        titleTablayout.setIndicatorHeight(80);

        //new一个适配器
        MyPagerAdapter2 mAdapter = new MyPagerAdapter2(getSupportFragmentManager(), mFagments, data);
        viewPager.setAdapter(mAdapter);
        titleTablayout.setViewPager(viewPager);


    }

    /**
     * @Description:下载Title 失败
     * @Time:2020/5/8 17:34
     * @Author:pk
     */
    @Override
    public void getCommodityTitleFail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
