package com.vinnlook.www.surface.fragment.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinnlook.www.R;
import com.vinnlook.www.event.MainHomeActivityEvent;

import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Time:2021/4/14$
 * @Author:pk$
 */
public class Title_New_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HashMap<String, Object>> getDatas;
    private static final int ITEM_FOOT = 4;
    OnItemClickListener listener;

    public Title_New_Adapter(Context context, List<HashMap<String, Object>> getData) {
        this.context = context;
        this.getDatas = getData;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listenser) {
        this.listener = listenser;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_FOOT) {
            View view = LayoutInflater.from(context).inflate(R.layout.discount_item_2, parent, false);
            return new FootViewHolder(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.title_new_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FootViewHolder) {
            ((FootViewHolder) holder).more_layout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "点击查看更多", Toast.LENGTH_SHORT).show();
//                    HomePublicClassActivity.startSelf(context, title, "0", "", "", String.valueOf(titleId), "");
                    new MainHomeActivityEvent("4").post();
                }
            });
        } else if (holder instanceof ViewHolder) {
            Matrix matrix = new Matrix();           //创建一个单位矩阵
            matrix.setTranslate(0, 0);          //平移x和y各100单位
            matrix.preRotate(0);                   //顺时针旋转30度
            ((ViewHolder) holder).title_img.setScaleType(ImageView.ScaleType.MATRIX);
            ((ViewHolder) holder).title_img.setImageMatrix(matrix);
//            ImageLoader.image(context, ((ViewHolder) holder).paoqi_item_img, getDatas.get(position).getGoods_thumb());
            Log.e("TitleDatas", "==phont==" + getDatas.get(position).get("phont"));
//            int asda = Integer.parseInt(getDatas.get(position).get("phont").toString());


            if (position == 0) {
                ((ViewHolder) holder).title_img.setImageDrawable(context.getResources()
                        .getDrawable(R.mipmap.new_haitao_icon));
            } else if (position == 1) {
                ((ViewHolder) holder).title_img.setImageDrawable(context.getResources()
                        .getDrawable(R.mipmap.new_ziying_icon));
            } else if (position == 2) {
                ((ViewHolder) holder).title_img.setImageDrawable(context.getResources()
                        .getDrawable(R.mipmap.new_xianshi_icon));
            } else if (position == 3) {
                ((ViewHolder) holder).title_img.setImageDrawable(context.getResources()
                        .getDrawable(R.mipmap.new_phang_icon));
            } else if (position == 4) {
                ((ViewHolder) holder).title_img.setImageDrawable(context.getResources()
                        .getDrawable(R.mipmap.new_huli_icon));
            } else if (position == 5) {
                ((ViewHolder) holder).title_img.setImageDrawable(context.getResources()
                        .getDrawable(R.mipmap.new_peidai_icon));
            } else if (position == 6) {
                ((ViewHolder) holder).title_img.setImageDrawable(context.getResources()
                        .getDrawable(R.mipmap.new_yimei_icon));
            }

            ((ViewHolder) holder).title_text.setText(getDatas.get(position).get("name").toString());

            ((ViewHolder) holder).paoqi_item_layout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });


//            ((ViewHolder) holder).paoqi_item_layout_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    MoveAbooutActivity_3.startSelf((Activity) context, getDatas.get(position).getGoods_id(), getDatas.get(position).getSearch_attr());
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return getDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFootView(position)) {
            return ITEM_FOOT;
        }
        return super.getItemViewType(position);
    }

    public boolean isFootView(int position) {
        return position >= getDatas.size();
    }


    class FootViewHolder extends RecyclerView.ViewHolder {
        LinearLayout more_layout_btn;

        public FootViewHolder(View itemView) {
            super(itemView);
            more_layout_btn = itemView.findViewById(R.id.more_layout_btn);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView title_img;
        TextView title_text;
        LinearLayout paoqi_item_layout_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            title_img = itemView.findViewById(R.id.title_img);
            title_text = itemView.findViewById(R.id.title_text);
            paoqi_item_layout_btn = itemView.findViewById(R.id.paoqi_item_layout_btn);

        }
    }
}

