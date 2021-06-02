package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GroupOrderListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * @Description:
 * @Time:2021/5/10$
 * @Author:pk$
 */
public class GroupListAdapter extends BaseStateAdapter<GroupOrderListBean.ListBean, GroupListAdapter.GroupListHolder> {

    Context context;
    int surplus_time;
    GroupListItemAdapter adapter;


    public GroupListAdapter(Context context) {
        this.context = context;
    }


    @Override
    protected GroupListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new GroupListHolder(inflate(parent, R.layout.grouplist_item));
    }

    public class GroupListHolder extends BaseHolder<GroupOrderListBean.ListBean> {

        TextView group_item1_static;
        RoundLinearLayout group_item1_time_layout;
        RoundTextView group_item1_time_hour, group_item1_time_min, group_item1_time_second;
        RecyclerView group_item1_recycler;
        TextView group_item1_peonple, group_item1_price, group_item1_see_btn;

        GroupListHolder(View itemView) {
            super(itemView);
            group_item1_static = itemView.findViewById(R.id.group_item1_static);
            group_item1_time_layout = itemView.findViewById(R.id.group_item1_time_layout);
            group_item1_time_hour = itemView.findViewById(R.id.group_item1_time_hour);
            group_item1_time_min = itemView.findViewById(R.id.group_item1_time_min);
            group_item1_time_second = itemView.findViewById(R.id.group_item1_time_second);
            group_item1_recycler = itemView.findViewById(R.id.group_item1_recycler);
            group_item1_peonple = itemView.findViewById(R.id.group_item1_peonple);
            group_item1_price = itemView.findViewById(R.id.group_item1_price);
            group_item1_see_btn = itemView.findViewById(R.id.group_item1_see_btn);

        }

        @Override
        public void bindData(GroupOrderListBean.ListBean data) {

            group_item1_peonple.setText(data.getContent());
            group_item1_price.setText(Html.fromHtml("&yen") + data.getOrder_amount());
            if (data.getGroup_status().equals("0")) {
                group_item1_static.setText("拼团中");
                group_item1_static.setVisibility(View.GONE);
                group_item1_time_layout.setVisibility(View.VISIBLE);
            } else if (data.getGroup_status().equals("1")) {
                group_item1_static.setText("拼团成功");
            } else if (data.getGroup_status().equals("2")) {
                group_item1_static.setText("拼团失败,已退款");
            }


            group_item1_recycler.setLayoutManager(new LinearLayoutManager(context));
            adapter = new GroupListItemAdapter(context);
            group_item1_recycler.setAdapter(adapter);
            adapter.setData(data.getShop_list());


        }
    }


}
