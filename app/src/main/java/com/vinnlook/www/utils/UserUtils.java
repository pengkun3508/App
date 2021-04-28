package com.vinnlook.www.utils;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.dm.lib.core.common.BaseApp;
import com.dm.lib.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vinnlook.www.eventbas.LoginStateChangeEvent;
import com.vinnlook.www.eventbas.UpDateUserInfoEvent;
import com.vinnlook.www.surface.activity.MainActivity;

/**
 * 用户的辅助类
 *
 * @author Yanbo
 * @version v1.0.0
 * @date 2018/4/11-下午1:03
 */
public class UserUtils {

    private static final String KEY_USER_INFO = "user_info";

    private static UserUtils mUserUtils = null;

    private UserInfoBean mUserInfo;

    public UserUtils() {
        getUserInfo();
    }

    public static UserUtils getInstance() {
        if (mUserUtils == null) {
            mUserUtils = new UserUtils();
        }
        return mUserUtils;
    }

    @NonNull
    public UserInfoBean getUserInfo() {
        if (mUserInfo == null) {
            String userInfoJson = SPUtils.getInstance().get(KEY_USER_INFO, "");
            try {
                mUserInfo = new Gson().fromJson(userInfoJson, UserInfoBean.class);
            } catch (JsonSyntaxException ignore) {
            }
        }
        if (mUserInfo == null) {
            logout();
            mUserInfo = createDefaultUserInfoBean();
        }
        return mUserInfo;
    }


    public void loginTemp(@NonNull UserInfoBean userInfoBean) {
        mUserInfo = userInfoBean;
    }

    public void loginTempCancel() {
        mUserInfo = null;
        mUserInfo = getUserInfo();
    }

    public void login(@NonNull UserInfoBean userInfoBean) {
        mUserInfo = userInfoBean;
        String userInfoJson = new Gson().toJson(mUserInfo);
        new LoginStateChangeEvent().post();
        SPUtils.getInstance().save(KEY_USER_INFO, userInfoJson);

    }

    public void update(@NonNull UserInfoBean userInfoBean) {
        mUserInfo = userInfoBean;
        String userInfoJson = new Gson().toJson(mUserInfo);
        SPUtils.getInstance().save(KEY_USER_INFO, userInfoJson);
        new UpDateUserInfoEvent().post();


    }

    public boolean doIfLogin(Context context) {
        if (isLogin()) {
            return true;
        }
//        LoginActivity.startSelf(context);
        return false;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(getUserId());
    }

    public String getUserId() {
        if (getUserInfo().getUser_id() == null) {
            getUserInfo().setUser_id("");
        }
        return getUserInfo().getUser_id();
    }

    public void logout() {
        mUserInfo = null;
        SPUtils.getInstance().clear();
        new LoginStateChangeEvent().post();
        BaseApp.finishActivityWithout(MainActivity.class);
    }


    private UserInfoBean createDefaultUserInfoBean() {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUser_id("");
        userInfoBean.setSex("0");
        userInfoBean.setMobile("");
        userInfoBean.setImg_url("");
        userInfoBean.setUser_name("");
        userInfoBean.setIs_member(0);
        userInfoBean.setMember_end_time("");

        return userInfoBean;
    }
}
