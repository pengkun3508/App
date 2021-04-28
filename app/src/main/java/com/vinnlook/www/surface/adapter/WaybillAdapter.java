package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.WayBillLogisticsBean;

/**
 * @Description:
 * @Time:2021/2/5$
 * @Author:pk$
 */
public class WaybillAdapter extends BaseStateAdapter<WayBillLogisticsBean.TracesBean, WaybillAdapter.WaybillHolder> {
    int mHeight;

    Context context;

    public WaybillAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected WaybillHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new WaybillHolder(inflate(parent, R.layout.rv_item_logistics));
    }

    class WaybillHolder extends BaseHolder<WayBillLogisticsBean.TracesBean> {

        View view, view2;
        TextView item_time, item_content;
        LinearLayout content_layout;


        WaybillHolder(View itemView) {
            super(itemView);
            view = getView(R.id.view);
            view2 = getView(R.id.view2);
            item_time = getView(R.id.item_time);
            item_content = getView(R.id.item_content);
            content_layout = getView(R.id.content_layout);
        }

        @Override
        public void bindData(WayBillLogisticsBean.TracesBean data) {
            if (getAdapterPosition() == 0) {
                view.setVisibility(View.INVISIBLE);
            } else {
                view.setVisibility(View.VISIBLE);
            }

            if (getAdapterPosition() == getData().size() - 1) {
                view2.setVisibility(View.INVISIBLE);
            } else {
                view2.setVisibility(View.VISIBLE);
            }
            item_time.setText(data.getAcceptTime());
            item_content.setText(data.getAcceptStation());


//获屏幕宽度
//            DisplayMetrics dm = new DisplayMetrics();
//            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
//            int height=dm.heightPixels ;


            content_layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // TODO Auto-generated method stub
                    content_layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int height = content_layout.getMeasuredHeight();
                    Log.e("测试：", content_layout.getMeasuredHeight()+ "," + content_layout.getMeasuredWidth());
                    RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) view2.getLayoutParams(); //取控件textView当前的布局参数
                    linearParams.height = height;// 控件的高强制设成20
                    view2.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
                }
            });


        }
    }

}
