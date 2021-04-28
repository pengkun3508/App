package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.LimitedBean;
import com.vinnlook.www.surface.adapter.LimitedAdapter_1;
import com.vinnlook.www.surface.mvp.presenter.LimitedPresenter;
import com.vinnlook.www.surface.mvp.view.LimitedView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DateUtil;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.SmartRefreshHelper;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:限时列表
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class LimitedActivity_1 extends BaseActivity<LimitedPresenter> implements LimitedView {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    LimitedBean limibean;
    @BindView(R.id.msg_title_back)
    ImageView msgTitleBack;
    @BindView(R.id.date_stare_end_text)
    TextView dateStareEndText;
    @BindView(R.id.home_xianshi_time_text_days)
    RoundTextView homeXianshiTimeTextDays;
    @BindView(R.id.home_xianshi_time_text_hours)
    RoundTextView homeXianshiTimeTextHours;
    @BindView(R.id.home_xianshi_time_text_minutes)
    RoundTextView homeXianshiTimeTextMinutes;
    @BindView(R.id.home_xianshi_time_text_seconds)
    RoundTextView homeXianshiTimeTextSeconds;
    @BindView(R.id.img_list_bg)
    ImageView imgListBg;
    @BindView(R.id.layout_bottoms)
    RelativeLayout layoutBottoms;
    //    @BindView(R.id.fragment_layout)
//    RelativeLayout fragmentLayout;
    @BindView(R.id.ctlTitle)
    CollapsingToolbarLayout ctlTitle;
    @BindView(R.id.icon1)
    ImageView icon1;
    @BindView(R.id.jieshu_text)
    TextView jieshuText;
    @BindView(R.id.xuan_layout3)
    LinearLayout xuanLayout3;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_text)
    TextView titleText;

    private SmartRefreshHelper<LimitedBean.ListBean> mSmartRefreshHelper;

    LimitedAdapter_1 adapter;
    int dataTime;

    int page = 1;
    int lastItem = -1;
    int judge = 0;
    int dtime;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, LimitedActivity_1.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_limited_1;
    }

    @Override
    protected LimitedPresenter initPresenter() {
        return new LimitedPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(LimitedActivity_1.this);
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
            int color = changeAlpha(getResources().getColor(R.color.limited_title), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());
            toolbar.setBackgroundColor(color);
            int color1 = changeAlpha(getResources().getColor(R.color.white), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());
            titleText.setTextColor(color1);


        });

        adapter = new LimitedAdapter_1(this);
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(manager3);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setAdapter(adapter);


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getLimiteDatas(page, 20);//下载限时列表
                judge = 0;
                lastItem = -1;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (adapter.getData().size() - 12 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getLimiteDatas(page, 20);//下载限时列表
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 20) {
                        lastItem = adapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getLimiteDatas(page, 20);//下载限时列表
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
    protected void loadData() {
//        presenter.getAppUpdate();//下载时间
        presenter.getLimiteDatas(page, 20);//下载商品详情数据
    }


    /**
     * 获取限时列表数据成功
     *
     * @param code
     * @param limibean
     */
    @Override
    public void getLimiteSuccess(int code, LimitedBean limibean) {
        Log.e("获取限时列表数据", "==成功=code=" + code);

        smartRefreshLayout.finishRefresh();
        this.limibean = limibean;
        if (judge == 0) {
            adapter.setData(limibean.getList());
        } else {
            adapter.addData(limibean.getList());
        }
        String startTime = DateUtil.getCurrentTime(Long.parseLong(limibean.getList().get(0).getPromote_start_date()));
        String endTime = DateUtil.getCurrentTime(Long.parseLong(limibean.getList().get(0).getPromote_end_date()));
        dateStareEndText.setText(startTime + " 到 " + endTime);

        dtime = limibean.getList().get(0).getSurplus_time();
        //计算秒杀倒计时---ms
        handler.sendEmptyMessageDelayed(0, 1000);


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dtime = dtime - 1;
            long days = dtime / (60 * 60 * 24);
            long hours1 = dtime % (60 * 60 * 24) / (60 * 60);
            long minutess1 = dtime % (60 * 60) / 60;
            long secondss1 = dtime % 60;

            String dayss;
            String hourss;
            String minutess;
            String secondss;
            if (days < 10) {
                dayss = "0" + days;
            } else {
                dayss = String.valueOf(days);
            }

            if (hours1 < 10) {
                hourss = "0" + hours1;
            } else {
                hourss = String.valueOf(hours1);
            }

            if (minutess1 < 10) {
                minutess = "0" + minutess1;
            } else {
                minutess = String.valueOf(minutess1);
            }

            if (secondss1 < 10) {
                secondss = "0" + secondss1;
            } else {
                secondss = String.valueOf(secondss1);
            }
            homeXianshiTimeTextDays.setText(dayss);
            homeXianshiTimeTextHours.setText(hourss);
            homeXianshiTimeTextMinutes.setText(minutess);
            homeXianshiTimeTextSeconds.setText(secondss);

            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (dtime <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 获取数据失败
     *
     * @param code
     * @param msg
     */

    @Override
    public void getLimiteFail(int code, String msg) {
//        if (code == 3000) {
//            LimitedBean data = new LimitedBean();
//            adapter.setData(data.getList());
//            presenter.dismissLoading();
//        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
