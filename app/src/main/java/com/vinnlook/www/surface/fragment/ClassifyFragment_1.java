package com.vinnlook.www.surface.fragment;


import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.event.ScreenIdListEvent;
import com.vinnlook.www.event.ShopCarJudgeEvent;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.activity.MoveAbooutActivity_3;
import com.vinnlook.www.surface.adapter.ClassiftType_2_Adapter;
import com.vinnlook.www.surface.adapter.ClassiftType_3_Adapter;
import com.vinnlook.www.surface.adapter.ClassiftType_Adapter;
import com.vinnlook.www.surface.adapter.ClassifyList_Adapter;
import com.vinnlook.www.surface.adapter.SearchList_Adapter;
import com.vinnlook.www.surface.bean.ClassifyBean;
import com.vinnlook.www.surface.bean.ClassifyTypeBean;
import com.vinnlook.www.surface.dialog.ScreenDialog;
import com.vinnlook.www.surface.mvp.presenter.ClassifyFragmentPresenter;
import com.vinnlook.www.surface.mvp.view.ClassifyFragmentView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.SmartRefreshHelper;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @Description:??????
 * @Time:2020/4/1 10:28
 * @Author:pk
 */
public class ClassifyFragment_1 extends BaseFragment<ClassifyFragmentPresenter> implements ClassifyFragmentView {
    @BindView(R.id.classify_serachview_edt)
    EditText classifySerachviewEdt;//??????
    @BindView(R.id.classify_qita)
    TextView classifyQita;//??????
//    @BindView(R.id.imageButton1)
//    RadioButton imageButton1;//??????
//    @BindView(R.id.imageButton2)
//    RadioButton imageButton2;//??????
//    @BindView(R.id.imageButton3)
//    RadioButton imageButton3;//??????
//    @BindView(R.id.radioGroup)
//    RadioGroup radioGroup;//

//    @BindView(R.id.all_view)
//    View allView;//
//    @BindView(R.id.ziying_view)
//    View ziyingView;//
//    @BindView(R.id.hiatao_view)
//    View hiataoView;//

    @BindView(R.id.classify_zonghe)
    LinearLayout classifyZonghe;//??????
    @BindView(R.id.classify_xiaoliang)
    LinearLayout classifyXiaoliang;//??????
    @BindView(R.id.classify_xinpin)
    LinearLayout classifyXinpin;//??????
    @BindView(R.id.classify_jiage)
    LinearLayout classifyJiage;//??????
    @BindView(R.id.classify_shaixuan)
    LinearLayout classifyShaixuan;//??????
    @BindView(R.id.classify_recycler)
    RecyclerView classifyRecycler;//List
//    @BindView(R.id.smart_refresh_layout)
//    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.zonghe_text)
    TextView zonghe_text;//??????
    @BindView(R.id.xiaoliang_text)
    TextView xiaoliang_text;//??????
    @BindView(R.id.xinpin_text)
    TextView xinpin_text;//??????
    @BindView(R.id.jiage_text)
    TextView jiage_text;//??????


    int mark = 0;
    int page = 1;
    int lastItem = -1;
    int judge = 0;

    String keywords;

    List<ClassifyTypeBean> classifyTypeBean;
    @BindView(R.id.jiage_img1)
    ImageView jiageImg1;
    @BindView(R.id.jiage_img2)
    ImageView jiageImg2;
    @BindView(R.id.classify_type_recyclerView)
    RecyclerView classifyTypeRecyclerView;//??????????????????
    @BindView(R.id.classify_type_recyclerView_2)
    RecyclerView classifyTypeRecyclerView2;//??????????????????2
    @BindView(R.id.classify_type_recyclerView_3)
    RecyclerView classifyTypeRecyclerView3;//???????????????????????????
    @BindView(R.id.classify_clear_btn)
    TextView classifyClearBtn;//??????????????????
    @BindView(R.id.search_list)
    RecyclerView searchList;//????????????
    @BindView(R.id.search_list_layout)
    LinearLayout searchListLayout;//??????????????????
    @BindView(R.id.clear_search_text)
    ImageView clearSearchText;
    @BindView(R.id.title)
    RelativeLayout title;

