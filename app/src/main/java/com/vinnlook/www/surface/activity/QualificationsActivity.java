package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.QualificationsAdapter;
import com.vinnlook.www.surface.bean.CertifyListBean;
import com.vinnlook.www.surface.mvp.presenter.QualificationsPresenter;
import com.vinnlook.www.surface.mvp.view.QualificationsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:资格证书
 * @Time:2021/4/19$
 * @Author:pk$
 */
public class QualificationsActivity extends BaseActivity<QualificationsPresenter> implements QualificationsView {


    QualificationsAdapter adapter;
    @BindView(R.id.qualifications_recycler)
    RecyclerView qualificationsRecycler;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, QualificationsActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qualifications;
    }

    @Override
    protected QualificationsPresenter initPresenter() {
        return new QualificationsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(this, true);

        adapter = new QualificationsAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        qualificationsRecycler.setLayoutManager(manager1);
        qualificationsRecycler.setAdapter(adapter);
        //适配器的点击事件适配器要这样写
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (!adapter.getData().get(position).getUrl().equals("")) {
                    WebActivity_1.startSelf(QualificationsActivity.this, adapter.getData().get(position).getUrl());
                }

            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getCertifyList();
    }

    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getCertifyListSuccess(int code, List<CertifyListBean> data) {
        adapter.setData(data);

    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getCertifyListFail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
