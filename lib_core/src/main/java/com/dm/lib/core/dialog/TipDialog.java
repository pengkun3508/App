package com.dm.lib.core.dialog;

import android.animation.Animator;
import android.content.Context;
import androidx.annotation.ColorInt;
import androidx.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.dm.lib.core.R;
import com.dm.lib.core.listener.SimpleCallback;

import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;


/**
 * @author Cuizhen
 * @date 2018/6/21-上午10:00
 */
public class TipDialog {
    private AnyLayer anyLayer;

    public AnyLayer getAnyLayer() {
        return anyLayer;
    }

    private static final long ANIM_DURATION = 200;
    private final Context context;
    private CharSequence title;
    private CharSequence msg;
    private CharSequence yesText;
    private CharSequence noText;
    private int yesTextColor;
    private int noTextColor;
    private boolean singleBtnYes = false;
    private boolean cancelable = true;
    private SimpleCallback<Void> callbackYes = null;
    private SimpleCallback<Void> callbackNo = null;
    private SimpleCallback<Void> onDismissListener = null;

    private TipDialog(Context context) {
        this.context = context;
    }

    public static TipDialog with(Context context) {
        return new TipDialog(context);
    }

    public TipDialog yesText(CharSequence yesText) {
        this.yesText = yesText;
        return this;
    }

    public TipDialog yesText(@StringRes int yesText) {
        this.yesText = context.getString(yesText);
        return this;
    }

    public TipDialog yesTextColor(@ColorInt int yesTextColor) {
        this.yesTextColor = yesTextColor;
        return this;
    }

    public TipDialog noTextColor(@ColorInt int noTextColor) {
        this.noTextColor = noTextColor;
        return this;
    }

    public TipDialog noText(CharSequence noText) {
        this.noText = noText;
        return this;
    }

    public TipDialog noText(@StringRes int noText) {
        this.noText = context.getString(noText);
        return this;
    }

    public TipDialog title(CharSequence title) {
        this.title = title;
        return this;
    }

    public TipDialog title(@StringRes int title) {
        this.title = context.getString(title);
        return this;
    }

    public TipDialog message(CharSequence msg) {
        this.msg = msg;
        return this;
    }

    public TipDialog message(@StringRes int msg) {
        this.msg = context.getString(msg);
        return this;
    }

    public TipDialog singleYesBtn() {
        singleBtnYes = true;
        return this;
    }

    public TipDialog cancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public TipDialog onYes(SimpleCallback<Void> callback) {
        callbackYes = callback;
        return this;
    }

    public TipDialog onNo(SimpleCallback<Void> callback) {
        callbackNo = callback;
        return this;
    }

    public TipDialog onDismissListener(SimpleCallback<Void> onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public void show() {
        anyLayer =AnyLayer.with(context)
                .contentView(R.layout.dialog_tip)
                .backgroundColorRes(R.color.dialog_bg)
                .cancelableOnTouchOutside(cancelable)
                .cancelableOnClickKeyBack(cancelable)
                .onVisibleChangeListener(new AnyLayer.OnVisibleChangeListener() {
                    @Override
                    public void onShow(AnyLayer anyLayer) {
                    }

                    @Override
                    public void onDismiss(AnyLayer anyLayer) {
                        if (onDismissListener != null) {
                            onDismissListener.onResult(null);
                        }
                    }
                })
                .bindData(new AnyLayer.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyLayer) {
                        TextView tv_dialog_yes = anyLayer.getView(R.id.tv_dialog_yes);
                        TextView tv_dialog_no = anyLayer.getView(R.id.tv_dialog_no);
                        View tv_dialog_line_v = anyLayer.getView(R.id.tv_dialog_line_v);

                        if (singleBtnYes) {
                            tv_dialog_no.setVisibility(View.GONE);
                            tv_dialog_line_v.setVisibility(View.GONE);
                        } else {
                            if (noText != null) {
                                tv_dialog_no.setText(noText);
                            } else {
                                tv_dialog_no.setText(R.string.dialog_btn_no);
                            }
                            if (noTextColor != 0) {
                                tv_dialog_no.setTextColor(noTextColor);
                            }
                        }

                        if (yesText != null) {
                            tv_dialog_yes.setText(yesText);
                        } else {
                            tv_dialog_yes.setText(R.string.dialog_btn_yes);
                        }
                        if (yesTextColor != 0) {
                            tv_dialog_yes.setTextColor(yesTextColor);
                        }


                        TextView tv_dialog_title = anyLayer.getView(R.id.tv_dialog_title);
                        if (title == null) {
                            tv_dialog_title.setVisibility(View.GONE);
                        } else {
                            tv_dialog_title.setText(title);
                        }

                        TextView tv_dialog_content = anyLayer.getView(R.id.tv_dialog_content);
                        tv_dialog_content.setText(msg);
                    }
                })
                .gravity(Gravity.CENTER)
                .contentAnim(new AnyLayer.IAnim() {
                    @Override
                    public Animator inAnim(View target) {
                        return AnimHelper.createBottomAlphaInAnim(target);
                    }

                    @Override
                    public Animator outAnim(View target) {
                        return AnimHelper.createBottomAlphaOutAnim(target);
                    }
                })

                .onClick(new AnyLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        anyLayer.dismiss();
                        if (callbackYes != null) {
                            callbackYes.onResult(null);
                        }
                    }
                }, R.id.tv_dialog_yes)
                .onClick(new AnyLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        anyLayer.dismiss();
                        if (callbackNo != null) {
                            callbackNo.onResult(null);
                        }
                    }
                },R.id.dialog_layout)
                .onClick(new AnyLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        anyLayer.dismiss();
                        if (callbackNo != null) {
                            callbackNo.onResult(null);
                        }
                    }
                },R.id.tv_dialog_no);
        anyLayer.show();
    }
}
