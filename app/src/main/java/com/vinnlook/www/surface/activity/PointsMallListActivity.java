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
import com.vinnlook.www.surface.adapter.PointsMallListAdapter;
import com.vinnlook.www.surface.bean.PointsMallListBean;
import com.vinnlook.www.surface.mvp.presenter.PointsMallListPresenter;
import com.vinnlook.www.surface.mvp.view.PointsMallListView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:获取积分列表
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class PointsMallListActivity extends BaseActivity<PointsMallListPresenter> implements PointsMallListView {


    @BindView(R.id.saving_order_recyclerView)
    RecyclerView savingOrderRecyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    int page = 1;
    int lastItem = -1;
    int judge = 0;

    PointsMallListAdapter adapter;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;

    List<PointsMallListBean.ListBean> listBean;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, PointsMallListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_saving_order;
    }

    @Override
    protected PointsMallListPresenter initPresenter() {
        return new PointsMallListPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(PointsMallListActivity.this);
        actionBar.setTitle("积分列表");

        savingOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PointsMallListAdapter();
        savingOrderRecyclerView.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                OrderDetailsActivity.startSelf(PointsMallListActivity.this, adapter.getData().get(position).getOrder_id());
            }
        });
        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getPointsMallList(page, 10);//下载订单列表数据
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
                        presenter.getPointsMallList(page, 10);//下载订单列表数据
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getPointsMallList(page, 10);//下载订单列表数据
                    }
                }
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getPointsMallList(page, 10);//下载首页数据

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 列表数据下载成功
     *
     * @param code
     * @param data
     */

    @Override
    public void getPointsMallListSuccess(int code, PointsMallListBean data) {
        listBean = data.getList();
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
    public void getPointsMallListFail(int code, String msg) {

    }
}
