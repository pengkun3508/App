package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.SearchAdapter;
import com.vinnlook.www.surface.adapter.SearchHotAdapter;
import com.vinnlook.www.surface.adapter.SearchList_Adapter;
import com.vinnlook.www.surface.bean.SearchListBean;
import com.vinnlook.www.surface.mvp.presenter.SearchPresenter;
import com.vinnlook.www.surface.mvp.view.SearchView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.SearchHistory;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:搜索页面
 * @Time:2020/4/14 9:45
 * @Author:pk
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.iv_deldet)
    ImageView ivDeldet;
    @BindView(R.id.clear_search_text)
    ImageView clearSearchText;
    @BindView(R.id.search_list)
    RecyclerView searchList;
    @BindView(R.id.search_list_layout)
    LinearLayout searchListLayout;

    SearchAdapter searchAdapter1;
    SearchHotAdapter searchAdapter2;
    List<SearchListBean.UserListBean> adapterList_1;
    List<SearchListBean.HotListBean> adapterList_2;
    SearchList_Adapter adapter5;

    String keywords;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(SearchActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        searchAdapter1 = new SearchAdapter();//历史搜索
        FlexboxLayoutManager flexboxLayoutManager1 = new FlexboxLayoutManager(getContext());
        recyclerView.setLayoutManager(flexboxLayoutManager1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(searchAdapter1);
        searchAdapter1.setData(SearchHistory.getSearchHistory());

        searchAdapter1.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                SearchHistory.saveSearchHistory(searchAdapter1.getData().get(position));//保存数据
                SearchListActivity.startSelf(getActivity(), searchAdapter1.getData().get(position));
            }
        });


        searchAdapter2 = new SearchHotAdapter();//热门搜索
        FlexboxLayoutManager flexboxLayoutManager2 = new FlexboxLayoutManager(getContext());
        recyclerView1.setLayoutManager(flexboxLayoutManager2);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(searchAdapter2);
        searchAdapter2.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                SearchHistory.saveSearchHistory(adapterList_2.get(position).getKeyword());//保存数据
                SearchListActivity.startSelf(getActivity(), adapterList_2.get(position).getKeyword());
            }
        });


        //检索列表
        adapter5 = new SearchList_Adapter();
        final GridLayoutManager manager5 = new GridLayoutManager(getActivity(), 1);
        searchList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        searchList.addItemDecoration(new SpaceItemDecoration(0, 0));
        searchList.setLayoutManager(manager5);
        searchList.setNestedScrollingEnabled(false);
        searchList.setHasFixedSize(true);
        searchList.setAdapter(adapter5);

        //搜索按钮
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etSearch.getText().toString().trim())) {
                    SearchHistory.saveSearchHistory(etSearch.getText().toString().trim());//保存数据
                    SearchListActivity.startSelf(getActivity(), etSearch.getText().toString().trim());
                    searchListLayout.setVisibility(View.GONE);
                    adapter5.clearData();
                } else {
                    Toast.makeText(SearchActivity.this, "请先输入先搜索的内容", Toast.LENGTH_SHORT).show();
                }
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action
                    if (!TextUtils.isEmpty(etSearch.getText().toString().trim())) {
                        SearchHistory.saveSearchHistory(etSearch.getText().toString().trim());//保存数据
                        SearchListActivity.startSelf(getActivity(), etSearch.getText().toString().trim());
                        searchListLayout.setVisibility(View.GONE);
                        adapter5.clearData();
                    } else {
                        Toast.makeText(SearchActivity.this, "请先输入先搜索的内容", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });


        //清除搜索框内容
        clearSearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
                presenter.getSearchList("");//检索
            }
        });


        //清除搜索历史
        ivDeldet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAdapter1.clearData();
                searchAdapter1.notifyDataSetChanged();
                SearchHistory.deleteSearchHistory();
            }
        });


        //搜索输入框
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence keyword, int start, int before, int count) {
                //text  输入框中改变后的字符串信息
                //start 输入框中改变后的字符串的起始位置
                //before 输入框中改变前的字符串的位置 默认为0
                //count 输入框中改变后的一共输入字符串的数量
                keywords = keyword.toString();
                Log.e("搜索输入框", "搜索框===" + keywords);
                //联想分类页面
                if (keyword.length() > 0) {
                    clearSearchText.setVisibility(View.VISIBLE);
                } else {
                    clearSearchText.setVisibility(View.GONE);
                }
                presenter.getSearchList(keywords);//检索

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //text  输入框中改变前的字符串信息
                //start 输入框中改变前的字符串的起始位置
                //count 输入框中改变前后的字符串改变数量一般为0
                //after 输入框中改变后的字符串与起始位置的偏移量
            }

            @Override
            public void afterTextChanged(Editable s) {
                //edit  输入结束呈现在输入框中的信息
            }
        });


    }

    @Override
    protected void loadData() {
        presenter.getSearchData();//下载首页数据

    }

    @Override
    protected void onResume() {
        super.onResume();
        searchAdapter1.setData(SearchHistory.getSearchHistory());
    }

    /**
     * 列表数据下载成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getSearchDataSuccess(int code, SearchListBean data) {


        adapterList_2 = data.getHot_list();
        searchAdapter2.setData(data.getHot_list());

//        adapterList_1 = data.getUser_list();
//        searchAdapter1.setData(data.getUser_list());

    }

    /**
     * 列表数据下载失败
     *
     * @param code
     * @param
     */
    @Override
    public void getSearchDataFail(int code, String msg) {

    }

    /**
     * 检索下载成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getSearchListSuccess(int code, List<String> data) {
        searchListLayout.setVisibility(View.VISIBLE);
        adapter5.setData(data);
        adapter5.setKeyWords(keywords);
        adapter5.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                SearchHistory.saveSearchHistory(adapter5.getData().get(position));//保存数据
                SearchListActivity.startSelf(getActivity(), adapter5.getData().get(position));
                etSearch.setText(adapter5.getData().get(position));
                searchListLayout.setVisibility(View.GONE);
                adapter5.clearData();

            }
        });

    }

    /**
     * 检索下载失败
     *
     * @param code
     * @param
     */
    @Override
    public void getSearchListFail(int code, String msg) {
        if (code == 4000) {
            adapter5.clearData();
            searchListLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
