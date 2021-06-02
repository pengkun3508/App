package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.LimitedBean;
import com.vinnlook.www.surface.adapter.LimitedAdapter_2;
import com.vinnlook.www.surface.mvp.presenter.MillionSubsidyPresenter;
import com.vinnlook.www.surface.mvp.view.MillionSubsidyView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:百万补贴
 * @Time:2021/6/2$
 * @Author:pk$
 */
public class MillionSubsidyActivity extends BaseActivity<MillionSubsidyPresenter> implements MillionSubsidyView {
    @BindView(R.id.img_list_bg)
    ImageView imgListBg;
    @BindView(R.id.msg_title_back)
    ImageView msgTitleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.layout_bottoms)
    RelativeLayout layoutBottoms;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ctlTitle)
    CollapsingToolbarLayout ctlTitle;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    int page = 1;
    int lastItem = -1;
    int judge = 0;
    int dtime;

    LimitedAdapter_2 adapter;
    LimitedBean limibean;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, MillionSubsidyActivity.class);
        context.startActivity(intent);
    }

    /**
     * 根据百分比改变颜色透明度
     */
    public static int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mullion_subsidy;
    }

    @Override
    protected MillionSubsidyPresenter initPresenter() {
        return new MillionSubsidyPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(MillionSubsidyActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        msgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layoutBottoms.bringToFront();
        appbarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int totalScrollRange = appBarLayout.getTotalScrollRange();
//            Log.e("snow_app", "==totalScrollRange===" + totalScrollRange);
//            Log.e("snow_app", "=滑动=verticalOffset===" + verticalOffset);
            ///折叠区域（0，0）坐标在屏幕的位置verticalOffset值为负值  折叠区域的总高度totalScrollRange值为正值
//            if (verticalOffset == 0) {
//                mBinding.ivBack.setImageResource(R.mipmap.common_icon_back_arrow);
//                mBinding.tvTitle.setText("");
//
//            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
//                mBinding.ivBack.setImageResource(R.mipmap.common_icon_back_arrow);
//                mBinding.tvTitle.setText("免费专区");
//            }
            int color = changeAlpha(getResources().getColor(R.color.million_title), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());
            toolbar.setBackgroundColor(color);
            int color1 = changeAlpha(getResources().getColor(R.color.white), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());
            titleText.setTextColor(color1);


        });


        adapter = new LimitedAdapter_2(getActivity());
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(manager3);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setAdapter(adapter);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getMaillionList(page, 20);//下载限时列表
                judge = 0;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

//                Log.e("recyclerView", "=滑动=newState===" + newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (adapter.getData().size() - 12 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getMaillionList(page, 20);//下载限时列表
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 20) {
                        lastItem = adapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getMaillionList(page, 20);//下载限时列表
                    }
                }
            }
        });

        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());


            }
        });

    }

    @Override
    protected void loadData() {
        page = 1;
        lastItem = -1;
        judge = 0;
        presenter.getMaillionList(page, 20);//下载首页品牌数据

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 下载数据成功
     * @param code
     * @param limibean
     */
    @Override
    public void getLimiteSuccess(int code, LimitedBean limibean) {
        smartRefreshLayout.finishRefresh();
        this.limibean = limibean;
        if (judge == 0) {
            adapter.setData(limibean.getList());
        } else {
            adapter.addData(limibean.getList());
        }


    }

    /**
     * 下载数据失败
     * @param code
     * @param
     */
    @Override
    public void getLimiteFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }
}
