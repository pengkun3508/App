package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/5/20$
 * @Author:pk$
 */
public class Title_New2_Adapter extends BaseStateAdapter<HomeTab1Bean.MenuBean, Title_New2_Adapter.Title_New2_Holder> {

    Context context;


    public Title_New2_Adapter(Context context) {
        this.context = context;

    }

    @Override
    protected Title_New2_Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new Title_New2_Holder(inflate(parent, R.layout.title_new_item));
    }


    class Title_New2_Holder extends BaseHolder<HomeTab1Bean.MenuBean> {

        ImageView title_img;
        TextView title_text;

        Title_New2_Holder(View itemView) {
            super(itemView);
            title_img = itemView.findViewById(R.id.title_img);
            title_text = itemView.findViewById(R.id.title_text);
        }

        @Override
        public void bindData(HomeTab1Bean.MenuBean data) {

            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            title_img.setScaleType(ImageView.ScaleType.MATRIX);
            title_img.setImageMatrix(matrix);
//            recomm_item_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, title_img, data.getImage());

            title_text.setText(data.getName());


        }
    }
}

