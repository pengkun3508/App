package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
import com.vinnlook.www.surface.adapter.NoticeAdapter;
import com.vinnlook.www.surface.bean.NoticeListBean;
import com.vinnlook.www.surface.mvp.presenter.NoticePresenter;
import com.vinnlook.www.surface.mvp.view.NoticeView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:公告
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class NoticeActivity extends BaseActivity<NoticePresenter> implements NoticeView {


    NoticeAdapter adapter;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    int page = 1;
    int lastItem = -1;
    int judge = 0;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, NoticeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected NoticePresenter initPresenter() {
        return new NoticePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice;
    }


    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NoticeAdapter(this);
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(manager2);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                WebActivity.startSelf(getActivity(), adapter.getData().get(position).getInfo_url());
            }
        });

        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getMessageList(page, 10);
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
                        presenter.getMessageList(page, 10);
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getMessageList(page, 10);
                    }
                }
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getMessageList(1, 10);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 请求列表成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getMessageListSuccess(int code, NoticeListBean data) {
//        adapter.setData(data.getList());
        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }

    }

    /**
     * 请求列表失败
     *
     * @param code
     * @param
     */
    @Override
    public void getMessageListFail(int code, String msg) {

    }
}
