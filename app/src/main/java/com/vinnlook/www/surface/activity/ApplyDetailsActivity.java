package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.PostWaybillEvent;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.adapter.ApplyDetailsAdapter;
import com.vinnlook.www.surface.bean.ApplyDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.ApplyDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.ApplyDetailsView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:退货订单详情
 * @Time:2020/11/16$
 * @Author:pk$
 */
public class ApplyDetailsActivity extends BaseActivity<ApplyDetailsPresenter> implements ApplyDetailsView {

    static String order_id;
    static String id;


    ApplyDetailsAdapter adapter;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.apply_details_msg)
    TextView applyDetailsMsg;
    @BindView(R.id.apply_details_recycler)
    RecyclerView applyDetailsRecycler;
    @BindView(R.id.apply_details_chexiao_btn)
    RoundTextView applyDetailsChexiaoBtn;
    //    @BindView(R.id.apply_details_img)
//    RoundedImageView applyDetailsImg;
//    @BindView(R.id.apply_details_name)
//    TextView applyDetailsName;
//    @BindView(R.id.apply_details_type)
//    TextView applyDetailsType;
//    @BindView(R.id.shouhou_relayou)
//    LinearLayout shouhouRelayou;
//    @BindView(R.id.apply_details_num)
//    TextView applyDetailsNum;
    @BindView(R.id.apply_details_price)
    TextView applyDetailsPrice;
    @BindView(R.id.apply_details_shouhou)
    TextView applyDetailsShouhou;
    @BindView(R.id.apply_details_status)
    TextView applyDetailsStatus;
    @BindView(R.id.apply_details_time)
    TextView applyDetailsTime;
    @BindView(R.id.apply_name)
    TextView applyName;
    @BindView(R.id.apply_phone)
    TextView applyPhone;
    @BindView(R.id.apply_address)
    TextView applyAddress;
    @BindView(R.id.apply_details_see_wuliu_btn)
    RoundTextView applyDetailsSeeWuliuBtn;
    @BindView(R.id.address_layout)
    LinearLayout addressLayout;
    @BindView(R.id.apply_details_add_wuliu_btn)
    RoundTextView applyDetailsAddWuliuBtn;
    @BindView(R.id.reapply_tuikuan_btn)
    RoundTextView reapplyTuikuanBtn;
    ApplyDetailsBean applyDetailsBean;
    @BindView(R.id.apply_details_icon)
    ImageView applyDetailsIcon;


    public static void startSelf(Context context, String order_ids, String ids) {
        Intent intent = new Intent(context, ApplyDetailsActivity.class);
        context.startActivity(intent);
        order_id = order_ids;
        id = ids;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_details;
    }

    @Override
    protected ApplyDetailsPresenter initPresenter() {
        return new ApplyDetailsPresenter();
    }


    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        applyDetailsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ApplyDetailsAdapter(this);
        applyDetailsRecycler.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(ApplyDetailsActivity.this, adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr(),"");
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getApplyDetailsData(order_id, id);


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        presenter.getApplyDetailsData(order_id, id);
//    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PostWaybillEvent event) {
        Log.e("接收消息", "PostWaybillEvent==" + event.getMark());
        presenter.getApplyDetailsData(order_id, id);
    }


    /**
     * 下载详情成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getApplyDetailsSuccess(int code, ApplyDetailsBean data) {
        applyDetailsBean = data;
        List<ApplyDetailsBean.ListBean> asd = data.getList();
        adapter.setData(data.getList());

        if (data.getType().equals("1")) {//1--仅退款；2--退货退款
            applyDetailsChexiaoBtn.setText("撤销退款");
//            actionBar.getTvTitle().setText("退款详情");
        } else if (data.getType().equals("2")) {//1--仅退款；2--退货退款
            applyDetailsChexiaoBtn.setText("撤销退货退款");
//            actionBar.getTvTitle().setText("退货退款详情");
        }
        if (data.getStatus().equals("1")) {//1:审核中
            applyDetailsMsg.setText("退款申请待商家处理。");
            applyDetailsSeeWuliuBtn.setVisibility(View.GONE);
            applyDetailsAddWuliuBtn.setVisibility(View.GONE);
            applyDetailsMsg.setTextColor(getResources().getColor(R.color.apply_text));
            applyDetailsChexiaoBtn.setVisibility(View.VISIBLE);
            addressLayout.setVisibility(View.GONE);
            reapplyTuikuanBtn.setVisibility(View.GONE);

            applyDetailsIcon.setBackgroundResource(R.mipmap.apply_icon_1);

        } else if (data.getStatus().equals("2")) {//2:同意退款成功
//            applyDetailsMsg.setText("退款成功，金额已退回您支付账户。");
            applyDetailsMsg.setText(data.getMsg());
            applyDetailsSeeWuliuBtn.setVisibility(View.GONE);
            applyDetailsAddWuliuBtn.setVisibility(View.GONE);
            applyDetailsMsg.setTextColor(getResources().getColor(R.color.apply_text));
            applyDetailsChexiaoBtn.setVisibility(View.GONE);
            addressLayout.setVisibility(View.GONE);
            reapplyTuikuanBtn.setVisibility(View.GONE);
            applyDetailsIcon.setBackgroundResource(R.mipmap.apply_icon_2);


        } else if (data.getStatus().equals("3")) {//3:拒绝退款
            applyDetailsMsg.setText("拒绝退款，不符合退款需求。" + data.getMsg());
            applyDetailsSeeWuliuBtn.setVisibility(View.GONE);
            applyDetailsAddWuliuBtn.setVisibility(View.GONE);
            applyDetailsMsg.setTextColor(getResources().getColor(R.color.apply_text));
            applyDetailsChexiaoBtn.setVisibility(View.GONE);
            addressLayout.setVisibility(View.GONE);
            reapplyTuikuanBtn.setVisibility(View.VISIBLE);
            applyDetailsIcon.setBackgroundResource(R.mipmap.apply_icon_3);
        } else if (data.getStatus().equals("4")) {//4:取消退款
            applyDetailsMsg.setText("撤销退款");
            applyDetailsSeeWuliuBtn.setVisibility(View.GONE);
            applyDetailsAddWuliuBtn.setVisibility(View.GONE);
            applyDetailsMsg.setTextColor(getResources().getColor(R.color.apply_text));
            applyDetailsChexiaoBtn.setVisibility(View.GONE);
            addressLayout.setVisibility(View.GONE);
            reapplyTuikuanBtn.setVisibility(View.GONE);
            applyDetailsIcon.setBackgroundResource(R.mipmap.apply_icon_4);
        } else if (data.getStatus().equals("5")) {//5:同意退货
            applyDetailsMsg.setText("已同意您的退货退款，请填写物流信息。");
            applyDetailsSeeWuliuBtn.setVisibility(View.GONE);
            applyDetailsAddWuliuBtn.setVisibility(View.VISIBLE);
            applyDetailsMsg.setTextColor(getResources().getColor(R.color.apply_text));
            applyDetailsChexiaoBtn.setVisibility(View.VISIBLE);
            addressLayout.setVisibility(View.VISIBLE);
            reapplyTuikuanBtn.setVisibility(View.GONE);
            applyDetailsIcon.setBackgroundResource(R.mipmap.apply_icon_5);
        } else if (data.getStatus().equals("6")) {//6:已发货
            applyDetailsMsg.setText("请等待商家收货后处理...");
            applyDetailsSeeWuliuBtn.setVisibility(View.VISIBLE);
            applyDetailsAddWuliuBtn.setVisibility(View.GONE);
            applyDetailsMsg.setTextColor(getResources().getColor(R.color.apply_text));
            applyDetailsChexiaoBtn.setVisibility(View.GONE);
            addressLayout.setVisibility(View.GONE);
            reapplyTuikuanBtn.setVisibility(View.GONE);
            applyDetailsIcon.setBackgroundResource(R.mipmap.apply_icon_6);
        } else if (data.getStatus().equals("7")) {//7:已收货
            applyDetailsMsg.setText("商家已收货，正在处理中...");
            applyDetailsSeeWuliuBtn.setVisibility(View.VISIBLE);
            applyDetailsAddWuliuBtn.setVisibility(View.GONE);
            applyDetailsMsg.setTextColor(getResources().getColor(R.color.apply_text));
            applyDetailsChexiaoBtn.setVisibility(View.GONE);
            addressLayout.setVisibility(View.GONE);
            reapplyTuikuanBtn.setVisibility(View.GONE);
            applyDetailsIcon.setBackgroundResource(R.mipmap.apply_icon_7);
        }
        applyName.setText(data.getReceiver_name());
        applyPhone.setText(data.getReceiver_tel());
        applyAddress.setText(data.getAddress());
//
//        Matrix matrix = new Matrix();           //创建一个单位矩阵
//        matrix.setTranslate(0, 0);          //平移x和y各100单位
//        matrix.preRotate(0);                   //顺时针旋转30度
//        applyDetailsImg.setScaleType(ImageView.ScaleType.MATRIX);
//        applyDetailsImg.setImageMatrix(matrix);
//        ImageLoader.image(this, applyDetailsImg, data.getImage());
//        applyDetailsName.setText(data.getGoods_name());
//        applyDetailsType.setText(data.getAttr_name());
//        applyDetailsNum.setText("x" + data.getGoods_number());


        applyDetailsPrice.setText(Html.fromHtml("&yen") + data.getPrice());
        applyDetailsTime.setText(data.getCreate_time());
        if (data.getType().equals("1")) {
            applyDetailsShouhou.setText("仅退款");
        } else if (data.getType().equals("2")) {
            applyDetailsShouhou.setText("退货退款");
        }
        if (data.getGoods_status().equals("1")) {
            applyDetailsStatus.setText("已收到货");
        } else if (data.getGoods_status().equals("2")) {
            applyDetailsStatus.setText("未收到货");
        }


        applyDetailsChexiaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getApplyCancel(data.getOrder_id(), data.getId());
            }
        });

        //添加物流信息
        applyDetailsAddWuliuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddWuLiuDataActivity.startSelf(ApplyDetailsActivity.this, data.getId(), data.getOrder_id());

            }
        });
        //查看物流信息
        applyDetailsSeeWuliuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogisticsWayBillActivity.startSelf(ApplyDetailsActivity.this, data.getId());

            }
        });


        //重新申请退款
        reapplyTuikuanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getOederDetailsData(data.getOrder_id());//下载数据
            }
        });
    }

    /**
     * 下载详情失败
     *
     * @param code
     * @param
     */
    @Override
    public void getApplyDetailsFail(int code, String msg) {
    }

    /**
     * 撤销退款请求成功
     *
     * @param code
     * @param
     */
    @Override
    public void getApplyCancelSuccess(int code, Object data) {
//        presenter.getApplyDetailsData(order_id, id);
        ApplyRefundListActivity.startSelf(this);//进入列表页面
        CacheActivity.finishActivity();
    }

    /**
     * 撤销退款请求失败
     *
     * @param code
     * @param
     */
    @Override
    public void getApplyCancelFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * @Description:获取订单详情成功
     * @Time:2020/5/8 14:22
     * @Author:pk
     */
    @Override
    public void getOederDetailsSuccess(int code, OrderDetailsBean data) {
        List<OrderDetailsBean.ShopListBean> listShop = new ArrayList<>();
//        OrderDetailsBean orderDetailsBeans = new OrderDetailsBean();
        String getIs_refund_all = data.getIs_refund_all();
        for (int i = 0; i < data.getShop_list().size(); i++) {
            if (data.getShop_list().get(i).getShop_status().equals("3")) {//拒绝退款
                for (int j = 0; j < applyDetailsBean.getList().size(); j++) {
                    if (data.getShop_list().get(i).getRec_id().equals(applyDetailsBean.getList().get(j).getRec_id())) {
                        listShop.add(data.getShop_list().get(i));
                    }
                }
            }
        }


        data.setMrakAll("0");
        data.setShop_list(listShop);
        Log.e("退款详情===获取订单详情成功===", "listShop.size()=====" + listShop.size());
        Log.e("退款详情===获取订单详情成功===", "getIs_all_refund=====" + data.getIs_all_refund());
        Log.e("退款详情===获取订单详情成功===", "getIs_refund_all=====" + getIs_refund_all);
        Log.e("退款详情===获取订单详情成功===", "getStatus=====" + data.getStatus());
//        //判断是否仅退款还是退货退款
//        if (data.getStatus() == 2) {//待发货
//            if (data.getIs_all_refund().equals("0")) {//可以退款
//                if (getIs_refund_all.equals("1")) {//必须整单退款（仅退款）
//                    //直接跳到退款页面，传入仅退款参数
//                    ApplyRefundSelectActivity_1.startSelf(this, data.getShop_list(), data.getOrder_id(), "1", getIs_refund_all);
//                } else if (getIs_refund_all.equals("0")) {//可以单个退款（仅退款）
//                    if (listShop.size() == 1) {
//                        ApplyRefundActivity.startSelf(this, data.getShop_list().get(0), order_id, getIs_refund_all, data.getStatus());
//                    } else {
//                        //直接跳入选择商品页面
//                        AfterSalesShopActivity.startSelf(this, data, "1", getIs_refund_all);
//                    }
//                }
//            } else {//不能退款
//                Toast.makeText(this, "很抱歉，暂时不能办理退款服务", Toast.LENGTH_SHORT).show();
//            }
//        } else {
////            AfterSalesActivity.startSelf(this, data, data.getIs_refund_all());//不是整单退款--选择售后
//        }


        if (data.getShop_list().get(0).getSuppliers_id().equals("1")) {//自营
            AfterSalesActivity.startSelf(this, data, getIs_refund_all, String.valueOf(data.getStatus()));//不是整单退款--可以选择单个商品
        } else if (data.getShop_list().get(0).getSuppliers_id().equals("2")) {//海淘
            if (data.getStatus() == 2) {//待发货状态--仅退款
                if (data.getIs_all_refund().equals("0")) {//可以退款
                    if (getIs_refund_all.equals("1")) {//必须整单退款（仅退款）
                        //直接跳到退款页面，传入仅退款参数--整单
                        ApplyRefundSelectActivity_1.startSelf(ApplyDetailsActivity.this, data.getShop_list(), data.getOrder_id(), "1", getIs_refund_all, String.valueOf(data.getStatus()));
                    } else if (getIs_refund_all.equals("0")) {//可以单个退款（仅退款）
                        //直接跳入选择商品页面--可以选择商品
                        AfterSalesShopActivity.startSelf(this, data, "1", getIs_refund_all, String.valueOf(data.getStatus()));
                    }
                } else {//不能退款
                    Toast.makeText(this, "很抱歉，暂时不能办理退款服务", Toast.LENGTH_SHORT).show();
                }
            } else {//可以退款，也可以退货退款
                AfterSalesActivity.startSelf(this, data, getIs_refund_all, String.valueOf(data.getStatus()));//不是整单退款--可以选择单个商品
            }
        }


    }

    /**
     * @Description:获取订单详情失败
     * @Time:2020/5/8 14:22
     * @Author:pk
     */
    @Override
    public void getOederDetailsFail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
