package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GuangThemBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

/**
 * @Description:
 * @Time:2021/6/28$
 * @Author:pk$
 */
public class Guang_1Adapter extends BaseStateAdapter<GuangThemBean.ItemBean, Guang_1Adapter.Guang_1Holder> {

    Context context;

    OnItemClickListener onItemClickListener;


    public Guang_1Adapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    protected Guang_1Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new Guang_1Holder(inflate(parent, R.layout.guang_1_item));
    }

    public void setOnAllClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        void onItemTitleClicked(int type, String name);

        void onItemDataClicked(int type,String iD);
    }

    class Guang_1Holder extends BaseHolder<GuangThemBean.ItemBean> {
        TextView guang1_item_text1;
        LinearLayout guang1_item_layout;
        RecyclerView guang_1recycler;
        RelativeLayout them_all_layout_btn;
        Guang_1_1Adapter guang11Adapter;
        Guang_1_2Adapter guang12Adapter;

        Guang_1Holder(View itemView) {
            super(itemView);
            guang1_item_text1 = itemView.findViewById(R.id.guang1_item_text1);
            guang1_item_layout = itemView.findViewById(R.id.guang1_item_layout);
            guang_1recycler = itemView.findViewById(R.id.guang_1recycler);
            them_all_layout_btn = itemView.findViewById(R.id.them_all_layout_btn);
        }

        @Override
        public void bindData(GuangThemBean.ItemBean data) {
            guang1_item_text1.setText(data.getName());
//            if (data.getType() == 1) {
            if (guang11Adapter == null) {
                guang11Adapter = new Guang_1_1Adapter(context);
                final GridLayoutManager manager3 = new GridLayoutManager(context, 1);
                manager3.setOrientation(GridLayoutManager.HORIZONTAL);
                guang_1recycler.setLayoutManager(manager3);
                guang_1recycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(context, 0)));
                guang_1recycler.addItemDecoration(new SpaceItemDecoration(15, 15));
                guang_1recycler.setAdapter(guang11Adapter);
                guang11Adapter.setData(data.getList());
                guang11Adapter.setType(data.getType());
            } else {
                Log.e("主题乐园", "==1111====getList===" + data.getList());
                guang11Adapter.setData(data.getList());
                guang11Adapter.setType(data.getType());
            }
//            } else {
//                if (guang12Adapter == null) {
//                    guang12Adapter = new Guang_1_2Adapter(context);
//                    final GridLayoutManager manager4 = new GridLayoutManager(context, 1);
//                    manager4.setOrientation(GridLayoutManager.HORIZONTAL);
//                    guang_1recycler.setLayoutManager(manager4);
//                    guang_1recycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(context, 0)));
//                    guang_1recycler.addItemDecoration(new SpaceItemDecoration(10, 10));
//                    guang_1recycler.setAdapter(guang12Adapter);
//                } else {
//                    Log.e("主题乐园", "==2222====getList===" + data.getList());
//                    guang12Adapter.setData(data.getList());
//                    guang12Adapter.setType(data.getType());
//                }
//            }


            them_all_layout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemTitleClicked(data.getType(), data.getName());
                }
            });

            guang11Adapter.addOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view, int position) {
                    onItemClickListener.onItemDataClicked(data.getType(),guang11Adapter.getData().get(position).getId());
                }
            });

        }
    }
}

