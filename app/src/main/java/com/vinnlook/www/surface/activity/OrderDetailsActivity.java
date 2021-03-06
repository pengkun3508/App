package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.DeviceIdUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.m7.imkfsdk.KfStartHelper;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.model.entity.NewCardInfo;
import com.moor.imkf.model.entity.NewCardInfoAttrs;
import com.moor.imkf.utils.MoorUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.App;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.AddressListBean_3Event;
import com.vinnlook.www.http.model.AllOrderListBean;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.http.model.SiginOrderBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.OrderCouponAdapter;
import com.vinnlook.www.surface.adapter.OrderDetailsAdapter;
import com.vinnlook.www.surface.mvp.presenter.OrderDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.OrderDetailsView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:????????????
 * @Time:2020/4/14 9:43
 * @Author:pk
 */
public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsView {
    private static String getOrder_id;
    String getOrder_amount;
    int getStatus;
    String sdktoken;
    private static final int BAIDU_READ_PHONE_STATE = 100;
    @BindView(R.id.order_details_address_name)
    TextView orderDetailsAddressName;
    @BindView(R.id.order_details_phone)
    TextView orderDetailsPhone;
    @BindView(R.id.order_details_address_addres)
    TextView orderDetailsAddressAddres;
    @BindView(R.id.order_details_address_idcar)
    TextView orderDetailsAddressIdcar;
    @BindView(R.id.order_details_shiming_name)
    TextView orderDetailsShimingName;
    @BindView(R.id.order_details_id_number)
    TextView orderDetailsIdNumber;
    @BindView(R.id.order_details_recycler)
    RecyclerView orderDetailsRecycler;
    @BindView(R.id.order_details_express)
    TextView orderDetailsExpress;
    @BindView(R.id.order_details_shop_price)
    TextView orderDetailsShopPrice;
    @BindView(R.id.order_details_express_price)
    TextView orderDetailsExpressPrice;
    @BindView(R.id.order_details_counpon_price)
    TextView orderDetailsCounponPrice;
    @BindView(R.id.order_details_toal_price)
    TextView orderDetailsToalPrice;
    @BindView(R.id.order_details_oeder_sn)
    TextView orderDetailsOederSn;
    @BindView(R.id.order_kefu_btn)
    TextView orderKefuBtn;
    @BindView(R.id.order_quxiao_btn)
    TextView orderQuxiaoBtn;
    @BindView(R.id.order_pay_btn)
    TextView orderPayBtn;
    @BindView(R.id.order_shouhuo_btn)
    TextView orderShouhuoBtn;
    @BindView(R.id.order_tuihuan_btn)
    TextView orderTuihuanBtn;
    @BindView(R.id.orderdetails_postscript)
    TextView orderdetailsPostscript;
    @BindView(R.id.order_see_wuliu)
    TextView orderSeeWuliu;//????????????
    @BindView(R.id.order_details_oeder_time)
    TextView orderDetailsOederTime;//????????????
    @BindView(R.id.counpon_price_list)
    RecyclerView counponPriceList;
    @BindView(R.id.black_bg)
    ImageView blackBg;
    @BindView(R.id.coupon_layout)
    RelativeLayout couponLayout;
    @BindView(R.id.order_no_copy)
    TextView orderNoCopy;
    @BindView(R.id.counpon_line)
    View counponLine;
    @BindView(R.id.order_update_address)
    TextView orderUpdateAddress;
    @BindView(R.id.shiming_name_img)
    ImageView shimingNameImg;
    OrderDetailsBean orderDetailsBean;
    List<OrderDetailsBean.ShopListBean> listBean = new ArrayList<>();
    public PopupWindow popupwindow;
    String flag = "0";
    String getIs_all_refund;
    String getIs_refund_all;
    String mark;


    OrderDetailsAdapter adapter;
    OrderCouponAdapter couponAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details_1;
    }


    public static void startSelf(Context context, String getOrder_ids) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        context.startActivity(intent);
        getOrder_id = getOrder_ids;
