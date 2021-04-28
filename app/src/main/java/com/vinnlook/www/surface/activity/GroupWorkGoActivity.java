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
import com.vinnlook.www.surface.adapter.GroupWorkGoAdapter;
import com.vinnlook.www.surface.mvp.presenter.GroupWorkGoPresenter;
import com.vinnlook.www.surface.mvp.view.GroupWorkGoView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 拼团Go列表
 * @Time:2021/4/15$
 * @Author:pk$
 */
public class GroupWorkGoActivity extends BaseActivity<GroupWorkGoPresenter> implements GroupWorkGoView {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    GroupWorkGoAdapter adapter;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, GroupWorkGoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_go_group;

    }


    @Override
    protected GroupWorkGoPresenter initPresenter() {
        return new GroupWorkGoPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(GroupWorkGoActivity.this);

        adapter = new GroupWorkGoAdapter(getActivity());
        final LinearLayoutManager manager4 = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(manager4);
        recycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recycler.addItemDecoration(new SpaceItemDecoration(0, 20));
        recycler.setNestedScrollingEnabled(false);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_4.startSelf(GroupWorkGoActivity.this, adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());
                MoveAbooutActivity_4.startSelf(GroupWorkGoActivity.this);
            }
        });


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

}
