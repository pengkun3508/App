package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HaiTaoClassBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/1/27$
 * @Author:pk$
 */
public class TitleList_Adapter extends BaseStateAdapter5<HaiTaoClassBean.TitleBean, TitleList_Adapter.TitleListHolder> {

    Context context;

    public TitleList_Adapter(Context context) {
        this.context = context;
    }

    @Override
    protected TitleListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new TitleListHolder(inflate(parent, R.layout.titlelist_item));
    }

    class TitleListHolder extends BaseHolder<HaiTaoClassBean.TitleBean> {

        ImageView img_title;
        TextView text_title;
        LinearLayout item_layout;

        TitleListHolder(View itemView) {
            super(itemView);
            img_title = getView(R.id.img_title);
            text_title = getView(R.id.text_title);
            item_layout = getView(R.id.item_layout);
        }

        @Override
        public void bindData(HaiTaoClassBean.TitleBean data) {
//            img_title.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(context).load(data.getBrand_logo()).into(tv_img);
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            img_title.setScaleType(ImageView.ScaleType.MATRIX);
            img_title.setImageMatrix(matrix);
            ImageLoader.image(context, img_title, data.getImage());
            text_title.setText(data.getName());

            //获屏幕宽度
            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
            int height = (int) dm.widthPixels / 7;

            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) img_title.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.width = height;// 控件的高强制设成20
            linearParams.height = height;// 控件的高强制设成20
            img_title.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }
    }
}

