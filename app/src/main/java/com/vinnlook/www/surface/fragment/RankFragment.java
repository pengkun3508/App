package com.vinnlook.www.surface.fragment;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.event.ReBangImageBgEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.adapter.ReBangListAdapter;
import com.vinnlook.www.surface.bean.ReBangListBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.presenter.RankFragmentPresenter;
import com.vinnlook.www.surface.mvp.view.RankFragmentView;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description:排行榜fragment
 * @Time:2020/12/23 12:02
 * @Author:pk
 */
@SuppressLint("ValidFragment")
public class RankFragment extends BaseFragment<RankFragmentPresenter> implements RankFragmentView {

    @BindView(R.id.rank_list_recycler)
    RecyclerView rankListRecycler;
    //    @BindView(R.id.smart_refresh_layout)
//    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.img_no2)
    RoundedImageView imgNo2;
    @BindView(R.id.name_no2)
    TextView nameNo2;
    @BindView(R.id.img_no1)
    RoundedImageView imgNo1;
    @BindView(R.id.name_no1)
    TextView nameNo1;
    @BindView(R.id.img_no3)
    RoundedImageView imgNo3;
    @BindView(R.id.name_no3)
    TextView nameNo3;
    private String posion;
    private String name;

    ReBangListAdapter adapter;
    List<ReBangListBean> reBangListBean;
    ReBangListBean listBean;
    List<String> list = new ArrayList<>();

    public static RankFragment getInstance(String posions, String names) {
        RankFragment sf = new RankFragment();
        sf.posion = posions;
        sf.name = names;

        return sf;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fr_simple_card;
    }

    @Override
    protected RankFragmentPresenter initPresenter() {
        return new RankFragmentPresenter();
    }

    @Override
    protected void initView() {
        adapter = new ReBangListAdapter(getActivity());
//        ImageLoader.image(getActivity(), rankTitleImg, name);
        final GridLayoutManager manager1 = new GridLayoutManager(getActivity(), 1);
        rankListRecycler.setLayoutManager(manager1);
        rankListRecycler.setNestedScrollingEnabled(false);
        rankListRecycler.setHasFixedSize(true);
        rankListRecycler.setAdapter(adapter);
        adapter.setReBangClickListener(new ReBangListAdapter.ReBangClickListener() {
            @Override
            public void onGoReClickListener(ReBangListBean data, String getGoods_id, String getSearch_attr) {
                Log.e("RankFragment", "加入购物车11111111111111");
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
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        Log.e("请求数据参数", "===posion===" + posion);
        presenter.getTypeReBangList(posion);//下载列表数据
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

        ImageLoader.image(getActivity(), imgNo1, data.get(0).getGoods_thumb());
        ImageLoader.image(getActivity(), imgNo2, data.get(1).getGoods_thumb());
        ImageLoader.image(getActivity(), imgNo3, data.get(2).getGoods_thumb());


        list.add(data.get(0).getGoods_thumb());

        Log.e("获取列表数据成功", "==list==" + list.size());

//        new ReBangImageBgEvent(data.get(0).getGoods_thumb()).post();

        nameNo1.setText(data.get(0).getBrand_name());
        nameNo2.setText(data.get(1).getBrand_name());
        nameNo3.setText(data.get(2).getBrand_name());

        imgNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoveAbooutActivity_3.startSelf(getActivity(), data.get(0).getGoods_id(), data.get(0).getSearch_attr());
            }
        });
        imgNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoveAbooutActivity_3.startSelf(getActivity(), data.get(1).getGoods_id(), data.get(1).getSearch_attr());
            }
        });
        imgNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoveAbooutActivity_3.startSelf(getActivity(), data.get(2).getGoods_id(), data.get(2).getSearch_attr());
            }
        });


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

        TypeSelectDialog.with(getActivity(), data, listBean.getSearch_attr(), "", new TypeSelectDialog.AddShopCarClickListener() {
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