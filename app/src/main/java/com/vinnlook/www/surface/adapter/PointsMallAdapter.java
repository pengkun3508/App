package com.vinnlook.www.surface.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.PointsMallActivity;
import com.vinnlook.www.surface.bean.PointsMallBean;

/**
 * @Description:
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class PointsMallAdapter extends BaseStateAdapter<PointsMallBean.DiscountBean, PointsMallAdapter.PointsMallHolder> {


    private OnItemReceiveClickListener mOnItemClickListener;

    public PointsMallAdapter(OnItemReceiveClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemReceiveClickListener {
        void onItemClicked(View view, int position);//领取优惠券

    }


    @Override
    protected PointsMallHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new PointsMallHolder(inflate(parent, R.layout.points_mall_item));
    }


    public class PointsMallHolder extends BaseHolder<PointsMallBean.DiscountBean> {
        TextView points_text, points_text_1, points_text_2, points_text_btn_3;


        PointsMallHolder(View itemView) {
            super(itemView);
            points_text = getView(R.id.points_text);//
            points_text_1 = getView(R.id.points_text_1);//
            points_text_2 = getView(R.id.points_text_2);//
            points_text_btn_3 = getView(R.id.points_text_btn_3);//


        }

        @Override
        public void bindData(PointsMallBean.DiscountBean data) {

            if (data.getType().equals("1")) {//折扣券
                points_text_1.setText(data.getReduced());
                points_text.setText("折");
            } else if (data.getType().equals("2")) {//优惠券
                points_text_1.setText(data.getReduced());
                points_text.setText("元");
            }
            points_text_2.setText(data.getPoint() + "积分");


            points_text_btn_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        int position = getAdapterPosition();
                        //确保position值有效
                        if (position != RecyclerView.NO_POSITION) {
                            mOnItemClickListener.onItemClicked(view, position);
                        }
                    }

                }
            });


        }
    }

}
