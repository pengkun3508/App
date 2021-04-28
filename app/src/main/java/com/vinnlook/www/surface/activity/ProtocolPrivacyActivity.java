package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.mvp.presenter.ProtocolPrivacyPresenter;
import com.vinnlook.www.surface.mvp.view.ProtocolPrivacyView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:
 * @Time:2020/7/13$
 * @Author:pk$
 */
public class ProtocolPrivacyActivity extends BaseActivity<ProtocolPrivacyPresenter> implements ProtocolPrivacyView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.protocol_btn)
    LinearLayout protocolBtn;
    @BindView(R.id.privacy_btn)
    LinearLayout privacyBtn;
    @BindView(R.id.pay_btn)
    LinearLayout payBtn;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, ProtocolPrivacyActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocol_privacy;
    }

    @Override
    protected ProtocolPrivacyPresenter initPresenter() {
        return new ProtocolPrivacyPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.protocol_btn, R.id.privacy_btn,R.id.pay_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.protocol_btn://用户协议
                WebActivity.startSelf(getActivity(), "http://shop.jealook.com/v1/html/article-info?id=119");
                break;
            case R.id.privacy_btn://隐私政策
                WebActivity.startSelf(getActivity(), "http://shop.jealook.com/v1/html/article-info?id=117");
                break;
            case R.id.pay_btn://隐私政策
                WebActivity.startSelf(getActivity(), "http://shop.jealook.com/v1/html/article-info?id=154");
                break;
        }
    }
}
