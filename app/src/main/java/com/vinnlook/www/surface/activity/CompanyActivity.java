package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.mvp.presenter.CompanyPresenter;
import com.vinnlook.www.surface.mvp.view.CompanyView;
import com.yanzhenjie.permission.Permission;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:关于Vinnlook
 * @Time:2020/12/29$
 * @Author:pk$
 */
public class CompanyActivity extends BaseActivity<CompanyPresenter> implements CompanyView {


    @BindView(R.id.about_call)
    RelativeLayout aboutCall;
    @BindView(R.id.phone_tel)
    TextView phoneTel;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, CompanyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company;
    }

    @Override
    protected CompanyPresenter initPresenter() {
        return new CompanyPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        aboutCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PermissionHelper.with(getContext()).permissions(Permission.CALL_PHONE)
                        .request(new PermissionHelper.PermissionListener() {
                            @Override
                            public void onSuccess() {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                Uri data = Uri.parse("tel:" + phoneTel.getText().toString());
                                intent.setData(data);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailed() {

                            }
                        });
            }
        });


    }

    @Override
    protected void loadData() {
//        presenter.getCollectionListData(page, 10);//下载收藏列表
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


    }


}
