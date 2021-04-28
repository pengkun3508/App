package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dm.lib.utils.StatusBarUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainHomeActivityEvent;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.bean.UserInfo;
import com.vinnlook.www.surface.mvp.presenter.EditDataPresenter;
import com.vinnlook.www.surface.mvp.view.EditDataView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:编辑个人资料
 * @Time:2020/4/14 9:41
 * @Author:pk
 */
public class EditDataActivity extends BaseActivity<EditDataPresenter> implements EditDataView {


    @BindView(R.id.personal_head_img)
    RoundedImageView personalHeadImg;
    @BindView(R.id.personal_head)
    LinearLayout personalHead;
    @BindView(R.id.personal_name_edit)
    TextView personalNameEdit;
    @BindView(R.id.personal_name)
    LinearLayout personalName;
    @BindView(R.id.personal_sex_edit)
    TextView personalSexEdit;
    @BindView(R.id.personal_sex)
    LinearLayout personalSex;
    @BindView(R.id.personal_phone_edit)
    TextView personalPhoneEdit;//手机号
    @BindView(R.id.personal_phone)
    RelativeLayout personalPhone;
    @BindView(R.id.personal_out_btn)
    TextView personalOutBtn;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    String getMobile;

    public PopupWindow popupwindow;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, EditDataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_data;
    }

    @Override
    protected EditDataPresenter initPresenter() {
        return new EditDataPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        actionBar.getTvRight().setText("编辑");
        actionBar.getTvRight().setVisibility(View.VISIBLE);
        actionBar.getTvRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDataActivity_2.startSelf(EditDataActivity.this);
            }
        });

        personalHeadImg.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    protected void loadData() {
//        presenter.getUserInfoData();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.personal_head_img, R.id.personal_name, R.id.personal_sex, R.id.personal_phone, R.id.personal_out_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_head_img://更换头像
                break;
            case R.id.personal_name://姓名
                break;
            case R.id.personal_sex://性别
                break;
            case R.id.personal_phone://修改手机号
                VerifyPhoneActivity.startSelf(this, getMobile);
                break;
            case R.id.personal_out_btn://退出登录
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView4();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }


                break;
        }
    }

    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    @Override
    public void getUserInfoSuccess(int code, UserInfo data) {
        getMobile = data.getMobile();
        ImageLoader.userIcon(this, personalHeadImg, data.getImg_url());
        personalNameEdit.setText(data.getUser_name());
        personalSexEdit.setText(data.getSex());
//        personalPhoneEdit.setText(data.getMobile());

        if (!getMobile.equals("")) {
            //进行加密
            String phone = getMobile.substring(0, 3) + "****" + getMobile.substring(7, getMobile.length());
            personalPhoneEdit.setText(phone);
        }

    }

    @Override
    public void getUserInfoFail(int code, String msg) {

    }

    @Override
    public void uploadPhotosSuccess(int code, UpdateImgBean data) {

    }

    @Override
    public void uploadPhotosFailed(int code, String msg) {

    }

    @Override
    public void uploadPersonalSuccess(int code, UserInfo data) {

    }

    @Override
    public void uploadPersonalFailed(int code, String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUserInfoData();
    }


    /**
     * 确定退出登录？
     */
    private void initmPopupWindowView4() {
        TextView return_update_btn, sure_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.login_out_layout, null, false);
        return_update_btn = customView.findViewById(R.id.return_update_btn);
        sure_btn = customView.findViewById(R.id.sure_btn);
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
//                UserUtils.getInstance().getUserInfo();
                UserUtils.getInstance().logout();
//                new MyPersonalJudgeEvent(1).post();
                new MainHomeActivityEvent("1").post();
                CacheActivity.finishActivity();
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });

    }

}
