package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.surface.activity.HomePublicClassActivity;
import com.vinnlook.www.surface.activity.MemberActivity_1;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.activity.ProductDetailsActivity;
import com.vinnlook.www.surface.activity.RecommendActivity_1;
import com.vinnlook.www.surface.activity.WebActivity;
import com.vinnlook.www.utils.AutoSplitTextView;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

/**
 * @Description:
 * @Time:2020/4/22$
 * @Author:pk$
 */
public class PaoQiList_Adapter extends BaseStateAdapter<HomeDataBean.ShopBean, PaoQiList_Adapter.PaoQiListHolder> {

    Context context;


    PaoQiAdapter_1ClickListener paoQiAdapter_1ClickListener;

    public void setPaoQiAdapter_1ClickListener(PaoQiAdapter_1ClickListener paoQiAdapter_1ClickListener) {
        this.paoQiAdapter_1ClickListener = paoQiAdapter_1ClickListener;
    }


    public PaoQiList_Adapter(Context context) {
        this.context = context;

    }

    @Override
    protected PaoQiListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new PaoQiListHolder(inflate(parent, R.layout.paoqilist_item));
    }


    class PaoQiListHolder extends BaseHolder<HomeDataBean.ShopBean> {

        TextView tv_name, paoqi_move;
        RecyclerView recycler_paoqi_item;
        PaoQiItem_Adapter paoQiAdapter3;
        RelativeLayout title_bg_relayout;
        ImageView paoqi_title_bg, paoqi_title_bg2;


        PaoQiListHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.title_bg_relayout);
            tv_name = itemView.findViewById(R.id.paoqi_title);
            paoqi_move = itemView.findViewById(R.id.paoqi_move);
            recycler_paoqi_item = itemView.findViewById(R.id.recycler_paoqi_item);
            paoqi_title_bg = itemView.findViewById(R.id.paoqi_title_bg);
            paoqi_title_bg2 = itemView.findViewById(R.id.paoqi_title_bg2);

        }

        @Override
        public void bindData(HomeDataBean.ShopBean data) {
            Glide.with(context).load(data.getImage().getImages()).into(paoqi_title_bg);
            Glide.with(context).load(data.getImage().getPhoto()).into(paoqi_title_bg2);
            if (data.getImage().getPhoto().equals("")) {
                paoqi_title_bg2.setVisibility(View.GONE);
            } else {
                paoqi_title_bg2.setVisibility(View.VISIBLE);
            }

            paoqi_title_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (data.getId() == -1) {
//                        new MainHomeActivityEvent("4").post();
//                    } else {
//                        RecommendActivity.startSelf((Activity) context, data.getTitle(), String.valueOf(data.getId()));
                    HomePublicClassActivity.startSelf(context, data.getTitle(), "0", "", "", String.valueOf(data.getId()),"");
//                    }
                }
            });
            paoqi_title_bg2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //type--1：商品详情；2：活动详情；3：url;4:文字；5：商品列表
                    if (data.getImage().getType().equals("1")) {//1：商品详情
//                        MoveAbooutActivity_1.startSelf((Activity) context, data.getImage().getList().getGoods_id(), data.getImage().getList().getSearch_attr());
                        MoveAbooutActivity_3.startSelf((Activity) context, data.getImage().getList().getGoods_id(), data.getImage().getList().getSearch_attr(),"");

                    } else if (data.getImage().getType().equals("2")) {//2：活动详情
                        ProductDetailsActivity.startSelf(context, data.getImage().getList().getActive_id());//进入活动详情页面
                    } else if (data.getImage().getType().equals("3")) {//3：url
                        WebActivity.startSelf(context, data.getImage().getList().getUrl());

                    } else if (data.getImage().getType().equals("4")) {//4：文字

                    } else if (data.getImage().getType().equals("5")) {//5：广告商品列表
//                        RecommendActivity_1.startSelf(context, "", data.getImage().getList().getId());//进入商品列表
                        HomePublicClassActivity.startSelf(context, "", "0", "", "", "", data.getImage().getList().getId());

                    } else if (data.getImage().getType().equals("6")) {//6：会员入口
//                        MemberActivity.startSelf(context, "2");//
                        MemberActivity_1.startSelf(context, "2");//会员购买入口
                    }
                }
            });

//            title_bg_relayout.setBackground( new BitmapDrawable(data.getImage()));

