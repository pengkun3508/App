package com.vinnlook.www.surface.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dm.lib.core.adapter.rv.state.BaseHolder;
import com.dm.lib.core.adapter.rv.state.BaseStateAdapter;
import com.vinnlook.www.R;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.utils.ImageLoader;

/**
 * @Description:
 * @Time:2020/11/16$
 * @Author:pk$
 */
public class BatchRefundListAdapter extends BaseStateAdapter<RefundInfoBean.GoodsListBean, BatchRefundListAdapter.BatchRefundHolder> {

    Context context;

    BatchRefundListClickListener batchRefundClickListener;

    public void setBatchRefundListClickListener(BatchRefundListClickListener batchRefundClickListener) {
        this.batchRefundClickListener = batchRefundClickListener;
    }

    public interface BatchRefundListClickListener {
        void onBatchRefundClickListener();//联系客服

    }


    public BatchRefundListAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    protected BatchRefundHolder getViewHolder(@NonNull ViewGroup parent, int holderType) {
        return new BatchRefundHolder(inflate(parent, R.layout.batch_refund_item));
    }

    class BatchRefundHolder extends BaseHolder<RefundInfoBean.GoodsListBean> {
        TextView batch_item_name, batch_item_type, batch_item_price, batch_item_number, batch_item_xiugai_text;
        ImageView batch_item_img;
        EditText batch_item_jine_edit;
        LinearLayout item_layout;


        BatchRefundHolder(View itemView) {
            super(itemView);
            batch_item_img = getView(R.id.batch_item_img);//图片
            batch_item_name = getView(R.id.batch_item_name);//产品名
            batch_item_type = getView(R.id.batch_item_type);//产品型号
            batch_item_price = getView(R.id.batch_item_price);//价格
            batch_item_number = getView(R.id.batch_item_number);//数量
            batch_item_jine_edit = getView(R.id.batch_item_jine_edit);//输入金额
            batch_item_xiugai_text = getView(R.id.batch_item_xiugai_text);//提示可修改金额
            item_layout = getView(R.id.item_layout);

        }

        @Override
        public void bindData(RefundInfoBean.GoodsListBean data) {

            batch_item_img.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(context).load(data.getBrand_logo()).into(tv_img);
            ImageLoader.image(context, batch_item_img, data.getImage());
            batch_item_name.setText(data.getGoods_name());
            batch_item_type.setText(data.getGoods_attr_name());
            batch_item_price.setText(Html.fromHtml("&yen") + data.getGoods_price());
            batch_item_number.setText("x" + data.getGoods_number());
            batch_item_xiugai_text.setText("可修改，最多可退" + Html.fromHtml("&yen") + data.getGoods_price());
            batch_item_jine_edit.setText(data.getGoods_price());
            data.setNew_goods_price(data.getGoods_price());


            //让EditText失去焦点
            item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    batch_item_jine_edit.setFocusable(false);
                    batch_item_jine_edit.setFocusableInTouchMode(false);
                    batch_item_jine_edit.requestFocus();

                }
            });
            batch_item_jine_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    batch_item_jine_edit.setFocusable(true);
                    batch_item_jine_edit.setFocusableInTouchMode(true);
                    batch_item_jine_edit.requestFocus();
                }
            });


            //输入金额
            batch_item_jine_edit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.e("输入的金额", "onTextChanged: ===" + s);

                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            batch_item_jine_edit.setText(s);
                            batch_item_jine_edit.setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        batch_item_jine_edit.setText(s);
                        batch_item_jine_edit.setSelection(2);
                    }

                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            batch_item_jine_edit.setText(s.subSequence(0, 1));
                            batch_item_jine_edit.setSelection(1);
                            return;
                        }
                    }


                    try {
                        if (batch_item_jine_edit.getText().toString().equals("") || batch_item_jine_edit.getText().toString() == null) {
                            data.setNew_goods_price("");
                        } else {
                            if (Float.parseFloat(batch_item_jine_edit.getText().toString()) > Float.parseFloat(data.getGoods_price())) {
                                Toast.makeText(context, "输入的金额不能大于" + data.getGoods_price(), Toast.LENGTH_SHORT).show();
                                batch_item_jine_edit.setText(data.getGoods_price());
                                data.setNew_goods_price(data.getGoods_price());
                            }
                            data.setNew_goods_price(batch_item_jine_edit.getText().toString());

                        }
                    } catch (Exception e) {
//                            // TODO Auto-generated catch block
//                            Toast.makeText(context, "输入的金额不能小于0.01元", Toast.LENGTH_SHORT).show();
//                            batch_item_jine_edit.setText("0.01");
                        data.setNew_goods_price("");
                    }

                    batchRefundClickListener.onBatchRefundClickListener();


                }

                @Override
                public void afterTextChanged(Editable editable) {


                }
            });

            batch_item_jine_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {

                    if (hasFocus) {
                        // 此处为得到焦点时的处理内容
                    } else {
                        // 此处为失去焦点时的处理内容
                        Log.e("onFocusChange", "onFocusChange===" + batch_item_jine_edit.getText().toString());

                        if (batch_item_jine_edit.getText().toString().equals("") || batch_item_jine_edit.getText().toString() == null) {
                            Toast.makeText(context, "输入的金额不能小于0.01", Toast.LENGTH_SHORT).show();
                            batch_item_jine_edit.setText("0.01");
                            data.setNew_goods_price("0.01");
                        } else {
                            if (Float.parseFloat(batch_item_jine_edit.getText().toString()) > Float.parseFloat(data.getGoods_price())) {
                                Toast.makeText(context, "输入的金额不能大于" + data.getGoods_price(), Toast.LENGTH_SHORT).show();
                                batch_item_jine_edit.setText(data.getGoods_price());
                                data.setNew_goods_price(data.getGoods_price());

                            } else if (Float.parseFloat(batch_item_jine_edit.getText().toString()) < 0.01f) {
                                Log.e("输入金额大小", "=====batch_item_jine_edit===" + Float.parseFloat(batch_item_jine_edit.getText().toString()));

                                Toast.makeText(context, "输入的金额不能小于0.01===" + Float.parseFloat(batch_item_jine_edit.getText().toString()), Toast.LENGTH_SHORT).show();

                                batch_item_jine_edit.setText(0.01 + "");
                                data.setNew_goods_price("0.01");
                            }

                        }

                        data.setNew_goods_price(batch_item_jine_edit.getText().toString());
                        batchRefundClickListener.onBatchRefundClickListener();


                    }

                }
            });


        }
    }


}

