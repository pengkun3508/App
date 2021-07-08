package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.luck.picture.lib.tools.ScreenUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.SelectEyeChartActivity;
import com.vinnlook.www.surface.bean.GuangSelectBean;
import com.vinnlook.www.utils.AutoSplitTextView;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2021/7/6$
 * @Author:pk$
 */
public class Guang_2_2Adapter extends RecyclerView.Adapter<Guang_2_2Adapter.BaseViewHolder> {
    static Context context;
    OnItemClickListener onItemClickListener;
    private List<GuangSelectBean.ListBean> dataList = new ArrayList<>();

    public Guang_2_2Adapter(Context context) {
        this.context = context;
    }

    public void replaceAll(List<GuangSelectBean.ListBean> list) {
        dataList.clear();
        if (list != null && list.size() > 0) {
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    /**
     * 插入数据使用notifyItemInserted，如果要使用插入动画，必须使用notifyItemInserted
     * 才会有效果。即便不需要使用插入动画，也建议使用notifyItemInserted方式添加数据，
     * 不然容易出现闪动和间距错乱的问题
     */
    public void addData(int position, List<GuangSelectBean.ListBean> list) {
        dataList.addAll(position, list);
        notifyItemInserted(position);
    }

    //移除数据使用notifyItemRemoved
    public void removeData(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.guang_2_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(dataList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public void setOnAllClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int type, String name);

    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        void setData(GuangSelectBean.ListBean data, int position) {

        }
    }

    private class OneViewHolder extends BaseViewHolder {
        ImageView select_item_img, select_item_zan_img;
        AutoSplitTextView select_item_goodsname;
        RoundedImageView select_item_hear;
        TextView select_item_name, select_item_zan_number;
        LinearLayout select_item_layout;

        public OneViewHolder(View view) {
            super(view);
            select_item_img = itemView.findViewById(R.id.select_item_img);
            select_item_goodsname = itemView.findViewById(R.id.select_item_goodsname);
            select_item_hear = itemView.findViewById(R.id.select_item_hear);
            select_item_name = itemView.findViewById(R.id.select_item_name);
            select_item_zan_img = itemView.findViewById(R.id.select_item_zan_img);
            select_item_zan_number = itemView.findViewById(R.id.select_item_zan_number);
            select_item_layout = itemView.findViewById(R.id.select_item_layout);
        }

        @Override
        void setData(GuangSelectBean.ListBean data, int position) {
            if (data != null) {
//                ivImage.setImageResource(id);
                Matrix matrix = new Matrix();           //创建一个单位矩阵
                matrix.setTranslate(0, 0);          //平移x和y各100单位
                matrix.preRotate(0);                   //顺时针旋转30度
                select_item_img.setScaleType(ImageView.ScaleType.MATRIX);
                select_item_img.setImageMatrix(matrix);

                ImageLoader.image(context, select_item_img, data.getImage());
                select_item_goodsname.setText(data.getName());

                ImageLoader.userIcon(context, select_item_hear, data.getImg_url());
                select_item_name.setText(data.getUser_name());
                select_item_zan_number.setText(data.getLike_num());
                if (data.getIs_like() == 1) {//已点赞
                    select_item_zan_img.setBackgroundResource(R.mipmap.article_zan_icon_1);
                } else {
                    select_item_zan_img.setBackgroundResource(R.mipmap.article_zan_icon);
                }


                //需要Item高度不同才能出现瀑布流的效果，此处简单粗暴地设置一下高度

//                int biLi = data.getWidth() / data.getHeight();
//                int height = select_item_img.getWidth() / biLi;
//                Log.e("精选眼图", "==111=宽高=biLi==" + biLi);
//                Log.e("精选眼图", "==111=宽高=height==" + height);
//                select_item_img.setLayoutParams(new LinearLayout.LayoutParams(data.getWidth(), data.getHeight()));

                //获取item宽度，计算图片等比例缩放后的高度，为imageView设置参数
                ViewGroup.LayoutParams layoutParams = select_item_img.getLayoutParams();
                float itemWidth = (ScreenUtils.getScreenWidth(context)+25) / 2;
                layoutParams.width = (int) itemWidth;
                float scale = (itemWidth + 0f) / data.getWidth();
                layoutParams.height = (int) (data.getHeight() * scale);

                Log.e("精选眼图", "==111=宽高=width==" + layoutParams.width);
                Log.e("精选眼图", "==111=宽高=height==" + layoutParams.height);
                select_item_img.setLayoutParams(layoutParams);


//                select_item_img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
//                if (position % 2 == 0) {
//                    select_item_img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                } else {
//                    select_item_img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.WRAP_CONTENT));
//                }
                select_item_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        onItemClickListener.onItemClickListener(data.getId());
                        SelectEyeChartActivity.startSelf(context, data.getId());

                    }
                });

            }


        }

    }


}
