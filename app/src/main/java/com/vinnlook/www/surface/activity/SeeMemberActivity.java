package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.MemeberQuanTtileAdapter;
import com.vinnlook.www.surface.adapter.MyPagerAdapters;
import com.vinnlook.www.surface.member.FragmentBaoYou;
import com.vinnlook.www.surface.member.FragmentFaHuo;
import com.vinnlook.www.surface.member.FragmentGouWu;
import com.vinnlook.www.surface.member.FragmentJiFen;
import com.vinnlook.www.surface.member.FragmentKeFu;
import com.vinnlook.www.surface.member.FragmentShengRi;
import com.vinnlook.www.surface.member.FragmentTuiKuan;
import com.vinnlook.www.surface.member.FragmentZheKou;
import com.vinnlook.www.surface.mvp.presenter.SeeMemberPresenter;
import com.vinnlook.www.surface.mvp.view.SeeMemberView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:
 * @Time:2020/10/16$
 * @Author:pk$
 */
public class SeeMemberActivity extends BaseActivity<SeeMemberPresenter> implements SeeMemberView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.tl_1)
    CommonTabLayout tl1;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.memeber_quanyi_title_recyclerv)
    RecyclerView memeberQuanyiTitleRecyclerv;

    private ArrayList<Integer> selectedIconRes = new ArrayList<>();         //tab??????????????????
    private ArrayList<Integer> unselectedIconRes = new ArrayList<>();       //tab?????????????????????
    private ArrayList<String> titleRes = new ArrayList<>();                 //tab????????????
    private ArrayList<Fragment> fsRes = new ArrayList<>();                  //fragment??????
    private List<CustomTabEntity> data = new ArrayList<>();                 //CommonTabLayout ??????????????????

    List<HashMap> writerimgTitle = new ArrayList<>();
    MemeberQuanTtileAdapter adapter;
    GridLayoutManager manager1;

    static int setectInt;


    public static void startSelf(Context context, int setectInts) {
        Intent intent = new Intent(context, SeeMemberActivity.class);
        context.startActivity(intent);
        setectInt = setectInts;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_see_member;
    }

    @Override
    protected SeeMemberPresenter initPresenter() {
        return new SeeMemberPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        initData();
        initListener();


    }


    private void initData() {
        setData();//????????????
        Log.e("setData", "==writerimgTitle====" + writerimgTitle);
        adapter = new MemeberQuanTtileAdapter(this);
        manager1 = new GridLayoutManager(getActivity(), 1);
        manager1.setOrientation(GridLayoutManager.HORIZONTAL);
        memeberQuanyiTitleRecyclerv.setLayoutManager(manager1);
        memeberQuanyiTitleRecyclerv.setAdapter(adapter);
        adapter.setData(writerimgTitle);

        //??????????????????
        selectedIconRes.add(R.mipmap.member_zhekou_2);
        selectedIconRes.add(R.mipmap.member_jifen_2);
        selectedIconRes.add(R.mipmap.member_tuikuan_2);
        selectedIconRes.add(R.mipmap.member_kefu_2);
        selectedIconRes.add(R.mipmap.member_gouwu_2);
        selectedIconRes.add(R.mipmap.member_baoyou_2);
        selectedIconRes.add(R.mipmap.member_shengri_2);
        selectedIconRes.add(R.mipmap.member_fahuo_2);

        //?????????????????????
        unselectedIconRes.add(R.mipmap.member_zhekou_1);
        unselectedIconRes.add(R.mipmap.member_jifen_1);
        unselectedIconRes.add(R.mipmap.member_tuikuan_1);
        unselectedIconRes.add(R.mipmap.member_kefu_1);
        unselectedIconRes.add(R.mipmap.member_gouwu_1);
        unselectedIconRes.add(R.mipmap.member_baoyou_1);
        unselectedIconRes.add(R.mipmap.member_shengri_1);
        unselectedIconRes.add(R.mipmap.member_fahuo_1);

        //????????????
        titleRes.add("??????95???");
        titleRes.add("????????????");
        titleRes.add("????????????");
        titleRes.add("????????????");
        titleRes.add("???????????????");
        titleRes.add("????????????");
        titleRes.add("????????????");
        titleRes.add("????????????");

        //fragment??????
        fsRes.add(new FragmentZheKou());
        fsRes.add(new FragmentJiFen());
        fsRes.add(new FragmentTuiKuan());
        fsRes.add(new FragmentKeFu());
        fsRes.add(new FragmentGouWu());
        fsRes.add(new FragmentBaoYou());
        fsRes.add(new FragmentShengRi());
        fsRes.add(new FragmentFaHuo());

        //????????????
        for (int i = 0; i < titleRes.size(); i++) {
            final int index = i;
            data.add(new CustomTabEntity() {
                @Override
                public String getTabTitle() {
                    return titleRes.get(index);
                }

                @Override
                public int getTabSelectedIcon() {
                    return selectedIconRes.get(index);
                }

                @Override
                public int getTabUnselectedIcon() {
                    return unselectedIconRes.get(index);
                }
            });
        }

        //????????????
        tl1.setTabData((ArrayList<CustomTabEntity>) data);
        viewPager.setAdapter(new MyPagerAdapters(getSupportFragmentManager(), fsRes));


        Log.e("??????", "==setectInt==" + setectInt);
        //???????????????item??????
        viewPager.setCurrentItem(setectInt);
        //?????????????????????????????????
        tl1.setCurrentTab(setectInt);
        MoveToPosition(manager1, memeberQuanyiTitleRecyclerv, setectInt);
        adapter.setPosion(setectInt);
        adapter.notifyDataSetChanged();


    }

    private void initListener() {
        //TabLayout??????
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //???????????????item??????
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                //???????????????item??????
//                viewPager.setCurrentItem(position);
            }
        });

        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                //???????????????item??????
