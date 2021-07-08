package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.BrandDetailsAdapter;
import com.vinnlook.www.surface.bean.BrandDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.BrendDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.BrendDetailsView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.ScrollExpandTextView;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:品牌商品列表
 * @Time:2021/4/1$
 * @Author:pk$
 */
public class BrendDetailsActivity extends BaseActivity<BrendDetailsPresenter> implements BrendDetailsView {

    @BindView(R.id.brand_details_img)
    ImageView brandDetailsImg;
    @BindView(R.id.msg_title_back)
    ImageView msgTitleBack;
    @BindView(R.id.brand_details_shuoming)
    ScrollExpandTextView brandDetailsShuoming;
    @BindView(R.id.brand_details_recylist)
    RecyclerView brandDetailsRecylist;

    BrandDetailsAdapter adapter;
    static String getBrand_id;


    public static void startSelf(Context context, String getBrand_ids) {
        Intent intent = new Intent(context, BrendDetailsActivity.class);
        context.startActivity(intent);
        getBrand_id = getBrand_ids;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_brend_details;
    }

    @Override
    protected BrendDetailsPresenter initPresenter() {
        return new BrendDetailsPresenter();
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

        adapter = new BrandDetailsAdapter(this);
        final GridLayoutManager manager2 = new GridLayoutManager(getActivity(), 2);
        brandDetailsRecylist.setLayoutManager(manager2);
        brandDetailsRecylist.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        brandDetailsRecylist.addItemDecoration(new SpaceItemDecoration(10, 10));
        brandDetailsRecylist.setNestedScrollingEnabled(false);
        brandDetailsRecylist.setHasFixedSize(true);
        brandDetailsRecylist.setFocusable(false);
        brandDetailsRecylist.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                CommodityActivity.startSelf(getContext(), adapter.getData().get(position).getBrand_id(), "", adapter.getData().get(position).getBrand_name(), type);
                MoveAbooutActivity_3.startSelf(BrendDetailsActivity.this, adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr(),"");

            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getBrandDetailsList("1", "10", getBrand_id);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getBrandDetailsListSuccess(int code, BrandDetailsBean data) {
        adapter.setData(data.getList());
        ImageLoader.image(this, brandDetailsImg, data.getInfo().getBrand_banner());
        brandDetailsShuoming.setText(data.getInfo().getBrand_desc());

    }

    /**
     * 失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getBrandDetailsListFail(int code, String msg) {

    }
}
