package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.vinnlook.www.R;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.viewholder.ImageHolder;
import com.vinnlook.www.surface.viewholder.VideoHolder;
import com.vinnlook.www.utils.ImageLoader;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * 自定义布局,多个不同UI切换
 */
public class MultipleTypesAdapter extends BannerAdapter<MoveDataBean.InfoBean.BannerBean, RecyclerView.ViewHolder> {
    private Context context;
    List<MoveDataBean.InfoBean.BannerBean> mBanData;
    String product_price;
    String imgUrl;

    public MultipleTypesAdapter(Context context, List<MoveDataBean.InfoBean.BannerBean> mDatas, String product_prices, String imgUrls) {
        super(mDatas);
        this.context = context;
        mBanData = mDatas;
        Log.e("MultipleTypesAdapter", "===product_prices===" + product_prices);
        product_price = product_prices;
        imgUrl = imgUrls;

    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
            case 2:
                return new VideoHolder(BannerUtils.getView(parent, R.layout.banner_video));
        }
        return new ImageHolder(BannerUtils.getView(parent, R.layout.banner_image));
    }

    @Override
    public int getItemViewType(int position) {
        return getData(getRealPosition(position)).getType();

    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder, MoveDataBean.InfoBean.BannerBean data, int position, int size) {
        int viewType = holder.getItemViewType();
        Log.e("onBindView", "viewType====" + viewType);
        switch (viewType) {
            case 1:
                ImageHolder imageHolder = (ImageHolder) holder;
//                imageHolder.imageView.setImageResource(data.getUrl());
                Matrix matrix = new Matrix();           //创建一个单位矩阵
                matrix.setTranslate(0, 0);          //平移x和y各100单位
                matrix.preRotate(0);                   //顺时针旋转30度
                imageHolder.imageView.setScaleType(ImageView.ScaleType.MATRIX);
                imageHolder.imageView.setImageMatrix(matrix);
//                imageHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.image(context, imageHolder.imageView, data.getUrl());


                if (product_price != null && !product_price.equals("")) {
                    imageHolder.price.setText(Html.fromHtml("&yen") + product_price);
                }
                if (imgUrl != null && !imgUrl.equals("")) {
                    ImageLoader.image(context, imageHolder.imageView1, imgUrl);
                    if (position == 0) {
                        imageHolder.imageView1.setVisibility(View.VISIBLE);
                        imageHolder.price.setVisibility(View.VISIBLE);
                    } else {
                        imageHolder.imageView1.setVisibility(View.GONE);
                        imageHolder.price.setVisibility(View.GONE);
                    }
                } else {
                    imageHolder.imageView1.setVisibility(View.GONE);
                    imageHolder.price.setVisibility(View.GONE);
                }


                break;
            case 2:
                VideoHolder videoHolder = (VideoHolder) holder;
                videoHolder.player.setUp(data.getUrl(), true, null);
                videoHolder.player.getBackButton().setVisibility(View.GONE);
//                //增加封面
                ImageView imageView = new ImageView(context);
                Matrix matrix1 = new Matrix();           //创建一个单位矩阵
                matrix1.setTranslate(0, 0);          //平移x和y各100单位
                matrix1.preRotate(0);                   //顺时针旋转30度
                imageView.setScaleType(ImageView.ScaleType.MATRIX);
                imageView.setImageMatrix(matrix1);
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.image(context, imageView, mBanData.get(1).getUrl());
                videoHolder.player.setThumbImageView(imageView);
                videoHolder.player.startPlayLogic();
                videoHolder.player.seekTo(1);//**调整从第一毫秒进行播放**
//                videoHolder.player.setZOrderOnTop(true);
                break;


        }
    }


}
