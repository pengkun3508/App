package com.vinnlook.www.surface.adapter;

import android.text.TextUtils;
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
import com.vinnlook.www.surface.bean.TypeShopCarBean;
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
public class TypeSelectAdapter_1 extends BaseStateAdapter<TypeShopCarBean.AttrBean, TypeSelectAdapter_1.ScreenHolder> {
    String getInfo;
    List<ProductBean> getProduct;
    List<TypeSelecttypeBean> type = new ArrayList<>();
    List<String> ProductBeanID = new ArrayList<>();
    ProductBean productBean;
    static OnTypeSelectItemClick onTypeSelectItemClick;


    public void setOnTypeSelectItemClick(OnTypeSelectItemClick onTypeSelectItemClicks) {
        onTypeSelectItemClick = onTypeSelectItemClicks;
    }

    public TypeSelectAdapter_1(String getInfo, List<ProductBean> getProduct, List<TypeShopCarBean.AttrBean> getAttr) {
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

    class ScreenHolder extends BaseHolder<TypeShopCarBean.AttrBean> {

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
        public void bindData(TypeShopCarBean.AttrBean data) {
            tvTitle.setText(data.getAttr_name());
            ScreenItemAdapter_1 adapter = new ScreenItemAdapter_1(data.getAttr_id(), getInfo);

            adapter.setOnScreenItemClick(new ScreenItemAdapter_1.OnScreenItemClick() {
                @Override
                public void onClick(TypeShopCarBean.AttrBean.ValueBean data, String typeID) {
//                    if (data.getBanner() != null) {
//                        new ShopTypeDataEvent("0", data.getBanner()).post();
//                    }
                    if (data.getBanner() != null) {
//                        if (data.getBanner().get(0).getType()==1) {
//                            new ShopTypeDataEvent("0", data.getBanner().get(0).getUrl(),).post();
//                        } else if (data.getBanner().get(0).getType()==2) {
//                            new ShopTypeDataEvent("0", data.getBanner().get(1).getUrl()).post();
//                        }
//                        new ShopTypeDataEvent("0", data).post();

                    }

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
                                productBean = data.getProductBeanList().get(i);
                            }
                        }

                    }

                    if (onTypeSelectItemClick != null) {
                        onTypeSelectItemClick.onTypeClick(productBean);
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
                List<ProductBean> getProducs = new ArrayList<>();
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
        void onTypeClick(ProductBean productBean);
    }

}