    private SmartRefreshHelper<ClassifyBean.ListBean> mSmartRefreshHelper;

    List<ClassifyBean.ListBean> classifyBean;

    private ClassifyList_Adapter adapter1;
    private ClassiftType_Adapter adapter2;
    private ClassiftType_2_Adapter adapter3;
    private ClassiftType_3_Adapter adapter4;
    private SearchList_Adapter adapter5;

    String sort_key = "type_sort";  //sort_key--?????????type_sort;  ?????????product_price; ?????????is_new????????????virtual_sales
    String sort_value = "desc";//???sort_key???????????????sort_value????????????asc:??????,desc?????????
    String count;//??????
    String iDstr = "";
    String idStr = "";

    ArrayList<String> iDLists = new ArrayList<>();
    ArrayList<String> iDLists1 = new ArrayList<>();
    List<ClassifyTypeBean.SonListBean> getSon_list;

    String getOneCat_id;

    List<HashMap<String, String>> listName = new ArrayList<>();
    HashMap<String, String> hampName = new HashMap<String, String>();//??????????????????3???

    String str;//????????????ID
    String str1;//????????????ID

    GridLayoutManager manager2;
    GridLayoutManager manager1;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify_1;
    }

    @Override
    protected ClassifyFragmentPresenter initPresenter() {
        return new ClassifyFragmentPresenter();
    }

    @Override
    protected void initView() {

        //??????????????????
        adapter1 = new ClassifyList_Adapter();
        manager1 = new GridLayoutManager(getActivity(), 3);
        classifyRecycler.setLayoutManager(manager1);
        classifyRecycler.setFocusable(false);
        classifyRecycler.setAdapter(adapter1);


        //??????????????????
        adapter2 = new ClassiftType_Adapter(getActivity());
        manager2 = new GridLayoutManager(getActivity(), 1);
        manager2.setOrientation(GridLayoutManager.HORIZONTAL);
        classifyTypeRecyclerView.setLayoutManager(manager2);
        classifyTypeRecyclerView.setNestedScrollingEnabled(false);
        classifyTypeRecyclerView.setHasFixedSize(true);
        classifyTypeRecyclerView.setAdapter(adapter2);

        //??????????????????2
        adapter3 = new ClassiftType_2_Adapter();
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 5);
        manager3.setOrientation(GridLayoutManager.VERTICAL);
        classifyTypeRecyclerView2.setLayoutManager(manager3);
        classifyTypeRecyclerView2.setNestedScrollingEnabled(false);
        classifyTypeRecyclerView2.setHasFixedSize(true);
        classifyTypeRecyclerView2.setAdapter(adapter3);

        //??????????????????3
        adapter4 = new ClassiftType_3_Adapter();
        final GridLayoutManager manager4 = new GridLayoutManager(getActivity(), 1);
        manager4.setOrientation(GridLayoutManager.HORIZONTAL);
        classifyTypeRecyclerView3.setLayoutManager(manager4);
        classifyTypeRecyclerView3.setNestedScrollingEnabled(false);
        classifyTypeRecyclerView3.setHasFixedSize(true);
        classifyTypeRecyclerView3.setAdapter(adapter4);


        //????????????
        adapter5 = new SearchList_Adapter();
        final GridLayoutManager manager5 = new GridLayoutManager(getActivity(), 1);
        searchList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 0)));
        searchList.addItemDecoration(new SpaceItemDecoration(0, 0));
        searchList.setLayoutManager(manager5);
        searchList.setNestedScrollingEnabled(false);
        searchList.setHasFixedSize(true);
        searchList.setAdapter(adapter5);

