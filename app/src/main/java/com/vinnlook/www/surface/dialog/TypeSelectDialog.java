package com.vinnlook.www.surface.dialog;

import android.animation.Animator;
import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.ToastMaker;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.activity.PhotoViewActivity_1;
import com.vinnlook.www.surface.adapter.TypeSelectAdapter;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;

/**
 * @Description:商品详情，规格选择
 * @Time:2020/4/3$
 * @Author:pk$
 */
public class TypeSelectDialog {

    static AnyLayer mAnyLayer;
    private static Activity mActivity;
    MoveDataBean moveDataBean;
    List<String> getBanner;
    String getBannerUrl;
    static RelativeLayout all_layout;
    static ImageView type_img;
    static TextView type_price;
    static TextView type_number;
    static TextView tv_num;
    static TextView tv_reduce;
    static TextView tv_plus;
    static RelativeLayout add_number;
    TextView add_shops;
    private static String url;
    AddShopCarClickListener addShopCarClickListener;
    ProductBean productBean;
    String getAttr_name;
    String mark;//==2，确认订单页面传来，隐藏数量加减；
    String goods_attr = "";
    String getProduct_id;
    String getRec_id;
    String getSearch_attr;


    public static TypeSelectDialog with(Activity activity, MoveDataBean moveDataBeas, String goods_attr, String mark, AddShopCarClickListener addShopCarClickListener) {
        return new TypeSelectDialog(activity, moveDataBeas, goods_attr, mark, addShopCarClickListener);
    }

    public TypeSelectDialog(Activity activity, MoveDataBean moveDataBeas, String goods_attrs, String marks, AddShopCarClickListener addShopCarClickListener) {
        this.mActivity = activity;
        this.moveDataBean = moveDataBeas;
        this.goods_attr = goods_attrs;
        this.addShopCarClickListener = addShopCarClickListener;
        this.mark = marks;
    }

