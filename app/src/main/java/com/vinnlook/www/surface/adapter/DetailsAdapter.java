package com.vinnlook.www.surface.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vinnlook.www.R;
import com.vinnlook.www.http.model.MoveDataBean;
import com.vinnlook.www.surface.activity.CouponActivity;
import com.vinnlook.www.surface.activity.WebActivity;
import com.vinnlook.www.surface.bean.DetailsBean;
import com.vinnlook.www.surface.dialog.TypeSelectDialog;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.SuperViewHolder;


/**
 * Created by ：方燚
 * time       ：2018/2/5.
 * description:地址列表
 */

public class DetailsAdapter extends ListBaseAdapter<DetailsBean> {
    private int layoutID;
    private int height = 0;
    MoveDataBean dataBean;
    Context context;


    RecyclerView img_list_details;
    RecyclerView img_list_details2;
    RecyclerView img_list_details3;
    TextView details_suppliers;
    DetailsImagAdapter adapter;
    CommentListAdapter commentAdapter;
    TextView pingjia_number, see_more_pingjia, move_type_text;
    RelativeLayout move_youhuiquan_btn;

    TextView details_brand_name;
    TextView details_goods_sn;
    TextView details_toss_period;
    TextView details_diameter;
    TextView details_color;
    TextView details_base_curve;
    TextView details_water_content;
    TextView details_coloring_diameter;
    TextView details_origin;
    TextView details_life_span;
    RelativeLayout move_type_layout;
    TextView move_transaction_name;
    TextView post_fee_text, fuwu_text;
    View text_line_shuilv;
    LinearLayout text_shuilv;
    RelativeLayout kuaidi_layout_btn;

    public DetailsAdapter(Context context) {
        super(context);
        this.context = context;
    }

    public void setDatas(MoveDataBean dataBean) {
        this.dataBean = dataBean;
    }


    @Override
    public int getItemViewType(int position) {
//        Log.e("DetailsAdapter", "== mDataList.size()==" + mDataList.size());
//        Log.e("DetailsAdapter", "==position==" + position);
        int type = mDataList.get(position).getType();
        if (type == 1) {
//            详情
            layoutID = R.layout.item_details1;
            return 1001;
        } else if (type == 2) {
//            评价title
            layoutID = R.layout.item_details3;
            return 1002;
        } else if (type == 3) {
//            须知
            layoutID = R.layout.item_details5;
            return 1003;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getLayoutId() {
        return layoutID;
    }


    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 1001) {//商品规格之类的-详情
            final LinearLayout item = holder.getView(R.id.item);
            getMeasureHeight(item, type);
            move_transaction_name = item.findViewById(R.id.move_transaction_name);
            move_youhuiquan_btn = item.findViewById(R.id.move_youhuiquan_btn);
            details_suppliers = item.findViewById(R.id.details_suppliers);

            details_brand_name = item.findViewById(R.id.details_brand_name);
            details_goods_sn = item.findViewById(R.id.details_goods_sn);
            details_toss_period = item.findViewById(R.id.details_toss_period);
            details_diameter = item.findViewById(R.id.details_diameter);
            details_color = item.findViewById(R.id.details_color);
            details_base_curve = item.findViewById(R.id.details_base_curve);
            details_water_content = item.findViewById(R.id.details_water_content);
            details_coloring_diameter = item.findViewById(R.id.details_coloring_diameter);
            details_origin = item.findViewById(R.id.details_origin);
            details_life_span = item.findViewById(R.id.details_life_span);
            move_type_text = item.findViewById(R.id.move_type_text);
            move_type_layout = item.findViewById(R.id.move_type_layout);
            post_fee_text = item.findViewById(R.id.post_fee_text);
            fuwu_text = item.findViewById(R.id.fuwu_text);
            text_line_shuilv = item.findViewById(R.id.text_line_shuilv);
            text_shuilv = item.findViewById(R.id.text_shuilv);
            kuaidi_layout_btn = item.findViewById(R.id.kuaidi_layout_btn);


            //评价
            img_list_details2 = item.findViewById(R.id.recyclerv_comment2);
            pingjia_number = item.findViewById(R.id.pingjia_number);
            see_more_pingjia = item.findViewById(R.id.see_more_pingjia);

            pingjia_number.setText("(" + dataBean.getComment().size() + ")");

            commentAdapter = new CommentListAdapter(context);
            final GridLayoutManager manager3 = new GridLayoutManager(context, 1);
            img_list_details2.setLayoutManager(manager3);
            img_list_details2.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(context, 1)));
            img_list_details2.addItemDecoration(new SpaceItemDecoration(0, 0));
            img_list_details2.setNestedScrollingEnabled(false);
            img_list_details2.setHasFixedSize(false);
            commentAdapter.setData(dataBean.getComment());
            img_list_details2.setAdapter(commentAdapter);

