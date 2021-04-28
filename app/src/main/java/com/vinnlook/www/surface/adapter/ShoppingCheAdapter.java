package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.ToastMaker;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.ShopCartListBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter4;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCheAdapter extends BaseStateAdapter4<ShopCartListBean.ListBean, ShoppingCheAdapter.ShoppingCheHolder> {

    private List<ShopCartListBean.ListBean> list = new ArrayList<>();
    private List<ShopCartListBean.ListBean> list2 = new ArrayList<>();
    int mark;

    Context context;
    AddShopNumberClickListener addShopNumberClickListener;


    public ShoppingCheAdapter(Context context, AddShopNumberClickListener addShopNumberClickListener) {

        this.context = context;
        this.addShopNumberClickListener = addShopNumberClickListener;
    }

    public List<ShopCartListBean.ListBean> getList() {
        return list;
    }

    public List<ShopCartListBean.ListBean> getList2() {
        return list2;
    }

    private ShoppingCheOnClickAll shoppingCheOnClickAll;

    public void setShoppingCheOnClickAll(ShoppingCheOnClickAll shoppingCheOnClickAll) {
        this.shoppingCheOnClickAll = shoppingCheOnClickAll;
    }

    public void setList(List<ShopCartListBean.ListBean> list, int mark) {
        this.list = list;
        this.mark = mark;
        notifyDataSetChanged();
    }

    public void setList2(List<ShopCartListBean.ListBean> list2, int mark) {
        this.list2 = list2;
        this.mark = mark;
        notifyDataSetChanged();
    }

    @Override
    protected ShoppingCheHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ShoppingCheHolder(inflate(parent, R.layout.rv_item_shopping_che));
    }

    @Override
    protected int getHolderType(int position) {
        return super.getHolderType(position);


    }

    class ShoppingCheHolder extends BaseHolder<ShopCartListBean.ListBean> {

        TextView tvPlus;
        RoundTextView tvNum;
        TextView tvReduce;
        ImageView ivCheckCircle;
        RelativeLayout rlCheckCircle;
        TextView shop_cart_name;
        ImageView shop_cart_img;
        TextView shop_cart_type;
        TextView shop_cart_price, shop_cart_price_1, shopping_detele_text;
        RelativeLayout shopping_bg_relayout, shop_cart_type_layout;
        LinearLayout shopping_bg_color;

        RoundLinearLayout item_shopping_btn;


        ShoppingCheHolder(View itemView) {
            super(itemView);
            ivCheckCircle = getView(R.id.iv_check_circle);
            rlCheckCircle = getView(R.id.rl_check_circle);
            tvPlus = getView(R.id.tv_plus);
            tvNum = getView(R.id.tv_num);
            tvReduce = getView(R.id.tv_reduce);
            shop_cart_name = getView(R.id.shop_cart_name);
            shop_cart_img = getView(R.id.shop_cart_img);
            shop_cart_type = getView(R.id.shop_cart_type);
            shop_cart_price = getView(R.id.shop_cart_price);
            shopping_bg_relayout = getView(R.id.shopping_bg_relayout);
            shopping_bg_color = getView(R.id.shopping_bg_color);
            shopping_detele_text = getView(R.id.shopping_detele_text);
            shop_cart_price_1 = getView(R.id.shop_cart_price_1);
            item_shopping_btn = getView(R.id.item_shopping_btn);
            shop_cart_type_layout = getView(R.id.shop_cart_type_layout);

        }


        @Override
        public void bindData(ShopCartListBean.ListBean data) {
            ImageLoader.image(context, shop_cart_img, data.getGoods_thumb());
            shop_cart_name.setText(data.getGoods_name());
            Log.e("bindData", "getGoods_name===" + data.getGoods_name());
            Log.e("bindData", "getGoods_attr_name===" + data.getGoods_attr_name());
            shop_cart_type.setText(data.getGoods_attr_name());
//            shop_cart_price.setText(data.getProduct_price());

            if (Integer.parseInt(data.getIs_promote()) == 1) {//显示限时页面
                shop_cart_price_1.setVisibility(View.VISIBLE);
                shop_cart_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
                shop_cart_price_1.setText(Html.fromHtml("&yen") + data.getProduct_price());
            } else if (Integer.parseInt(data.getIs_promote()) == 0) {//显示普通页面
                shop_cart_price_1.setVisibility(View.GONE);
                shop_cart_price.setText(Html.fromHtml("&yen")+ data.getProduct_price());
            }
            shop_cart_price_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


            tvNum.setText(data.getGoods_number());

            if (data.getIs_check().equals("0")) {//未选择
                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
            } else if (data.getIs_check().equals("1")) {//选择状态
                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
            }

            if (mark == 1) {
//                if (list.contains(data)) {
//                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
//                } else {
//                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
//                }
                rlCheckCircle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data.getIs_check().equals("0")) {//为选择
                            list.add(data);
                            if (shoppingCheOnClickAll != null) {
//                                shoppingCheOnClickAll.OnClick(1, list.size() == getData().size());
                                shoppingCheOnClickAll.OnClick(1, data.getRec_id(), list.size() == getData().size());
                            }
                        } else if (data.getIs_check().equals("1")) {//已选择状态
                            list.remove(data);
                            if (shoppingCheOnClickAll != null) {
//                                shoppingCheOnClickAll.OnClick(0, list.size() == getData().size());
                                shoppingCheOnClickAll.OnClick(0, data.getRec_id(), list.size() == getData().size());
                            }
                        }

//                        if (list.contains(data)) {
//                            list.remove(data);
////                            ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
//                        } else {
////                            ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
//                            list.add(data);
//                        }


                    }
                });