    public static void setUrl(String urls) {
        url = urls;
        Log.e("ShopTypeDataEvent", "==setUrl====" + urls);
        ImageLoader.image(mActivity, type_img, urls);
        type_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoViewActivity_1.startSelf((Activity) mActivity, urls);
            }
        });


    }

    public void show() {
        mAnyLayer = AnyLayer.with(mActivity)
                .contentView(R.layout.dialog_type_select)
                .bindData(new AnyLayer.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyDialog) {
                        MoveDataBean.InfoBean getInfo = moveDataBean.getInfo();
                        List<ProductBean> getProduct = moveDataBean.getProduct();
                        RecyclerView recyclerView = anyDialog.getView(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

                        String getAttr_value = moveDataBean.getAttr().get(2).getValue().get(0).getGoods_attr_id();
                        String getSearch_attrs = getInfo.getSearch_attr() + "|" + getAttr_value;
                        getSearch_attr = getInfo.getSearch_attr();

                        Log.e("默认选择值", "===第一次====goods_attr===000000==" + goods_attr);

                        Log.e("默认选择值", "===第一次====getSearch_attr===000000==" + getSearch_attr);

                        if (goods_attr != null && !goods_attr.equals("")) {
                            getSearch_attr = goods_attr;
                            Log.e("默认选择值", "===默认值====goods_attr===000000==" + goods_attr);

                            for (int i = 0; i < moveDataBean.getProduct().size(); i++) {
                                Log.e("默认选择值", "===默认值====getGoods_attr===111111111==" + moveDataBean.getProduct().get(i).getGoods_attr());
                                if (goods_attr.equals(moveDataBean.getProduct().get(i).getGoods_attr())) {
                                    productBean = moveDataBean.getProduct().get(i);
                                    Log.e("默认选择值", "===默认值====productBean==111===" + productBean);
                                }
                            }
                        }


                        Log.e("默认选择值", "===默认值====productBean===222==" + productBean);
                        Log.e("默认选择值", "===默认值====getSearch_attr=====" + getSearch_attr);
                        Log.e("默认选择值", "===默认值====getProduct=====" + moveDataBean.getProduct());
                        Log.e("默认选择值", "===默认值====getAttr=====" + moveDataBean.getAttr());

                        TypeSelectAdapter typeSelectAdapter = new TypeSelectAdapter(getSearch_attr, getProduct, moveDataBean.getAttr());
                        recyclerView.setAdapter(typeSelectAdapter);
                        typeSelectAdapter.setData(moveDataBean.getAttr());
                        typeSelectAdapter.setOnTypeSelectItemClick(new TypeSelectAdapter.OnTypeSelectItemClick() {
                            @Override
                            public void onTypeClick(ProductBean productBeans, List<MoveDataBean.AttrBean.ValueBean.BannerBeanX> getBanner) {
                                if (productBeans == null) {
                                    return;
                                }
                                productBean = productBeans;
                                type_number.setText("库存" + productBeans.getProduct_number() + "件");

                                if (moveDataBean.getInfo().getIs_group().equals("1")) {
                                    type_price.setText(Html.fromHtml("&yen") + moveDataBean.getInfo().getPreferential_price());
                                } else {
                                    if (moveDataBean.getInfo().getIs_promote().equals("1")) {//显示限时页面
                                        type_price.setText(Html.fromHtml("&yen") + productBeans.getPreferential_price());
                                    } else {
                                        type_price.setText(Html.fromHtml("&yen") + productBeans.getProduct_price());
                                    }
                                }


                                getRec_id = productBean.getRec_id();
                                getProduct_id = productBean.getProduct_id();
                                Log.e("ScreenItemAdapter", "==getIs_promote==" + moveDataBean.getInfo().getIs_promote());
                                Log.e("ScreenItemAdapter", "==getPreferential_price==" + productBeans.getPreferential_price());
                                Log.e("ScreenItemAdapter", "==getProduct_price==" + productBeans.getProduct_price());
                                Log.e("ScreenItemAdapter", "==productBeans==" + productBeans);
                                Log.e("ScreenItemAdapter", "getRec_id==getRec_id==" + getRec_id);
                                Log.e("ScreenItemAdapter", "getProduct_id==getProduct_id==" + getProduct_id);
                                Log.e("TypeSelectDialog", "==getProduct_id==" + productBeans.getProduct_id());
                                Log.e("TypeSelectDialog", "==getAttr_name==" + productBeans.getAttr_name());
                                Log.e("TypeSelectDialog", "==getBanner==" + getBanner);
                                getAttr_name = productBeans.getAttr_name();
                            }
                        });

                        all_layout = anyDialog.getView(R.id.all_layout);
                        type_img = anyDialog.getView(R.id.type_img);
                        type_price = anyDialog.getView(R.id.type_price);
                        type_number = anyDialog.getView(R.id.type_number);
                        add_shops = anyDialog.getView(R.id.add_shops);
                        tv_reduce = anyDialog.getView(R.id.tv_reduce);
                        tv_num = anyDialog.getView(R.id.tv_num);
                        tv_plus = anyDialog.getView(R.id.tv_plus);
                        add_number = anyDialog.getView(R.id.add_number);
                        all_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dismiss();
                            }
                        });


//                        List<MoveDataBean.InfoBean.BannerBean> asda = getInfo.getBanner();
//                        getBanner = getInfo.getBanner().get;
//                        if (getBanner.size() > 1) {
//                            getBannerUrl = getBanner.get(1);
//                        } else {
//                            getBannerUrl = getBanner.get(0);
//                        }

                        if (mark.equals("2")) {
                            add_number.setVisibility(View.GONE);
                        } else {
                            add_number.setVisibility(View.VISIBLE);
                        }

                        if (getInfo.getBanner().size() > 0) {
                            if (getInfo.getBanner().size() == 1) {
                                if (getInfo.getBanner().get(0).getType() == 1) {
                                    getBannerUrl = getInfo.getBanner().get(0).getUrl();
                                } else if (getInfo.getBanner().get(0).getType() == 2) {
                                    getBannerUrl = getInfo.getBanner().get(1).getUrl();
                                }
                            } else if (getInfo.getBanner().size() > 1) {
                                if (getInfo.getBanner().get(0).getType() == 1) {
                                    getBannerUrl = getInfo.getBanner().get(1).getUrl();
                                } else if (getInfo.getBanner().get(0).getType() == 2) {
                                    getBannerUrl = getInfo.getBanner().get(2).getUrl();

                                }
                            }

                        }


