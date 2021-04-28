package com.vinnlook.www.surface.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;

public class HomeTab3Adapter extends BaseStateAdapter<String, HomeTab3Adapter.HomeTabHolder> {


    @Override
    protected HomeTabHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new HomeTabHolder(inflate(parent, R.layout.classifylist_item));
    }

    class HomeTabHolder extends BaseHolder<String> {


        HomeTabHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void bindData(String data) {

        }


    }
}