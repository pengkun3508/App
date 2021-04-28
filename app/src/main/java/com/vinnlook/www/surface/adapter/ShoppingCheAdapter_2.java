package com.vinnlook.www.surface.adapter;

import android.app.Activity;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.ToastMaker;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.activity.PreferentialActivity;
import com.vinnlook.www.surface.activity.SetMealActivity;
import com.vinnlook.www.surface.bean.ShopCartListBean_1;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter4;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2020/11/25$
 * @Author:pk$
 */
public class ShoppingCheAdapter_2 extends BaseStateAdapter4<ShopCartListBean_1.ListBean, ShoppingCheAdapter_2.ShoppingCheHolder> {

    Context context;
    List<ShopCartListBean_1.ListBean> listCheck1 = new ArrayList<>();
    List<ShopCartListBean_1.ListBean> listCheck2 = new ArrayList<>();
    int mark;
    int mark1;
    int mark2;
    int typeId;
    ShoppingCheAdapter_3 adapter;


    private AddItemCheckClick addItemCheckClick;

    public void setAddItemCheckClick(AddItemCheckClick addItemCheckClick) {
        this.addItemCheckClick = addItemCheckClick;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setTypeId(int typeIds) {
        typeId = typeIds;
    }


    public interface AddItemCheckClick {
        void onAddItemCheckClickListener(String rec_id, int isCheck, boolean isAll);

        void onBtnNumberClickListener(String s, String rec_id, int adapterPosition);

        void onTypeClickListener(ShopCartListBean_1.ListBean data, int adapterPosition);

        void onLongClickListener(int posion);

        void onType3ClickListener(ShopCartListBean_1.ListBean.ListBeanX data, int position, int adapterPosition);

        void onItemMoveClickListener(String goods_id, String search_attr);

        void onType4ClickListener(ShopCartListBean_1.ListBean data, int adapterPosition);

//        void onItemMoveClickListener(String goods_id, String search_attr);
    }


    public ShoppingCheAdapter_2(Context context) {
        this.context = context;
    }


    public List<ShopCartListBean_1.ListBean> getListCheck2_1() {
        return listCheck1;
    }

    public void setListCheck2_1(List<ShopCartListBean_1.ListBean> listCheck, int mark) {
        this.listCheck1 = listCheck;
        this.mark1 = mark;
        notifyDataSetChanged();
    }

    public List<ShopCartListBean_1.ListBean> getListCheck2_2() {
        return listCheck2;
    }

    public void setListCheck2_2(List<ShopCartListBean_1.ListBean> listCheck, int mark) {
        this.listCheck2 = listCheck;
        this.mark2 = mark;
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

    class ShoppingCheHolder extends BaseHolder<ShopCartListBean_1.ListBean> {

        TextView tvPlus, tvReduce, shop_cart_name, shop_cart_type, go_coudan_text, tv_reduce11, tv_plus11;
        RoundTextView tvNum, tv_num11, shixiao_flag;
        ImageView ivCheckCircle;
        RelativeLayout rlCheckCircle;
        ImageView shop_cart_img;
        TextView shop_cart_price, shop_cart_price_1, shopping_detele_text, shopping_item_label_1, shopping_item_label_2, shopping_item_content, shixiao_tips_text, see_details_btn;
        RelativeLayout shopping_bg_relayout, shop_cart_type_layout, shopping_flag_layout, meal_item_num_layout;
        LinearLayout shopping_bg_color, shopping_item_coudan_btn;

        RoundLinearLayout item_shopping_btn, jia_jian_layout;
        RecyclerView meal_item_recycler;


        ShoppingCheHolder(View itemView) {
            super(itemView);
            rlCheckCircle = getView(R.id.rl_check_circle);
            ivCheckCircle = getView(R.id.iv_check_circle);
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
            shopping_item_label_1 = getView(R.id.shopping_item_label_1);
            shopping_item_label_2 = getView(R.id.shopping_item_label_2);
            shopping_item_content = getView(R.id.shopping_item_content);
            shopping_item_coudan_btn = getView(R.id.shopping_item_coudan_btn);
            shopping_flag_layout = getView(R.id.shopping_flag_layout);
            shixiao_tips_text = getView(R.id.shixiao_tips_text);
            see_details_btn = getView(R.id.see_details_btn);

            shixiao_flag = getView(R.id.shixiao_flag);
            go_coudan_text = getView(R.id.go_coudan_text);
            meal_item_recycler = getView(R.id.meal_item_recycler);
            jia_jian_layout = getView(R.id.jia_jian_layout);//加减边框1
            meal_item_num_layout = getView(R.id.meal_item_num_layout);//加减边框2
            tv_reduce11 = getView(R.id.tv_reduce11);//减2
            tv_num11 = getView(R.id.tv_num11);//数量2
            tv_plus11 = getView(R.id.tv_plus11);//加2
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    addItemCheckClick.onLongClickListener(getAdapterPosition());
                    return true;
                }
            });

        }


