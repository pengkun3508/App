package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.adapter.HomePublicAdapter;
import com.vinnlook.www.surface.bean.HomeGoodsListBean;
import com.vinnlook.www.surface.bean.HomePublicListBean;
import com.vinnlook.www.surface.bean.TypeGoodsBean;
import com.vinnlook.www.surface.mvp.presenter.RecommendPresenter;
import com.vinnlook.www.surface.mvp.view.RecommendView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.SmartRefreshHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:公用商品列表
 * @Time:2020/10/15$
 * @Author:pk$
 */
public class HomePublicClassActivity extends BaseActivity<RecommendPresenter> implements RecommendView {


    //    @BindView(R.id.action_bar)
//    ActionBarSimple actionBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    List<HomePublicListBean.ListBean> listBean;
    @BindView(R.id.zonghe_text)
    TextView zongheText;//综合字体
    @BindView(R.id.classify_zonghe)
    LinearLayout classifyZonghe;//综合布局
    @BindView(R.id.xiaoliang_text)
    TextView xiaoliangText;//销量字体
    @BindView(R.id.classify_xiaoliang)
    LinearLayout classifyXiaoliang;//销量布局
    @BindView(R.id.xinpin_text)
    TextView xinpinText;//新品字体
    @BindView(R.id.classify_xinpin)
    LinearLayout classifyXinpin;//新品布局
    @BindView(R.id.jiage_text)
    TextView jiageText;//价格字体
    @BindView(R.id.jiage_img1)
    ImageView jiageImg1;//价格符号1
    @BindView(R.id.jiage_img2)
    ImageView jiageImg2;//价格符号2
    @BindView(R.id.classify_jiage)
    LinearLayout classifyJiage;//价格布局
    @BindView(R.id.shaixuan_text)
    TextView shaixuanText;//筛选
    @BindView(R.id.classify_shaixuan)
    LinearLayout classifyShaixuan;//筛选布局
    @BindView(R.id.back_btn)
    ImageView backBtn;//返回
    @BindView(R.id.title_name)
    TextView titleName;//标题
    @BindView(R.id.classify_serachview_edt)
    EditText classifySerachviewEdt;//搜索输入框
    @BindView(R.id.searchview_icon)
    ImageView searchviewIcon;//搜索按钮
    @BindView(R.id.search_linear_layout)
    LinearLayout searchLinearLayout;//搜索布局

    private SmartRefreshHelper<HomePublicListBean.ListBean> mSmartRefreshHelper;

    HomePublicAdapter adapter;
    private static String title;
    private static String suppliersId;
    private static String isColor;
    private static String tossPeriod;
    private static String attrId;
    private static String getId;

    int mark = 0;
    int page = 1;
    int lastItem = -1;
    int judge = 0;

    int searcrJudge = 0;

    String sort_key = "type_sort";  //sort_key--综合：type_sort;  价格：product_price; 新品：is_new；销量：virtual_sales
    String sort_value = "desc";//当sort_key不为空时，sort_value不为空；asc:正序,desc：倒序

    GridLayoutManager manager5;

    public static void startSelf(Context context, String titles, String suppliersIds, String isColors, String tossPeriods, String attrIds, String getIds) {
        Intent intent = new Intent(context, HomePublicClassActivity.class);
        context.startActivity(intent);
        title = titles;
        suppliersId = suppliersIds;
        isColor = isColors;
        tossPeriod = tossPeriods;
        attrId = attrIds;
        getId = getIds;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_brand;
    }

