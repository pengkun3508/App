package com.vinnlook.www.surface.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundLinearLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainShoppingEvent;
import com.vinnlook.www.indicator.NumIndicator;
import com.vinnlook.www.surface.adapter.ArticleDetailsPopup_1Adapter;
import com.vinnlook.www.surface.adapter.BannerImgAdapter9;
import com.vinnlook.www.surface.adapter.EyeArticleAdapter;
import com.vinnlook.www.surface.adapter.EyeChartGoodsAdapter;
import com.vinnlook.www.surface.bean.EyeChartDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.SelectEyeChartPresenter;
import com.vinnlook.www.surface.mvp.view.SelectEyeChartView;
import com.vinnlook.www.utils.AutoSplitTextView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.StaggeredDividerItemDecoration;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.FullyStaggeredGridLayoutManager;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 精选眼图--详情页面
 * @Time:2021/7/5$
 * @Author:pk$
 */
public class SelectEyeChartActivity extends BaseActivity<SelectEyeChartPresenter> implements SelectEyeChartView {

    static String iD;
    public PopupWindow popupwindow;
    @BindView(R.id.chart_eye_back)
    ImageView chartEyeBack;
    @BindView(R.id.chart_eye_hear)
    RoundedImageView chartEyeHear;
    @BindView(R.id.chart_eye_name)
    TextView chartEyeName;
    @BindView(R.id.chart_eye_share)
    ImageView chartEyeShare;
    @BindView(R.id.chart_eye_banner)
    Banner chartEyeBanner;
    @BindView(R.id.chart_eye_recy1)
    RecyclerView chartEyeRecy1;
    @BindView(R.id.chart_eye_shop_name)
    AutoSplitTextView chartEyeShopName;
    @BindView(R.id.chart_eye_conten)
    TextView chartEyeConten;
    @BindView(R.id.chart_eye_recy2)
    RecyclerView chartEyeRecy2;
    EyeChartGoodsAdapter goodsAdapter;
    EyeArticleAdapter articleAdapter;
    @BindView(R.id.chart_eye_time)
    TextView chartEyeTime;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.chart_eye_chenk)
    CheckBox chartEyeChenk;
    @BindView(R.id.chart_eye_scroll)
    ScrollView chartEyeScroll;
    @BindView(R.id.imag1)
    ImageView imag1;
    @BindView(R.id.eye_zan_number)
    TextView eyeZanNumber;
    @BindView(R.id.eye_zan_btn)
    LinearLayout eyeZanBtn;
    @BindView(R.id.imag2)
    ImageView imag2;
    @BindView(R.id.eye_shou_number)
    TextView eyeShouNumber;
    @BindView(R.id.eye_shou_btn)
    LinearLayout eyeShouBtn;
    @BindView(R.id.eye_car_btn)
    LinearLayout eyeCarBtn;
    @BindView(R.id.eye_shop_number)
    TextView eyeShopNumber;
    @BindView(R.id.eye_shop_btn)
    RoundLinearLayout eyeShopBtn;
    @BindView(R.id.eye_bottom_layout)
    LinearLayout eyeBottomLayout;
    @BindView(R.id.layout_bottoms)
    RelativeLayout layoutBottoms;
    @BindView(R.id.imag3)
    ImageView imag3;
    @BindView(R.id.tv_move_about)
    TextView tvMoveAbout;
    float alpha = 1f;
    int giveType;//点赞
    int collectType;//收藏
    int getLike_num;
    int getCollect_num;
    int judge;
    EyeChartDetailsBean eyeChartDetailsBean;
    String articleId;
    ArticleDetailsPopup_1Adapter popupAdapter;
    @BindView(R.id.banner_new_num)
    TextView bannerNewNum;
    @BindView(R.id.banner_con_num)
    TextView bannerConNum;
    //弹窗改变背景
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    backgroundAlpha((float) msg.obj);
                    break;
            }
        }
    };

    public static void startSelf(Context context, String iDs) {
        Intent intent = new Intent(context, SelectEyeChartActivity.class);
        context.startActivity(intent);
        iD = iDs;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_eye_chart;
    }

    @Override
    protected SelectEyeChartPresenter initPresenter() {
        return new SelectEyeChartPresenter();
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        chartEyeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        chartEyeBanner.post(new Runnable() {
//            @Override
//            public void run() {
//                chartEyeBanner.getWidth();
//
////                ViewGroup.LayoutParams layoutParams = holder.banner_imgs.getLayoutParams();
////                float itemWidth = (ScreenUtils.getScreenWidth(context) - 16 * 3) / 2;
////                layoutParams.width = (int) itemWidth;
////                float scale = (itemWidth + 0f) / getWidth;
////                layoutParams.height = (int) (getHeight * scale);
//
//                double f = Float.valueOf(chartEyeBanner.getWidth() + "") / (2);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(chartEyeBanner.getWidth(), (int) f);
//                chartEyeBanner.setLayoutParams(layoutParams);
//            }
//        });
//        chartEyeBanner.setIndicator(new NumIndicator(this));
//        chartEyeBanner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
        //banner滑动监听
        chartEyeBanner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bannerNewNum.setText(position + 1 + "");

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod);//下载列表数据
                presenter.getEyeChartData(iD);
                judge = 0;
            }
        });

        //商品
        goodsAdapter = new EyeChartGoodsAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(this, 1);
        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
        chartEyeRecy1.setLayoutManager(manager1);
        chartEyeRecy1.setNestedScrollingEnabled(false);
        chartEyeRecy1.setHasFixedSize(true);
        chartEyeRecy1.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
        chartEyeRecy1.addItemDecoration(new SpaceItemDecoration(10, 10));
        chartEyeRecy1.setAdapter(goodsAdapter);
        goodsAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(SelectEyeChartActivity.this, goodsAdapter.getData().get(position).getGoods_id(), goodsAdapter.getData().get(position).getSearch_attr(), articleId);
            }
        });

        //文章
        articleAdapter = new EyeArticleAdapter(getActivity());
