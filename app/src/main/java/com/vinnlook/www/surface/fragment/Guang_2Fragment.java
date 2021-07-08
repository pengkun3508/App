package com.vinnlook.www.surface.fragment;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.surface.adapter.Guang_2_2Adapter;
import com.vinnlook.www.surface.bean.GuangSelectBean;
import com.vinnlook.www.surface.mvp.presenter.Guang_2FragmentPresenter;
import com.vinnlook.www.surface.mvp.view.Guang_2FragmentView;
import com.vinnlook.www.utils.StaggeredDividerItemDecoration;

import butterknife.BindView;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public class Guang_2Fragment extends BaseFragment<Guang_2FragmentPresenter> implements Guang_2FragmentView {


    @BindView(R.id.selected_recy)
    RecyclerView selectedRecy;

    Guang_2_2Adapter guang2Adapter;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    int page = 1;
    int lastItem = -1;
    int judge = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_guang_2;
    }

    @Override
    protected Guang_2FragmentPresenter initPresenter() {
        return new Guang_2FragmentPresenter();
    }

    @Override
    protected void initView() {

//        guang2Adapter = new Guang_2Adapter(getActivity());
        guang2Adapter = new Guang_2_2Adapter(getActivity());
//        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        //垂直方向的2列
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //防止Item切换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        selectedRecy.setLayoutManager(layoutManager);
        selectedRecy.setNestedScrollingEnabled(false);
        selectedRecy.setHasFixedSize(true);
        selectedRecy.setAdapter(guang2Adapter);
//
        final int spanCount = 2;
        selectedRecy.addItemDecoration(new StaggeredDividerItemDecoration(getActivity(), 0, spanCount));

        //解决底部滚动到顶部时，顶部item上方偶尔会出现一大片间隔的问题
        selectedRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int[] first = new int[spanCount];
                layoutManager.findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    layoutManager.invalidateSpanAssignments();
                }
            }
        });


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
//                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod);//下载列表数据
                presenter.getSelectData(page, 20);
                judge = 0;
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        judge = 1;
                        presenter.getSelectData(page, 20);
                    }
                }, 2000);
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getSelectData(1, 20);
    }


    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getSelectDataSuccess(int code, GuangSelectBean data) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
//        if (data.getList().size() > 0) {
//            selectedRecy.setVisibility(View.VISIBLE);
            if (judge == 0) {
                guang2Adapter.replaceAll(data.getList());
            } else {
                guang2Adapter.addData(guang2Adapter.getItemCount(), data.getList());
            }
//        } else {
//            selectedRecy.setVisibility(View.GONE);
//        }


    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getSelectDataFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
}