//        //????????????
//        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                page = 1;
//                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
//                judge = 0;
//            }
//        });


        presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????

        classifyRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();//???????????????????????????item??????
                if (adapter1.getData().size() - 12 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                    }
                } else {
                    if (adapter1.getData().size() - lastItem > 20) {
                        lastItem = adapter1.getData().size() - 12;
                        page++;
                        judge = 1;
                        presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                    }
                }
            }
        });
        //?????????????????????
        clearSearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classifySerachviewEdt.setText("");
                presenter.getSearchList("");//??????
            }
        });

        //???????????????
        classifySerachviewEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence keyword, int start, int before, int count) {
                //text  ???????????????????????????????????????
                //start ????????????????????????????????????????????????
                //before ?????????????????????????????????????????? ?????????0
                //count ??????????????????????????????????????????????????????
                keywords = keyword.toString();
                Log.e("???????????????", "?????????===" + keywords);
                if (keyword.length() > 0) {
                    clearSearchText.setVisibility(View.VISIBLE);
                } else {
                    clearSearchText.setVisibility(View.GONE);
                }
                //??????????????????
                presenter.getSearchList(keywords);//??????

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //text  ???????????????????????????????????????
                //start ????????????????????????????????????????????????
                //count ?????????????????????????????????????????????????????????0
                //after ????????????????????????????????????????????????????????????
            }

            @Override
            public void afterTextChanged(Editable s) {
                //edit  ??????????????????????????????????????????
            }
        });

        classifySerachviewEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//????????????action
                    if (!TextUtils.isEmpty(classifySerachviewEdt.getText().toString().trim())) {
                        presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                        searchListLayout.setVisibility(View.GONE);
                        adapter5.clearData();
                    } else {
                        Toast.makeText(getActivity(), "??????????????????????????????", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        adapter1.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                MoveAbooutActivity_1.startSelf(getActivity(), adapter1.getData().get(position).getGoods_id(), adapter1.getData().get(position).getSearch_attr());
                MoveAbooutActivity_3.startSelf(getActivity(), adapter1.getData().get(position).getGoods_id(), adapter1.getData().get(position).getSearch_attr(),"");

            }
        });

        //??????????????????????????????
        adapter2.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                view.setBackgroundResource(R.color.white);
                getOneCat_id = adapter2.getData().get(position).getCat_id();
                for (int i = 0; i < adapter2.getData().size(); i++) {
                    if (position == i) {
                        adapter2.getData().get(i).setColor_mark("1");
                    } else {
                        adapter2.getData().get(i).setColor_mark("0");
                    }
                }
                getSon_list = adapter2.getData().get(position).getSon_list();
                adapter3.setData(adapter2.getData().get(position).getSon_list());
                adapter3.setId(adapter2.getData().get(position).getCat_id());

                adapter2.notifyDataSetChanged();
                adapter3.notifyDataSetChanged();
                iDLists.add(adapter2.getData().get(position).getCat_id());

//                MoveToPosition(manager2, classifyTypeRecyclerView, position);
//                classifyTypeRecyclerView.smoothScrollToPosition(position);

                int size = adapter2.getData().size();
                if (size > 2) {
                    if (position > 1 && position < size - 2) {
                        moveToCenter(view, classifyTypeRecyclerView);
                    } else if (position >= 0 && position < 2) {
                        classifyTypeRecyclerView.smoothScrollToPosition(0);
                    } else {
                        classifyTypeRecyclerView.smoothScrollToPosition(size - 1);
                    }
                }
            }
        });

//        hampName.put("id","");
//        hampName.put("name","");
//        listName.add(hampName);

        adapter4.setData(listName);


        //??????????????????????????????
        adapter3.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
