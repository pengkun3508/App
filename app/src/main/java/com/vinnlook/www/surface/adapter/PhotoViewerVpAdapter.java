package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.TouchImageViewer;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/2/22
 */
public class PhotoViewerVpAdapter extends PagerAdapter {
    List<String> images;
    Context context;


    List<TouchImageViewer> viewSet = new ArrayList<>();

    private TouchImageViewer preView;

    public PhotoViewerVpAdapter(List<String> images, Context context) {

        this.context = context;
        this.images = images;
    }

    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return images.size();
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        TouchImageViewer touchImageViewers = new TouchImageViewer(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        touchImageViewers.setLayoutParams(params);

        if (!viewSet.contains(touchImageViewers))
            viewSet.add(touchImageViewers);
        ImageLoader.image(context, touchImageViewers, images.get(position));
        view.addView(touchImageViewers);
        return touchImageViewers;
    }


    public void refresh() {

        for (TouchImageViewer touchImageViewer : viewSet) {
            touchImageViewer.resetZoom();
        }

    }

}
