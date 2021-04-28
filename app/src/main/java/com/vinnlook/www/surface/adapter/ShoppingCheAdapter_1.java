package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.utils.ResUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.HomePublicClassActivity;
import com.vinnlook.www.surface.bean.ShopCartListBean_1;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter4;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

/**
 * @Description:
 * @Time:2020/11/25$
 * @Author:pk$
 */
public class ShoppingCheAdapter_1 extends BaseStateAdapter4<ShopCartListBean_1, ShoppingCheAdapter_1.ShoppingCheHolder> {

    Context context;
    ShoppingCheAdapter_2 adapter2;

    AddCarAdapterClickListener addClickListener;
    private GestureDetector mGestureDetector;

    LinearLayout shopping_bg_color;
    RoundTextView shopping_detele_text;
    int mark;


    List<ShopCartListBean_1.ListBean> ziYingList;
    List<ShopCartListBean_1.ListBean> haiTaoList;


    public void setAddCarAdapterClickListener(AddCarAdapterClickListener addClickListener) {
        this.addClickListener = addClickListener;
    }

    public interface AddCarAdapterClickListener {
        void onBtnAllCheckClickListener(ShopCartListBean_1 data, int type, int isAll);

        void onBtnItemCheckClick(String rec_id, int isCheck, boolean isAll);

        void onBtnNumberClick(String num, String rec_id, int adapterPosition);

        void onTypeClickListener(ShopCartListBean_1.ListBean data, int position1, int position2);

        void onLongClickListener(ShopCartListBean_1.ListBean data, int posion);

        void onItemMoveClickListener(String goods_id, String search_attr);

        void onType3ClickListener(ShopCartListBean_1.ListBean.ListBeanX data, int adapterPosition, int position2, int position3);

        void onRemoveClickListener();

        void onType4ClickListener(ShopCartListBean_1.ListBean data, int adapterPosition, int adapterPosition1);
    }


    public List<ShopCartListBean_1.ListBean> getListCheck1_1() {
        return adapter2.getListCheck2_1();
    }

    public void setListCheck1_1(List<ShopCartListBean_1.ListBean> listCheck, int mark) {
        adapter2.setListCheck2_1(listCheck, mark);
    }

    public List<ShopCartListBean_1.ListBean> getListCheck1_2() {
        return adapter2.getListCheck2_2();
    }

    public void setListCheck1_2(List<ShopCartListBean_1.ListBean> listCheck, int mark) {
        Log.e("adapter", "=====adapter2=====" + adapter2);
        adapter2.setListCheck2_2(listCheck, mark);
    }

    public void setZiYingList(List<ShopCartListBean_1.ListBean> ziYingList) {
        this.ziYingList = ziYingList;
    }

    public void setHaiTaoList(List<ShopCartListBean_1.ListBean> haiTaoList) {
        this.haiTaoList = haiTaoList;
    }


    public ShoppingCheAdapter_1(Context context) {
        this.context = context;
    }


