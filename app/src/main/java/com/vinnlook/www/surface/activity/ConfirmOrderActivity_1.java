package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundRelativeLayout;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.AddressListBean_2Event;
import com.vinnlook.www.event.Address_KeyDown;
import com.vinnlook.www.event.CancelYouHuiQuanEvent;
import com.vinnlook.www.event.CouponEvent;
import com.vinnlook.www.event.PayMemberEvent;
import com.vinnlook.www.event.RealName_2Event;
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.http.model.AlreadyCouponListBean;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.adapter.ConfirmOrder_1Adapter;
import com.vinnlook.www.surface.adapter.ConfirmOrder_1ZengAdapter;
import com.vinnlook.www.surface.adapter.ConfirmOrder_2Adapter;
import com.vinnlook.www.surface.adapter.ConfirmOrder_2ZengAdapter;
import com.vinnlook.www.surface.adapter.ConponListYseAdapter;
import com.vinnlook.www.surface.adapter.ConponList_1Adapter;
import com.vinnlook.www.surface.adapter.ExpressList_1Adapter;
import com.vinnlook.www.surface.adapter.ExpressList_2Adapter;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.RealNameListBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.presenter.ConfirmOrderPresenter;
import com.vinnlook.www.surface.mvp.view.ConfirmOrderView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:新--确认订单
 * @Time:2020/11/26$
 * @Author:pk$
 */


