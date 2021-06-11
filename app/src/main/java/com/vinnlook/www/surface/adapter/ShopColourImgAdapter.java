package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.luck.picture.lib.entity.LocalMedia;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

/**
 * @Description:
 * @Time:2021/5/24$
 * @Author:pk$
 */
public class ShopColourImgAdapter extends BaseStateAdapter5<MoveDataBean.AttrBean.ValueBean, ShopColourImgAdapter.ShopColourImgHolder> {

    Context context;
    CommentImgAdapter commentAdapter;
    List<String> getImage;
    List<LocalMedia> selectList;

    public ShopColourImgAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected ShopColourImgHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ShopColourImgHolder(inflate(parent, R.layout.shop_coloru_item));
    }

    class ShopColourImgHolder extends BaseHolder<MoveDataBean.AttrBean.ValueBean> {

        ImageView shop_coloru_img, shop_coloru_img_select;

        ShopColourImgHolder(View itemView) {
            super(itemView);
            shop_coloru_img = itemView.findViewById(R.id.shop_coloru_img);
            shop_coloru_img_select = itemView.findViewById(R.id.shop_coloru_img_select);
        }

        @Override
        public void bindData(MoveDataBean.AttrBean.ValueBean data) {
            shop_coloru_img.setScaleType(ImageView.ScaleType.FIT_XY);
            if (data.getBanner().size() > 0) {
                if (data.getBanner().size() == 1) {
                    if (data.getBanner().get(0).getType() == 1) {
                        ImageLoader.userIcon(context, shop_coloru_img, data.getBanner().get(0).getUrl());
                    } else if (data.getBanner().get(0).getType() == 2) {
                        ImageLoader.userIcon(context, shop_coloru_img, data.getBanner().get(1).getUrl());
                    }
                } else if (data.getBanner().size() > 1) {
                    if (data.getBanner().get(0).getType() == 1) {
                        ImageLoader.userIcon(context, shop_coloru_img, data.getBanner().get(1).getUrl());
//                        if (getAdapterPosition() == 0) {
//                            ImageLoader.userIcon(context, shop_coloru_img, data.getBanner().get(0).getUrl());
//                        } else {
//                            ImageLoader.userIcon(context, shop_coloru_img, data.getBanner().get(1).getUrl());
//                        }
                    } else if (data.getBanner().get(0).getType() == 2) {
                        ImageLoader.userIcon(context, shop_coloru_img, data.getBanner().get(2).getUrl());
//                        if (getAdapterPosition() == 0) {
//                            ImageLoader.userIcon(context, shop_coloru_img, data.getBanner().get(1).getUrl());
//                        } else {
//                            ImageLoader.userIcon(context, shop_coloru_img, data.getBanner().get(2).getUrl());
//                        }
                    }
                }
            }
            if (data.getFlage().equals("0")) {//未选择
                shop_coloru_img_select.setVisibility(View.GONE);
            } else if (data.getFlage().equals("1")) {//已选择
                shop_coloru_img_select.setVisibility(View.VISIBLE);
            }


        }


    }
}