//        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        //垂直方向的2列
        final FullyStaggeredGridLayoutManager layoutManager = new FullyStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //防止Item切换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        chartEyeRecy2.setLayoutManager(layoutManager);

        chartEyeRecy2.setNestedScrollingEnabled(false);
        chartEyeRecy2.setHasFixedSize(true);

//
        chartEyeRecy2.setAdapter(articleAdapter);
        final int spanCount = 2;
        chartEyeRecy2.addItemDecoration(new StaggeredDividerItemDecoration(getActivity(), 0, spanCount));
        //解决底部滚动到顶部时，顶部item上方偶尔会出现一大片间隔的问题
        chartEyeRecy2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int[] first = new int[spanCount];
                layoutManager.findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    layoutManager.invalidateSpanAssignments();
                }
            }
        });

        chartEyeScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int newx, int newy, int oldx, int oldy) {
                if (newy > 1) {
                    eyeBottomLayout.setVisibility(View.VISIBLE);
                } else {
                    eyeBottomLayout.setVisibility(View.GONE);
                }
            }
        });



    }

    @Override
    protected void loadData() {
        presenter.getEyeChartData(iD);

    }

    @OnClick({R.id.eye_zan_btn, R.id.eye_shou_btn, R.id.eye_car_btn, R.id.eye_shop_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eye_zan_btn://点赞
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    Log.e("点赞", "==giveType==" + giveType);
                    presenter.getGiveData(articleId, giveType);
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.eye_shou_btn://收藏
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    Log.e("收藏", "==collectType==" + collectType);
                    presenter.getCollectData(articleId, collectType);
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.eye_car_btn://购物车
                CacheActivity.finishActivity();
                new MainShoppingEvent("10").post();
                break;
            case R.id.eye_shop_btn://商品
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (alpha > 0.5f) {
                                try {
                                    //4是根据弹出动画时间和减少的透明度计算
                                    Thread.sleep(4);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Message msg = mHandler.obtainMessage();
                                msg.what = 1;
                                //每次减少0.01，精度越高，变暗的效果越流畅
                                alpha -= 0.01f;
                                msg.obj = alpha;
                                mHandler.sendMessage(msg);
                            }
                        }

                    }).start();
                    initmPopupWindowView();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                break;
        }
    }

    //商品列表
    private void initmPopupWindowView() {
        LinearLayout popup_close_btn;
        RecyclerView popup_recy;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.popup_goods_list_layout, null, false);
        popup_close_btn = customView.findViewById(R.id.popup_close_btn);//关闭
        popup_recy = customView.findViewById(R.id.popup_recy);//商品List

        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //添加pop窗口关闭事件，主要是实现关闭时改变背景的透明度
        popupwindow.setOnDismissListener(new poponDismissListener());
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(1f);
        //添加弹出、弹入的动画
        popupwindow.setAnimationStyle(R.style.Popupwindow);
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


        //商品

        popupAdapter = new ArticleDetailsPopup_1Adapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(this, 1);
        popup_recy.setLayoutManager(manager1);
        popup_recy.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
        popup_recy.addItemDecoration(new SpaceItemDecoration(10, 10));
        popup_recy.setAdapter(popupAdapter);
        popupAdapter.setData(eyeChartDetailsBean.getGoods_list());

        popup_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });

        popupAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(SelectEyeChartActivity.this, popupAdapter.getData().get(position).getGoods_id(), popupAdapter.getData().get(position).getSearch_attr(), articleId);
            }
        });
    }

    /* 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getEyeChartDataSuccess(int code, EyeChartDetailsBean data) {
        eyeChartDetailsBean = data;
        ImageLoader.userIcon(this, chartEyeHear, data.getImg_url());
        chartEyeName.setText(data.getUser_name());
        chartEyeShopName.setText(data.getName());
        chartEyeConten.setText(data.getContent());
        chartEyeTime.setText(data.getCreate_time());

        articleId = data.getId();//文章ID
        getLike_num = Integer.parseInt(data.getLike_num());
        getCollect_num = Integer.parseInt(data.getCollect_num());

        eyeZanNumber.setText(data.getLike_num());//点赞数量
        eyeShouNumber.setText(data.getCollect_num());//收藏数量
        giveType = data.getIs_like();//点赞状态
        collectType = data.getIs_collect();//收藏状态
        Log.e("点赞状态", "==giveType==" + giveType);
        Log.e("收藏状态", "==collectType==" + collectType);
        if (data.getIs_like() == 1) {//已点赞
            giveType = 0;
            imag1.setBackgroundResource(R.mipmap.article_zan_icon_1);
        } else if (data.getIs_like() == 0) {//未点赞
            giveType = 1;
            imag1.setBackgroundResource(R.mipmap.article_zan_icon);
        }
        if (data.getIs_collect() == 1) {//已收藏
            collectType = 0;
            imag2.setBackgroundResource(R.mipmap.article_xing_icon_1);
        } else if (data.getIs_collect() == 0) {//未收藏
            collectType = 1;
            imag2.setBackgroundResource(R.mipmap.article_xing_icon);
        }

        data.setMaxlines(chartEyeConten.getLineCount()); //保存一个最大行数，在集合对应的对象中
        Log.e("行数", "===getLineCount===" + chartEyeConten.getLineCount());
        chartEyeConten.setMaxLines(3);
        chartEyeChenk.setText("继续阅读");

        if (chartEyeConten.getLineCount() > 4) {
            chartEyeChenk.setVisibility(View.VISIBLE);
        } else {
            chartEyeChenk.setVisibility(View.GONE);
        }

        //当点击按钮发生改变
        chartEyeChenk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.setCheck(isChecked); //这里在你的对象集合中记录一个状态值，防止滑动checkBox选中状态错乱
                if (isChecked) {
                    chartEyeChenk.setText("收起");
                    chartEyeConten.setMaxLines(data.getMaxlines());
                    chartEyeConten.postInvalidate();
                } else {
                    chartEyeConten.setMaxLines(3);
                    chartEyeConten.postInvalidate(); //刷新控件，不加的话，如果下拉刷新列表notifyDataSetChanged()的时候TextView会自动重绘
                    chartEyeChenk.setText("继续阅读");
                }
            }
        });

        List<String> bannerImage = data.getImage();//轮播
        if (bannerImage != null) {
            BannerImgAdapter9 bannerImgAdapter = new BannerImgAdapter9(getActivity(), bannerImage, data.getWidth(), data.getHeight());
            chartEyeBanner.setAdapter(bannerImgAdapter);
            chartEyeBanner.start();
        }
        bannerConNum.setText(chartEyeBanner.getRealCount() + "");
        eyeShopNumber.setText(data.getGoods_list().size() + "");
        if (data.getGoods_list().size() > 0) {
            chartEyeRecy1.setVisibility(View.VISIBLE);
            goodsAdapter.setData(data.getGoods_list());
        } else {
            chartEyeRecy1.setVisibility(View.GONE);
        }

        if (data.getList().size() > 0) {
            chartEyeRecy2.setVisibility(View.VISIBLE);
            Log.e("精选眼图", "===size===" + data.getList().size());
            articleAdapter.replaceAll(data.getList());

        } else {
            chartEyeRecy2.setVisibility(View.GONE);
        }

    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getEyeChartDataFail(int code, String msg) {

    }

    /**
     * 点赞成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getGiveDataSuccess(int code, Object data) {

        if (giveType == 1) {
            giveType = 0;
            Toast.makeText(this, "点赞成功", Toast.LENGTH_SHORT).show();
            imag1.setBackgroundResource(R.mipmap.article_zan_icon_1);
            getLike_num = getLike_num + 1;
            eyeZanNumber.setText(getLike_num + "");//点赞数量

        } else if (giveType == 0) {
            giveType = 1;
            Toast.makeText(this, "取消点赞成功", Toast.LENGTH_SHORT).show();
            imag1.setBackgroundResource(R.mipmap.article_zan_icon);
            getLike_num = getLike_num - 1;
            eyeZanNumber.setText(getLike_num + "");//点赞数量
        }


    }

    /**
     * 点赞失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getGiveDataFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 收藏成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getCollectDataSuccess(int code, Object data) {
        if (collectType == 1) {
            collectType = 0;
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
            imag2.setBackgroundResource(R.mipmap.article_xing_icon_1);
            getCollect_num = getCollect_num + 1;
            eyeShouNumber.setText(getCollect_num + "");//收藏数量
        } else if (collectType == 0) {
            collectType = 1;
            Toast.makeText(this, "取消收藏成功", Toast.LENGTH_SHORT).show();
            imag2.setBackgroundResource(R.mipmap.article_xing_icon);
            getCollect_num = getCollect_num - 1;
            eyeShouNumber.setText(getCollect_num + "");//收藏数量
        }
    }

    /**
     * 收藏失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getCollectDataFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回或者点击空白位置的时候将背景透明度改回来
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //此处while的条件alpha不能<= 否则会出现黑屏
                    while (alpha < 1f) {
                        try {
                            Thread.sleep(4);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message msg = mHandler.obtainMessage();
                        msg.what = 1;
                        alpha += 0.01f;
                        msg.obj = alpha;
                        mHandler.sendMessage(msg);
                    }
                }

            }).start();
        }

    }


}
