package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.HomeTab1Bean;
import com.vinnlook.www.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Time:2021/3/31$
 * @Author:pk$
 */
public class InnerAdapter extends BaseAdapter {

    List<HomeTab1Bean.HeadBannerBean> objs = new ArrayList<>();
    Context context;
    private int cardWidth;
    private int cardHeight;


    public InnerAdapter(Context contexts) {
        this.context = contexts;
    }

    public void addAll(List<HomeTab1Bean.HeadBannerBean> collection) {
        if (isEmpty()) {
            objs.addAll(collection);
            notifyDataSetChanged();
        } else {
            objs.addAll(collection);
        }
    }

    public void clear() {
        objs.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return objs.isEmpty();
    }

    public void remove(int index) {
        if (index > -1 && index < objs.size()) {
            objs.remove(index);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return objs.size();
    }

    @Override
    public HomeTab1Bean.HeadBannerBean getItem(int position) {
        if (objs == null || objs.size() == 0) return null;
        return objs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO: getView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        HomeTab1Bean.HeadBannerBean talent = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_new_item, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            float density = dm.density;
            cardWidth = (int) (dm.widthPixels - (2 * 18 * density));
            cardHeight = (int) (dm.heightPixels - (338 * density));
            convertView.getLayoutParams().width = cardWidth;
            holder.portraitView = (ImageView) convertView.findViewById(R.id.portrait);
            //holder.portraitView.getLayoutParams().width = cardWidth;
            holder.portraitView.getLayoutParams().height = cardHeight;
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Log.e("适配器","====="+talent.getPhoto());

        ImageLoader.image(context, holder.portraitView, talent.getPhoto());


        //holder.jobView.setText(talent.jobName);

        final CharSequence no = "暂无";
        return convertView;
    }

    private static class ViewHolder {
        ImageView portraitView;

    }


}