            see_more_pingjia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onSeeMoreBtnClick(dataBean.getInfo().getGoods_id());

                }
            });



            move_transaction_name.setText(dataBean.getInfo().getShop_name() + " " + dataBean.getInfo().getShop_attr_name());
            Log.e("商品名称", "getShop_attr_name===" + dataBean.getInfo().getShop_attr_name());
            Log.e("规格名称", "getAttr_names===" + dataBean.getInfo().getShop_attr_name());
            if (dataBean.getInfo().getShop_attr_name().equals("") || dataBean.getInfo().getShop_attr_name() == null) {
                move_type_text.setText("选择 颜色 规格（片数） 度数");
            } else {
                move_type_text.setText("已选 " + dataBean.getInfo().getShop_attr_name());
            }
            details_brand_name.setText(dataBean.getInfo().getBrand_name());
            details_goods_sn.setText(dataBean.getInfo().getGoods_sn());
            details_toss_period.setText(dataBean.getInfo().getToss_period());
            details_color.setText(dataBean.getInfo().getColor());
            details_origin.setText(dataBean.getInfo().getOrigin());
            details_life_span.setText(dataBean.getInfo().getLife_span());
            post_fee_text.setText(dataBean.getInfo().getPost_fee());

            details_diameter.setText(dataBean.getInfo().getDiameter());
            details_base_curve.setText(dataBean.getInfo().getBase_curve());
            details_water_content.setText(dataBean.getInfo().getWater_content());
            details_coloring_diameter.setText(dataBean.getInfo().getColoring_diameter());


            //领取优惠券
            move_youhuiquan_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CouponActivity.startSelf(context);//优惠券页面
                }
            });

            //包邮规则
            kuaidi_layout_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WebActivity.startSelf(context,"http:h5.jealook.com/vinnlook/Package.html");
                }
            });

            //选规格
            move_type_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TypeSelectDialog.with((Activity) context, dataBean, "", "", new TypeSelectDialog.AddShopCarClickListener() {
                        @Override
                        public void onBtnClickListener(String goods_id, String getRec_id,String product_id, String num, String getAttr_names, String mmake) {
//                            dataBean.getInfo().setShop_attr_name(getAttr_names);
//                            move_type_text.setText(getAttr_names);
//                            notifyDataSetChanged();
                            TypeSelectDialog.dismiss();
                        }
                    }).show();
                }
            });


            if (dataBean.getInfo().getSuppliers_id().equals("1")) {//自营
                details_suppliers.setText("自营");
                fuwu_text.setText("自营仓发货");
                text_line_shuilv.setVisibility(View.GONE);
                text_shuilv.setVisibility(View.GONE);
                details_suppliers.setBackgroundDrawable(details_suppliers.getContext().getResources().getDrawable(R.drawable.classify_list_item_1));
            } else if (dataBean.getInfo().getSuppliers_id().equals("2")) {//海淘
                details_suppliers.setText("海淘");
                fuwu_text.setText("保税仓直邮·不支持无理由退货");
                text_line_shuilv.setVisibility(View.VISIBLE);
                text_shuilv.setVisibility(View.VISIBLE);
                details_suppliers.setBackgroundDrawable(details_suppliers.getContext().getResources().getDrawable(R.drawable.classify_list_item));
            }



            Log.e("DetailsAdapter", "=type=" + type);

        }
        if (type == 1002) {//评价title
            final LinearLayout item = holder.getView(R.id.item);
            getMeasureHeight(item, type);
            img_list_details = item.findViewById(R.id.img_list_details_1);
            adapter = new DetailsImagAdapter(context, dataBean.getInfo().getDetails(), "0");
//            final GridLayoutManager manager2 = new GridLayoutManager(context, 1);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            img_list_details.setLayoutManager(layoutManager);
//            img_list_details.setLayoutManager(manager2);
            img_list_details.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(context, 1)));
            img_list_details.addItemDecoration(new SpaceItemDecoration(0, 0));
            img_list_details.setNestedScrollingEnabled(false);
            img_list_details.setFocusable(false);

            img_list_details.setAdapter(adapter);




            Log.e("DetailsAdapter", "=type=" + type);


        }
        if (type == 1003) {//须知
            final LinearLayout item = holder.getView(R.id.item);
            getMeasureHeight(item, type);
            img_list_details3 = item.findViewById(R.id.img_list_details);

            adapter = new DetailsImagAdapter(context, dataBean.getInfo().getUser_notes(), "1");
//            final GridLayoutManager manager2 = new GridLayoutManager(context, 1);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            img_list_details3.setLayoutManager(layoutManager);
//            img_list_details3.setLayoutManager(manager2);
            img_list_details3.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(context, 1)));
            img_list_details3.addItemDecoration(new SpaceItemDecoration(0, 0));
            img_list_details3.setNestedScrollingEnabled(false);
            img_list_details3.setFocusable(false);

            img_list_details3.setAdapter(adapter);


        }
    }

    /**
     * 获取每个item的高度
     *
     * @param view item的跟布局
     * @param type 用于判断是那个item的高度
     */
    int getHeight;

    public void getMeasureHeight(final View view, final int type) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (listener != null) {
                    if (type == 1003) {
                        if (height != 0) {
                            height += view.getHeight();
                            listener.setOnItemHeightListener(height, type);
                        } else {
                            height = view.getHeight();
                        }

                    } else {
                        listener.setOnItemHeightListener(view.getHeight(), type);
                    }
                }
            }
        });
    }

    public void setAttrName(String getAttr_names) {
        if (getAttr_names != null) {
            Log.e("赋值控件ID", "move_type_text==" + move_type_text.getId());
            if (move_type_text.getImeActionId() > 0) {
                move_type_text.setText(getAttr_names);
            }
        }
    }

    public interface OnItemHeightListener {
        void setOnItemHeightListener(int height, int type);

        void onSeeMoreBtnClick(String goods_id);
    }

    private OnItemHeightListener listener;

    public void setListener(OnItemHeightListener listener) {
        this.listener = listener;
    }
}
