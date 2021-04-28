package com.vinnlook.www.surface.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.surface.activity.HomePublicClassActivity;
import com.vinnlook.www.surface.activity.MemberActivity_1;
import com.vinnlook.www.surface.activity.RankingListActivity_1;
import com.vinnlook.www.surface.mvp.presenter.HomeTab1ViewPagePresenter;
import com.vinnlook.www.surface.mvp.view.HomeTab1ViewPageView;
import com.vinnlook.www.utils.UserUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description:
 * @Time:2021/4/2$
 * @Author:pk$
 */
public class HomeTab1ViewPage_2 extends BaseFragment<HomeTab1ViewPagePresenter> implements HomeTab1ViewPageView {


    @BindView(R.id.home_peidai_btn)
    LinearLayout homePeidaiBtn;
    @BindView(R.id.home_yimei_btn)
    LinearLayout homeYimeiBtn;
    @BindView(R.id.home_paihang_btn)
    LinearLayout homePaihangBtn;
    @BindView(R.id.home_plus_btn)
    LinearLayout homePlusBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.home_tab_1_viewpage_2;
    }

    @Override
    protected HomeTab1ViewPagePresenter initPresenter() {
        return new HomeTab1ViewPagePresenter();
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {
    }


    @OnClick({R.id.home_peidai_btn, R.id.home_yimei_btn, R.id.home_paihang_btn, R.id.home_plus_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_peidai_btn://佩戴
                HomePublicClassActivity.startSelf(getActivity(), "佩戴工具专区", "", "", "1", "", "");
                break;
            case R.id.home_yimei_btn://医美
                HomePublicClassActivity.startSelf(getActivity(), "医美专区", "", "", "2", "", "");
                break;
            case R.id.home_paihang_btn://排行
                RankingListActivity_1.startSelf(getActivity());
                break;
            case R.id.home_plus_btn://Plus会员
                if (!UserUtils.getInstance().getUserId().equals("")) {
//                        MemberActivity.startSelf(getContext(), "2");//会员购买入口
                    MemberActivity_1.startSelf(getContext(), "2");//会员购买入口
                } else {
                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
