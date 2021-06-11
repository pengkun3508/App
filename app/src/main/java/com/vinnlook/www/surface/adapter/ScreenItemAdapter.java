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
import com.vinnlook.www.event.ChangeDetailPriceEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ScreenItemAdapter extends BaseStateAdapter<MoveDataBean.AttrBean.ValueBean, ScreenItemAdapter.ScreenItemHolder> {

    String typeID;
    String id;
    String qtid;
    List<ProductBean> getProducts;
    List<String> ProductBeanID = new ArrayList<>();
    private OnScreenItemClick onScreenItemClick;
    String iD;

    public ScreenItemAdapter(String typeID) {
        this.typeID = typeID;
    }

    public void setOnScreenItemClick(OnScreenItemClick onScreenItemClick) {
        this.onScreenItemClick = onScreenItemClick;
    }

    public void setID(String id, String qtid, List<ProductBean> getProduct) {
        this.id = id;
        this.qtid = qtid;
        this.getProducts = getProduct;

    }

    @Override
    protected ScreenItemHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ScreenItemHolder(inflate(parent, R.layout.rv_item_screen_item));
    }

    class ScreenItemHolder extends BaseHolder<MoveDataBean.AttrBean.ValueBean> {

        @BindView(R.id.tv_title)
        RoundTextView tvTitle;

        ScreenItemHolder(View itemView) {
            super(itemView);
            tvTitle = getView(R.id.tv_title);
        }


        @Override
        public void bindData(MoveDataBean.AttrBean.ValueBean data) {
            List<ProductBean> getProductdata = new ArrayList<>();
            tvTitle.setText(data.getAttr_value());
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onScreenItemClick != null) {
                        onScreenItemClick.onClick(data, typeID, getAdapterPosition(),getProductdata);
                    }
                }
            });


            Log.e("点击的ID", "==id==" + id);
            if (!TextUtils.isEmpty(id)) {
                if (id.equals(data.getGoods_attr_id())) {
                    if (!TextUtils.isEmpty(qtid)) {

                        iD = id + "|" + qtid;
                        iD = iD.substring(0, iD.length() - 1);
                        Log.e("ScreenItemAdapter", "==拼装ID==" + iD);
                        Log.e("ScreenItemAdapter", "==qtid==" + qtid);


//                        for (int l = 0; l < data.getProductBeanList().size(); l++) {
                        if (data.getProductBeanList().contains(iD)) {
                            Log.e("getProductBeanList.size", "==getProductBeanList==size==111===" + data.getProductBeanList().size());
//                            for (int i = 0; i < data.getProductBeanList().size(); i++) {
//                                for (int j = 0; j < qtid.split("\\|").length; j++) {
//                                    if (!data.getProductBeanList().get(i).getGoods_attr().contains(qtid.split("\\|")[j])) {
//                                        break;
//                                    }
//                                    if (j == qtid.split("\\|").length - 1) {
//                                        getProductdata.add(data.getProductBeanList().get(i));
//                                    }
//                                }
//                            }
                            for (int i = 0; i < data.getProductBeanList().size(); i++) {
                                String goods_attr = "|" + data.getProductBeanList().get(i).getGoods_attr() + "|";
                                String[]  ids= iD.split("\\|");
                                for (int j = 0; j < ids.length; j++) {
                                    if (!goods_attr.contains("|"+ids[j]+"|")){
                                        break;
                                    }
                                    if (j==ids.length-1) {
                                        getProductdata.add(data.getProductBeanList().get(i));
                                        new ChangeDetailPriceEvent(data.getProductBeanList().get(i), "2",data.getGoods_attr_id()).post();
                                    }
                                }
                            }


                            for (int i = 0; i < getProductdata.size(); i++) {
                                if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {
                                    tvTitle.getDelegate().setStrokeColor(R.color.white);//边框变色
                                    tvTitle.getDelegate().setStrokePressColor(R.color.white);
                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //背景变色
                                    tvTitle.setTextColor(ResUtils.getColor(R.color.white));//字体变色+
                                    tvTitle.setEnabled(true);
                                    break;
                                }
                                if (i == getProductdata.size() - 1) {//没有库存
                                    tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//边框变色
                                    tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.classify_text_bg)); //背景变色
                                    tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//字体变色+
                                    tvTitle.setEnabled(false);
                                }

                            }

                        } else {
                            Log.e("getProductBeanList.size", "==getProductBeanList==size==222===" + data.getProductBeanList().size());
//                            for (int i = 0; i < data.getProductBeanList().size(); i++) {
//                                for (int j = 0; j < qtid.split("\\|").length; j++) {
//                                    if (!data.getProductBeanList().get(i).getGoods_attr().contains(qtid.split("\\|")[j])) {
//                                        break;
//                                    }
//                                    if (j == qtid.split("\\|").length - 1) {
//                                        getProductdata.add(data.getProductBeanList().get(i));
//                                        new ChangeDetailPriceEvent(data.getProductBeanList().get(i), "2").post();
//                                    }
//                                }
//                            }

                            for (int i = 0; i < data.getProductBeanList().size(); i++) {
                                String goods_attr = "|" + data.getProductBeanList().get(i).getGoods_attr() + "|";
                                String[]  ids= iD.split("\\|");
                                for (int j = 0; j < ids.length; j++) {
                                    if (!goods_attr.contains("|"+ids[j]+"|")){
                                        break;
                                    }
                                    if (j==ids.length-1) {
                                        getProductdata.add(data.getProductBeanList().get(i));
                                        new ChangeDetailPriceEvent(data.getProductBeanList().get(i), "2",data.getGoods_attr_id()).post();
                                    }
                                }
                            }

                            for (int i = 0; i < getProductdata.size(); i++) {
                                if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {
                                    tvTitle.getDelegate().setStrokeColor(R.color.white);//边框变色
                                    tvTitle.getDelegate().setStrokePressColor(R.color.white);
                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //背景变色
                                    tvTitle.setTextColor(ResUtils.getColor(R.color.white));//字体变色+
                                    tvTitle.setEnabled(true);
                                    break;
                                }
                                if (i == getProductdata.size() - 1) {
                                    tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//边框变色
                                    tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.classify_text_bg)); //背景变色
                                    tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//字体变色+
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
                            if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {
                                tvTitle.getDelegate().setStrokeColor(R.color.white);//边框变色
                                tvTitle.getDelegate().setStrokePressColor(R.color.white);
                                tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //背景变色
                                tvTitle.setTextColor(ResUtils.getColor(R.color.white));//字体变色+
                                tvTitle.setEnabled(true);
                                break;
                            }
                            if (i == getProductdata.size() - 1) {
                                tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//边框变色
                                tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
                                tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.classify_text_bg)); //背景变色
                                tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//字体变色+
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
                        //可以选择
                        if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {//有库存
                            tvTitle.getDelegate().setStrokeColor(R.color.white);//边框变色+
                            tvTitle.getDelegate().setStrokePressColor(R.color.gray_dark);
                            tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_2)); //背景变色
                            tvTitle.setTextColor(ResUtils.getColor(R.color.text_black));//字体变色+
                            tvTitle.setEnabled(true);
                            break;
                        }
                        if (i == getProductdata.size() - 1) {
                            tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//边框变色
                            tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
                            tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.classify_text_bg)); //背景变色
                            tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//字体变色+
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
                    //可以选择
                    if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {//判断库存
                        tvTitle.getDelegate().setStrokeColor(R.color.white);//边框变色
                        tvTitle.getDelegate().setStrokePressColor(R.color.gray_dark);
                        tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_2)); //背景变色
                        tvTitle.setTextColor(ResUtils.getColor(R.color.text_black));//字体变色+
                        tvTitle.setEnabled(true);
                        break;
                    }
                    if (i == getProductdata.size() - 1) {
                        tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//边框变色
                        tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
                        tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.classify_text_bg)); //背景变色
                        tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//字体变色+
                        tvTitle.setEnabled(false);
                    }
                }
            }
        }
    }

    public interface OnScreenItemClick {
        void onClick(MoveDataBean.AttrBean.ValueBean data, String typeID, int posion,List<ProductBean> getProductdata);
    }

}




