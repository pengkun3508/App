package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.activity.PhotoViewActivity;
import com.vinnlook.www.surface.bean.EvaluateListBean;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;

import java.util.List;

/**
 * @Description:
 * @Time:2020/5/12$
 * @Author:pk$
 */
public class EvaluateListAdapter extends BaseStateAdapter<EvaluateListBean.ListBean, EvaluateListAdapter.EvaluateListHolder> {
    Context context;
    CommentImgAdapter commentAdapter;

    public EvaluateListAdapter(Context context) {
        this.context = context;
    }


    @Override
    protected EvaluateListHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new EvaluateListHolder(inflate(parent, R.layout.comment_item));
    }

    class EvaluateListHolder extends BaseHolder<EvaluateListBean.ListBean> {
        RoundedImageView comment_img_head;
        TextView comment_name, comment_time, comment_type, comment_conten, kefu_content;
        RecyclerView comment_img_grid;
        LinearLayout kefu_content_layout;

        EvaluateListHolder(View itemView) {
            super(itemView);
            comment_img_head = itemView.findViewById(R.id.comment_img_head);//头像
            comment_name = itemView.findViewById(R.id.comment_name);//名称
            comment_time = itemView.findViewById(R.id.comment_time);//时间
            comment_type = itemView.findViewById(R.id.comment_type);//规格类型
            comment_conten = itemView.findViewById(R.id.comment_conten);//规格类型
            comment_img_grid = itemView.findViewById(R.id.comment_img_grid);//图片List
            kefu_content = itemView.findViewById(R.id.kefu_content);//客服回复内容
            kefu_content_layout = itemView.findViewById(R.id.kefu_content_layout);//客服回复内容


        }

        @Override
        public void bindData(EvaluateListBean.ListBean data) {
            commentAdapter = new CommentImgAdapter(context);
            final GridLayoutManager manager3 = new GridLayoutManager(context, 1);
            manager3.setOrientation(GridLayoutManager.HORIZONTAL);
            comment_img_grid.setLayoutManager(manager3);
            comment_img_grid.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(context, 1)));
            comment_img_grid.addItemDecoration(new SpaceItemDecoration(0, 0));
            comment_img_grid.setNestedScrollingEnabled(false);
            comment_img_grid.setHasFixedSize(true);
            comment_img_grid.setAdapter(commentAdapter);

            comment_img_head.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.userIcon(context, comment_img_head, data.getImg_url());

            comment_name.setText(data.getUser_name());

            comment_time.setText(data.getAdd_time());
            comment_type.setText(data.getInfo());
            comment_conten.setText(data.getContent());


            Log.e("评论图片", "==评论图片==" + data);
            if (data.getImage().size() > 0) {
                List<String> getImage = data.getImage();
                comment_img_grid.setVisibility(View.VISIBLE);
                commentAdapter.setData(getImage);
                commentAdapter.notifyDataSetChanged();
            } else {
                comment_img_grid.setVisibility(View.GONE);
            }

            //是否有客服回复内容
            if (data.getReply_content().equals("")) {
                kefu_content_layout.setVisibility(View.GONE);
            } else {
                kefu_content_layout.setVisibility(View.VISIBLE);
                kefu_content.setText(data.getReply_content());
            }


            //查看图片
            commentAdapter.addOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view, int position) {
                    PhotoViewActivity.startSelf((Activity) context, data.getImage(), position);
                }
            });


        }


    }

}
