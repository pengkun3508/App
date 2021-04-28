package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.SearchListAdapter;
import com.vinnlook.www.surface.bean.SearchListListBean;
import com.vinnlook.www.surface.mvp.presenter.SearchListPresenter;
import com.vinnlook.www.surface.mvp.view.SearchListView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:搜索页面，进入搜索的商品列表
 * @Time:2020/5/25$
 * @Author:pk$
 */
public class SearchListActivity extends BaseActivity<SearchListPresenter> implements SearchListView {

    static String keyword;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    SearchListAdapter adapter;
    List<SearchListListBean.ListBean> listBean;

    int mark = 0;
    int page = 1;
    int lastItem = -1;
    int judge = 0;
    @BindView(R.id.classify_zonghe)
    LinearLayout classifyZonghe;
    @BindView(R.id.classify_xiaoliang)
    LinearLayout classifyXiaoliang;
    @BindView(R.id.classify_xinpin)
    LinearLayout classifyXinpin;
    @BindView(R.id.classify_jiage)
    LinearLayout classifyJiage;
    @BindView(R.id.classify_shaixuan)
    LinearLayout classifyShaixuan;
    @BindView(R.id.zonghe_text)
    TextView zongheText;
    @BindView(R.id.xiaoliang_text)
    TextView xiaoliangText;
    @BindView(R.id.xinpin_text)
    TextView xinpinText;
    @BindView(R.id.jiage_text)
    TextView jiageText;
    @BindView(R.id.jiage_img1)
    ImageView jiageImg1;
    @BindView(R.id.jiage_img2)
    ImageView jiageImg2;

    String sort_key = "type_sort";  //sort_key--综合：type_sort;  价格：product_price; 新品：is_new；销量：virtual_sales
    String sort_value = "desc";//当sort_key不为空时，sort_value不为空；asc:正序,desc：倒序

    public static void startSelf(Context context, String keywords) {
        Intent intent = new Intent(context, SearchListActivity.class);
        context.startActivity(intent);
        keyword = keywords;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_1;
    }

    @Override
    protected SearchListPresenter initPresenter() {
        return new SearchListPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(SearchListActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);

        adapter = new SearchListAdapter(this);
        final GridLayoutManager manager5 = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(manager5);
//        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
//        recyclerView.addItemDecoration(new SpaceItemDecoration(0, 0));
        recyclerView.setAdapter(adapter);


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
//                presenter.getSearchListData(page, 20, keyword);//下载商品详情数据
                presenter.getSearchListData(page, 20, keyword, sort_key, sort_value);//下载商品详情数据
                judge = 0;
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (adapter.getData().size() - 12 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getSearchListData(page, 20, keyword, sort_key, sort_value);//下载商品详情数据
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 20) {
                        lastItem = adapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getSearchListData(page, 20, keyword, sort_key, sort_value);//下载商品详情数据
                    }
                }
            }
        });

        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(getActivity(), adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());

            }
        });

    }

    @Override
    protected void loadData() {
//        presenter.getSearchListData(page, 20, keyword);//下载商品详情数据
        presenter.getSearchListData(page, 20, keyword, sort_key, sort_value);//下载商品详情数据
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //成功
    @Override
    public void getSearchListDataSuccess(int code, SearchListListBean data) {
        listBean = data.getList();
        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }


    }

    //失败
    @Override
    public void getSearchListDataFail(int code, String msg) {
        Log.e("SearchListActivity", "==code==" + code);
//        if (code == 3000) {
//            SearchListListBean data = new SearchListListBean();
//            adapter.setData(data.getList());
//            presenter.dismissLoading();
//        }
    }

    @OnClick({R.id.classify_zonghe, R.id.classify_xiaoliang, R.id.classify_xinpin, R.id.classify_jiage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.classify_zonghe://综合
                zongheText.setTextColor(getResources().getColor(R.color.them));
                xiaoliangText.setTextColor(getResources().getColor(R.color.classify_text1));
                xinpinText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageImg1.setImageResource(R.mipmap.classify_icon_2);
                jiageImg2.setImageResource(R.mipmap.classify_icon_3);
                sort_key = "type_sort";
                sort_value = "desc";
                judge = 0;
                page = 1;
                presenter.getSearchListData(page, 20, keyword, sort_key, sort_value);//下载商品详情数据
                break;
            case R.id.classify_xiaoliang://销量
                zongheText.setTextColor(getResources().getColor(R.color.classify_text1));
                xiaoliangText.setTextColor(getResources().getColor(R.color.them));
                xinpinText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageImg1.setImageResource(R.mipmap.classify_icon_2);
                jiageImg2.setImageResource(R.mipmap.classify_icon_3);
                sort_key = "virtual_sales";
                sort_value = "desc";
                judge = 0;
                page = 1;
                presenter.getSearchListData(page, 20, keyword, sort_key, sort_value);//下载商品详情数据
                break;
            case R.id.classify_xinpin://新品
                zongheText.setTextColor(getResources().getColor(R.color.classify_text1));
                xiaoliangText.setTextColor(getResources().getColor(R.color.classify_text1));
                xinpinText.setTextColor(getResources().getColor(R.color.them));
                jiageText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageImg1.setImageResource(R.mipmap.classify_icon_2);
                jiageImg2.setImageResource(R.mipmap.classify_icon_3);
                sort_key = "is_new";
                sort_value = "desc";
                judge = 0;
                page = 1;
                presenter.getSearchListData(page, 20, keyword, sort_key, sort_value);//下载商品详情数据
                break;
            case R.id.classify_jiage://价格
                zongheText.setTextColor(getResources().getColor(R.color.classify_text1));
                xiaoliangText.setTextColor(getResources().getColor(R.color.classify_text1));
                xinpinText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageText.setTextColor(getResources().getColor(R.color.them));
                sort_key = "product_price";
                if (sort_value.equals("asc")) {
                    sort_value = "desc";
                    jiageImg1.setImageResource(R.mipmap.classify_icon_2);
                    jiageImg2.setImageResource(R.mipmap.classify_icon_3_1);

                } else {
                    sort_value = "asc";
                    jiageImg1.setImageResource(R.mipmap.classify_icon_2_1);
                    jiageImg2.setImageResource(R.mipmap.classify_icon_3);

                }
                judge = 0;
                page = 1;
                presenter.getSearchListData(page, 20, keyword, sort_key, sort_value);//下载商品详情数据
                break;
        }
    }
}
