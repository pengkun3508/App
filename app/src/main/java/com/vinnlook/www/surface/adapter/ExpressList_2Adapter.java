package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ConfirmOrderBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:海淘
 * @Time:2020/11/26$
 * @Author:pk$
 */
public class ExpressList_2Adapter extends BaseStateAdapter<ConfirmOrderBean.HtShopListBean.WaybillListBeanX, ExpressList_2Adapter.ExpressListListHolder> {

    Context context;

    public ExpressList_2Adapter(Context contexts) {
        context = contexts;
    }

    @Override
    protected ExpressListListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ExpressListListHolder(inflate(parent, R.layout.express_list_item));
    }

    class ExpressListListHolder extends BaseHolder<ConfirmOrderBean.HtShopListBean.WaybillListBeanX> {

        ImageView express_item_img;
        TextView express_name_text;
        TextView express_shuoming_text;
        TextView express_price_text;


        ExpressListListHolder(View itemView) {
            super(itemView);
            express_item_img = getView(R.id.express_item_img);//图标
            express_name_text = getView(R.id.express_name_text);//名称
            express_shuoming_text = getView(R.id.express_shuoming_text);//说明
            express_price_text = getView(R.id.express_price_text);//价格
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void bindData(ConfirmOrderBean.HtShopListBean.WaybillListBeanX data) {
            ImageLoader.image(express_item_img.getContext(), express_item_img, data.getImage());
            express_name_text.setText(data.getName());
            express_shuoming_text.setText(data.getContent());
            express_price_text.setText(Html.fromHtml("&yen") + String.valueOf(data.getPrice()));


        }

    }

}
