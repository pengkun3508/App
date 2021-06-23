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
public class TypeSelectAdapte_1 extends BaseStateAdapter<MoveDataBean.AttrBean, TypeSelectAdapte_1.ScreenHolder> {
    String getSearch_attr;
    List<ProductBean> getProduct;
    List<MoveDataBean.AttrBean> getAttr;
    List<TypeSelecttypeBean> typeList = new ArrayList<>();
    List<String> ProductBeanID = new ArrayList<>();//Product里边的goods_attrID
    ProductBean productBean;
    List<MoveDataBean.AttrBean.ValueBean.BannerBeanX> getBanner;
    OnTypeSelectItemClick onTypeSelectItemClick;

    List<ProductBean> getProducs;

    public TypeSelectAdapte_1(String getSearch_attr, List<ProductBean> getProduct, List<MoveDataBean.AttrBean> getAttr) {
        this.getSearch_attr = getSearch_attr;
        this.getProduct = getProduct;
        this.getAttr=getAttr;

        //将product中的goods_attr全部装到一个List中
        for (int i = 0; i < getProduct.size(); i++) {
            ProductBeanID.add(getProduct.get(i).getGoods_attr());
        }
        for (int i = 0; i < getAttr.size(); i++) {
            TypeSelecttypeBean typeSelecttypeBean = new TypeSelecttypeBean();
            typeSelecttypeBean.setTypeID(getAttr.get(i).getAttr_id());//保存颜色，规格，度数的ID
            List<String> strings = new ArrayList<>();
            for (int j = 0; j < getAttr.get(i).getValue().size(); j++) {
                strings.add(getAttr.get(i).getValue().get(j).getGoods_attr_id());//保存颜色，规格，度数里边具体数据的ID
            }
            typeSelecttypeBean.setList(strings);
            typeList.add(typeSelecttypeBean);//将这些数据装到一个list中
        }

    }

    public void setOnTypeSelectItemClick(OnTypeSelectItemClick onTypeSelectItemClick) {
        this.onTypeSelectItemClick = onTypeSelectItemClick;
    }

    @Override
    protected ScreenHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ScreenHolder(inflate(parent, R.layout.rv_item_screen));
    }

    public interface OnTypeSelectItemClick {
        void onTypeClick(ProductBean productBean, List<MoveDataBean.AttrBean.ValueBean.BannerBeanX> getBanner);
    }

    class ScreenHolder extends BaseHolder<MoveDataBean.AttrBean> {
        @BindView(R.id.tv_title)
        TextView tvTitle;        @BindView(R.id.recyclerView)
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
            ScreenItemAdapte_1 adapter = new ScreenItemAdapte_1(data.getAttr_id());
            adapter.setOnScreenItemClick(new ScreenItemAdapte_1.OnScreenItemClick() {
                @Override
                public void onClick(MoveDataBean.AttrBean.ValueBean data, String typeID, int posion, List<ProductBean> getProductdata) {

                    if (data.getBanner() != null) {
                        new ShopTypeDataEvent("0", data, posion, data.getAttr_value()).post();
                        Log.e("选择的是?????", "getAttr_value====" + data.getAttr_value());
                    }
//                    if (data.getBanner()!=null) {
//                        new ShopTypeDataEvent("0",data.getBanner()).post();
//                    }

                    Log.e("问题点", "getGoods_attr_id====" + data.getGoods_attr_id());
                    Log.e("问题点", "typeID====" + typeID);
                    for (int i = 0; i < typeList.size(); i++) {
                        Log.e("问题点", "getTypeID()====" + typeList.get(i).getTypeID());
                        if (typeList.get(i).getTypeID().equals(typeID)) {
                            typeList.get(i).setId(data.getGoods_attr_id());
                            break;
                        }
                    }

                    for (int i = 0; i < data.getProductBeanList().size(); i++) {
                        for (int j = 0; j < typeList.size(); j++) {
                            if (typeList.get(j).getId() == null) {
                                break;
                            }
                            if (!data.getProductBeanList().get(i).getGoods_attr().contains(typeList.get(j).getId())) {
                                break;
                            }
                            String[] ids = data.getProductBeanList().get(i).getGoods_attr().split("\\|");
                            for (int k = 0; k < ids.length; k++) {
                                if (ids[k].equals(data.getGoods_attr_id())) {
                                    productBean = data.getProductBeanList().get(i);
                                    getBanner = data.getBanner();
                                    Log.e("最后拿到的数据", "typeList.get(j).getId()====" + typeList.get(j).getId());
                                    Log.e("最后拿到的数据", "getProduct_id===" + productBean.getProduct_id());
                                    Log.e("最后拿到的数据", "getGoods_attr===" + productBean.getGoods_attr());
                                }
                            }

//                            if (j == typeList.size() - 1) {
//                                Log.e("ScreenItemAdapter", "getGoods_attr====" + data.getProductBeanList().get(i).getGoods_attr());
//                                productBean = data.getProductBeanList().get(i);
//                                getBanner = data.getBanner();
//                            }
                        }
                    }
                    if (onTypeSelectItemClick != null) {
                        Log.e("最后拿到的数据", "getProductdata==size==" + getProductdata.size());
//                        onTypeSelectItemClick.onTypeClick(getProductdata.get(0), getBanner);
                        onTypeSelectItemClick.onTypeClick(productBean, getBanner);

                    }
                    notifyDataSetChanged();


                }
            });
            String id = "";
            String qtid = "";
            for (int i = 0; i < typeList.size(); i++) {
                if (!TextUtils.isEmpty(typeList.get(i).getTypeID())) {
                    if (typeList.get(i).getTypeID().equals(data.getAttr_id())) {
                        id = typeList.get(i).getId();
                    } else {
                        if (!TextUtils.isEmpty(typeList.get(i).getId())) {
                            qtid = qtid + typeList.get(i).getId() + "|";
                        }
                    }
                }
            }
            Log.e("新测试", "qtid==111====" + qtid);
            if (qtid.length() > 0) {
                qtid.substring(0, qtid.length() - 2);
            }
            Log.e("新测试", "qtid==222====" + qtid);
            adapter.setIDs(id, qtid, getProduct);
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

}