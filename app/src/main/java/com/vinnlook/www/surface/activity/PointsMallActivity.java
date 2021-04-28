package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.PointsMallAdapter;
import com.vinnlook.www.surface.bean.ExchangeBean;
import com.vinnlook.www.surface.bean.PointsMallBean;
import com.vinnlook.www.surface.mvp.presenter.PointsMallPresenter;
import com.vinnlook.www.surface.mvp.view.PointsMallView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:积分商城
 * @Time:2020/10/26$
 * @Author:pk$
 */
public class PointsMallActivity extends BaseActivity<PointsMallPresenter> implements PointsMallView, PointsMallAdapter.OnItemReceiveClickListener {


    @BindView(R.id.points_jifen_text)
    TextView pointsJifenText;
    @BindView(R.id.points_jifen_text_yi)
    TextView pointsJifenTextYi;
    @BindView(R.id.points_mall_recyclerView)
    RecyclerView pointsMallRecyclerView;
    @BindView(R.id.points_jifen_guize_layout)
    LinearLayout pointsJifenGuizeLayout;
    @BindView(R.id.bg_img)
    ImageView bgImg;


    PointsMallAdapter adapter;
    public PopupWindow popupwindow;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, PointsMallActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_points_mall;
    }

    @Override
    protected PointsMallPresenter initPresenter() {
        return new PointsMallPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);


        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);                        //动画执行时间
        scaleAnimation.setRepeatCount(-1);                    //-1表示重复执行动画
        scaleAnimation.setRepeatMode(Animation.REVERSE);    //重复 缩小和放大效果
        bgImg.startAnimation(scaleAnimation);

        ScaleAnimation scaleAnimation1 = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation1.setDuration(2000);                        //动画执行时间
        scaleAnimation1.setRepeatCount(-1);                    //-1表示重复执行动画
        scaleAnimation1.setRepeatMode(Animation.REVERSE);    //重复 缩小和放大效果
        pointsJifenText.startAnimation(scaleAnimation1);

        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        pointsMallRecyclerView.setLayoutManager(manager3);
        pointsMallRecyclerView.setNestedScrollingEnabled(false);
        pointsMallRecyclerView.setHasFixedSize(true);
        adapter = new PointsMallAdapter(this);
        pointsMallRecyclerView.setAdapter(adapter);

        //积分规则
        pointsJifenGuizeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView2();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
            }
        });


    }

    /**
     * 积分规则
     */
    private void initmPopupWindowView2() {
        WebView jifen_guize_web;
        LinearLayout guize_que_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.jifen_guize_layout, null, false);
        jifen_guize_web = customView.findViewById(R.id.jifen_guize_web);//取消
        guize_que_btn = customView.findViewById(R.id.guize_que_btn);
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        //popupwindow.setAnimationStyle(R.style.AnimationFade);

        WebSettings webSettings = jifen_guize_web.getSettings();
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");

        jifen_guize_web.setVerticalScrollBarEnabled(false); //垂直不显示
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        guize_que_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });


        jifen_guize_web.loadUrl("http://shop.jealook.com/v1/html/article-info?id=156");


    }

    @Override
    protected void loadData() {
        presenter.getPointsMall();//下载数据
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 数据下载成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getPointsMallSuccess(int code, PointsMallBean data) {

        pointsJifenText.setText(data.getPoints());
        pointsJifenTextYi.setText("已使用积分：" + data.getPay_points());
        adapter.setData(data.getDiscount());


    }

    /**
     * 数据下载失败
     *
     * @param code
     * @param
     */
    @Override
    public void getPointsMallFail(int code, String msg) {


    }

    /**
     * 兑换优惠券成功
     *
     * @param code
     * @param
     */
    @Override
    public void getExchangeSuccess(int code, ExchangeBean data) {
        pointsJifenText.setText(data.getPoints());
        pointsJifenTextYi.setText("已使用积分：" + data.getPay_points());
        Toast.makeText(this, "兑换成功，请到礼券红包中查看", Toast.LENGTH_SHORT).show();

    }

    /**
     * 兑换优惠券失败
     *
     * @param code
     * @param
     */
    @Override
    public void getExchangeFail(int code, String msg) {
        if (code == 4000) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 兑换优惠券
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClicked(View view, int position) {
        if (popupwindow != null && popupwindow.isShowing()) {
            popupwindow.dismiss();
            return;
        } else {
            initmPopupWindowView4(position);
            popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }


    }

    /**
     * 请确认是否兑换该积分？
     */
    private void initmPopupWindowView4(int position) {
        TextView return_update_btn, sure_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.tips_jifen_layout, null, false);
        return_update_btn = customView.findViewById(R.id.return_update_btn);
        sure_btn = customView.findViewById(R.id.sure_btn);
        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });
        //返回
        return_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
            }
        });
        //确定
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
                presenter.getExchange(adapter.getData().get(position).getId());
            }
        });

    }
}
