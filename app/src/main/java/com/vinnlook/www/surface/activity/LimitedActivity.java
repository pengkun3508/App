package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.LimitedBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.LimitedAdapter;
import com.vinnlook.www.surface.mvp.presenter.LimitedPresenter;
import com.vinnlook.www.surface.mvp.view.LimitedView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.SmartRefreshHelper;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * @Description:限时列表
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class LimitedActivity extends BaseActivity<LimitedPresenter> implements LimitedView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    LimitedBean limibean;

    private SmartRefreshHelper<LimitedBean.ListBean> mSmartRefreshHelper;

    LimitedAdapter adapter;
    int dataTime;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;

    int mark = 0;
    int page = 1;
    int lastItem = -1;
    int judge = 0;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, LimitedActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_1;
    }

    @Override
    protected LimitedPresenter initPresenter() {
        return new LimitedPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(LimitedActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        actionBar.setTitle("限时折扣");

        adapter = new LimitedAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(manager3);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setAdapter(adapter);


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getLimiteDatas(page, 10);//下载限时列表
                judge = 0;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (adapter.getData().size() - 5 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getLimiteDatas(page, 10);//下载限时列表
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getLimiteDatas(page, 10);//下载限时列表
                    }
                }
            }
        });

        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr(),"");



            }
        });


    }

    @Override
    protected void loadData() {
//        presenter.getAppUpdate();//下载时间
        presenter.getLimiteDatas(page, 10);//下载商品详情数据
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
        Log.e("获取限时列表数据", "==成功=limibean=" + limibean);
        smartRefreshLayout.finishRefresh();
        this.limibean = limibean;
        if (judge == 0) {
            adapter.setData(limibean.getList());
        } else {
            adapter.addData(limibean.getList());
        }
//        计算秒杀倒计时---ms
        handler.sendEmptyMessageDelayed(0, 1000);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (int i = 0; i < adapter.getData().size(); i++) {
                dataTime = Integer.valueOf(adapter.getData().get(i).getSurplus_time());
                dataTime=dataTime-1;
//                dataTime = dataTime - 1000;
                adapter.getData().get(i).setSurplus_time(dataTime);

            }
            //设置倒计时
            adapter.setData(adapter.getData());
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
            if (dataTime <= 0) {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };

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


}
