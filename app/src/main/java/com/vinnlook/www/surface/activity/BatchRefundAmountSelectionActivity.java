package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.BatchRefundEvent;
import com.vinnlook.www.surface.adapter.BatchRefundListAdapter;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.surface.mvp.presenter.BatchRefundPresenter;
import com.vinnlook.www.surface.mvp.view.BatchRefundView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:批量退款更改价格列表
 * @Time:2020/11/16$
 * @Author:pk$
 */
public class BatchRefundAmountSelectionActivity extends BaseActivity<BatchRefundPresenter> implements BatchRefundView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.batch_btn)
    TextView batchBtn;


    BatchRefundListAdapter batchadapter;
    static RefundInfoBean refundInfoBean;
    @BindView(R.id.shenqing_jine_toal_text)
    TextView shenqingJineToalText;

    float priceFloat;//总价
    float priceFloat_1;


    public static void startSelf(Context context, RefundInfoBean refundInfoBeans) {
        Intent intent = new Intent(context, BatchRefundAmountSelectionActivity.class);
        context.startActivity(intent);
        refundInfoBean = refundInfoBeans;
    }

    //    @Override
//    protected boolean isRegisterEventBus() {
//        return true;
//    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_batch_refund_amount;
    }

    @Override
    protected BatchRefundPresenter initPresenter() {
        return new BatchRefundPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        shenqingJineToalText.setText(refundInfoBean.getPrice());

        batchadapter = new BatchRefundListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(batchadapter);
        batchadapter.setData(refundInfoBean.getGoods_list());
        batchadapter.setBatchRefundListClickListener(new BatchRefundListAdapter.BatchRefundListClickListener() {
            @Override
            public void onBatchRefundClickListener() {
                float priceFloats = 0;
                for (int i = 0; i < batchadapter.getData().size(); i++) {
                    if (!batchadapter.getData().get(i).getNew_goods_price().equals("") && batchadapter.getData().get(i).getNew_goods_price() != null) {
                        priceFloat = Float.parseFloat(batchadapter.getData().get(i).getNew_goods_price());
                        Log.e("batchadapter", "priceFloat===1111===" + priceFloat);
                        priceFloats = priceFloats + priceFloat;
                        Log.e("batchadapter", "priceFloats===2222===" + priceFloats);
                    }
                }
                shenqingJineToalText.setText(String.format("%.2f", priceFloats));
            }
        });
        batchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("batchadapter", "batchBtn===2222===" + shenqingJineToalText.getText().toString());


                float priceFloats = 0;
                float priceFloats_1 = 0;

                for (int i = 0; i < batchadapter.getData().size(); i++) {
                    if (!batchadapter.getData().get(i).getNew_goods_price().equals("") && batchadapter.getData().get(i).getNew_goods_price() != null) {
                        priceFloat = Float.parseFloat(batchadapter.getData().get(i).getNew_goods_price());
                        Log.e("batchadapter", "priceFloat===1111===" + priceFloat);
                        priceFloats = priceFloats + priceFloat;
                        Log.e("batchadapter", "priceFloats===2222===" + priceFloats);
                        priceFloat_1 = Float.parseFloat(batchadapter.getData().get(i).getNew_goods_price());
                        priceFloats_1 = priceFloats_1 + priceFloat_1;
                        if (Float.parseFloat(batchadapter.getData().get(i).getNew_goods_price()) < 0.01f) {
                            Toast.makeText(BatchRefundAmountSelectionActivity.this, "输入的金额不能少于0.01，请重新输入", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Float.parseFloat(batchadapter.getData().get(i).getNew_goods_price()) > Float.parseFloat(batchadapter.getData().get(i).getGoods_price())) {
                            Toast.makeText(BatchRefundAmountSelectionActivity.this, "输入的金额不能大于最多可退的金额数，请重新输入", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            new BatchRefundEvent(shenqingJineToalText.getText().toString()).post();
                            finish();
                        }
                    } else {
                        Toast.makeText(BatchRefundAmountSelectionActivity.this, "输入的金额不能为空，请重新输入", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

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
