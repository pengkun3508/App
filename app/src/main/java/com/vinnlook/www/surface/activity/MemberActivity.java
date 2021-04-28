package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.bean.MemberBean;
import com.vinnlook.www.surface.mvp.presenter.MemberPresenter;
import com.vinnlook.www.surface.mvp.view.MemberView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 购买会员
 * @Time:2020/8/18$
 * @Author:pk$
 */
public class MemberActivity extends BaseActivity<MemberPresenter> implements MemberView {

    //    MemberPrice_Adapter memberPriceAdapter;
    @BindView(R.id.recyclerv_2)
    RecyclerView recyclerv2;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.member_time_text)
    TextView memberTimeText;
    @BindView(R.id.iv_check_circle)
    ImageView ivCheckCircle;
    @BindView(R.id.member_check_circle)
    RelativeLayout memberCheckCircle;
    @BindView(R.id.member_pay_btn)
    TextView memberPayBtn;
    @BindView(R.id.member_heart)
    RoundedImageView memberHeart;//头像
    @BindView(R.id.member_user_name)
    TextView memberUserName;//昵称
    @BindView(R.id.member_xieyi_text)
    TextView memberXieyiText;

    String mark = "0";
    String getIs_member;//1--已开通会员；0--未开通会员
    String memberId;//会员卡ID
    String memberPrice;//会员卡价格
    static String channel;//渠道；1==详情页面
    @BindView(R.id.see_member_benefit)
    LinearLayout seeMemberBenefit;


    public static void startSelf(Context context, String channels) {
        Intent intent = new Intent(context, MemberActivity.class);
        context.startActivity(intent);
        channel = channels;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member;
    }

    @Override
    protected MemberPresenter initPresenter() {
        return new MemberPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(MemberActivity.this);
//        //适配器
//        memberPriceAdapter = new MemberPrice_Adapter(getActivity());
//        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 1);
//        manager2.setOrientation(GridLayoutManager.HORIZONTAL);
//        recyclerv2.setLayoutManager(manager2);
//        recyclerv2.setNestedScrollingEnabled(false);
//        recyclerv2.setHasFixedSize(true);
//        recyclerv2.setAdapter(memberPriceAdapter);
//        memberPriceAdapter.addOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view, int position) {//选择会员规格
//                memberPriceAdapter.setDefSelect(position);//设置选中状态
//                if (getIs_member.equals("1")) {//会员
//                    memberPayBtn.setText("立即续费 " + Html.fromHtml("&yen") + memberPriceAdapter.getData().get(position).getPrice());
//                } else {
//                    memberPayBtn.setText("立即支付 " + Html.fromHtml("&yen") + memberPriceAdapter.getData().get(position).getPrice());
//                }
//                memberId = memberPriceAdapter.getData().get(position).getId();
//                memberPrice = memberPriceAdapter.getData().get(position).getPrice();
//
//            }
//        });

//        memberPriceAdapter.setDefSelect(0);//设置默认选中第一项


        String courseName = "《vinnlook会员用户协议》";
        SpannableString courseSpannable = new SpannableString(courseName);
        ClickableSpan courseSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
//                Toast.makeText(MemberActivity.this, "触发点击事件2222!", Toast.LENGTH_SHORT).show();
                WebActivity.startSelf(MemberActivity.this, "http://shop.jealook.com/develop/html/article-info?id=124");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.them));
                ds.setUnderlineText(false);
            }
        };
        courseSpannable.setSpan(courseSpan, 0, courseName.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        memberXieyiText.append("已阅读并同意");
        memberXieyiText.append(courseSpannable);
        memberXieyiText.setMovementMethod(LinkMovementMethod.getInstance());
        memberXieyiText.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明
    }


    @Override
    protected void loadData() {
        presenter.getMemberDetailData();//会员详情
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.member_check_circle, R.id.member_pay_btn, R.id.see_member_benefit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.member_check_circle://选择协议
                if (mark.equals("0")) {
                    mark = "1";//已选择
                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
                } else {
                    mark = "0";//已取消
                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                }
                break;
            case R.id.member_pay_btn://支付
                if (mark.equals("1")) {
                    PayMemberActivity.startSelf(MemberActivity.this, memberId, memberPrice, channel);


                } else {
                    Toast.makeText(this, "请同意会员用户协议", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.see_member_benefit://查看权益
                SeeMemberActivity.startSelf(MemberActivity.this,0);
                break;
        }
    }

    /**
     * @Description:会员详情-成功
     * @Time:2020/8/19 15:36
     * @Author:pk
     */
    @Override
    public void getMemberDetailSuccess(int code, MemberBean data) {
//        memberPriceAdapter.setData(data.getList());
//        memberHeart.setScaleType(ImageView.ScaleType.FIT_XY);
//        ImageLoader.userIcon(this, memberHeart, data.getUsers().getImg_url());
//        memberUserName.setText(data.getUsers().getUser_name());
//        getIs_member = data.getUsers().getIs_member();
//        if (getIs_member.equals("1")) {//会员
//            memberTimeText.setText("会员到期时间：" + data.getUsers().getMember_end_time());
//            memberPayBtn.setText("立即续费 " + Html.fromHtml("&yen") + data.getList().get(0).getPrice());
//        } else {
//            memberTimeText.setText("未开通会员");
//            memberPayBtn.setText("立即支付 " + Html.fromHtml("&yen") + data.getList().get(0).getPrice());
//        }
//
//        memberId = data.getList().get(0).getId();
//        memberPrice = data.getList().get(0).getPrice();

    }

    /**
     * @Description:会员详情-失败
     * @Time:2020/8/19 15:36
     * @Author:pk
     */
    @Override
    public void getMemberDetailFail(int code, String msg) {

    }
}
