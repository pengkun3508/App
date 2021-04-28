package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.ProblemListEvent;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.adapter.ProblemAdapter;
import com.vinnlook.www.surface.bean.ProblemBean;
import com.vinnlook.www.surface.mvp.presenter.ProblemPresenter;
import com.vinnlook.www.surface.mvp.view.ProblemView;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 问一问列表
 * @Time:2020/11/6$
 * @Author:pk$
 */
public class ProblemActivity extends BaseActivity<ProblemPresenter> implements ProblemView {

    static Context context;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.problem_recycler)
    RecyclerView problemRecycler;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    ProblemAdapter adapter;
    static MoveDataBean moveDataBean;
    @BindView(R.id.wen_wen_img)
    RoundedImageView wenWenImg;
    @BindView(R.id.wen_wen_name)
    TextView wenWenName;
    @BindView(R.id.wen_wen_number)
    TextView wenWenNumber;

    List<ProblemBean.ListBean> problemBean;
    @BindView(R.id.wen_wen_edittext)
    TextView wenWenEdittext;
    @BindView(R.id.wen_wen_btn)
    RoundTextView wenWenBtn;

    public PopupWindow popupwindow;
    @BindView(R.id.shurukuang_layout)
    LinearLayout shurukuangLayout;
    @BindView(R.id.wen_shuru_edittext)
    EditText wenShuruEdittext;
    @BindView(R.id.wen_shuru_btn)
    RoundTextView wenShuruBtn;

    int page = 1;
    int lastItem = -1;
    int judge = 0;

    ProblemBean problemBeans;

    public static void startSelf(Context contexts, MoveDataBean moveDataBeans) {
        Intent intent = new Intent(contexts, ProblemActivity.class);
        contexts.startActivity(intent);
        context = contexts;
        moveDataBean = moveDataBeans;
    }

//    @Override
//    protected boolean isRegisterEventBus() {
//        return true;
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_problem;
    }


    @Override
    protected ProblemPresenter initPresenter() {
        return new ProblemPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        wenWenImg.setScaleType(ImageView.ScaleType.MATRIX);
        wenWenImg.setImageMatrix(matrix);

        if (moveDataBean.getInfo().getBanner().get(0).getType() == 1) {
            ImageLoader.image(this, wenWenImg, moveDataBean.getInfo().getBanner().get(0).getUrl());
        } else if (moveDataBean.getInfo().getBanner().get(0).getType() == 2) {
            ImageLoader.image(this, wenWenImg, moveDataBean.getInfo().getBanner().get(1).getUrl());
        }


        wenWenName.setText(moveDataBean.getInfo().getShop_name() + moveDataBean.getInfo().getShop_attr_name());
        wenWenNumber.setText("共" + moveDataBean.getInfo().getQuestion_count() + "个问题");

        adapter = new ProblemAdapter(this);
        final GridLayoutManager manager5 = new GridLayoutManager(getActivity(), 1);
        problemRecycler.setLayoutManager(manager5);
        problemRecycler.setAdapter(adapter);


        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getProblemList(moveDataBean.getInfo().getGoods_id(), page, 10);
                judge = 0;
            }
        });

        problemRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (adapter.getData().size() - 5 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getProblemList(moveDataBean.getInfo().getGoods_id(), page, 10);
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getProblemList(moveDataBean.getInfo().getGoods_id(), page, 10);
                    }
                }
            }
        });


        //点击输入框
        wenWenEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (popupwindow != null && popupwindow.isShowing()) {
//                    popupwindow.dismiss();
//                    return;
//                } else {
//                    initmPopupWindowView();
//                    popupwindow.showAtLocation(actionBar, Gravity.CENTER, 0, 0);
//                }

                shurukuangLayout.setVisibility(View.VISIBLE);
                wenShuruEdittext.setFocusable(true);
                wenShuruEdittext.setFocusableInTouchMode(true);
                wenShuruEdittext.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(wenShuruEdittext, 0);

            }
        });

        //输入之前的提交
        wenWenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shurukuangLayout.setVisibility(View.VISIBLE);
                wenShuruEdittext.setFocusable(true);
                wenShuruEdittext.setFocusableInTouchMode(true);
                wenShuruEdittext.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(wenShuruEdittext, 0);


            }
        });

        shurukuangLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shurukuangLayout.setVisibility(View.GONE);
                wenWenEdittext.setText(wenShuruEdittext.getText().toString());
            }
        });

        //输入内容
        wenShuruEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("输入框字数", "==wenShuruEdittext==" + editable.length());
                if (editable.length() >= 50) {
                    Toast.makeText(ProblemActivity.this, "上限了，亲", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //提交数据
        wenShuruBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getQuestionData(moveDataBean.getInfo().getGoods_id(), moveDataBean.getInfo().getSearch_attr(), wenShuruEdittext.getText().toString().trim());

            }
        });


    }

    @Override
    protected void loadData() {
//        presenter.getProblemList(moveDataBean.getInfo().getGoods_id(), 1, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getProblemList(moveDataBean.getInfo().getGoods_id(), page, 10);
    }

    /**
     * 下载数据成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getProblemListSuccess(int code, ProblemBean data) {
        problemBean = data.getList();
        problemBeans = data;
        smartRefreshLayout.finishRefresh();
        if (judge == 0) {
            adapter.setData(data.getList());
        } else {
            adapter.addData(data.getList());
        }

        List<ProblemBean.ListBean> newList = new ArrayList<>();
        if (adapter.getData().size() > 2) {
            newList.add(adapter.getData().get(0));
            newList.add(adapter.getData().get(1));
        } else if (adapter.getData().size() == 1) {
            newList.add(adapter.getData().get(0));
        }

        Log.e("下载数据成功", "==getProblemListSuccess===" + newList.size());
        new ProblemListEvent(newList, data.getCount()).post();
        adapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                ProblemDetailsActivity.startSelf(ProblemActivity.this, moveDataBean, adapter.getData().get(position).getId(), position);


            }
        });

    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param
     */
    @Override
    public void getProblemListFail(int code, String msg) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    /**
     * 提交问题成功
     *
     * @param code
     * @param
     */
    @Override
    public void getQuestionDataSuccess(int code, Object data) {
        shurukuangLayout.setVisibility(View.GONE);

        presenter.getProblemList(moveDataBean.getInfo().getGoods_id(), 1, 10);


    }

    /**
     * 提交问题失败
     *
     * @param code
     * @param
     */
    @Override
    public void getQuestionDataFail(int code, String msg) {
//        if (code == 4000) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//        }
    }


}
