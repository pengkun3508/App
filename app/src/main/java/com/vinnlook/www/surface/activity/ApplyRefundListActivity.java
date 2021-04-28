package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.StatusBarUtils;
import com.m7.imkfsdk.KfStartHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.ApplyListAdapter;
import com.vinnlook.www.surface.bean.ApplyListBean;
import com.vinnlook.www.surface.mvp.presenter.ApplyRefundListPresenter;
import com.vinnlook.www.surface.mvp.view.ApplyRefundListView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:退款/售后列表
 * @Time:2020/11/13$
 * @Author:pk$
 */
public class ApplyRefundListActivity extends BaseActivity<ApplyRefundListPresenter> implements ApplyRefundListView {


    ApplyListAdapter adapter;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    int page = 1;
    int lastItem = -1;
    int judge = 0;

    ApplyListBean applyListBean;
    int posions;//客服Item

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, ApplyRefundListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_refund_list;
    }

    @Override
    protected ApplyRefundListPresenter initPresenter() {
        return new ApplyRefundListPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);

        adapter = new ApplyListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getApplyList(page, 10);
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
                        presenter.getApplyList(page, 10);//下载订单列表数据
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getApplyList(page, 10);//下载订单列表数据
                    }
                }
            }
        });
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ApplyDetailsActivity.startSelf(ApplyRefundListActivity.this, adapter.getData().get(position).getOrder_id(), adapter.getData().get(position).getId());
            }
        });


        adapter.setApplyListClickListener(new ApplyListAdapter.ApplyListClickListener() {
            @Override
            public void onApplyKeFuClickListener(int posion) {//联系客服
                posions = posion;

                PermissionHelper.with(getContext()).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request(new PermissionHelper.PermissionListener() {
                            @Override
                            public void onSuccess() {
                                //初始化SDK
                                initSdk();
                            }

                            @Override
                            public void onFailed() {
                            }
                        });

            }

            @Override
            public void onApplySeeDetailsClickListener(int posion) {//查看详情

                ApplyDetailsActivity.startSelf(ApplyRefundListActivity.this, adapter.getData().get(posion).getOrder_id(), adapter.getData().get(posion).getId());

            }
        });

    }

    @Override
    protected void loadData() {
//        presenter.getApplyList(1, 10);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getApplyList(1, 10);
    }

    /**
     * 成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getApplyListSuccess(int code, ApplyListBean data) {

        applyListBean = data;
        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }

    }

    /**
     * 失败
     *
     * @param code
     * @param
     */
    @Override
    public void getApplyListFail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 初始化SDK
     */
    private void initSdk() {
        //设置sdk 显示语言版本
//        initLanguage("en");
        /*
          第一步:初始化help
         */
        final KfStartHelper helper = new KfStartHelper(this);
        /*
          商品信息实例，若有需要，请参照此方法；
         */
//        handleCardInfo(helper);
         /*
          新卡片类型示例，若有需要，请参照此方法；
         */
//        handleNewCardInfo(helper);
        /*
          第二步:设置参数
          初始化sdk方法，必须先调用该方法进行初始化后才能使用IM相关功能
          @param accessId       接入id（需后台配置获取）
          @param userName       用户名
          @param userId         用户id
         */
        /*
         * 修改会话页面 注销按钮 文案。建议两个 文字
         */
//        helper.setChatActivityLeftText("注销");

        /**
         * 传递orderid和头像路径
         */
        helper.setOrderHead(adapter.getData().get(posions).getOrder_id(), UserUtils.getInstance().getUserInfo().getImg_url());
        /*
         * 修改会话页面 是否需要 emoji表情 按钮。
         */
//        helper.setChatActivityEmoji(true);

        String userID = UserUtils.getInstance().getUserInfo().getUser_id();
        if (UserUtils.getInstance().getUserInfo().getUser_id().length() < 2) {
            userID = "0" + userID;
        }
        helper.initSdkChat("97e623b0-f404-11ea-938a-2d31778d2422", UserUtils.getInstance().getUserInfo().getUser_name(), userID);
    }

}
