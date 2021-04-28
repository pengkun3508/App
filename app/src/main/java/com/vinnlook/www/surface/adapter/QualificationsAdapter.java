package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.CertifyListBean;

import java.util.List;

/**
 * @Description:
 * @Time:2021/4/19$
 * @Author:pk$
 */
public class QualificationsAdapter extends BaseStateAdapter<CertifyListBean, QualificationsAdapter.QualificationsHolder> {

    Context getContext;
    Context context;

    public QualificationsAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected QualificationsHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new QualificationsHolder(inflate(parent, R.layout.qualifications_item));
    }


    class QualificationsHolder extends BaseHolder<CertifyListBean> {
        TextView text_1, text_2;

        QualificationsHolder(View itemView) {
            super(itemView);
            getContext = itemView.getContext();
            text_1 = getView(R.id.text_1);
            text_2 = getView(R.id.text_2);
        }

        @Override
        public void bindData(CertifyListBean data) {
            text_1.setText(data.getName());
            text_2.setText(data.getValue());

        }
    }

}
