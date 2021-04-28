package com.vinnlook.www.surface.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.SearchListBean;

public class SearchHotAdapter extends BaseStateAdapter<SearchListBean.HotListBean, SearchHotAdapter.SearchHotHolder> {


    @Override
    protected SearchHotHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new SearchHotHolder(inflate(parent, R.layout.rv_item_search));
    }

    class SearchHotHolder extends BaseHolder<SearchListBean.HotListBean> {
        RoundTextView searchText;


        SearchHotHolder(View itemView) {
            super(itemView);
            searchText = getView(R.id.search_text);
        }

        @Override
        public void bindData(SearchListBean.HotListBean data) {
            searchText.setText(data.getKeyword());

        }


    }
}