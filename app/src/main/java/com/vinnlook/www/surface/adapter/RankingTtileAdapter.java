package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.RankingTabBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/12/23$
 * @Author:pk$
 */
public class RankingTtileAdapter extends BaseStateAdapter<RankingTabBean, RankingTtileAdapter.RankingTtileHolder> {

    Context context;
    int defItem;
    int index;

    View itemViews;

    public RankingTtileAdapter(Context context) {
        this.context = context;
    }


    @Override
    protected RankingTtileHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new RankingTtileHolder(inflate(parent, R.layout.rank_title_item));
    }

    public void setPosion(int indexs) {
        index = indexs;
    }


    class RankingTtileHolder extends BaseHolder<RankingTabBean> {

        TextView rank_title_text, rank_title_text2;
        LinearLayout item_rank_layout;
        ImageView title_img;


        RankingTtileHolder(View itemView) {
            super(itemView);
            rank_title_text = itemView.findViewById(R.id.rank_title_text);
            rank_title_text2 = itemView.findViewById(R.id.rank_title_text2);
            item_rank_layout = itemView.findViewById(R.id.item_rank_layout);
            title_img = itemView.findViewById(R.id.title_img);
        }

        @Override
        public void bindData(RankingTabBean data) {
//            rank_title_text.setText(data.getName());
            rank_title_text2.setText(data.getE_name());

//            if (index == getAdapterPosition()) {
//                rank_title_text.setTextSize(14);
//                item_rank_layout.setPadding(15, 5, 15, 0);
//            } else {
//                rank_title_text.setTextSize(11);
//                item_rank_layout.setPadding(15, 10, 15, 5);
//            }

            Log.e("bindData", "===index===" + index);
            Log.e("getAdapterPosition", "===getAdapterPosition===" + getAdapterPosition());

            //获屏幕宽度
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
            int height = dm.widthPixels / 5;
            Log.e("高度：", "height======" + dm.widthPixels);
            Log.e("高度：", "height===/4===" + height);

            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) title_img.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.width = height;// 控件的高强制设成20
//            linearParams.height = height;// 控件的高强制设成20
            title_img.setLayoutParams(linearParams); //使设置好的布局参数应用到控件

            if (index == getAdapterPosition()) {
                ImageLoader.image(context, title_img, data.getPitch_image());
//                Glide.with(context).load(data.getPitch_image())
//                        .apply(new RequestOptions()
//                                .centerCrop()
//                        .override(height+10,35))
//                        .into(title_img);

                LinearLayout.LayoutParams linearParams1 = (LinearLayout.LayoutParams) title_img.getLayoutParams(); //取控件textView当前的布局参数
                linearParams.width = height + 20;// 控件的高强制设成20
//            linearParams.height = height;// 控件的高强制设成20
                title_img.setLayoutParams(linearParams1); //使设置好的布局参数应用到控件
            } else {
                ImageLoader.image(context, title_img, data.getS_image());
//                Glide.with(context).load(data.getPitch_image())
//                        .apply(new RequestOptions()
//                                .centerCrop()
//                                .override(height,25))
//                        .into(title_img);


                LinearLayout.LayoutParams linearParams2 = (LinearLayout.LayoutParams) title_img.getLayoutParams(); //取控件textView当前的布局参数
                linearParams.width = height;// 控件的高强制设成20
//            linearParams.height = height;// 控件的高强制设成20
                title_img.setLayoutParams(linearParams2); //使设置好的布局参数应用到控件

            }


        }






    }

}
