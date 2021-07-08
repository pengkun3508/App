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
import com.vinnlook.www.surface.bean.ArticleDetailsBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2021/7/2$
 * @Author:pk$
 */
public class ArticleDetailspopupAdapter extends BaseStateAdapter<ArticleDetailsBean.GoodsListBean, ArticleDetailspopupAdapter.ArticleDetailspopupHolder> {

    Context context;
    ApplyListAdapter.ApplyListClickListener applyListClickListener;

    public ArticleDetailspopupAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setApplyListClickListener(ApplyListAdapter.ApplyListClickListener applyListClickListener) {
        this.applyListClickListener = applyListClickListener;
    }

    @Override
    protected ArticleDetailspopupHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ArticleDetailspopupHolder(inflate(parent, R.layout.article_goods_item_1));
    }

    public interface ApplyListClickListener {
        void onApplyKeFuClickListener(int posion);//联系客服

        void onApplySeeDetailsClickListener(int posion);//联系客服

    }

    class ArticleDetailspopupHolder extends BaseHolder<ArticleDetailsBean.GoodsListBean> {
        ImageView article_goods_item_img;
        TextView article_goods_item_name;
        TextView article_goods_item_fuhao;
        TextView article_goods_item_jiage;


        ArticleDetailspopupHolder(View itemView) {
            super(itemView);
            article_goods_item_img = getView(R.id.article_goods_item_img);//图片
            article_goods_item_name = getView(R.id.article_goods_item_name);//商品名
            article_goods_item_fuhao = getView(R.id.article_goods_item_fuhao);//符号
            article_goods_item_jiage = getView(R.id.article_goods_item_jiage);//价格

        }

        @Override
        public void bindData(ArticleDetailsBean.GoodsListBean data) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            article_goods_item_img.setScaleType(ImageView.ScaleType.MATRIX);
            article_goods_item_img.setImageMatrix(matrix);
            ImageLoader.image(context, article_goods_item_img, data.getGoods_thumb());
            article_goods_item_name.setText(data.getGoods_name());

            if (data.getIs_promote() == 1) {//显示限时页面
                article_goods_item_jiage.setText(data.getPreferential_price());
            } else if (data.getIs_promote() == 0) {//显示普通页面
                article_goods_item_jiage.setText(data.getProduct_price());
            }


        }
    }


}

