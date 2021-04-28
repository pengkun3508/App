package com.vinnlook.www.surface.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.event.ShopCarJudgeEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.activity.ConfirmOrderActivity_1;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.adapter.ShoppingCheAdapter_1;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.surface.bean.ShopCartListBean_1;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.surface.mvp.presenter.VideonFragmentPresenter_1;
import com.vinnlook.www.surface.mvp.view.VideonFragmentView_1;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description: 新购物车
 * @Time:2020/11/24$
 * @Author:pk$
 */
public class ShoppingCheFragment_1 extends BaseFragment<VideonFragmentPresenter_1> implements VideonFragmentView_1 {


    LinearLayout carGoHaitaoBtn;//海淘去凑单
    @BindView(R.id.car_recycler_list)
    RecyclerView carRecyclerList;//海淘List
    @BindView(R.id.car_price_text)
    TextView carPriceText;//价格
    @BindView(R.id.car_go_btn)
    RoundTextView carGoBtn;//去结算
    //    @BindView(R.id.smart_refresh_layout)
//    SmartRefreshLayout smartRefreshLayout;
    private ShoppingCheAdapter_1 adapter1;
    ShopCartListBean_1.ListBean.ListBeanX listBeans3;
    ShopCartListBean_1.ListBean listBeans4;

    String getRec_id;
    String goods_attr;
    ShopCartListBean_1.ListBean listBeans;
    List<ShopCartListBean_1> shopCartListBean_1;

    List<ShopCartListBean_1.ListBean> strings1 = new ArrayList<>();
    List<ShopCartListBean_1.ListBean> strings2 = new ArrayList<>();

    String recId = "";

