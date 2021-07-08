package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.luck.picture.lib.tools.ScreenUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GuangThemBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/6/28$
 * @Author:pk$
 */
public class Guang_1_1Adapter extends BaseStateAdapter5<GuangThemBean.ItemBean.ListBeanX, Guang_1_1Adapter.Guang_1_1Holder> {

    Context context;
    int type;


    public Guang_1_1Adapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    protected Guang_1_1Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new Guang_1_1Holder(inflate(parent, R.layout.guang_1_1item));

    }

    public void setType(int type) {
        this.type = type;
    }


    class Guang_1_1Holder extends BaseHolder<GuangThemBean.ItemBean.ListBeanX> {
        RoundedImageView img;

        Guang_1_1Holder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }

        @Override
        public void bindData(GuangThemBean.ItemBean.ListBeanX data) {
            Log.e("主题乐园", "===type===" + type);
            if (type == 1) {
                //获取item宽度，计算图片等比例缩放后的高度，为imageView设置参数
                ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
                float itemWidth = (ScreenUtils.getScreenWidth(context) + 25) / 2;
                layoutParams.width = (int) itemWidth;
                float scale = (itemWidth + 0f) / data.getWidth();
                layoutParams.height = (int) (data.getHeight() * scale);

                Log.e("主题乐园", "==111=宽高=width==" + layoutParams.width);
                Log.e("主题乐园", "==111=宽高=height==" + layoutParams.height);
                img.setLayoutParams(layoutParams);
                ImageLoader.image(context, img, data.getHeight_image());
            } else {
                Matrix matrix = new Matrix();           //创建一个单位矩阵
                matrix.setTranslate(0, 0);          //平移x和y各100单位
                matrix.preRotate(0);                   //顺时针旋转30度
                img.setScaleType(ImageView.ScaleType.MATRIX);
                img.setImageMatrix(matrix);

                ImageLoader.image(context, img, data.getImage());
            }


        }
    }
}

