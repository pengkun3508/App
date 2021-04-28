package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.adapter.MealAdapter;
import com.vinnlook.www.surface.bean.SetMealBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.presenter.SetMealPresenter;
import com.vinnlook.www.surface.mvp.view.SetMealView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:套餐列表
 * @Time:2020/12/25$
 * @Author:pk$
 */
public class SetMealActivity extends BaseActivity<SetMealPresenter> implements SetMealView {


    static String goods_id;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.meal_recycler)
    RecyclerView mealRecycler;

    MealAdapter adapter;
    SetMealBean.ListBean listBeans;
    String goods_attr;
    int position1;
    int position2;
    @BindView(R.id.title_back_btn)
    ImageView titleBackBtn;

    public static void startSelf(Context context, String goods_ids) {
        Intent intent = new Intent(context, SetMealActivity.class);
        context.startActivity(intent);
        goods_id = goods_ids;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_meal;
    }

    @Override
    protected SetMealPresenter initPresenter() {
        return new SetMealPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);

        titleBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new MealAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        mealRecycler.setLayoutManager(manager3);
        mealRecycler.setAdapter(adapter);
        adapter.setItemClickListener(new MealAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (adapter.getData().get(position).getType().equals("1")) {
                    adapter.getData().get(position).setType("2");
                } else if (adapter.getData().get(position).getType().equals("2")) {
                    adapter.getData().get(position).setType("1");
                }
                adapter.notifyDataSetChanged();
            }

            //选规格
            @Override
            public void onTypeClickListener(SetMealBean.ListBean data, int position1s, int position2s) {
                goods_attr = data.getGoods_attr();
                listBeans = data;
                position1 = position1s;
                position2 = position2s;
                presenter.getTypeShopData(data.getGoods_id());
            }

            //加入购物车
            @Override
            public void onAddCatClicked(SetMealBean data, int posion) {
                StringBuilder productIdSB = new StringBuilder();

                for (int j = 0; j < adapter.getData().get(posion).getList().size(); j++) {
                    productIdSB.append(adapter.getData().get(posion).getList().get(j).getProduct_id() + ",");
                }

                Log.e("onAddCatClicked", "==data.getAct_id()=" + data.getAct_id());
                Log.e("onAddCatClicked", "==productIdSB=" + productIdSB);
                presenter.addShoppingCarData(data.getAct_id(), productIdSB);

            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getMealListData(goods_id);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getMealListDataSuccess(int code, List<SetMealBean> data) {
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setType("1");
        }
        adapter.setData(data);


    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param
     */
    @Override
    public void getMealListDataFail(int code, String msg) {

    }

    /**
     * @Description:选择商品类型
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShopSuccess(int code, MoveDataBean moveDataBeas) {
        Log.e("选择商品类型", "==Success==" + code);
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(listBeans.getGoods_thumb());
        banner.add(bannerBeans);
        infoBean.setBanner(banner);
        infoBean.setSearch_attr(listBeans.getSearch_attr());
        infoBean.setGoods_id(listBeans.getGoods_id());
        infoBean.setProduct_number(listBeans.getProduct_number());
        infoBean.setProduct_price(listBeans.getProduct_price());
        moveDataBeas.setInfo(infoBean);
        Log.e("选择商品类型", "goods_attr===" + goods_attr);

        TypeSelectDialog.with(getActivity(), moveDataBeas, goods_attr, "2", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_ids, String num, String getAttr_name, String mmake) {
                adapter.getData().get(position1).getList().get(position2).setProduct_id(product_ids);
                Log.e("TypeSelectDialog", "===product_id=====" + product_ids);
                Log.e("TypeSelectDialog", "===getAttr_name=====" + getAttr_name);
                adapter.getData().get(position1).getList().get(position2).setGoods_attr_name(getAttr_name);
                adapter.notifyDataSetChanged();
                TypeSelectDialog.dismiss();

            }
        }).show();


    }

    /**
     * @Description:选择商品类型失败
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShopFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @Description:加入购物车成功
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getAddShoppingCarSuccess(int code, Object data) {
        Toast.makeText(this, "加入购物车成功", Toast.LENGTH_SHORT).show();

    }

    /**
     * @Description:加入购物车失败
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getAddShoppingCarFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
