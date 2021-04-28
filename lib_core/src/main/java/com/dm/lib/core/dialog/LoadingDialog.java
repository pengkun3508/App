package com.dm.lib.core.dialog;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import com.dm.lib.core.R;

import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;


/**
 * loading弹窗
 *
 * @author Cuizhen
 * @date 2018/6/21-上午10:00
 */
public class LoadingDialog {

    private static final long ANIM_DURATION = 200;
    private final Context context;
    private int count = 0;
    private AnyLayer anyLayer;

    private LoadingDialog(Context context) {
        this.context = context;
    }

    public static LoadingDialog with(Context context) {
        return new LoadingDialog(context);
    }

    public void show() {
        if (count <= 0) {
            count = 0;
            anyLayer = AnyLayer.with(context)
                    .contentView(R.layout.dialog_loading)
                    .backgroundColorInt(Color.TRANSPARENT)
                    .cancelableOnClickKeyBack(false)
                    .cancelableOnTouchOutside(false)
                    .gravity(Gravity.CENTER)
                    .contentAnim(new AnyLayer.IAnim() {
                        @Override
                        public Animator inAnim(View target) {
                            return AnimHelper.createZoomInAnim( target).setDuration(ANIM_DURATION);
                        }

                        @Override
                        public Animator outAnim(View target) {
                            return AnimHelper.createZoomOutAnim( target).setDuration(ANIM_DURATION);

                        }
                    });
            anyLayer.show();
        }
        count++;
    }

//    public void show2() {
//        if (count <= 0) {
//            count = 0;
//            mAnyDialog = AnyDialog.with(context)
//                    .contentView(R.layout.dialog_loading)
//                    .backgroundColorInt(Color.TRANSPARENT)
//                    .cancelableOnClickKeyBack(false)
//                    .cancelableOnTouchOutside(false)
//                    .gravity(Gravity.CENTER)
//                    .contentAnim(new IAnim() {
//                        @Override
//                        public Animator inAnim(View target) {
//
//                            return AnimHelper.createZoomInAnim( target);
//                        }
//
//                        @Override
//                        public Animator outAnim(View target) {
//                            return AnimHelper.createZoomOutAnim( target);
//
//                        }
//                    });
//            mAnyDialog.show();
//        }
//        count++;
//    }

    public void dismiss() {
        count--;
        if (count <= 0){
            clear();
        }
    }

    public void clear(){
        if (anyLayer != null) {
            anyLayer.dismiss();
            anyLayer = null;
        }
        count = 0;
    }

//    public void clear2(){
//        if (mAnyDialog != null) {
//            mAnyDialog.dismiss();
//            mAnyDialog = null;
//        }
//        count = 0;
//    }
}
