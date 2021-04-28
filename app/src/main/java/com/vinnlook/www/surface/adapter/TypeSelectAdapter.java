package com.vinnlook.www.surface.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.vinnlook.www.R;
import com.vinnlook.www.event.ShopTypeDataEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.mvp.model.bean.ProductBean;
import com.vinnlook.www.surface.mvp.model.bean.TypeSelecttypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Description:
 * @Time:2020/4/3$
 * @Author:pk$ RecyclerView.Adapter<AllOrderAdapter.ViewHolder>
 */
public class TypeSelectAdapter extends BaseStateAdapter<MoveDataBean.AttrBean, TypeSelectAdapter.ScreenHolder> {
    String getInfo;
    List<ProductBean> getProduct;
    List<TypeSelecttypeBean> type = new ArrayList<>();
    List<String> ProductBeanID = new ArrayList<>();//Product里边的goods_attrID
    ProductBean productBean;
    List<MoveDataBean.AttrBean.ValueBean.BannerBeanX> getBanner;
    OnTypeSelectItemClick onTypeSelectItemClick;

    List<ProductBean> getProducs;

    public void setOnTypeSelectItemClick(OnTypeSelectItemClick onTypeSelectItemClick) {
        this.onTypeSelectItemClick = onTypeSelectItemClick;
    }

    public TypeSelectAdapter(String getInfo, List<ProductBean> getProduct, List<MoveDataBean.AttrBean> getAttr) {
        this.getInfo = getInfo;
        this.getProduct = getProduct;
        for (int i = 0; i < getProduct.size(); i++) {
            ProductBeanID.add(getProduct.get(i).getGoods_attr());
        }
        for (int i = 0; i < getAttr.size(); i++) {
            TypeSelecttypeBean typeSelecttypeBean = new TypeSelecttypeBean();
            typeSelecttypeBean.setTypeID(getAttr.get(i).getAttr_id());
            List<String> strings = new ArrayList<>();
            for (int j = 0; j < getAttr.get(i).getValue().size(); j++) {
                strings.add(getAttr.get(i).getValue().get(j).getGoods_attr_id());
            }
            typeSelecttypeBean.setList(strings);
            type.add(typeSelecttypeBean);
        }
        String[] getInfos = getInfo.split("\\|");
        for (int i = 0; i < getInfos.length; i++) {
            for (int j = 0; j < type.size(); j++) {
                if (type.get(j).getList().contains(getInfos[i])) {
                    type.get(j).setId(getInfos[i]);
                }
            }
        }


    }

    @Override
    protected ScreenHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ScreenHolder(inflate(parent, R.layout.rv_item_screen));
    }

    class ScreenHolder extends BaseHolder<MoveDataBean.AttrBean> {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        ScreenHolder(View itemView) {
            super(itemView);
            tvTitle = getView(R.id.tv_title);
            recyclerView = getView(R.id.recyclerView);
            recyclerView.setLayoutManager(new FlexboxLayoutManager(itemView.getContext()));
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(true);

        }

        @Override
        public void bindData(MoveDataBean.AttrBean data) {
            tvTitle.setText(data.getAttr_name());
            ScreenItemAdapter adapter = new ScreenItemAdapter(data.getAttr_id());
            adapter.setOnScreenItemClick(new ScreenItemAdapter.OnScreenItemClick() {
                @Override
                public void onClick(MoveDataBean.AttrBean.ValueBean data, String typeID, int posion, List<ProductBean> getProductdata) {

                    if (data.getBanner() != null) {
                        new ShopTypeDataEvent("0", data, posion, data.getAttr_value()).post();
                        Log.e("选择的是?????", "getAttr_value====" + data.getAttr_value());
                    }
//                    if (data.getBanner()!=null) {
//                        new ShopTypeDataEvent("0",data.getBanner()).post();
//                    }
                    for (int i = 0; i < type.size(); i++) {
                        if (type.get(i).getTypeID().equals(typeID)) {
                            type.get(i).setId(data.getGoods_attr_id());
                            break;
                        }
                    }
                    for (int i = 0; i < data.getProductBeanList().size(); i++) {
                        for (int j = 0; j < type.size(); j++) {
                            if (type.get(j).getId() == null) {
                                break;
                            }
                            if (!data.getProductBeanList().get(i).getGoods_attr().contains(type.get(j).getId())) {
                                break;
                            }
                            if (j == type.size() - 1) {
                                Log.e("ScreenItemAdapter", "getGoods_attr====" + data.getProductBeanList().get(i).getGoods_attr());
                                productBean = data.getProductBeanList().get(i);
                                getBanner = data.getBanner();
                            }
                        }
                    }
                    if (onTypeSelectItemClick != null) {
                        Log.e("最后拿到的数据", "getProductdata==size==" + getProductdata.size());
                        onTypeSelectItemClick.onTypeClick(getProductdata.get(0), getBanner);
                    }
                    notifyDataSetChanged();


                }
            });
            String id = "";
            String qtid = "";
            for (int i = 0; i < type.size(); i++) {
                if (!TextUtils.isEmpty(type.get(i).getTypeID())) {
                    if (type.get(i).getTypeID().equals(data.getAttr_id())) {
                        id = type.get(i).getId();
                    } else {
                        if (!TextUtils.isEmpty(type.get(i).getId())) {
                            qtid = qtid + type.get(i).getId() + "|";
                        }
                    }
                }
            }
            if (qtid.length() > 0) {
                qtid.substring(0, qtid.length() - 2);
            }
            adapter.setID(id, qtid, getProduct);
            recyclerView.setAdapter(adapter);
            for (int i = 0; i < data.getValue().size(); i++) {
                getProducs = new ArrayList<>();
                for (int k = 0; k < ProductBeanID.size(); k++) {
                    if (ProductBeanID.get(k).contains(data.getValue().get(i).getGoods_attr_id())) {
                        getProducs.add(getProduct.get(k));
                    }
                }
                data.getValue().get(i).setProductBeanList(getProducs);
            }

            adapter.setData(data.getValue());
        }
    }

    public interface OnTypeSelectItemClick {
        void onTypeClick(ProductBean productBean, List<MoveDataBean.AttrBean.ValueBean.BannerBeanX> getBanner);
    }

}