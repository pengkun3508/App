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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.RecommendAdapter;
import com.vinnlook.www.surface.bean.HomeGoodsListBean;
import com.vinnlook.www.surface.bean.HomePublicListBean;
import com.vinnlook.www.surface.bean.TypeGoodsBean;
import com.vinnlook.www.surface.mvp.presenter.RecommendPresenter;
import com.vinnlook.www.surface.mvp.view.RecommendView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.SmartRefreshHelper;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.List;

import butterknife.BindView;

/**
 * @Description: 新品推荐
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class RecommendActivity extends BaseActivity<RecommendPresenter> implements RecommendView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    List<TypeGoodsBean.ListBean> listBean;

    private SmartRefreshHelper<TypeGoodsBean.ListBean> mSmartRefreshHelper;

    RecommendAdapter adapter;
    private static String title;
    private static String iD;

    int mark = 0;
    int page = 1;
    int lastItem = -1;
    int judge = 0;

    public static void startSelf(Context context, String titles, String iDs) {
        Intent intent = new Intent(context, RecommendActivity.class);
        context.startActivity(intent);
        title = titles;
        iD = iDs;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_1;
    }

    @Override
    protected RecommendPresenter initPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(RecommendActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        if (title.equals("")) {
            actionBar.setTitle("商品推荐");
        } else {
            actionBar.setTitle(title);
        }

        adapter = new RecommendAdapter(this);
        final GridLayoutManager manager5 = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager5);
        recyclerView.setAdapter(adapter);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getTypeGoods(page, 10, iD);//下载列表数据
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
                        presenter.getTypeGoods(page, 10, iD);//下载列表数据
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getTypeGoods(page, 10, iD);//下载列表数据
                    }
                }
            }
        });

        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(RecommendActivity.this, adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(RecommendActivity.this, adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());

            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getTypeGoods(page, 10, iD);//下载列表数据

    }


    /**
     * 下载时间成功
     *
     * @param code
     * @param version
     */
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {


    }

    /**
     * 下载时间失败
     *
     * @param code
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * 下载列表数据成功
     *
     * @param code
     */
    @Override
    public void getTypeGoodsSuccess(int code, TypeGoodsBean data) {
        listBean = data.getList();
        smartRefreshLayout.finishRefresh();

        if (judge == 0) {
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }


    }

    /**
     * 下载列表数据失败
     *
     * @param code
     */
    @Override
    public void getTypeGoodsFail(int code, String msg) {
//        if (code == 3000) {
//            TypeGoodsBean data = new TypeGoodsBean();
//            adapter.setData(data.getList());
//            presenter.dismissLoading();
//        }
    }

    @Override
    public void getHomePublicSuccess(int code, HomePublicListBean data) {

    }

    @Override
    public void getHomePublicFail(int code, String msg) {

    }

    @Override
    public void getGoodsListSuccess(int code, HomeGoodsListBean data) {

    }


    @Override
    public void getGoodsListFail(int code, String msg) {

    }
}