//            tv_name.setText(data.getTitle());
//            //查看更多
//            paoqi_move.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    RecommendActivity.startSelf((Activity) context, data.getTitle(), String.valueOf(data.getId()));
//                }
//            });


            List<HomeDataBean.ShopBean.DataBean> getData = data.getData();
            if (paoQiAdapter3 == null) {
                paoQiAdapter3 = new PaoQiItem_Adapter();
                final GridLayoutManager manager3 = new GridLayoutManager(context, 3);
                recycler_paoqi_item.setLayoutManager(manager3);
//                recycler_paoqi_item.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(context, 2)));
//                recycler_paoqi_item.addItemDecoration(new SpaceItemDecoration(0, 0));

                recycler_paoqi_item.setAdapter(paoQiAdapter3);
                paoQiAdapter3.setData(getData);
            } else {
                paoQiAdapter3.setData(getData);
                paoQiAdapter3.notifyDataSetChanged();
            }
            paoQiAdapter3.addOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view, int position) {
//                    MoveAbooutActivity_1.startSelf((Activity) context, getData.get(position).getGoods_id(), getData.get(position).getSearch_attr());
                    MoveAbooutActivity_3.startSelf((Activity) context, paoQiAdapter3.getData().get(position).getGoods_id(), paoQiAdapter3.getData().get(position).getSearch_attr(),"");

                }
            });


        }
    }


    public class PaoQiItem_Adapter extends BaseStateAdapter<HomeDataBean.ShopBean.DataBean, PaoQiItem_Adapter.PaoQiItemHolder> {

        @Override
        protected PaoQiItemHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
            return new PaoQiItemHolder(inflate(parent, R.layout.ripaolist_item));
        }


        class PaoQiItemHolder extends BaseHolder<HomeDataBean.ShopBean.DataBean> {

            ImageView paoqi_item_img;
            AutoSplitTextView paoqi_item_name;
            TextView paoqi_item_type;
            TextView paoqi_item_price, classify_supi;
            LinearLayout home_paoqi_add_car;

            PaoQiItemHolder(View itemView) {
                super(itemView);
                paoqi_item_img = itemView.findViewById(R.id.paoqi_item_img);
                paoqi_item_name = itemView.findViewById(R.id.paoqi_item_name);
                paoqi_item_type = itemView.findViewById(R.id.paoqi_item_type);
                paoqi_item_price = itemView.findViewById(R.id.paoqi_item_price);
                classify_supi = getView(R.id.classify_supi);
                home_paoqi_add_car = getView(R.id.home_paoqi_add_car);


            }

            @Override
            public void bindData(HomeDataBean.ShopBean.DataBean data) {


//                paoqi_item_img.setImageDrawable(ResUtils.getDrawable(R.drawable.shapeyuanjiao3));

                Matrix matrix = new Matrix();           //创建一个单位矩阵
                matrix.setTranslate(0, 0);          //平移x和y各100单位
                matrix.preRotate(0);                   //顺时针旋转30度
                paoqi_item_img.setScaleType(ImageView.ScaleType.MATRIX);
                paoqi_item_img.setImageMatrix(matrix);

//                paoqi_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                Glide.with(context).load(data.getGoods_thumb()).into(paoqi_item_img);
                ImageLoader.image(context, paoqi_item_img, data.getGoods_thumb());
                paoqi_item_name.setText("\u3000\u3000\u3000 " + data.getGoods_name());
//                paoqi_item_type.setText(data.getGoods_name());
                paoqi_item_price.setText(Html.fromHtml("&yen") + data.getProduct_price());
                paoqi_item_type.setText(data.getBrand_name());

                if (data.getSuppliers_id().equals("1")) {//自营
                    classify_supi.setText("自营");
                    classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
                } else if (data.getSuppliers_id().equals("2")) {//海淘
                    classify_supi.setText("海淘");
                    classify_supi.setBackgroundDrawable(classify_supi.getContext().getResources().getDrawable(R.drawable.classify_list_item));
                }

                //加入购物车
                home_paoqi_add_car.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        paoQiAdapter_1ClickListener.onGoClickListener(data, data.getGoods_id(), data.getSearch_attr());
                    }
                });


            }
        }


    }


    public interface PaoQiAdapter_1ClickListener {
        void onGoClickListener(HomeDataBean.ShopBean.DataBean data, String getGoods_id, String getSearch_attr);

    }

}








