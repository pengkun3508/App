package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.http.model.SiginOrderBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.AllOrderAdapter;
import com.vinnlook.www.surface.mvp.presenter.AllOrderPresenter;
import com.vinnlook.www.surface.mvp.view.AllOrderView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.SmartRefreshHelper;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description我的订单
 * @Time:2020/4/2$
 * @Author:pk$
 */
public class AllOrderActivity extends BaseActivity<AllOrderPresenter> implements AllOrderView, AllOrderAdapter.OnItemClickListener {

    private static int mark;//判断入口
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.imageButton1)
    RadioButton imageButton1;
    @BindView(R.id.imageButton2)
    RadioButton imageButton2;
    @BindView(R.id.imageButton3)
    RadioButton imageButton3;
    @BindView(R.id.imageButton4)
    RadioButton imageButton4;
    @BindView(R.id.imageButton5)
    RadioButton imageButton5;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.view_2)
    View view2;
    @BindView(R.id.view_3)
    View view3;
    @BindView(R.id.view_4)
    View view4;
    @BindView(R.id.view_5)
    View view5;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    AllOrderListBean alOrderListBean;
    @BindView(R.id.imageButton6)
    RadioButton imageButton6;
    @BindView(R.id.view_6)
    View view6;

    private SmartRefreshHelper<AllOrderListBean.ListBean> mSmartRefreshHelper;

    AllOrderAdapter adapter;
    int poss;//取消订单POS

    int page = 1;
    int lastItem = -1;
    int judge = 0;


    public static void startSelf(Context context, int type) {
        Intent intent = new Intent(context, AllOrderActivity.class);
        context.startActivity(intent);
        mark = type;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_all_order;
    }

    @Override
    protected AllOrderPresenter initPresenter() {
        return new AllOrderPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AllOrderAdapter(getActivity(), this, mark);
        recyclerView.setAdapter(adapter);
//        mSmartRefreshHelper = SmartRefreshHelper.with(smartRefreshLayout, adapter)
//                .setPerPageCount(Config.ONE_PAGE_ITEM_MAX_COUNT_DEFAULT)
//                .init(new SmartRefreshHelper.RefreshCallback() {
//                    @Override
//                    public void doRequestData(int page) {
//                        presenter.getOrderListData(page, 10, mark);//下载订单列表数据
//                    }
//
//                });

        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getOrderListData(page, 10, mark);//下载订单列表数据
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
                        presenter.getOrderListData(page, 10, mark);//下载订单列表数据
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getOrderListData(page, 10, mark);//下载订单列表数据
                    }
                }
            }
        });

        imageButton1.setChecked(true);

        if (mark == 0) {//全部
            selsectBtn(imageButton1, view1);
        } else if (mark == 1) {//待付款
            selsectBtn(imageButton2, view2);
        } else if (mark == 2) {//待发货
            selsectBtn(imageButton6, view6);
        } else if (mark == 3) {//待收货
            selsectBtn(imageButton3, view3);
        } else if (mark == 4) {//待评价
            selsectBtn(imageButton4, view4);
        } else if (mark == 5) {//退换货
            selsectBtn(imageButton5, view5);
        }
    }

    private void selsectBtn(RadioButton imageButton, View view) {
        imageButton.setChecked(true);
        view.setBackgroundColor(getResources().getColor(R.color.them));
    }


    @Override
    protected void loadData() {
//        presenter.getAppUpdate();//下载时间
        presenter.getOrderListData(1, 10, mark);//下载购物车列表数据
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getOrderListData(1, 10, mark);//下载订单列表数据
    }

    @OnClick({R.id.imageButton1, R.id.imageButton2, R.id.imageButton3, R.id.imageButton4, R.id.imageButton5, R.id.imageButton6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageButton1://全部
                mark = 0;
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.them));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                view5.setBackgroundColor(getResources().getColor(R.color.white));
                view6.setBackgroundColor(getResources().getColor(R.color.white));
                presenter.getOrderListData(page, 10, mark);//下载订单列表数据

                break;
            case R.id.imageButton2://待付款
                mark = 1;
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.them));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                view5.setBackgroundColor(getResources().getColor(R.color.white));
                view6.setBackgroundColor(getResources().getColor(R.color.white));
                presenter.getOrderListData(page, 10, mark);//下载订单列表数据
                break;
            case R.id.imageButton6://待发货
                mark = 2;
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                view5.setBackgroundColor(getResources().getColor(R.color.white));
                view6.setBackgroundColor(getResources().getColor(R.color.them));
                presenter.getOrderListData(page, 10, mark);//下载订单列表数据
                break;
            case R.id.imageButton3://待收货
                mark = 3;
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.them));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                view5.setBackgroundColor(getResources().getColor(R.color.white));
                view6.setBackgroundColor(getResources().getColor(R.color.white));
                presenter.getOrderListData(page, 10, mark);//下载订单列表数据
                break;
            case R.id.imageButton4://待评价
                mark = 4;
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.them));
                view5.setBackgroundColor(getResources().getColor(R.color.white));
                view6.setBackgroundColor(getResources().getColor(R.color.white));
                presenter.getOrderListData(page, 10, mark);//下载订单列表数据
                break;
            case R.id.imageButton5://退换货
                mark = 5;
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                view5.setBackgroundColor(getResources().getColor(R.color.them));
                view6.setBackgroundColor(getResources().getColor(R.color.white));
                presenter.getOrderListData(page, 10, mark);//下载订单列表数据
                break;

        }
    }

    /**
     * Item 点击事件--进入详情
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClicked(View view, int position) {
        Log.e("onItemClicked", "==position===" + position);
        OrderDetailsActivity.startSelf(getActivity(), adapter.getData().get(position).getOrder_id());

    }

    /**
     * 删除订单
     */
    @Override
    public void onCelearOrderClicked(String order_id, int adapterPosition, String type) {
        poss = adapterPosition;
        presenter.getCelearOrderData(order_id, String.valueOf(mark));

    }

    /**
     * 取消订单
     */
    @Override
    public void onCelearOrderClicked_1(String order_id, int adapterPosition, String type) {


        poss = adapterPosition;
        presenter.getCelearOrderData_1(order_id, String.valueOf(mark));

    }

    /**
     * 签收订单
     */
    @Override
    public void onSignInOrderClicked(String order_id, int adapterPosition) {
        Log.e("签收订单", "==order_id=" + order_id);
        presenter.getSignInOrderData(order_id);

    }

    /**
     * 下载时间成功
     *
     * @param
     * @param
     */
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    /**
     * 下载时间失败
     *
     * @param
     * @param
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * 下载列表数据成功
     *
     * @param
     * @param
     */
    @Override
    public void getOrderListSuccess(int code, AllOrderListBean data) {
        alOrderListBean = data;
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
     * @param
     * @param
     */
    @Override
    public void getOrderListFail(int code, String msg) {
//        if (code == 3000) {
//            AllOrderListBean data = new AllOrderListBean();
//            adapter.setData(data.getList());
//            presenter.dismissLoading();
//        }
    }


    /**
     * 取消订单成功
     *
     * @param
     * @param
     */
    @Override
    public void getCelearOrderSuccess_1(int code, AllOrderListBean data) {
        adapter.setData(data.getList());
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "订单取消成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 取消订单失败
     *
     * @param
     * @param
     */
    @Override
    public void getCelearOrderFail_1(int code, String msg) {
        Toast.makeText(this, "订单取消失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 删除订单成功
     *
     * @param
     * @param
     */
    @Override
    public void getCelearOrderSuccess(int code, AllOrderListBean data) {
//        adapter.removeData(poss);
//        mSmartRefreshHelper.requestFirstPage(false);
        adapter.setData(data.getList());
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "订单删除成功", Toast.LENGTH_SHORT).show();

    }

    /**
     * 删除订单失败
     *
     * @param
     * @param
     */
    @Override
    public void getCelearOrderFail(int code, String msg) {
//        if (code == 3000) {
//            AllOrderListBean data = new AllOrderListBean();
//            adapter.setData(data.getList());
//            presenter.dismissLoading();
//        }
        Toast.makeText(this, "订单删除失败", Toast.LENGTH_SHORT).show();

    }

    /**
     * 签收订单成功
     *
     * @param
     * @param
     */
    @Override
    public void getSignInOrderSuccess(int code, SiginOrderBean data) {
        Log.e("签收订单成功", "==code==" + code);
        presenter.getOrderListData(page, 10, mark);//下载订单列表数据
//        Toast.makeText(this, "订单签收成功", Toast.LENGTH_SHORT).show();

    }

    /**
     * 签收订单失败
     *
     * @param
     * @param
     */
    @Override
    public void getSignInOrderFail(int code, String msg) {
        Log.e("签收订单失败", "==code==" + code);
//        if (code == 3000) {
//            presenter.getOrderListData(page, 10, mark);//下载订单列表数据
//        }else  if (code == 5000){
//            presenter.getOrderListData(page, 10, mark);//下载订单列表数据
//        }

    }


}
