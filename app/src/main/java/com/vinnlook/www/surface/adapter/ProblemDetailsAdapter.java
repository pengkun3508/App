package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ProblemDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/11/11$
 * @Author:pk$
 */
public class ProblemDetailsAdapter extends BaseStateAdapter<ProblemDetailsBean.ListBean, ProblemDetailsAdapter.ProblemDetailsHolder> {
    Context context;



    ProblemZanClickListener problemZanClickListener;

    public void setProblemZanClickListener(ProblemZanClickListener problemZanClickListener) {
        this.problemZanClickListener = problemZanClickListener;
    }


    public ProblemDetailsAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected ProblemDetailsHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ProblemDetailsHolder(inflate(parent, R.layout.problem_details_item));
    }



    class ProblemDetailsHolder extends BaseHolder<ProblemDetailsBean.ListBean> {
        RoundedImageView wen_img_head;
        TextView wen_name, wen_content, wen_time, wen_zan_number;
        LinearLayout wen_zan_layout;
        ImageView wen_zan_img;

        ProblemDetailsHolder(View itemView) {
            super(itemView);

            wen_img_head = itemView.findViewById(R.id.wen_img_head);//头像
            wen_name = itemView.findViewById(R.id.wen_name);//昵称
            wen_content = itemView.findViewById(R.id.wen_content);//内容
            wen_time = itemView.findViewById(R.id.wen_time);//时间
            wen_zan_layout = itemView.findViewById(R.id.wen_zan_layout);//点赞布局
            wen_zan_number = itemView.findViewById(R.id.wen_zan_number);//点赞数量
            wen_zan_img = itemView.findViewById(R.id.wen_zan_img);//点赞图标

        }

        @Override
        public void bindData(ProblemDetailsBean.ListBean data) {
//            Matrix matrix = new Matrix();           //创建一个单位矩阵
//            matrix.setTranslate(0, 0);          //平移x和y各100单位
//            matrix.preRotate(0);                   //顺时针旋转30度
//            wen_img_head.setScaleType(ImageView.ScaleType.MATRIX);
//            wen_img_head.setImageMatrix(matrix);
            wen_img_head.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.userIcon(context, wen_img_head, data.getImg_url());

            wen_name.setText(data.getUser_name());
            wen_content.setText(data.getContent());
            wen_time.setText(data.getCreate_time());
            wen_zan_number.setText(data.getPraise_count());

            //没有点赞
            if (data.getIs_praise() == 0) {
                wen_zan_img.setBackground(context.getResources().getDrawable(R.mipmap.wen_zan_no));
                //已点赞
            } else if (data.getIs_praise() == 1) {
                wen_zan_img.setBackground(context.getResources().getDrawable(R.mipmap.wen_zan_yes));
            }


            wen_zan_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //没有点赞
                    if (data.getIs_praise() == 0) {
                        problemZanClickListener.onGoClickListener(data.getId(), "1",getAdapterPosition());
                        //已点赞
                    } else if (data.getIs_praise() == 1) {
                        problemZanClickListener.onGoClickListener(data.getId(), "2", getAdapterPosition());
                    }

                }
            });


        }


    }


    public interface ProblemZanClickListener {
        void onGoClickListener(String iD,String type,int position);

    }
}
