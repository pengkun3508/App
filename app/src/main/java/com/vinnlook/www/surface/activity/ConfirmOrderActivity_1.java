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
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;
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
 * @Description:???--????????????
 * @Time:2020/11/26$
 * @Author:pk$
 */


public class ConfirmOrderActivity_1 extends BaseActivity<ConfirmOrderPresenter> implements ConfirmOrderView {
    @BindView(R.id.confirm_address_name)
    TextView confirmAddressName;//???????????????
    @BindView(R.id.confirm_address_phone)
    TextView confirmAddressPhone;//???????????????
    @BindView(R.id.confirm_address_address)
    TextView confirmAddressAddress;//???????????????
    @BindView(R.id.rl_address_1)
    RoundRelativeLayout rlAddress1;//??????--?????????
    @BindView(R.id.rl_address_2)
    RoundRelativeLayout rlAddress2;//??????--?????????
    @BindView(R.id.confirm_realname_name)
    TextView confirmRealnameName;//????????????
    @BindView(R.id.confirm_realname_idcard)
    TextView confirmRealnameIdcard;//??????????????????
    @BindView(R.id.order_realname_2)
    RoundRelativeLayout orderRealname2;//??????--?????????
    @BindView(R.id.order_realname_1)
    RoundRelativeLayout orderRealname1;//??????--?????????
    @BindView(R.id.confirm_recycler_hai)
    RecyclerView confirmRecyclerHai;//??????List
    @BindView(R.id.confirm_message2)
    EditText confirmMessage2;//????????????
    @BindView(R.id.confirm_recycler_zi)
    RecyclerView confirmRecyclerZi;//??????List
    @BindView(R.id.confirm_message1)
    EditText confirmMessage1;//????????????
    @BindView(R.id.confirm_order_fee)
    TextView confirmOrderFee;//??????
    @BindView(R.id.order_yhq_money)
    TextView orderYhqMoney;//?????????Text
    @BindView(R.id.order_conpon_layout)
    RoundRelativeLayout orderConponLayout;//???????????????
    @BindView(R.id.order_yhq_jine_money)
    TextView orderYhqJineMoney;//????????????
    @BindView(R.id.confirm_order_price)
    TextView confirmOrderPrice;//????????????
    @BindView(R.id.confirm_order_toale_price)
    TextView confirmOrderToalePrice;//??????
    @BindView(R.id.tv_ko)
    RoundTextView tvKo;//????????????
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R.id.hai_title_text)
    TextView haiTitleText;//??????Title
    @BindView(R.id.order_hai_express)
    TextView orderHaiExpress;//????????????Text
    @BindView(R.id.order_express_hai_layout)
    RoundRelativeLayout orderExpressHaiLayout;//??????-????????????
    @BindView(R.id.confirm_hai_shuifei)
    TextView confirmHaiShuifei;//????????????
    @BindView(R.id.zi_title_text)
    TextView ziTitleText;//??????-Title
    @BindView(R.id.order_zi_express)
    TextView orderZiExpress;//????????????Text
    @BindView(R.id.order_zi_express_layout)
    RoundRelativeLayout orderZiExpressLayout;//??????-????????????

    @BindView(R.id.hai_tao_all_layout)
    LinearLayout haiTaoAllLayout;//??????????????????
    @BindView(R.id.zi_ying_all_layout)
    LinearLayout ziYingAllLayout;//??????????????????

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
    static String flag;//??????1???????????????????????????2????????????????????????
    static String group_info;//???????????????
    static String group_id;//???????????????
    static String articleId;//??????ID
    String address_id = "";
    String real_id = "";
    String bonus_id = "";//?????????ID
    String waybill_HT_id;//??????ID
    String waybill_ZY_id;//??????ID
    String payPrice;

    ConfirmOrderBean confirmOrderBean;

    ConfirmOrder_1Adapter ziAdapter;
    ConfirmOrder_1ZengAdapter ziZengAdapter;
    ConfirmOrder_2Adapter haiAdapter;
    ConfirmOrder_2ZengAdapter haiZengAdapter;
    public PopupWindow popupwindow;

    RealNameListBean realBean = null;//????????????Bean
    AddressListBean addressBean = null;//??????Bean

    String getSuppliers_id;//1==????????????????????????==???????????????

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
    String wayBillId = "";//??????ID


    public static void startSelf(Context context, String recIds, String goods_ids, String product_ids, String nums, String flags, String group_infos, String group_ids, String articleIds) {
        Intent intent = new Intent(context, ConfirmOrderActivity_1.class);
        context.startActivity(intent);
        recId = recIds;
        goods_id = goods_ids;
        product_id = product_ids;
        num = nums;
        flag = flags;
        group_info = group_infos;
        group_id = group_ids;
        articleId = articleIds;
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

        //???????????????
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        confirmRecyclerZi.setLayoutManager(manager1);
        confirmRecyclerZi.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        confirmRecyclerZi.addItemDecoration(new SpaceItemDecoration(0, 0));
        confirmRecyclerZi.setNestedScrollingEnabled(false);
        confirmRecyclerZi.setHasFixedSize(true);
        ziAdapter = new ConfirmOrder_1Adapter(this);
        confirmRecyclerZi.setAdapter(ziAdapter);

        //??????--??????--?????????
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


        //???????????????
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 1);
        confirmRecyclerHai.setLayoutManager(manager2);
        confirmRecyclerHai.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        confirmRecyclerHai.addItemDecoration(new SpaceItemDecoration(0, 0));
        confirmRecyclerHai.setNestedScrollingEnabled(false);
        confirmRecyclerHai.setHasFixedSize(true);
        haiAdapter = new ConfirmOrder_2Adapter(this);
        confirmRecyclerHai.setAdapter(haiAdapter);


        //??????--??????--?????????
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
                Log.e("??????", "==data==" + data);
                Log.e("??????", "==getSearch_attrs==" + getSearch_attrs);
                Log.e("??????", "==getGoods_id==" + getGoods_id);
                presenter.getTypeShopData(getGoods_id);
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, group_info, group_id);

    }

    @OnClick({R.id.order_realname_2, R.id.order_realname_1, R.id.rl_address_1, R.id.rl_address_2, R.id.pay_member_layout_btn, R.id.order_express_hai_layout, R.id.order_zi_express_layout,
            R.id.order_conpon_layout, R.id.order_conpon_jine_layout, R.id.tv_ko})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_realname_2://?????????--????????????
                RealNameActivity.startSelf(this, "0");
                break;
            case R.id.order_realname_1:// ?????????  --????????????
                RealNameActivity.startSelf(this, "0");
                break;
            case R.id.rl_address_1://?????????  --??????
                AddressActivity.startSelf(this, "0");
                break;
            case R.id.rl_address_2://?????????  --??????
                AddressActivity.startSelf(this, "0");
                break;

            case R.id.pay_member_layout_btn://????????????
                MemberActivity_1.startSelf(getContext(), "3");//??????????????????  1---??????????????????2--???????????????????????????????????????3---??????????????????
                break;

            case R.id.order_express_hai_layout://????????????????????????
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView2();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;
            case R.id.order_zi_express_layout://????????????????????????
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {

                    initmPopupWindowView1();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;
            case R.id.order_conpon_layout://???????????????
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {

                    initmPopupWindowView5();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }


                break;
            case R.id.order_conpon_jine_layout://??????????????????
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {

                    initmPopupWindowView3();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;

            case R.id.tv_ko://????????????
                String type = "";
                Log.e("????????????", "==recId==" + recId);
                Log.e("????????????", "==goods_id==" + goods_id);
                Log.e("????????????", "==product_id==" + product_id);
                Log.e("????????????", "==num==" + num);
                Log.e("????????????", "==real_id==" + real_id);
                Log.e("????????????", "==address_id==" + address_id);
                Log.e("????????????", "==type==" + type);
                Log.e("????????????", "==confirmMessage1.getText().toString()==" + confirmMessage1.getText().toString());
                Log.e("????????????", "==confirmMessage2.getText().toString()==" + confirmMessage2.getText().toString());
                Log.e("????????????", "==payPrice==" + payPrice);
                Log.e("????????????", "==bonus_id==" + bonus_id);
                Log.e("????????????", "==sB.toString()==" + sBuilder.toString());
                Log.e("????????????", "==zYSbString==" + zYSbString);
                Log.e("????????????", "== hTSbString==" + hTSbString);
                Log.e("????????????", "== group_info==" + group_info);
                Log.e("????????????", "== group_id==" + group_id);

                StringBuilder sb = new StringBuilder();
                StringBuilder sb1 = new StringBuilder();


                if (haiZengAdapter.getData().size() > 0) {
                    listString = new ArrayList<>();
                    for (int i = 0; i < haiZengAdapter.getData().size(); i++) {
                        if (haiZengAdapter.getData().get(i).getType().equals("1")) {

                            Toast.makeText(this, "???????????????" + haiZengAdapter.getData().get(i).getGoods_name() + "???,???????????????", Toast.LENGTH_SHORT).show();
                            sb.append(i);

                        } else {
                            listString.add(String.valueOf(i));
                        }
                    }
                    Log.e("????????????", "==???==listString==111==" + listString.size());
                    Log.e("????????????", "==???==size()==111==" + haiZengAdapter.getData().size());
                    if (listString.size() == haiZengAdapter.getData().size()) {
                        if (ziZengAdapter.getData().size() > 0) {
                            listString1 = new ArrayList<>();
                            for (int i = 0; i < ziZengAdapter.getData().size(); i++) {
                                if (ziZengAdapter.getData().get(i).getType().equals("1")) {
                                    Toast.makeText(this, "???????????????" + ziZengAdapter.getData().get(i).getGoods_name() + "???,???????????????", Toast.LENGTH_SHORT).show();
                                    sb1.append(i);

                                } else {
                                    listString1.add(String.valueOf(i));
                                }
                            }
                            Log.e("????????????", "==???==listString1()==111==" + listString1.size());
                            Log.e("????????????", "==???==size()==111==" + ziZengAdapter.getData().size());
                            if (listString1.size() == ziZengAdapter.getData().size()) {
                                if (address_id.equals("")) {
                                    Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (getSuppliers_id.equals("")) {//??????????????????
                                        PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                                                confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString, group_info, group_id,articleId);
                                    } else if (getSuppliers_id.equals("1")) {//???????????????
                                        if (real_id.equals("")) {
                                            Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                            } else {
                                if (getSuppliers_id.equals("")) {//??????????????????
                                    PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                                            confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString, group_info, group_id,articleId);
                                } else if (getSuppliers_id.equals("1")) {//???????????????
                                    if (real_id.equals("")) {
                                        Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(this, "???????????????" + ziZengAdapter.getData().get(i).getGoods_name() + "???,???????????????", Toast.LENGTH_SHORT).show();
                            sb1.append(i);

                        } else {
                            listString1.add(String.valueOf(i));
                        }
                    }
                    Log.e("????????????", "==???==listString1()==222==" + listString1.size());
                    Log.e("????????????", "==???==size()==222==" + ziZengAdapter.getData().size());
                    if (listString1.size() == ziZengAdapter.getData().size()) {
                        if (address_id.equals("")) {
                            Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                        } else {
                            if (getSuppliers_id.equals("")) {//??????????????????
                                PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                                        confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString, group_info, group_id, articleId);
                            } else if (getSuppliers_id.equals("1")) {//???????????????
                                if (real_id.equals("")) {
                                    Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    } else {
                        if (getSuppliers_id.equals("")) {//??????????????????
                            PayOrderActivity.startSelf(this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                                    confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString, group_info, group_id, articleId);
                        } else if (getSuppliers_id.equals("1")) {//???????????????
                            if (real_id.equals("")) {
                                Toast.makeText(this, "??????????????????????????????", Toast.LENGTH_SHORT).show();
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
     * ????????????--??????????????????
     */
    private void initmPopupWindowView4() {
        TextView return_update_btn, sure_btn;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.tips_haitao_layout, null, false);
        return_update_btn = customView.findViewById(R.id.return_update_btn);
        sure_btn = customView.findViewById(R.id.sure_btn);
        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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
        //??????
        return_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
            }
        });
        //??????
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayOrderActivity.startSelf(ConfirmOrderActivity_1.this, recId, goods_id, product_id, num, real_id, address_id, type, confirmMessage1.getText().toString(),
                        confirmMessage2.getText().toString(), "", payPrice, bonus_id, sBuilder.toString(), zYSbString, hTSbString, group_info, group_id,articleId);
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });

    }


    /**
     * ???????????????????????????
     */
    private void initmPopupWindowView5() {
        ImageView conpon_list_clear;
        RecyclerView conpon_list_recycler;
        TextView clear_conpon_btn;

        Log.e("", "");
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.conpon_yes_list_layout, null, false);
        conpon_list_clear = customView.findViewById(R.id.conpon_list_clear);//??????
        conpon_list_recycler = customView.findViewById(R.id.conpon_list_recycler);//list
        clear_conpon_btn = customView.findViewById(R.id.clear_conpon_btn);//?????????????????????
        conpon_list_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        yesAdapter = new ConponListYseAdapter(getActivity());
        conpon_list_recycler.setAdapter(yesAdapter);
        yesAdapter.setData(alreadyCouponListBean);
        yesAdapter.setId(bonus_id);
        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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

        //???????????????
        yesAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                presenter.getConfirmOrderData1(recId, goods_id, product_id, num, yesAdapter.getData().get(position).getId(), address_id);
                presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, yesAdapter.getData().get(position).getId(), hTSbString, zYSbString, proIdSbs, "", "");
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }

                bonus_id = yesAdapter.getData().get(position).getId();

            }
        });
        //???????????????????????????--?????????
        clear_conpon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                presenter.getConfirmOrderData1(recId, goods_id, product_id, num, "-1", address_id);//???-1?????????????????????
                presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, "-1", hTSbString, zYSbString, proIdSbs, "", "");
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                bonus_id = "-1";
            }
        });


    }


    /**
     * ????????????????????????
     */
    private void initmPopupWindowView3() {
        ImageView conpon_clear;
        RecyclerView conpon_recycler;
        ConponList_1Adapter adapter1;
        Log.e("", "");
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.conpon_list_layout, null, false);
        conpon_clear = customView.findViewById(R.id.conpon_clear);//??????
        conpon_recycler = customView.findViewById(R.id.conpon_recycler);//list
        conpon_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter1 = new ConponList_1Adapter(getActivity());
        conpon_recycler.setAdapter(adapter1);
        adapter1.setData(confirmOrderBean.getDiscountPriceList());//??????
        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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
        //??????
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
     * ??????????????????
     */
    private void initmPopupWindowView1() {
        ImageView express_clear;
        RecyclerView express_recycler;
        ExpressList_1Adapter adapter1;
        Log.e("", "");
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.express_list_layout, null, false);
        express_clear = customView.findViewById(R.id.express_clear);//??????
        express_recycler = customView.findViewById(R.id.express_recycler);//list
        express_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter1 = new ExpressList_1Adapter(getActivity());
        express_recycler.setAdapter(adapter1);
        adapter1.setData(confirmOrderBean.getZy_shop_list().getWaybillList());//??????
        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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
        //??????
        express_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });
        //??????????????????
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
                Log.e("???????????????", "??????ID===" + sBuilder.toString());
