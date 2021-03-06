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
 * @Description: ????????????--????????????
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
    int giveType;//??????
    int collectType;//??????
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
    //??????????????????
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
        //banner????????????
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
//                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod);//??????????????????
                presenter.getEyeChartData(iD);
                judge = 0;
            }
        });

        //??????
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

        //??????
        articleAdapter = new EyeArticleAdapter(getActivity());
//        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        //???????????????2???
        final FullyStaggeredGridLayoutManager layoutManager = new FullyStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //??????Item??????
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        chartEyeRecy2.setLayoutManager(layoutManager);

        chartEyeRecy2.setNestedScrollingEnabled(false);
        chartEyeRecy2.setHasFixedSize(true);

//
        chartEyeRecy2.setAdapter(articleAdapter);
        final int spanCount = 2;
        chartEyeRecy2.addItemDecoration(new StaggeredDividerItemDecoration(getActivity(), 0, spanCount));
        //???????????????????????????????????????item?????????????????????????????????????????????
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
            case R.id.eye_zan_btn://??????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    Log.e("??????", "==giveType==" + giveType);
                    presenter.getGiveData(articleId, giveType);
                } else {
                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.eye_shou_btn://??????
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    Log.e("??????", "==collectType==" + collectType);
                    presenter.getCollectData(articleId, collectType);
                } else {
                    Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.eye_car_btn://?????????
                CacheActivity.finishActivity();
                new MainShoppingEvent("10").post();
                break;
            case R.id.eye_shop_btn://??????
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (alpha > 0.5f) {
                                try {
                                    //4??????????????????????????????????????????????????????
                                    Thread.sleep(4);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Message msg = mHandler.obtainMessage();
                                msg.what = 1;
                                //????????????0.01??????????????????????????????????????????
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

    //????????????
    private void initmPopupWindowView() {
        LinearLayout popup_close_btn;
        RecyclerView popup_recy;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.popup_goods_list_layout, null, false);
        popup_close_btn = customView.findViewById(R.id.popup_close_btn);//??????
        popup_recy = customView.findViewById(R.id.popup_recy);//??????List

        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //??????pop?????????????????????????????????????????????????????????????????????
        popupwindow.setOnDismissListener(new poponDismissListener());
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(1f);
        //??????????????????????????????
        popupwindow.setAnimationStyle(R.style.Popupwindow);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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


        //??????

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

    /* ????????????????????????????????????
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
     * ??????????????????
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

        articleId = data.getId();//??????ID
        getLike_num = Integer.parseInt(data.getLike_num());
        getCollect_num = Integer.parseInt(data.getCollect_num());

        eyeZanNumber.setText(data.getLike_num());//????????????
        eyeShouNumber.setText(data.getCollect_num());//????????????
        giveType = data.getIs_like();//????????????
        collectType = data.getIs_collect();//????????????
        Log.e("????????????", "==giveType==" + giveType);
        Log.e("????????????", "==collectType==" + collectType);
        if (data.getIs_like() == 1) {//?????????
            giveType = 0;
            imag1.setBackgroundResource(R.mipmap.article_zan_icon_1);
        } else if (data.getIs_like() == 0) {//?????????
            giveType = 1;
            imag1.setBackgroundResource(R.mipmap.article_zan_icon);
        }
        if (data.getIs_collect() == 1) {//?????????
            collectType = 0;
            imag2.setBackgroundResource(R.mipmap.article_xing_icon_1);
        } else if (data.getIs_collect() == 0) {//?????????
            collectType = 1;
            imag2.setBackgroundResource(R.mipmap.article_xing_icon);
        }

        data.setMaxlines(chartEyeConten.getLineCount()); //??????????????????????????????????????????????????????
        Log.e("??????", "===getLineCount===" + chartEyeConten.getLineCount());
        chartEyeConten.setMaxLines(3);
        chartEyeChenk.setText("????????????");

        if (chartEyeConten.getLineCount() > 4) {
            chartEyeChenk.setVisibility(View.VISIBLE);
        } else {
            chartEyeChenk.setVisibility(View.GONE);
        }

        //???????????????????????????
        chartEyeChenk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.setCheck(isChecked); //??????????????????????????????????????????????????????????????????checkBox??????????????????
                if (isChecked) {
                    chartEyeChenk.setText("??????");
                    chartEyeConten.setMaxLines(data.getMaxlines());
                    chartEyeConten.postInvalidate();
                } else {
                    chartEyeConten.setMaxLines(3);
                    chartEyeConten.postInvalidate(); //??????????????????????????????????????????????????????notifyDataSetChanged()?????????TextView???????????????
                    chartEyeChenk.setText("????????????");
                }
            }
        });

        List<String> bannerImage = data.getImage();//??????
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
            Log.e("????????????", "===size===" + data.getList().size());
            articleAdapter.replaceAll(data.getList());

        } else {
            chartEyeRecy2.setVisibility(View.GONE);
        }

    }

    /**
     * ??????????????????
     *
     * @param code
     * @param msg
     */
    @Override
    public void getEyeChartDataFail(int code, String msg) {

    }

    /**
     * ????????????
     *
     * @param code
     * @param data
     */
    @Override
    public void getGiveDataSuccess(int code, Object data) {

        if (giveType == 1) {
            giveType = 0;
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
            imag1.setBackgroundResource(R.mipmap.article_zan_icon_1);
            getLike_num = getLike_num + 1;
            eyeZanNumber.setText(getLike_num + "");//????????????

        } else if (giveType == 0) {
            giveType = 1;
            Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
            imag1.setBackgroundResource(R.mipmap.article_zan_icon);
            getLike_num = getLike_num - 1;
            eyeZanNumber.setText(getLike_num + "");//????????????
        }


    }

    /**
     * ????????????
     *
     * @param code
     * @param msg
     */
    @Override
    public void getGiveDataFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * ????????????
     *
     * @param code
     * @param data
     */
    @Override
    public void getCollectDataSuccess(int code, Object data) {
        if (collectType == 1) {
            collectType = 0;
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
            imag2.setBackgroundResource(R.mipmap.article_xing_icon_1);
            getCollect_num = getCollect_num + 1;
            eyeShouNumber.setText(getCollect_num + "");//????????????
        } else if (collectType == 0) {
            collectType = 1;
            Toast.makeText(this, "??????????????????", Toast.LENGTH_SHORT).show();
            imag2.setBackgroundResource(R.mipmap.article_xing_icon);
            getCollect_num = getCollect_num - 1;
            eyeShouNumber.setText(getCollect_num + "");//????????????
        }
    }

    /**
     * ????????????
     *
     * @param code
     * @param msg
     */
    @Override
    public void getCollectDataFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * ??????????????????????????????????????????????????????????????????
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //??????while?????????alpha??????<= ?????????????????????
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
