package com.vinnlook.www.surface.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.listener.SimpleCallback;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.ThemeDetailsAdapter_1;
import com.vinnlook.www.surface.bean.ThemeDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.ThemeDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.ThemeDetailsView;
import com.vinnlook.www.utils.BlurredView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:主题详情
 * @Time:2021/6/30$
 * @Author:pk$
 */
public class ThemeDetailsActivity extends BaseActivity<ThemeDetailsPresenter> implements ThemeDetailsView {

    static String iD;
    @BindView(R.id.msg_title_back)
    ImageView msgTitleBack;
    @BindView(R.id.theme_details_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.yahooweather_blurredview)
    BlurredView mBlurredView;
    @BindView(R.id.theme_toolbar)
    Toolbar themeToolbar;
//    @BindView(R.id.appbar_layout)
//    AppBarLayout appbarLayout;
    int titleBarHeight;
    String getColor;
    Bitmap bitmap = null;
    private float totaldy;
    private int mScrollerY;
    private int mAlpha;

    public static void startSelf(Context context, String iDs) {
        Intent intent = new Intent(context, ThemeDetailsActivity.class);
        context.startActivity(intent);
        iD = iDs;
    }

    /**
     * 根据百分比改变颜色透明度
     */
    public static int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme_datails;
    }

    @Override
    protected ThemeDetailsPresenter initPresenter() {
        return new ThemeDetailsPresenter();
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(this, false);
        CacheActivity.addActivity(this);

        msgTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollerY += dy;
                if (Math.abs(mScrollerY) > 1000) {
                    mBlurredView.setBlurredTop(100);
                    mAlpha = 100;
                } else {
                    mBlurredView.setBlurredTop(mScrollerY / 10);
                    mAlpha = Math.abs(mScrollerY) / 10;
                }
                mBlurredView.setBlurredLevel(mAlpha);

                int color = changeAlpha(Color.parseColor(getColor), -mAlpha);
                themeToolbar.setBackgroundColor(color);

            }
        });
    }

    @Override
    protected void loadData() {
        presenter.getThemeDetails(iD);

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
    public void getThemeDetailsSuccess(int code, ThemeDetailsBean data) {
        getColor = data.getColor();
        ImageLoader.gets(ThemeDetailsActivity.this, data.getHeight_image(), new SimpleCallback<Bitmap>() {
            @Override
            public void onResult(Bitmap data) {
                Bitmap bitmap = data;
                Log.e("bitmap", "===bitmap===" + bitmap);
                mBlurredView.setBlurredImg(bitmap);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ThemeDetailsAdapter_1(this, data.getColor(), data.getContent(), data.getName(), data.getList()));
    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getThemeDetailsFail(int code, String msg) {
    }

    public Bitmap returnBitMap(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;
                try {

                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }


}