//                presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id);
                presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, "", "");
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
     * ??????????????????
     */
    private void initmPopupWindowView2() {
        ImageView express_clear;
        RecyclerView express_recycler;
        ExpressList_2Adapter adapter1;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.express_list_layout, null, false);
        express_clear = customView.findViewById(R.id.express_clear);//??????
        express_recycler = customView.findViewById(R.id.express_recycler);//list
        express_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter1 = new ExpressList_2Adapter(getActivity());
        express_recycler.setAdapter(adapter1);
        adapter1.setData(confirmOrderBean.getHt_shop_list().getWaybillList());//??????
        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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
        //??????
        express_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });

        //??????????????????
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
                Log.e("???????????????", "??????ID===" + sBuilder.toString());
//                presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id);
                presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, "", "");
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

    //????????????--???????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Address_KeyDown event) {
        Log.e("Address_KeyDown", "==???????????????==");
//        presenter.getConfirmOrderData(recId, goods_id, product_id, num, "", "", "");
        presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, "", "");
    }


    //????????????--?????????--??????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CancelYouHuiQuanEvent event) {
        Log.e("", "==??????????????????==");
        bonus_id = event.getMark();
//        presenter.getConfirmOrderData1(recId, goods_id, product_id, num, bonus_id, address_id);
        presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, "", "");

    }

    //????????????--??????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(RealName_2Event event) {
        Log.e("????????????", "==??????????????????==" + event.getRealNameBean());
        orderRealname2.setVisibility(View.VISIBLE);
        orderRealname1.setVisibility(View.GONE);
        realBean = event.getRealNameBean();
        confirmRealnameName.setText(realBean.getName());
        confirmRealnameIdcard.setText(realBean.getId_card());
        real_id = realBean.getId();
    }

    //????????????--??????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddressListBean_2Event event) {
        Log.e("????????????", "==??????????????????==" + event.getAddressListBean());
        address_id = event.getAddressListBean().getAddress_id();
