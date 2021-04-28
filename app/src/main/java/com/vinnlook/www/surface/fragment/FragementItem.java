package com.vinnlook.www.surface.fragment;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.http.model.CommodityListBean;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.adapter.CommodityAdapter;
import com.vinnlook.www.surface.mvp.presenter.ItemFragmentPresenter;
import com.vinnlook.www.surface.mvp.view.ItemFragmentView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import butterknife.BindView;

/**
 * @Description:
 * @Time:2021/1/12$
 * @Author:pk$
 */
public class FragementItem extends BaseFragment<ItemFragmentPresenter> implements ItemFragmentView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    Context context;
    String getBrand_id;
    String getFlood;
    String getPieces;
    String type;

    int mark = 0;
    int page = 1;
    int lastItem = -1;
    int judge = 0;

    CommodityAdapter adapter;


    public FragementItem(Context contexts, String getBrand_ids, String getFloods, String getPiecess, String types) {
        context = contexts;
        getBrand_id = getBrand_ids;
        getFlood = getFloods;
        getPieces = getPiecess;
        type = types;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.item_fragment;
    }

    @Override
    protected ItemFragmentPresenter initPresenter() {
        return new ItemFragmentPresenter();
    }

    @Override
    protected void initView() {


        adapter = new CommodityAdapter(getActivity());
        final GridLayoutManager manager5 = new GridLayoutManager(getActivity(), 1);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setLayoutManager(manager5);
        recyclerView.setAdapter(adapter);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getCommodityList(page, 20, getBrand_id, getFlood, getPieces,type);//
                judge = 0;
            }
        });


        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getCommodityList(page, 20, getBrand_id, getFlood, getPieces,type);//下载商品详情数据
                judge = 0;
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();//找到最后完全可见的item位置
                if (adapter.getData().size() - 12 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getCommodityList(page, 20, getBrand_id, getFlood, getPieces,type);//下载商品详情数据
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 20) {
                        lastItem = adapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getCommodityList(page, 20, getBrand_id, getFlood, getPieces,type);//下载商品详情数据
                    }
                }
            }
        });


        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());

            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getCommodityList(page, 20, getBrand_id, getFlood, getPieces,type);//下载商品详情数据
    }

    @Override
    public void getCommodityListSuccess(int code, CommodityListBean data) {
        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            adapter.setData(data.getList());
            adapter.notifyDataSetChanged();
        } else {
            adapter.addData(data.getList());
        }


    }

    @Override
    public void getCommodityListFail(int code, String msg) {

    }
}
