package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.adapter.BannerImgAdapter3;
import com.vinnlook.www.surface.adapter.PaoQiListAdapter;
import com.vinnlook.www.surface.adapter.PinpaiListAdapter;
import com.vinnlook.www.surface.adapter.ReMaiListAdapter;
import com.vinnlook.www.surface.adapter.TitleList_Adapter;
import com.vinnlook.www.surface.bean.HaiTaoClassBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;
import com.vinnlook.www.surface.mvp.presenter.HaiTaoClassPresenter;
import com.vinnlook.www.surface.mvp.view.HaiTaoClassView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:首页--海淘专区-自营专区
 * @Time:2021/1/27$
 * @Author:pk$
 */
public class HaiTaoClassActivity extends BaseActivity<HaiTaoClassPresenter> implements HaiTaoClassView {

    @BindView(R.id.hai_banner)
    Banner haiBanner;
    @BindView(R.id.recyclerv_1)
    RecyclerView recyclerv1;
    @BindView(R.id.re_title_img)
    ImageView reTitleImg;
    @BindView(R.id.recyclerv_remai)
    RecyclerView recyclervRemai;
    @BindView(R.id.pin_title_img)
    ImageView pinTitleImg;
    @BindView(R.id.recyclerv_pinpai)
    RecyclerView recyclervPinpai;
    @BindView(R.id.pao_title_img)
    ImageView paoTitleImg;
    @BindView(R.id.recycler_paoqi)
    RecyclerView recyclerPaoqi;
    @BindView(R.id.home_scroll)
    ScrollView homeScroll;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    TitleList_Adapter adapterTitle;
    ReMaiListAdapter adapterReBang;
    PinpaiListAdapter adapterPinPai;
    PaoQiListAdapter adapterPaoQi;

    List<HaiTaoClassBean.BannerBean> getBanner;
    List<HaiTaoClassBean.TitleBean> getTitle;
    List<HaiTaoClassBean.BrandBean> getBrand;
    List<HaiTaoClassBean.HotGoodsListBean.ListBean> getHotList;
    List<HaiTaoClassBean.BannerBean> bannerImage;

    String xianGoods_attr;
    HaiTaoClassBean.ShopBean.DataBean dataBeanXX;
    HaiTaoClassBean.HotGoodsListBean.ListBean listBean;

