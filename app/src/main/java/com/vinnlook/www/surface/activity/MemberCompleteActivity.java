package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.PayMemberEvent;
import com.vinnlook.www.surface.bean.MemberBean;
import com.vinnlook.www.surface.mvp.presenter.MemberCompletePresenter;
import com.vinnlook.www.surface.mvp.view.MemberCompleteView;
import com.vinnlook.www.utils.CacheActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:会员购买成功--结果
 * @Time:2020/8/19$
 * @Author:pk$
 */
public class MemberCompleteActivity extends BaseActivity<MemberCompletePresenter> implements MemberCompleteView {

    @BindView(R.id.member_time_expire)
    TextView memberTimeExpire;
    @BindView(R.id.member_return_btn)
    TextView memberReturnBtn;

    static String channel;//渠道；channel会员购买入口:  1---详情页面，，2--其他页面进入会员购买页面，3---确认订单页面

    public static void startSelf(Context context, String channels) {
        Intent intent = new Intent(context, MemberCompleteActivity.class);
        context.startActivity(intent);
        channel = channels;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_complete;
    }

    @Override
    protected MemberCompletePresenter initPresenter() {
        return new MemberCompletePresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);

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

    @OnClick(R.id.member_return_btn)
    public void onViewClicked() {//返回
        //channel会员购买入口:  1---详情页面，，2--其他页面进入会员购买页面，3---确认订单页面
        if (channel.equals("3")) {//确认订单页面
            finish();
            CacheActivity.finishActivity();
            new PayMemberEvent(true).post();

        } else {
            finish();
//            CacheActivity.finishSingleActivityByClass(PayMemberActivity.class);
//            CacheActivity.finishSingleActivityByClass(MemberActivity.class);
            CacheActivity.finishActivity();
        }
    }

    /**
     * @Description:会员详情-成功
     * @Time:2020/8/19 15:36
     * @Author:pk
     */
    @Override
    public void getMemberDetailSuccess(int code, MemberBean data) {
        memberTimeExpire.setText("会员到期时间：" + data.getUsers().getMember_end_time());

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
