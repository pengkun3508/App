package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.GroupListBean;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2021/5/13$
 * @Author:pk$
 */
public class GroupWorkGo1Adapter extends RecyclerView.Adapter<GroupWorkGo1Adapter.MyViewHolder> {

    public static final int TYPE_FOOT = 1;
    public static final int TYPE_NOMAL = 0;
    public List<MyViewHolder> myViewHolderList = new ArrayList<>();
    Context context;
    List<GroupListBean.ListBean> data;
    private RecyclerView mRecyclerView;
    private View footerView;
    private OnLoadMoreListener mOnLoadMoreListener;
    private OnItemClickListener mOnItemClickListener;

    public GroupWorkGo1Adapter(Context context, List<GroupListBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FOOT && footerView != null) {
            view = footerView;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.group_go_item, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //用holder绑定对应的position
        holder.setDataPosition(position);
        //判断list里面是否含有该holder，没有就增加
        //因为list已经持有holder的引用，所有数据自动会改变
        if (!(myViewHolderList.contains(holder))) {
            myViewHolderList.add(holder);
        }
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        holder.xianshi_img.setScaleType(ImageView.ScaleType.MATRIX);
        holder.xianshi_img.setImageMatrix(matrix);
        ImageLoader.image(context, holder.xianshi_img, data.get(position).getGoods_thumb());

        holder.xianshi_name.setText(data.get(position).getGoods_name());
        holder.xianshi_group_num.setText(data.get(position).getGroup_people() + "人团");
        holder.xianshi_jiage.setText(data.get(position).getGroup_price());
        holder.xianshi_yuanjia.setText("单价卖" + Html.fromHtml("&yen") + data.get(position).getProduct_price());
        holder.xianshi_yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        if (data.get(position).getIs_post_fee().equals("0")) {
            holder.xianshi_group_flag.setVisibility(View.GONE);
        } else if (data.get(position).getIs_post_fee().equals("1")) {
            holder.xianshi_group_flag.setVisibility(View.VISIBLE);
        }

        holder.group_go_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view,position,data.get(position).getGoods_id(),data.get(position).getSearch_attr());
            }
        });

    }

    //遍历list，刷新相应holder的TextView
    public void notifyData() {
        for (int i = 0; i < myViewHolderList.size(); i++) {
            myViewHolderList.get(i).home_xianshi_time_text_days.setText(data.get(myViewHolderList.get(i).position).getDay());
            myViewHolderList.get(i).home_xianshi_time_text_hours.setText(data.get(myViewHolderList.get(i).position).getHour());
            myViewHolderList.get(i).home_xianshi_time_text_minutes.setText(data.get(myViewHolderList.get(i).position).getMinute());
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
        void onItemClick(View view, int position, String goods_id, String search_attr);

        void onItemLongClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView xianshi_img;
        TextView xianshi_name, xianshi_group_num, xianshi_group_flag, xianshi_jiage, xianshi_yuanjia, home_add_xianshi_car;
        RoundTextView home_xianshi_time_text_days, home_xianshi_time_text_hours, home_xianshi_time_text_minutes;
        RelativeLayout group_go_item_layout;
        private int position;

        public MyViewHolder(View view) {
            super(view);
            xianshi_img = itemView.findViewById(R.id.xianshi_img);
            xianshi_name = itemView.findViewById(R.id.xianshi_name);
            xianshi_group_num = itemView.findViewById(R.id.xianshi_group_num);
            xianshi_group_flag = itemView.findViewById(R.id.xianshi_group_flag);
            xianshi_jiage = itemView.findViewById(R.id.xianshi_jiage);
            xianshi_yuanjia = itemView.findViewById(R.id.xianshi_yuanjia);
            home_add_xianshi_car = itemView.findViewById(R.id.home_add_xianshi_car);

            home_xianshi_time_text_days = itemView.findViewById(R.id.home_xianshi_time_text_days);
            home_xianshi_time_text_hours = itemView.findViewById(R.id.home_xianshi_time_text_hours);
            home_xianshi_time_text_minutes = itemView.findViewById(R.id.home_xianshi_time_text_minutes);

            group_go_item_layout = itemView.findViewById(R.id.group_go_item_layout);



        }

        private void setDataPosition(int position) {
            this.position = position;
        }

    }

}