    private static String name;
    private static String type;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.search_linear_layout)
    LinearLayout searchLinearLayout;

    public static void startSelf(Context context, String names, String types) {
        Intent intent = new Intent(context, HaiTaoClassActivity.class);
        context.startActivity(intent);
        name = names;
        type = types;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.hai_tao_class_activity;
    }

    @Override
    protected HaiTaoClassPresenter initPresenter() {
        return new HaiTaoClassPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(HaiTaoClassActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleName.setText(name);
        searchLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.startSelf(getContext());
            }
        });

        //数据适配器
        dataadpter();
        haiBanner.post(new Runnable() {
            @Override
            public void run() {
                haiBanner.getWidth();
                double f = Float.valueOf(haiBanner.getWidth() + "") / (1.8);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(haiBanner.getWidth(), (int) f);
                haiBanner.setLayoutParams(layoutParams);
            }
        });

        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.getHaiListData(type);//下载首页数据

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();

            }
        });
    }

    @Override
    protected void loadData() {
        presenter.getHaiListData(type);
    }


    /**
     * 数据适配器
     */
    private void dataadpter() {

        //title展示
        adapterTitle = new TitleList_Adapter(this);
        final LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerv1.setLayoutManager(manager2);
        recyclerv1.setNestedScrollingEnabled(false);
        recyclerv1.setHasFixedSize(true);
        recyclerv1.setAdapter(adapterTitle);
        adapterTitle.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                HomePublicClassActivity.startSelf(HaiTaoClassActivity.this, adapterTitle.getData().get(position).getName(), type, "", "",
                        String.valueOf(adapterTitle.getData().get(position).getId()), "");
            }
        });


        //热卖数据
        adapterReBang = new ReMaiListAdapter(this);
        final LinearLayoutManager manager1 = new LinearLayoutManager(this);
        recyclervRemai.setLayoutManager(manager1);
        recyclervRemai.setNestedScrollingEnabled(false);
        recyclervRemai.setHasFixedSize(true);
        recyclervRemai.setAdapter(adapterReBang);
        adapterReBang.setReBangClickListener(new ReMaiListAdapter.ReBangClickListener() {
            @Override
            public void onGoReClickListener(HaiTaoClassBean.HotGoodsListBean.ListBean data, String getGoods_id, String getSearch_attr) {
                listBean = data;
                presenter.getTypeShopData(getGoods_id);
//                presenter.getTypeShopData("90");
            }
        });

        adapterReBang.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(getActivity(), adapterReBang.getData().get(position).getGoods_id(), adapterReBang.getData().get(position).getSearch_attr(),"");

            }
        });


        //品牌展示
        adapterPinPai = new PinpaiListAdapter(this);
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        manager3.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclervPinpai.setLayoutManager(manager3);
        recyclervPinpai.setNestedScrollingEnabled(false);
        recyclervPinpai.setHasFixedSize(true);
        recyclervPinpai.setAdapter(adapterPinPai);
        adapterPinPai.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {//跳入品牌列表页面
//                CommodityActivity.startSelf(getContext(), getBrand.get(position).getBrand_id(), "", getBrand.get(position).getBrand_name(), type);
                BrendDetailsActivity.startSelf(getActivity(),adapterPinPai.getData().get(position).getBrand_id());

            }
        });
        pinTitleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BrandActivity.startSelf(getContext(), type);

            }
        });

        //抛期专区
        adapterPaoQi = new PaoQiListAdapter(getActivity(), type);
        final LinearLayoutManager manager4 = new LinearLayoutManager(this);
        recyclerPaoqi.setLayoutManager(manager4);
        recyclerPaoqi.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        recyclerPaoqi.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerPaoqi.setNestedScrollingEnabled(false);
        recyclerPaoqi.setHasFixedSize(true);
        recyclerPaoqi.setAdapter(adapterPaoQi);
        adapterPaoQi.setPaoQiAdapter_1ClickListener(new PaoQiListAdapter.PaoQiAdapter_1ClickListener() {
            @Override
            public void onGoClickListener(HaiTaoClassBean.ShopBean.DataBean data, String getGoods_id, String getSearch_attr) {
                xianGoods_attr = getSearch_attr;
                dataBeanXX = data;
                presenter.getTypeShopData_1(getGoods_id);
            }
        });
    }


    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getHaiListDataSuccess(int code, HaiTaoClassBean data) {
        smartRefreshLayout.finishRefresh();
        getBanner = data.getBanner();
        getTitle = data.getTitle();
        //Title
        adapterTitle.setData(getTitle);
        //热卖Tilte
        ImageLoader.image(this, reTitleImg, data.getHot_goods_list().getImage());
        getHotList = data.getHot_goods_list().getList();
        Log.e("海淘专区", "==getHotList=====" + getHotList.size());
        adapterReBang.setData(data.getHot_goods_list().getList());
        //品牌Tilte
        ImageLoader.image(this, pinTitleImg, data.getBrand_img());
        getBrand = data.getBrand().get(0);
        List<HaiTaoClassBean.BrandBean> getBrnad2 = data.getBrand().get(1);//品牌
        for (int i = 0; i < getBrnad2.size(); i++) {
            getBrand.add(getBrnad2.get(i));
        }
        adapterPinPai.setData(getBrand);
        //抛期
        adapterPaoQi.setData(data.getShop());
        //轮播
        bannerImage = data.getBanner();
        haiBanner.setStartPosition(0);
        BannerImgAdapter3 bannerImgAdapter = new BannerImgAdapter3(getActivity(), gatBannetData());
        haiBanner.setAdapter(bannerImgAdapter);
        haiBanner.setIndicator(new CircleIndicator(getActivity()));
        haiBanner.start();

        haiBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                SharedPreferences spf = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                spf.edit().putString("dataid", "海淘，自营专区").commit();
                //type--1：商品详情；2：活动详情；3：url;4:文字；5：商品列表
                if (bannerImage.get(position).getType().equals("1")) {//1：商品详情
//                    MoveAbooutActivity_1.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr());
                    MoveAbooutActivity_3.startSelf(getActivity(), bannerImage.get(position).getList().getGoods_id(), bannerImage.get(position).getList().getSearch_attr(),"");

                } else if (bannerImage.get(position).getType().equals("2")) {//2：活动详情
                    ProductDetailsActivity.startSelf(getContext(), bannerImage.get(position).getList().getActive_id());//进入活动详情页面
                } else if (bannerImage.get(position).getType().equals("3")) {//3：url
                    WebActivity.startSelf(getActivity(), bannerImage.get(position).getList().getUrl());

                } else if (bannerImage.get(position).getType().equals("4")) {//4：文字

                } else if (bannerImage.get(position).getType().equals("5")) {//5：广告商品列表
//                    RecommendActivity_1.startSelf(getContext(), "", bannerImage.get(position).getList().getId());//进入活动详情页面
                    HomePublicClassActivity.startSelf(getContext(), "", "0", "", "", "", bannerImage.get(position).getList().getId());
                } else if (bannerImage.get(position).getType().equals("6")) {//6：会员入口
//                    MemberActivity.startSelf(getContext(), "2");//
                    MemberActivity_1.startSelf(getContext(), "2");//会员购买入口  1---详情页面，，2--其他页面进入会员购买页面
                } else if (bannerImage.get(position).getType().equals("7")) {//7：品牌商品列表
                    CommodityActivity.startSelf(getContext(), bannerImage.get(position).getList().getActive_id(), bannerImage.get(position).getList().getId(), "商品列表", "0");//进入活动详情页面
                }else if (bannerImage.get(position).getType().equals("8")) {//新活动专区
                    HuoDongZoneActivity_1.startSelf(getActivity());
                } else if (bannerImage.get(position).getType().equals("9")) {//9：品团go
                    GroupWorkGoActivity.startSelf(getActivity());
                } else if (bannerImage.get(position).getType().equals("10")) {//10：限时折扣
                    LimitedActivity_1.startSelf(getActivity());
                } else if (bannerImage.get(position).getType().equals("11")) {//11：排行榜
                    RankingListActivity_1.startSelf(getActivity());
                } else if (bannerImage.get(position).getType().equals("12")) {//12：优惠券
                    CouponActivity.startSelf(getActivity());
                }

            }
        });


    }


    public List<String> gatBannetData() {
        List<String> strings = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < bannerImage.size(); i++) {
            ids.add(bannerImage.get(i).getId());
            strings.add(bannerImage.get(i).getPhoto());
        }
        return strings;
    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getHaiListDataFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
    }

    /**
     * @Description:选择商品类型--抛期
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShopSuccess_1(int code, MoveDataBean moveDataBeas) {
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();

        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(dataBeanXX.getGoods_thumb());
        banner.add(bannerBeans);
        infoBean.setBanner(banner);

        infoBean.setSearch_attr(dataBeanXX.getSearch_attr());
        infoBean.setGoods_id(dataBeanXX.getGoods_id());
        infoBean.setProduct_number("0");
        infoBean.setProduct_price(dataBeanXX.getProduct_price());
        moveDataBeas.setInfo(infoBean);
        Log.e("选择商品类型", "===抛期====xianGoods_attr===" + xianGoods_attr);
        TypeSelectDialog.with(getActivity(), moveDataBeas, xianGoods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_name, ProductBean productBean, String mmake) {
                xianGoods_attr = "";
//                presenter.getModifyType(mark, getRec_id, num, product_id);
                presenter.getAddShopCar(goods_id, product_id, num,"");

            }
        }).show();

    }

    @Override
    public void getTypeShopFail_1(int code, String msg) {

    }

    /**
     * 添加购物车成功
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarSuccess(int code, Object data) {
        TypeSelectDialog.dismiss();
        Toast.makeText(getActivity(), "加入购物车成功", Toast.LENGTH_SHORT).show();

    }

    /**
     * 添加购物车失败
     *
     * @param code
     * @param
     */
    @Override
    public void getAddShopCarFail(int code, String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * 加入购物车--选择规格成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getTypeShopSuccess(int code, MoveDataBean data) {
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();
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
            public void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_name,ProductBean productBean, String mmake) {

                presenter.getAddShopCar(goods_id, product_id, num,"");

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
