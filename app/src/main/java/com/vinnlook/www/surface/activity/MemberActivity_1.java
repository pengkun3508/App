package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.surface.adapter.MemeberPrice_Adapter_1;
import com.vinnlook.www.surface.adapter.MemeberPrice_Adapter_2;
import com.vinnlook.www.surface.bean.MemberBean;
import com.vinnlook.www.surface.mvp.presenter.MemberPresenter;
import com.vinnlook.www.surface.mvp.view.MemberView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:会员中心
 * @Time:2020/10/22$
 * @Author:pk$
 */
public class MemberActivity_1 extends BaseActivity<MemberPresenter> implements MemberView {


    @BindView(R.id.memeber_heart)
    RoundedImageView memeberHeart;//头像
    @BindView(R.id.memeber_user_name)
    TextView memeberUserName;//姓名
    @BindView(R.id.memeber_pay_btn)
    TextView memeberPayBtn;//开通会员按钮
    @BindView(R.id.memeber_recycler_1)
    RecyclerView memeberRecycler1;//优惠券List
    @BindView(R.id.memeber_see_benefit)
    LinearLayout memeberSeeBenefit;//查看会员权益
    @BindView(R.id.memeber_recycler_2)
    RecyclerView memeberRecycler2;//热门推荐
    @BindView(R.id.memeber_not_layout)
    LinearLayout memeberNotLayout;//未开通会员布局
    @BindView(R.id.memeber_sheng_price_text)
    TextView memeberShengPriceText;//节省金额
    @BindView(R.id.memeber_see_order_text)
    TextView memeberSeeOrderText;//查看节省订单
    @BindView(R.id.memeber_xufei_pay_btn)
    TextView memeberXufeiPayBtn;//续费会员按钮
    @BindView(R.id.memeber_yes_layout)
    LinearLayout memeberYesLayout;//开通会员布局
    @BindView(R.id.memeber_xu_kai_text)
    TextView memeberXuKaiText;
    @BindView(R.id.memeber_huiyuan_biaozhi1)
    ImageView memeberHuiyuanBiaozhi1;//会员皇冠
    @BindView(R.id.memeber_huiyuan_biaozhi2)
    LinearLayout memeberHuiyuanBiaozhi2;//黑卡会员标志
    @BindView(R.id.memeber_huiyuan_time)
    TextView memeberHuiyuanTime;//到期时间
    @BindView(R.id.memeber_see_quanbu_shop)
    LinearLayout memeberSeeQuanbuShop;//查看全部商品

    MemeberPrice_Adapter_1 memeberPriceAdapter1;
    MemeberPrice_Adapter_2 memeberPriceAdapter2;

    String memberId;
    String memberPrice;
    static String channel;//渠道；1==详情页面
    @BindView(R.id.memeber_zhe_layou_btn)
    LinearLayout memeberZheLayouBtn;
    @BindView(R.id.memeber_fen_layou_btn)
    LinearLayout memeberFenLayouBtn;
    @BindView(R.id.memeber_tui_layou_btn)
    LinearLayout memeberTuiLayouBtn;
    @BindView(R.id.memeber_ke_layou_btn)
    LinearLayout memeberKeLayouBtn;
    @BindView(R.id.memeber_you_layou_btn)
    LinearLayout memeberYouLayouBtn;
    @BindView(R.id.memeber_quan_layou_btn)
    LinearLayout memeberQuanLayouBtn;
    @BindView(R.id.memeber_fa_layou_btn)
    LinearLayout memeberFaLayouBtn;
    @BindView(R.id.memeber_gou_layou_btn)
    LinearLayout memeberGouLayouBtn;
    @BindView(R.id.memeber_agreement_text)
    TextView memeberAgreementText;
    @BindView(R.id.memeber_agreement_layout)
    LinearLayout memeberAgreementLayout;
    @BindView(R.id.car_item_check_img)
    ImageView carItemCheckImg;
    @BindView(R.id.memeber_agreement_text_2)
    TextView memeberAgreementText2;
    @BindView(R.id.memeber_agreement_layout_2)
    LinearLayout memeberAgreementLayout2;
    @BindView(R.id.car_item_check_img_2)
    ImageView carItemCheckImg2;

    boolean flag = false;
    boolean flag2 = false;

    public PopupWindow popupwindow;
    String getIs_member;


    public static void startSelf(Context context, String channels) {
        Intent intent = new Intent(context, MemberActivity_1.class);
        context.startActivity(intent);
        channel = channels;

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_1;
    }

