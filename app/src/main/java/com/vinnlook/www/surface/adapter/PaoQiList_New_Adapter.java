package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.HomeDataBean;
import com.vinnlook.www.surface.activity.HomePublicClassActivity;
import com.vinnlook.www.surface.activity.MemberActivity_1;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.activity.ProductDetailsActivity;
import com.vinnlook.www.surface.activity.WebActivity;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.utils.AutoSplitTextView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import java.util.List;

/**
 * @Description:
 * @Time:2021/4/6$
 * @Author:pk$
 */
public class PaoQiList_New_Adapter extends BaseStateAdapter<HomeTab1Bean.ShopBean, PaoQiList_New_Adapter.PaoQiListHolder> {

    Context context;


    PaoQiAdapter_1ClickListener paoQiAdapter_1ClickListener;

    public void setPaoQiAdapter_1ClickListener(PaoQiAdapter_1ClickListener paoQiAdapter_1ClickListener) {
        this.paoQiAdapter_1ClickListener = paoQiAdapter_1ClickListener;
    }


    public PaoQiList_New_Adapter(Context context) {
        this.context = context;

    }

    @Override
    protected PaoQiListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new PaoQiListHolder(inflate(parent, R.layout.paoqilist_item));
    }


    class PaoQiListHolder extends BaseHolder<HomeTab1Bean.ShopBean> {

        TextView tv_name, paoqi_move;
        RecyclerView recycler_paoqi_item;
        PaoQiItem_New_Adapter paoQiAdapter3;
        RelativeLayout title_bg_relayout;
        //        ImageView paoqi_title_bg;
        ImageView paoqi_title_bg2;


        PaoQiListHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.title_bg_relayout);
            tv_name = itemView.findViewById(R.id.paoqi_title);
            paoqi_move = itemView.findViewById(R.id.paoqi_move);
            recycler_paoqi_item = itemView.findViewById(R.id.recycler_paoqi_item);
//            paoqi_title_bg = itemView.findViewById(R.id.paoqi_title_bg);
            paoqi_title_bg2 = itemView.findViewById(R.id.paoqi_title_bg2);

        }

        @Override
        public void bindData(HomeTab1Bean.ShopBean data) {
//            Glide.with(context).load(data.getImage().getImages()).into(paoqi_title_bg);
            Glide.with(context).load(data.getImage().getPhoto()).into(paoqi_title_bg2);
            if (data.getImage().getPhoto().equals("")) {
                paoqi_title_bg2.setVisibility(View.GONE);
            } else {
                paoqi_title_bg2.setVisibility(View.VISIBLE);
            }
            paoqi_title_bg2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //type--1：商品详情；2：活动详情；3：url;4:文字；5：商品列表
//                    if (data.getImage().getType().equals("1")) {//1：商品详情
////                        MoveAbooutActivity_1.startSelf((Activity) context, data.getImage().getList().getGoods_id(), data.getImage().getList().getSearch_attr());
//                        MoveAbooutActivity_3.startSelf((Activity) context, data.getImage().getList().getGoods_id(), data.getImage().getList().getSearch_attr());
//
//                    } else if (data.getImage().getType().equals("2")) {//2：活动详情
//                        ProductDetailsActivity.startSelf(context, data.getImage().getList().getActive_id());//进入活动详情页面
//                    } else if (data.getImage().getType().equals("3")) {//3：url
//                        WebActivity.startSelf(context, data.getImage().getList().getUrl());
//
//                    } else if (data.getImage().getType().equals("4")) {//4：文字
//
//                    } else if (data.getImage().getType().equals("5")) {//5：广告商品列表
////                        RecommendActivity_1.startSelf(context, "", data.getImage().getList().getId());//进入商品列表
//                        HomePublicClassActivity.startSelf(context, "", "0", "", "", "", data.getImage().getList().getId());
//
//                    } else if (data.getImage().getType().equals("6")) {//6：会员入口
////                        MemberActivity.startSelf(context, "2");//
//                        MemberActivity_1.startSelf(context, "2");//会员购买入口
//                    }

                    HomePublicClassActivity.startSelf(context, data.getTitle(), "0", "", "", String.valueOf(data.getId()), "");


                }
            });


            List<HomeTab1Bean.ShopBean.DataBean> getData = data.getData();
            if (paoQiAdapter3 == null) {
                paoQiAdapter3 = new PaoQiItem_New_Adapter(context, data.getData());
                final GridLayoutManager manager3 = new GridLayoutManager(context, 1);
                manager3.setOrientation(GridLayoutManager.HORIZONTAL);
                recycler_paoqi_item.setLayoutManager(manager3);
                recycler_paoqi_item.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(context, 0)));
                recycler_paoqi_item.addItemDecoration(new SpaceItemDecoration(10, 10));
                recycler_paoqi_item.setAdapter(paoQiAdapter3);
                paoQiAdapter3.setTitle(data.getTitle(), data.getId());
            } else {
                paoQiAdapter3.setData(getData);
                paoQiAdapter3.setTitle(data.getTitle(), data.getId());
            }
