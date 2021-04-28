package com.vinnlook.www.surface.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.vinnlook.www.R;
import com.vinnlook.www.event.ScreenIdListEvent;
import com.vinnlook.www.surface.bean.ClassifyTypeBean;
import com.vinnlook.www.surface.mvp.presenter.ClassifyFragmentPresenter;

import java.util.ArrayList;

public class ScreenAdapter extends BaseStateAdapter<ClassifyTypeBean, ScreenAdapter.ScreenHolder> {

    ClassifyFragmentPresenter classifyFragmentPresenter;
    StringBuilder sb = new StringBuilder();
    ArrayList<String> iDLists = new ArrayList<>();
    ArrayList<String> iDLists1 = new ArrayList<>();
    String str = "";
    String str1 = "";
    String iDStr;
    String iDStr1;

    ClassifyTypeOnClickAll classifyTypeClickListener;

    public ScreenAdapter(ClassifyFragmentPresenter classifyFragmentPresenters, ClassifyTypeOnClickAll classifyTypeClickListener) {
        this.classifyFragmentPresenter = classifyFragmentPresenters;
        this.classifyTypeClickListener = classifyTypeClickListener;
    }

    @Override
    protected ScreenHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new ScreenHolder(inflate(parent, R.layout.rv_item_screen));
    }

    public void setDatas(String iDStr, String iDStr1) {
        this.iDStr = iDStr;
        this.iDStr1 = iDStr1;

    }

    class ScreenHolder extends BaseHolder<ClassifyTypeBean> {

        TextView tv_title;
        RecyclerView recyclerView;
        String newString;

        ScreenHolder(View itemView) {
            super(itemView);
            tv_title = getView(R.id.tv_title);
            recyclerView = getView(R.id.recyclerView);
            recyclerView.setLayoutManager(new FlexboxLayoutManager(itemView.getContext()));
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(true);
        }

        @Override
        public void bindData(ClassifyTypeBean data) {

            TypeItemAdapter adapter = new TypeItemAdapter();
            recyclerView.setAdapter(adapter);
            Log.e("ScreenAdapter", "bindData==iDStr===" + iDStr);
            iDLists = new ArrayList<>();
            iDLists1 = new ArrayList<>();
            String[] asdas = iDStr.split(",");
            for (int i = 0; i < asdas.length; i++) {
                iDLists.add(asdas[i]);
            }
            Log.e("ScreenAdapter", "bindData==iDLists===" + iDLists);
            for (int i = 0; i < data.getSon_list().size(); i++) {
                if (iDLists.contains(data.getSon_list().get(i).getCat_id())) {
                    data.getSon_list().get(i).setType("2");
                } else {
                    data.getSon_list().get(i).setType("1");//未选择状态
                }
            }
            adapter.setData(data.getSon_list());
            tv_title.setText(data.getCat_name());

            Log.e("ScreenAdapter", "bindData==iDStr1111===" + iDStr1);

            if (!iDStr1.equals("")) {
                String[] asdas1 = iDStr1.split(",");
                for (int i = 0; i < asdas1.length; i++) {
                    iDLists1.add(asdas1[i].toString());
                    Log.e("ScreenAdapter", "bindData==asdas1[i]===" + asdas1[i]);
                }
                Log.e("ScreenAdapter", "bindData==iDLists1111===" + iDLists1);
            }
            //点击事件
            adapter.addOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view, int position) {
                    adapter.getData().get(position).getCat_id();
//                    classifyFragmentPresenter.getClassifyData("", 1, 10, mark, sort_key, sort_value);//下载分类数据
                    if (adapter.getData().get(position).getType().equals("1")) {
                        adapter.getData().get(position).setType("2");
                        StringBuilder sv = new StringBuilder();
                        StringBuilder sv1 = new StringBuilder();
                        sv1.append(adapter.getData().get(position).getParent_id());
                        sv1.append(":");
                        sv1.append(adapter.getData().get(position).getCat_id());
                        sv.append(adapter.getData().get(position).getCat_id());
                        Log.e("bindData", "==sv=" + sv.toString());
                        Log.e("bindData", "==sv=iDLists00000==" + iDLists);
                        iDLists1.add(sv1.toString());
                        iDLists.add(sv.toString());
                        Log.e("bindData", "==sv=iDLists==" + iDLists);
                    } else if (adapter.getData().get(position).getType().equals("2")) {
                        adapter.getData().get(position).setType("1");
                        StringBuilder sv = new StringBuilder();
                        StringBuilder sv1 = new StringBuilder();
                        sv1.append(adapter.getData().get(position).getParent_id());
                        sv1.append(":");
                        sv1.append(adapter.getData().get(position).getCat_id());

                        sv.append(adapter.getData().get(position).getCat_id());

                        Log.e("bindData", "==idList=2222=" + iDLists);

                        Log.e("bindData", "==sv1=1111=" + sv1.toString());

                        Log.e("ScreenAdapter", "idList111111111===" + iDLists1);
                        if (iDLists.contains(sv.toString())) {
                            iDLists.remove(sv.toString());
                        }
                        if (iDLists1.contains(sv1.toString())) {
                            iDLists1.remove(sv1.toString());
                        }


                    }
                    Log.e("ScreenAdapter", "idList===" + iDLists);

                    if (iDLists.size() > 0) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < iDLists.size(); i++) {
                            stringBuffer.append(iDLists.get(i).toString().trim() + ",");
                        }
//                        if (!iDStr.equals("")) {
//                            stringBuffer.append(iDStr);
//                        }
                        str = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
                        Log.e("ScreenAdapter", "str===" + str);
                    } else {
                        str = "";
                    }

                    Log.e("ScreenAdapter", "idList1111===" + iDLists1);
                    if (iDLists1.size() > 0) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < iDLists1.size(); i++) {
                            stringBuffer.append(iDLists1.get(i).toString().trim() + ",");
                        }
//                        if (!iDStr.equals("")) {
//                            stringBuffer.append(iDStr);
//                        }
                        str1 = stringBuffer.substring(0, stringBuffer.length() - 1).toString();
                        Log.e("ScreenAdapter", "str1111===" + str1);
                    } else {
                        str1 = "";
                    }

                    new ScreenIdListEvent(str, str1).post();
                    adapter.notifyDataSetChanged();
//
                    classifyTypeClickListener.onTypeClickListener(str, str1);

                }
            });


        }


    }

    public interface ClassifyTypeOnClickAll {
        void onTypeClickListener(String str, String str1);
    }


}