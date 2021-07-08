package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/7/8$
 * @Author:pk$
 */
public class GuangImagAdapter extends BaseStateAdapter5<MoveDataBean.ArticleListBean, GuangImagAdapter.GuangImagHolder> {

    Context context;
    int type;

    public GuangImagAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    protected GuangImagHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new GuangImagHolder(inflate(parent, R.layout.move_guang_item));
    }

    public void setType(int type) {
        this.type = type;
    }

    class GuangImagHolder extends BaseHolder<MoveDataBean.ArticleListBean> {
        ImageView img;

        GuangImagHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
        }

        @Override
        public void bindData(MoveDataBean.ArticleListBean data) {

//            Matrix matrix = new Matrix();           //创建一个单位矩阵
//            matrix.setTranslate(0, 0);          //平移x和y各100单位
//            matrix.preRotate(0);                   //顺时针旋转30度
//            img.setScaleType(ImageView.ScaleType.MATRIX);
//            img.setImageMatrix(matrix);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(context, img, data.getImage());


        }
    }
}

