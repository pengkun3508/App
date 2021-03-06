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

    public void setIDs(String id, String qtid, List<ProductBean> getProduct) {
        this.id = id;
        this.qtid = qtid;
        this.getProducts = getProduct;

    }

    @Override
    protected ScreenItemHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ScreenItemHolder(inflate(parent, R.layout.rv_item_screen_item));
    }

    public interface OnScreenItemClick {
        void onClick(MoveDataBean.AttrBean.ValueBean data, String typeID, int posion, List<ProductBean> getProductdata);
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
                        onScreenItemClick.onClick(data, typeID, getAdapterPosition(), getProductdata);
                    }
                }
            });


            Log.e("?????????ID", "==id==" + id);
            Log.e("?????????", "==qtid==" + qtid);
            if (!TextUtils.isEmpty(id)) {
                if (id.equals(data.getGoods_attr_id())) {
                    if (!TextUtils.isEmpty(qtid)) {

                        iD = id + "|" + qtid;
                        iD = iD.substring(0, iD.length() - 1);
                        Log.e("ScreenItemAdapter", "==??????ID==" + iD);
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
                                String[] ids = iD.split("\\|");
                                for (int j = 0; j < ids.length; j++) {
                                    if (!goods_attr.contains("|" + ids[j] + "|")) {
                                        break;
                                    }
                                    if (j == ids.length - 1) {
                                        getProductdata.add(data.getProductBeanList().get(i));
                                        new ChangeDetailPriceEvent(data.getProductBeanList().get(i), "2", data.getGoods_attr_id()).post();
                                    }
                                }
                            }


                            for (int i = 0; i < getProductdata.size(); i++) {
                                if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {
                                    tvTitle.getDelegate().setStrokeColor(R.color.white);//????????????
                                    tvTitle.getDelegate().setStrokePressColor(R.color.white);
                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //????????????
                                    tvTitle.setTextColor(ResUtils.getColor(R.color.white));//????????????+
                                    tvTitle.setEnabled(true);
                                    break;
                                } else {
                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
                                    tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//????????????+
                                    tvTitle.setEnabled(false);
                                }
//                                if (i == getProductdata.size() - 1) {//????????????
////                                    tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//????????????
////                                    tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
//                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
//                                    tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//????????????+
//                                    tvTitle.setEnabled(false);
//                                    break;
//                                }

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
                                String[] ids = iD.split("\\|");
                                for (int j = 0; j < ids.length; j++) {
                                    if (!goods_attr.contains("|" + ids[j] + "|")) {
                                        break;
                                    }
                                    if (j == ids.length - 1) {
                                        getProductdata.add(data.getProductBeanList().get(i));
                                        new ChangeDetailPriceEvent(data.getProductBeanList().get(i), "2", data.getGoods_attr_id()).post();
                                    }

                                }
                            }

                            for (int i = 0; i < getProductdata.size(); i++) {
                                if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {
//                                    tvTitle.getDelegate().setStrokeColor(R.color.white);//????????????
//                                    tvTitle.getDelegate().setStrokePressColor(R.color.white);
                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //????????????
                                    tvTitle.setTextColor(ResUtils.getColor(R.color.white));//????????????+
                                    tvTitle.setEnabled(true);
                                    break;
                                } else {
                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
                                    tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//????????????+
                                    tvTitle.setEnabled(false);
                                }
//                                if (i == getProductdata.size() - 1) {
////                                    tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//????????????
////                                    tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
//                                    tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
//                                    tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//????????????+
//                                    tvTitle.setEnabled(false);
//                                    break;
//                                }
                            }

                        }
                    } else {
                        for (int i = 0; i < data.getProductBeanList().size(); i++) {
                            for (int j = 0; j < qtid.split("\\|").length; j++) {
                                String getGoods_attr = "|" + data.getProductBeanList().get(i).getGoods_attr() + "|";
                                if (!getGoods_attr.contains("|" + qtid.split("\\|")[j] + "|")) {
                                    break;
                                }
//                                if (!data.getProductBeanList().get(i).getGoods_attr().contains(qtid.split("\\|")[j])) {
//                                    break;
//                                }
                                if (j == qtid.split("\\|").length - 1) {
                                    getProductdata.add(data.getProductBeanList().get(i));
                                }
                            }
                        }

                        for (int i = 0; i < getProductdata.size(); i++) {
                            if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {
//                                tvTitle.getDelegate().setStrokeColor(R.color.white);//????????????
//                                tvTitle.getDelegate().setStrokePressColor(R.color.white);
                                tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.them)); //????????????
                                tvTitle.setTextColor(ResUtils.getColor(R.color.white));//????????????+
                                tvTitle.setEnabled(true);
                                break;
                            }
                            if (i == getProductdata.size() - 1) {
//                                tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//????????????
//                                tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
                                tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
                                tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//????????????+
                                tvTitle.setEnabled(false);
                                break;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < data.getProductBeanList().size(); i++) {
                        for (int j = 0; j < qtid.split("\\|").length; j++) {
                            String getGoods_attr = "|" + data.getProductBeanList().get(i).getGoods_attr() + "|";
                            if (!getGoods_attr.contains("|" + qtid.split("\\|")[j] + "|")) {
                                break;
                            }
//                            if (!data.getProductBeanList().get(i).getGoods_attr().contains(qtid.split("\\|")[j])) {
//                                break;
//                            }
                            if (j == qtid.split("\\|").length - 1) {
                                getProductdata.add(data.getProductBeanList().get(i));
                            }
                        }
                    }
                    for (int i = 0; i < getProductdata.size(); i++) {
                        //????????????
                        if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {//?????????
//                            tvTitle.getDelegate().setStrokeColor(R.color.white);//????????????+
//                            tvTitle.getDelegate().setStrokePressColor(R.color.text_gray_light);
                            tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
                            tvTitle.setTextColor(ResUtils.getColor(R.color.text_black));//????????????+
                            tvTitle.setEnabled(true);
                            break;
                        }
                        if (i == getProductdata.size() - 1) {
//                            tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//????????????
//                            tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
                            tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
                            tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//????????????+
                            tvTitle.setEnabled(false);
                            break;
                        }
                    }
                }
            } else {
                //????????????????????????????????????????????????????????????Goods_attr?????????
                Log.e("pksss", "==qtid==" + qtid);
                String[] asda = qtid.split("\\|");

                for (int i = 0; i < data.getProductBeanList().size(); i++) {
                    Log.e("pksss", "==getGoods_attr==" + data.getProductBeanList().get(i).getGoods_attr());
                    String getGoods_attr = "|" + data.getProductBeanList().get(i).getGoods_attr() + "|";
                    Log.e("pksss", "==getGoods_attr===|==|==" + getGoods_attr);
                    for (int j = 0; j < asda.length; j++) {
                        Log.e("pksss", "==asda.length=====" + "|" + asda[j] + "|");
                        if (!getGoods_attr.contains("|" + asda[j] + "|")) {
                            break;
                        }
//                        else{
//
//                            getProductdata.add(data.getProductBeanList().get(i));
//                            Log.e("pklove", "==getGoods_attr==" + data.getProductBeanList().get(i).getGoods_attr());
//                        }
                        if (j == qtid.split("\\|").length - 1) {
                            getProductdata.add(data.getProductBeanList().get(i));
                            Log.e("pklove", "==getGoods_attr==" + data.getProductBeanList().get(i).getGoods_attr());
                        }
                    }
                }
                Log.e("??????Id??????", "==getProductdata.size==" + getProductdata.size());


                for (int i = 0; i < getProductdata.size(); i++) {
                    Log.e("??????Id??????", "==getGoods_attrasd==" + getProductdata.get(i).getGoods_attr());
                    Log.e("??????Id??????", "==getProduct_id==" + getProductdata.get(i).getProduct_id());
                    Log.e("??????Id??????", "==getProduct_number==" + getProductdata.get(i).getProduct_number());
                    //????????????
                    if (Integer.valueOf(getProductdata.get(i).getProduct_number()) > 0) {//????????????
                        Log.e("??????Id??????", "==getProduct_id=111=" + getProductdata.get(i).getProduct_id());
//                        tvTitle.getDelegate().setStrokeColor(R.color.white);//????????????
//                        tvTitle.getDelegate().setStrokePressColor(R.color.gray_dark);
                        tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
                        tvTitle.setTextColor(ResUtils.getColor(R.color.text_black));//????????????+
                        tvTitle.setEnabled(true);
                        break;
                    } else {
                        Log.e("??????Id??????", "==getProduct_id=222=" + getProductdata.get(i).getProduct_id());
//                        tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//????????????
//                        tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
                        tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
                        tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//????????????+
                        tvTitle.setEnabled(false);
                        break;
                    }
//                    if (i == getProductdata.size() - 1) {
//                        Log.e("??????Id??????", "==getProduct_id=222=" + getProductdata.get(i).getProduct_id());
////                        tvTitle.getDelegate().setStrokeColor(R.color.gray_light);//????????????
////                        tvTitle.getDelegate().setStrokePressColor(R.color.classify_text_bg);
//                        tvTitle.getDelegate().setBackgroundColor(ResUtils.getColor(R.color.shop_line_20)); //????????????
//                        tvTitle.setTextColor(ResUtils.getColor(R.color.gray_light));//????????????+
//                        tvTitle.setEnabled(false);
//                        break;
//                    }
                }
            }
        }
    }

}




