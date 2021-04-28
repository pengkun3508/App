package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundLinearLayout;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.presenter.ApplyRefundPresenter;
import com.vinnlook.www.surface.mvp.view.ApplyRefundView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:选择售后方式
 * @Time:2020/11/12$
 * @Author:pk$
 */
public class ApplyRefundActivity extends BaseActivity<ApplyRefundPresenter> implements ApplyRefundView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.shouhou_img1)
    ImageView shouhouImg1;
    @BindView(R.id.shouhou_name)
    TextView shouhouName;
    @BindView(R.id.shouhou_type)
    TextView shouhouType;
    @BindView(R.id.shouhou_num)
    TextView shouhouNum;
    @BindView(R.id.tuikuan_shuoming_text)
    TextView tuikuanShuomingText;
    @BindView(R.id.tuikuan_shuoming_layout)
    RoundLinearLayout tuikuanShuomingLayout;

    static OrderDetailsBean.ShopListBean data;
    static String order_id;
    static String getIs_refund_all;
    static int getStatus;
    @BindView(R.id.after_tuihuo_tuikuan)
    RelativeLayout afterTuihuoTuikuan;

    public static void startSelf(Context context, OrderDetailsBean.ShopListBean datas, String order_ids, String getIs_refund_alls,int getStatuss) {
        Intent intent = new Intent(context, ApplyRefundActivity.class);
        context.startActivity(intent);
        data = datas;
        order_id = order_ids;
        getIs_refund_all = getIs_refund_alls;
        getStatus=getStatuss;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_refund;
    }

    @Override
    protected ApplyRefundPresenter initPresenter() {
        return new ApplyRefundPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        shouhouImg1.setScaleType(ImageView.ScaleType.MATRIX);
        shouhouImg1.setImageMatrix(matrix);
        ImageLoader.image(this, shouhouImg1, data.getGoods_thumb());

        shouhouName.setText(data.getGoods_name());
        shouhouType.setText(data.getGoods_attr_name());
        shouhouNum.setText("x" + data.getNumber());

        //仅退款
        tuikuanShuomingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ApplyRefundActivity_1.startSelf(ApplyRefundActivity.this, data, order_id, "1", getIs_refund_all,getStatus);
            }
        });
        //退货退款
        afterTuihuoTuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ApplyRefundActivity_1.startSelf(ApplyRefundActivity.this, data, order_id, "2", getIs_refund_all,getStatus);
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

    @Override
    public void uploadPhotosSuccess(int code, UpdateImgBean data) {

    }

    @Override
    public void uploadPhotosFailed(int code, String msg) {

    }

    @Override
    public void getAddRefundApplySuccess(int code, Object data) {

    }

    @Override
    public void getAddRefundApplyFail(int code, String msg) {

    }

    @Override
    public void getRefundInfoSuccess(int code, RefundInfoBean data) {

    }

    @Override
    public void getRefundInfoFail(int code, String msg) {

    }
}
