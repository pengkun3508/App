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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.adapter.ProblemDetailsAdapter;
import com.vinnlook.www.surface.bean.ProblemDetailsBean;
import com.vinnlook.www.surface.mvp.presenter.ProblemDetailsPresenter;
import com.vinnlook.www.surface.mvp.view.ProblemDetailsView;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:回答问题列表
 * @Time:2020/11/11$
 * @Author:pk$
 */
public class ProblemDetailsActivity extends BaseActivity<ProblemDetailsPresenter> implements ProblemDetailsView {


    static MoveDataBean moveDataBean;
    static String id;
    static int pos;
    ProblemDetailsAdapter adapter;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.wen_details_img)
    RoundedImageView wenDetailsImg;
    @BindView(R.id.wen_details_name)
    TextView wenDetailsName;
    @BindView(R.id.wen_details_number)
    TextView wenDetailsNumber;
    @BindView(R.id.wen_1_layout)
    LinearLayout wen1Layout;
    @BindView(R.id.wen_details_number2)
    TextView wenDetailsNumber2;
    @BindView(R.id.problem_details_recycler)
    RecyclerView problemDetailsRecycler;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.wen_details_edittext)
    TextView wenDetailsEdittext;
    @BindView(R.id.wen_details_btn)
    RoundTextView wenDetailsBtn;
    @BindView(R.id.wen_shuru_details_edittext)
    EditText wenShuruDetailsEdittext;
    @BindView(R.id.wen_shuru_details_btn)
    RoundTextView wenShuruDetailsBtn;
    @BindView(R.id.shurukuang_details_layout)
    LinearLayout shurukuangDetailsLayout;


    int page = 1;
    int lastItem = -1;
    int judge = 0;

    String priceNum;//点赞数量
    String mark;//判断攒点与否  8--点赞；9--取消点赞
    int position;

    ProblemDetailsBean problemDetailsBean;


    public static void startSelf(Context context, MoveDataBean moveDataBeans, String ids, int poss) {
        Intent intent = new Intent(context, ProblemDetailsActivity.class);
        context.startActivity(intent);
        moveDataBean = moveDataBeans;
        id = ids;
        pos = poss;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_problem_details;
    }

    @Override
    protected ProblemDetailsPresenter initPresenter() {
        return new ProblemDetailsPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);

        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        wenDetailsImg.setScaleType(ImageView.ScaleType.MATRIX);
        wenDetailsImg.setImageMatrix(matrix);


        if (moveDataBean.getInfo().getBanner().get(0).getType() == 1) {
            ImageLoader.image(this, wenDetailsImg, moveDataBean.getInfo().getBanner().get(0).getUrl());
        } else if (moveDataBean.getInfo().getBanner().get(0).getType() == 2) {
            ImageLoader.image(this, wenDetailsImg, moveDataBean.getInfo().getBanner().get(1).getUrl());
        }
//        ImageLoader.image(this, wenDetailsImg, moveDataBean.getInfo().getBanner().get(0));
        wenDetailsName.setText(moveDataBean.getInfo().getShop_name() + moveDataBean.getInfo().getShop_attr_name());


        adapter = new ProblemDetailsAdapter(this);
        final GridLayoutManager manager5 = new GridLayoutManager(getActivity(), 1);
        problemDetailsRecycler.setLayoutManager(manager5);
        problemDetailsRecycler.setAdapter(adapter);


        adapter.setProblemZanClickListener(new ProblemDetailsAdapter.ProblemZanClickListener() {
            @Override
            public void onGoClickListener(String iD, String type, int positions) {
                position = positions;
                presenter.getEditPraise(iD, type);
            }
        });


        //刷新数据
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.getProblemDetailsList(moveDataBean.getInfo().getGoods_id(), id, page, 10);
                judge = 0;
            }
        });

        problemDetailsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                if (adapter.getData().size() - 5 == lastVisibleItem) {
                    if (lastItem != lastVisibleItem) {
                        lastItem = lastVisibleItem;
                        page++;
                        judge = 1;
                        presenter.getProblemDetailsList(moveDataBean.getInfo().getGoods_id(), id, page, 10);
                    }
                } else {
                    if (adapter.getData().size() - lastItem > 10) {
                        lastItem = adapter.getData().size() - 5;
                        page++;
                        judge = 1;
                        presenter.getProblemDetailsList(moveDataBean.getInfo().getGoods_id(), id, page, 10);
                    }
                }
            }
        });


        //点击输入框
        wenDetailsEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shurukuangDetailsLayout.setVisibility(View.VISIBLE);
                wenShuruDetailsEdittext.setFocusable(true);
                wenShuruDetailsEdittext.setFocusableInTouchMode(true);
                wenShuruDetailsEdittext.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(wenShuruDetailsEdittext, 0);
            }
        });