//                iDLists1.add(adapter3.getData().get(position).getCat_id());//??????ID
                //???????????????--??????
                if (adapter3.getData().get(position).getType().equals("1")) {
                    adapter3.getData().get(position).setType("2");
                    StringBuilder sv = new StringBuilder();
                    StringBuilder sv1 = new StringBuilder();
                    sv1.append(adapter3.getData().get(position).getParent_id());
                    sv1.append(":");
                    sv1.append(adapter3.getData().get(position).getCat_id());
                    sv.append(adapter3.getData().get(position).getCat_id());
                    Log.e("bindData", "==sv=" + sv.toString());
                    Log.e("bindData", "==sv=iDLists00000==" + iDLists);
                    iDLists1.add(sv1.toString());
                    iDLists.add(sv.toString());

                    hampName = new HashMap<>();
                    hampName.put("oneId", getOneCat_id);
                    hampName.put("id", adapter3.getData().get(position).getCat_id());
                    hampName.put("name", adapter3.getData().get(position).getCat_name());
                    listName.add(hampName);

                    Log.e("listName", "==listName=0000=" + listName);

                    Log.e("bindData", "==sv=iDLists==" + iDLists);
                    //?????????--??????
                } else if (adapter3.getData().get(position).getType().equals("2")) {
                    adapter3.getData().get(position).setType("1");
                    StringBuilder sv = new StringBuilder();
                    StringBuilder sv1 = new StringBuilder();
                    sv1.append(adapter3.getData().get(position).getParent_id());
                    sv1.append(":");
                    sv1.append(adapter3.getData().get(position).getCat_id());

                    sv.append(adapter3.getData().get(position).getCat_id());

                    if (iDLists.contains(sv.toString())) {
                        iDLists.remove(sv.toString());
                    }
                    if (iDLists1.contains(sv1.toString())) {
                        iDLists1.remove(sv1.toString());
                    }

                    for (int i = 0; i < listName.size(); i++) {
                        if (listName.get(i).get("name").contains(adapter3.getData().get(position).getCat_name())) {
                            listName.remove(i);
                        }
                    }
                }
                if (iDLists.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < iDLists.size(); i++) {
                        stringBuffer.append(iDLists.get(i).toString().trim() + ",");
                    }

                    str = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
                } else {
                    str = "";
                }

                if (iDLists1.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < iDLists1.size(); i++) {
                        stringBuffer.append(iDLists1.get(i).toString().trim() + ",");
                    }
//                        if (!iDStr.equals("")) {
//                            stringBuffer.append(iDStr);
//                        }
                    str1 = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
                } else {
                    str1 = "";
                }
                idStr = str;
                iDstr = str1;
                judge = 0;
                page = 1;
                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                adapter4.setData(listName);
                adapter4.notifyDataSetChanged();
                adapter3.notifyDataSetChanged();


            }
        });
        adapter4.setTitleItemClickListener(new ClassiftType_3_Adapter.OnTitleItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {

                String forId = adapter4.getData().get(position).get("id");
                adapter4.getData().remove(position);

                StringBuilder sv = new StringBuilder();
                StringBuilder sv1 = new StringBuilder();
                //?????????????????????????????????????????????
                for (int i = 0; i < adapter2.getData().size(); i++) {
                    for (int j = 0; j < adapter2.getData().get(i).getSon_list().size(); j++) {
                        if (forId.equals(adapter2.getData().get(i).getSon_list().get(j).getCat_id())) {
                            sv1.append(adapter2.getData().get(i).getSon_list().get(j).getParent_id());
                            sv1.append(":");
                            sv1.append(adapter2.getData().get(i).getSon_list().get(j).getCat_id());
                            sv.append(adapter2.getData().get(i).getSon_list().get(j).getCat_id());
                            adapter2.getData().get(i).getSon_list().get(j).setType("1");
                        }
                    }
                }
                //??????ID
                if (iDLists.contains(sv.toString())) {
                    iDLists.remove(sv.toString());
                }
                if (iDLists1.contains(sv1.toString())) {
                    iDLists1.remove(sv1.toString());
                }
                if (iDLists.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < iDLists.size(); i++) {
                        stringBuffer.append(iDLists.get(i).toString().trim() + ",");
                    }
                    str = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
                } else {
                    str = "";
                }
                if (iDLists1.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < iDLists1.size(); i++) {
                        stringBuffer.append(iDLists1.get(i).toString().trim() + ",");
                    }
//                        if (!iDStr.equals("")) {
//                            stringBuffer.append(iDStr);
//                        }
                    str1 = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
                    Log.e("adapter4", "str--1111===" + str1);
                } else {
                    str1 = "";
                }
                idStr = str;
                iDstr = str1;
                judge = 0;
                page = 1;
                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                adapter3.notifyDataSetChanged();
                adapter4.notifyDataSetChanged();
            }
        });

        //???????????????List????????????
        adapter4.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                String forId = adapter4.getData().get(position).get("id");
                adapter4.getData().remove(position);

                StringBuilder sv = new StringBuilder();
                StringBuilder sv1 = new StringBuilder();
                //?????????????????????????????????????????????
                for (int i = 0; i < adapter2.getData().size(); i++) {
                    for (int j = 0; j < adapter2.getData().get(i).getSon_list().size(); j++) {
                        if (forId.equals(adapter2.getData().get(i).getSon_list().get(j).getCat_id())) {
                            sv1.append(adapter2.getData().get(i).getSon_list().get(j).getParent_id());
                            sv1.append(":");
                            sv1.append(adapter2.getData().get(i).getSon_list().get(j).getCat_id());
                            sv.append(adapter2.getData().get(i).getSon_list().get(j).getCat_id());
                            adapter2.getData().get(i).getSon_list().get(j).setType("1");
                        }
                    }
                }
                //??????ID
                if (iDLists.contains(sv.toString())) {
                    iDLists.remove(sv.toString());
                }
                if (iDLists1.contains(sv1.toString())) {
                    iDLists1.remove(sv1.toString());
                }
                if (iDLists.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < iDLists.size(); i++) {
                        stringBuffer.append(iDLists.get(i).toString().trim() + ",");
                    }
                    str = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
                } else {
                    str = "";
                }
                if (iDLists1.size() > 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < iDLists1.size(); i++) {
                        stringBuffer.append(iDLists1.get(i).toString().trim() + ",");
                    }
