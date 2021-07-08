package com.vinnlook.www.surface.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundLinearLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.MainShoppingEvent;
import com.vinnlook.www.surface.adapter.ArticleDetailsFrequencyAdapter;
import com.vinnlook.www.surface.adapter.ArticleDetailsGoodsAdapter;
import com.vinnlook.www.surface.adapter.ArticleDetailspopupAdapter;
import com.vinnlook.www.surface.bean.ArticleDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.ArticleDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.ArticleDetailsView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:主题-文章详情-1精选眼图
 * @Time:2021/7/1$
 * @Author:pk$
 */
public class ArticleDetailsActivity extends BaseActivity<ArticleDetailsPresenter> implements ArticleDetailsView {

    static String iD;
    public PopupWindow popupwindow;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.article_img)
    RoundedImageView articleImg;
    @BindView(R.id.article_name)
    TextView articleName;
    @BindView(R.id.article_title)
    TextView articleTitle;
    @BindView(R.id.article_web)
    WebView articleWeb;
    @BindView(R.id.article_goods_recy)
    RecyclerView articleGoodsRecy;
    @BindView(R.id.article_other_recy)
    RecyclerView articleOtherRecy;
    @BindView(R.id.article_zan_number)
    TextView articleZanNumber;
    @BindView(R.id.article_zan_btn)
    LinearLayout articleZanBtn;
    @BindView(R.id.article_shou_number)
    TextView articleShouNumber;
    @BindView(R.id.article_shou_btn)
    LinearLayout articleShouBtn;
    @BindView(R.id.article_car_btn)
    LinearLayout articleCarBtn;
    @BindView(R.id.article_shop_btn)
    RoundLinearLayout articleShopBtn;
    @BindView(R.id.article_shop_number)
    TextView articleShopNumber;
    @BindView(R.id.article_goods_text)
    TextView articleGoodsText;
    @BindView(R.id.article_bottom_layout)
    LinearLayout articleBottomLayout;
    @BindView(R.id.article_scroll)
    ScrollView articleScroll;
    @BindView(R.id.article_other_text)
    TextView articleOtherText;
    @BindView(R.id.imag1)
    ImageView imag1;
    @BindView(R.id.imag2)
    ImageView imag2;
    ArticleDetailsBean articleDetailsBean;
    ArticleDetailsGoodsAdapter goodsAdapter;
    ArticleDetailsFrequencyAdapter frequencyAdapter;
    ArticleDetailspopupAdapter popupAdapter;
    boolean flag = false;//关闭状态
    float alpha = 1f;
    int giveType;//点赞
    int collectType;//收藏
    int getLike_num;
    int getCollect_num;
    String articleId;
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
        Intent intent = new Intent(context, ArticleDetailsActivity.class);
        context.startActivity(intent);
        iD = iDs;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme_article_details_1;
    }

    @Override
    protected ArticleDetailsPresenter initPresenter() {
        return new ArticleDetailsPresenter();
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(this, true);
        CacheActivity.addActivity(this);
        //商品
        goodsAdapter = new ArticleDetailsGoodsAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(this, 1);
        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
        articleGoodsRecy.setLayoutManager(manager1);
        articleGoodsRecy.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
        articleGoodsRecy.addItemDecoration(new SpaceItemDecoration(10, 10));
        articleGoodsRecy.setAdapter(goodsAdapter);
        goodsAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MoveAbooutActivity_3.startSelf(ArticleDetailsActivity.this, goodsAdapter.getData().get(position).getGoods_id(), goodsAdapter.getData().get(position).getSearch_attr(), articleId);
            }
        });

        //刊期
        frequencyAdapter = new ArticleDetailsFrequencyAdapter(this);
        final GridLayoutManager manager2 = new GridLayoutManager(this, 1);
        manager2.setOrientation(GridLayoutManager.HORIZONTAL);
        articleOtherRecy.setLayoutManager(manager2);
        articleOtherRecy.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
        articleOtherRecy.addItemDecoration(new SpaceItemDecoration(10, 10));
        articleOtherRecy.setAdapter(frequencyAdapter);

        articleScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int newx, int newy, int oldx, int oldy) {
                if (newy > 1) {
                    articleBottomLayout.setVisibility(View.VISIBLE);
                } else {
                    articleBottomLayout.setVisibility(View.GONE);
                }


            }
        });

        frequencyAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ArticleDetailsActivity.startSelf(ArticleDetailsActivity.this, frequencyAdapter.getData().get(position).getId());
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getArticleData(iD);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.article_zan_btn, R.id.article_shou_btn, R.id.article_car_btn, R.id.article_shop_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.article_zan_btn://点赞
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    presenter.getGiveData(articleId, giveType);
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.article_shou_btn://收藏
                if (!UserUtils.getInstance().getUserId().equals("")) {
                    presenter.getCollectData(articleId, collectType);
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.article_car_btn://购物车
                CacheActivity.finishActivity();
                new MainShoppingEvent("10").post();
                break;
            case R.id.article_shop_btn://商品
//                if (articleDetailsBean.getGoods_list().size() > 0) {
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
//                } else {
//                    Toast.makeText(this, "暂无商品", Toast.LENGTH_SHORT).show();
//                }

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
        popupAdapter = new ArticleDetailspopupAdapter(this);
        final GridLayoutManager manager1 = new GridLayoutManager(this, 1);
        popup_recy.setLayoutManager(manager1);
        popup_recy.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(this, 0)));
        popup_recy.addItemDecoration(new SpaceItemDecoration(10, 10));
        popup_recy.setAdapter(popupAdapter);
        popupAdapter.setData(articleDetailsBean.getGoods_list());

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
                MoveAbooutActivity_3.startSelf(ArticleDetailsActivity.this, popupAdapter.getData().get(position).getGoods_id(), popupAdapter.getData().get(position).getSearch_attr(), articleId);
            }
        });
    }

    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getArticleSuccess(int code, ArticleDetailsBean data) {
        articleDetailsBean = data;
        actionBar.setTitle(data.getName());
        articleName.setText(data.getName());
        articleTitle.setText(data.getSubheading());
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        articleImg.setScaleType(ImageView.ScaleType.MATRIX);
        articleImg.setImageMatrix(matrix);
        ImageLoader.image(this, articleImg, data.getImage());
        articleWeb.loadUrl(data.getContent_url());

        getLike_num = Integer.parseInt(data.getLike_num());
        getCollect_num = Integer.parseInt(data.getCollect_num());

        articleZanNumber.setText(data.getLike_num());//点赞数量
        articleShouNumber.setText(data.getCollect_num());//收藏数量
        articleId = data.getId();


        giveType = data.getIs_like();//点赞状态
        collectType = data.getIs_collect();//收藏状态
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

        Log.e("文章详情", "===size===" + data.getGoods_list().size());

        articleShopNumber.setText(data.getGoods_list().size() + "");//商品数量
        //商品
        if (data.getGoods_list().size() > 0 && data.getGoods_list() != null) {
            articleShopNumber.setVisibility(View.VISIBLE);
            articleGoodsRecy.setVisibility(View.VISIBLE);
            articleGoodsText.setVisibility(View.VISIBLE);
            goodsAdapter.setData(data.getGoods_list());
        } else {
            articleShopNumber.setVisibility(View.GONE);
            articleGoodsRecy.setVisibility(View.GONE);
            articleGoodsText.setVisibility(View.GONE);
        }

        //刊期
        if (data.getList().size() > 0) {
            articleOtherText.setVisibility(View.VISIBLE);
            articleOtherRecy.setVisibility(View.VISIBLE);
            frequencyAdapter.setData(data.getList());
        } else {
            articleOtherText.setVisibility(View.GONE);
            articleOtherRecy.setVisibility(View.GONE);
        }


    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getArticleFail(int code, String msg) {

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
            articleZanNumber.setText(getLike_num + "");//点赞数量

        } else if (giveType == 0) {
            giveType = 1;
            Toast.makeText(this, "取消点赞成功", Toast.LENGTH_SHORT).show();
            imag1.setBackgroundResource(R.mipmap.article_zan_icon);
            getLike_num = getLike_num - 1;
            articleZanNumber.setText(getLike_num + "");//点赞数量
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
            articleShouNumber.setText(getCollect_num + "");//收藏数量
        } else if (collectType == 0) {
            collectType = 1;
            Toast.makeText(this, "取消收藏成功", Toast.LENGTH_SHORT).show();
            imag2.setBackgroundResource(R.mipmap.article_xing_icon);
            getCollect_num = getCollect_num - 1;
            articleShouNumber.setText(getCollect_num + "");//收藏数量
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

    /* 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    protected void onDestroy() {
        super.onDestroy();
        articleWeb.removeAllViews();
        articleWeb.destroy();
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