public class ConfirmOrderActivity_1 extends BaseActivity<ConfirmOrderPresenter> implements ConfirmOrderView {
    @BindView(R.id.confirm_address_name)
    TextView confirmAddressName;//收货人姓名
    @BindView(R.id.confirm_address_phone)
    TextView confirmAddressPhone;//收货人电话
    @BindView(R.id.confirm_address_address)
    TextView confirmAddressAddress;//收货人地址
    @BindView(R.id.rl_address_1)
    RoundRelativeLayout rlAddress1;//地址--有数据
    @BindView(R.id.rl_address_2)
    RoundRelativeLayout rlAddress2;//地址--无数据
    @BindView(R.id.confirm_realname_name)
    TextView confirmRealnameName;//实名姓名
    @BindView(R.id.confirm_realname_idcard)
    TextView confirmRealnameIdcard;//实名身份证号
    @BindView(R.id.order_realname_2)
    RoundRelativeLayout orderRealname2;//实名--有数据
    @BindView(R.id.order_realname_1)
    RoundRelativeLayout orderRealname1;//实名--无数据
    @BindView(R.id.confirm_recycler_hai)
    RecyclerView confirmRecyclerHai;//海淘List
    @BindView(R.id.confirm_message2)
    EditText confirmMessage2;//海淘留言
    @BindView(R.id.confirm_recycler_zi)
    RecyclerView confirmRecyclerZi;//自营List
    @BindView(R.id.confirm_message1)
    EditText confirmMessage1;//自营留言
    @BindView(R.id.confirm_order_fee)
    TextView confirmOrderFee;//运费
    @BindView(R.id.order_yhq_money)
    TextView orderYhqMoney;//优惠券Text
    @BindView(R.id.order_conpon_layout)
    RoundRelativeLayout orderConponLayout;//优惠券布局
    @BindView(R.id.order_yhq_jine_money)
    TextView orderYhqJineMoney;//优惠金额
    @BindView(R.id.confirm_order_price)
    TextView confirmOrderPrice;//商品金额
    @BindView(R.id.confirm_order_toale_price)
    TextView confirmOrderToalePrice;//合计
    @BindView(R.id.tv_ko)
    RoundTextView tvKo;//提交订单
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R.id.hai_title_text)
    TextView haiTitleText;//海淘Title
    @BindView(R.id.order_hai_express)
    TextView orderHaiExpress;//海淘快递Text
    @BindView(R.id.order_express_hai_layout)
    RoundRelativeLayout orderExpressHaiLayout;//海淘-快递布局
    @BindView(R.id.confirm_hai_shuifei)
    TextView confirmHaiShuifei;//海淘税费
    @BindView(R.id.zi_title_text)
    TextView ziTitleText;//自营-Title
    @BindView(R.id.order_zi_express)
    TextView orderZiExpress;//自营快递Text
    @BindView(R.id.order_zi_express_layout)
    RoundRelativeLayout orderZiExpressLayout;//自营-快递布局

    @BindView(R.id.hai_tao_all_layout)
    LinearLayout haiTaoAllLayout;//海淘全部布局
    @BindView(R.id.zi_ying_all_layout)
    LinearLayout ziYingAllLayout;//自营全部布局

    @BindView(R.id.confirm_recycler_hai_zeng)
    RecyclerView confirmRecyclerHaiZeng;
    @BindView(R.id.confirm_recycler_zi_zeng)
    RecyclerView confirmRecyclerZiZeng;

    @BindView(R.id.confirm_haitao_shuoming)
    TextView confirmHaitaoShuoming;
    @BindView(R.id.confirm_recycler_hai_zeng_line)
    View confirmRecyclerHaiZengLine;
    @BindView(R.id.confirm_recycler_zi_zeng_line)
    View confirmRecyclerZiZengLine;
    @BindView(R.id.order_conpon_jine_layout)
    RelativeLayout orderConponJineLayout;
    @BindView(R.id.ht_icon_img)
    ImageView htIconImg;
    @BindView(R.id.zy_icon_img)
    ImageView zyIconImg;
    @BindView(R.id.confrim_member_reduce_text)
    TextView confrimMemberReduceText;
    @BindView(R.id.confrim_member_price_text)
    TextView confrimMemberPriceText;

    @BindView(R.id.pay_member_layout_btn)
    RelativeLayout payMemberLayoutBtn;


    static String recId = "";
    static String goods_id = "";
    static String product_id = "";
    static String num = "";
    static String flag;//等于1为购物车进入；等于2为商品详情进入；
    String address_id = "";
    String real_id = "";
    String bonus_id = "";//优惠券ID
    String waybill_HT_id;//快递ID
    String waybill_ZY_id;//快递ID
    String payPrice;

    ConfirmOrderBean confirmOrderBean;

    ConfirmOrder_1Adapter ziAdapter;
    ConfirmOrder_1ZengAdapter ziZengAdapter;
    ConfirmOrder_2Adapter haiAdapter;
    ConfirmOrder_2ZengAdapter haiZengAdapter;
    public PopupWindow popupwindow;

    RealNameListBean realBean = null;//实名认证Bean
    AddressListBean addressBean = null;//地址Bean

    String getSuppliers_id;//1==有海淘商品；“”==无海淘商品

    StringBuilder sBuilder = new StringBuilder();
    ConfirmOrderBean.ZySendListBean listBean;
    ConfirmOrderBean.HtSendListBean listBean2;
    String getSearch_attr;
    String type;

    String hTSbString = "";
    String zYSbString = "";
    int position;
    List<String> listString = new ArrayList<>();
    List<String> listString1 = new ArrayList<>();

    ConponListYseAdapter yesAdapter;
    List<AlreadyCouponListBean> alreadyCouponListBean;
    String proIdSbs;
    String wayBillId = "";//快递ID


    public static void startSelf(Context context, String recIds, String goods_ids, String product_ids, String nums, String flags) {
        Intent intent = new Intent(context, ConfirmOrderActivity_1.class);
        context.startActivity(intent);
        recId = recIds;
        goods_id = goods_ids;
        product_id = product_ids;
        num = nums;
        flag = flags;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_order_1;
    }

    @Override
    protected ConfirmOrderPresenter initPresenter() {
        return new ConfirmOrderPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(ConfirmOrderActivity_1.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        int heights = bottomLayout.getHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMarginEnd(heights);
        scrollview.setLayoutParams(layoutParams);

        //自营适配器
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        confirmRecyclerZi.setLayoutManager(manager1);
        confirmRecyclerZi.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        confirmRecyclerZi.addItemDecoration(new SpaceItemDecoration(0, 0));
        confirmRecyclerZi.setNestedScrollingEnabled(false);
        confirmRecyclerZi.setHasFixedSize(true);
        ziAdapter = new ConfirmOrder_1Adapter(this);
        confirmRecyclerZi.setAdapter(ziAdapter);

        //自营--赠送--适配器
        final LinearLayoutManager manager3 = new LinearLayoutManager(getActivity());
        confirmRecyclerZiZeng.setLayoutManager(manager3);
        confirmRecyclerZiZeng.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        confirmRecyclerZiZeng.addItemDecoration(new SpaceItemDecoration(0, 0));
        confirmRecyclerZiZeng.setNestedScrollingEnabled(false);
        confirmRecyclerZiZeng.setHasFixedSize(true);
        ziZengAdapter = new ConfirmOrder_1ZengAdapter(this);
        confirmRecyclerZiZeng.setAdapter(ziZengAdapter);


        ziZengAdapter.setPreferClickListener(new ConfirmOrder_1ZengAdapter.PreferClickListener() {
            @Override
            public void onGoReClickListener(ConfirmOrderBean.ZySendListBean data, String getGoods_id, String getSearch_attrs, int positions) {
                type = "1";
                listBean = data;
                getSearch_attr = getSearch_attrs;
                position = positions;
//                presenter.getTypeShopData(getGoods_id);
                presenter.getTypeShopData(getGoods_id);
            }
        });


        //海淘适配器
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 1);
        confirmRecyclerHai.setLayoutManager(manager2);
        confirmRecyclerHai.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        confirmRecyclerHai.addItemDecoration(new SpaceItemDecoration(0, 0));
        confirmRecyclerHai.setNestedScrollingEnabled(false);
        confirmRecyclerHai.setHasFixedSize(true);
        haiAdapter = new ConfirmOrder_2Adapter(this);
        confirmRecyclerHai.setAdapter(haiAdapter);


        //海淘--赠送--适配器
        final LinearLayoutManager manager4 = new LinearLayoutManager(getActivity());
        confirmRecyclerHaiZeng.setLayoutManager(manager4);
        confirmRecyclerHaiZeng.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        confirmRecyclerHaiZeng.addItemDecoration(new SpaceItemDecoration(0, 0));
        confirmRecyclerHaiZeng.setNestedScrollingEnabled(false);
        confirmRecyclerHaiZeng.setHasFixedSize(true);
        haiZengAdapter = new ConfirmOrder_2ZengAdapter(this);
        confirmRecyclerHaiZeng.setAdapter(haiZengAdapter);

        haiZengAdapter.setPreferClickListener(new ConfirmOrder_2ZengAdapter.PreferClickListener() {
            @Override
            public void onGoReClickListener(ConfirmOrderBean.HtSendListBean data, String getGoods_id, String getSearch_attrs, int positions) {
                type = "2";
                listBean2 = data;
                getSearch_attr = getSearch_attrs;
                position = positions;
//                presenter.getTypeShopData(getGoods_id);
                presenter.getTypeShopData(getGoods_id);
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);

    }

    @OnClick({R.id.order_realname_2, R.id.order_realname_1, R.id.rl_address_1, R.id.rl_address_2, R.id.pay_member_layout_btn, R.id.order_express_hai_layout, R.id.order_zi_express_layout,
            R.id.order_conpon_layout, R.id.order_conpon_jine_layout, R.id.tv_ko})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_realname_2://有数据--实名认证
                RealNameActivity.startSelf(this, "0");
                break;
            case R.id.order_realname_1:// 无数据  --实名认证
                RealNameActivity.startSelf(this, "0");
                break;
            case R.id.rl_address_1://有数据  --地址
                AddressActivity.startSelf(this, "0");
                break;
            case R.id.rl_address_2://无数据  --地址
                AddressActivity.startSelf(this, "0");
                break;

            case R.id.pay_member_layout_btn://购买会员
                MemberActivity_1.startSelf(getContext(), "3");//会员购买入口  1---详情页面，，2--其他页面进入会员购买页面，3---确认订单页面
                break;

            case R.id.order_express_hai_layout://选择海淘配送方式
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView2();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;
            case R.id.order_zi_express_layout://选择自营配送方式
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {

                    initmPopupWindowView1();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;
            case R.id.order_conpon_layout://优惠券列表
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {

                    initmPopupWindowView5();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }


                break;
            case R.id.order_conpon_jine_layout://优惠金额列表
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {

                    initmPopupWindowView3();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;

            case R.id.tv_ko://提交订单
                String type = "";
                Log.e("提交订单", "==recId==" + recId);
                Log.e("提交订单", "==goods_id==" + goods_id);
                Log.e("提交订单", "==product_id==" + product_id);
                Log.e("提交订单", "==num==" + num);
                Log.e("提交订单", "==real_id==" + real_id);
                Log.e("提交订单", "==address_id==" + address_id);
                Log.e("提交订单", "==type==" + type);
                Log.e("提交订单", "==confirmMessage1.getText().toString()==" + confirmMessage1.getText().toString());
                Log.e("提交订单", "==confirmMessage2.getText().toString()==" + confirmMessage2.getText().toString());
                Log.e("提交订单", "==payPrice==" + payPrice);
                Log.e("提交订单", "==bonus_id==" + bonus_id);
                Log.e("提交订单", "==sB.toString()==" + sBuilder.toString());
                Log.e("提交订单", "==zYSbString==" + zYSbString);
                Log.e("提交订单", "== hTSbString==" + hTSbString);

                StringBuilder sb = new StringBuilder();
                StringBuilder sb1 = new StringBuilder();


                if (haiZengAdapter.getData().size() > 0) {
                    listString = new ArrayList<>();
                    for (int i = 0; i < haiZengAdapter.getData().size(); i++) {
                        if (haiZengAdapter.getData().get(i).getType().equals("1")) {

                            Toast.makeText(this, "赠送商品【" + haiZengAdapter.getData().get(i).getGoods_name() + "】,请选择规格", Toast.LENGTH_SHORT).show();
                            sb.append(i);

                        } else {
                            listString.add(String.valueOf(i));
                        }
                    }
                    Log.e("提交订单", "==海==listString==111==" + listString.size());
                    Log.e("提交订单", "==海==size()==111==" + haiZengAdapter.getData().size());
                    if (listString.size() == haiZengAdapter.getData().size()) {
                        if (ziZengAdapter.getData().size() > 0) {
                            listString1 = new ArrayList<>();
                            for (int i = 0; i < ziZengAdapter.getData().size(); i++) {
                                if (ziZengAdapter.getData().get(i).getType().equals("1")) {
                                    Toast.makeText(this, "赠送商品【" + ziZengAdapter.getData().get(i).getGoods_name() + "】,请选择规格", Toast.LENGTH_SHORT).show();
                                    sb1.append(i);

                                } else {
                                    listString1.add(String.valueOf(i));
                                }
                            }
                            Log.e("提交订单", "==自==listString1()==111==" + listString1.size());
                            Log.e("提交订单", "==自==size()==111==" + ziZengAdapter.getData().size());
                            if (listString1.size() == ziZengAdapter.getData().size()) {
                                if (address_id.equals("")) {
                                    Toast.makeText(this, "请先选择收货地址信息", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (getSuppliers_id.equals("")) {//没有海淘商品
                                        PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                                                confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString);
                                    } else if (getSuppliers_id.equals("1")) {//有海淘商品
                                        if (real_id.equals("")) {
                                            Toast.makeText(this, "请先选择实名认证信息", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (popupwindow != null && popupwindow.isShowing()) {
                                                popupwindow.dismiss();
                                                return;
                                            } else {
                                                initmPopupWindowView4();
                                                popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                                            }

                                        }
                                    }
                                }
                            }
                        } else {
                            if (address_id.equals("")) {
                                Toast.makeText(this, "请先选择收货地址信息", Toast.LENGTH_SHORT).show();
                            } else {
                                if (getSuppliers_id.equals("")) {//没有海淘商品
                                    PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                                            confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString);
                                } else if (getSuppliers_id.equals("1")) {//有海淘商品
                                    if (real_id.equals("")) {
                                        Toast.makeText(this, "请先选择实名认证信息", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (popupwindow != null && popupwindow.isShowing()) {
                                            popupwindow.dismiss();
                                            return;
                                        } else {
                                            initmPopupWindowView4();
                                            popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                                        }

                                    }
                                }
                            }

                        }

                    }
                } else if (ziZengAdapter.getData().size() > 0) {
                    listString1 = new ArrayList<>();
                    for (int i = 0; i < ziZengAdapter.getData().size(); i++) {
                        if (ziZengAdapter.getData().get(i).getType().equals("1")) {
                            Toast.makeText(this, "赠送商品【" + ziZengAdapter.getData().get(i).getGoods_name() + "】,请选择规格", Toast.LENGTH_SHORT).show();
                            sb1.append(i);

                        } else {
                            listString1.add(String.valueOf(i));
                        }
                    }
                    Log.e("提交订单", "==自==listString1()==222==" + listString1.size());
                    Log.e("提交订单", "==自==size()==222==" + ziZengAdapter.getData().size());
                    if (listString1.size() == ziZengAdapter.getData().size()) {
                        if (address_id.equals("")) {
                            Toast.makeText(this, "请先选择收货地址信息", Toast.LENGTH_SHORT).show();
                        } else {
                            if (getSuppliers_id.equals("")) {//没有海淘商品
                                PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                                        confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString);
                            } else if (getSuppliers_id.equals("1")) {//有海淘商品
                                if (real_id.equals("")) {
                                    Toast.makeText(this, "请先选择实名认证信息", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (popupwindow != null && popupwindow.isShowing()) {
                                        popupwindow.dismiss();
                                        return;
                                    } else {
                                        initmPopupWindowView4();
                                        popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (address_id.equals("")) {
                        Toast.makeText(this, "请先选择收货地址信息", Toast.LENGTH_SHORT).show();
                    } else {
                        if (getSuppliers_id.equals("")) {//没有海淘商品
                            PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                                    confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString);
                        } else if (getSuppliers_id.equals("1")) {//有海淘商品
                            if (real_id.equals("")) {
                                Toast.makeText(this, "请先选择实名认证信息", Toast.LENGTH_SHORT).show();
                            } else {
                                if (popupwindow != null && popupwindow.isShowing()) {
                                    popupwindow.dismiss();
                                    return;
                                } else {
                                    initmPopupWindowView4();
                                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                                }
                            }
                        }
                    }
                }


                break;
        }
    }

    /**
     * 提交订单--提示实名认证
     */
    private void initmPopupWindowView4() {
        TextView return_update_btn, sure_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.tips_haitao_layout, null, false);
        return_update_btn = customView.findViewById(R.id.return_update_btn);
        sure_btn = customView.findViewById(R.id.sure_btn);
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        //返回
        return_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
            }
        });
        //确定
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayOrderActivity.startSelf(ConfirmOrderActivity_1.this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                        confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString);
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });

    }


    /**
     * 可以使用优惠券列表
     */
    private void initmPopupWindowView5() {
        ImageView conpon_list_clear;
        RecyclerView conpon_list_recycler;
        TextView clear_conpon_btn;

        Log.e("", "");
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.conpon_yes_list_layout, null, false);
        conpon_list_clear = customView.findViewById(R.id.conpon_list_clear);//取消
        conpon_list_recycler = customView.findViewById(R.id.conpon_list_recycler);//list
        clear_conpon_btn = customView.findViewById(R.id.clear_conpon_btn);//取消选择优惠券
        conpon_list_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        yesAdapter = new ConponListYseAdapter(getActivity());
        conpon_list_recycler.setAdapter(yesAdapter);
        yesAdapter.setData(alreadyCouponListBean);
        yesAdapter.setId(bonus_id);
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });

        //选择优惠券
        yesAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                presenter.getConfirmOrderData1(recId, goods_id, product_id, num, yesAdapter.getData().get(position).getId(), address_id);
                presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, yesAdapter.getData().get(position).getId(), hTSbString, zYSbString, proIdSbs);
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }

                bonus_id = yesAdapter.getData().get(position).getId();

            }
        });
        //取消所选择的优惠券--不使用
        clear_conpon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                presenter.getConfirmOrderData1(recId, goods_id, product_id, num, "-1", address_id);//传-1，不使用优惠券
                presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, "-1", hTSbString, zYSbString, proIdSbs);
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                bonus_id = "-1";
            }
        });


    }


    /**
     * 查看优惠金额列表
     */
    private void initmPopupWindowView3() {
        ImageView conpon_clear;
        RecyclerView conpon_recycler;
        ConponList_1Adapter adapter1;
        Log.e("", "");
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.conpon_list_layout, null, false);
        conpon_clear = customView.findViewById(R.id.conpon_clear);//取消
        conpon_recycler = customView.findViewById(R.id.conpon_recycler);//list
        conpon_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter1 = new ConponList_1Adapter(getActivity());
        conpon_recycler.setAdapter(adapter1);
        adapter1.setData(confirmOrderBean.getDiscountPriceList());//快递
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        //取消
        conpon_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });
    }

    /**
     * 自营快递选择
     */
    private void initmPopupWindowView1() {
        ImageView express_clear;
        RecyclerView express_recycler;
        ExpressList_1Adapter adapter1;
        Log.e("", "");
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.express_list_layout, null, false);
        express_clear = customView.findViewById(R.id.express_clear);//取消
        express_recycler = customView.findViewById(R.id.express_recycler);//list
        express_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter1 = new ExpressList_1Adapter(getActivity());
        express_recycler.setAdapter(adapter1);
        adapter1.setData(confirmOrderBean.getZy_shop_list().getWaybillList());//快递
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        //取消
        express_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });
        //选择配送方式
        adapter1.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                sBuilder = new StringBuilder();
                waybill_ZY_id = String.valueOf(confirmOrderBean.getZy_shop_list().getWaybillList().get(position).getId());
                sBuilder.append("1");
                sBuilder.append(":");
                sBuilder.append(waybill_ZY_id);
                sBuilder.append(",");
                sBuilder.append("2");
                sBuilder.append(":");
                sBuilder.append(waybill_HT_id);

                wayBillId = sBuilder.toString();
                Log.e("快递适配器", "快递ID===" + sBuilder.toString());
