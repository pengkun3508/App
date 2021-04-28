package com.vinnlook.www.widgat.actionbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.dm.lib.core.listener.OnClickListenerWithoutLogin;
import com.dm.lib.utils.ResUtils;
import com.vinnlook.www.R;

import per.goweii.actionbarex.ActionBarEx;

/**
 * 描述：通用标题
 *
 * @author Yanbo
 * @date 2018/11/4
 */
public class MoveAboutBarSimple extends ActionBarEx {

    private TextView tvTitle;
    private ImageView ivBack;
    private TextView tvBack;
    private ImageView ivRight;
    private ImageView tvRight;
    private ImageView ivleftIcon;

    private String title;
    private int titleTextColor;
    private int backIconRes;
    private String backText;
    private int rightIconRes;
    private String rightText;
    private int leftIcon;

    public MoveAboutBarSimple(Context context) {
        this(context, null);
    }

    public MoveAboutBarSimple(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveAboutBarSimple(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initAttrs(AttributeSet attrs) {
        super.initAttrs(attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ActionBarSimple);
        title = typedArray.getString(R.styleable.ActionBarSimple_abs_title);
        titleTextColor = typedArray.getColor(R.styleable.ActionBarSimple_abs_title_text_color, ContextCompat.getColor(getContext(), R.color.text_black));
        backIconRes = typedArray.getResourceId(R.styleable.ActionBarSimple_abs_back_icon, 0);
        leftIcon = typedArray.getResourceId(R.styleable.ActionBarSimple_abs_left_icon, 0);
        rightIconRes = typedArray.getResourceId(R.styleable.ActionBarSimple_abs_right_icon, 0);
        backText = typedArray.getString(R.styleable.ActionBarSimple_abs_back_text);
        rightText = typedArray.getString(R.styleable.ActionBarSimple_abs_right_text);
        typedArray.recycle();
    }

    @Override
    protected View inflateTitleBar() {
        View titleBarChild = inflate(getContext(), R.layout.move_about_bar_simple, null);
        tvTitle = titleBarChild.findViewById(R.id.tv_action_bar_simple_title);
        ivBack = titleBarChild.findViewById(R.id.iv_action_bar_simple_back);
        tvBack = titleBarChild.findViewById(R.id.tv_action_bar_simple_back);
        ivRight = titleBarChild.findViewById(R.id.iv_action_bar_simple_right);
        tvRight = titleBarChild.findViewById(R.id.iv_action_bar_simple_right1);
        ivleftIcon = titleBarChild.findViewById(R.id.iv_action_bar_simple_title);

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(VISIBLE);
            tvTitle.setText(title);
            tvTitle.setTextColor(titleTextColor);
        } else {
            tvTitle.setVisibility(GONE);
        }
        if (backIconRes > 0) {
            ivBack.setVisibility(VISIBLE);
            ivBack.setImageResource(backIconRes);
        } else {
            ivBack.setVisibility(GONE);
            if (!TextUtils.isEmpty(backText)) {
                tvBack.setVisibility(VISIBLE);
                tvBack.setText(backText);
            } else {
                tvBack.setVisibility(GONE);
            }
        }

        if (leftIcon > 0) {
            ivleftIcon.setVisibility(VISIBLE);
            ivleftIcon.setImageResource(leftIcon);
        } else {
            ivleftIcon.setVisibility(View.GONE);
        }


        if (rightIconRes > 0) {
            ivRight.setVisibility(VISIBLE);
            ivRight.setImageResource(rightIconRes);
        } else {
            ivRight.setVisibility(GONE);

        }
//        ivRight.setVisibility(VISIBLE);
//        ivRight.setImageDrawable(ResUtils.getDrawable(R.mipmap.customer_service));
//        tvRight.setVisibility(VISIBLE);
//        tvRight.setImageDrawable(ResUtils.getDrawable(R.mipmap.share));
        ivBack.setOnClickListener(new OnClickListenerWithoutLogin() {
            @Override
            public void onClickWithoutLogin(View v) {
                if (onBackClickListener != null) {
                    onBackClickListener.onClick(v);
                } else {
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).finish();
                    }
                }
            }
        });
        tvBack.setOnClickListener(new OnClickListenerWithoutLogin() {
            @Override
            public void onClickWithoutLogin(View v) {
                if (onBackClickListener != null) {
                    onBackClickListener.onClick(v);
                } else {
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).finish();
                    }
                }
            }
        });
        return titleBarChild;
    }

    public void setTitle(String title) {
        tvTitle.setVisibility(VISIBLE);
        tvTitle.setText(title);
    }

    public void setTitle(@StringRes int title) {
        tvTitle.setVisibility(VISIBLE);
        tvTitle.setText(title);
    }

    public ImageView getIvBack() {
        return ivBack;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public ImageView getTvRight() {
        return tvRight;
    }

    private OnClickListener onBackClickListener = null;

    public void setOnBackClickListener(final OnClickListener listener) {
        onBackClickListener = listener;
    }

    public void setOnRightIconClickListener(final OnClickListener listener) {
        ivRight.setOnClickListener(new OnClickListenerWithoutLogin() {
            @Override
            public void onClickWithoutLogin(View v) {
                listener.onClick(v);
            }
        });
    }

    public void setOnRightTextClickListener(final OnClickListener listener) {
        tvRight.setOnClickListener(new OnClickListenerWithoutLogin() {
            @Override
            public void onClickWithoutLogin(View v) {
                listener.onClick(v);
            }
        });
    }

    public interface OnClickListener {
        void onClick(View v);
    }
}