//        getOrder_id="1493";
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected OrderDetailsPresenter initPresenter() {
        return new OrderDetailsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
//        viewLin.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        orderDetailsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        });

//        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
//        orderDetailsRecycler.setLayoutManager(manager1);
//        orderDetailsRecycler.setNestedScrollingEnabled(false);
//        orderDetailsRecycler.setHasFixedSize(true);

        shimingNameImg.setVisibility(View.GONE);

        adapter = new OrderDetailsAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        orderDetailsRecycler.setLayoutManager(manager3);
        orderDetailsRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        orderDetailsRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        orderDetailsRecycler.setNestedScrollingEnabled(false);
        orderDetailsRecycler.setHasFixedSize(true);

        couponAdapter = new OrderCouponAdapter(this);
        final GridLayoutManager manager4 = new GridLayoutManager(getActivity(), 1);
        counponPriceList.setLayoutManager(manager4);
        counponPriceList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        counponPriceList.addItemDecoration(new SpaceItemDecoration(0, 0));
        counponPriceList.setNestedScrollingEnabled(false);
        counponPriceList.setHasFixedSize(true);

        //????????????
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr(),"");
            }
        });

        //??????
        orderNoCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(orderDetailsOederSn.getText());
                Toast.makeText(OrderDetailsActivity.this, "????????????", Toast.LENGTH_LONG).show();
            }
        });

        adapter.setOnTuiKuaiClickListener(new OrderDetailsAdapter.OrderDetailsClickListener() {
            @Override
            public void onGoClickListener(OrderDetailsBean.ShopListBean data, String order_id, String getIs_refund_all, int getStatus) {
                List<OrderDetailsBean.ShopListBean> list = new ArrayList();
                list.add(data);
                orderDetailsBean.setShop_list(list);
                AfterSalesActivity.startSelf(OrderDetailsActivity.this, orderDetailsBean, getIs_refund_all, String.valueOf(getStatus));//??????????????????--????????????????????????
            }

            @Override
            public void onAddShopCarListener(String goods_id, String product_id, String number) {  //?????????????????????

                presenter.getAddShopCar(goods_id, product_id, number,"");

            }
        });


    }

    @Override
    protected void loadData() {
//        presenter.getAppUpdate();//????????????
        presenter.getOederDetailsData(getOrder_id);//????????????

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        presenter.getOederDetailsData(getOrder_id);//????????????
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {
        presenter.getOederDetailsData(getOrder_id);//????????????
    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:??????????????????
     * @Time:2020/5/8 14:22
     * @Author:pk
     */
    @Override
    public void getOederDetailsSuccess(int code, OrderDetailsBean data) {
        orderDetailsBean = data;
        orderDetailsBean.setMrakAll("0");
        adapter.setData(data.getShop_list());
        orderDetailsRecycler.setAdapter(adapter);

        counponPriceList.setAdapter(couponAdapter);

        getIs_refund_all = data.getIs_refund_all();//0:??????????????????1??????????????????

        getIs_all_refund = data.getIs_all_refund();//1==??????????????????0==????????????
//        getIs_all_refund = "1";


        if (data.getDiscount_info().size() > 0) {
            couponLayout.setVisibility(View.VISIBLE);
            counponLine.setVisibility(View.VISIBLE);
            couponAdapter.setData(data.getDiscount_info());
            //????????????????????????
            couponLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (flag.equals("0")) {
                        flag = "1";
                        counponPriceList.setVisibility(View.VISIBLE);
                        blackBg.setBackground(getResources().getDrawable(R.mipmap.up_icon));
                    } else if (flag.equals("1")) {
                        flag = "0";
                        counponPriceList.setVisibility(View.GONE);
                        blackBg.setBackground(getResources().getDrawable(R.mipmap.down_icon));
                    }

                }
            });
        } else {
            counponPriceList.setVisibility(View.GONE);
            couponLayout.setVisibility(View.GONE);
            counponLine.setVisibility(View.GONE);
        }


        getOrder_id = data.getOrder_id();
        getStatus = data.getStatus();
        adapter.setOrder_Id(getOrder_id, getStatus, getIs_all_refund, getIs_refund_all);
        getOrder_amount = data.getOrder_amount();
        adapter.notifyDataSetChanged();
        OrderDetailsBean.AddressBean getAddress = data.getAddress();//???????????????
        OrderDetailsBean.RealBean getReal = data.getReal();//???????????????

        orderDetailsAddressName.setText(getAddress.getAddress_name());
        orderDetailsPhone.setText(getAddress.getMobile());
        orderDetailsAddressAddres.setText(getAddress.getAddress_refer() + getAddress.getAddress());
        orderDetailsAddressIdcar.setText(getAddress.getAddress_id());

        orderDetailsShimingName.setText(getReal.getName());
        orderDetailsIdNumber.setText(getReal.getId_card());
        orderdetailsPostscript.setText(data.getPostscript());
        orderDetailsShopPrice.setText(Html.fromHtml("&yen") + data.getGoods_amount());
        orderDetailsExpressPrice.setText(Html.fromHtml("&yen") + data.getShipping_fee());
        orderDetailsCounponPrice.setText("-" + Html.fromHtml("&yen") + data.getBonus());
        orderDetailsToalPrice.setText(Html.fromHtml("&yen") + data.getOrder_amount());
        orderDetailsOederSn.setText("????????????" + data.getOrder_sn());
        orderDetailsExpress.setText(data.getWaybillName());

        if (data.getShop_list().get(0).getSuppliers_id().equals("1")) {//??????
            if (data.getStatus() == 1) {//?????????
                // ?????????????????????  0???????????????1?????????
                if (data.getIs_edit_address().equals("0")) {
                    orderUpdateAddress.setVisibility(View.GONE);//????????????
                } else if (data.getIs_edit_address().equals("1")) {
                    orderUpdateAddress.setVisibility(View.VISIBLE);//????????????
                }
                orderQuxiaoBtn.setVisibility(View.VISIBLE);//????????????
                orderPayBtn.setVisibility(View.VISIBLE);//????????????
                orderSeeWuliu.setVisibility(View.GONE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
            } else if (data.getStatus() == 2) {//?????????
                // ?????????????????????  0???????????????1?????????
                if (data.getIs_edit_address().equals("0")) {
                    orderUpdateAddress.setVisibility(View.GONE);//????????????
                } else if (data.getIs_edit_address().equals("1")) {
                    orderUpdateAddress.setVisibility(View.VISIBLE);//????????????
                }
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
                orderTuihuanBtn.setText("????????????");
                List<OrderDetailsBean.ShopListBean> newList = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList1 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList2 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList3 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList4 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList5 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList6 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList7 = new ArrayList<>();
                for (int i = 0; i < data.getShop_list().size(); i++) {
                    if (data.getShop_list().get(i).getShop_status().equals("7")) {//?????????
                        newList7.add(data.getShop_list().get(i));
                        if (newList7.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("6")) {//?????????
                        newList6.add(data.getShop_list().get(i));
                        if (newList6.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("5")) {//????????????
                        newList5.add(data.getShop_list().get(i));
                        if (newList5.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("4")) {//????????????--????????????0
                        newList4.add(data.getShop_list().get(i));
                        if (newList4.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("3")) {//????????????
                        newList3.add(data.getShop_list().get(i));
                        if (newList3.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("2")) {//????????????
                        newList2.add(data.getShop_list().get(i));
                        if (newList2.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("1")) {//?????????
                        newList1.add(data.getShop_list().get(i));
                        if (newList1.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("0")) {//?????????
                        newList.add(data.getShop_list().get(i));
                        if (newList.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    }
                }
            } else if (data.getStatus() == 3) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.VISIBLE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
                List<OrderDetailsBean.ShopListBean> newList = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList1 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList2 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList3 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList4 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList5 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList6 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList7 = new ArrayList<>();
                for (int i = 0; i < data.getShop_list().size(); i++) {
                    if (data.getShop_list().get(i).getShop_status().equals("7")) {//?????????
                        newList7.add(data.getShop_list().get(i));
                        if (newList7.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("6")) {//?????????
                        newList6.add(data.getShop_list().get(i));
                        if (newList6.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("5")) {//????????????
                        newList5.add(data.getShop_list().get(i));
                        if (newList5.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("4")) {//????????????--????????????0
                        newList4.add(data.getShop_list().get(i));
                        if (newList4.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("3")) {//????????????
                        newList3.add(data.getShop_list().get(i));
                        if (newList3.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("2")) {//????????????
                        newList2.add(data.getShop_list().get(i));
                        if (newList2.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("1")) {//?????????
                        newList1.add(data.getShop_list().get(i));
                        if (newList1.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("0")) {//?????????
                        newList.add(data.getShop_list().get(i));
                        if (newList.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    }
                }
            } else if (data.getStatus() == 4) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
                orderTuihuanBtn.setText("????????????");
                List<OrderDetailsBean.ShopListBean> newList = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList1 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList2 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList3 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList4 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList5 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList6 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList7 = new ArrayList<>();
                for (int i = 0; i < data.getShop_list().size(); i++) {
                    if (data.getShop_list().get(i).getShop_status().equals("7")) {//?????????
                        newList7.add(data.getShop_list().get(i));
                        if (newList7.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("6")) {//?????????
                        newList6.add(data.getShop_list().get(i));
                        if (newList6.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("5")) {//????????????
                        newList5.add(data.getShop_list().get(i));
                        if (newList5.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("4")) {//????????????--????????????0
                        newList4.add(data.getShop_list().get(i));
                        if (newList4.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("3")) {//????????????
                        newList3.add(data.getShop_list().get(i));
                        if (newList3.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("2")) {//????????????
                        newList2.add(data.getShop_list().get(i));
                        if (newList2.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("1")) {//?????????
                        newList1.add(data.getShop_list().get(i));
                        if (newList1.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("0")) {//?????????
                        newList.add(data.getShop_list().get(i));
                        if (newList.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    }
                }
            } else if (data.getStatus() == 5) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
            } else if (data.getStatus() == 6) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
                orderTuihuanBtn.setText("????????????");
                List<OrderDetailsBean.ShopListBean> newList = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList1 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList2 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList3 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList4 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList5 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList6 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList7 = new ArrayList<>();
                for (int i = 0; i < data.getShop_list().size(); i++) {
                    if (data.getShop_list().get(i).getShop_status().equals("7")) {//?????????
                        newList7.add(data.getShop_list().get(i));
                        if (newList7.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("6")) {//?????????
                        newList6.add(data.getShop_list().get(i));
                        if (newList6.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("5")) {//????????????
                        newList5.add(data.getShop_list().get(i));
                        if (newList5.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("4")) {//????????????--????????????0
                        newList4.add(data.getShop_list().get(i));
                        if (newList4.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("3")) {//????????????
                        newList3.add(data.getShop_list().get(i));
                        if (newList3.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("2")) {//????????????
                        newList2.add(data.getShop_list().get(i));
                        if (newList2.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("1")) {//?????????
                        newList1.add(data.getShop_list().get(i));
                        if (newList1.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("0")) {//?????????
                        newList.add(data.getShop_list().get(i));
                        if (newList.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    }
                }
            } else if (data.getStatus() == 7) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.GONE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
            }
        } else if (data.getShop_list().get(0).getSuppliers_id().equals("2")) {//??????
            if (data.getStatus() == 1) {//?????????
                // ?????????????????????  0???????????????1?????????
                if (data.getIs_edit_address().equals("0")) {
                    orderUpdateAddress.setVisibility(View.GONE);//????????????
                } else if (data.getIs_edit_address().equals("1")) {
                    orderUpdateAddress.setVisibility(View.VISIBLE);//????????????
                }
                orderQuxiaoBtn.setVisibility(View.VISIBLE);//????????????
                orderPayBtn.setVisibility(View.VISIBLE);//????????????
                orderSeeWuliu.setVisibility(View.GONE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
            } else if (data.getStatus() == 2) {//?????????
                // ?????????????????????  0???????????????1?????????
                if (data.getIs_edit_address().equals("0")) {
                    orderUpdateAddress.setVisibility(View.GONE);//????????????
                } else if (data.getIs_edit_address().equals("1")) {
                    orderUpdateAddress.setVisibility(View.VISIBLE);//????????????
                }
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
                orderTuihuanBtn.setText("????????????");
                List<OrderDetailsBean.ShopListBean> newList = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList1 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList2 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList3 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList4 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList5 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList6 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList7 = new ArrayList<>();
                if (getIs_all_refund.equals("1")) {//????????????
                    orderTuihuanBtn.setVisibility(View.GONE);
                } else {//????????????
                    for (int i = 0; i < data.getShop_list().size(); i++) {
                        if (data.getShop_list().get(i).getShop_status().equals("7")) {//?????????
                            newList7.add(data.getShop_list().get(i));
                            if (newList7.size() == data.getShop_list().size()) {
                                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                            } else {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            }
                        } else if (data.getShop_list().get(i).getShop_status().equals("6")) {//?????????
                            newList6.add(data.getShop_list().get(i));
                            if (newList6.size() == data.getShop_list().size()) {
                                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                            } else {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            }
                        } else if (data.getShop_list().get(i).getShop_status().equals("5")) {//????????????
                            newList5.add(data.getShop_list().get(i));
                            if (newList5.size() == data.getShop_list().size()) {
                                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                            } else {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            }
                        } else if (data.getShop_list().get(i).getShop_status().equals("4")) {//????????????
                            newList4.add(data.getShop_list().get(i));
                            if (newList4.size() == data.getShop_list().size()) {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            } else {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            }
                        } else if (data.getShop_list().get(i).getShop_status().equals("3")) {//????????????
                            newList3.add(data.getShop_list().get(i));
                            if (newList3.size() == data.getShop_list().size()) {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            } else {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            }
                        } else if (data.getShop_list().get(i).getShop_status().equals("2")) {//????????????
                            newList2.add(data.getShop_list().get(i));
                            if (newList2.size() == data.getShop_list().size()) {
                                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                            } else {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            }
                        } else if (data.getShop_list().get(i).getShop_status().equals("1")) {//?????????
                            newList1.add(data.getShop_list().get(i));
                            if (newList1.size() == data.getShop_list().size()) {
                                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                            } else {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            }
                        } else if (data.getShop_list().get(i).getShop_status().equals("0")) {//?????????
                            newList.add(data.getShop_list().get(i));
                            if (newList.size() == data.getShop_list().size()) {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            } else {
                                orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                            }
                        }
                    }
                }
            } else if (data.getStatus() == 3) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.VISIBLE);//????????????
//            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
                orderTuihuanBtn.setText("????????????");
                List<OrderDetailsBean.ShopListBean> newList = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList1 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList2 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList3 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList4 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList5 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList6 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList7 = new ArrayList<>();
                for (int i = 0; i < data.getShop_list().size(); i++) {
                    if (data.getShop_list().get(i).getShop_status().equals("7")) {//?????????
                        newList7.add(data.getShop_list().get(i));
                        if (newList7.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("6")) {//?????????
                        newList6.add(data.getShop_list().get(i));
                        if (newList6.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("5")) {//????????????
                        newList5.add(data.getShop_list().get(i));
                        if (newList5.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("4")) {//????????????
                        newList4.add(data.getShop_list().get(i));
                        if (newList4.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("3")) {//????????????
                        newList3.add(data.getShop_list().get(i));
                        if (newList3.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("2")) {//????????????
                        newList2.add(data.getShop_list().get(i));
                        if (newList2.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("1")) {//?????????
                        newList1.add(data.getShop_list().get(i));
                        if (newList1.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("0")) {//?????????
                        newList.add(data.getShop_list().get(i));
                        if (newList.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    }
                }
            } else if (data.getStatus() == 4) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
                orderTuihuanBtn.setText("????????????");
                List<OrderDetailsBean.ShopListBean> newList = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList1 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList2 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList3 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList4 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList5 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList6 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList7 = new ArrayList<>();
                for (int i = 0; i < data.getShop_list().size(); i++) {
                    if (data.getShop_list().get(i).getShop_status().equals("7")) {//?????????
                        newList7.add(data.getShop_list().get(i));
                        if (newList7.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("6")) {//?????????
                        newList6.add(data.getShop_list().get(i));
                        if (newList6.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("5")) {//????????????
                        newList5.add(data.getShop_list().get(i));
                        if (newList5.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("4")) {//????????????
                        newList4.add(data.getShop_list().get(i));
                        if (newList4.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("3")) {//????????????
                        newList3.add(data.getShop_list().get(i));
                        if (newList3.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("2")) {//????????????
                        newList2.add(data.getShop_list().get(i));
                        if (newList2.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("1")) {//?????????
                        newList1.add(data.getShop_list().get(i));
                        if (newList1.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("0")) {//?????????
                        newList.add(data.getShop_list().get(i));
                        if (newList.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    }
                }
            } else if (data.getStatus() == 5) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
            } else if (data.getStatus() == 6) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.VISIBLE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
                orderTuihuanBtn.setText("????????????");
                List<OrderDetailsBean.ShopListBean> newList = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList1 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList2 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList3 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList4 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList5 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList6 = new ArrayList<>();
                List<OrderDetailsBean.ShopListBean> newList7 = new ArrayList<>();
                for (int i = 0; i < data.getShop_list().size(); i++) {
                    if (data.getShop_list().get(i).getShop_status().equals("7")) {//?????????
                        newList7.add(data.getShop_list().get(i));
                        if (newList7.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("6")) {//?????????
                        newList6.add(data.getShop_list().get(i));
                        if (newList6.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("5")) {//????????????
                        newList5.add(data.getShop_list().get(i));
                        if (newList5.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("4")) {//????????????
                        newList4.add(data.getShop_list().get(i));
                        if (newList4.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("3")) {//????????????
                        newList3.add(data.getShop_list().get(i));
                        if (newList3.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("2")) {//????????????
                        newList2.add(data.getShop_list().get(i));
                        if (newList2.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("1")) {//?????????
                        newList1.add(data.getShop_list().get(i));
                        if (newList1.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.GONE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    } else if (data.getShop_list().get(i).getShop_status().equals("0")) {//?????????
                        newList.add(data.getShop_list().get(i));
                        if (newList.size() == data.getShop_list().size()) {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        } else {
                            orderTuihuanBtn.setVisibility(View.VISIBLE);//????????????
                        }
                    }
                }
            } else if (data.getStatus() == 7) {//?????????
                orderQuxiaoBtn.setVisibility(View.GONE);//????????????
                orderPayBtn.setVisibility(View.GONE);//????????????
                orderUpdateAddress.setVisibility(View.GONE);//????????????
                orderSeeWuliu.setVisibility(View.GONE);//????????????
                orderShouhuoBtn.setVisibility(View.GONE);//????????????
                orderTuihuanBtn.setVisibility(View.GONE);//????????????
                orderDetailsOederTime.setText("???????????????" + data.getConfirm_time());
            }
        }
    }

    /**
     * @Description:??????????????????
     * @Time:2020/5/8 14:22
     * @Author:pk
     */
    @Override
    public void getOederDetailsFail(int code, String msg) {
        if (code == 4005) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Description:??????????????????
     * @Time:2020/5/8 14:22
     * @Author:pk
     */
    @Override
    public void getCelearOrderSuccess(int code, AllOrderListBean data) {
        finish();

    }

    /**
     * @Description:??????????????????
     * @Time:2020/5/8 14:22
     * @Author:pk
     */
    @Override
    public void getCelearOrderFail(int code, String msg) {

    }

    /**
     * @Description:????????????
     * @Time:2020/5/8 14:22
     * @Author:pk
     */
    @Override
    public void getSignInOrderSuccess(int code, SiginOrderBean data) {
        orderQuxiaoBtn.setVisibility(View.GONE);//????????????
        orderPayBtn.setVisibility(View.GONE);//????????????
        orderShouhuoBtn.setVisibility(View.GONE);//????????????
        orderTuihuanBtn.setVisibility(View.GONE);//?????????
    }

    /**
     * @Description:????????????
     * @Time:2020/5/8 14:22
     * @Author:pk
     */
    @Override
    public void getSignInOrderFail(int code, String msg) {

    }

    /**
     * ???????????????????????????
     *
     * @param code
     * @param data
     */
    @Override
    public void getAddShopCarSuccess(int code, Object data) {
        Toast.makeText(this, "?????????????????????", Toast.LENGTH_SHORT).show();
    }

    /**
     * ???????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @OnClick({R.id.order_kefu_btn, R.id.order_quxiao_btn, R.id.order_pay_btn, R.id.order_update_address, R.id.order_see_wuliu, R.id.order_shouhuo_btn, R.id.order_tuihuan_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_kefu_btn:
//                if (Build.VERSION.SDK_INT >= 23) {
//                    showContacts();
//                } else {
//                    CustomerService();
//                }
                PermissionHelper.with(getContext()).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request(new PermissionHelper.PermissionListener() {
                            @Override
                            public void onSuccess() {
                                //?????????SDK
                                initSdk();

                            }

                            @Override
                            public void onFailed() {
                            }
                        });
                break;
            case R.id.order_quxiao_btn://????????????
                presenter.getCelearOrderData(getOrder_id, getStatus + "");
                break;
            case R.id.order_pay_btn://??????
                String recId = "";
                String goods_id = "";
                String product_id = "";
                String num = "";
                String real_id = "";
                String address_id = "";
                String type = "";
                String confirmMessage = "";
                String confirmMessage2 = "";
                PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage, confirmMessage2, getOrder_id,
                        getOrder_amount, "", "", "", "","","","");

                break;
            case R.id.order_update_address://????????????
                AddressActivity.startSelf(getActivity(), "2");
                break;
            case R.id.order_see_wuliu://????????????
                LogisticsActivity.startSelf(this, getOrder_id);
                break;
            case R.id.order_shouhuo_btn://????????????
                presenter.getSignInOrderData(getOrder_id);
                break;
            case R.id.order_tuihuan_btn://????????????
                List<OrderDetailsBean.ShopListBean> list = new ArrayList();
                List<OrderDetailsBean.ShopListBean> lists = orderDetailsBean.getShop_list();
                for (int i = 0; i < lists.size(); i++) {
                    if (lists.get(i).getShop_status().equals("0") || lists.get(i).getShop_status().equals("3")) {
                        list.add(lists.get(i));
                    }
                }
                orderDetailsBean.setShop_list(list);

                if (orderDetailsBean.getShop_list().get(0).getSuppliers_id().equals("1")) {//??????
                    AfterSalesActivity.startSelf(this, orderDetailsBean, getIs_refund_all, String.valueOf(getStatus));//??????????????????--????????????????????????
                } else if (orderDetailsBean.getShop_list().get(0).getSuppliers_id().equals("2")) {//??????
                    if (getStatus == 2) {//???????????????--?????????
                        if (getIs_refund_all.equals("1")) {//?????????????????????????????????
                            //????????????????????????????????????????????????--??????
                            ApplyRefundSelectActivity_1.startSelf(OrderDetailsActivity.this, orderDetailsBean.getShop_list(), orderDetailsBean.getOrder_id(), "1", getIs_refund_all, String.valueOf(getStatus));
                        } else if (getIs_refund_all.equals("0")) {//?????????????????????????????????
                            //??????????????????????????????--??????????????????
                            AfterSalesShopActivity.startSelf(this, orderDetailsBean, "1", getIs_refund_all, String.valueOf(getStatus));
                        }
                    } else {//????????????????????????????????????
                        AfterSalesActivity.startSelf(this, orderDetailsBean, getIs_refund_all, String.valueOf(getStatus));//??????????????????--????????????????????????
                    }
                }


                break;
        }
    }


    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "????????????,???????????????????????????", Toast.LENGTH_SHORT).show();
            // ?????????????????????????????????????????????????????????????????????????????????????????????
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        } else {
            CustomerService();
        }
    }

    @SuppressLint("MissingPermission")
    public void CustomerService() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        sdktoken = TelephonyMgr.getDeviceId();
        sdktoken = App.getDeviceId;
        Log.e("??????Token???", "===sdktoken====" + sdktoken);
        if (TextUtils.isEmpty(sdktoken)) {
            sdktoken = DeviceIdUtils.getId();
        }

//        UdeskSDKManager.getInstance().initApiKey(this, "ali-8fbe83.udesk.cn", "63ce4fc962cfe796cb60ace665215188", "b2c735f0dc0fb7ca");
//        UdeskSDKManager.getInstance().entryChat(getApplicationContext(), makeBuilder().build(), sdktoken);
    }


    //????????????--??????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddressListBean_3Event event) {
        Log.e("????????????", "==??????????????????==" + event.getAddressListBean());
        String address_id = event.getAddressListBean().getAddress_id();
        presenter.getEditAddressOrderData(getOrder_id, address_id);//????????????

    }

    /**
     * ?????????SDK
     */
    private void initSdk() {
        //??????sdk ??????????????????
//        initLanguage("en");
        /*
          ?????????:?????????help
         */
        final KfStartHelper helper = new KfStartHelper(this);
        /*
          ?????????????????????????????????????????????????????????
         */
//        handleCardInfo(helper);
         /*
          ????????????????????????????????????????????????????????????
         */
//        handleNewCardInfo(helper);
        /*
          ?????????:????????????
          ?????????sdk???????????????????????????????????????????????????????????????IM????????????
          @param accessId       ??????id???????????????????????????
          @param userName       ?????????
          @param userId         ??????id
         */
        /*
         * ?????????????????? ???????????? ????????????????????? ??????
         */
//        helper.setChatActivityLeftText("??????");

        /**
         * ??????orderid???????????????
         */
        helper.setOrderHead(orderDetailsBean.getOrder_id(), UserUtils.getInstance().getUserInfo().getImg_url());
        /*
         * ?????????????????? ???????????? emoji?????? ?????????
         */
//        helper.setChatActivityEmoji(true);

        String userID = UserUtils.getInstance().getUserInfo().getUser_id();
        if (UserUtils.getInstance().getUserInfo().getUser_id().length() < 2) {
            userID = "0" + userID;
        }
        helper.initSdkChat("97e623b0-f404-11ea-938a-2d31778d2422", UserUtils.getInstance().getUserInfo().getUser_name(), userID);
    }

    /**
     * ???????????????????????????
     */
    private void getUnReadCount() {
        if (MoorUtils.isInitForUnread(getApplicationContext())) {

            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acount) {
                    Toast.makeText(OrderDetailsActivity.this, "?????????????????????" + acount, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //?????????????????????????????? ???0
            Toast.makeText(OrderDetailsActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * ??????????????????
     *
     * @param helper
     */
    private void handleCardInfo(KfStartHelper helper) {
        String s = "https://wap.boosoo.com.cn/bobishop/goodsdetail?id=10160&mid=36819";
//        String s = "https://share1.atoshi.cn/#/productdetail?productId=376&userId=100123350544";
        CardInfo ci = new CardInfo("http://seopic.699pic.com/photo/40023/0579.jpg_wh1200.jpg", "??????????????????????????????", "??????name???????????????", "?????? 1000-9999", "https://www.baidu.com");
        String icon = "https://www.tianxiadengcang.com//index.php?m=Api&c=Goods&a=goodsThumImages&width=200&height=200&goods_id=168";
        String title = "??????????????????????????????????????????????????????D1-31008-12???";
        String content = "8???/??520*H350/96W ?????????????????? ??????";
        String rigth3 = " ??548.00";
        try {
            ci = new CardInfo(URLEncoder.encode(icon, "utf-8"), URLEncoder.encode(title, "utf-8"),
                    URLEncoder.encode(content, "utf-8"), URLEncoder.encode(rigth3, "utf-8"),
                    URLEncoder.encode(s, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper.setCard(ci);
    }

    /**
     * ?????????????????????,{@link NewCardInfo.Builder()} Builder??????????????????????????????????????????????????????
     */
    private void handleNewCardInfo(KfStartHelper helper) {
//        NewCardInfo newCardInfo = new NewCardInfo.Builder()
//                .build();

        NewCardInfo newCardInfo = new NewCardInfo.Builder()
                .setTitle("????????????")
                .setAttr_one(new NewCardInfoAttrs().setColor("#487903").setContent("x9"))
                .setAttr_two(new NewCardInfoAttrs().setColor("#845433").setContent("?????????"))
                .setOther_title_one("????????????")
                .setOther_title_two(null)
                .setOther_title_three(null)
                .setSub_title("???????????????")
                .setPrice("$999")
                .build();


        helper.setNewCardInfo(newCardInfo);
    }

    /**
     * ????????????
     * ?????? language???""
     * ?????? language???"en"
     */
    private void initLanguage(String language) {
        Resources resources = getApplicationContext().getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(language);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());//????????????
    }

    //Android6.0???????????????????????????
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode????????????????????????????????????checkSelfPermission?????????
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // ????????????????????????????????????????????????SDK????????????????????????????????????????????????????????????????????????
//                    CustomerService();

                    break;
                } else {
                    // ???????????????????????????????????????
                    Toast.makeText(this, "??????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


}
