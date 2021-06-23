package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.BrandListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.BannerImgAdapter;
import com.vinnlook.www.surface.adapter.BrandAdapter;
import com.vinnlook.www.surface.mvp.presenter.BrandPresenter;
import com.vinnlook.www.surface.mvp.view.BrandView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:品牌展示
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class BrandActivity extends BaseActivity<BrandPresenter> implements BrandView {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.banner)
    Banner banner2;

    BrandAdapter adapter;
    List<BrandListBean.ListBean> brandListBean;
    private static String type;
    List<BrandListBean.BannerBean> bannerImage;


    public static void startSelf(Context context, String types) {
        Intent intent = new Intent(context, BrandActivity.class);
        context.startActivity(intent);
        type = types;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_2;
    }

    @Override
    protected BrandPresenter initPresenter() {
        return new BrandPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(BrandActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        actionBar.setTitle("品牌展示");
        adapter = new BrandAdapter(this);
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager2);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(10, 10));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                CommodityActivity.startSelf(getContext(), adapter.getData().get(position).getBrand_id(), "", adapter.getData().get(position).getBrand_name(), type);
                BrendDetailsActivity.startSelf(getActivity(), adapter.getData().get(position).getBrand_id());
            }
        });

        banner2.post(new Runnable() {
            @Override
            public void run() {
                banner2.getWidth();
                double f = Float.valueOf(banner2.getWidth() + "") / (1.4);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(banner2.getWidth(), (int) f);
                banner2.setLayoutParams(layoutParams);
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getBrandList(type);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * @Description:下载时间成功
     * @Time:2020/5/8 16:36
     * @Author:pk
     */
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    /**
     * @Description:下载时间失败
     * @Time:2020/5/8 16:36
     * @Author:pk
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:下载品牌列表成功
     * @Time:2020/5/8 16:36
     * @Author:pk
     */
    @Override
    public void getBrandListSuccess(int code, BrandListBean data) {
        brandListBean = data.getList();
        adapter.setData(data.getList());
        bannerImage = data.getBanner();//轮播
        if (bannerImage != null) {
            banner2.setStartPosition(0);
            BannerImgAdapter bannerImgAdapter = new BannerImgAdapter(getActivity(), gatBannetData());
            banner2.setAdapter(bannerImgAdapter);
            banner2.setIndicator(new CircleIndicator(getActivity()));
            banner2.start();
        }

    }

    /**
     * @Description:下载品牌列表失败
     * @Time:2020/5/8 16:36
     * @Author:pk
     */
    @Override
    public void getBrandListFail(int code, String msg) {
//        if (code == 3000) {
//            List<BrandListBean> data = new ArrayList<>();
//            adapter.setData(data);
//            presenter.dismissLoading();
//        }
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
