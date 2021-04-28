package com.vinnlook.www.surface.dialog;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Matrix;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.core.listener.SimpleCallback;
import com.vinnlook.www.R;
import com.vinnlook.www.common.Config;
import com.vinnlook.www.event.GuangGaoEvent;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.SPAppUpdateUtils;

import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;

/**
 * 版本更新弹窗--提示
 *
 * @author Cuizhen
 * @date 2018/8/6-上午9:17
 */
public class UpdateDialog {
    private static final long ANIM_DURATION = Config.DIALOG_ANIM_DURATION;

    private final VersionBean mVersionBean;
    private final Activity mActivity;

    private boolean isBtnYesClicked = false;

    private SimpleCallback<Void> onDismissListener = null;

    private UpdateDialog(Activity activity, VersionBean bean) {
        this.mVersionBean = bean;
        this.mActivity = activity;
    }

    public static UpdateDialog with(Activity activity, VersionBean bean) {
        return new UpdateDialog(activity, bean);
    }

    public void show() {
        AnyLayer mAnyLayer = AnyLayer.with(mActivity)
                .contentView(R.layout.dialog_update_1)
                .cancelableOnTouchOutside(false)
                .cancelableOnClickKeyBack(false)
                .onVisibleChangeListener(new AnyLayer.OnVisibleChangeListener() {
                    @Override
                    public void onShow(AnyLayer popupView) {
                        isBtnYesClicked = false;
                    }

                    @Override
                    public void onDismiss(AnyLayer popupView) {
                        if (isBtnYesClicked) {
                            DownloadDialog.with(mActivity, mVersionBean.getMust() == 1, mVersionBean.getUrl());
                        }
                        if (onDismissListener != null) {
                            onDismissListener.onResult(null);
                        }
                    }
                })
                .bindData(new AnyLayer.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyDialog) {
                        ;
//                        View view_line = anyDialog.getView(R.id.view_update_line);
                        ImageView dialog_update_img = anyDialog.getView(R.id.dialog_update_img);
                        ImageView dialog_update_close = anyDialog.getView(R.id.dialog_update_close);
                        final TextView tv_dialog_update_content = anyDialog.getView(R.id.tv_dialog_update_content);

//                        tv_dialog_update_content.setText(mVersionBean.getDescription());
                        Log.e("更新版本", "下载图片地址===" + mVersionBean.getUrl());
                        Log.e("更新版本", "图片地址===" + mVersionBean.getImgUrl());
//                        dialog_update_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Matrix matrix = new Matrix();           //创建一个单位矩阵
                        matrix.setTranslate(0, 0);          //平移x和y各100单位
                        matrix.preRotate(0);                   //顺时针旋转30度
                        dialog_update_img.setScaleType(ImageView.ScaleType.MATRIX);
                        dialog_update_img.setImageMatrix(matrix);

                        ImageLoader.image(mActivity, dialog_update_img, mVersionBean.getImgUrl());

                        if (mVersionBean.getMust() == 2) {//1：最新版本；2：强制更新；3：提示更新；4：手动更新
                            dialog_update_close.setVisibility(View.GONE);
                            Toast.makeText(mActivity, "您有新版本需要更新", Toast.LENGTH_SHORT).show();
                        } else if (mVersionBean.getMust() == 3) {//1：最新版本；2：强制更新；3：提示更新；4：手动更新
                            dialog_update_close.setVisibility(View.VISIBLE);
                        }


                    }
                })
                .backgroundColorRes(R.color.dialog_bg)
                .gravity(Gravity.CENTER)
                .contentAnim(new AnyLayer.IAnim() {
                    @Override
                    public Animator inAnim(View target) {
                        return AnimHelper.createBottomAlphaInAnim(target).setDuration(ANIM_DURATION);
                    }

                    @Override
                    public Animator outAnim(View target) {
                        return AnimHelper.createBottomAlphaOutAnim(target).setDuration(ANIM_DURATION);
                    }
                })
                .onClick(new AnyLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyDialog, View v) {
                        anyDialog.dismiss();
                        isBtnYesClicked = true;
                        final SPAppUpdateUtils utils = SPAppUpdateUtils.instance();
                        utils.setIgnore(Integer.valueOf(mVersionBean.getCode()));

                    }
                }, R.id.dialog_update_img)//立即更新
                .onClick(new AnyLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyDialog, View v) {
                        anyDialog.dismiss();
                        if (mVersionBean.getMust() != 2) {//1：最新版本；2：强制更新；3：提示更新；4：手动更新
                        //1：最新版本；2：强制更新；3：提示更新；4：手动更新
                            final SPAppUpdateUtils utils = SPAppUpdateUtils.instance();
                            utils.setIgnore(Integer.valueOf(mVersionBean.getCode()));
                            new GuangGaoEvent(true).post();
                        }
                    }
                }, R.id.dialog_update_close);//暂不更新
        mAnyLayer.show();
    }

//    private boolean isForce() {
//        return mVersionBean.getMust() == 1;

//    }

    public UpdateDialog setOnDismissListener(SimpleCallback<Void> callback) {
        onDismissListener = callback;
        return this;
    }
}
