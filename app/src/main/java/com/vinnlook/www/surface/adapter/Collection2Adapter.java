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
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.CollectionList2Bean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/7/8$
 * @Author:pk$
 */
public class Collection2Adapter extends BaseStateAdapter<CollectionList2Bean.ListBean, Collection2Adapter.Collection2Holder> {

    Context context;

    public Collection2Adapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected Collection2Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new Collection2Holder(inflate(parent, R.layout.collection_2item));
    }

    class Collection2Holder extends BaseHolder<CollectionList2Bean.ListBean> {

        RoundedImageView collec_img;
        TextView collec_name;

        Collection2Holder(View itemView) {
            super(itemView);
            collec_img = getView(R.id.collec_img);
            collec_name = getView(R.id.collec_name);

        }

        @Override
        public void bindData(CollectionList2Bean.ListBean data) {
            collec_name.setText(data.getName());

//            Matrix matrix = new Matrix();           //创建一个单位矩阵
//            matrix.setTranslate(0, 0);          //平移x和y各100单位
//            matrix.preRotate(0);                   //顺时针旋转30度
//            collec_img.setScaleType(ImageView.ScaleType.MATRIX);
//            collec_img.setImageMatrix(matrix);
            collec_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageLoader.image(collec_img.getContext(), collec_img, data.getImage());

        }


    }
}
