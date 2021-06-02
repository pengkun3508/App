package com.vinnlook.www.surface.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.flyco.roundview.RoundTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.http.model.LimitedBean;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.adapter.LimitedAdapter_2;
import com.vinnlook.www.surface.mvp.presenter.HomeTab3FragmentPresenter;
import com.vinnlook.www.surface.mvp.view.HomeTab3FragmentView;
import com.vinnlook.www.utils.DateUtil;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import butterknife.BindView;

/**
 * @Description:百万补贴
 * @Time:2021/4/1$
 * @Author:pk$
 */
public class HomeTab3Fragment extends BaseFragment<HomeTab3FragmentPresenter> implements HomeTab3FragmentView {


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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    LimitedAdapter_2 adapter;
    LimitedBean limibean;

    int page = 1;
    int lastItem = -1;
    int judge = 0;
    int dtime;


    @Override
    protected int getLayoutId() {
        return R.layout.home_tab_3_fragment;
    }


    @Override
    protected HomeTab3FragmentPresenter initPresenter() {
        return new HomeTab3FragmentPresenter();
    }

    @Override
    protected void initView() {

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
                presenter.getBaiWanList(page, 20);//下载限时列表
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
                        presenter.getBaiWanList(page, 20);//下载限时列表
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 20) {
                        lastItem = adapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getBaiWanList(page, 20);//下载限时列表
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
        presenter.getBaiWanList(page, 20);//下载首页品牌数据
    }


    /**
     * 下载--成功
     *
     * @param code
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
        String startTime = DateUtil.getCurrentTime(Long.parseLong(limibean.getList().get(0).getPromote_start_date()));
        String endTime = DateUtil.getCurrentTime(Long.parseLong(limibean.getList().get(0).getPromote_end_date()));
        dateStareEndText.setText(startTime + " 到 " + endTime);

        dtime = limibean.getList().get(0).getSurplus_time();
        //计算秒杀倒计时---ms
        handler.sendEmptyMessageDelayed(0, 1000);

    }

    /**
     * 下载--失败
     *
     * @param code
     */
    @Override
    public void getLimiteFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
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
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }


}
