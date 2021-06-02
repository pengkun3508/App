package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GroupListBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/4/23$
 * @Author:pk$
 */
public class GroupWorkGoAdapter extends BaseStateAdapter5<GroupListBean.ListBean, GroupWorkGoAdapter.DiscountHolder> {

    Context context;

    public GroupWorkGoAdapter(Context context) {
        super();
        this.context = context;
    }

//    @Override
//    public int getItemCount() {
//        return 9;
//    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    protected DiscountHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new DiscountHolder(inflate(parent, R.layout.group_go_item));
    }


    class DiscountHolder extends BaseHolder<GroupListBean.ListBean> {
        ImageView xianshi_img;
        TextView xianshi_name, xianshi_group_num, xianshi_group_flag, xianshi_jiage, xianshi_yuanjia, home_add_xianshi_car;

        DiscountHolder(View itemView) {
            super(itemView);
            xianshi_img = itemView.findViewById(R.id.xianshi_img);
            xianshi_name = itemView.findViewById(R.id.xianshi_name);
            xianshi_group_num = itemView.findViewById(R.id.xianshi_group_num);
            xianshi_group_flag = itemView.findViewById(R.id.xianshi_group_flag);
            xianshi_jiage = itemView.findViewById(R.id.xianshi_jiage);
            xianshi_yuanjia = itemView.findViewById(R.id.xianshi_yuanjia);
            home_add_xianshi_car = itemView.findViewById(R.id.home_add_xianshi_car);


        }

        @Override
        public void bindData(GroupListBean.ListBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            xianshi_img.setScaleType(ImageView.ScaleType.MATRIX);
            xianshi_img.setImageMatrix(matrix);
            ImageLoader.image(context, xianshi_img, data.getGoods_thumb());

            xianshi_name.setText(data.getGoods_name());
            xianshi_group_num.setText(data.getGroup_people() + "人团");
            xianshi_jiage.setText(data.getGroup_price());
            xianshi_yuanjia.setText("单价卖" + Html.fromHtml("&yen") + data.getProduct_price());
            xianshi_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

            if (data.getIs_post_fee().equals("0")) {
                xianshi_group_flag.setVisibility(View.GONE);
            } else if (data.getIs_post_fee().equals("1")) {
                xianshi_group_flag.setVisibility(View.VISIBLE);
            }

            //计算秒杀倒计时---ms
//            endTime = Integer.valueOf(data.getEnd_time());
//            handler1.sendEmptyMessageDelayed(0, 1000);


        }


    }
}

