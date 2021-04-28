package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.RankingTabBean;

/**
 * @Description:
 * @Time:2020/12/23$
 * @Author:pk$
 */
public class RankingTtileAdapter1 extends BaseStateAdapter<RankingTabBean, RankingTtileAdapter1.RankingTtileHolder> {

    Context context;
    int defItem;
    int index;

    View itemViews;

    public RankingTtileAdapter1(Context context) {
        this.context = context;
    }


    @Override
    protected RankingTtileHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new RankingTtileHolder(inflate(parent, R.layout.rank_title_item_1));
    }

    public void setPosion(int indexs) {
        index = indexs;
    }


    class RankingTtileHolder extends BaseHolder<RankingTabBean> {

        TextView name_text, label_text;
        RelativeLayout item_rank_layout;


        RankingTtileHolder(View itemView) {
            super(itemView);
            name_text = itemView.findViewById(R.id.name_text);
            label_text = itemView.findViewById(R.id.label_text);
            item_rank_layout = itemView.findViewById(R.id.item_rank_layout);
        }

        @Override
        public void bindData(RankingTabBean data) {
            name_text.setText(data.getName());
            label_text.setText(data.getE_name());
            if (index == getAdapterPosition()) {
                item_rank_layout.setBackgroundDrawable(item_rank_layout.getContext().getResources().getDrawable(R.drawable.rebang_item_bg));
                name_text.setTextColor(context.getResources().getColor(R.color.black));
                label_text.setTextColor(context.getResources().getColor(R.color.black));
            } else {
                item_rank_layout.setBackgroundDrawable(item_rank_layout.getContext().getResources().getDrawable(R.drawable.rebang_item_bg_1));
                name_text.setTextColor(context.getResources().getColor(R.color.white));
                label_text.setTextColor(context.getResources().getColor(R.color.white));
            }


            if (getData().size() < 5) {
                //获屏幕宽度
                DisplayMetrics dm = new DisplayMetrics();
                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
                int height = dm.widthPixels / 5;
                Log.e("高度：", "height======" + dm.widthPixels);
                Log.e("高度：", "height===/4===" + height);

                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) item_rank_layout.getLayoutParams(); //取控件textView当前的布局参数
                linearParams.width = height;// 控件的高强制设成20
//            linearParams.height = height;// 控件的高强制设成20
                item_rank_layout.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
            }


        }


    }


}
