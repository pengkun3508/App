package com.vinnlook.www.surface.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.vinnlook.www.R;
import com.vinnlook.www.surface.adapter.PhotoViewerVpAdapter;
import com.vinnlook.www.widgat.TouchImageViewer;

import java.text.MessageFormat;
import java.util.List;

import per.goweii.anylayer.AnyLayer;

/**
 * 描述：
 *
 * @author Yanbo
 * @date 2019/4/2
 */
public class PhoneLookDialog {

    private AnyLayer mAnyLayer;
    private OnPhoneDeleteListener onPhoneDeleteListener;
    private Activity activity;
    private List<String> list;
    private int position;
    private PhotoViewerVpAdapter photoViewerVpAdapter;
    private ViewPager view;
    private TextView tvTitle;
    private TouchImageViewer imageView;

    public static PhoneLookDialog with(Activity activity, List<String> list, int position) {
        return new PhoneLookDialog(activity, list, position, null);
    }

    public static PhoneLookDialog with(Activity activity, List<String> list, int position, OnPhoneDeleteListener onPhoneDeleteListener) {
        return new PhoneLookDialog(activity, list, position, onPhoneDeleteListener);
    }

    private PhoneLookDialog(Activity activity, List<String> list, int position, OnPhoneDeleteListener onPhoneDeleteListener) {
        this.activity = activity;
        this.list = list;
        this.position = position;
        this.onPhoneDeleteListener = onPhoneDeleteListener;
        show();
    }

    private void show() {
        mAnyLayer = AnyLayer.with(activity);
        mAnyLayer.contentView(R.layout.dialog_phone_look);
        mAnyLayer.gravity(Gravity.CENTER);
        mAnyLayer.cancelableOnTouchOutside(true);
        mAnyLayer.cancelableOnClickKeyBack(true);
        mAnyLayer.bindData(new AnyLayer.IDataBinder() {
            @Override
            public void bind(final AnyLayer anyLayer) {
                tvTitle = anyLayer.getView(R.id.tv_title);
                view = anyLayer.getView(R.id.vp_view);
                ImageView tvDelete = anyLayer.getView(R.id.iv_delete);
                photoViewerVpAdapter = new PhotoViewerVpAdapter(list, activity);
                view.setAdapter(photoViewerVpAdapter);
                tvTitle.setText(MessageFormat.format("{0}/{1}", position + 1, list.size()));
                view.setCurrentItem(position);
                if (onPhoneDeleteListener != null) {
                    tvDelete.setVisibility(View.VISIBLE);
                } else {
                    tvDelete.setVisibility(View.GONE);
                }
                view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int i) {
                        position = i;
                        tvTitle.setText(MessageFormat.format("{0}/{1}", i + 1, list.size()));
                        photoViewerVpAdapter.refresh();
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
            }
        }).onClick(R.id.iv_back, new AnyLayer.OnLayerClickListener() {
            @Override
            public void onClick(AnyLayer anyLayer, View v) {
                anyLayer.dismiss();
            }
        }).onClick(R.id.iv_delete, new AnyLayer.OnLayerClickListener() {
            @Override
            public void onClick(AnyLayer anyLayer, View v) {
                if (onPhoneDeleteListener != null) {
                    onPhoneDeleteListener.onDeleteClick(position);
                    list.remove(position);
                    String positions;
                    if (position == 0) {
                        positions = "1";
                    } else {
                        positions = String.valueOf(position);
                    }
                    tvTitle.setText(MessageFormat.format("{0}/{1}", positions, list.size()));
                    photoViewerVpAdapter = new PhotoViewerVpAdapter(list, activity);
                    view.setAdapter(photoViewerVpAdapter);
                    position = Integer.valueOf(positions) - 1;
                    view.setCurrentItem(position);
                    if (photoViewerVpAdapter.getCount() < 1) {
                        anyLayer.dismiss();
                    }
                }
            }
        });
        mAnyLayer.show();
    }

    public interface OnPhoneDeleteListener {
        void onDeleteClick(int index);
    }

}
