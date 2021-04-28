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
import com.vinnlook.www.surface.adapter.WaybillAdapter;
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
 * @Description: 查看退款物流信息
 * @Time:2021/2/5$
 * @Author:pk$
 */
public class LogisticsWayBillActivity extends BaseActivity<LogisticsPresenter> implements LogisticsView {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.waybill_wuliu_name)
    TextView waybillWuliuName;
    @BindView(R.id.waybill_copy_name)
    TextView waybillCopyName;
    @BindView(R.id.waybill_wuliu_sn)
    TextView waybillWuliuSn;
    @BindView(R.id.waybill_copy_sn)
    TextView waybillCopySn;
    @BindView(R.id.order_logistics_recycler)
    RecyclerView orderLogisticsRecycler;

    private WaybillAdapter adapter;
    private static String wayId;

    public static void startSelf(Context context, String wayIds) {
        Intent intent = new Intent(context, LogisticsWayBillActivity.class);
        context.startActivity(intent);
        wayId = wayIds;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logistics_waybill;
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
        adapter = new WaybillAdapter(this);
        orderLogisticsRecycler.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                ProductDetailsActivity.startSelf(getContext());
            }
        });

        //复制物流号
        waybillCopyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(waybillWuliuName.getText());
                Toast.makeText(LogisticsWayBillActivity.this, "复制成功", Toast.LENGTH_LONG).show();

            }
        });
        //复制订单号
        waybillCopySn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(waybillWuliuSn.getText());
                Toast.makeText(LogisticsWayBillActivity.this, "复制成功", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getWayBillLogistics(wayId);

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

    /**
     * 获取退款物流信息成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getWayBillLogisticsSuccess(int code, WayBillLogisticsBean data) {

        waybillWuliuSn.setText(data.getWaybill());
        waybillWuliuName.setText(data.getWaybill_name());
        adapter.setData(data.getTraces());
    }

    /**
     * 获取退款物流信息失败
     *
     * @param code
     * @param
     */
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
