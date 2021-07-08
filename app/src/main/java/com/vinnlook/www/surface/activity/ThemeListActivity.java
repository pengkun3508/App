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
import com.vinnlook.www.surface.adapter.ThemeListAdapter;
import com.vinnlook.www.surface.bean.ThemeListBean;
import com.vinnlook.www.surface.mvp.presenter.ThemeListPresenter;
import com.vinnlook.www.surface.mvp.view.ThemeListView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:主题列表
 * @Time:2021/6/30$
 * @Author:pk$
 */
public class ThemeListActivity extends BaseActivity<ThemeListPresenter> implements ThemeListView {

    static String title;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.theme_liss_recycler)

    RecyclerView themeLissRecycler;

    ThemeListAdapter themeAdapter;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    int page = 1;
    int lastItem = -1;
    int judge = 0;

    public static void startSelf(Context context, String titles) {
        Intent intent = new Intent(context, ThemeListActivity.class);
        context.startActivity(intent);
        title = titles;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme_list;
    }

    @Override
    protected ThemeListPresenter initPresenter() {
        return new ThemeListPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(this, true);
        actionBar.setTitle(title);

        themeAdapter = new ThemeListAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        themeLissRecycler.setLayoutManager(manager3);
        themeLissRecycler.setNestedScrollingEnabled(false);
        themeLissRecycler.setHasFixedSize(true);
        themeLissRecycler.setAdapter(themeAdapter);

        themeAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ThemeDetailsActivity.startSelf(ThemeListActivity.this,themeAdapter.getData().get(position).getId());
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
//                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod);//下载列表数据
                presenter.getThemeList(page, 20);
                judge = 0;
            }
        });

        themeLissRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (themeAdapter.getData().size() - 12 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getThemeList(page, 20);

                    }
                } else {
                    if (themeAdapter.getData().size() - lastItem > 20) {
                        lastItem = themeAdapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getThemeList(page, 20);

                    }
                }
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getThemeList(1, 20);

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
    public void getThemeListSuccess(int code, ThemeListBean data) {

        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            themeAdapter.setData(data.getList());
        } else {
            themeAdapter.addData(data.getList());
        }
    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getThemeListFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }
}
