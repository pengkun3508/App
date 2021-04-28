package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.BrowseAdapter;
import com.vinnlook.www.surface.bean.BrowseListBean;
import com.vinnlook.www.surface.mvp.presenter.BrowsePresenter;
import com.vinnlook.www.surface.mvp.view.BrowseView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description:浏览历史
 * @Time:2020/9/9$
 * @Author:pk$
 */
public class BrowseActivity extends BaseActivity<BrowsePresenter> implements BrowseView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    BrowseAdapter adapter;
    List<BrowseListBean.ListBean> getList = new ArrayList<>();

    int page = 1;
    int lastItem = -1;
    int judge = 0;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, BrowseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.browse_activity;
    }

    @Override
    protected BrowsePresenter initPresenter() {
        return new BrowsePresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(BrowseActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);

        adapter = new BrowseAdapter(this);
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager2);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setAdapter(adapter);


        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getBrowseListData(page, 20);//下载收藏列表
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
                        presenter.getBrowseListData(page, 20);//下载收藏列表
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 20) {
                        lastItem = adapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getBrowseListData(page, 20);//下载收藏列表
                    }
                }
            }
        });
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(getActivity(), getList.get(position).getGoods_id(), getList.get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());

            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getBrowseListData(page, 20);//下载收藏列表
    }

    /**
     * @Description:下载收藏列表成功
     * @Time:2020/5/8 15:29
     * @Author:pk
     */
    @Override
    public void getBrowseListSuccess(int code, BrowseListBean data) {
        smartRefreshLayout.finishRefresh();
        getList = data.getList();
        if (judge == 0) {
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }
    }

    /**
     * @Description:下载收藏列表失败
     * @Time:2020/5/8 15:29
     * @Author:pk
     */
    @Override
    public void getBrowseListFail(int code, String msg) {

    }
}