    @Override
    protected ShoppingCheHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ShoppingCheHolder(inflate(parent, R.layout.shopping_car_title_item));
    }

    @Override
    protected int getHolderType(int position) {
        return super.getHolderType(position);

    }

    class ShoppingCheHolder extends BaseHolder<ShopCartListBean_1> {

        LinearLayout car_item_check_layout, car_item_coudan_btn;
        ImageView car_item_check_img, img_icon;
        TextView car_item_title, car_item_type, shixiao_clear_text;
        RecyclerView car_item_recycler_list;


        ShoppingCheHolder(View itemView) {
            super(itemView);
            car_item_check_layout = getView(R.id.car_item_check_layout);
            car_item_check_img = getView(R.id.car_item_check_img);
            car_item_title = getView(R.id.car_item_title);
            car_item_type = getView(R.id.car_item_type);
            car_item_coudan_btn = getView(R.id.car_item_coudan_btn);
            car_item_recycler_list = getView(R.id.car_item_recycler_list);
            shixiao_clear_text = getView(R.id.shixiao_clear_text);
            img_icon = getView(R.id.img_icon);


        }


        @Override
        public void bindData(ShopCartListBean_1 data) {

            if (!data.getImage().equals("") && data != null) {
                img_icon.setVisibility(View.VISIBLE);
                ImageLoader.image(context, img_icon, data.getImage());
            } else {
                img_icon.setVisibility(View.GONE);
            }


            if (data.getIs_all() == 0) {//未选择
                car_item_check_img.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
            } else if (data.getIs_all() == 1) {//选择状态
                car_item_check_img.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
            }
            car_item_title.setText(data.getTitle());
            car_item_type.setText("(" + data.getWaybill() + ")");
            car_item_recycler_list.setAdapter(null);
            car_item_recycler_list.setLayoutManager(new LinearLayoutManager(context));
            car_item_recycler_list.setNestedScrollingEnabled(false);
            car_item_recycler_list.setHasFixedSize(true);
            adapter2 = new ShoppingCheAdapter_2(context);
            car_item_recycler_list.setAdapter(adapter2);
            adapter2.setMark(data.getId());
            adapter2.setData(data.getList());
            adapter2.setTypeId(data.getId());

            if (data.getId() == 3) {//失效商品
                car_item_check_img.setVisibility(View.GONE);
                car_item_coudan_btn.setVisibility(View.GONE);
                shixiao_clear_text.setVisibility(View.VISIBLE);
                car_item_type.setVisibility(View.GONE);
            } else {
                car_item_check_img.setVisibility(View.VISIBLE);
                car_item_coudan_btn.setVisibility(View.VISIBLE);
                shixiao_clear_text.setVisibility(View.GONE);
                car_item_type.setVisibility(View.VISIBLE);
            }


            //进入详情页面
            adapter2.addOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view, int position) {
                    addClickListener.onItemMoveClickListener(data.getList().get(position).getGoods_id(), data.getList().get(position).getSearch_attr());
                }
            });

            //清除失效宝贝
            shixiao_clear_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "清除失效宝贝....", Toast.LENGTH_SHORT).show();
                    addClickListener.onRemoveClickListener();
                }
            });

            adapter2.setAddItemCheckClick(new ShoppingCheAdapter_2.AddItemCheckClick() {

                @Override
                public void onAddItemCheckClickListener(String rec_id, int isCheck, boolean isAll) {//单选按钮
                    addClickListener.onBtnItemCheckClick(rec_id, isCheck, isAll);
                }

                @Override
                public void onBtnNumberClickListener(String num, String rec_id, int adapterPosition) {//修改数量
                    addClickListener.onBtnNumberClick(num, rec_id, adapterPosition);
                }

                @Override
                public void onTypeClickListener(ShopCartListBean_1.ListBean data, int adapterPosition) {//修改规格
                    addClickListener.onTypeClickListener(data, getAdapterPosition(), adapterPosition);
                }

                @Override
                public void onLongClickListener(int posion) {
                    addClickListener.onLongClickListener(data.getList().get(posion), getAdapterPosition());
                }

                @Override
                public void onType3ClickListener(ShopCartListBean_1.ListBean.ListBeanX data, int position2, int position3) {
                    addClickListener.onType3ClickListener(data, getAdapterPosition(), position2, position3);
                }

                @Override
                public void onItemMoveClickListener(String goods_id, String search_attr) {
                    addClickListener.onItemMoveClickListener(goods_id, search_attr);
                }

                @Override
                public void onType4ClickListener(ShopCartListBean_1.ListBean data, int adapterPosition) {
                    addClickListener.onType4ClickListener(data, getAdapterPosition(), adapterPosition);
                }
//                @Override
//                public void onItemMoveClickListener(String goods_id, String search_attr) {//进入详情页面
//                    addClickListener.onItemMoveClickListener(goods_id, search_attr);
//                }
            });

            //全选按钮
            car_item_check_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addClickListener.onBtnAllCheckClickListener(data, data.getId(), data.getIs_all());
                }
            });

            //去凑单
            car_item_coudan_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.getId() == 1) {//自营
                        HomePublicClassActivity.startSelf(context, "自营专区", "1", "", "","","");
                    } else if (data.getId() == 2) {//海淘
                        HomePublicClassActivity.startSelf(context, "海淘专区", "2", "", "","","");
                    }
                }
            });
        }
    }
}

