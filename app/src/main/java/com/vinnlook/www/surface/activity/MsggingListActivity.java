package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.MsggingListAdapter;
import com.vinnlook.www.surface.bean.MsggingListBean;
import com.vinnlook.www.surface.mvp.presenter.MsggingListPresenter;
import com.vinnlook.www.surface.mvp.view.MsggingListView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:
 * @Time:2021/3/3$
 * @Author:pk$
 */
public class MsggingListActivity extends BaseActivity<MsggingListPresenter> implements MsggingListView {
    static String getType;
    static String getName;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.msg_list_recycler)
    RecyclerView msgListRecycler;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    MsggingListAdapter msgAdapter;
    int page = 1;
    int lastItem = -1;
    int judge = 0;


    public static void startSelf(Context context, String getTypes, String getNames) {
        Intent intent = new Intent(context, MsggingListActivity.class);
        context.startActivity(intent);
        getType = getTypes;
        getName = getNames;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.msg_list_activity;
    }

    @Override
    protected MsggingListPresenter initPresenter() {
        return new MsggingListPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        actionBar.getTvTitle().setText(getName);
        msgAdapter = new MsggingListAdapter(getActivity());
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        msgListRecycler.setLayoutManager(manager3);
        msgListRecycler.setNestedScrollingEnabled(false);
        msgListRecycler.setHasFixedSize(true);
        msgListRecycler.setAdapter(msgAdapter);

        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getPushListData(page, 10, getType);//下载订单列表数据
                judge = 0;
            }
        });


        msgListRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (msgAdapter.getData().size() - 5 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getPushListData(page, 10, getType);//下载订单列表数据
                    }
                } else {
                    if (msgAdapter.getData().size() - lastItem > 10) {
                        lastItem = msgAdapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getPushListData(page, 10, getType);//下载订单列表数据
                    }
                }
            }
        });

        msgAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (getType.equals("2")){
                    CouponActivity.startSelf(getContext());
                }
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getPushListData(page, 10, getType);//下载订单列表数据
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getPushListDataSuccess(int code, MsggingListBean data) {

        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            msgAdapter.setData(data.getList());
        } else {
            msgAdapter.addData(data.getList());
        }

    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getPushListDataFail(int code, String msg) {

    }
}