    public PopupWindow popupwindow;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_che;
    }

    @Override
    protected VideonFragmentPresenter_1 initPresenter() {
        return new VideonFragmentPresenter_1();
    }

    @Override
    protected void initView() {
        initAdapter();

    }

    //创建适配器
    private void initAdapter() {
        adapter1 = new ShoppingCheAdapter_1(getActivity());
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        carRecyclerList.setLayoutManager(manager3);
        carRecyclerList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        carRecyclerList.addItemDecoration(new SpaceItemDecoration(0, 0));
        carRecyclerList.setNestedScrollingEnabled(false);
        carRecyclerList.setHasFixedSize(true);
        carRecyclerList.setAdapter(adapter1);
//        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
//        smartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
//        //刷新数据
//        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                presenter.getShopListData();//下载购物车列表数据
//            }
//        });
        adapter1.setAddCarAdapterClickListener(new ShoppingCheAdapter_1.AddCarAdapterClickListener() {
            @Override
            public void onBtnAllCheckClickListener(ShopCartListBean_1 data, int type, int isAll) {//全选按钮
                if (type == 1) {//自营
                    if (isAll == 0) {
                        presenter.getSelectAllShopping(type, 1, 1);
                        List<ShopCartListBean_1.ListBean> strings = new ArrayList<>();
                        strings.addAll(data.getList());
                        adapter1.setListCheck1_1(strings, 1);
                    } else if (isAll == 1) {
                        presenter.getSelectAllShopping(type, 1, 0);
                        adapter1.setListCheck1_1(new ArrayList<>(), 1);
                    }
                } else {//海淘
                    if (isAll == 0) {
                        presenter.getSelectAllShopping(type, 1, 1);
                        List<ShopCartListBean_1.ListBean> strings = new ArrayList<>();
                        strings.addAll(data.getList());
                        adapter1.setListCheck1_2(strings, 2);
                    } else if (isAll == 1) {
                        presenter.getSelectAllShopping(type, 1, 0);
                        adapter1.setListCheck1_2(new ArrayList<>(), 2);
                    }
                }
            }

            @Override
            public void onBtnItemCheckClick(String rec_id, int isCheck, boolean isAll) {//单选按钮
                presenter.getDanSelectShopping(rec_id, isCheck);

            }

            @Override
            public void onBtnNumberClick(String num, String rec_id, int adapterPosition) {//改变数量
                presenter.getNumberData(num, rec_id);
            }

            @Override
            public void onTypeClickListener(ShopCartListBean_1.ListBean data, int position1, int position2) {//修改规格
//                getRec_id = adapter1.getData(position1).getList().get(position2).getRec_id();
//                goods_attr = adapter1.getData(position1).getList().get(position2).getGoods_attr();
                getRec_id = data.getRec_id();
                goods_attr = data.getGoods_attr();
                listBeans = data;
                presenter.getTypeShopData(data.getGoods_id());

            }

            @Override
            public void onType3ClickListener(ShopCartListBean_1.ListBean.ListBeanX data, int adapterPosition, int position2, int position3) {
                getRec_id = data.getRec_id();
                goods_attr = data.getGoods_attr();
                listBeans3 = data;
                presenter.getTypeShopData3(data.getGoods_id());
            }

            //移除失效产品
            @Override
            public void onRemoveClickListener() {
                presenter.getRemoveData();
            }

            @Override
            public void onType4ClickListener(ShopCartListBean_1.ListBean data, int adapterPosition, int adapterPosition1) {
                getRec_id = data.getRec_id();
                goods_attr = data.getGoods_attr();
                listBeans4 = data;
                presenter.getTypeShopData4(data.getGoods_id());
            }

            @Override
            public void onLongClickListener(ShopCartListBean_1.ListBean data, int posion) {//侧滑删除
                Log.e("要删除的数据ID", "===posion===" + posion);
                String getRec_id = data.getRec_id();
                Log.e("要删除的数据ID", "===getRec_id===" + getRec_id);
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                //    设置Title的图标
//                builder.setIcon(R.drawable.ic_launcher);
                //    设置Title的内容
                builder.setTitle("确定删除吗？");
                //    设置Content来显示一个信息
//                builder.setMessage("确定删除吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.getDeleteData_1(getRec_id, "0");//删除购物车列表数据
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                //    显示出该对话框
                builder.show();

//
            }

            @Override
            public void onItemMoveClickListener(String goods_id, String search_attr) {//进入详情页面
                MoveAbooutActivity_3.startSelf(getActivity(), goods_id, search_attr);

            }


        });


        //去结算
        carGoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer sb = new StringBuffer();
                if (strings1.size() > 0) {
                    for (int i = 0; i < strings1.size(); i++) {
                        sb.append(strings1.get(i).getRec_id()).append(",");
                    }
                    Log.e("结算", "===sb=1111111==" + sb);
                }
                if (strings2.size() > 0) {
                    for (int i = 0; i < strings2.size(); i++) {
                        sb.append(strings2.get(i).getRec_id()).append(",");
                    }
                    Log.e("结算", "===sb=2222222==" + sb);
                }
                Log.e("结算", "===sb===" + sb);
                Log.e("结算", "===length===" + sb.length());

                if (!UserUtils.getInstance().getUserId().equals("")) {
                    if (sb.length() > 0) {
                        recId = sb.deleteCharAt(sb.length() - 1).toString();//去掉最后逗号
                        Log.e("结算", "===拼接字符串===" + recId);
                        presenter.getConfirmOrderData(recId, "", "", "", "", "", "", "", "", "", "");
                    } else {
                        Toast.makeText(getActivity(), "请先选择商品后再进行结算", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onResume() {
        super.onResume();
//        presenter.getShopListData(mark);//下载购物车列表数据
        if (!UserUtils.getInstance().getUserId().equals("")) {
            presenter.getShopListData();//下载购物车列表数据
        } else {
            Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 新购物车列表数据下载成功
     *
     * @param code
     * @param
     */
    @Override
    public void getShopListData_1Success(int code, List<ShopCartListBean_1> data) {
//        if (adapter1.getData().size() > 0) {
//            adapter1.clearData();
//        }
//        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
//        smartRefreshLayout.finishRefresh();
        shopCartListBean_1 = data;
        adapter1.setData(data);
        strings1 = new ArrayList<>();
        strings2 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == 1) {//自营
                if (data.get(i).getIs_all() == 0) {//未选择
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings1.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_1(strings1, 1);
                } else if (data.get(i).getIs_all() == 1) {//已选择

                    strings1.addAll(data.get(i).getList());
                    adapter1.setListCheck1_1(strings1, 1);
                }
                //单独添加数据
                adapter1.setZiYingList(data.get(i).getList());

            } else if (data.get(i).getId() == 2) {//海淘
                if (data.get(i).getIs_all() == 0) {//未选择
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings2.add(data.get(i).getList().get(j));
                        }
                    }
                    Log.e("adapter", "=====strings2=====" + strings2);
                    Log.e("adapter", "=====adapter1=====" + adapter1);
                    adapter1.setListCheck1_2(strings2, 2);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings2.addAll(data.get(i).getList());
                    adapter1.setListCheck1_2(strings2, 2);
                }

                //单独添加数据
                adapter1.setHaiTaoList(data.get(i).getList());

            }
        }

//        getJG();
        getNewJG();

    }

    /**
     * 新购物车列表数据下载失败
     *
     * @param code
     * @param
     */
    @Override
    public void getShopListData_1Fail(int code, String msg) {
        Log.e("新购物车列表数据下载失败", "code======" + code);
        Log.e("新购物车列表数据下载失败", "msg======" + msg);
        if (code == 3000) {
//            smartRefreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
            adapter1.clearData();
        }

    }

    /**
     * 全选成功
     *
     * @param code
     * @param
     */
    @Override
    public void getSelectAllShoppingSuccess(int code, List<ShopCartListBean_1> data) {
        shopCartListBean_1 = data;
        strings1 = new ArrayList<>();
        strings2 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == 1) {//自营
                if (data.get(i).getIs_all() == 0) {//未选择

                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings1.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_1(strings1, 1);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings1 = new ArrayList<>();
                    strings1.addAll(data.get(i).getList());
                    adapter1.setListCheck1_1(strings1, 1);
                }
                //单独添加数据
                adapter1.setZiYingList(data.get(i).getList());

            } else if (data.get(i).getId() == 2) {//海淘
                if (data.get(i).getIs_all() == 0) {//未选择

                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings2.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_2(strings2, 2);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings2 = new ArrayList<>();
                    strings2.addAll(data.get(i).getList());
                    adapter1.setListCheck1_2(strings2, 2);
                }

                //单独添加数据
                adapter1.setHaiTaoList(data.get(i).getList());

            }
        }


        adapter1.setData(data);
        adapter1.notifyDataSetChanged();
        carRecyclerList.setSelected(true);
        //        getJG();
        getNewJG();
    }

    /**
     * 全选失败
     *
     * @param code
     * @param
     */
    @Override
    public void getSelectAllShoppingFail(int code, String msg) {

    }

    /**
     * 单选成功
     *
     * @param code
     * @param
     */
    @Override
    public void getDanSelectShoppingSuccess(int code, List<ShopCartListBean_1> data) {
        shopCartListBean_1 = data;
        strings1 = new ArrayList<>();
        strings2 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == 1) {//自营
                if (data.get(i).getIs_all() == 0) {//未选择

                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings1.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_1(strings1, 1);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings1 = new ArrayList<>();
                    strings1.addAll(data.get(i).getList());
                    adapter1.setListCheck1_1(strings1, 1);
                }
                //单独添加数据
                adapter1.setZiYingList(data.get(i).getList());

            } else if (data.get(i).getId() == 2) {//海淘
                if (data.get(i).getIs_all() == 0) {//未选择

                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings2.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_2(strings2, 2);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings2 = new ArrayList<>();
                    strings2.addAll(data.get(i).getList());
                    adapter1.setListCheck1_2(strings2, 2);
                }

                //单独添加数据
                adapter1.setHaiTaoList(data.get(i).getList());

            }
        }


        adapter1.setData(data);
        adapter1.notifyDataSetChanged();
        carRecyclerList.setSelected(true);
        //        getJG();
        getNewJG();
    }

    /**
     * 单选失败
     *
     * @param code
     * @param
     */
    @Override
    public void getDanSelectShoppingFail(int code, String msg) {

    }

    /**
     * 修改数量成功
     *
     * @param code
     * @param
     */
    @Override
    public void getNumberDataSuccess(int code, List<ShopCartListBean_1> data) {
        shopCartListBean_1 = data;
        strings1 = new ArrayList<>();
        strings2 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == 1) {//自营
                if (data.get(i).getIs_all() == 0) {//未选择

                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings1.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_1(strings1, 1);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings1 = new ArrayList<>();
                    strings1.addAll(data.get(i).getList());
                    adapter1.setListCheck1_1(strings1, 1);
                }
                //单独添加数据
                adapter1.setZiYingList(data.get(i).getList());

            } else if (data.get(i).getId() == 2) {//海淘
                if (data.get(i).getIs_all() == 0) {//未选择

                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings2.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_2(strings2, 2);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings2 = new ArrayList<>();
                    strings2.addAll(data.get(i).getList());
                    adapter1.setListCheck1_2(strings2, 2);
                }

                //单独添加数据
                adapter1.setHaiTaoList(data.get(i).getList());

            }
        }


        adapter1.setData(data);
        adapter1.notifyDataSetChanged();
        carRecyclerList.setSelected(true);
        //        getJG();
        getNewJG();
    }

    /**
     * 修改数量失败
     *
     * @param code
     * @param
     */
    @Override
    public void getNumberDataFail(int code, String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

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
        infoBean.setProduct_number(listBeans.getNumber());
        infoBean.setProduct_price(listBeans.getProduct_price());
        moveDataBeas.setInfo(infoBean);
        Log.e("选择商品类型", "goods_attr===" + goods_attr);

        TypeSelectDialog.with(getActivity(), moveDataBeas, goods_attr, "", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id,String getRec_id, String product_id, String num, String getAttr_name, String mmake) {
                goods_attr = "";
                presenter.getModifyType_1(getRec_id, num, product_id);
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

    }

    /**
     * @Description:修改商品规格成功
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getModifyTypeSuccess(int code, List<ShopCartListBean_1> data) {
//        presenter.getShopListData();//下载购物车列表数据
        shopCartListBean_1 = data;
        strings1 = new ArrayList<>();
        strings2 = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == 1) {//自营
                if (data.get(i).getIs_all() == 0) {//未选择

                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings1.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_1(strings1, 1);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings1 = new ArrayList<>();
                    strings1.addAll(data.get(i).getList());
                    adapter1.setListCheck1_1(strings1, 1);
                }
                //单独添加数据
                adapter1.setZiYingList(data.get(i).getList());

            } else if (data.get(i).getId() == 2) {//海淘
                if (data.get(i).getIs_all() == 0) {//未选择

                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings2.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_2(strings2, 2);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings2 = new ArrayList<>();
                    strings2.addAll(data.get(i).getList());
                    adapter1.setListCheck1_2(strings2, 2);
                }

                //单独添加数据
                adapter1.setHaiTaoList(data.get(i).getList());

            }
        }

        adapter1.setData(data);
        adapter1.notifyDataSetChanged();
        carRecyclerList.setSelected(true);
        TypeSelectDialog.dismiss();
        //        getJG();
        getNewJG();
    }

    /**
     * @Description:修改商品规格失败
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getModifyTypeFail(int code, String msg) {

    }

    /**
     * @Description:删除数据成功
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getDeleteDataSuccess(int code, List<ShopCartListBean_1> data) {
//        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        shopCartListBean_1 = data;
        strings1 = new ArrayList<>();
        strings2 = new ArrayList<>();
        Log.e("getDeleteDataSuccess", "==删除数据成功==" + data.size());
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == 1) {//自营
                if (data.get(i).getIs_all() == 0) {//未选择
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings1.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_1(strings1, 1);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings1 = new ArrayList<>();
                    strings1.addAll(data.get(i).getList());
                    adapter1.setListCheck1_1(strings1, 1);
                }
                //单独添加数据
                adapter1.setZiYingList(data.get(i).getList());

            } else if (data.get(i).getId() == 2) {//海淘
                if (data.get(i).getIs_all() == 0) {//未选择
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        if (data.get(i).getList().get(j).getIs_check().equals("1")) {
                            strings2.add(data.get(i).getList().get(j));
                        }
                    }
                    adapter1.setListCheck1_2(strings2, 2);
                } else if (data.get(i).getIs_all() == 1) {//已选择
                    strings2 = new ArrayList<>();
                    strings2.addAll(data.get(i).getList());
                    adapter1.setListCheck1_2(strings2, 2);
                }
                //单独添加数据
                adapter1.setHaiTaoList(data.get(i).getList());

            }
        }
        Log.e("", "");

        adapter1.setData(data);
        adapter1.notifyDataSetChanged();
        carRecyclerList.setSelected(true);
        //        getJG();
        getNewJG();
    }

    /**
     * @Description:删除数据失败
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getDeleteDataFail(int code, String msg) {
//        smartRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        adapter1.clearData();
    }

    /**
     * 结算成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getConfirmOrderSuccess(int code, ConfirmOrderBean data) {
        Log.e("购物车", "结算成功===code===" + code);
//        ConfirmOrderActivity.startSelf(getContext(), recId, "", "", "");
        ConfirmOrderActivity_1.startSelf(getContext(), recId, "", "", "", "1");


    }


    /**
     * 结算失败
     *
     * @param code
     * @param
     */
    @Override
    public void getConfirmOrderFail(int code, String msg) {
        if (code == 4000) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * @Description:选择商品类型
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShop3Success(int code, MoveDataBean moveDataBeas) {
        Log.e("选择商品类型", "==Success==" + code);
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();

        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(listBeans3.getGoods_thumb());
        banner.add(bannerBeans);
        infoBean.setBanner(banner);
        infoBean.setSearch_attr(listBeans3.getSearch_attr());
        infoBean.setGoods_id(listBeans3.getGoods_id());
        infoBean.setProduct_number(listBeans3.getNumber());
        infoBean.setProduct_price(listBeans3.getProduct_price());
        moveDataBeas.setInfo(infoBean);
        Log.e("选择商品类型", "goods_attr===" + goods_attr);

        TypeSelectDialog.with(getActivity(), moveDataBeas, goods_attr, "2", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id, String getRec_id,String product_id, String num, String getAttr_name, String mmake) {
                goods_attr = "";
                presenter.getModifyType_1(getRec_id, num, product_id);
            }
        }).show();


    }

    /**
     * @Description:选择商品类型失败
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShop3Fail(int code, String msg) {

    }

    /**
     * @Description:选择商品类型
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShop4Success(int code, MoveDataBean moveDataBeas) {
        Log.e("选择商品类型", "==Success==" + code);
        MoveDataBean.InfoBean infoBean = new MoveDataBean.InfoBean();

        List<MoveDataBean.InfoBean.BannerBean> banner = new ArrayList<>();
        MoveDataBean.InfoBean.BannerBean bannerBeans = new MoveDataBean.InfoBean.BannerBean();
        bannerBeans.setType(1);
        bannerBeans.setUrl(listBeans4.getGoods_thumb());
        banner.add(bannerBeans);
        infoBean.setBanner(banner);
        infoBean.setSearch_attr(listBeans4.getSearch_attr());
        infoBean.setGoods_id(listBeans4.getGoods_id());
        infoBean.setProduct_number(listBeans4.getNumber());
        infoBean.setProduct_price(listBeans4.getProduct_price());
        moveDataBeas.setInfo(infoBean);
        Log.e("选择商品类型", "goods_attr===" + goods_attr);

        TypeSelectDialog.with(getActivity(), moveDataBeas, goods_attr, "2", new TypeSelectDialog.AddShopCarClickListener() {
            @Override
            public void onBtnClickListener(String goods_id,String getRec_id, String product_id, String num, String getAttr_name, String mmake) {
                goods_attr = "";
                presenter.getModifyType_1(getRec_id, num, product_id);
            }
        }).show();


    }

    /**
     * @Description:选择商品类型失败
     * @Time:2020/5/11 13:58
     * @Author:pk
     */
    @Override
    public void getTypeShop4Fail(int code, String msg) {

    }


    /**
     * 新计算价格
     *
     * @return
     */
    private double getNewJG() {
        double d = 0.00;
        double d1 = 0.00;
        double d2 = 0.00;
        for (int i = 0; i < adapter1.getData().size(); i++) {
            if (adapter1.getData().get(i).getId() != 3) {
                for (int j = 0; j < adapter1.getData().get(i).getList().size(); j++) {
                    if (adapter1.getData().get(i).getList().get(j).getIs_check().equals("1")) {
                        if (adapter1.getData().get(i).getList().get(j).getIs_promote() == 1) {//限时
                            for (int k = 0; k < Integer.valueOf(adapter1.getData().get(i).getList().get(j).getGoods_number()); k++) {
                                d1 = d1 + Double.valueOf(adapter1.getData().get(i).getList().get(j).getPreferential_price());
                            }
                        } else if (adapter1.getData().get(i).getList().get(j).getIs_promote() == 0) {//普通
                            for (int k = 0; k < Integer.valueOf(adapter1.getData().get(i).getList().get(j).getGoods_number()); k++) {
                                if (adapter1.getData().get(i).getList().get(j).getActive_type().equals("2")) {//套餐
                                    d1 = d1 + Double.valueOf(adapter1.getData().get(i).getList().get(j).getProduct_price());
                                    for (int s = 0; s < adapter1.getData().get(i).getList().get(j).getList().size(); s++) {
                                        d1 = d1 + Double.valueOf(adapter1.getData().get(i).getList().get(j).getList().get(s).getProduct_price());
                                    }
                                } else {
                                    d1 = d1 + Double.valueOf(adapter1.getData().get(i).getList().get(j).getProduct_price());
                                }
                            }
                        }
                    }
                }
            }
        }
        d = d1 + d2;
        Log.e("计算价格", "===d1+d2======" + d);
        carPriceText.setText("合计：" + Html.fromHtml("&yen") + "" + String.format("%.2f", d));
        return d;

    }

    /**
     * 计算价格
     *
     * @return
     */
    private double getJG() {
        Log.e("计算价格", "getListCheck1_1======" + adapter1.getListCheck1_1().size());
        Log.e("计算价格", "getListCheck1_2======" + adapter1.getListCheck1_2().size());
        double d = 0.00;
        double d1 = 0.00;
        double d2 = 0.00;
        for (int i = 0; i < adapter1.getListCheck1_1().size(); i++) {
            if (adapter1.getListCheck1_1().get(i).getIs_promote() == 1) {//限时
                for (int k = 0; k < Integer.valueOf(adapter1.getListCheck1_1().get(i).getGoods_number()); k++) {
                    d1 = d1 + Double.valueOf(adapter1.getListCheck1_1().get(i).getPreferential_price());
                }
            } else if (adapter1.getListCheck1_1().get(i).getIs_promote() == 0) {//普通
                for (int k = 0; k < Integer.valueOf(adapter1.getListCheck1_1().get(i).getGoods_number()); k++) {
                    d1 = d1 + Double.valueOf(adapter1.getListCheck1_1().get(i).getProduct_price());
                }
            }

        }
        for (int i = 0; i < adapter1.getListCheck1_2().size(); i++) {
            if (adapter1.getListCheck1_2().get(i).getIs_promote() == 1) {//限时
                for (int k = 0; k < Integer.valueOf(adapter1.getListCheck1_2().get(i).getGoods_number()); k++) {
                    d2 = d2 + Double.valueOf(adapter1.getListCheck1_2().get(i).getPreferential_price());
                }
            } else if (adapter1.getListCheck1_2().get(i).getIs_promote() == 0) {//普通
                for (int k = 0; k < Integer.valueOf(adapter1.getListCheck1_2().get(i).getGoods_number()); k++) {
                    d2 = d2 + Double.valueOf(adapter1.getListCheck1_2().get(i).getProduct_price());
                }
            }
        }

        d = d1 + d2;
        Log.e("计算价格", "===d1+d2======" + d);
        carPriceText.setText("合计：" + Html.fromHtml("&yen") + "" + String.format("%.2f", d));
        return d;
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ShopCarJudgeEvent event) {
        Log.e("ShoppingFragment", "=首页传递消息==");
        int getJupdg = event.getJupdg();
        if (getJupdg == 1) {
//            presenter.getAppUpdate();//下载时间
            if (!UserUtils.getInstance().getUserId().equals("")) {
                presenter.getShopListData();//下载购物车列表数据
            } else {
                adapter1.clearData();
                adapter1.notifyDataSetChanged();
                carPriceText.setText("合计：" + Html.fromHtml("&yen") + "0.00");
                Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
