package com.vinnlook.www.widgat;


import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/3/11
 */
public class CardTransformer implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.1f;
    private static final float MIN_SCALE = 0.9f;//0.85f

    @Override
    public void transformPage(View page, float position) {
        if (position <= 1) {
            //   1.2f + (1-1)*(1.2-1.0)
            float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);

            page.setScaleX(scaleFactor);  //缩放效果

            if (position > 0) {
                page.setTranslationX(-scaleFactor * 2);
            } else if (position < 0) {
                page.setTranslationX(scaleFactor * 2);
            }
            page.setScaleY(scaleFactor);
        } else {

            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }

}
