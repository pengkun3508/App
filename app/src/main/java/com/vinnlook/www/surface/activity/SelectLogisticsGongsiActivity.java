package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.AddressEvent;
import com.vinnlook.www.event.LogisticsEvent;
import com.vinnlook.www.surface.adapter.LogisticsListAdapter;
import com.vinnlook.www.surface.bean.WaybillListBean;
import com.vinnlook.www.surface.mvp.presenter.SelectLogisticsPresenter;
import com.vinnlook.www.surface.mvp.view.SelectLogisticsView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 选择物流公司
 * @Time:2021/2/4$
 * @Author:pk$
 */
public class SelectLogisticsGongsiActivity extends BaseActivity<SelectLogisticsPresenter> implements SelectLogisticsView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.logistics_recycler)
    RecyclerView logisticsRecycler;
    LogisticsListAdapter adapter;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, SelectLogisticsGongsiActivity.class);
        context.startActivity(intent);

    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_logistics;
    }

    @Override
    protected SelectLogisticsPresenter initPresenter() {
        return new SelectLogisticsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        logisticsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LogisticsListAdapter(this);
        logisticsRecycler.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                new LogisticsEvent(adapter.getData().get(position).getId(), adapter.getData().get(position).getValue()).post();
                finish();
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getLogisticsList();//下载物流公司列表

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
    public void getLogisticsListSuccess(int code, List<WaybillListBean> data) {
        adapter.setData(data);
    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param
     */
    @Override
    public void getLogisticsListFail(int code, String msg) {

    }
}
