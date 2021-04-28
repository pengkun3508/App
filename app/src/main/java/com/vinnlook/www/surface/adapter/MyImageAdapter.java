package com.vinnlook.www.surface.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import com.github.chrisbanes.photoview.PhotoView;
import com.vinnlook.www.utils.ImageLoader;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/27$
 * @Author:pk$
 */
public class MyImageAdapter extends PagerAdapter {
    public static final String TAG = MyImageAdapter.class.getSimpleName();
    private List<String> imageUrls;
    private AppCompatActivity activity;

    public MyImageAdapter(List<String> imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = imageUrls.get(position);
        PhotoView photoView = new PhotoView(activity);
        ImageLoader.image(activity,photoView,url);
//        Picasso.with(activity)
//                .load(url)
//                .into(photoView);
        container.addView(photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                activity.finish();
            }
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}