//                shopping_bg_relayout.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View view) {
//                        shopping_bg_color.setBackgroundColor(context.getResources().getColor(R.color.black_60));
//                        shopping_detele_text.setVisibility(View.VISIBLE);
//
//
//                        return false;
//                    }
//                });


                //加号
                tvPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int s = Integer.valueOf(tvNum.getText().toString()) + 1;
                        if (list.contains(data)) {
                            list.get(list.lastIndexOf(data)).setGoods_number("" + s);
                        }
                        data.setGoods_number("" + s);
                        addShopNumberClickListener.onBtnClickListener(s + "", data.getRec_id(), getAdapterPosition());

                    }
                });
                //减号
                tvReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.valueOf(tvNum.getText().toString()) == 1) {
                            ToastMaker.showShort("亲，我是有底线的~");
                            return;
                        }

                        int s = Integer.valueOf(tvNum.getText().toString()) - 1;
                        if (list.contains(data)) {
                            list.get(list.lastIndexOf(data)).setGoods_number("" + s);
                        }
                        data.setGoods_number(s + "");
                        addShopNumberClickListener.onBtnClickListener(s + "", data.getRec_id(), getAdapterPosition());

                    }
                });

                //选类型
                shop_cart_type_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("选类型", "getGoods_attr=leixing=1111=" + data.getGoods_attr());
                        addShopNumberClickListener.onTypeClickListener(data, getAdapterPosition());
//                    TypeSelectDialog.with(getActivity(), moveDataBean, this).show();
                    }
                });
            } else if (mark == 2) {//海淘
//                if (list2.contains(data)) {
//                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
//                } else {
//                    ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
//                }
                rlCheckCircle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data.getIs_check().equals("0")) {//为选择
                            list2.add(data);
                            if (shoppingCheOnClickAll != null) {
                                shoppingCheOnClickAll.OnClick(1, data.getRec_id(), list.size() == getData().size());
                            }
                        } else if (data.getIs_check().equals("1")) {//已选择状态
                            list2.remove(data);
                            if (shoppingCheOnClickAll != null) {
                                shoppingCheOnClickAll.OnClick(0, data.getRec_id(), list.size() == getData().size());
                            }
                        }


//                        if (list2.contains(data)) {
//                            list2.remove(data);
//                            ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
//                        } else {
//                            ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
//                            list2.add(data);
//                        }
//
//                        if (shoppingCheOnClickAll != null) {
//                            shoppingCheOnClickAll.OnClick(list2.size() == getData().size());
//                        }
                    }
                });


                //加号
                tvPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int s = Integer.valueOf(tvNum.getText().toString()) + 1;
//                        if (list2.contains(data)) {
//                            list2.get(list.lastIndexOf(data)).setGoods_number("" + s);
//                        }
//                        data.setGoods_number("" + s);
                        addShopNumberClickListener.onBtnClickListener(s + "", data.getRec_id(), getAdapterPosition());

                    }
                });
                //减号
                tvReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.valueOf(tvNum.getText().toString()) == 1) {
                            ToastMaker.showShort("亲，我是有底线的~");
                            return;
                        }

                        int s = Integer.valueOf(tvNum.getText().toString()) - 1;
//                        if (list2.contains(data)) {
//                            list2.get(list.lastIndexOf(data)).setGoods_number("" + s);
//                        }
//                        data.setGoods_number(s + "");
                        addShopNumberClickListener.onBtnClickListener(s + "", data.getRec_id(), getAdapterPosition());

                    }
                });

                //选类型
                shop_cart_type_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("选类型", "getGoods_attr=leixing=2222=" + data.getGoods_attr());
                        addShopNumberClickListener.onTypeClickListener(data, getAdapterPosition());
//                    TypeSelectDialog.with(getActivity(), moveDataBean, this).show();
                    }
                });


            }

//            //进入详情页面
//            item_shopping_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    addShopNumberClickListener.onItemDetailsPageListener(data, getAdapterPosition());
//                }
//            });


        }

    }

    public interface ShoppingCheOnClickAll {
        void OnClick(int is_check, String rec_id, boolean isall);
    }

    public interface AddShopNumberClickListener {
        void onBtnClickListener(String s, String rec_id, int position);

        void onTypeClickListener(ShopCartListBean.ListBean data, int position);//选择商品类型

//        void onItemDetailsPageListener(ShopCartListBean.ListBean data, int position);
    }

}