package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.surface.mvp.presenter.PayResultsPresenter;
import com.vinnlook.www.surface.mvp.view.PayResultsView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description:支付结果
 * @Time:2020/5/27 10:16
 * @Author:pk
 */
public class PayResultsActivity extends BaseActivity<PayResultsPresenter> implements PayResultsView {


    @BindView(R.id.pay_see_order)
    RoundTextView paySeeOrder;
    @BindView(R.id.pay_return_home)
    RoundTextView payReturnHome;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, PayResultsActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_resylts;
    }

    @Override
    protected PayResultsPresenter initPresenter() {
        return new PayResultsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.pay_see_order, R.id.pay_return_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_see_order://查看订单
                AllOrderActivity.startSelf(this, 2);//返回待收货
                finish();
                break;
            case R.id.pay_return_home://返回首页
                new MainHomeActivityEvent("1").post();
                finish();
                break;

        }
    }


}
