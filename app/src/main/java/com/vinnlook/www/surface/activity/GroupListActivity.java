package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.CountDownTimerAdapter;
import com.vinnlook.www.surface.bean.GroupOrderListBean;
import com.vinnlook.www.surface.mvp.presenter.GroupListPresenter;
import com.vinnlook.www.surface.mvp.view.GroupListView;
import com.vinnlook.www.utils.CacheActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:拼团订单Lisi列表
 * @Time:2021/5/10$
 * @Author:pk$
 */
public class GroupListActivity extends BaseActivity<GroupListPresenter> implements GroupListView {

    private static String mark;//判断入口
    @BindView(R.id.imageButton1)
    RadioButton imageButton1;
    @BindView(R.id.imageButton2)
    RadioButton imageButton2;
    @BindView(R.id.imageButton3)
    RadioButton imageButton3;
    @BindView(R.id.imageButton4)
    RadioButton imageButton4;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.view_2)
    View view2;
    @BindView(R.id.view_3)
    View view3;
    @BindView(R.id.view_4)
    View view4;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.no_data_layout)
    TextView noDataLayout;
    CountDownTimerAdapter adapters;
    int page = 1;
    int lastItem = -1;
    int judge = 0;

    GroupOrderListBean groupOrderListBean;
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
                    adapters.notifyData();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private MyThread timeThread;

    public static void startSelf(Context context, int type) {
        Intent intent = new Intent(context, GroupListActivity.class);
        context.startActivity(intent);
        mark = String.valueOf(type);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_list;
    }

    @Override
    protected GroupListPresenter initPresenter() {
        return new GroupListPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getGroupOrderListData(page, 100, mark);//下载订单列表数据
                judge = 0;
            }
        });

//        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {;
//            }
//        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//                if (adapters.getNewData().size() - 5 == lastVisibleItem) {
//                    if (lastItem != lastVisibleItem) {
//                        lastItem = lastVisibleItem;
//                        page++;
//                        judge = 1;
//                        presenter.getGroupOrderListData(page, 10, mark);//下载订单列表数据
//                    }
//                } else {
//                    if (adapters.getNewData().size() - lastItem > 10) {
//                        lastItem = adapters.getNewData().size() - 5;
//                        page++;
//                        judge = 1;
//                        presenter.getGroupOrderListData(page, 10, mark);//下载订单列表数据
//                    }
//                }
//            }
//        });

        imageButton1.setChecked(true);

        if (mark.equals("0")) {//全部
            selsectBtn(imageButton1, view1);
        } else if (mark.equals("1")) {//开团中
            selsectBtn(imageButton2, view2);
        } else if (mark.equals("2")) {//参团中
            selsectBtn(imageButton3, view3);
        } else if (mark.equals("3")) {//拼团成功
            selsectBtn(imageButton4, view4);
        }
    }

    private void selsectBtn(RadioButton imageButton, View view) {
        imageButton.setChecked(true);
        view.setBackgroundColor(getResources().getColor(R.color.them));
    }

    @Override
    protected void loadData() {
        presenter.getGroupOrderListData(1, 100, mark);//下载购物车列表数据
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
////        presenter.getGroupListData(1, 10, mark);//下载订单列表数据
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageButton1, R.id.imageButton2, R.id.imageButton3, R.id.imageButton4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageButton1://全部
                mark = "0";
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.them));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                presenter.getGroupOrderListData(page, 100, mark);//下载订单列表数据

                break;
            case R.id.imageButton2://开团中
                mark = "1";
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.them));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                presenter.getGroupOrderListData(page, 100, mark);//下载订单列表数据
                break;
            case R.id.imageButton3://参团中
                mark = "2";
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.them));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                presenter.getGroupOrderListData(page, 100, mark);//下载订单列表数据
                break;
            case R.id.imageButton4://拼团成功
                mark = "3";
                judge = 0;
                page = 1;
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.them));
                presenter.getGroupOrderListData(page, 100, mark);//下载订单列表数据
                break;


        }
    }

    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getGroupOrderListSuccess(int code, GroupOrderListBean data) {
        groupOrderListBean = data;
        smartRefreshLayout.finishRefresh();
        Log.e("拼团订单列表", "===data===" + data);
        Log.e("拼团订单列表", "===data.getCount()===" + data.getCount());
        if (data.getCount().equals("0")) {
            noDataLayout.setVisibility(View.VISIBLE);
            smartRefreshLayout.setVisibility(View.GONE);
        } else {
            noDataLayout.setVisibility(View.GONE);
            smartRefreshLayout.setVisibility(View.VISIBLE);
            adapters = new CountDownTimerAdapter(this, data.getList());
            recyclerView.setAdapter(adapters);
            //进入拼团详情
            adapters.setOnItemClickListener(new CountDownTimerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, String orderID, String group_status, String goods_id, String search_attr,String group_id) {
                    if (group_status.equals("0")) {//拼团中
                        MoveAbooutActivity_4.startSelf(GroupListActivity.this, goods_id, search_attr, group_id,"");
                    } else {
                        GroupOrderDetailsActivity.startSelf(GroupListActivity.this, orderID);
                    }

//                    OpenGroupSuccessActivity.startSelf(GroupListActivity.this,orderID);

                }
                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
            //遍历所有数据，算出时间差并保存在每个商品的counttime属性内
            for (int i = 0; i < data.getList().size(); i++) {
                data.getList().get(i).setCountTime(data.getList().get(i).getSurplus_time());
            }

            timeThread = new MyThread(data.getList());
            new Thread(timeThread).start();
            adapters.notifyDataSetChanged();
        }
    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getGroupOrderListFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(1);
    }

    class MyThread implements Runnable {
        //用来停止线程
        boolean endThread;
        List<GroupOrderListBean.ListBean> mRecommendActivitiesList;
        public MyThread(List<GroupOrderListBean.ListBean> mRecommendActivitiesList) {
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
                        long hours1 = counttime % (60 * 60 * 24) / (60 * 60);
                        long minutess1 = counttime % (60 * 60) / 60;
                        long secondss1 = counttime % 60;

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
                        mRecommendActivitiesList.get(i).setHour(hourss);
                        mRecommendActivitiesList.get(i).setMinute(minutess);
                        mRecommendActivitiesList.get(i).setSecond(secondss);
                        //如果时间差大于1秒钟，将每件商品的时间差减去一秒钟，
                        // 并保存在每件商品的counttime属性内
                        if (counttime > 1) {
                            mRecommendActivitiesList.get(i).setCountTime(counttime - 1);
                        }
//                        else{
//                            mRecommendActivitiesList.remove(i);
//                            adapters.notifyDataSetChanged();
//                        }
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
