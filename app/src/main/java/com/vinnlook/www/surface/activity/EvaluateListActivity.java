package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.Config;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.EvaluateListAdapter;
import com.vinnlook.www.surface.bean.EvaluateListBean;
import com.vinnlook.www.surface.mvp.presenter.EvaluateListPresenter;
import com.vinnlook.www.surface.mvp.view.EvaluateListView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.SmartRefreshHelper;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 评论列表
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class EvaluateListActivity extends BaseActivity<EvaluateListPresenter> implements EvaluateListView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private SmartRefreshHelper<EvaluateListBean.ListBean> mSmartRefreshHelper;

    EvaluateListAdapter commentAdapter;
    static String goods_id;


    public static void startSelf(Context context, String goods_ids) {
        Intent intent = new Intent(context, EvaluateListActivity.class);
        context.startActivity(intent);
        goods_id = goods_ids;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate_list;
    }

    @Override
    protected EvaluateListPresenter initPresenter() {
        return new EvaluateListPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        commentAdapter = new EvaluateListAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager3);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 1)));
        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setHasFixedSize(false);

        recyclerView.setAdapter(commentAdapter);

        mSmartRefreshHelper = SmartRefreshHelper.with(smartRefreshLayout, commentAdapter)
                .setPerPageCount(Config.ONE_PAGE_ITEM_MAX_COUNT_DEFAULT)
                .init(new SmartRefreshHelper.RefreshCallback() {
                    @Override
                    public void doRequestData(int page) {
                        presenter.getEvaluateList(page, 10, goods_id);//下载评论列表
                    }

                });

    }

    @Override
    protected void loadData() {
        presenter.getEvaluateList(1, 10, goods_id);//下载评论列表
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * 评论列表下载成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getEvaluateListSuccess(int code, EvaluateListBean data) {
        mSmartRefreshHelper.onSuccess(2000, data.getList());
//        commentAdapter.setData(data.getList());
//        commentAdapter.notifyDataSetChanged();

    }

    /**
     * 评论列表下载失败
     *
     * @param
     * @param
     */
    @Override
    public void getEvaluateListFail(int code, String msg) {

    }
}
