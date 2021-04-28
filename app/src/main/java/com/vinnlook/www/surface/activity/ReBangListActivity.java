package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.adapter.ReBangListAdapter;
import com.vinnlook.www.surface.bean.ReBangListBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.presenter.ReBangListPresenter;
import com.vinnlook.www.surface.mvp.view.ReBangListView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:商品详情进入--热榜列表
 * @Time:2020/11/5$
 * @Author:pk$
 */
public class ReBangListActivity extends BaseActivity<ReBangListPresenter> implements ReBangListView {

    static String type;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.rebang_list_recycler)
    RecyclerView rebangListRecycler;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rebang_title)
    TextView rebangTitle;

    ReBangListAdapter adapter;
    List<ReBangListBean> reBangListBean;
    ReBangListBean listBean;
    @BindView(R.id.msg_title_back)
    ImageView msgTitleBack;

    public static void startSelf(Context context, String types) {
        Intent intent = new Intent(context, ReBangListActivity.class);
        context.startActivity(intent);
        type = types;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rebang_list;
    }

    @Override
    protected ReBangListPresenter initPresenter() {
        return new ReBangListPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        msgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (type.equals("1")) {
//            actionBar.setTitle("Vinnlook新品排行榜");
            rebangTitle.setText("新品畅销榜");
        } else if (type.equals("2")) {
//            actionBar.setTitle("Vinnlook全部排行榜");
            rebangTitle.setText("全部畅销榜");
        } else if (type.equals("3")) {
//            actionBar.setTitle("Vinnlook日抛排行榜");
            rebangTitle.setText("日抛畅销榜");
        } else if (type.equals("4")) {
//            actionBar.setTitle("Vinnlook双周抛排行榜");
            rebangTitle.setText("双周抛畅销榜");
        } else if (type.equals("5")) {
//            actionBar.setTitle("Vinnlook月抛排行榜");
            rebangTitle.setText("月抛畅销榜");
        }

        adapter = new ReBangListAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        rebangListRecycler.setLayoutManager(manager1);
        rebangListRecycler.setNestedScrollingEnabled(false);
        rebangListRecycler.setHasFixedSize(true);
        rebangListRecycler.setAdapter(adapter);

        adapter.setReBangClickListener(new ReBangListAdapter.ReBangClickListener() {
            @Override
            public void onGoReClickListener(ReBangListBean data, String getGoods_id, String getSearch_attr) {
                Log.e("rebang_add_car", "加入购物车11111111111111");
                listBean = data;
                presenter.getTypeShopData(getGoods_id);
//                presenter.getTypeShopData("90");
            }
        });
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());

            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getTypeReBangList(type);//下载列表数据

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 获取列表数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getTypeReBangListSuccess(int code, List<ReBangListBean> data) {
        reBangListBean = data;
        adapter.setData(data);

    }

    /**
     * 获取列表数据失败
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeReBangListFail(int code, String msg) {

    }

    /**
     * 加入购物车--选择规格成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getTypeShopSuccess(int code, MoveDataBean data) {
        Log.e("rebang_add_car", "加入购物车222222222222222");
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
//        List<String> strings = new ArrayList<>();
//        strings.add(listBean.getGoods_thumb());
//        infoBean.setBanner(strings);

        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(listBean.getGoods_thumb());
        banner.add(bannerBeans);
        infoBean.setBanner(banner);
        infoBean.setSearch_attr(listBean.getSearch_attr());
        infoBean.setGoods_id(listBean.getGoods_id());
        infoBean.setProduct_number("0");
        infoBean.setProduct_price(listBean.getProduct_price());
        data.setInfo(infoBean);

        TypeSelectDialog.with(this, data, listBean.getSearch_attr(), "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_name, String mmake) {

//                presenter.getModifyType(mark, getRec_id, num, product_id);
                presenter.getAddShopCar(goods_id, product_id, num);

            }
        }).show();

    }

    /**
     * 加入购物车--选择规格失败
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeShopFail(int code, String msg) {

    }

    /**
     * 加入购物车--成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getAddShopCarSuccess(int code, Object data) {
        TypeSelectDialog.dismiss();
        Toast.makeText(getActivity(), "加入购物车成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加入购物车--失败
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarFail(int code, String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
