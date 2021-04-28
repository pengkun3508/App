package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.Address_KeyDown;
import com.vinnlook.www.event.RealNameEvent;
import com.vinnlook.www.event.RealName_2Event;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.RealNameAdapter;
import com.vinnlook.www.surface.bean.RealNameListBean;
import com.vinnlook.www.surface.mvp.presenter.RealNamePresenter;
import com.vinnlook.www.surface.mvp.view.RealNameView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:实名认证列表
 * @Time:2020/5/9$
 * @Author:pk$
 */
public class RealNameActivity extends BaseActivity<RealNamePresenter> implements RealNameView, RealNameAdapter.ItemBtnClickListener {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //    @BindView(R.id.smart_refresh_layout)
//    SmartRefreshLayout smartRefreshLayout;
    RealNameAdapter adapter;
    @BindView(R.id.tv_ok)
    RoundTextView tvOk;

    List<RealNameListBean> realNameListBean;
    static String judge;//1--从我的列表进入；0--从订单页面进入

    public static void startSelf(Context context, String judges) {
        Intent intent = new Intent(context, RealNameActivity.class);
        context.startActivity(intent);
        judge = judges;

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_realname;
    }

    @Override
    protected RealNamePresenter initPresenter() {
        return new RealNamePresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RealNameAdapter(this, this);
        recyclerView.setAdapter(adapter);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealNameEditActivity.startSelf(RealNameActivity.this, null, null);
            }
        });

        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.e("----adapter----", "==position==" + position);
                if (judge.equals("0")) {
                    Log.e("----adapter----", "==realNameListBean==" + realNameListBean.get(position));
                    RealNameListBean bean = realNameListBean.get(position);
                    new RealName_2Event(bean).post();
                    finish();
                }
            }
        });


        actionBar.getIvBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("actionBar.getIvBack()", "==addressListBean.size()==" + realNameListBean.size());
                if (realNameListBean.size() == 0) {
                    new Address_KeyDown().post();
                }
                finish();
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getRealNameListData();//下载
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //这里写需要重写的方法
            if (realNameListBean.size() == 0) {
                new Address_KeyDown().post();
            }
            finish();
            return false;
        }
        return false;

    }


    /**
     * @Description:下载时间成功
     * @Time:2020/5/9 15:11
     * @Author:pk
     */
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    /**
     * @Description:下载时间失败
     * @Time:2020/5/9 15:11
     * @Author:pk
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:下载实名认证成功
     * @Time:2020/5/9 15:11
     * @Author:pk
     */
    @Override
    public void getRealNameListSuccess(int code, List<RealNameListBean> data) {

        realNameListBean = data;
        adapter.setData(data);
        adapter.notifyDataSetChanged();

    }

    /**
     * @Description:下载实名认证失败
     * @Time:2020/5/9 15:11
     * @Author:pk
     */
    @Override
    public void getRealNameListFail(int code, String msg) {
//        if (code == 3000) {
//            List<RealNameListBean> data = new ArrayList<>();
//            adapter.setData(data);
//            adapter.notifyDataSetChanged();
//            presenter.dismissLoading();
//        }
    }

    /**
     * @Description:默认实名认证成功
     * @Time:2020/5/9 16:06
     * @Author:pk
     */
    @Override
    public void getEditRealNameSuccess(int code, List<RealNameListBean> data) {
        realNameListBean = data;
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    /**
     * @Description:默认实名认证失败
     * @Time:2020/5/9 16:06
     * @Author:pk
     */
    @Override
    public void getEditRealNameFail(int code, String msg) {

    }


    /**
     * @Description:删除实名认证成功
     * @Time:2020/5/9 16:06
     * @Author:pk
     */
    @Override
    public void getDeletRealNameSuccess(int code, List<RealNameListBean> data) {
        realNameListBean = data;
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    /**
     * @Description:删除实名认证失败
     * @Time:2020/5/9 16:06
     * @Author:pk
     */
    @Override
    public void getDeletRealNameFail(int code, String msg) {

    }

    //选择默认状态
    @Override
    public void onBtnClickListener(String id, String is_default) {

        presenter.getEditRealName(id, is_default);//默认选择

    }

    //  删除
    @Override
    public void onDeletClickListener(String id, int pos) {
        adapter.removeData(pos);
        presenter.getDeletRealName(id);//默认选择

    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(RealNameEvent event) {
//        adapter.setData(event.getRealNameList());
//        adapter.notifyDataSetChanged();

        presenter.getRealNameListData();//下载
    }
}
