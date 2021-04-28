package com.vinnlook.www.surface.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.dm.lib.utils.ResUtils;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.TypeShopCarBean;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScreenItemAdapter_1 extends BaseStateAdapter<TypeShopCarBean.AttrBean.ValueBean, ScreenItemAdapter_1.ScreenItemHolder> {

    String typeID;
    String getInfo;
    String id;
    String qtid;
    List<ProductBean> getProduct;
    List<String> ProductBeanID = new ArrayList<>();
    private OnScreenItemClick onScreenItemClick;
    String iD;

    public ScreenItemAdapter_1(String typeID, String getInfo) {
        this.typeID = typeID;
        this.getInfo = getInfo;
    }

    public void setOnScreenItemClick(OnScreenItemClick onScreenItemClick) {
        this.onScreenItemClick = onScreenItemClick;
    }

    public void setID(String id, String qtid, List<ProductBean> getProduct) {
        this.id = id;
        this.qtid = qtid;
        this.getProduct = getProduct;

    }

    @Override
    protected ScreenItemHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ScreenItemHolder(inflate(parent, R.layout.rv_item_screen_item));
    }

    class ScreenItemHolder extends BaseHolder<TypeShopCarBean.AttrBean.ValueBean> {

        @BindView(R.id.tv_title)
        RoundTextView tvTitle;

        ScreenItemHolder(View itemView) {
            super(itemView);
            tvTitle = getView(R.id.tv_title);
        }


        @Override
        public void bindData(TypeShopCarBean.AttrBean.ValueBean data) {
            tvTitle.setText(data.getAttr_value());
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onScreenItemClick != null) {
                        onScreenItemClick.onClick(data, typeID);
                    }
                }
            });
            List<ProductBean> getProductdata = new ArrayList<>();
            Log.e("点击的ID", "==id==" + id);
            if (!TextUtils.isEmpty(id)) {
                if (id.equals(data.getGoods_attr_id())) {
                    if (!TextUtils.isEmpty(qtid)) {
                        iD = id + "|" + qtid;
                        iD = iD.substring(0, iD.length() - 1);
                        Log.e("ScreenItemAdapter", "==拼装ID==" + iD);
                        for (int i = 0; i < data.getProductBeanList().size(); i++) {
                            for (int j = 0; j < qtid.split("\\|").length; j++) {
                                if (!data.getProductBeanList().get(i).getGoods_attr().contains(qtid.split("\\|")[j])) {
                                    break;
                                }
                                if (j == qtid.split("\\|").length - 1) {
                                    getProductdata.add(data.getProductBeanList().get(i));
                                }
                            }
                        }

                        for (int i = 0; i < getProductdata.size(); i++) {
                            if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {
                                tvTitle.getDelegate().setStrokeColor(R.color.white);//边框变色
                                tvTitle.getDelegate().setStrokePressColor(R.color.white);
                                tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //背景变色
                                tvTitle.setTextColor(ResUtils.getColor(R.color.white));//字体变色+
                                break;
                            }
                            if (i == getProductdata.size() - 1) {//没有库存
                                tvTitle.getDelegate().setStrokeColor(R.color.shop_line);//边框变色
                                tvTitle.getDelegate().setStrokePressColor(R.color.shop_line);
                                tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line)); //背景变色
                                tvTitle.setTextColor(ResUtils.getColor(R.color.gray_dark));//字体变色+
                            }
                        }

                    } else {
                        for (int i = 0; i < data.getProductBeanList().size(); i++) {
                            for (int j = 0; j < qtid.split("\\|").length; j++) {
                                if (!data.getProductBeanList().get(i).getGoods_attr().contains(qtid.split("\\|")[j])) {
                                    break;
                                }
                                if (j == qtid.split("\\|").length - 1) {
                                    getProductdata.add(data.getProductBeanList().get(i));
                                }
                            }
                        }

                        for (int i = 0; i < getProductdata.size(); i++) {
                            if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {
                                tvTitle.getDelegate().setStrokeColor(R.color.white);//边框变色
                                tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //背景变色
                                tvTitle.setTextColor(ResUtils.getColor(R.color.white));//字体变色+
                                break;
                            }
                            if (i == getProductdata.size() - 1) {
                                tvTitle.getDelegate().setStrokeColor(R.color.shop_line);//边框变色
                                tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line)); //背景变色
                                tvTitle.setTextColor(ResUtils.getColor(R.color.gray_dark));//字体变色+
                                tvTitle.setEnabled(false);
                            }
                        }
                    }


                } else {
                    for (int i = 0; i < data.getProductBeanList().size(); i++) {
                        for (int j = 0; j < qtid.split("\\|").length; j++) {
                            if (!data.getProductBeanList().get(i).getGoods_attr().contains(qtid.split("\\|")[j])) {
                                break;
                            }
                            if (j == qtid.split("\\|").length - 1) {
                                getProductdata.add(data.getProductBeanList().get(i));
                            }
                        }
                    }

                    for (int i = 0; i < getProductdata.size(); i++) {
                        if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {//有库存
                            tvTitle.getDelegate().setStrokeColor(R.color.gray_dark);//边框变色
                            tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.white)); //背景变色
                            tvTitle.setTextColor(ResUtils.getColor(R.color.text_black));//字体变色+
                            break;
                        }
                        if (i == getProductdata.size() - 1) {
                            tvTitle.getDelegate().setStrokeColor(R.color.shop_line);//边框变色
                            tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line)); //背景变色
                            tvTitle.setTextColor(ResUtils.getColor(R.color.gray_dark));//字体变色+
                            tvTitle.setEnabled(false);
                        }

                    }
                }
            } else {
                for (int i = 0; i < data.getProductBeanList().size(); i++) {
                    for (int j = 0; j < qtid.split("\\|").length; j++) {
                        if (!data.getProductBeanList().get(i).getGoods_attr().contains(qtid.split("\\|")[j])) {
                            break;
                        }
                        if (j == qtid.split("\\|").length - 1) {
                            getProductdata.add(data.getProductBeanList().get(i));
                        }
                    }
                }

                for (int i = 0; i < getProductdata.size(); i++) {
                    if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {//判断库存
                        tvTitle.getDelegate().setStrokeColor(R.color.gray_dark);//边框变色
                        tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.white)); //背景变色
                        tvTitle.setTextColor(ResUtils.getColor(R.color.text_black));//字体变色+
                        break;
                    }
                    if (i == getProductdata.size() - 1) {
                        tvTitle.getDelegate().setStrokeColor(R.color.shop_line);//边框变色
                        tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line)); //背景变色
                        tvTitle.setTextColor(ResUtils.getColor(R.color.gray_dark));//字体变色+
                        tvTitle.setEnabled(false);
                    }


                }

            }

        }


    }


    public interface OnScreenItemClick {
        void onClick(TypeShopCarBean.AttrBean.ValueBean data, String typeID);
    }
}