//                presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id);
                presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                orderZiExpress.setText(confirmOrderBean.getZy_shop_list().getWaybillList().get(position).getName() + "   " + Html.fromHtml("&yen") +
                        confirmOrderBean.getZy_shop_list().getWaybillList().get(position).getPrice());
            }
        });
    }

    /**
     * 海淘快递选择
     */
    private void initmPopupWindowView2() {
        ImageView express_clear;
        RecyclerView express_recycler;
        ExpressList_2Adapter adapter1;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.express_list_layout, null, false);
        express_clear = customView.findViewById(R.id.express_clear);//取消
        express_recycler = customView.findViewById(R.id.express_recycler);//list
        express_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter1 = new ExpressList_2Adapter(getActivity());
        express_recycler.setAdapter(adapter1);
        adapter1.setData(confirmOrderBean.getHt_shop_list().getWaybillList());//快递
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        //取消
        express_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });

        //选择配送方式
        adapter1.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                sBuilder = new StringBuilder();
                waybill_HT_id = String.valueOf(confirmOrderBean.getHt_shop_list().getWaybillList().get(position).getId());
                sBuilder.append("1");
                sBuilder.append(":");
                sBuilder.append(waybill_ZY_id);
                sBuilder.append(",");
                sBuilder.append("2");
                sBuilder.append(":");
                sBuilder.append(waybill_HT_id);
                wayBillId = sBuilder.toString();
                Log.e("快递适配器", "快递ID===" + sBuilder.toString());
