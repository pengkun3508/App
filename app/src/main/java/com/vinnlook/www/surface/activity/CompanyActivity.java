package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.CompanyAdapter;
import com.vinnlook.www.surface.bean.CompanyBean;
import com.vinnlook.www.surface.mvp.presenter.CompanyPresenter;
import com.vinnlook.www.surface.mvp.view.CompanyView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.yanzhenjie.permission.Permission;

import java.util.List;

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
    @BindView(R.id.about_recycler)
    RecyclerView aboutRecycler;
    CompanyAdapter adapter;

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

        //抛期专区
        adapter = new CompanyAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        aboutRecycler.setLayoutManager(manager3);
        aboutRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        aboutRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        aboutRecycler.setAdapter(adapter);


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
        presenter.getCompanyListData();//下载收藏列表
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


    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getCompanyListSuccess(int code, List<CompanyBean> data) {
        adapter.setData(data);
    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param
     */
    @Override
    public void getCompanyListFail(int code, String msg) {

    }
}
