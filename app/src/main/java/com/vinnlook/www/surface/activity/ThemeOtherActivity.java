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
import com.vinnlook.www.surface.adapter.ThemeOtherListAdapter;
import com.vinnlook.www.surface.bean.ThemeOtherListBean;
import com.vinnlook.www.surface.mvp.presenter.ThemeOtherPresenter;
import com.vinnlook.www.surface.mvp.view.ThemeOtherView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:主题-其他列表页面
 * @Time:2021/7/1$
 * @Author:pk$
 */
public class ThemeOtherActivity extends BaseActivity<ThemeOtherPresenter> implements ThemeOtherView {

    static String title;
    static int type;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.other_liss_recycler)
    RecyclerView otherLissRecycler;


    ThemeOtherListAdapter themeOtherAdapter;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    int page = 1;
    int lastItem = -1;
    int judge = 0;


    public static void startSelf(Context context, String titles, int types) {
        Intent intent = new Intent(context, ThemeOtherActivity.class);
        context.startActivity(intent);
        title = titles;
        type = types;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_list;
    }

    @Override
    protected ThemeOtherPresenter initPresenter() {
        return new ThemeOtherPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(this, true);
        actionBar.setTitle(title);

        themeOtherAdapter = new ThemeOtherListAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 3);
        otherLissRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        otherLissRecycler.addItemDecoration(new SpaceItemDecoration(5, 10));
        otherLissRecycler.setLayoutManager(manager3);
        otherLissRecycler.setNestedScrollingEnabled(false);
        otherLissRecycler.setHasFixedSize(true);
        otherLissRecycler.setAdapter(themeOtherAdapter);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getThemeOtherList(page, 20, type);
                judge = 0;
            }
        });

        otherLissRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (themeOtherAdapter.getData().size() - 12 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getThemeOtherList(page, 20, type);

                    }
                } else {
                    if (themeOtherAdapter.getData().size() - lastItem > 20) {
                        lastItem = themeOtherAdapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getThemeOtherList(page, 20, type);

                    }
                }
            }
        });
        themeOtherAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ThemeOtherDetailsActivity.startSelf(getActivity(), themeOtherAdapter.getData().get(position).getId());
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getThemeOtherList(1, 20, type);

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
    public void getThemeOtherListSuccess(int code, ThemeOtherListBean data) {

        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            themeOtherAdapter.setData(data.getList());
        } else {
            themeOtherAdapter.addData(data.getList());
        }
    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getThemeOtherListFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }
}
