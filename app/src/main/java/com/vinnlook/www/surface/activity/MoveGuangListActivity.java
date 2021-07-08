package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dm.lib.utils.StatusBarUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.MoveGuangAdapter;
import com.vinnlook.www.surface.bean.MoveGuangListBean;
import com.vinnlook.www.surface.mvp.presenter.MoveGuangListPresenter;
import com.vinnlook.www.surface.mvp.view.MoveGuangListView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.StaggeredDividerItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:商品详情页面--进入的文章列表
 * @Time:2021/7/8$
 * @Author:pk$
 */
public class MoveGuangListActivity extends BaseActivity<MoveGuangListPresenter> implements MoveGuangListView {

    static Context context;
    static String shopId;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.move_guang_img)
    RoundedImageView moveGuangImg;
    @BindView(R.id.move_guang_num)
    TextView moveGuangNum;
    @BindView(R.id.move_guang_recycler)
    RecyclerView moveGuangRecycler;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    MoveGuangAdapter guang2Adapter;

    int page = 1;
    int lastItem = -1;
    int judge = 0;

    public static void startSelf(Context contexts, String shopIds) {
        Intent intent = new Intent(contexts, MoveGuangListActivity.class);
        contexts.startActivity(intent);
        context = contexts;
        shopId = shopIds;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move_guang;
    }

    @Override
    protected MoveGuangListPresenter initPresenter() {
        return new MoveGuangListPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        //文章
        guang2Adapter = new MoveGuangAdapter(getActivity());
//        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        //垂直方向的2列
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //防止Item切换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        moveGuangRecycler.setLayoutManager(layoutManager);
        moveGuangRecycler.setNestedScrollingEnabled(false);
        moveGuangRecycler.setHasFixedSize(true);
        moveGuangRecycler.setAdapter(guang2Adapter);
//
        final int spanCount = 2;
        moveGuangRecycler.addItemDecoration(new StaggeredDividerItemDecoration(getActivity(), 0, spanCount));

        //解决底部滚动到顶部时，顶部item上方偶尔会出现一大片间隔的问题
        moveGuangRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int[] first = new int[spanCount];
                layoutManager.findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    layoutManager.invalidateSpanAssignments();
                }
            }
        });


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
//                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod);//下载列表数据
                presenter.getMoveGuangList(page, 20, shopId);
                judge = 0;
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        judge = 1;
                        presenter.getMoveGuangList(page, 20, shopId);
                    }
                }, 2000);
            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getMoveGuangList(1, 20, shopId);//下载地址列表数据

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
    public void getMoveGuangListSuccess(int code, MoveGuangListBean data) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
        if (judge == 0) {
            guang2Adapter.replaceAll(data.getList());
        } else {
            guang2Adapter.addData(guang2Adapter.getItemCount(), data.getList());
        }

        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        moveGuangImg.setScaleType(ImageView.ScaleType.MATRIX);
        moveGuangImg.setImageMatrix(matrix);
        ImageLoader.image(this, moveGuangImg, data.getImage());

        moveGuangNum.setText(data.getCount() + "条相关优质推荐");


    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void getMoveGuangListFail(int code, String msg) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();
    }
}
