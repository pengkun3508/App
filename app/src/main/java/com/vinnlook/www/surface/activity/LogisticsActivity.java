package com.vinnlook.www.surface.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.LogisticsAdapter;
import com.vinnlook.www.surface.bean.OrderLogisticsBean;
import com.vinnlook.www.surface.bean.WayBillLogisticsBean;
import com.vinnlook.www.surface.mvp.presenter.LogisticsPresenter;
import com.vinnlook.www.surface.mvp.view.LogisticsView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:查看物流
 * @Time:2020/4/14 9:42
 * @Author:pk
 */
public class LogisticsActivity extends BaseActivity<LogisticsPresenter> implements LogisticsView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.order_logistics_sn)
    TextView orderLogisticsSn;
    @BindView(R.id.order_logistics_time)
    TextView orderLogisticsTime;
    @BindView(R.id.order_logistics_recycler)
    RecyclerView orderLogisticsRecycler;
    @BindView(R.id.order_wuliu_sn)
    TextView orderWuliuSn;
    @BindView(R.id.order_no_copy_order)
    TextView orderNoCopyOrder;
    @BindView(R.id.order_no_copy_wuliu)
    TextView orderNoCopyWuliu;

    private LogisticsAdapter adapter;
    private static String order_id;

    public static void startSelf(Context context, String order_ids) {
        Intent intent = new Intent(context, LogisticsActivity.class);
        context.startActivity(intent);
        order_id = order_ids;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logistics;
    }

    @Override
    protected LogisticsPresenter initPresenter() {
        return new LogisticsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        orderLogisticsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        orderLogisticsRecycler.setNestedScrollingEnabled(false);
        orderLogisticsRecycler.setHasFixedSize(true);
        adapter = new LogisticsAdapter();
        orderLogisticsRecycler.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                ProductDetailsActivity.startSelf(getContext());
            }
        });

        //复制物流号
        orderNoCopyWuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(orderWuliuSn.getText());
                Toast.makeText(LogisticsActivity.this, "复制成功", Toast.LENGTH_LONG).show();

            }
        });
        //复制订单号
        orderNoCopyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(orderLogisticsSn.getText());
                Toast.makeText(LogisticsActivity.this, "复制成功", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getOrderLogistics(order_id);

    }

    public List<String> getData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            strings.add("1");
        }
        return strings;
    }


    /**
     * 获取物流信息成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getOrderLogisticsSuccess(int code, OrderLogisticsBean data) {
        orderLogisticsTime.setText(data.getCreate_time());

        if (data.getOrder_sn().equals("")) {
            orderNoCopyOrder.setVisibility(View.GONE);
        } else {
            orderNoCopyOrder.setVisibility(View.VISIBLE);
            orderLogisticsSn.setText(data.getOrder_sn());
        }

        if (data.getWaybill().equals("")) {
            orderNoCopyWuliu.setVisibility(View.GONE);
        } else {
            orderNoCopyWuliu.setVisibility(View.VISIBLE);
            orderWuliuSn.setText(data.getWaybill());
        }

        adapter.setData(data.getTraces());


    }

    /**
     * 获取物流信息失败
     *
     * @param code
     * @param
     */
    @Override
    public void getOrderLogisticsFail(int code, String msg) {


    }

    @Override
    public void getWayBillLogisticsSuccess(int code, WayBillLogisticsBean data) {

    }

    @Override
    public void getWayBillLogisticsFail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
