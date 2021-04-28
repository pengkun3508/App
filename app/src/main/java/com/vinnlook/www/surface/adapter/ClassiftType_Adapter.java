package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.ClassifyTypeBean;

/**
 * @Description:
 * @Time:2020/10/15$
 * @Author:pk$
 */
public class ClassiftType_Adapter extends BaseStateAdapter<ClassifyTypeBean, ClassiftType_Adapter.ClassiftTypeHolder> {

    Context context;

    public ClassiftType_Adapter(Context context) {
        this.context = context;
    }

    @Override
    protected ClassiftTypeHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ClassiftTypeHolder(inflate(parent, R.layout.classifytype_item));
    }

    class ClassiftTypeHolder extends BaseHolder<ClassifyTypeBean> {

        TextView classift_type_text;

        ClassiftTypeHolder(View itemView) {
            super(itemView);
            classift_type_text = getView(R.id.classift_type_text);

        }

        @Override
        public void bindData(ClassifyTypeBean data) {
            if (data.getColor_mark().equals("1")) {
                classift_type_text.setBackgroundResource(R.drawable.classify_title_text_bg);
                classift_type_text.setTextColor(context.getResources().getColor(R.color.classify_text));
            } else {
                classift_type_text.setBackgroundResource(R.color.touming);
                classift_type_text.setTextColor(context.getResources().getColor(R.color.white));
            }
            classift_type_text.setText(data.getCat_name());


        }
    }
}



