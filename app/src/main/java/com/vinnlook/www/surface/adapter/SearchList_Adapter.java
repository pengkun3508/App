package com.vinnlook.www.surface.adapter;

import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;
import com.vinnlook.www.utils.KeywordUtil;

/**
 * @Description: 分类检索适配器
 * @Time:2021/1/20$
 * @Author:pk$
 */
public class SearchList_Adapter extends BaseStateAdapter5<String, SearchList_Adapter.SearchList_Holder> {
    String keywords;
    @Override
    protected SearchList_Holder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new SearchList_Holder(inflate(parent, R.layout.search_item));
    }

    public void setKeyWords(String keywordss) {
        keywords=keywordss;
    }

    class SearchList_Holder extends BaseHolder<String> {

        TextView item_text;

        SearchList_Holder(View itemView) {
            super(itemView);
            item_text = getView(R.id.item_text);

        }

        @Override
        public void bindData(String data) {

            SpannableString string = KeywordUtil.matcherSearchTitle(item_text.getContext().getResources().getColor(R.color.black), data, keywords);
            item_text.setText(string);
        }
    }
}

