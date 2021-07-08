package com.vinnlook.www.surface.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
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
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.OtherDetails1Adapter;
import com.vinnlook.www.surface.adapter.OtherDetails2Adapter;
import com.vinnlook.www.surface.bean.ThemeOtherDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.ThemeOtherDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.ThemeOtherDetailsView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:主题其他商品详情
 * @Time:2021/7/7$
 * @Author:pk$
 */
public class ThemeOtherDetailsActivity extends BaseActivity<ThemeOtherDetailsPresenter> implements ThemeOtherDetailsView {

    static String iD;
    @BindView(R.id.other_details_img)
    ImageView otherDetailsImg;
    @BindView(R.id.other_details_name)
    TextView otherDetailsName;
    @BindView(R.id.other_details_recy1)
    RecyclerView otherDetailsRecy1;
    @BindView(R.id.other_details_go)
    TextView otherDetailsGo;
    @BindView(R.id.other_details_jieshao)
    TextView otherDetailsJieshao;
    @BindView(R.id.other_details_recy2)
    RecyclerView otherDetailsRecy2;

    OtherDetails1Adapter adapter_1;
    OtherDetails2Adapter adapter_2;
    ThemeOtherDetailsBean themeOtherDetailsBean;

    public static void startSelf(Context context, String iDs) {
        Intent intent = new Intent(context, ThemeOtherDetailsActivity.class);
        context.startActivity(intent);
        iD = iDs;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme_other_details;
    }

    @Override
    protected ThemeOtherDetailsPresenter initPresenter() {
        return new ThemeOtherDetailsPresenter();
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(this, false);
        adapter_1 = new OtherDetails1Adapter(this);
        final GridLayoutManager manager = new GridLayoutManager(this, 1);
        manager.setOrientation(GridLayoutManager.HORIZONTAL);
        otherDetailsRecy1.setLayoutManager(manager);
        otherDetailsRecy1.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
        otherDetailsRecy1.addItemDecoration(new SpaceItemDecoration(10, 0));
        otherDetailsRecy1.setAdapter(adapter_1);

        adapter_2 = new OtherDetails2Adapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(this, 1);
        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
        otherDetailsRecy2.setLayoutManager(manager1);
        otherDetailsRecy2.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
        otherDetailsRecy2.addItemDecoration(new SpaceItemDecoration(10, 0));
        otherDetailsRecy2.setAdapter(adapter_2);

        otherDetailsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (themeOtherDetailsBean.getGoods_list().size() > 0) {
                    MoveAbooutActivity_3.startSelf(ThemeOtherDetailsActivity.this, themeOtherDetailsBean.getGoods_list().get(0).getGoods_id(), themeOtherDetailsBean.getGoods_list().get(0).getSearch_attr(), "");
                } else {
                    Toast.makeText(ThemeOtherDetailsActivity.this, "暂无商品", Toast.LENGTH_SHORT).show();
                }
            }
        });
        adapter_2.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                SelectEyeChartActivity.startSelf(ThemeOtherDetailsActivity.this, adapter_2.getData().get(position).getId());
            }
        });


    }

    @Override
    protected void loadData() {
        Log.e("主题其他商品详情", "===iD===" + iD);
        presenter.getThemeOtherDetails(iD);

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
    public void getThemeOtherDetailsSuccess(int code, ThemeOtherDetailsBean data) {
        themeOtherDetailsBean = data;
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        otherDetailsImg.setScaleType(ImageView.ScaleType.MATRIX);
        otherDetailsImg.setImageMatrix(matrix);
        ImageLoader.image(this, otherDetailsImg, data.getImage());
        otherDetailsName.setText(data.getName());
        otherDetailsJieshao.setText(data.getContent());
        List<String> asd = data.getImage_list();
        if (data.getImage_list().size() > 0) {
            otherDetailsRecy1.setVisibility(View.VISIBLE);
            adapter_1.setData(data.getImage_list());
        } else {
            otherDetailsRecy1.setVisibility(View.GONE);
        }

        if (data.getList().size() > 0) {
            otherDetailsRecy2.setVisibility(View.VISIBLE);
            adapter_2.setData(data.getList());
        } else {
            otherDetailsRecy2.setVisibility(View.GONE);
        }


    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getThemeOtherDetailsFail(int code, String msg) {

    }
}