//        presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id);
        presenter.getConfirmOrderData(recId, goods_id, product_id, num, sBuilder.toString(), "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, "", "");
    }


    //???????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PayMemberEvent event) {
        Log.e("??????????????????", "==???????????????==");
        if (event.getFlag()) {
            presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, "", "");
        }
    }

    //????????????--?????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CouponEvent event) {
        Log.e("CouponEvent", "==?????????????????????==" + event.getData());
        confirmOrderBean = event.getData();
        confirmOrderPrice.setText(Html.fromHtml("&yen") + "" + event.getData().getGoods_price());
        payPrice = event.getData().getPrice();
        confirmOrderToalePrice.setText(event.getData().getPrice());
        confirmOrderFee.setText(Html.fromHtml("&yen") + event.getData().getPost_fee());
        orderYhqJineMoney.setText("-" + Html.fromHtml("&yen") + event.getData().getCoupon_price());

        confrimMemberReduceText.setText(Html.fromHtml("&yen") + event.getData().getMember_order_price());
        confrimMemberPriceText.setText("Plus?????????????????????" + Html.fromHtml("&yen") + event.getData().getMember_price() + "???");
        if (event.getData().getIs_member()) {//?????????
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
        //????????????
        haiZengAdapter.setData(event.getData().getHt_send_list());
        //????????????
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

        Log.e("??????", "hTSbString==333==" + hTSbString);
        Log.e("??????", "zYSbString==333==" + zYSbString);

        ImageLoader.image(this, htIconImg, event.getData().getHt_shop_list().getLogo_image());
        haiTitleText.setText(event.getData().getHt_shop_list().getTitle());//????????????
        orderHaiExpress.setText(event.getData().getHt_shop_list().getWaybillList().get(0).getName());//????????????
        waybill_HT_id = String.valueOf(event.getData().getHt_shop_list().getWaybillList().get(0).getId());//????????????ID
        confirmHaiShuifei.setText(Html.fromHtml("&yen") + event.getData().getTax_total());//??????
        ImageLoader.image(this, zyIconImg, event.getData().getZy_shop_list().getLogo_image());
        ziTitleText.setText(event.getData().getZy_shop_list().getTitle());//????????????
        orderZiExpress.setText(event.getData().getZy_shop_list().getWaybillList().get(0).getName());//????????????
        waybill_ZY_id = String.valueOf(event.getData().getZy_shop_list().getWaybillList().get(0).getId());//????????????ID
        sBuilder = new StringBuilder();
        sBuilder.append("1");
        sBuilder.append(":");
        sBuilder.append(waybill_ZY_id);
        sBuilder.append(",");
        sBuilder.append("2");
        sBuilder.append(":");
        sBuilder.append(waybill_HT_id);

        wayBillId = sBuilder.toString();

        //???????????????
        if (event.getData().getBonus_info().getId() != null) {
            bonus_id = event.getData().getBonus_info().getId();
            orderYhqMoney.setText(event.getData().getBonus_info().getCoupon_name());
        } else {
            orderYhqMoney.setText("??????????????????");
            bonus_id = "-1";
        }

        //????????????
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
        //????????????
        if (event.getData().getReal_list().size() > 0) {//?????????????????????
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
     * ??????????????????
     *
     * @param code
     * @param data
     */
    @Override
    public void getConfirmOrderSuccess(int code, ConfirmOrderBean data) {
        presenter.getCouponListData1(recId, goods_id, product_id, num, group_info);//?????????????????????
        confirmOrderBean = data;
        confirmOrderPrice.setText(Html.fromHtml("&yen") + "" + data.getGoods_price());
        payPrice = data.getPrice();
        confirmOrderToalePrice.setText(data.getPrice());
        confirmOrderFee.setText(Html.fromHtml("&yen") + data.getPost_fee());
        orderYhqJineMoney.setText("-" + Html.fromHtml("&yen") + data.getCoupon_price());


        confrimMemberReduceText.setText(Html.fromHtml("&yen") + data.getMember_order_price());
        confrimMemberPriceText.setText("Plus?????????????????????" + Html.fromHtml("&yen") + data.getMember_price() + "???");
        if (data.getIs_member()) {//?????????
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
        //????????????
        haiZengAdapter.setData(data.getHt_send_list());
        //????????????
        ziZengAdapter.setData(data.getZy_send_list());

        StringBuilder hTSb = new StringBuilder();
        StringBuilder zYSb = new StringBuilder();
        if (data.getHt_send_list().size() > 0) {
            for (int i = 0; i < data.getHt_send_list().size(); i++) {
                Log.e("??????", "hTSbString==??????==" + haiZengAdapter.getData().get(i).getRec_id());
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

        Log.e("??????", "hTSbString==????????????==" + hTSbString);
        Log.e("??????", "zYSbString==????????????==" + zYSbString);

        ImageLoader.image(this, htIconImg, data.getHt_shop_list().getLogo_image());
        haiTitleText.setText(data.getHt_shop_list().getTitle());//????????????
        orderHaiExpress.setText(data.getHt_shop_list().getWaybillList().get(0).getName());//????????????
        waybill_HT_id = String.valueOf(data.getHt_shop_list().getWaybillList().get(0).getId());//????????????ID
        confirmHaiShuifei.setText(Html.fromHtml("&yen") + data.getTax_total());//??????
        ImageLoader.image(this, zyIconImg, data.getZy_shop_list().getLogo_image());
        ziTitleText.setText(data.getZy_shop_list().getTitle());//????????????
        orderZiExpress.setText(data.getZy_shop_list().getWaybillList().get(0).getName());//????????????
        waybill_ZY_id = String.valueOf(data.getZy_shop_list().getWaybillList().get(0).getId());//????????????ID
        sBuilder = new StringBuilder();
        sBuilder.append("1");
        sBuilder.append(":");
        sBuilder.append(waybill_ZY_id);
        sBuilder.append(",");
        sBuilder.append("2");
        sBuilder.append(":");
        sBuilder.append(waybill_HT_id);

        wayBillId = sBuilder.toString();

        //???????????????
        if (data.getBonus_info().getId() != null) {
            bonus_id = data.getBonus_info().getId();
            orderYhqMoney.setText(data.getBonus_info().getCoupon_name());
        } else {
            orderYhqMoney.setText("??????????????????");
            bonus_id = "-1";
        }

        Log.e("bonus_id", "bonus_id====" + bonus_id);
        Log.e("getConfirmOrderSuccess", "getAddress_list====" + data.getAddress_list().size());
        //????????????
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
        //????????????
        if (data.getReal_list().size() > 0) {//?????????????????????
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
        //????????????
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
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getConfirmOrderFail(int code, String msg) {


    }

    /**
     * ?????????????????????
     */
    private void initmPopupWindowView(String getAlert_image) {
        ImageView confirm_tips_img, confirm_tips_clear_btn;
        TextView confirm_tips_add_btn, confirm_tips_btn;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.confirm_order_layout, null, false);
        confirm_tips_img = customView.findViewById(R.id.confirm_tips_img);//??????
        confirm_tips_add_btn = customView.findViewById(R.id.confirm_tips_add_btn);//????????????
        confirm_tips_btn = customView.findViewById(R.id.confirm_tips_btn);//????????????
        confirm_tips_clear_btn = customView.findViewById(R.id.confirm_tips_clear_btn);//????????????

        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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
        Matrix matrix = new Matrix();           //????????????????????????
        matrix.setTranslate(0, 0);          //??????x???y???100??????
        matrix.preRotate(0);                   //???????????????30???
        confirm_tips_img.setScaleType(ImageView.ScaleType.MATRIX);
        confirm_tips_img.setImageMatrix(matrix);
        ImageLoader.image(confirm_tips_img.getContext(), confirm_tips_img, getAlert_image);

        //????????????
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


        //????????????
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
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShopSuccess(int code, MoveDataBean data) {
        Log.e("getTypeShopSuccess", "??????????????????");
        if (type.equals("1")) {//??????

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
                public void onBtnClickListener(String goods_id, String getRec_id, String product_ids, String nums, String getAttr_name, ProductBean productBean, String mmake) {
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
                    Log.e("getTypeShopSuccess", "==??????==product_id=111=" + product_id);
                    Log.e("getTypeShopSuccess", "==??????==getProduct_id==??????=111=" + hTSb.toString());
                    Log.e("getTypeShopSuccess", "==??????==getProduct_id==??????=111=" + zYSb.toString());
                    hTSbString = hTSb.toString();
                    zYSbString = zYSb.toString();
                    Log.e("??????", "hTSbString==111==" + hTSbString);
                    Log.e("??????", "zYSbString==111==" + zYSbString);

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

                    Log.e("??????==??????ProductId", "==proIdSb==111===" + proIdSb);
                    Log.e("??????", "hTSbString==??????==" + hTSbString);
                    Log.e("??????", "zYSbString==??????==" + zYSbString);
                    //????????????????????????
//                    presenter.getConfirmTypeData(recId, goods_id, product_id, num, hTSb.toString(), zYSb.toString(), proIdSb.toString());
                    presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, "", "");

                    TypeSelectDialog.dismiss();

                }
            }).show();


        } else if (type.equals("2")) {//??????
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
                public void onBtnClickListener(String goods_id, String getRec_id, String product_ids, String nums, String getAttr_name, ProductBean productBean, String mmake) {
                    Log.e("??????", "getRec_id==222==1==" + getRec_id);
                    Log.e("??????", "getRec_id==222==2==" + getRec_id);
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

                    Log.e("getTypeShopSuccess", "==??????==product_id=222=" + product_id);
                    Log.e("getTypeShopSuccess", "==??????==getProduct_id==??????=222=" + hTSb.toString());
                    Log.e("getTypeShopSuccess", "==??????==getProduct_id==??????=222=" + zYSb.toString());

                    hTSbString = hTSb.toString();
                    zYSbString = zYSb.toString();
                    Log.e("??????", "hTSbString==222==" + hTSbString);
                    Log.e("??????", "zYSbString==222==" + zYSbString);

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
                    Log.e("??????==??????ProductId", "==proIdSb==222===" + proIdSb);
                    //????????????????????????
//                    presenter.getConfirmTypeData(recId, goods_id, product_id, num, hTSb.toString(), zYSb.toString(), proIdSb.toString());
                    presenter.getConfirmOrderData(recId, goods_id, product_id, num, wayBillId, "", address_id, bonus_id, hTSbString, zYSbString, proIdSbs, "", "");
                    TypeSelectDialog.dismiss();

                }
            }).show();

        }


    }

    /**
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShopFail(int code, String msg) {

    }

    /**
     * ??????????????????????????????
     *
     * @param code
     * @param data
     */
    @Override
    public void getCouponList1Success(int code, List<AlreadyCouponListBean> data) {
        Log.e("??????????????????????????????", "==data===" + data);
        alreadyCouponListBean = data;

    }

    /**
     * ??????????????????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getCouponList1Fail(int code, String msg) {
        Log.e("??????????????????????????????", "==code===" + code);
        Log.e("??????????????????????????????", "==msg===" + msg);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
