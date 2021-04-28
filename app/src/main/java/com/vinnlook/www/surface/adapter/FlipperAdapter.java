package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.OrderDetailsActivity;
import com.vinnlook.www.surface.bean.PersonalInformationBean;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2020/12/1$
 * @Author:pk$
 */
public class FlipperAdapter extends BaseAdapter {


    private List<PersonalInformationBean.WaybillListBean> list;

    Context context;

    public FlipperAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public void setList(List<PersonalInformationBean.WaybillListBean> list) {
        if (this.list.size() > 0) {
            this.list = new ArrayList<>();
        }
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public PersonalInformationBean.WaybillListBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ayto_poll_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PersonalInformationBean.WaybillListBean item = getItem(position);
        holder.wuliu_content.setText(item.getInfo().getAcceptStation());
        ImageLoader.image(context, holder.wuliu_img, item.getImage());
        holder.wuliu_layout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderDetailsActivity.startSelf(context, item.getOrder_id());
            }
        });


        return convertView;
    }


    private static class ViewHolder {
        private TextView wuliu_content;
        private RoundedImageView wuliu_img;
        private LinearLayout wuliu_layout_btn;

        public ViewHolder(View view) {
            this.wuliu_content = view.findViewById(R.id.wuliu_content);
            this.wuliu_img = view.findViewById(R.id.wuliu_img);
            this.wuliu_layout_btn = view.findViewById(R.id.wuliu_layout_btn);
        }
    }
}