//                        if (!iDStr.equals("")) {
//                            stringBuffer.append(iDStr);
//                        }
                    str1 = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
                    Log.e("adapter4", "str--1111===" + str1);
                } else {
                    str1 = "";
                }
                idStr = str;
                iDstr = str1;
                judge = 0;
                page = 1;
                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                adapter3.notifyDataSetChanged();
                adapter4.notifyDataSetChanged();


            }
        });

        /**
         * ????????????
         */
        classifyClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //????????????????????????

                iDLists = new ArrayList<>();
                iDLists1 = new ArrayList<>();
                str = "";
                str1 = "";
                iDstr = "";
                for (int i = 0; i < classifyTypeBean.size(); i++) {
                    classifyTypeBean.get(i).setColor_mark("0");
                    for (int j = 0; j < classifyTypeBean.get(i).getSon_list().size(); j++) {
                        classifyTypeBean.get(i).getSon_list().get(j).setType("1");
                    }
                }
                classifyTypeBean.get(0).setColor_mark("1");

                adapter2.setData(classifyTypeBean);
                adapter3.setData(classifyTypeBean.get(0).getSon_list());
                listName = new ArrayList<>();
                adapter4.setData(listName);
                adapter2.notifyDataSetChanged();
                adapter3.notifyDataSetChanged();
                adapter4.notifyDataSetChanged();
                judge = 0;
                page = 1;
                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????

            }
        });


    }

    @Override
    protected void loadData() {
//        presenter.getAppUpdate();//????????????
        presenter.getTypeList(0);


    }

    //    R.id.imageButton1, R.id.imageButton2, R.id.imageButton3,
    @OnClick({R.id.classify_zonghe, R.id.classify_xiaoliang, R.id.classify_xinpin, R.id.classify_jiage, R.id.classify_shaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.imageButton1://??????
//                mark = 0;
//                judge = 0;
//                page = 1;
//                allView.setBackgroundColor(getResources().getColor(R.color.them));
//                ziyingView.setBackgroundColor(getResources().getColor(R.color.classify_them));
//                hiataoView.setBackgroundColor(getResources().getColor(R.color.classify_them));
//                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
//                break;
//            case R.id.imageButton2://??????
//                mark = 1;
//                judge = 0;
//                page = 1;
//                allView.setBackgroundColor(getResources().getColor(R.color.classify_them));
//                ziyingView.setBackgroundColor(getResources().getColor(R.color.them));
//                hiataoView.setBackgroundColor(getResources().getColor(R.color.classify_them));
//
//                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
//                break;
//            case R.id.imageButton3://??????
//                mark = 2;
//                judge = 0;
//                page = 1;
//                allView.setBackgroundColor(getResources().getColor(R.color.classify_them));
//                ziyingView.setBackgroundColor(getResources().getColor(R.color.classify_them));
//                hiataoView.setBackgroundColor(getResources().getColor(R.color.them));
//
//                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
//                break;
//            case R.id.classify_serachview_edt://??????
////                SearchActivity.startSelf(getContext());
//                break;
            case R.id.classify_zonghe://??????
                zonghe_text.setTextColor(getResources().getColor(R.color.them));
                xiaoliang_text.setTextColor(getResources().getColor(R.color.classify_text1));
                xinpin_text.setTextColor(getResources().getColor(R.color.classify_text1));
                jiage_text.setTextColor(getResources().getColor(R.color.classify_text1));
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
//                sort_key--?????????type_sort;  ?????????product_price; ?????????is_new????????????virtual_sales
//                adapter1.clearData();
                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                break;
            case R.id.classify_xiaoliang://??????
                zonghe_text.setTextColor(getResources().getColor(R.color.classify_text1));
                xiaoliang_text.setTextColor(getResources().getColor(R.color.them));
                xinpin_text.setTextColor(getResources().getColor(R.color.classify_text1));
                jiage_text.setTextColor(getResources().getColor(R.color.classify_text1));
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
                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                break;
            case R.id.classify_xinpin://??????
                zonghe_text.setTextColor(getResources().getColor(R.color.classify_text1));
                xiaoliang_text.setTextColor(getResources().getColor(R.color.classify_text1));
                xinpin_text.setTextColor(getResources().getColor(R.color.them));
                jiage_text.setTextColor(getResources().getColor(R.color.classify_text1));
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
                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                break;
            case R.id.classify_jiage://??????
                zonghe_text.setTextColor(getResources().getColor(R.color.classify_text1));
                xiaoliang_text.setTextColor(getResources().getColor(R.color.classify_text1));
                xinpin_text.setTextColor(getResources().getColor(R.color.classify_text1));
                jiage_text.setTextColor(getResources().getColor(R.color.them));
                sort_key = "product_price";
                Log.e("??????", "==sort_value==" + sort_value);
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
                Log.e("??????", "==sort_value=222222222====" + sort_value);
                Log.e("??????", "==sort_key=====" + sort_key);
                Log.e("??????", "==sort_value=====" + sort_value);
                presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                break;


            case R.id.classify_shaixuan://??????
                Log.e("??????", "==iDstr==" + iDstr);
                page = 1;
                judge = 0;
                if (classifyTypeBean != null) {
                    ScreenDialog.with(getActivity(), classifyTypeBean, new ClassifyFragmentPresenter(), count, idStr, iDstr, new ScreenDialog.ClassifDialogOnClickAll() {
                        @Override
                        public void onDialogClickListener(String str, String str1) {
                            Log.e("??????", "==iDStr===" + str);
                            Log.e("??????", "==iDStr11111===" + str1);
                            idStr = str;//???????????????---297,3157
                            iDstr = str1;//?????????????????????;---34:297,45:3157
                            presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                        }

                    }).show();
                } else {
                    presenter.getTypeList(1);
                }

                break;
        }
    }


    /**
     * ??????????????????
     *
     * @param
     * @param
     */
    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {
    }

    /**
     * ??????????????????
     *
     * @param
     * @param
     */
    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * ????????????????????????
     *
     * @param
     * @param
     */
    @Override
    public void getClassifySuccess(int code, ClassifyBean data) {
//        smartRefreshLayout.finishRefresh();
        searchListLayout.setVisibility(View.GONE);
        adapter5.clearData();
        count = data.getCount();
        ScreenDialog.setCount(count);
        if (judge == 0) {
            manager1.scrollToPosition(0);
            adapter1.setData(data.getList());
        } else {
            adapter1.addData(data.getList());
        }
    }

    /**
     * ????????????????????????
     *
     * @param
     * @param
     */
    @Override
    public void getClassifyFail(int code, String msg) {
//        smartRefreshLayout.finishRefresh();
//        if (code == 3000) {
//            ClassifyBean data = new ClassifyBean();
//            adapter1.setData(data.getList());
//            presenter.dismissLoading();
//        }
    }


    /**
     * @Description:????????????????????????
     * @Time:2020/5/11 16:53
     * @Author:pk
     */
    @Override
    public void getClassifyTypeListSuccess(int code, List<ClassifyTypeBean> data, int type) {
        Log.e("??????", "==data==" + data);
        classifyTypeBean = data;
        classifyTypeBean.get(0).setColor_mark("1");
        if (type == 1) {
            ScreenDialog.with(getActivity(), classifyTypeBean, new ClassifyFragmentPresenter(), count, idStr, iDstr, new ScreenDialog.ClassifDialogOnClickAll() {
                @Override
                public void onDialogClickListener(String str, String str1) {
                    Log.e("??????", "==????????????????????????=getParent_id===" + str);
                    idStr = str;//???????????????---297,3157
                    iDstr = str1;//?????????????????????;---34:297,45:3157
                    page = 1;
                    judge = 0;
                    Log.e("??????", "==????????????????????????=getParent_id===" + str1);
                    presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                }

            }).show();
        }
        //????????????????????????
        adapter2.setData(classifyTypeBean);

        adapter3.setData(classifyTypeBean.get(0).getSon_list());

    }

    /**
     * @Description:????????????????????????
     * @Time:2020/5/11 16:53
     * @Author:pk
     */
    @Override
    public void getClassifyTypeListFail(int code, String msg) {

    }

    /**
     * ??????????????????
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
                page = 1;
                judge = 0;
                presenter.getClassifyData(adapter5.getData().get(position), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
                classifySerachviewEdt.setText(adapter5.getData().get(position));
                searchListLayout.setVisibility(View.GONE);
                adapter5.clearData();

            }
        });

//        if (adapter5.getData().size() > 0) {
//            for (int i = 0; i < adapter5.getData().size(); i++) {
//                Log.e("????????????","==adapter5=="+ adapter5.getData().get(i));
//                Log.e("????????????","==keywords=="+ keywords);
//                KeywordUtil.matcherSearchTitle(getActivity().getResources().getColor(R.color.black), adapter5.getData().get(i), keywords);
//            }
//        }

    }

    /**
     * ??????????????????
     *
     * @param code
     * @param
     */
    @Override
    public void getSearchListFail(int code, String msg) {
//        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        if (code == 4000) {
            adapter5.clearData();
            searchListLayout.setVisibility(View.GONE);
            page = 1;
            judge = 0;
            presenter.getClassifyData("", page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
        }

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    //????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ScreenIdListEvent event) {
        String getListId = event.getListId();
        Log.e("ClassifyFragment", "=?????????????????????=getListId===" + getListId);
    }

    //????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ShopCarJudgeEvent event) {
        int getJupdg = event.getJupdg();
        judge = 0;
        if (getJupdg == 2) {
            page = 1;
//            presenter.getAppUpdate();//????????????
            presenter.getClassifyData(classifySerachviewEdt.getText().toString(), page, 20, mark, sort_key, sort_value, iDstr);//??????????????????
        }
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

    //????????????????????????
    private void moveToCenter(View itemView, RecyclerView recyclerView) {
        int[] locationView = new int[2];
        itemView.getLocationOnScreen(locationView);
        int viewWidth = itemView.getWidth();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int centerX = dm.widthPixels / 2;
        int distance = locationView[0] - centerX + viewWidth / 2;
        recyclerView.smoothScrollBy(distance, 0);
    }

}