        @Override
        public void bindData(ShopCartListBean_1.ListBean data) {
            ImageLoader.image(context, shop_cart_img, data.getGoods_thumb());
            shop_cart_name.setText(data.getGoods_name());
            shop_cart_type.setText(data.getGoods_attr_name());
//            shop_cart_price.setText(data.getProduct_price());

            if (data.getActive_type().equals("1")) {
                go_coudan_text.setText("去凑单");
            } else if (data.getActive_type().equals("2")) {
                go_coudan_text.setText("查看更多");
            }
            if (data.getActive_type().equals("2")) {
                jia_jian_layout.setVisibility(View.GONE);
                meal_item_num_layout.setVisibility(View.VISIBLE);
            } else {
                jia_jian_layout.setVisibility(View.VISIBLE);
                meal_item_num_layout.setVisibility(View.GONE);
            }

            if (data.getIs_promote() == 1) {//显示限时页面
                shop_cart_price_1.setVisibility(View.VISIBLE);
                shop_cart_price.setText(Html.fromHtml("&yen") + data.getPreferential_price());
                shop_cart_price_1.setText(Html.fromHtml("&yen") + data.getProduct_price());
            } else if (data.getIs_promote() == 0) {//显示普通页面
                shop_cart_price_1.setVisibility(View.GONE);
                shop_cart_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
            }
            shop_cart_price_1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


            tvNum.setText(data.getGoods_number());
            tv_num11.setText(data.getGoods_number());

            if (data.getIs_check().equals("0")) {//未选择
                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
            } else if (data.getIs_check().equals("1")) {//选择状态
                ivCheckCircle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
            }

            //活动
            if (!data.getShort_flag().equals("")) {
                shopping_item_label_1.setVisibility(View.VISIBLE);
                shopping_flag_layout.setVisibility(View.VISIBLE);
                shopping_item_label_1.setText(data.getShort_flag());
                shopping_item_label_2.setText(data.getShort_flag());
                shopping_item_content.setText(data.getLong_flag());
            } else {
                shopping_item_label_1.setVisibility(View.GONE);
                shopping_flag_layout.setVisibility(View.GONE);
            }
            //是否有凑单
            if (data.getActive_id().equals("")) {
                shopping_item_coudan_btn.setVisibility(View.GONE);
            } else {
                shopping_item_coudan_btn.setVisibility(View.VISIBLE);
            }

            //判断是否是失效产品
            if (typeId == 3) {//失效产品
                jia_jian_layout.setVisibility(View.GONE);
                meal_item_num_layout.setVisibility(View.GONE);
                shopping_item_label_1.setVisibility(View.GONE);
                shopping_flag_layout.setVisibility(View.GONE);
                rlCheckCircle.setVisibility(View.GONE);
                shixiao_flag.setVisibility(View.VISIBLE);
                shop_cart_price.setVisibility(View.GONE);
                shop_cart_price_1.setVisibility(View.GONE);
                shop_cart_name.setTextColor(shop_cart_name.getResources().getColor(R.color.button_normal));
                shop_cart_price_1.setTextColor(shop_cart_price_1.getResources().getColor(R.color.button_normal));
                shixiao_tips_text.setVisibility(View.VISIBLE);
                see_details_btn.setVisibility(View.VISIBLE);

            } else {
                see_details_btn.setVisibility(View.GONE);
                shixiao_tips_text.setVisibility(View.GONE);
                shixiao_flag.setVisibility(View.GONE);
                //选类型
                shop_cart_type_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("选类型", "getGoods_attr=leixing=2222=" + data.getGoods_attr());

//                    TypeSelectDialog.with(getActivity(), moveDataBean, this).show();

                        if (data.getActive_type().equals("1")) {//去凑单
                            addItemCheckClick.onTypeClickListener(data, getAdapterPosition());
                        } else if (data.getActive_type().equals("2")) {//重选套餐
                            List<ShopCartListBean_1.ListBean.ListBeanX> asdas = data.getList();
                            addItemCheckClick.onType4ClickListener(data, getAdapterPosition());
                        }


                    }
                });

            }

            see_details_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MoveAbooutActivity_3.startSelf((Activity) context, data.getGoods_id(), data.getSearch_attr());
                }
            });

            //适配器
            adapter = new ShoppingCheAdapter_3(context);
            final GridLayoutManager manager3 = new GridLayoutManager(context, 1);
            meal_item_recycler.setLayoutManager(manager3);
            meal_item_recycler.setNestedScrollingEnabled(false);
            meal_item_recycler.setHasFixedSize(true);
            meal_item_recycler.setAdapter(adapter);
            adapter.setData(data.getList());
            adapter.setFlag(data.getShort_flag(), typeId);
            adapter.setAdapter3ClickListener(new ShoppingCheAdapter_3.SelectListClickListener() {
                @Override
                public void onAdapter3ClickListener(ShopCartListBean_1.ListBean.ListBeanX data, int posion3) {
                    addItemCheckClick.onType3ClickListener(data, getAdapterPosition(), posion3);
                }
            });

            //进入详情页面
            adapter.addOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view, int position) {
                    addItemCheckClick.onItemMoveClickListener(data.getList().get(position).getGoods_id(), data.getList().get(position).getSearch_attr());
                }
            });


            //去凑单
            shopping_item_coudan_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.getActive_type().equals("1")) {//去凑单
                        PreferentialActivity.startSelf(context, data.getActive_id());
                    } else if (data.getActive_type().equals("2")) {//重选套餐
                        SetMealActivity.startSelf(context, data.getGoods_id());
                    }

                }
            });


            //选择
            rlCheckCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("单选", "mark===" + mark);
                    if (mark == 1) {
                        if (data.getIs_check().equals("0")) {
                            listCheck1.add(data);
                            if (addItemCheckClick != null) {
                                addItemCheckClick.onAddItemCheckClickListener(data.getRec_id(), 1, listCheck1.size() == getData().size());
                            }
                        } else if (data.getIs_check().equals("1")) {
                            listCheck1.remove(data);
                            if (addItemCheckClick != null) {
                                addItemCheckClick.onAddItemCheckClickListener(data.getRec_id(), 0, listCheck1.size() == getData().size());
                            }
                        }
                    } else if (mark == 2) {
                        if (data.getIs_check().equals("0")) {
                            listCheck2.add(data);
                            if (addItemCheckClick != null) {
                                addItemCheckClick.onAddItemCheckClickListener(data.getRec_id(), 1, listCheck2.size() == getData().size());
                            }
                        } else if (data.getIs_check().equals("1")) {
                            listCheck2.remove(data);
                            if (addItemCheckClick != null) {
                                addItemCheckClick.onAddItemCheckClickListener(data.getRec_id(), 0, listCheck2.size() == getData().size());
                            }
                        }
                    }
                }
            });
