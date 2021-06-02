package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.GroupWorkGo1Adapter;
import com.vinnlook.www.surface.adapter.GroupWorkGoAdapter;
import com.vinnlook.www.surface.bean.GroupListBean;
import com.vinnlook.www.surface.mvp.presenter.GroupWorkGoPresenter;
import com.vinnlook.www.surface.mvp.view.GroupWorkGoView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.List;

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
    @BindView(R.id.home_scroll)
    ScrollView homeScroll;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    GroupWorkGoAdapter adapter;
    GroupWorkGo1Adapter adapter1;
    @BindView(R.id.no_data_layout)
    TextView noDataLayout;

    int page = 1;
    int lastItem = -1;
    int judge = 0;

    GroupListBean groupListBean;
    String dayss;
    String hourss;
    String minutess;
    String secondss;
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //刷新适配器
                    //    mRecommendActivitiesAdapter.notifyDataSetChanged();
                    //优化刷新adapter的方法
                    adapter1.notifyData();
                    break;
            }
            super.handleMessage(msg);
        }
    };


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
    private MyThread timeThread;

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(GroupWorkGoActivity.this);

//        adapter = new GroupWorkGoAdapter(getActivity());
//        final LinearLayoutManager manager4 = new LinearLayoutManager(getActivity());
//        recycler.setLayoutManager(manager4);
//        recycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 1)));
//        recycler.addItemDecoration(new SpaceItemDecoration(0, 20));
//        recycler.setNestedScrollingEnabled(false);
//        recycler.setHasFixedSize(true);
//        recycler.setAdapter(adapter);
//        adapter.addOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                MoveAbooutActivity_4.startSelf(GroupWorkGoActivity.this, adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());
//            }
//        });


        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getGroupListData(page, 100);
                judge = 0;
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();

            }
        });

//        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//                if (adapter.getData().size() - 5 == lastVisibleItem) {
//                    if (lastItem != lastVisibleItem) {
//                        lastItem = lastVisibleItem;
//                        page++;
//                        judge = 1;
//                        presenter.getGroupListData(page, 10);
//                    }
//                } else {
//                    if (adapter.getData().size() - lastItem > 10) {
//                        lastItem = adapter.getData().size() - 5;
//                        page++;
//                        judge = 1;
//                        presenter.getGroupListData(page, 10);
//                    }
//                }
//            }
//        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        presenter.getGroupListData(page, 100);
    }

    /**
     * 下载列表数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getGroupListSuccess(int code, GroupListBean data) {
        groupListBean = data;
        smartRefreshLayout.finishRefresh();
//        adapter.setData(data.getList());

        if (data.getCount().equals("0")) {
            noDataLayout.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        } else {
            noDataLayout.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
            adapter1 = new GroupWorkGo1Adapter(this, data.getList());
            final LinearLayoutManager manager4 = new LinearLayoutManager(getActivity());
            recycler.setLayoutManager(manager4);
            recycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 1)));
            recycler.addItemDecoration(new SpaceItemDecoration(0, 20));
            recycler.setNestedScrollingEnabled(false);
            recycler.setHasFixedSize(true);
            recycler.setAdapter(adapter1);
            adapter1.setOnItemClickListener(new GroupWorkGo1Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, String goods_id, String search_attr) {
                    MoveAbooutActivity_4.startSelf(GroupWorkGoActivity.this, goods_id, search_attr,"","1");
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });

            //遍历所有数据，算出时间差并保存在每个商品的counttime属性内
            for (int i = 0; i < data.getList().size(); i++) {
                data.getList().get(i).setCountTime(data.getList().get(i).getStill_time());
            }

            timeThread = new MyThread(data.getList());
            new Thread(timeThread).start();
            adapter1.notifyDataSetChanged();
        }
    }

    /**
     * 下载列表数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getGroupListFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }

    class MyThread implements Runnable {
        //用来停止线程
        boolean endThread;
        List<GroupListBean.ListBean> mRecommendActivitiesList;

        public MyThread(List<GroupListBean.ListBean> mRecommendActivitiesList) {
            this.mRecommendActivitiesList = mRecommendActivitiesList;
        }

        @Override
        public void run() {
            while (!endThread) {
                try {
                    Thread.sleep(1000);
                    for (int i = 0; i < mRecommendActivitiesList.size(); i++) {
                        //拿到每件商品的时间差，转化为具体的多少天多少小时多少分多少秒
                        //并保存在商品time这个属性内
                        long counttime = mRecommendActivitiesList.get(i).getCountTime();
                        long days = counttime / (60 * 60 * 24);
                        long hours1 = counttime % (60 * 60 * 24) / (60 * 60);
                        long minutess1 = counttime % (60 * 60) / 60;
                        long secondss1 = counttime % 60;

                        if (days < 10) {
                            dayss = "0" + days;
                        } else {
                            dayss = String.valueOf(days);
                        }

                        if (hours1 < 10) {
                            hourss = "0" + hours1;
                        } else {
                            hourss = String.valueOf(hours1);
                        }
                        if (minutess1 < 10) {
                            minutess = "0" + minutess1;
                        } else {
                            minutess = String.valueOf(minutess1);
                        }
                        if (secondss1 < 10) {
                            secondss = "0" + secondss1;
                        } else {
                            secondss = String.valueOf(secondss1);
                        }
                        //并保存在商品time这个属性内
                        mRecommendActivitiesList.get(i).setDay(dayss);
                        mRecommendActivitiesList.get(i).setHour(hourss);
                        mRecommendActivitiesList.get(i).setMinute(minutess);
                        //如果时间差大于1秒钟，将每件商品的时间差减去一秒钟，
                        // 并保存在每件商品的counttime属性内
                        if (counttime > 1) {
                            mRecommendActivitiesList.get(i).setCountTime(counttime - 1);
                        }
                    }
                    Message message = new Message();
                    message.what = 1;
                    //发送信息给handler
                    handler.sendMessage(message);
                } catch (Exception e) {

                }
            }
        }
    }
}
