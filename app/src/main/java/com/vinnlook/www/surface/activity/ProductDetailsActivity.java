package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.HomeTabAdapter;
import com.vinnlook.www.surface.adapter.ProductImageAdapter;
import com.vinnlook.www.surface.bean.ProductDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.ProductDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.ProductDetailsView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:活动页面
 * @Time:2020/4/14 9:43
 * @Author:pk
 */
public class ProductDetailsActivity extends BaseActivity<ProductDetailsPresenter> implements ProductDetailsView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView_img)
    RecyclerView recyclerViewImg;
    private HomeTabAdapter adapter;
    ProductImageAdapter adapterImage;
    static String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_details;
    }


    public static void startSelf(Context context, String ids) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        context.startActivity(intent);
        id = ids;
    }

    @Override
    protected ProductDetailsPresenter initPresenter() {
        return new ProductDetailsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        recyclerViewImg.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewImg.setNestedScrollingEnabled(false);
        recyclerViewImg.setHasFixedSize(true);
        adapterImage = new ProductImageAdapter(this);
        recyclerViewImg.setAdapter(adapterImage);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new HomeTabAdapter();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

//        final int divider = (int) DisplayInfoUtils.getInstance().dp2px(5);
//        RecyclerView.ItemDecoration gridItemDecoration = new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
//                final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
//                final int spanCount = layoutManager.getSpanCount();
//                int layoutPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
//                if (lp.getSpanSize() != spanCount) {
//                    //左边间距
//                    if (layoutPosition % 2 == 1) {
//                        outRect.left = divider / 2;
//                    } else {
//                        outRect.right = divider / 2;
//                    }
//                }
//                outRect.top = divider;
//            }
//        };
//        recyclerView.addItemDecoration(gridItemDecoration);
        recyclerView.setAdapter(adapter);
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr(),"");

            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getProductDetailsData(id);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * @Description:请求成功
     * @Time:2020/5/26 15:51
     * @Author:pk
     */
    @Override
    public void getProductDetailsSuccess(int code, ProductDetailsBean data) {
        actionBar.getTvTitle().setText(data.getTitle());
        adapterImage.setData(data.getContent());
        adapter.setData(data.getProduct());

    }

    /**
     * @Description:请求失败
     * @Time:2020/5/26 15:51
     * @Author:pk
     */
    @Override
    public void getProductDetailsFail(int code, String msg) {

    }
}
