package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.flyco.roundview.RoundLinearLayout;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.OrderDetailsActivity;
import com.vinnlook.www.surface.bean.GroupOrderListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2021/5/11$
 * @Author:pk$
 */
public class CountDownTimerAdapter extends RecyclerView.Adapter<CountDownTimerAdapter.MyViewHolder> {

    public static final int TYPE_FOOT = 1;
    public static final int TYPE_NOMAL = 0;
    public List<MyViewHolder> myViewHolderList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Context context;
    private List<GroupOrderListBean.ListBean> data;
    private View footerView;
    private OnLoadMoreListener mOnLoadMoreListener;
    private OnItemClickListener mOnItemClickListener;

    public CountDownTimerAdapter(Context context, List<GroupOrderListBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FOOT && footerView != null) {
            view = footerView;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.grouplist_item, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //用holder绑定对应的position
        holder.setDataPosition(position);
        //判断list里面是否含有该holder，没有就增加
        //因为list已经持有holder的引用，所有数据自动会改变
        if (!(myViewHolderList.contains(holder))) {
            myViewHolderList.add(holder);
        }
        holder.group_item1_peonple.setText(data.get(position).getContent());
        holder.group_item1_price.setText(Html.fromHtml("&yen") + data.get(position).getOrder_amount());
        if (data.get(position).getGroup_status().equals("0")) {
            holder.group_item1_static.setText("拼团中");
            holder.group_item1_static.setVisibility(View.GONE);
            holder.group_item1_time_layout.setVisibility(View.VISIBLE);
            holder.group_item1_see_btn.setVisibility(View.GONE);
            holder.tuikuan_static_text.setVisibility(View.GONE);
        } else if (data.get(position).getGroup_status().equals("1")) {
            holder.group_item1_static.setText("拼团成功");
            holder.group_item1_static.setVisibility(View.VISIBLE);
            holder.group_item1_time_layout.setVisibility(View.GONE);
            holder.group_item1_see_btn.setVisibility(View.VISIBLE);
            holder.tuikuan_static_text.setVisibility(View.GONE);

        } else if (data.get(position).getGroup_status().equals("2")) {
            holder.group_item1_static.setText("拼团失败");
            holder.group_item1_static.setVisibility(View.VISIBLE);
            holder.group_item1_time_layout.setVisibility(View.GONE);
            holder.group_item1_see_btn.setVisibility(View.GONE);
            holder.tuikuan_static_text.setVisibility(View.VISIBLE);
        }

        if (data.get(position).getIs_first_group().equals("0")) {//不是团长
            holder.group_item1_chief.setVisibility(View.GONE);
        } else {
            holder.group_item1_chief.setVisibility(View.VISIBLE);
        }
        holder.group_item1_time_hour.setText(data.get(position).getHour());
        holder.group_item1_time_min.setText(data.get(position).getMinute());
        holder.group_item1_time_second.setText(data.get(position).getSecond());

        holder.group_item1_recycler.setLayoutManager(new LinearLayoutManager(context));
        GroupListItemAdapter adapter = new GroupListItemAdapter(context);
        holder.group_item1_recycler.setAdapter(adapter);
        adapter.setData(data.get(position).getShop_list());

        adapter.setOnItemClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position1) {
                Log.e("getOrder_id", "===getOrder_id==" + data.get(position).getOrder_id());
                mOnItemClickListener.onItemClick(view, position, data.get(position).getOrder_id(), data.get(position).getGroup_status(),
                        data.get(position).getGoods_id(), data.get(position).getSearch_attr(),data.get(position).getGroup_id());
            }
        });

        //进入团购详情
        holder.order_to_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, position, data.get(position).getOrder_id(), data.get(position).getGroup_status(),
                        data.get(position).getGoods_id(), data.get(position).getSearch_attr(),data.get(position).getGroup_id());
            }
        });


        //查看订单--进入订单详情
        holder.group_item1_see_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetailsActivity.startSelf(context, data.get(position).getOrder_id());
            }
        });


    }

    //遍历list，刷新相应holder的TextView
    public void notifyData() {
        for (int i = 0; i < myViewHolderList.size(); i++) {
            myViewHolderList.get(i).group_item1_time_hour.setText(data.get(myViewHolderList.get(i).position).getHour());
            myViewHolderList.get(i).group_item1_time_min.setText(data.get(myViewHolderList.get(i).position).getMinute());
            myViewHolderList.get(i).group_item1_time_second.setText(data.get(myViewHolderList.get(i).position).getSecond());
        }
    }

    @Override
    public int getItemCount() {
        int count = (data == null ? 0 : data.size());
        if (footerView != null) {
            count++;
        }
        return count;
    }

    public List<GroupOrderListBean.ListBean> getNewData() {
        return data;
    }

    @Override
    public int getItemViewType(int position) {
        if (footerView == null)
            return TYPE_NOMAL;
        if (position == getItemCount() - 1)
            return TYPE_FOOT;
        return TYPE_NOMAL;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (!recyclerView.canScrollVertically(1)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.loadMore();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnLoadMoreListener {
        void loadMore();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String orderID, String group_status, String goods_id, String search_attr,String group_id);

        void onItemLongClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView group_item1_static;
        RoundLinearLayout group_item1_time_layout;
        RoundTextView group_item1_time_hour, group_item1_time_min, group_item1_time_second;
        RecyclerView group_item1_recycler;
        TextView group_item1_peonple, group_item1_price, group_item1_see_btn, tuikuan_static_text;
        ImageView group_item1_chief;
        LinearLayout order_to_details_btn;
        private int position;

        public MyViewHolder(View view) {
            super(view);
            group_item1_static = itemView.findViewById(R.id.group_item1_static);
            group_item1_time_layout = itemView.findViewById(R.id.group_item1_time_layout);
            group_item1_time_hour = itemView.findViewById(R.id.group_item1_time_hour);
            group_item1_time_min = itemView.findViewById(R.id.group_item1_time_min);
            group_item1_time_second = itemView.findViewById(R.id.group_item1_time_second);
            group_item1_recycler = itemView.findViewById(R.id.group_item1_recycler);
            group_item1_peonple = itemView.findViewById(R.id.group_item1_peonple);
            group_item1_price = itemView.findViewById(R.id.group_item1_price);
            group_item1_see_btn = itemView.findViewById(R.id.group_item1_see_btn);
            group_item1_chief = itemView.findViewById(R.id.group_item1_chief);
            order_to_details_btn = itemView.findViewById(R.id.order_to_details_btn);
            tuikuan_static_text = itemView.findViewById(R.id.tuikuan_static_text);
        }

        private void setDataPosition(int position) {
            this.position = position;
        }

    }

}
