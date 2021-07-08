package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.flyco.roundview.RoundTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.ArticleDetailsActivity;
import com.vinnlook.www.surface.bean.ThemeDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

/**
 * @Description:
 * @Time:2021/7/1$
 * @Author:pk$
 */
public class ThemeDetailsAdapter_1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_COUNT = 10;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private final Context context;
    List<ThemeDetailsBean.ListBean> themeDetailsBean;
    String color;
    String content;
    String name;

    public ThemeDetailsAdapter_1(Context context, String color, String content, String name, List<ThemeDetailsBean.ListBean> themeDetailsBean) {
        this.context = context;
        this.color = color;
        this.content = content;
        this.name = name;
        this.themeDetailsBean = themeDetailsBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_header, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderHolder) {
            ((HeaderHolder) holder).theme_details_bgcolor.setBackgroundColor(Color.parseColor(color));
            ((HeaderHolder) holder).theme_details_title.setText(name);
            ((HeaderHolder) holder).theme_details_content.setText(content);

        } else if (holder instanceof ViewHolder) {
            holder.itemView.setTag(position);
            ((ViewHolder) holder).them_item_bgcolor.setBackgroundColor(Color.parseColor(color));
            ((ViewHolder) holder).theme_details_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            ((ViewHolder) holder).theme_details_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            ((ViewHolder) holder).theme_details_item_img.setImageMatrix(matrix);
            ImageLoader.image(context, ((ViewHolder) holder).theme_details_item_img, themeDetailsBean.get(position - 1).getImage());
            ((ViewHolder) holder).theme_details_item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArticleDetailsActivity.startSelf(context, themeDetailsBean.get(position - 1).getId());
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return themeDetailsBean.size() + 1;
//        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView theme_details_item_img;
        RelativeLayout them_item_bgcolor;

        public ViewHolder(View itemView) {
            super(itemView);
            theme_details_item_img = itemView.findViewById(R.id.theme_details_item_img);
            them_item_bgcolor = itemView.findViewById(R.id.them_item_bgcolor);
        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {
        TextView theme_details_title;
        RoundTextView theme_details_content;
        LinearLayout theme_details_bgcolor;

        public HeaderHolder(View itemView) {
            super(itemView);
            theme_details_title = itemView.findViewById(R.id.theme_details_title);
            theme_details_content = itemView.findViewById(R.id.theme_details_content);
            theme_details_bgcolor = itemView.findViewById(R.id.theme_details_bgcolor);
        }
    }
}
