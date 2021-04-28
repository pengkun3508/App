package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dm.lib.core.listener.OnClickListenerWithoutLogin;

/**
 * @author Yanbo
 * @date 2018/7/11-下午1:01
 */
public class WelcomePagerAdapter extends PagerAdapter {

    private final Context context;
    @DrawableRes
    private int[] images = null;
    private OnImageViewClickListener listener = null;

    public WelcomePagerAdapter(Context context) {
        this.context = context;
    }

    public void setImages(@DrawableRes int[] images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images[position]);
        imageView.setOnClickListener(new OnClickListenerWithoutLogin() {
            @Override
            public void onClickWithoutLogin(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void setOnImageViewClickListener(OnImageViewClickListener listener) {
        this.listener = listener;
    }

    public interface OnImageViewClickListener {
        void onClick(int position);
    }
}
