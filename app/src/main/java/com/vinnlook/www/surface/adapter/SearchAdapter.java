package com.vinnlook.www.surface.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.flyco.roundview.RoundTextView;
import com.vinnlook.www.R;


public class SearchAdapter extends BaseStateAdapter<String, SearchAdapter.SearchHolder> {


    @Override
    protected SearchHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new SearchHolder(inflate(parent, R.layout.rv_item_search));
    }

    class SearchHolder extends BaseHolder<String> {
        RoundTextView searchText;

        SearchHolder(View itemView) {
            super(itemView);
            searchText = getView(R.id.search_text);

        }

        @Override
        public void bindData(String data) {
            searchText.setText(data);
        }


    }
}