//输入之前的提交按钮
        wenDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shurukuangDetailsLayout.setVisibility(View.VISIBLE);
                wenShuruDetailsEdittext.setFocusable(true);
                wenShuruDetailsEdittext.setFocusableInTouchMode(true);
                wenShuruDetailsEdittext.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(wenShuruDetailsEdittext, 0);
            }
        });

        shurukuangDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shurukuangDetailsLayout.setVisibility(View.GONE);
                wenDetailsEdittext.setText(wenShuruDetailsEdittext.getText().toString());
            }
        });

        //输入内容
        wenDetailsEdittext.addTextChangedListener(new TextWatcher() {
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
                    Toast.makeText(ProblemDetailsActivity.this, "上限了，亲", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //提交数据
        wenShuruDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getAnswerData(id, wenShuruDetailsEdittext.getText().toString().trim());

            }
        });

    }

    @Override
    protected void loadData() {
        presenter.getProblemDetailsList(moveDataBean.getInfo().getGoods_id(), id, 1, 10);

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
    public void getProblemDetailsSuccess(int code, ProblemDetailsBean data) {
//        wenDetailsNumber2.setText("全部" + data.getCount() + "个回答");
        wenDetailsNumber.setText("共" + data.getCount() + "个回答");
        smartRefreshLayout.finishRefresh();

        if (judge == 0) {
            problemDetailsBean = data;
            adapter.setData(data.getList());
        } else {
//            adapter.addData(data.getList());
            for (int i = 0; i < data.getList().size(); i++) {
                Log.e("problemDetailsBean", " problemDetailsBean.getList()" + problemDetailsBean.getList());
                Log.e("problemDetailsBean", "getList====" + data.getList().get(i));
                problemDetailsBean.getList().add(data.getList().get(i));
            }
            adapter.setData(problemDetailsBean.getList());

        }

//        new ProblemList2Event(adapter.getData().get(0).getUser_name(),adapter.getData().get(0).getContent(), data.getCount(),pos).post();


        //不可以回答
        if (data.getIs_answer().equals("0")) {
            wenDetailsEdittext.setText("已买过的用户才可进行评价");
            wenDetailsEdittext.setTextColor(getResources().getColor(R.color.ex_col));
            wenDetailsBtn.setBackground(getResources().getDrawable(R.drawable.wen_details_btncolor_2));

            wenDetailsEdittext.setClickable(false);
            wenDetailsEdittext.setEnabled(false);
            wenDetailsBtn.setClickable(false);
            wenDetailsBtn.setEnabled(false);

            //可以回答
        } else if (data.getIs_answer().equals("1")) {
            wenDetailsEdittext.setText("进行回答问题，0~50字");
            wenDetailsEdittext.setTextColor(getResources().getColor(R.color.ex_col_2));
            wenDetailsBtn.setBackground(getResources().getDrawable(R.drawable.wen_details_btn_bgcolor));

            wenDetailsEdittext.setClickable(true);
            wenDetailsEdittext.setEnabled(true);
            wenDetailsBtn.setClickable(true);
            wenDetailsBtn.setEnabled(true);
        }


    }

    /**
     * 下载数据失败
     *
     * @param code
     * @param
     */
    @Override
    public void getProblemDetailsFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 提交成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getAnswerDataSuccess(int code, Object data) {
        shurukuangDetailsLayout.setVisibility(View.GONE);

        presenter.getProblemDetailsList(moveDataBean.getInfo().getGoods_id(), id, 1, 10);
    }

    /**
     * 提交失败
     *
     * @param code
     * @param
     */
    @Override
    public void getAnswerDataFail(int code, String msg) {
        if (code == 4000) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 点赞成功
     *
     * @param code
     * @param
     */
    @Override
    public void getEditPraiseSuccess(int code, Object data) {
//        Toast.makeText(this, "点赞成功", Toast.LENGTH_SHORT).show();


        if (problemDetailsBean.getList().get(position).getIs_praise() == 0) {//点赞
            Toast.makeText(this, "点赞成功", Toast.LENGTH_SHORT).show();
            problemDetailsBean.getList().get(position).setPraise_count(Integer.parseInt(problemDetailsBean.getList().get(position).getPraise_count()) + 1 + "");
            problemDetailsBean.getList().get(position).setIs_praise(1);
//            priceNum = Integer.parseInt(priceNum) + 1 + "";

        } else if (problemDetailsBean.getList().get(position).getIs_praise() == 1) {//取消点赞
            Toast.makeText(this, "取消成功", Toast.LENGTH_SHORT).show();
            problemDetailsBean.getList().get(position).setPraise_count(Integer.parseInt(problemDetailsBean.getList().get(position).getPraise_count()) - 1 + "");
            problemDetailsBean.getList().get(position).setIs_praise(0);
//            priceNum = Integer.parseInt(priceNum) - 1 + "";
        }

        adapter.setData(problemDetailsBean.getList());
        adapter.notifyDataSetChanged();


//        presenter.getProblemDetailsList(moveDataBean.getInfo().getGoods_id(), id, page, 10);
    }

    /**
     * 点赞失败
     *
     * @param code
     * @param
     */
    @Override
    public void getEditPraiseFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