//                viewPager.setCurrentItem(position);

                viewPager.setCurrentItem(position);
                int size = writerimgTitle.size();
                if (size > 2) {
                    if (position > 1 && position < size - 2) {
                        moveToCenter(view);
                    } else if (position >= 0 && position < 2) {
                        memeberQuanyiTitleRecyclerv.smoothScrollToPosition(0);
                    } else {
                        memeberQuanyiTitleRecyclerv.smoothScrollToPosition(size - 1);
                    }
                }


            }
        });


        //ViewPager??????
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
//                tl1.setCurrentTab(i);
            }

            @Override
            public void onPageSelected(int i) {
                View itemView = adapter.getItemView();

//                //?????????????????????????????????
//                tl1.setCurrentTab(i);
//                memeberQuanyiTitleRecyclerv.smoothScrollToPosition(i);
////                MoveToPosition(manager1, memeberQuanyiTitleRecyclerv, i);
//                adapter.setPosion(i);
//                adapter.notifyDataSetChanged();
//                if (i==2){
//                    memeberQuanyiTitleRecyclerv.scrollTo(screenWidth / 2, 0);
//                }

                memeberQuanyiTitleRecyclerv.smoothScrollToPosition(i);
                int size = fsRes.size() - 1;
                if (i <= size) {
                    adapter.setPosion(i);
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
//        //???????????????0???
//        viewPager.setCurrentItem(0);
    }

    //????????????????????????
    private void moveToCenter(View itemView) {
        int[] locationView = new int[2];
        itemView.getLocationOnScreen(locationView);
        int viewWidth = itemView.getWidth();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int centerX = dm.widthPixels / 2;
        int distance = locationView[0] - centerX + viewWidth / 2;
        memeberQuanyiTitleRecyclerv.smoothScrollBy(distance, 0);
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public void setData() {

        HashMap hasmap1 = new HashMap();
        hasmap1.put("img1", R.mipmap.member_zhekou_1);
        hasmap1.put("img2", R.mipmap.member_zhekou_2);
        hasmap1.put("name", "??????95???");
        HashMap hasmap2 = new HashMap();
        hasmap2.put("img1", R.mipmap.member_jifen_1);
        hasmap2.put("img2", R.mipmap.member_jifen_2);
        hasmap2.put("name", "????????????");
        HashMap hasmap3 = new HashMap();
        hasmap3.put("img1", R.mipmap.member_tuikuan_1);
        hasmap3.put("img2", R.mipmap.member_tuikuan_2);
        hasmap3.put("name", "????????????");
        HashMap hasmap4 = new HashMap();
        hasmap4.put("img1", R.mipmap.member_kefu_1);
        hasmap4.put("img2", R.mipmap.member_kefu_2);
        hasmap4.put("name", "????????????");
        HashMap hasmap5 = new HashMap();
        hasmap5.put("img1", R.mipmap.member_gouwu_1);
        hasmap5.put("img2", R.mipmap.member_gouwu_2);
        hasmap5.put("name", "???????????????");
        HashMap hasmap6 = new HashMap();
        hasmap6.put("img1", R.mipmap.member_baoyou_1);
        hasmap6.put("img2", R.mipmap.member_baoyou_2);
        hasmap6.put("name", "????????????");
        HashMap hasmap7 = new HashMap();
        hasmap7.put("img1", R.mipmap.member_shengri_1);
        hasmap7.put("img2", R.mipmap.member_shengri_2);
        hasmap7.put("name", "????????????");
        HashMap hasmap8 = new HashMap();
        hasmap8.put("img1", R.mipmap.member_fahuo_1);
        hasmap8.put("img2", R.mipmap.member_fahuo_2);
        hasmap8.put("name", "????????????");
        writerimgTitle.add(hasmap1);
        writerimgTitle.add(hasmap2);
        writerimgTitle.add(hasmap3);
        writerimgTitle.add(hasmap4);
        writerimgTitle.add(hasmap5);
        writerimgTitle.add(hasmap6);
        writerimgTitle.add(hasmap7);
        writerimgTitle.add(hasmap8);
    }


    /**
     * RecyclerView ????????????????????????
     *
     * @param manager       ??????RecyclerView?????????manager
     * @param mRecyclerView ?????????RecyclerView
     * @param n             ??????????????????
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }
}
