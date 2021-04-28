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
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.AddressEvent;
import com.vinnlook.www.event.AddressListBean_2Event;
import com.vinnlook.www.event.AddressListBean_3Event;
import com.vinnlook.www.event.Address_KeyDown;
import com.vinnlook.www.http.model.AddressListBean;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.AddressAdapter;
import com.vinnlook.www.surface.mvp.presenter.AddressPresenter;
import com.vinnlook.www.surface.mvp.view.AddressView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:管理收货地址
 * @Time:2020/4/8 10:15
 * @Author:pk
 */
public class AddressActivity extends BaseActivity<AddressPresenter> implements AddressView, AddressAdapter.ItemBtnClickListener {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //    @BindView(R.id.smart_refresh_layout)
//    SmartRefreshLayout smartRefreshLayout;
    private AddressAdapter adapter;
    List<AddressListBean> addressListBean;
    static String judge = "0";//1--从我的列表进入；0--从确认订单页面进入,2--从订单详情页面进入

    public static void startSelf(Context context, String judges) {
        Intent intent = new Intent(context, AddressActivity.class);
        context.startActivity(intent);
        judge = judges;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected AddressPresenter initPresenter() {
        return new AddressPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AddressAdapter(this, this);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.e("adapter", "点击Item====" + adapter.getData().get(position).getAddress_name());
                if (judge.equals("0")) {//从确认订单
                    AddressListBean bean = adapter.getData().get(position);
                    new AddressListBean_2Event(bean).post();
                    finish();
                } else if (judge.equals("2")) {//从订单详情
                    AddressListBean bean = adapter.getData().get(position);
                    new AddressListBean_3Event(bean).post();
                    finish();
                }

            }
        });


        actionBar.getIvBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("actionBar.getIvBack()", "==addressListBean.size()==" + addressListBean.size());
//                if (addressListBean.size() == 0) {
                new Address_KeyDown().post();
//                }
                finish();
            }
        });


    }

    @Override
    protected void loadData() {
//        presenter.getAppUpdate();//下载时间
        presenter.getAddressData();//下载地址列表数据

    }


    @OnClick({R.id.tv_ok})
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public boolean onClickWithoutLogin(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_ok:
                AddressAddActivity.startSelf(getContext(), null, null);
                break;

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //这里写需要重写的方法
            if (addressListBean.size() == 0) {
                new Address_KeyDown().post();
            }
            finish();
            return false;
        }
        return false;

    }


    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddressEvent event) {
//        adapter.setData(event.getRealNameList());
//        adapter.notifyDataSetChanged();

        presenter.getAddressData();//下载地址列表数据
    }


    //获取时间成功
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {
//        presenter.getAddressData();//下载地址列表数据
    }

    //获取时间失败
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    //下载列表数据成功
    @Override
    public void getAddressDataSuccess(int code, List<AddressListBean> data) {
        addressListBean = data;
        adapter.setData(data);
        adapter.notifyDataSetChanged();

    }

    //下载列表数据失败
    @Override
    public void getAddressDataFail(int code, String msg) {
//        if (code == 3000) {
//            List<AddressListBean> data = new ArrayList<>();
//            adapter.setData(data);
//            adapter.notifyDataSetChanged();
//            presenter.dismissLoading();
//        }

    }

    //编辑地址成功
    @Override
    public void getEditAddressDataSuccess(int code, List<AddressListBean> data) {
        addressListBean = data;
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    //编辑地址失败
    @Override
    public void getEditAddressDataFail(int code, String msg) {
//        if (code == 3000) {
//            List<AddressListBean> data = new ArrayList<>();
//            adapter.setData(data);
//            adapter.notifyDataSetChanged();
//            presenter.dismissLoading();
//        }

    }

    //删除地址成功
    @Override
    public void getDeleteAddressDataSuccess(int code, List<AddressListBean> data) {
        addressListBean = data;
        adapter.setData(data);
        adapter.notifyDataSetChanged();

    }

    //删除地址失败
    @Override
    public void getDeleteAddressDataFail(int code, String msg) {
//        if (code == 3000) {
//            List<AddressListBean> data = new ArrayList<>();
//            adapter.setData(data);
//            adapter.notifyDataSetChanged();
//            presenter.dismissLoading();
//        }
    }

    //默认选择
    @Override
    public void onBtnClickListener(String address_id, String is_default) {
//        presenter.getEditAdress("", "", "", "", "", "", is_default, address_id, "");//下载首页数据
        presenter.getEditAdress(is_default, address_id);//默认选择
    }

    //删除点击
    @Override
    public void onDeletClickListener(String address_id, int pos) {
        adapter.removeData(pos);
        presenter.getDeleteAdress(address_id);//删除数据
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        presenter.getAddressData();//下载地址列表数据
//    }
}
