package com.vinnlook.www.surface.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.adapter.MyImageAdapter;
import com.vinnlook.www.widgat.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:选规格的左上角产品图片放大
 * @Time:2020/8/3$
 * @Author:pk$
 */
public class PhotoViewActivity_1 extends AppCompatActivity {

    public static final String TAG = PhotoViewActivity.class.getSimpleName();
    private static String url;
    private PhotoViewPager mViewPager;
    private int currentPosition;
    private MyImageAdapter adapter;
    private TextView mTvImageCount;
    private TextView mTvSaveImage;
    private List<String> Urls = new ArrayList<>();


    public static void startSelf(Activity context, String urls) {
        Intent intent = new Intent(context, PhotoViewActivity_1.class);
        context.startActivity(intent);
        url = urls;
        Log.e("PhotoViewActivity_1", "==urls==" + urls);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        initView();
        initData();
    }

    private void initView() {
        StatusBarUtils.setStatusBarMode(this, true);
        mViewPager = (PhotoViewPager) findViewById(R.id.view_pager_photo);
        mTvImageCount = (TextView) findViewById(R.id.tv_image_count);
        mTvSaveImage = (TextView) findViewById(R.id.tv_save_image_photo);

    }

    private void initData() {
        Intent intent = getIntent();
        currentPosition = 0;
        intent.getStringExtra("url");
        Urls.add(url);
//        Urls = imgLists.getAttach().getImage().getOri();
        Log.e("PhotoViewActivity_1", "==Urls==" + Urls);
        adapter = new MyImageAdapter(Urls, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition, false);
        mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
            }
        });
    }


}
