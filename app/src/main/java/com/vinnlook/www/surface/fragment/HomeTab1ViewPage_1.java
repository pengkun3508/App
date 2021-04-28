package com.vinnlook.www.surface.fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.surface.activity.HaiTaoClassActivity;
import com.vinnlook.www.surface.activity.HomePublicClassActivity;
import com.vinnlook.www.surface.mvp.presenter.HomeTab1ViewPagePresenter;
import com.vinnlook.www.surface.mvp.view.HomeTab1ViewPageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description:
 * @Time:2021/4/2$
 * @Author:pk$
 */
public class HomeTab1ViewPage_1 extends BaseFragment<HomeTab1ViewPagePresenter> implements HomeTab1ViewPageView {


    @BindView(R.id.home_haitao_btn)
    LinearLayout homeHaitaoBtn;
    @BindView(R.id.home_ziying_btn)
    LinearLayout homeZiyingBtn;
    @BindView(R.id.home_huli_btn)
    LinearLayout homeHuliBtn;
    @BindView(R.id.home_touming_btn)
    LinearLayout homeToumingBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.home_tab_1_viewpage_1;
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


    @OnClick({R.id.home_haitao_btn, R.id.home_ziying_btn, R.id.home_huli_btn, R.id.home_touming_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_haitao_btn://海淘专区
                HaiTaoClassActivity.startSelf(getActivity(), "海淘专区", "2");
                break;
            case R.id.home_ziying_btn://自营专区
                HaiTaoClassActivity.startSelf(getActivity(), "自营专区", "1");
                break;
            case R.id.home_huli_btn://护理液
                HomePublicClassActivity.startSelf(getActivity(), "护理液专区", "", "2", "", "", "");
                break;
            case R.id.home_touming_btn://透明片
                HomePublicClassActivity.startSelf(getActivity(), "透明片专区", "", "1", "", "", "");
                break;
        }
    }
}
