package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.luck.picture.lib.entity.LocalMedia;
import com.vinnlook.www.R;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.fragment.adapter.BaseStateAdapter5;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/6$
 * @Author:pk$
 */
public class WenListAdapter extends BaseStateAdapter5<MoveDataBean.QuestionListBean, WenListAdapter.WenListHolder> {

    Context context;
    CommentImgAdapter commentAdapter;
    List<String> getImage;
    List<LocalMedia> selectList;

    public WenListAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected WenListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new WenListHolder(inflate(parent, R.layout.wen_list_item));
    }

    class WenListHolder extends BaseHolder<MoveDataBean.QuestionListBean> {

        TextView wen_conten_text, wen_number_text;

        WenListHolder(View itemView) {
            super(itemView);
            wen_conten_text = itemView.findViewById(R.id.wen_conten_text);//头像
            wen_number_text = itemView.findViewById(R.id.wen_number_text);//名称


        }

        @Override
        public void bindData(MoveDataBean.QuestionListBean data) {

            wen_conten_text.setText(data.getQuestion());
            wen_number_text.setText(data.getAnswer_count() + "个回答");


        }


    }
}