//                        if (getInfo.getBanner().get(0).getType() == 1) {
//                            ImageLoader.image(mActivity, type_img, getInfo.getBanner().get(0).getUrl());
//                            getBannerUrl = getInfo.getBanner().get(1).getUrl();
//                        } else if (getInfo.getBanner().get(0).getType() == 2) {
//                            ImageLoader.image(mActivity, type_img, getInfo.getBanner().get(2).getUrl());
//                            getBannerUrl = getInfo.getBanner().get(2).getUrl();
//                        }

                        Log.e("getBannerUrl", "==getBannerUrl==" + getBannerUrl);
                        ImageLoader.image(mActivity, type_img, getBannerUrl);

                        if (moveDataBean.getInfo().getIs_group().equals("1")) {
                            type_price.setText(Html.fromHtml("&yen") + moveDataBean.getInfo().getPreferential_price());
                        } else {
                            if (moveDataBean.getInfo().getIs_promote().equals("1")) {//显示限时页面
                                type_price.setText(Html.fromHtml("&yen") + getInfo.getPreferential_price());
                            } else {
                                type_price.setText(Html.fromHtml("&yen") + getInfo.getProduct_price());
                            }
                        }


                        type_number.setText("库存" + getInfo.getProduct_number() + "件");
                        type_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PhotoViewActivity_1.startSelf((Activity) mActivity, getBannerUrl);
                            }
                        });


                        //加号
                        tv_plus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.e("加号", "==点击了加号====");
                                if (productBean != null) {
                                    int s = Integer.valueOf(tv_num.getText().toString()) + 1;
                                    if (s <= Integer.parseInt(productBean.getProduct_number())) {
                                        tv_num.setText(s + "");
                                    } else {
                                        Toast.makeText(mActivity, "数量已超过库存", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(mActivity, "请先选择完产品", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        //减号
                        tv_reduce.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.e("加号", "==点击了减号====");
                                if (Integer.valueOf(tv_num.getText().toString()) == 1) {
                                    ToastMaker.showShort("亲，我是有底线的~");
                                    return;
                                }

                                if (productBean != null) {
                                    int s = Integer.valueOf(tv_num.getText().toString()) - 1;
                                    tv_num.setText(s + "");
                                } else {
                                    Toast.makeText(mActivity, "请先选择完产品", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });


                        //确定
                        add_shops.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.e("确定", "==productBean==" + productBean);
                                if (productBean != null) {
                                    addShopCarClickListener.onBtnClickListener(moveDataBean.getInfo().getGoods_id(), getRec_id, getProduct_id, tv_num.getText().toString().trim(), productBean.getAttr_name(), productBean, mark);
                                } else {
                                    Toast.makeText(mActivity, "请先选择完产品", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                })
                .backgroundColorRes(R.color.dialog_bg)
                .gravity(Gravity.BOTTOM)
                .contentAnim(new AnyLayer.IAnim() {
                    @Override
                    public Animator inAnim(View target) {
                        return AnimHelper.createBottomAlphaInAnim(target);
                    }

                    @Override
                    public Animator outAnim(View target) {
                        return AnimHelper.createBottomAlphaOutAnim(target);
                    }
                });
        mAnyLayer.show();


    }

    public static void dismiss() {
        mAnyLayer.dismiss();
    }


    public interface AddShopCarClickListener {
        void onBtnClickListener(String goods_id, String getRec_id, String product_id, String num, String getAttr_name, ProductBean productBean, String mmake);
    }


}
