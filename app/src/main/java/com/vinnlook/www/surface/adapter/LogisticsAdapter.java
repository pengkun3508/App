package com.vinnlook.www.surface.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.OrderLogisticsBean;

public class LogisticsAdapter extends BaseStateAdapter<OrderLogisticsBean.TracesBean, LogisticsAdapter.LogisticsHolder> {
    int mHeight;

    @Override
    protected LogisticsHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new LogisticsHolder(inflate(parent, R.layout.rv_item_logistics));
    }

    class LogisticsHolder extends BaseHolder<OrderLogisticsBean.TracesBean> {

        View view, view2;
        TextView item_time, item_content;
        LinearLayout content_layout;

        LogisticsHolder(View itemView) {
            super(itemView);
            view = getView(R.id.view);
            view2 = getView(R.id.view2);
            item_time = getView(R.id.item_time);
            item_content = getView(R.id.item_content);
            content_layout = getView(R.id.content_layout);
        }

        @Override
        public void bindData(OrderLogisticsBean.TracesBean data) {
            if (getAdapterPosition() == 0) {
                view.setVisibility(View.INVISIBLE);
            } else {
                view.setVisibility(View.VISIBLE);
            }

            if (getAdapterPosition() == getData().size() - 1) {
                view2.setVisibility(View.INVISIBLE);
            } else {
                view2.setVisibility(View.VISIBLE);
            }
            item_time.setText(data.getAcceptTime());
            item_content.setText(data.getAcceptStation());

            content_layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // TODO Auto-generated method stub
                    content_layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int height = content_layout.getMeasuredHeight();
                    Log.e("?????????", content_layout.getMeasuredHeight() - 67.5 + "," + content_layout.getMeasuredWidth());
                    RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view2.getLayoutParams(); //?????????textView?????????????????????
                    linearParams.height = height;// ????????????????????????20
                    view2.setLayoutParams(linearParams); //??????????????????????????????????????????
                }
            });


        }


    }
}