//                presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id);
                presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                orderHaiExpress.setText(confirmOrderBean.getHt_shop_list().getWaybillList().get(position).getName() + "   " + Html.fromHtml("&yen") +
                        confirmOrderBean.getHt_shop_list().getWaybillList().get(position).getPrice());
            }
        });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    //接收消息--系统返回键
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Address_KeyDown event) {
        Log.e("Address_KeyDown", "==系统返回键==");
//        presenter.getConfirmOrderData(recId, goods_id, product_id, num, "", "", "");
        presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);
    }


    //接收消息--优惠券--取消
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CancelYouHuiQuanEvent event) {
        Log.e("", "==实名接收消息==");
        bonus_id = event.getMark();
//        presenter.getConfirmOrderData1(recId, goods_id, product_id, num, bonus_id, address_id);
        presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);

    }

    //接收消息--实名
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(RealName_2Event event) {
        Log.e("接收消息", "==实名接收消息==" + event.getRealNameBean());
        orderRealname2.setVisibility(View.VISIBLE);
        orderRealname1.setVisibility(View.GONE);
        realBean = event.getRealNameBean();
        confirmRealnameName.setText(realBean.getName());
        confirmRealnameIdcard.setText(realBean.getId_card());
        real_id = realBean.getId();
    }

    //接收消息--地址
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddressListBean_2Event event) {
        Log.e("接收消息", "==地址接收消息==" + event.getAddressListBean());
        address_id = event.getAddressListBean().getAddress_id();
//        presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id);
        presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);
    }


    //购买完会员
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PayMemberEvent event) {
        Log.e("确认订单页面", "==购买完会员==");
        if (event.getFlag()) {
            presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);
        }
    }

    //接收消息--优惠券
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CouponEvent event) {
        Log.e("CouponEvent", "==优惠券接收消息==" + event.getData());
        confirmOrderBean = event.getData();
        confirmOrderPrice.setText(Html.fromHtml("&yen") + "" + event.getData().getGoods_price());
        payPrice = event.getData().getPrice();
        confirmOrderToalePrice.setText(event.getData().getPrice());
        confirmOrderFee.setText(Html.fromHtml("&yen") + event.getData().getPost_fee());
        orderYhqJineMoney.setText("-" + Html.fromHtml("&yen") + event.getData().getCoupon_price());

        confrimMemberReduceText.setText(Html.fromHtml("&yen") + event.getData().getMember_order_price());
        confrimMemberPriceText.setText("Plus会员卡一年仅需" + Html.fromHtml("&yen") + event.getData().getMember_price() + "元");
        if (event.getData().getIs_member()) {//是会员
            payMemberLayoutBtn.setVisibility(View.GONE);
        } else {
            payMemberLayoutBtn.setVisibility(View.VISIBLE);
        }

        if (event.getData().getZy_shop_list().getShop_list().size() > 0) {
            ziAdapter.setData(event.getData().getZy_shop_list().getShop_list());
            ziYingAllLayout.setVisibility(View.VISIBLE);
        } else {
            ziYingAllLayout.setVisibility(View.GONE);
        }
        if (event.getData().getHt_shop_list().getShop_list().size() > 0) {
            haiAdapter.setData(event.getData().getHt_shop_list().getShop_list());
            haiTaoAllLayout.setVisibility(View.VISIBLE);
            confirmHaitaoShuoming.setVisibility(View.VISIBLE);
            getSuppliers_id = "1";
        } else {
            getSuppliers_id = "";
            haiTaoAllLayout.setVisibility(View.GONE);
            confirmHaitaoShuoming.setVisibility(View.GONE);
        }
        //海淘赠品
        haiZengAdapter.setData(event.getData().getHt_send_list());
        //自营赠品
        ziZengAdapter.setData(event.getData().getZy_send_list());
        StringBuilder hTSb = new StringBuilder();
        StringBuilder zYSb = new StringBuilder();
        if (event.getData().getHt_send_list().size() > 0) {
            for (int i = 0; i < event.getData().getHt_send_list().size(); i++) {
                hTSb.append(haiZengAdapter.getData().get(i).getRec_id());
                hTSb.append(":");
                hTSb.append(haiZengAdapter.getData().get(i).getProduct_id() + ",");
            }
        }
        if (event.getData().getZy_send_list().size() > 0) {
            for (int i = 0; i < event.getData().getZy_send_list().size(); i++) {
                zYSb.append(ziZengAdapter.getData().get(i).getRec_id());
                zYSb.append(":");
                zYSb.append(ziZengAdapter.getData().get(i).getProduct_id() + ",");
            }
        }

        hTSbString = hTSb.toString();
        zYSbString = zYSb.toString();

        Log.e("赠品", "hTSbString==333==" + hTSbString);
        Log.e("赠品", "zYSbString==333==" + zYSbString);

        ImageLoader.image(this, htIconImg, event.getData().getHt_shop_list().getLogo_image());
        haiTitleText.setText(event.getData().getHt_shop_list().getTitle());//海淘标题
        orderHaiExpress.setText(event.getData().getHt_shop_list().getWaybillList().get(0).getName());//海淘快递
        waybill_HT_id = String.valueOf(event.getData().getHt_shop_list().getWaybillList().get(0).getId());//海淘快递ID
        confirmHaiShuifei.setText(event.getData().getTax_total());//税费
        ImageLoader.image(this, zyIconImg, event.getData().getZy_shop_list().getLogo_image());
        ziTitleText.setText(event.getData().getZy_shop_list().getTitle());//自营标题
        orderZiExpress.setText(event.getData().getZy_shop_list().getWaybillList().get(0).getName());//自营快递
        waybill_ZY_id = String.valueOf(event.getData().getZy_shop_list().getWaybillList().get(0).getId());//自营快递ID
        sBuilder = new StringBuilder();
        sBuilder.append("1");
        sBuilder.append(":");
        sBuilder.append(waybill_ZY_id);
        sBuilder.append(",");
        sBuilder.append("2");
        sBuilder.append(":");
        sBuilder.append(waybill_HT_id);

        wayBillId = sBuilder.toString();

        //默认优惠券
        if (event.getData().getBonus_info().getId() != null) {
            bonus_id = event.getData().getBonus_info().getId();
            orderYhqMoney.setText(event.getData().getBonus_info().getCoupon_name());
        } else {
            orderYhqMoney.setText("未选择优惠券");
            bonus_id = "-1";
        }

        //收货地址
        if (event.getData().getAddress_list().size() > 0) {
            rlAddress1.setVisibility(View.VISIBLE);
            rlAddress2.setVisibility(View.GONE);
            confirmAddressName.setText(event.getData().getAddress_list().get(0).getAddress_name());
            confirmAddressPhone.setText(event.getData().getAddress_list().get(0).getMobile());
            confirmAddressAddress.setText(event.getData().getAddress_list().get(0).getAddress_refer() + " " + event.getData().getAddress_list().get(0).getAddress());
            address_id = event.getData().getAddress_list().get(0).getAddress_id();
        } else {
            address_id = "";
            rlAddress1.setVisibility(View.GONE);
            rlAddress2.setVisibility(View.VISIBLE);
        }
        //实名认证
        if (event.getData().getReal_list().size() > 0) {//有实名认证信息
            orderRealname2.setVisibility(View.VISIBLE);
            orderRealname1.setVisibility(View.GONE);
            confirmRealnameName.setText(event.getData().getReal_list().get(0).getName());
            confirmRealnameIdcard.setText(event.getData().getReal_list().get(0).getId_card());
            real_id = event.getData().getReal_list().get(0).getId();

        } else {
            real_id = "";
            orderRealname2.setVisibility(View.GONE);
            orderRealname1.setVisibility(View.VISIBLE);
        }

    }


    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getConfirmOrderSuccess(int code, ConfirmOrderBean data) {
        presenter.getCouponListData1(recId, goods_id, product_id, num);//已领取列表数据
        confirmOrderBean = data;
        confirmOrderPrice.setText(Html.fromHtml("&yen") + "" + data.getGoods_price());
        payPrice = data.getPrice();
        confirmOrderToalePrice.setText(data.getPrice());
        confirmOrderFee.setText(Html.fromHtml("&yen") + data.getPost_fee());
        orderYhqJineMoney.setText("-" + Html.fromHtml("&yen") + data.getCoupon_price());


        confrimMemberReduceText.setText(Html.fromHtml("&yen") + data.getMember_order_price());
        confrimMemberPriceText.setText("Plus会员卡一年仅需" + Html.fromHtml("&yen") + data.getMember_price() + "元");
        if (data.getIs_member()) {//是会员
            payMemberLayoutBtn.setVisibility(View.GONE);
        } else {
            payMemberLayoutBtn.setVisibility(View.VISIBLE);
        }


        if (data.getZy_shop_list().getShop_list().size() > 0) {
            ziAdapter.setData(data.getZy_shop_list().getShop_list());
            ziYingAllLayout.setVisibility(View.VISIBLE);
        } else {
            ziYingAllLayout.setVisibility(View.GONE);
        }
        if (data.getHt_shop_list().getShop_list().size() > 0) {
            haiAdapter.setData(data.getHt_shop_list().getShop_list());
            haiTaoAllLayout.setVisibility(View.VISIBLE);
            confirmHaitaoShuoming.setVisibility(View.VISIBLE);
            getSuppliers_id = "1";
        } else {
            getSuppliers_id = "";
            haiTaoAllLayout.setVisibility(View.GONE);
            confirmHaitaoShuoming.setVisibility(View.GONE);
        }
        //海淘赠品
        haiZengAdapter.setData(data.getHt_send_list());
        //自营赠品
        ziZengAdapter.setData(data.getZy_send_list());

        StringBuilder hTSb = new StringBuilder();
        StringBuilder zYSb = new StringBuilder();
        if (data.getHt_send_list().size() > 0) {
            for (int i = 0; i < data.getHt_send_list().size(); i++) {
                Log.e("赠品", "hTSbString==数据==" + haiZengAdapter.getData().get(i).getRec_id());
                hTSb.append(haiZengAdapter.getData().get(i).getRec_id());
                hTSb.append(":");
                hTSb.append(haiZengAdapter.getData().get(i).getProduct_id() + ",");
            }
        }
        if (data.getZy_send_list().size() > 0) {
            for (int i = 0; i < data.getZy_send_list().size(); i++) {
                zYSb.append(ziZengAdapter.getData().get(i).getRec_id());
                zYSb.append(":");
                zYSb.append(ziZengAdapter.getData().get(i).getProduct_id() + ",");
            }
        }

        hTSbString = hTSb.toString();
        zYSbString = zYSb.toString();

        Log.e("赠品", "hTSbString==下载数据==" + hTSbString);
        Log.e("赠品", "zYSbString==下载数据==" + zYSbString);

        ImageLoader.image(this, htIconImg, data.getHt_shop_list().getLogo_image());
        haiTitleText.setText(data.getHt_shop_list().getTitle());//海淘标题
        orderHaiExpress.setText(data.getHt_shop_list().getWaybillList().get(0).getName());//海淘快递
        waybill_HT_id = String.valueOf(data.getHt_shop_list().getWaybillList().get(0).getId());//海淘快递ID
        confirmHaiShuifei.setText(data.getTax_total());//税费
        ImageLoader.image(this, zyIconImg, data.getZy_shop_list().getLogo_image());
        ziTitleText.setText(data.getZy_shop_list().getTitle());//自营标题
        orderZiExpress.setText(data.getZy_shop_list().getWaybillList().get(0).getName());//自营快递
        waybill_ZY_id = String.valueOf(data.getZy_shop_list().getWaybillList().get(0).getId());//自营快递ID
        sBuilder = new StringBuilder();
        sBuilder.append("1");
        sBuilder.append(":");
        sBuilder.append(waybill_ZY_id);
        sBuilder.append(",");
        sBuilder.append("2");
        sBuilder.append(":");
        sBuilder.append(waybill_HT_id);

        wayBillId = sBuilder.toString();

        //默认优惠券
        if (data.getBonus_info().getId() != null) {
            bonus_id = data.getBonus_info().getId();
            orderYhqMoney.setText(data.getBonus_info().getCoupon_name());
        } else {
            orderYhqMoney.setText("未选择优惠券");
            bonus_id = "-1";
        }

        Log.e("bonus_id", "bonus_id====" + bonus_id);
        Log.e("getConfirmOrderSuccess", "getAddress_list====" + data.getAddress_list().size());
        //收货地址
        if (data.getAddress_list().size() > 0) {
            rlAddress1.setVisibility(View.VISIBLE);
            rlAddress2.setVisibility(View.GONE);
            confirmAddressName.setText(data.getAddress_list().get(0).getAddress_name());
            confirmAddressPhone.setText(data.getAddress_list().get(0).getMobile());
            confirmAddressAddress.setText(data.getAddress_list().get(0).getAddress_refer() + " " + data.getAddress_list().get(0).getAddress());
            address_id = data.getAddress_list().get(0).getAddress_id();
        } else {
            address_id = "";
            rlAddress1.setVisibility(View.GONE);
            rlAddress2.setVisibility(View.VISIBLE);
        }
        //实名认证
        if (data.getReal_list().size() > 0) {//有实名认证信息
            orderRealname2.setVisibility(View.VISIBLE);
            orderRealname1.setVisibility(View.GONE);
            confirmRealnameName.setText(data.getReal_list().get(0).getName());
            confirmRealnameIdcard.setText(data.getReal_list().get(0).getId_card());
            real_id = data.getReal_list().get(0).getId();

        } else {
            real_id = "";
            orderRealname2.setVisibility(View.GONE);
            orderRealname1.setVisibility(View.VISIBLE);
        }

        Log.e("getAlert_image", "getAlert_image==000==" + data.getAlert_image());
        //活动提示
        if (!data.getAlert_image().equals("") && data.getAlert_image() != null) {
            Log.e("getAlert_image", "getAlert_image==111==" + data.getAlert_image());
            if (popupwindow != null && popupwindow.isShowing()) {
                popupwindow.dismiss();
                return;
            } else {
                initmPopupWindowView(data.getAlert_image());
                popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            }
        }


    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param
     */
    @Override
    public void getConfirmOrderFail(int code, String msg) {


    }

    /**
     * 商品活动提示框
     */
    private void initmPopupWindowView(String getAlert_image) {
        ImageView confirm_tips_img, confirm_tips_clear_btn;
        TextView confirm_tips_add_btn, confirm_tips_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.confirm_order_layout, null, false);
        confirm_tips_img = customView.findViewById(R.id.confirm_tips_img);//图片
        confirm_tips_add_btn = customView.findViewById(R.id.confirm_tips_add_btn);//返回添加
        confirm_tips_btn = customView.findViewById(R.id.confirm_tips_btn);//狠心拒绝
        confirm_tips_clear_btn = customView.findViewById(R.id.confirm_tips_clear_btn);//狠心拒绝

        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
//        customView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (popupwindow != null && popupwindow.isShowing()) {
//                    popupwindow.dismiss();
//                    popupwindow = null;
//                }
//                return false;
//            }
//        });

        Log.e("getAlert_image", "getAlert_image==222==" + getAlert_image);
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        confirm_tips_img.setScaleType(ImageView.ScaleType.MATRIX);
        confirm_tips_img.setImageMatrix(matrix);
        ImageLoader.image(confirm_tips_img.getContext(), confirm_tips_img, getAlert_image);

        //返回添加
        confirm_tips_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                finish();
            }
        });


        //狠心拒绝
        confirm_tips_clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });
    }


    /**
     * 下载规格成功
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShopSuccess(int code, MoveDataBean data) {
        Log.e("getTypeShopSuccess", "下载规格成功");
        if (type.equals("1")) {//自营
            MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
            List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
            MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
            bannerBeans.setType(1);
            bannerBeans.setUrl(listBean.getGoods_thumb());
            banner.add(bannerBeans);
            infoBean.setBanner(banner);
            infoBean.setSearch_attr(listBean.getSearch_attr());
            infoBean.setGoods_id(listBean.getGoods_id());
            infoBean.setProduct_number("0");
            infoBean.setProduct_price(listBean.getProduct_price());
            data.setInfo(infoBean);
            StringBuilder hTSb = new StringBuilder();
            StringBuilder zYSb = new StringBuilder();
            StringBuilder proIdSb = new StringBuilder();

            TypeSelectDialog.with(getActivity(), data, listBean.getSearch_attr(), "2", new TypeSelectDialog.AddShopCarClickListener() {
                @Override
                public void onBtnClickListener(String goods_id, String getRec_id, String product_ids, String nums, String getAttr_name, String mmake) {
                    ziZengAdapter.getData().get(position).setProduct_id(product_ids);
//                    ziZengAdapter.getData().get(position).setRec_id(getRec_id);

                    for (int i = 0; i < haiZengAdapter.getData().size(); i++) {
                        hTSb.append(haiZengAdapter.getData().get(i).getRec_id());
                        hTSb.append(":");
                        hTSb.append(haiZengAdapter.getData().get(i).getProduct_id() + ",");
                    }
                    for (int i = 0; i < ziZengAdapter.getData().size(); i++) {
                        zYSb.append(ziZengAdapter.getData().get(i).getRec_id());
                        zYSb.append(":");
                        zYSb.append(ziZengAdapter.getData().get(i).getProduct_id() + ",");
                    }
                    Log.e("getTypeShopSuccess", "==赠品==product_id=111=" + product_id);
                    Log.e("getTypeShopSuccess", "==赠品==getProduct_id==海淘=111=" + hTSb.toString());
                    Log.e("getTypeShopSuccess", "==赠品==getProduct_id==自营=111=" + zYSb.toString());
                    hTSbString = hTSb.toString();
                    zYSbString = zYSb.toString();
                    Log.e("赠品", "hTSbString==111==" + hTSbString);
                    Log.e("赠品", "zYSbString==111==" + zYSbString);

                    if (confirmOrderBean.getHt_send_list().size() > 0) {
                        proIdSb.append(getRec_id + ":" + product_ids + ",");
                        for (int i = 0; i < confirmOrderBean.getHt_send_list().size(); i++) {
                            if (confirmOrderBean.getHt_send_list().get(i).getType().equals("2")) {
                                proIdSb.append(confirmOrderBean.getHt_send_list().get(i).getRec_id() + ":" + confirmOrderBean.getHt_send_list().get(i).getProduct_id() + ",");
                            }
                        }
                    }
                    if (confirmOrderBean.getZy_send_list().size() > 0) {
                        proIdSb.append(getRec_id + ":" + product_ids + ",");
                        for (int i = 0; i < confirmOrderBean.getZy_send_list().size(); i++) {
                            if (confirmOrderBean.getZy_send_list().get(i).getType().equals("2")) {
                                proIdSb.append(confirmOrderBean.getZy_send_list().get(i).getRec_id() + ":" + confirmOrderBean.getZy_send_list().get(i).getProduct_id() + ",");
                            }
                        }
                    }

                    proIdSbs = proIdSb.toString();

                    Log.e("赠品==拼接ProductId", "==proIdSb==111===" + proIdSb);
                    Log.e("赠品", "hTSbString==请求==" + hTSbString);
                    Log.e("赠品", "zYSbString==请求==" + zYSbString);
                    //请求确认订单接口
//                    presenter.getConfirmTypeData(recId, goods_id, product_id, num, hTSb.toString(), zYSb.toString(), proIdSb.toString());
                    presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);

                    TypeSelectDialog.dismiss();

                }
            }).show();


        } else if (type.equals("2")) {//海淘
            MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
            List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
            MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
            bannerBeans.setType(1);
            bannerBeans.setUrl(listBean2.getGoods_thumb());
            banner.add(bannerBeans);
            infoBean.setBanner(banner);
            infoBean.setSearch_attr(listBean2.getSearch_attr());
            infoBean.setGoods_id(listBean2.getGoods_id());
            infoBean.setProduct_number("0");
            infoBean.setProduct_price(listBean2.getProduct_price());
            data.setInfo(infoBean);
            StringBuilder hTSb = new StringBuilder();
            StringBuilder zYSb = new StringBuilder();
            StringBuilder proIdSb = new StringBuilder();

            TypeSelectDialog.with(getActivity(), data, listBean2.getSearch_attr(), "2", new TypeSelectDialog.AddShopCarClickListener() {
                @Override
                public void onBtnClickListener(String goods_id, String getRec_id, String product_ids, String nums, String getAttr_name, String mmake) {
                    Log.e("赠品", "getRec_id==222==1==" + getRec_id);
                    Log.e("赠品", "getRec_id==222==2==" + getRec_id);
                    haiZengAdapter.getData().get(position).setProduct_id(product_ids);
//                    haiZengAdapter.getData().get(position).setRec_id(getRec_id);


                    for (int i = 0; i < haiZengAdapter.getData().size(); i++) {
                        hTSb.append(haiZengAdapter.getData().get(i).getRec_id());
                        hTSb.append(":");
                        hTSb.append(haiZengAdapter.getData().get(i).getProduct_id() + ",");
                    }
                    for (int i = 0; i < ziZengAdapter.getData().size(); i++) {
                        zYSb.append(ziZengAdapter.getData().get(i).getRec_id());
                        zYSb.append(":");
                        zYSb.append(ziZengAdapter.getData().get(i).getProduct_id() + ",");
                    }

                    Log.e("getTypeShopSuccess", "==赠品==product_id=222=" + product_id);
                    Log.e("getTypeShopSuccess", "==赠品==getProduct_id==海淘=222=" + hTSb.toString());
                    Log.e("getTypeShopSuccess", "==赠品==getProduct_id==自营=222=" + zYSb.toString());

                    hTSbString = hTSb.toString();
                    zYSbString = zYSb.toString();
                    Log.e("赠品", "hTSbString==222==" + hTSbString);
                    Log.e("赠品", "zYSbString==222==" + zYSbString);

                    if (confirmOrderBean.getHt_send_list().size() > 0) {
                        proIdSb.append(getRec_id + ":" + product_ids + ",");
                        for (int i = 0; i < confirmOrderBean.getHt_send_list().size(); i++) {
                            if (confirmOrderBean.getHt_send_list().get(i).getType().equals("2")) {
                                proIdSb.append(confirmOrderBean.getHt_send_list().get(i).getRec_id() + ":" + confirmOrderBean.getHt_send_list().get(i).getProduct_id() + ",");
                            }
                        }
                    }
                    if (confirmOrderBean.getZy_send_list().size() > 0) {
                        proIdSb.append(getRec_id + ":" + product_ids + ",");
                        for (int i = 0; i < confirmOrderBean.getZy_send_list().size(); i++) {
                            if (confirmOrderBean.getZy_send_list().get(i).getType().equals("2")) {
                                proIdSb.append(confirmOrderBean.getZy_send_list().get(i).getRec_id() + ":" + confirmOrderBean.getZy_send_list().get(i).getProduct_id() + ",");
                            }
                        }
                    }
                    proIdSbs = proIdSb.toString();
                    Log.e("赠品==拼接ProductId", "==proIdSb==222===" + proIdSb);
                    //请求确认订单接口
//                    presenter.getConfirmTypeData(recId, goods_id, product_id, num, hTSb.toString(), zYSb.toString(), proIdSb.toString());
                    presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs);
                    TypeSelectDialog.dismiss();

                }
            }).show();

        }


    }

    /**
     * 下载规格失败
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShopFail(int code, String msg) {

    }

    /**
     * 下载可使用优惠券成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getCouponList1Success(int code, List<AlreadyCouponListBean> data) {
        alreadyCouponListBean = data;

    }

    /**
     * 下载可使用优惠券失败
     *
     * @param code
     * @param
     */
    @Override
    public void getCouponList1Fail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
