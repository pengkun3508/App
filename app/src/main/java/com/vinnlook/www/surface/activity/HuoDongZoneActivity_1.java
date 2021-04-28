package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.HuoDongContent_1Adapter;
import com.vinnlook.www.surface.adapter.HuoDongTitle_1Adapter;
import com.vinnlook.www.surface.bean.HuoDong2Bean;
import com.vinnlook.www.surface.mvp.presenter.HuoDongZone_1Presenter;
import com.vinnlook.www.surface.mvp.view.HuoDongZone_1View;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:新--活动页面
 * @Time:2021/3/8$
 * @Author:pk$
 */
public class HuoDongZoneActivity_1 extends BaseActivity<HuoDongZone_1Presenter> implements HuoDongZone_1View {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.title_recycler)
    RecyclerView titleRecycler;
    @BindView(R.id.content_recycler)
    RecyclerView contentRecycler;

    HuoDongTitle_1Adapter adapter;
    HuoDongContent_1Adapter adapter2;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, HuoDongZoneActivity_1.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.huo_dong_activity;
    }

    @Override
    protected HuoDongZone_1Presenter initPresenter() {
        return new HuoDongZone_1Presenter();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        titleRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HuoDongTitle_1Adapter(this);
        titleRecycler.setAdapter(adapter);

        contentRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        adapter2 = new HuoDongContent_1Adapter(this);
        contentRecycler.setAdapter(adapter2);

    }

    @Override
    protected void loadData() {
        presenter.getActivityList();

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
    public void getActivityListSuccess(int code, List<HuoDong2Bean> data) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setMark("0");
        }
        data.get(0).setMark("1");
        adapter.setData(data);
        adapter2.setData(data.get(0).getActive_list());
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                for (int i = 0; i < adapter.getData().size(); i++) {
                    adapter.getData().get(i).setMark("0");
                }
                adapter.getData().get(position).setMark("1");
                adapter.notifyDataSetChanged();
                adapter2.setData(adapter.getData().get(position).getActive_list());
            }
        });

    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getActivityListFail(int code, String msg) {

    }
}
