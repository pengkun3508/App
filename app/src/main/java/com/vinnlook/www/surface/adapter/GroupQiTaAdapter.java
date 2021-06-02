package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GroupDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/5/12$
 * @Author:pk$
 */
public class GroupQiTaAdapter extends BaseStateAdapter<GroupDetailsBean.GroupListBean, GroupQiTaAdapter.GroupQiTaHolder> {

    Context context;
    String hourss;
    String minutess;
    String secondss;

    public GroupQiTaAdapter(Context context) {
        this.context = context;
    }

    @Override
    protected GroupQiTaHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new GroupQiTaHolder(inflate(parent, R.layout.group_qita_item));
    }

    public class GroupQiTaHolder extends BaseHolder<GroupDetailsBean.GroupListBean> {
        ImageView group_item_qita_image;
        TextView group_item_qita_title, group_item_qita_price, group_item_qita_number, group_item_qita_hour,
                group_item_qita_min, group_item_qita_second;

        GroupQiTaHolder(View itemView) {
            super(itemView);
            group_item_qita_image = itemView.findViewById(R.id.group_item_qita_image);
            group_item_qita_title = itemView.findViewById(R.id.group_item_qita_title);
            group_item_qita_price = itemView.findViewById(R.id.group_item_qita_price);
            group_item_qita_number = itemView.findViewById(R.id.group_item_qita_number);
            group_item_qita_hour = itemView.findViewById(R.id.group_item_qita_hour);
            group_item_qita_min = itemView.findViewById(R.id.group_item_qita_min);
            group_item_qita_second = itemView.findViewById(R.id.group_item_qita_second);

        }

        @Override
        public void bindData(GroupDetailsBean.GroupListBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            group_item_qita_image.setScaleType(ImageView.ScaleType.MATRIX);
            group_item_qita_image.setImageMatrix(matrix);
            ImageLoader.image(context, group_item_qita_image, data.getGoods_thumb());

            group_item_qita_title.setText(data.getGoods_name());
            group_item_qita_price.setText(Html.fromHtml("&yen") + data.getGroup_price());
            group_item_qita_number.setText("已拼" + data.getVirtual_sales() + "件");
            long hours = data.getStill_time() / (60 * 60);
            long minutes = (data.getStill_time() / 60) % 60;
            long seconds = data.getStill_time() % 60;
            String hourss = String.valueOf(hours);
            minutess = String.valueOf(minutes);
            secondss = String.valueOf(seconds);

            if (hours < 10) {
                hourss = "0" + hours;
            }
            if (minutes < 10) {
                minutess = "0" + minutes;
            }
            if (seconds < 10) {
                secondss = "0" + seconds;
            }
//            Log.e("倒计时--列表", "seconds====" + secondss);

            group_item_qita_hour.setText(hourss);
            group_item_qita_min.setText(minutess);
            group_item_qita_second.setText(secondss);

        }
    }


}
