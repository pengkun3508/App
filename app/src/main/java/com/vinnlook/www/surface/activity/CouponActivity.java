package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.AlreadyCouponListBean;
import com.vinnlook.www.http.model.CouponListBean;
import com.vinnlook.www.http.model.NotCouponListBean;
import com.vinnlook.www.surface.adapter.CouponAdapter;
import com.vinnlook.www.surface.adapter.CouponYesAdapter;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.NewNotCouponListBean;
import com.vinnlook.www.surface.mvp.presenter.CouponPresenter;
import com.vinnlook.www.surface.mvp.view.CouponView;
import com.vinnlook.www.utils.SmartRefreshHelper;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:我的优惠券
 * @Time:2020/4/1 18:15
 * @Author:pk
 */
public class CouponActivity extends BaseActivity<CouponPresenter> implements CouponView, CouponAdapter.ItemBtnClickListener, CouponYesAdapter.ItemBtnClickListener {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imageButton1)
    RadioButton imageButton1;//未领取
    @BindView(R.id.imageButton2)
    RadioButton imageButton2;//已领取
    TextView coupon_btn;
    @BindView(R.id.imageButton3)
    RadioButton imageButton3;
    @BindView(R.id.imageButton4)
    RadioButton imageButton4;
    @BindView(R.id.view_1)
    View view1;
    @BindView(R.id.view_2)
    View view2;
    @BindView(R.id.view_3)
    View view3;
    @BindView(R.id.view_4)
    View view4;

    String type = "1";

    private CouponYesAdapter yesAdapter;
    private SmartRefreshHelper<CouponListBean> mSmartRefreshHelper;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, CouponActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    protected CouponPresenter initPresenter() {
        return new CouponPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        yesAdapter = new CouponYesAdapter(this, this);
        recyclerView.setAdapter(yesAdapter);


    }

    @Override
    protected void loadData() {
//        presenter.getCouponListData("", "", "", "");//未领取列表数据
        presenter.getNewCounponList(type);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageButton1, R.id.imageButton2, R.id.imageButton3, R.id.imageButton4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageButton1://待领取
                view1.setBackgroundColor(getResources().getColor(R.color.them));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                type = "1";
                presenter.getNewCounponList(type);
                break;
            case R.id.imageButton2://待使用
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.them));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                type = "2";
                presenter.getNewCounponList(type);
                break;
            case R.id.imageButton3://已使用
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.them));
                view4.setBackgroundColor(getResources().getColor(R.color.white));
                type = "3";
                presenter.getNewCounponList(type);
                break;
            case R.id.imageButton4://已过期
                view1.setBackgroundColor(getResources().getColor(R.color.white));
                view2.setBackgroundColor(getResources().getColor(R.color.white));
                view3.setBackgroundColor(getResources().getColor(R.color.white));
                view4.setBackgroundColor(getResources().getColor(R.color.them));
                type = "4";
                presenter.getNewCounponList(type);
                break;
        }
    }


    /**
     * 新--优惠券列表-成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getNewCouponListSuccess(int code, List<NewNotCouponListBean> data) {
        yesAdapter.setData(data);
        yesAdapter.setType(type);
    }

    /**
     * 新--优惠券列表-失败
     *
     * @param code
     * @param
     */
    @Override
    public void getNewCouponListFail(int code, String msg) {

    }

    /**
     * @Description:领取成功
     * @Time:2020/5/8 9:33
     * @Author:pk
     */
    @Override
    public void getCollectCouponsSuccess(int code, Object data) {
//        coupon_btn.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "领取成功", Toast.LENGTH_SHORT).show();
        presenter.getCouponListData("", "", "", "");//未领取列表数据
    }

    /**
     * @Description:领取失败
     * @Time:2020/5/8 9:33
     * @Author:pk
     */
    @Override
    public void getCollectCouponsFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @Description:使用成功
     * @Time:2020/5/8 9:33
     * @Author:pk
     */
    @Override
    public void getConfirmOrderSuccess(int code, ConfirmOrderBean data) {
    }

    /**
     * @Description:使用失败
     * @Time:2020/5/8 9:33
     * @Author:pk
     */
    @Override
    public void getConfirmOrderFail(int code, String msg) {
    }

    /**
     * 领取优惠券按钮
     *
     * @param type_id
     * @param
     */
    @Override
    public void onBtnClickListener(String type_id, TextView coupon_btns) {
        Log.e("领取优惠券", "==type_id==" + type_id);
        coupon_btn = coupon_btns;
        presenter.getCollectCoupons(type_id);

    }


    /**
     * @Description:下载列表数据成功--未领取
     * @Time:2020/5/8 9:33
     * @Author:pk
     */
    @Override
    public void getCouponListSuccess(int code, List<NotCouponListBean> data) {
    }

    /**
     * @Description:下载列表数据失败--未领取
     * @Time:2020/5/8 9:33
     * @Author:pk
     */
    @Override
    public void getCouponListFail(int code, String msg) {
    }

    /**
     * @Description:下载列表数据成功--已领取
     * @Time:2020/5/8 9:33
     * @Author:pk
     */
    @Override
    public void getCouponList1Success(int code, List<AlreadyCouponListBean> data) {
    }

    /**
     * @Description:下载列表数据失败--已领取
     * @Time:2020/5/8 9:33
     * @Author:pk
     */
    @Override
    public void getCouponList1Fail(int code, String msg) {
    }
}
