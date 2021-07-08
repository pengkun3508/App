package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GuangSelectBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.AutoSplitTextView;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public class Guang_2Adapter extends BaseStateAdapter<GuangSelectBean.ListBean, Guang_2Adapter.Guang_2Holder> {

    Context context;

    OnItemClickListener onItemClickListener;


    public Guang_2Adapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    protected Guang_2Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new Guang_2Holder(inflate(parent, R.layout.guang_2_item));
    }

    public void setOnAllClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemTitleClicked(int type, String name);

        void onItemDataClicked(int type, String iD);
    }

    class Guang_2Holder extends BaseHolder<GuangSelectBean.ListBean> {
        ImageView select_item_img, select_item_zan_img;
        AutoSplitTextView select_item_goodsname;
        RoundedImageView select_item_hear;
        TextView select_item_name, select_item_zan_number;
        LinearLayout select_item_layout;

        Guang_2Holder(View itemView) {
            super(itemView);
            select_item_img = itemView.findViewById(R.id.select_item_img);
            select_item_goodsname = itemView.findViewById(R.id.select_item_goodsname);
            select_item_hear = itemView.findViewById(R.id.select_item_hear);
            select_item_name = itemView.findViewById(R.id.select_item_name);
            select_item_zan_img = itemView.findViewById(R.id.select_item_zan_img);
            select_item_zan_number = itemView.findViewById(R.id.select_item_zan_number);
            select_item_layout = itemView.findViewById(R.id.select_item_layout);
        }

        @Override
        public void bindData(GuangSelectBean.ListBean data) {




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
//            if (getAdapterPosition() % 2 == 0) {
                select_item_img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            } else {
            select_item_layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            }



        }
    }
}