    @Override
    protected MemberPresenter initPresenter() {
        return new MemberPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);                        //动画执行时间
        scaleAnimation.setRepeatCount(-1);                    //-1表示重复执行动画
        scaleAnimation.setRepeatMode(Animation.REVERSE);    //重复 缩小和放大效果
        memeberPayBtn.startAnimation(scaleAnimation);

        ScaleAnimation scaleAnimation1 = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation1.setDuration(2000);                        //动画执行时间
        scaleAnimation1.setRepeatCount(-1);                    //-1表示重复执行动画
        scaleAnimation1.setRepeatMode(Animation.REVERSE);    //重复 缩小和放大效果
        memeberXufeiPayBtn.startAnimation(scaleAnimation1);


        //适配器--优惠券
        memeberPriceAdapter1 = new MemeberPrice_Adapter_1(getActivity());
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
        memeberRecycler1.setLayoutManager(manager1);
        memeberRecycler1.setNestedScrollingEnabled(false);
        memeberRecycler1.setHasFixedSize(true);
        memeberRecycler1.setAdapter(memeberPriceAdapter1);

        //适配器--热门
        memeberPriceAdapter2 = new MemeberPrice_Adapter_2(getActivity());
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 3);
//        manager2.setOrientation(GridLayoutManager.HORIZONTAL);
        memeberRecycler2.setLayoutManager(manager2);
        memeberRecycler2.setNestedScrollingEnabled(false);
        memeberRecycler2.setHasFixedSize(true);
        memeberRecycler2.setAdapter(memeberPriceAdapter2);

        memeberPriceAdapter2.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(MemberActivity_1.this, memeberPriceAdapter2.getData().get(position).getGoods_id(), memeberPriceAdapter2.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(MemberActivity_1.this, memeberPriceAdapter2.getData().get(position).getGoods_id(), memeberPriceAdapter2.getData().get(position).getSearch_attr());

            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getMemberDetailData();//会员详情
    }


    @Override
    public void getMemberDetailSuccess(int code, MemberBean data) {

        MemberBean datas = data;


        if (data.getUsers().getImg_url() != null && !data.getUsers().getImg_url().equals("")) {
            memeberHeart.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.image(this, memeberHeart, data.getUsers().getImg_url());
        }
        memeberUserName.setText(data.getUsers().getUser_name());
        memeberPriceAdapter1.setData(data.getDiscount());
        memeberPriceAdapter2.setData(data.getShop());


        memberId = data.getInfo().getId();
        memberPrice = data.getInfo().getPrice();


        memeberShengPriceText.setText(data.getSpare_price());//节省金额
        memeberHuiyuanTime.setText(data.getUsers().getMember_end_time() + "到期，可续费尊享会员");

        getIs_member = data.getUsers().getIs_member();//1==已开通会员；0==未开通会员

        //已开通会员
        if (data.getUsers().getIs_member().equals("1")) {
            memeberYesLayout.setVisibility(View.VISIBLE);
            memeberNotLayout.setVisibility(View.GONE);
            memeberXufeiPayBtn.setText(data.getInfo().getPrice() + "元/年  立即续卡");
            memeberXuKaiText.setText("续卡领取优惠券");

            memeberHuiyuanBiaozhi1.setVisibility(View.VISIBLE);
            memeberHuiyuanBiaozhi2.setVisibility(View.VISIBLE);
            memeberHuiyuanTime.setVisibility(View.VISIBLE);

        } else if (data.getUsers().getIs_member().equals("0")) {//未开通
            memeberYesLayout.setVisibility(View.GONE);
            memeberNotLayout.setVisibility(View.VISIBLE);
            memeberPayBtn.setText(data.getInfo().getPrice() + "元/年  立即开通");
            memeberXuKaiText.setText("开卡领取优惠券");

            memeberHuiyuanBiaozhi1.setVisibility(View.GONE);
            memeberHuiyuanBiaozhi2.setVisibility(View.GONE);
            memeberHuiyuanTime.setVisibility(View.GONE);
        }


    }

    @Override
    public void getMemberDetailFail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.memeber_see_benefit, R.id.memeber_pay_btn, R.id.memeber_see_order_text, R.id.memeber_xufei_pay_btn, R.id.memeber_see_quanbu_shop,
            R.id.memeber_zhe_layou_btn, R.id.memeber_fen_layou_btn, R.id.memeber_tui_layou_btn, R.id.memeber_ke_layou_btn,
            R.id.memeber_gou_layou_btn, R.id.memeber_you_layou_btn, R.id.memeber_quan_layou_btn, R.id.memeber_fa_layou_btn, R.id.memeber_agreement_layout, R.id.memeber_agreement_text,
            R.id.memeber_agreement_layout_2, R.id.memeber_agreement_text_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.memeber_pay_btn://开通会员
            case R.id.memeber_xufei_pay_btn://续费会员
                if (getIs_member.equals("1")) {//已开通会员
                    if (flag2) {
                        PayMemberActivity.startSelf(MemberActivity_1.this, memberId, memberPrice, channel);//channel==会员购买入口  1---详情页面，，2--其他页面进入会员购买页面，3---确认订单页面
                    } else {
                        Toast.makeText(this, "请同意《vinnlook付费会员服务协议》后再进行购买", Toast.LENGTH_SHORT).show();
                    }
                } else if (getIs_member.equals("0")) {//未开通会员
                    if (flag) {
                        PayMemberActivity.startSelf(MemberActivity_1.this, memberId, memberPrice, channel);//channel==会员购买入口  1---详情页面，，2--其他页面进入会员购买页面，3---确认订单页面
                    } else {
                        Toast.makeText(this, "请同意《vinnlook付费会员服务协议》后再进行购买", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.memeber_see_order_text://查看节省订单
                SavingOrdersActivity.startSelf(MemberActivity_1.this);
                break;
            case R.id.memeber_see_quanbu_shop://查看全部商品
                new MainHomeActivityEvent("4").post();
                finish();
                break;
            case R.id.memeber_see_benefit://查看会员权益
            case R.id.memeber_zhe_layou_btn://全场9折扣
                SeeMemberActivity.startSelf(MemberActivity_1.this, 0);
                break;
            case R.id.memeber_fen_layou_btn://专属积分
                SeeMemberActivity.startSelf(MemberActivity_1.this, 1);
                break;
            case R.id.memeber_tui_layou_btn://极速退款
                SeeMemberActivity.startSelf(MemberActivity_1.this, 2);
                break;
            case R.id.memeber_ke_layou_btn://专属客服
                SeeMemberActivity.startSelf(MemberActivity_1.this, 3);
                break;
            case R.id.memeber_gou_layou_btn://会员购物节
                SeeMemberActivity.startSelf(MemberActivity_1.this, 4);
                break;
            case R.id.memeber_you_layou_btn://一件包邮
                SeeMemberActivity.startSelf(MemberActivity_1.this, 5);
                break;
            case R.id.memeber_quan_layou_btn://神秘礼券
                SeeMemberActivity.startSelf(MemberActivity_1.this, 6);
                break;
            case R.id.memeber_fa_layou_btn://优先发货
                SeeMemberActivity.startSelf(MemberActivity_1.this, 7);
                break;
            case R.id.memeber_agreement_layout://是否同意
                if (flag) {
                    flag = false;
                    carItemCheckImg.setBackgroundResource(R.mipmap.shop_cat_icon_2);
                } else {
                    flag = true;
                    carItemCheckImg.setBackgroundResource(R.mipmap.shop_cat_icon_1);
                }
                break;
            case R.id.memeber_agreement_text://查看会员协议
            case R.id.memeber_agreement_text_2://查看会员协议
//                if (popupwindow != null && popupwindow.isShowing()) {
//                    popupwindow.dismiss();
//                    return;
//                } else {
//                    initmPopupWindowView2();
//                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//                }

                MemberAgreementActivity.startSelf(MemberActivity_1.this);

                break;

            case R.id.memeber_agreement_layout_2://会员状态下是否同意
                if (flag2) {
                    flag2 = false;
                    carItemCheckImg2.setBackgroundResource(R.mipmap.shop_cat_icon_2);
                } else {
                    flag2 = true;
                    carItemCheckImg2.setBackgroundResource(R.mipmap.shop_cat_icon_1);
                }
                break;


        }
    }


    /**
     * 《vinnlook付费会员服务协议》
     */
    private void initmPopupWindowView2() {
        WebView jifen_guize_web;
        LinearLayout guize_que_btn;
        TextView jifen_title;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.jifen_guize_layout, null, false);
        jifen_title = customView.findViewById(R.id.jifen_title);
        jifen_guize_web = customView.findViewById(R.id.jifen_guize_web);//取消
        guize_que_btn = customView.findViewById(R.id.guize_que_btn);
        jifen_title.setText("Vinnlook会员协议");
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        //popupwindow.setAnimationStyle(R.style.AnimationFade);

        WebSettings webSettings = jifen_guize_web.getSettings();
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");

        jifen_guize_web.setVerticalScrollBarEnabled(false); //垂直不显示
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
        guize_que_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });


        jifen_guize_web.loadUrl("https://shop.jealook.com/v4/html/article-info?id=157");


    }


}