//            paoQiAdapter3.addOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view, int position) {
////                    MoveAbooutActivity_1.startSelf((Activity) context, getData.get(position).getGoods_id(), getData.get(position).getSearch_attr());
//                    MoveAbooutActivity_3.startSelf((Activity) context, paoQiAdapter3.getData().get(position).getGoods_id(), paoQiAdapter3.getData().get(position).getSearch_attr());
//
//                }
//            });


        }
    }


    public class PaoQiItem_New_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        Context context;
        List<HomeTab1Bean.ShopBean.DataBean> getDatas;
        private static final int ITEM_FOOT = 4;
        String title;
        int titleId;

        public PaoQiItem_New_Adapter(Context context, List<HomeTab1Bean.ShopBean.DataBean> getData) {
            this.context = context;
            this.getDatas = getData;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == ITEM_FOOT) {
                View view = LayoutInflater.from(context).inflate(R.layout.discount_item_1, parent, false);
                return new FootViewHolder(view);
            }
            View view = LayoutInflater.from(context).inflate(R.layout.ripaolist_new_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof FootViewHolder) {
                ((FootViewHolder) holder).more_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(context, "点击查看更多", Toast.LENGTH_SHORT).show();
                        HomePublicClassActivity.startSelf(context, title, "0", "", "", String.valueOf(titleId), "");
                    }
                });
            } else if (holder instanceof ViewHolder) {

                Matrix matrix = new Matrix();           //创建一个单位矩阵
                matrix.setTranslate(0, 0);          //平移x和y各100单位
                matrix.preRotate(0);                   //顺时针旋转30度
                ((ViewHolder) holder).paoqi_item_img.setScaleType(ImageView.ScaleType.MATRIX);
                ((ViewHolder) holder).paoqi_item_img.setImageMatrix(matrix);
                ImageLoader.image(context, ((ViewHolder) holder).paoqi_item_img, getDatas.get(position).getGoods_thumb());
                ((ViewHolder) holder).paoqi_item_name.setText(getDatas.get(position).getBrand_name());
                if (getDatas.get(position).getIs_promote() == 0) {
                    ((ViewHolder) holder).paoqi_item_price.setText(Html.fromHtml("&yen") + getDatas.get(position).getProduct_price());
                } else if (getDatas.get(position).getIs_promote() == 1) {
                    ((ViewHolder) holder).paoqi_item_price.setText(Html.fromHtml("&yen") + getDatas.get(position).getPreferential_price());
                }
                ((ViewHolder) holder).discount_item_name.setText(getDatas.get(position).getGoods_name());

                ((ViewHolder) holder).paoqi_item_layout_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MoveAbooutActivity_3.startSelf((Activity) context, getDatas.get(position).getGoods_id(), getDatas.get(position).getSearch_attr());
                    }
                });

            }

        }

        @Override
        public int getItemCount() {
            return getDatas.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (isFootView(position)) {
                return ITEM_FOOT;
            }
            return super.getItemViewType(position);
        }

        public boolean isFootView(int position) {
            return position >= getDatas.size();
        }

        public void setData(List<HomeTab1Bean.ShopBean.DataBean> getData) {
            this.getDatas = getData;
            notifyDataSetChanged();
        }

        public void setTitle(String title, int id) {
            this.title = title;
            this.titleId = id;
        }

        class FootViewHolder extends RecyclerView.ViewHolder {
            LinearLayout more_layout_btn;

            public FootViewHolder(View itemView) {
                super(itemView);
                more_layout_btn = itemView.findViewById(R.id.more_layout_btn);
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView paoqi_item_img;
            AutoSplitTextView paoqi_item_name;
            TextView paoqi_item_price;
            TextView discount_item_name;
            LinearLayout paoqi_item_layout_btn;

            public ViewHolder(View itemView) {
                super(itemView);
                paoqi_item_img = itemView.findViewById(R.id.paoqi_item_img);
                paoqi_item_name = itemView.findViewById(R.id.paoqi_item_name);
                paoqi_item_price = itemView.findViewById(R.id.paoqi_item_price);
                discount_item_name = itemView.findViewById(R.id.discount_item_name);
                paoqi_item_layout_btn = itemView.findViewById(R.id.paoqi_item_layout_btn);
            }
        }

    }


    public interface PaoQiAdapter_1ClickListener {
        void onGoClickListener(HomeDataBean.ShopBean.DataBean data, String getGoods_id, String getSearch_attr);

    }

}








