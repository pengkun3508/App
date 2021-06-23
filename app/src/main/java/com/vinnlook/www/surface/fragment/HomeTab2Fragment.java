package com.vinnlook.www.surface.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.surface.activity.BrendDetailsActivity;
import com.vinnlook.www.surface.adapter.BannerImgAdapter;
import com.vinnlook.www.surface.adapter.HomeTab2Adapter;
import com.vinnlook.www.surface.bean.HomeTab2Bean;
import com.vinnlook.www.surface.mvp.presenter.HomeTab2FragmentPresenter;
import com.vinnlook.www.surface.mvp.view.HomeTab2FragmentView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.mobile.auth.gatewayauth.utils.ReflectionUtils.getActivity;

/**
 * @Description:首页--品牌展示
 * @Time:2021/3/31$
 * @Author:pk$
 */
public class HomeTab2Fragment extends BaseFragment<HomeTab2FragmentPresenter> implements HomeTab2FragmentView {


    @BindView(R.id.banner)
    Banner banner2;
    @BindView(R.id.recy_list)
    RecyclerView recyList;
//    @BindView(R.id.home_scroll)
//    ScrollView homeScroll;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    HomeTab2Adapter adapter;
    List<HomeTab2Bean.BannerBean> bannerImage;


    @Override
    protected int getLayoutId() {
        return R.layout.home_tab_2_fragment;
    }

    @Override
    protected HomeTab2FragmentPresenter initPresenter() {
        return new HomeTab2FragmentPresenter();
    }

    @Override
    protected void initView() {
        adapter = new HomeTab2Adapter();
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        recyList.setLayoutManager(manager3);
        recyList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyList.addItemDecoration(new SpaceItemDecoration(10, 10));
        recyList.setNestedScrollingEnabled(false);
        recyList.setHasFixedSize(false);
        recyList.setAdapter(adapter);

        banner2.post(new Runnable() {
            @Override
            public void run() {
                banner2.getWidth();
                double f = Float.valueOf(banner2.getWidth() + "") / (1.4);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(banner2.getWidth(), (int) f);
                banner2.setLayoutParams(layoutParams);
            }
        });


        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {

                BrendDetailsActivity.startSelf(getActivity(),adapter.getData().get(position).getBrand_id());

            }
        });

        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getBrendList();//下载首页品牌数据

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();

            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getBrendList();//下载首页品牌数据
    }


    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getHomeTab2DataSuccess(int code, HomeTab2Bean data) {
        smartRefreshLayout.finishRefresh();
        adapter.setData(data.getList());
        bannerImage = data.getBanner();//轮播
        if (bannerImage!=null){
            banner2.setStartPosition(0);
            BannerImgAdapter bannerImgAdapter = new BannerImgAdapter(getActivity(), gatBannetData());
            banner2.setAdapter(bannerImgAdapter);
            banner2.setIndicator(new CircleIndicator(getActivity()));
            banner2.start();
        }
    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getHomeTab2DataFail(int code, String msg) {
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
