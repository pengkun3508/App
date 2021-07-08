package com.vinnlook.www.surface.fragment;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.surface.activity.ThemeDetailsActivity;
import com.vinnlook.www.surface.activity.ThemeListActivity;
import com.vinnlook.www.surface.activity.ThemeOtherActivity;
import com.vinnlook.www.surface.activity.ThemeOtherDetailsActivity;
import com.vinnlook.www.surface.adapter.BannerImgAdapter4;
import com.vinnlook.www.surface.adapter.BannerImgAdapter8;
import com.vinnlook.www.surface.adapter.Guang_1Adapter;
import com.vinnlook.www.surface.bean.GuangThemBean;
import com.vinnlook.www.surface.mvp.presenter.Guang_1FragmentPresenter;
import com.vinnlook.www.surface.mvp.view.Guang_1FragmentView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description:主题乐园
 * @Time:2021/6/28$
 * @Author:pk$
 */
public class Guang_1Fragment extends BaseFragment<Guang_1FragmentPresenter> implements Guang_1FragmentView {
    @BindView(R.id.banner_1)
    Banner banner1;
    @BindView(R.id.guang_recycler)
    RecyclerView guangRecycler;

    Guang_1Adapter guang1Adapter;
    GuangThemBean guangThemBean;
    List<GuangThemBean.BannerBean> bannerImage;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guang_1;
    }

    @Override
    protected Guang_1FragmentPresenter initPresenter() {
        return new Guang_1FragmentPresenter();
    }

    @Override
    protected void initView() {
        banner1.post(new Runnable() {
            @Override
            public void run() {
                banner1.getWidth();
                double f = Float.valueOf(banner1.getWidth() + "") / (2.1);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(banner1.getWidth(), (int) f);
                banner1.setLayoutParams(layoutParams);
            }
        });
        guangRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        guang1Adapter = new Guang_1Adapter(getActivity());
        guangRecycler.setNestedScrollingEnabled(false);
        guangRecycler.setHasFixedSize(true);
        guangRecycler.setAdapter(guang1Adapter);
        guang1Adapter.setOnAllClickListener(new Guang_1Adapter.OnItemClickListener() {
            //标题点击
            @Override
            public void onItemTitleClicked(int type, String name) {
                if (type == 1) {//主题
                    ThemeListActivity.startSelf(getActivity(), name);
                } else {//其他
                    ThemeOtherActivity.startSelf(getActivity(), name, type);
                }
            }

            //商品点击
            @Override
            public void onItemDataClicked(int type, String iD) {
                if (type == 1) {//主题
                    ThemeDetailsActivity.startSelf(getActivity(), iD);
                } else {//其他
                    ThemeOtherDetailsActivity.startSelf(getActivity(), iD);
                }
            }
        });


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getThemData();
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getThemData();
    }

    /**
     * 下载数据成功
     *
     * @param code
     * @param guangThemBeans
     */
    @Override
    public void getThemDataSuccess(int code, GuangThemBean guangThemBeans) {
        smartRefreshLayout.finishRefresh();
        guangThemBean = guangThemBeans;
        bannerImage = guangThemBeans.getBanner();//轮播
        if (bannerImage != null) {
            BannerImgAdapter8 bannerImgAdapter = new BannerImgAdapter8(getActivity(), gatBannetData());
            banner1.setAdapter(bannerImgAdapter);
//        bannerMy.setIndicator(new CircleIndicator(getActivity()));
            banner1.start();

        }
        if (guangThemBeans.getItem().size()>0){
            guangRecycler.setVisibility(View.VISIBLE);
            Log.e("主题乐园","===size==="+guangThemBeans.getItem().size());
            guang1Adapter.setData(guangThemBeans.getItem());
        }else{
            guangRecycler.setVisibility(View.GONE);
        }




    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param
     */
    @Override
    public void getThemDataFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }

    public List<String> gatBannetData() {
        List<String> strings = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < bannerImage.size(); i++) {
            ids.add(bannerImage.get(i).getId());
            strings.add(bannerImage.get(i).getPhoto());
        }
        return strings;
    }
}
