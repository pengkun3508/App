package com.vinnlook.www.surface.dialog;

import android.animation.Animator;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vinnlook.www.R;
import com.vinnlook.www.surface.adapter.ScreenAdapter;
import com.vinnlook.www.surface.bean.ClassifyTypeBean;
import com.vinnlook.www.surface.mvp.presenter.ClassifyFragmentPresenter;

import java.util.List;

import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;

public class ScreenDialog {

    private final Activity mActivity;
    List<ClassifyTypeBean> classifyTypeBean;

    ClassifyFragmentPresenter classifyFragmentPresenter;
    static String count;
    ClassifDialogOnClickAll classifDialogOnClickAll;
    static TextView scree_count;
    String iDStr = "";
    String iDStr1 = "";
    static AnyLayer mAnyLayer;

    ScreenAdapter screenAdapter;
    TextView move_add_shopcat_btn, tv_move_about;

    public static ScreenDialog with(Activity activity, List<ClassifyTypeBean> classifyTypeBean, ClassifyFragmentPresenter classifyFragmentPresenter, String count, String iDStr, String iDStr1, ClassifDialogOnClickAll classifDialogOnClickAll) {
        return new ScreenDialog(activity, classifyTypeBean, classifyFragmentPresenter, count, iDStr, iDStr1, classifDialogOnClickAll);
    }

    public static void setCount(String counts) {
        count = counts;
        if (mAnyLayer != null) {
            scree_count.setText(count);
        }
    }

    private ScreenDialog(Activity activity, List<ClassifyTypeBean> classifyTypeBean, ClassifyFragmentPresenter classifyFragmentPresenter, String count, String iDStr, String iDStr1, ClassifDialogOnClickAll classifDialogOnClickAll) {
        this.mActivity = activity;
        this.classifyTypeBean = classifyTypeBean;
        this.classifyFragmentPresenter = classifyFragmentPresenter;
        this.count = count;
        this.classifDialogOnClickAll = classifDialogOnClickAll;
        this.iDStr = iDStr;
        this.iDStr1 = iDStr1;
        Log.e("ScreenDialog", "==iDStr==" + iDStr);
        Log.e("ScreenDialog", "==iDStr11111==" + iDStr1);
    }


    public void show() {
        mAnyLayer = AnyLayer.with(mActivity)
                .contentView(R.layout.dialog_screen)
                .bindData(new AnyLayer.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyDialog) {
                        RecyclerView recyclerView = anyDialog.getView(R.id.recyclerView);
                        scree_count = anyDialog.getView(R.id.scree_count);
                        move_add_shopcat_btn = anyDialog.getView(R.id.move_add_shopcat_btn);//充值
                        tv_move_about = anyDialog.getView(R.id.tv_move_about);//确定
                        scree_count.setText(count);
                        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                        screenAdapter = new ScreenAdapter(classifyFragmentPresenter, new ScreenAdapter.ClassifyTypeOnClickAll() {
                            @Override
                            public void onTypeClickListener(String str, String str1) {

                                classifDialogOnClickAll.onDialogClickListener(str, str1);
                            }
                        });

                        recyclerView.setAdapter(screenAdapter);
                        screenAdapter.setData(classifyTypeBean);
                        screenAdapter.setDatas(iDStr,iDStr1);

                        //重置
                        move_add_shopcat_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                for (int i = 0; i < screenAdapter.getData().size(); i++) {
//                                    for (int j = 0; j < screenAdapter.getData().get(i).getSon_list().size(); j++) {
//                                        screenAdapter.getData().get(i).getSon_list().get(j).setType("1");
//
//                                    }
//                                }
//                                screenAdapter.notifyDataSetChanged();
                                screenAdapter.setData(classifyTypeBean);
                                screenAdapter.setDatas("","");
                                scree_count.setText(count);
                                classifDialogOnClickAll.onDialogClickListener("", "");

                            }
                        });

                        //确定
                        tv_move_about.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mAnyLayer.dismiss();
                            }
                        });


                    }
                })
                .backgroundColorRes(R.color.dialog_bg)
                .gravity(Gravity.RIGHT)
                .contentAnim(new AnyLayer.IAnim() {
                    @Override
                    public Animator inAnim(View target) {
                        return AnimHelper.createRightAlphaInAnim(target);
                    }

                    @Override
                    public Animator outAnim(View target) {
                        return AnimHelper.createRightAlphaOutAnim(target);
                    }
                });


        mAnyLayer.show();
    }

    public interface ClassifDialogOnClickAll {
        void onDialogClickListener(String str, String str1);

//        void onResetClickListener();
    }


}
