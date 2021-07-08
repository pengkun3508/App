package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.LimitedActivity;
import com.vinnlook.www.surface.activity.LimitedActivity_1;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.utils.AutoSplitTextView;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

/**
 * @Description:
 * @Time:2021/4/6$
 * @Author:pk$
 */
public class Discount_Adapter_1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<HomeTab1Bean.DiscountBean.ListBeanXX> list;
    private static final int ITEM_FOOT = 4;
    private OnItemClickListener onItemClickListener;

    public Discount_Adapter_1(Context context, List<HomeTab1Bean.DiscountBean.ListBeanXX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_FOOT) {
            View view = LayoutInflater.from(context).inflate(R.layout.discount_item_1, parent, false);
            return new FootViewHolder(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.discount_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FootViewHolder) {

            ((FootViewHolder) holder).more_layout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//查看更多
//                    Toast.makeText(context, "点击查看更多", Toast.LENGTH_SHORT).show();
                    LimitedActivity_1.startSelf(context);
                }
            });

        } else if (holder instanceof ViewHolder) {
            holder.itemView.setTag(position);
            ((ViewHolder) holder).discount_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, ((ViewHolder) holder).discount_item_img, list.get(position).getGoods_thumb());
            ((ViewHolder) holder).discount_item_brend.setText(list.get(position).getBrand_name());
            ((ViewHolder) holder).discount_item_name.setText(list.get(position).getGoods_name());
            if (list.get(position).getIs_promote() == 0) {
                ((ViewHolder) holder).discount_item_price.setText(Html.fromHtml("&yen") + list.get(position).getProduct_price());
            } else if (list.get(position).getIs_promote() == 1) {
                ((ViewHolder) holder).discount_item_price.setText(Html.fromHtml("&yen") + list.get(position).getPreferential_price());
            }

            ((ViewHolder) holder).discount_item_layout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MoveAbooutActivity_3.startSelf((Activity) context, list.get(position).getGoods_id(), list.get(position).getSearch_attr(),"");
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFootView(position)) {
            return ITEM_FOOT;
        }
        return super.getItemViewType(position);
    }

    public boolean isFootView(int position) {
        return position >= list.size();
    }


    class FootViewHolder extends RecyclerView.ViewHolder {
        LinearLayout more_layout_btn;

        public FootViewHolder(View itemView) {
            super(itemView);
            more_layout_btn = itemView.findViewById(R.id.more_layout_btn);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView discount_item_img;
        TextView discount_item_name, discount_item_price, discount_item_go;
        AutoSplitTextView discount_item_brend;
        LinearLayout discount_item_layout_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            discount_item_img = itemView.findViewById(R.id.discount_item_img);
            discount_item_brend = itemView.findViewById(R.id.discount_item_brend);
            discount_item_name = itemView.findViewById(R.id.discount_item_name);
            discount_item_price = itemView.findViewById(R.id.discount_item_price);
            discount_item_go = itemView.findViewById(R.id.discount_item_go);
            discount_item_layout_btn = itemView.findViewById(R.id.discount_item_layout_btn);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
