package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.RealNameEvent;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.RealNameDetailsBean;
import com.vinnlook.www.surface.bean.RealNameListBean;
import com.vinnlook.www.surface.mvp.presenter.RealNameEditPresenter;
import com.vinnlook.www.surface.mvp.view.RealNameEditView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:编辑--新添认证页面
 * @Time:2020/5/9$
 * @Author:pk$
 */
public class RealNameEditActivity extends BaseActivity<RealNameEditPresenter> implements RealNameEditView {

    private static String GetId;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.et_realname_name)
    EditText etRealnameName;
    @BindView(R.id.et_realname_idcar)
    EditText etRealnameIdcar;
    @BindView(R.id.iv_check_circle)
    ImageView ivCheckCircle;
    @BindView(R.id.iv_check_circle_ly)
    RelativeLayout ivCheckCircleLy;
    @BindView(R.id.add_ok)
    RoundTextView addOk;

    RealNameListBean bean;

    String getIs_default = "0";

    public static void startSelf(Context context, RealNameListBean bean, String GetIds) {
        Intent intent = new Intent(context, RealNameEditActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
        GetId = GetIds;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_realname_edit;
    }

    @Override
    protected RealNameEditPresenter initPresenter() {
        return new RealNameEditPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        bean = (RealNameListBean) getIntent().getSerializableExtra("bean");
        if (bean == null) {
            actionBar.setTitle("新增认证");
        } else {
            actionBar.setTitle("编辑认证");
        }

        //默认选择
        ivCheckCircleLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getIs_default.equals("1")) {
                    getIs_default = "0";
                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                } else {
                    getIs_default = "1";
                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
                }
            }
        });

        /**
         * 保存
         */
        addOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(AddressAddActivity.this, "点击保存！！！", Toast.LENGTH_LONG).show();
                if (!TextUtils.isEmpty(etRealnameName.getText())) {//姓名
                    if (!TextUtils.isEmpty(etRealnameIdcar.getText())) {//联系电话
                        if (bean == null) {
                            Log.e("保存", "==新增数据===");
                            //新增地址
                            Log.e("", "==新增数据=etRealnameName==" + etRealnameName.getText().toString().trim());
                            Log.e("", "==新增数据=etRealnameIdcar==" + etRealnameIdcar.getText().toString().trim());
                            Log.e("", "==新增数据=getIs_default==" + getIs_default);
                            presenter.getAddRealName(etRealnameName.getText().toString(), etRealnameIdcar.getText().toString().trim(), getIs_default);//上传数据
                        } else {
                            //编辑地址
                            presenter.getRealNameEdit(etRealnameName.getText().toString(), etRealnameIdcar.getText().toString(), getIs_default, GetId);//上传数据
                        }

                    } else {
                        Toast.makeText(RealNameEditActivity.this, "身份证号不能为空", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RealNameEditActivity.this, "姓名不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void loadData() {
        if (bean == null) {//新增地址
            return;
        } else {//编辑地址
            presenter.getRealNameDetails(GetId);//下载地址详情数据
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * @Description:下载时间成功
     * @Time:2020/5/9 16:53
     * @Author:pk
     */
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {


    }

    /**
     * @Description:下载时间失败
     * @Time:2020/5/9 16:53
     * @Author:pk
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:下载实名成功
     * @Time:2020/5/9 16:53
     * @Author:pk
     */
    @Override
    public void getRealNameDetailsSuccess(int code, RealNameDetailsBean data) {

        etRealnameName.setText(data.getName());
        etRealnameIdcar.setText(data.getId_card());
        getIs_default = data.getIs_default();

        if (data.getIs_default().equals("1")) {//默认
            ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
        } else {
            ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
        }


    }

    /**
     * @Description:下载实名失败
     * @Time:2020/5/9 16:53
     * @Author:pk
     */
    @Override
    public void getRealNameDetailsFail(int code, String msg) {


    }

    /**
     * @Description:新添实名
     * @Time:2020/5/9 16:53
     * @Author:pk
     */
    @Override
    public void getAddRealNameSuccess(int code, List<RealNameListBean> data) {
        new RealNameEvent(data).post();
        finish();
    }

    @Override
    public void getAddRealNameFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @Description:编辑实名
     * @Time:2020/5/9 16:53
     * @Author:pk
     */
    @Override
    public void getRealNameEditSuccess(int code, List<RealNameListBean> data) {
        new RealNameEvent(data).post();
        finish();
    }

    @Override
    public void getRealNameEditFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
