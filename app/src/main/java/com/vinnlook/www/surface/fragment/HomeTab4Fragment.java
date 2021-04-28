package com.vinnlook.www.surface.fragment;

import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseFragment;
import com.vinnlook.www.surface.mvp.presenter.HomeTab3FragmentPresenter;
import com.vinnlook.www.surface.mvp.presenter.HomeTab4FragmentPresenter;
import com.vinnlook.www.surface.mvp.view.HomeTab3FragmentView;
import com.vinnlook.www.surface.mvp.view.HomeTab4FragmentView;

/**
 * @Description:
 * @Time:2021/4/2$
 * @Author:pk$
 */
public  class HomeTab4Fragment extends BaseFragment<HomeTab4FragmentPresenter> implements HomeTab4FragmentView {


    @Override
    protected int getLayoutId() {
        return R.layout.home_tab_4_fragment;
    }

    @Override
    protected HomeTab4FragmentPresenter initPresenter() {
        return new HomeTab4FragmentPresenter();
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void loadData() {
//        presenter.getBrendList();//下载首页品牌数据
    }


}
