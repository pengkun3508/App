package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.utils.CacheActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.CollectionListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.CollectionAdapter;
import com.vinnlook.www.surface.mvp.presenter.CollectionPresenter;
import com.vinnlook.www.surface.mvp.view.CollectionView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.SmartRefreshHelper;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:收藏列表
 * @Time:2020/4/14 9:40
 * @Author:pk
 */
public class CollectionActivity extends BaseActivity<CollectionPresenter> implements CollectionView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    private CollectionAdapter adapter;
    private SmartRefreshHelper<CollectionListBean.ListBean> mSmartRefreshHelper;
    List<CollectionListBean.ListBean> getList = new ArrayList<>();
    int mark = 0;
    int page = 1;
    int lastItem = -1;
    int judge = 0;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected CollectionPresenter initPresenter() {
        return new CollectionPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(CollectionActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        adapter = new CollectionAdapter(this);

        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager2);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setAdapter(adapter);

//        mSmartRefreshHelper = SmartRefreshHelper.with(smartRefreshLayout, adapter)
//                .setPerPageCount(Config.ONE_PAGE_ITEM_MAX_COUNT_DEFAULT)
//                .init(new SmartRefreshHelper.RefreshCallback() {
//                    @Override
//                    public void doRequestData(int page) {
//                        presenter.getCollectionListData(page, 10);//下载收藏列表
//                    }
//                });
        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getCollectionListData(page, 20);//下载收藏列表
                judge = 0;
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
                        presenter.getCollectionListData(page, 20);//下载收藏列表
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 20) {
                        lastItem = adapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getCollectionListData(page, 20);//下载收藏列表
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
//        presenter.getCollectionListData(page, 10);//下载收藏列表
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getCollectionListData(page, 20);//下载收藏列表
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * @Description:下载时间成功
     * @Time:2020/5/8 15:29
     * @Author:pk
     */
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    /**
     * @Description:下载时间失败
     * @Time:2020/5/8 15:29
     * @Author:pk
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:下载收藏列表成功
     * @Time:2020/5/8 15:29
     * @Author:pk
     */
    @Override
    public void getCollectionListSuccess(int code, CollectionListBean data) {
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
    public void getCollectionListFail(int code, String msg) {
        Log.e("下载收藏列表失败", "=code==" + code);
        Log.e("下载收藏列表失败", "=msg==" + msg);

//        if (code == 3000) {
//            CollectionListBean data = new CollectionListBean();
//            adapter.setData(data.getList());
//            adapter.notifyDataSetChanged();
//            presenter.dismissLoading();
//        }

    }
}
