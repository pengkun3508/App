package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.SavingOrdersAdapter;
import com.vinnlook.www.surface.bean.SavingOrderBean;
import com.vinnlook.www.surface.mvp.presenter.SavingOrdersPresenter;
import com.vinnlook.www.surface.mvp.view.SavingOrdersView;
import com.vinnlook.www.utils.CacheActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:节省订单
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class SavingOrdersActivity extends BaseActivity<SavingOrdersPresenter> implements SavingOrdersView {


    @BindView(R.id.saving_order_recyclerView)
    RecyclerView savingOrderRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    int page = 1;
    int lastItem = -1;
    int judge = 0;

    SavingOrdersAdapter adapter;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, SavingOrdersActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_saving_order;
    }

    @Override
    protected SavingOrdersPresenter initPresenter() {
        return new SavingOrdersPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(SavingOrdersActivity.this);

        savingOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SavingOrdersAdapter();
        savingOrderRecyclerView.setAdapter(adapter);


        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getSavingOrdersList(page, 10);//下载订单列表数据
                judge = 0;
            }
        });

        savingOrderRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (adapter.getData().size() - 5 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getSavingOrdersList(page, 10);//下载订单列表数据
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getSavingOrdersList(page, 10);//下载订单列表数据
                    }
                }
            }
        });


        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                OrderDetailsActivity.startSelf(SavingOrdersActivity.this, adapter.getData().get(position).getOrder_id());
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getSavingOrdersList(page, 10);//下载首页数据

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 列表数据下载成功
     *
     * @param code
     * @param data
     */

    @Override
    public void getSavingOrdersSuccess(int code, SavingOrderBean data) {

        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }

    }

    /**
     * 列表数据下载失败
     *
     * @param code
     * @param
     */
    @Override
    public void getSavingOrdersFail(int code, String msg) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
