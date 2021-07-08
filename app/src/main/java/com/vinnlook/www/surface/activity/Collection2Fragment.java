package com.vinnlook.www.surface.activity;

import android.util.Log;
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
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.http.model.CollectionList2Bean;
import com.vinnlook.www.http.model.CollectionListBean;
import com.vinnlook.www.surface.adapter.Collection2Adapter;
import com.vinnlook.www.surface.mvp.presenter.CollectionPresenter;
import com.vinnlook.www.surface.mvp.view.CollectionView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description:
 * @Time:2021/7/8$
 * @Author:pk$
 */
public class Collection2Fragment extends BaseFragment<CollectionPresenter> implements CollectionView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    List<CollectionList2Bean.ListBean> getList = new ArrayList<>();
    int page = 1;
    int lastItem = -1;
    int judge = 0;
    private Collection2Adapter adapter;

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
        CacheActivity.addActivity(getActivity());
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        adapter = new Collection2Adapter(getActivity());

        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager2);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(3, 5));
        recyclerView.setAdapter(adapter);
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
                //1:主题下的文章；2：新挖宝藏；3：热销安利;4:精选眼图
                if (adapter.getData().get(position).getTheme_type().equals("1")) {
                    ArticleDetailsActivity.startSelf(getActivity(), adapter.getData().get(position).getArticle_id());
                } else if (adapter.getData().get(position).getTheme_type().equals("4")) {
                    SelectEyeChartActivity.startSelf(getActivity(), adapter.getData().get(position).getArticle_id());

                }

            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getCollectionList2Data(page, 20, "2");//下载收藏列表
    }


    //商品
    @Override
    public void getCollectionListSuccess(int code, CollectionListBean data) {


    }

    //商品
    @Override
    public void getCollectionListFail(int code, String msg) {


    }

    /**
     * 文章
     *
     * @param code
     * @param data
     */
    @Override
    public void getCollectionList2Success(int code, CollectionList2Bean data) {
        smartRefreshLayout.finishRefresh();
        getList = data.getList();
        if (judge == 0) {
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }
    }

    /**
     * 文章
     *
     * @param code
     * @param msg
     */
    @Override
    public void getCollectionList2Fail(int code, String msg) {
        Log.e("下载收藏列表失败", "=文章--code==" + code);
        Log.e("下载收藏列表失败", "=文章--msg==" + msg);
    }


}