    @Override
    protected RecommendPresenter initPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected void initView() {
        CacheActivity.addActivity(HomePublicClassActivity.this);
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        classifySerachviewEdt.setFocusable(false);
        classifySerachviewEdt.setFocusableInTouchMode(false);
        if (title.equals("")) {
            titleName.setText("商品");
        } else {
            titleName.setText(title);
        }

        adapter = new HomePublicAdapter(this);
        manager5 = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(manager5);
        recyclerView.setFocusable(false);
        recyclerView.setAdapter(adapter);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
//                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod);//下载列表数据
                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, classifySerachviewEdt.getText().toString(), sort_key, sort_value, attrId, getId);//下载列表数据
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
                        presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, classifySerachviewEdt.getText().toString(), sort_key, sort_value, attrId, getId);//下载列表数据
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 20) {
                        lastItem = adapter.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, classifySerachviewEdt.getText().toString(), sort_key, sort_value, attrId, getId);//下载列表数据
                    }
                }
            }
        });

        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(HomePublicClassActivity.this, adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(HomePublicClassActivity.this, adapter.getData().get(position).getGoods_id(), adapter.getData().get(position).getSearch_attr(),"");
            }
        });


        //搜索输入框
        classifySerachviewEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //text  输入框中改变后的字符串信息
                //start 输入框中改变后的字符串的起始位置
                //before 输入框中改变前的字符串的位置 默认为0
                //count 输入框中改变后的一共输入字符串的数量
                Log.e("搜索输入框", "搜索框===" + s);
                page = 1;
                judge = 0;
                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, s.toString(), sort_key, sort_value, attrId, getId);//下载列表数据
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
        presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, classifySerachviewEdt.getText().toString(), sort_key, sort_value, attrId, getId);//下载列表数据

    }


    @OnClick({R.id.back_btn, R.id.searchview_icon, R.id.classify_serachview_edt, R.id.classify_zonghe, R.id.classify_xiaoliang, R.id.classify_xinpin, R.id.classify_jiage, R.id.classify_shaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_btn://返回
//                CacheActivity.finishActivity();
                finish();
                break;
            case R.id.searchview_icon://搜索按钮
                //获取屏幕宽度
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;

                if (searcrJudge == 0) {//打开搜索框
                    searcrJudge = 1;
                    //定义布局参数
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.width = (int) (width - 130);
                    layoutParams.setMarginStart(130);
                    layoutParams.setMarginEnd(30);
                    searchLinearLayout.setLayoutParams(layoutParams);
                    classifySerachviewEdt.setHint("请输入您要搜索的商品");
                    classifySerachviewEdt.setFocusable(true);
                    classifySerachviewEdt.setFocusableInTouchMode(true);

                } else if (searcrJudge == 1) {//关闭索索框
                    searcrJudge = 0;
                    //定义布局参数
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    layoutParams.width = (int) (width);
                    layoutParams.setMarginStart(width - 160);
                    searchLinearLayout.setLayoutParams(layoutParams);
                    classifySerachviewEdt.setHint("搜索");

                    classifySerachviewEdt.setFocusable(false);
                    classifySerachviewEdt.setFocusableInTouchMode(false);
                }

                break;
            case R.id.classify_zonghe://综合
                zongheText.setTextColor(getResources().getColor(R.color.them));
                xiaoliangText.setTextColor(getResources().getColor(R.color.classify_text1));
                xinpinText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageImg1.setImageResource(R.mipmap.classify_icon_2);
                jiageImg2.setImageResource(R.mipmap.classify_icon_3);
                sort_key = "type_sort";
                sort_value = "desc";
//                if (sort_value.equals("asc")) {
//                    sort_value = "desc";
//                } else {
//                sort_value = "asc";
//                }
                judge = 0;
                page = 1;
//                sort_key--综合：type_sort;  价格：product_price; 新品：is_new；销量：virtual_sales
//                adapter1.clearData();
                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, classifySerachviewEdt.getText().toString(), sort_key, sort_value, attrId, getId);//下载列表数据
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
//                if (sort_value.equals("asc")) {
//                    sort_value = "desc";
//                } else {
//                sort_value = "asc";
//                }
                judge = 0;
                page = 1;
//                adapter1.clearData();
                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, classifySerachviewEdt.getText().toString(), sort_key, sort_value, attrId, getId);//下载列表数据
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
//                if (sort_value.equals("asc")) {
//                    sort_value = "desc";
//                } else {
//                sort_value = "asc";
//                }
                judge = 0;
                page = 1;
//                adapter1.clearData();
                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, classifySerachviewEdt.getText().toString(), sort_key, sort_value, attrId, getId);//下载列表数据
                break;
            case R.id.classify_jiage://价格
                zongheText.setTextColor(getResources().getColor(R.color.classify_text1));
                xiaoliangText.setTextColor(getResources().getColor(R.color.classify_text1));
                xinpinText.setTextColor(getResources().getColor(R.color.classify_text1));
                jiageText.setTextColor(getResources().getColor(R.color.them));
                sort_key = "product_price";
                Log.e("价格", "==sort_value==" + sort_value);
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
//                adapter1.clearData();
                Log.e("价格", "==sort_value=222222222====" + sort_value);
                Log.e("价格", "==sort_key=====" + sort_key);
                Log.e("价格", "==sort_value=====" + sort_value);
                presenter.getDataList(page, 20, suppliersId, isColor, tossPeriod, classifySerachviewEdt.getText().toString(), sort_key, sort_value, attrId, getId);//下载列表数据
                break;

        }
    }


    /**
     * 下载时间成功
     *
     * @param code
     * @param version
     */
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {


    }

    /**
     * 下载时间失败
     *
     * @param code
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }


    @Override
    public void getTypeGoodsSuccess(int code, TypeGoodsBean data) {
//        listBean = data.getList();
//        smartRefreshLayout.finishRefresh();
//
//        if (judge == 0) {
//            adapter.setData(data.getList());
//        } else {
//            adapter.addData(data.getList());
//        }


    }


    @Override
    public void getTypeGoodsFail(int code, String msg) {
//        if (code == 3000) {
//            TypeGoodsBean data = new TypeGoodsBean();
//            adapter.setData(data.getList());
//            presenter.dismissLoading();
//        }
    }

    /**
     * 下载列表数据成功
     *
     * @param code
     */
    @Override
    public void getHomePublicSuccess(int code, HomePublicListBean data) {
        listBean = data.getList();
        smartRefreshLayout.finishRefresh();

        if (judge == 0) {
            manager5.scrollToPosition(0);
            adapter.clearData();
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }

    }

    /**
     * 下载列表数据失败
     *
     * @param code
     */
    @Override
    public void getHomePublicFail(int code, String msg) {

    }

    @Override
    public void getGoodsListSuccess(int code, HomeGoodsListBean data) {

    }

    @Override
    public void getGoodsListFail(int code, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