//            //进入详情页面
//            shop_cart_img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    addItemCheckClick.onItemMoveClickListener(data.getGoods_id(), data.getSearch_attr());
//                }
//            });

            //加号
            tvPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int s = Integer.valueOf(tvNum.getText().toString()) + 1;
                    addItemCheckClick.onBtnNumberClickListener(s + "", data.getRec_id(), getAdapterPosition());

                }
            });
            //加号2
            tv_plus11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int s = Integer.valueOf(tv_num11.getText().toString()) + 1;
                    addItemCheckClick.onBtnNumberClickListener(s + "", data.getRec_id(), getAdapterPosition());
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
                    addItemCheckClick.onBtnNumberClickListener(s + "", data.getRec_id(), getAdapterPosition());
                }
            });
            //减号2
            tv_reduce11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.valueOf(tv_num11.getText().toString()) == 1) {
                        ToastMaker.showShort("亲，我是有底线的~");
                        return;
                    }
                    int s = Integer.valueOf(tv_num11.getText().toString()) - 1;
                    addItemCheckClick.onBtnNumberClickListener(s + "", data.getRec_id(), getAdapterPosition());
                }
            });


//            shopping_bg_color.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//
//                    addItemCheckClick.onLongClickListener(data, getAdapterPosition());
//                    return true;
//                }
//            });


        }

    }


    public interface AddShopNumberClickListener {
        void onBtnCheckClickListener(String s, String rec_id, int position);

    }
}

