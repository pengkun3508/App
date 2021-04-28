package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.core.listener.SimpleCallback;
import com.dm.lib.utils.AppInfoUtils;
import com.dm.lib.utils.SPUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.surface.dialog.UpdateDialog;
import com.vinnlook.www.surface.mvp.presenter.AboutUsPresenter;
import com.vinnlook.www.surface.mvp.view.AboutUsView;
import com.vinnlook.www.utils.IntoShopUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 关于我们
 * @Time:2020/6/30$
 * @Author:pk$
 */
public class AboutUsActivity extends BaseActivity<AboutUsPresenter> implements AboutUsView {


    @BindView(R.id.about_pingfen)
    LinearLayout aboutPingfen;
    @BindView(R.id.about_update)
    LinearLayout aboutUpdate;
    @BindView(R.id.about_company)
    LinearLayout aboutCompany;
    @BindView(R.id.about_guize)
    LinearLayout aboutGuize;
    @BindView(R.id.version_no)
    TextView versionNo;

    @BindView(R.id.protocol_btn)
    LinearLayout protocolBtn;
    @BindView(R.id.privacy_btn)
    LinearLayout privacyBtn;
    @BindView(R.id.pay_btn)
    LinearLayout payBtn;

    private UpdateDialog updateDialog = null;
    SignBean signBean;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected AboutUsPresenter initPresenter() {
        return new AboutUsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        versionNo.setText("V" + AppInfoUtils.getVersionName());

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

    @OnClick({R.id.about_pingfen, R.id.about_update, R.id.about_company, R.id.about_guize, R.id.protocol_btn, R.id.privacy_btn, R.id.pay_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.about_pingfen://评分
                String name = Build.MANUFACTURER;
                Log.e("获取手机品牌", "===name===" + name);
                Log.e("获取手机品牌", "===BRAND===" + android.os.Build.BRAND);
                switch (name) {
                    case "HUAWEI":
                        IntoShopUtils.toHuaWei(this, "com.vinnlook.www");
                        break;
                    case "Xiaomi":
                        IntoShopUtils.toXiaoMi(this, "com.vinnlook.www");
                        break;
                    case "vivo":

                    case "OPPO":
                    case "Coolpad":
                    case "Meizu":
                    case "samsung":
                    case "Sony":
                    case "LG":
                    default:
                        //安装了应用宝
                        if (isMobile_spExist()) {
                            //跳到应用宝下载安装
                            IntoShopUtils.toQQDownload(this, "com.vinnlook.www");
                        } else {
                            //没安装
                            Toast.makeText(this, "您的设备未安装腾讯应用宝", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }


                break;
            case R.id.about_update://更新
                String signBeanSting = SPUtils.getInstance().get(Constant.KEY_SIGN_BEAN, "");
                if (signBeanSting != null && !signBeanSting.equals("")) {
                    try {
                        Log.e("更新版本", "==signBeanSting===" + signBeanSting);
                        signBean = new Gson().fromJson(signBeanSting, SignBean.class);
                        Log.e("更新版本", "==signBean===" + signBean);
                        Log.e("更新版本", "==getVersion===" + signBean.getVersion());
                        Log.e("更新版本", "==getVersion===" + signBean.getVersion());
                        Log.e("更新版本", "==getMust===" + signBean.getVersion().getMust());
                        //1：最新版本；2：强制更新；3：提示更新；4：手动更新
                        if (signBean.getVersion().getMust() == 3 || signBean.getVersion().getMust() == 2) {
                            if (updateDialog == null) {
                                updateDialog = UpdateDialog.with(getActivity(), signBean.getVersion())
                                        .setOnDismissListener(new SimpleCallback<Void>() {
                                            @Override
                                            public void onResult(Void data) {
                                                updateDialog = null;
                                            }
                                        });
                                updateDialog.show();
                            }
                        } else {
                            Toast.makeText(this, "当前版本为：V" + AppInfoUtils.getVersionName() + "，暂无新版本", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JsonSyntaxException ignore) {
                        Log.e("更新版本", "==JsonSyntaxException===" + ignore);
                    }
                } else {
                    Toast.makeText(this, "当前版本为：V" + AppInfoUtils.getVersionName() + "，暂无新版本", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.about_company://公司信息
//                WebActivity.startSelf(getActivity(), "http://shop.jealook.com/v1/html/article-info?id=116");
                CompanyActivity.startSelf(getActivity());
                break;
            case R.id.about_guize://协议与规则
//                WebActivity.startSelf(getActivity(), "http://shop.jealook.com/v1/html/article-info?id=119");
                ProtocolPrivacyActivity.startSelf(getContext());
                break;

            case R.id.protocol_btn://用户协议
                WebActivity.startSelf(getActivity(), "http://shop.jealook.com/v1/html/article-info?id=119");
                break;
            case R.id.privacy_btn://隐私政策
                WebActivity.startSelf(getActivity(), "http://shop.jealook.com/v1/html/article-info?id=117");
                break;
            case R.id.pay_btn://购买须知
                WebActivity.startSelf(getActivity(), "http://shop.jealook.com/v1/html/article-info?id=154");
                break;
        }
    }


    public boolean isMobile_spExist() {
        PackageManager manager = this.getPackageManager();
        List<PackageInfo> pkgList = manager.getInstalledPackages(0);
        for (int i = 0; i < pkgList.size(); i++) {
            PackageInfo pI = pkgList.get(i);
            if (pI.packageName.equalsIgnoreCase("com.tencent.android.qqdownloader"))
                return true;
        }
        return false;
    }


}

