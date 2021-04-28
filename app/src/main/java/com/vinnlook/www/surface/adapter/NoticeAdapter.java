package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.NoticeListBean;

/**
 * @Description:
 * @Time:2020/4/14$
 * @Author:pk$
 */
public class NoticeAdapter extends BaseStateAdapter<NoticeListBean.ListBean, NoticeAdapter.NoticeHolder> {
    Context context;

    public NoticeAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected NoticeHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new NoticeHolder(inflate(parent, R.layout.notice_item));
    }

    class NoticeHolder extends BaseHolder<NoticeListBean.ListBean> {
        TextView notice_time_1, notice_time_2, notice_title;


        NoticeHolder(View itemView) {
            super(itemView);
            notice_time_1 = getView(R.id.notice_time_1);//订单号
            notice_time_2 = getView(R.id.notice_time_2);//复制
            notice_title = getView(R.id.notice_title);//图片

        }

        @Override
        public void bindData(NoticeListBean.ListBean data) {
            notice_time_1.setText(data.getAdd_time_day());
            notice_time_2.setText(data.getAdd_time());
            notice_title.setText(data.getTitle());


        }
    }


}

