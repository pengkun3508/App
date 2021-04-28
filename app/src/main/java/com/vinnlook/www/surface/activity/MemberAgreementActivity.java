package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.mvp.presenter.MemberAgreementPresenter;
import com.vinnlook.www.surface.mvp.view.MemberAgreementView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description://会员协议
 * @Time:2021/3/11$
 * @Author:pk$
 */
public class MemberAgreementActivity extends BaseActivity<MemberAgreementPresenter> implements MemberAgreementView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.web)
    WebView web;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, MemberAgreementActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_agreement;
    }

    @Override
    protected MemberAgreementPresenter initPresenter() {
        return new MemberAgreementPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);

    }

    @Override
    protected void loadData() {
        web.loadUrl("https://shop.jealook.com/v4/html/article-info-one?id=157");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
    }
}
