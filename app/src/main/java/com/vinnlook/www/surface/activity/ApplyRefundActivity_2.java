package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.StatusBarUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.GridImageAdapter_1;
import com.vinnlook.www.surface.PictureSelectorBean;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.presenter.ApplyRefundPresenter;
import com.vinnlook.www.surface.mvp.view.ApplyRefundView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.FullyGridLayoutManager;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.PictureSelectorHelper;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:单个商品申请退款
 * @Time:2020/11/12$
 * @Author:pk$
 */
public class ApplyRefundActivity_2 extends BaseActivity<ApplyRefundPresenter> implements ApplyRefundView {
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.shouhou_img1)
    RoundedImageView shouhouImg1;
    @BindView(R.id.shouhou_name)
    TextView shouhouName;
    @BindView(R.id.shouhou_type)
    TextView shouhouType;
    @BindView(R.id.shouhou_num)
    TextView shouhouNum;
    @BindView(R.id.shenqing_jine_text)
    EditText shenqingJineText;//返回申请的金额
    @BindView(R.id.shenqing_jine_edit)
    TextView shenqingJineEdit;//输入申请金额
    @BindView(R.id.shenqiang_edit)
    EditText shenqiangEdit;//问题描述
    @BindView(R.id.rclPicture)
    RecyclerView rclPicture;//上传图片

    GridImageAdapter_1 adapter;
    public PopupWindow popupwindow;
    int maxSelectNum = 3;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.shenqing_btn)
    TextView shenqingBtn;
    @BindView(R.id.shenqing_jine_fuhao_text)
    TextView shenqingJineFuhaoText;
    private List<PictureSelectorBean> pictureSelectorBeanList = new ArrayList<>();
    private List<LocalMedia> selectList;
    private List<String> imgUrlList;
    StringBuilder sb = new StringBuilder();

    static OrderDetailsBean.ShopListBean shopListBean;
    static String order_id;
    //    static String rec_id;
    static String type;

    String status = "";//1：已经收到货;2：未收到货；

    float getGoods_price;//价格
    String refund_list;
    RefundInfoBean refundInfoBean;
    static String getIs_refund_all;
    static String getStatus;

    public static void startSelf(Context context, List<OrderDetailsBean.ShopListBean> datas, String order_ids, String types, String getIs_refund_alls, String getStatuss) {
        Intent intent = new Intent(context, ApplyRefundActivity_2.class);
        context.startActivity(intent);
        shopListBean = datas.get(0);
        order_id = order_ids;
        type = types;
        getIs_refund_all = getIs_refund_alls;
        getStatus = getStatuss;
//        rec_id = getRec_id;
        Log.e("startSelf", "rec_id===" + shopListBean);
        Log.e("startSelf", "order_id===" + order_id);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_refund_2;
    }

    @Override
    protected ApplyRefundPresenter initPresenter() {
        return new ApplyRefundPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);


        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        rclPicture.setLayoutManager(manager);
        adapter = new GridImageAdapter_1(this, new GridImageAdapter_1.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                Log.e("PublishCommentActivity", "点击上传照片");
                PermissionHelper.with(getContext()).permissions(Permission.READ_EXTERNAL_STORAGE
                        , Permission.WRITE_EXTERNAL_STORAGE, Permission.RECORD_AUDIO, Permission.CAMERA)
                        .request(new PermissionHelper.PermissionListener() {
                            @Override
                            public void onSuccess() {

                                if (popupwindow != null && popupwindow.isShowing()) {
                                    popupwindow.dismiss();
                                    return;
                                } else {

                                    initmPopupWindowView();
                                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                                }

//                                PictureSelectorHelper.with(getActivity(), PictureConfig.CHOOSE_REQUEST).maxSelectNum(maxSelectNum).selectPhoto();
                            }

                            @Override
                            public void onFailed() {

                            }
                        });
            }

            @Override
            public void onRemoveItem() {
                pictureSelectorBeanList = adapter.getList();
                selectList.clear();
                for (int i = 0; i < pictureSelectorBeanList.size(); i++) {
                    selectList.add(pictureSelectorBeanList.get(i).getLocalMedia());
                }
            }
        });
        adapter.setSelectMax(maxSelectNum);
        rclPicture.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter_1.OnItemClickListener() {
            @Override
            public void onItemClick(int position, PictureSelectorBean pictureSelectorBean, View v) {
                LocalMedia media = pictureSelectorBean.getLocalMedia();
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片 可自定长按保存路径
                        PictureSelector.create(getActivity()).themeStyle(R.style.PictureSelectorNumStyle).openExternalPreview(position, selectList);
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(getActivity()).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频
                        PictureSelector.create(getActivity()).externalPictureAudio(media.getPath());
                        break;
                }
            }

        });


        //输入金额
        shenqingJineText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("输入的金额", "onTextChanged: ===" + s);
                Log.e("输入的金额", "start: ===" + start);
                Log.e("输入的金额", "before: ===" + before);
                Log.e("输入的金额", "count: ===" + count);
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        shenqingJineText.setText(s);
                        shenqingJineText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    shenqingJineText.setText(s);
                    shenqingJineText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        shenqingJineText.setText(s.subSequence(0, 1));
                        shenqingJineText.setSelection(1);
                        return;
                    }
                }

                try {
                    if (shenqingJineText.getText().toString().equals("") || shenqingJineText.getText().toString() == null) {
//                        refundInfoBean.getGoods_list().get(0).setNew_goods_price("");
//                        shenqingJineText.setText("");
                    } else {
                        if (Float.parseFloat(shenqingJineText.getText().toString()) > Float.parseFloat(refundInfoBean.getGoods_list().get(0).getGoods_price())) {
                            Toast.makeText(ApplyRefundActivity_2.this, "输入的金额不能大于" + refundInfoBean.getGoods_list().get(0).getGoods_price(), Toast.LENGTH_SHORT).show();
                            shenqingJineText.setText(refundInfoBean.getGoods_list().get(0).getGoods_price());
//                            refundInfoBean.getGoods_list().get(0).setNew_goods_price(refundInfoBean.getGoods_list().get(0).getGoods_price());
                            shenqingJineText.setText(refundInfoBean.getGoods_list().get(0).getGoods_price());
                        }

                    }
                } catch (Exception e) {
//                            // TODO Auto-generated catch block
//                            Toast.makeText(context, "输入的金额不能小于0.01元", Toast.LENGTH_SHORT).show();
//                            batch_item_jine_edit.setText("0.01");
//                    refundInfoBean.getGoods_list().get(0).setNew_goods_price("");
//                    shenqingJineText.setText("");
                }

//                float priceFloats = 0;
//                for (int i = 0; i <   refundInfoBean.getGoods_list().size(); i++) {
//                    if (! refundInfoBean.getGoods_list().get(i).getNew_goods_price().equals("") &&  refundInfoBean.getGoods_list().get(i).getNew_goods_price() != null) {
//                        float priceFloat = Float.parseFloat( refundInfoBean.getGoods_list().get(i).getNew_goods_price());
//                        Log.e("batchadapter", "priceFloat===1111===" + priceFloat);
//                        priceFloats = priceFloats + priceFloat;
//                        Log.e("batchadapter", "priceFloats===2222===" + priceFloats);
//                    }
//                }
//                shenqingJineText.setText(String.format("%.2f", priceFloats));


            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        shenqingJineText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    Log.e("onFocusChange", "onFocusChange===" + shenqingJineText.getText().toString());

                    if (shenqingJineText.getText().toString().equals("") || shenqingJineText.getText().toString() == null) {
                        Toast.makeText(ApplyRefundActivity_2.this, "输入的金额不能小于0.01", Toast.LENGTH_SHORT).show();
                        shenqingJineText.setText("0.01");
//                        refundInfoBean.getGoods_list().get(0).setNew_goods_price("0.01");
                    } else {
                        if (Float.parseFloat(shenqingJineText.getText().toString()) > Float.parseFloat(refundInfoBean.getGoods_list().get(0).getGoods_price())) {
                            Toast.makeText(ApplyRefundActivity_2.this, "输入的金额不能大于" + refundInfoBean.getGoods_list().get(0).getGoods_price(), Toast.LENGTH_SHORT).show();
                            shenqingJineText.setText(refundInfoBean.getGoods_list().get(0).getGoods_price());
//                            refundInfoBean.getGoods_list().get(0).setNew_goods_price(refundInfoBean.getGoods_list().get(0).getGoods_price());

                        } else if (Float.parseFloat(shenqingJineText.getText().toString()) < 0.01f) {
                            Log.e("输入金额大小", "=====batch_item_jine_edit===" + Float.parseFloat(shenqingJineText.getText().toString()));

                            Toast.makeText(ApplyRefundActivity_2.this, "输入的金额不能小于0.01===" + Float.parseFloat(shenqingJineText.getText().toString()), Toast.LENGTH_SHORT).show();

                            shenqingJineText.setText(0.01 + "");
//                            refundInfoBean.getGoods_list().get(0).setNew_goods_price("0.01");
                        }

                    }

//                    refundInfoBean.getGoods_list().get(0).setNew_goods_price(shenqingJineText.getText().toString());
//
//                    float priceFloats = 0;
//                    for (int i = 0; i <   refundInfoBean.getGoods_list().size(); i++) {
//                        if (! refundInfoBean.getGoods_list().get(i).getNew_goods_price().equals("") &&  refundInfoBean.getGoods_list().get(i).getNew_goods_price() != null) {
//                            float priceFloat = Float.parseFloat( refundInfoBean.getGoods_list().get(i).getNew_goods_price());
//                            Log.e("batchadapter", "priceFloat===1111===" + priceFloat);
//                            priceFloats = priceFloats + priceFloat;
//                            Log.e("batchadapter", "priceFloats===2222===" + priceFloats);
//                        }
//                    }
//                    shenqingJineText.setText(String.format("%.2f", priceFloats));


                }

            }
        });


        //问题描述
        shenqiangEdit.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                textView1.setText(String.valueOf(editable.length()) + "/300");
                if (editable.length() >= 300) {
                    Toast.makeText(ApplyRefundActivity_2.this, "上限了，亲", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //申请退款
        shenqingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refund_list = shopListBean.getRec_id() + ":" + shenqingJineText.getText().toString();
                if (!TextUtils.isEmpty(shenqingJineText.getText().toString())) {
                    if (Float.parseFloat(shenqingJineText.getText().toString()) > 0) {
                        if (getGoods_price >= Float.parseFloat(shenqingJineText.getText().toString())) {
                            if (!shenqiangEdit.getText().toString().equals("")) {
                                if (selectList == null || selectList.size() <= 0) {
                                    Log.e("申请退款", "order_id===" + order_id);
                                    Log.e("申请退款", "refund_list===" + refund_list);
                                    Log.e("申请退款", "type===" + type);
                                    Log.e("申请退款", "status===" + status);
                                    Log.e("申请退款", "shenqiangEdit===" + shenqiangEdit.getText().toString());
                                    Log.e("申请退款", "sb===" + sb.toString());
                                    presenter.getAddRefundApply(order_id, refund_list, type, status, shenqiangEdit.getText().toString(), sb.toString(), getIs_refund_all);
                                } else {
                                    for (int i = 0; i < selectList.size(); i++) {
                                        presenter.postUploadPhotos(selectList.get(i).getPath());
                                    }
                                }
                            } else {
                                Toast.makeText(ApplyRefundActivity_2.this, "问题描述不能为空", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ApplyRefundActivity_2.this, "输入的金额不能大于最大退额", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ApplyRefundActivity_2.this, "金额不能为0", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ApplyRefundActivity_2.this, "金额不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void loadData() {
        presenter.getRefundInfo(order_id, shopListBean.getRec_id());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    Log.e("PublishCommentActivity", "==回调照片==" + selectList);
                    if (selectList.size() > 0) {
                        pictureSelectorBeanList.clear();
                    }
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (int i = 0; i < selectList.size(); i++) {
                        Log.i("图片-----》", selectList.get(i).getPath());
                        Log.e("PublishCommentActivity", "图片-----》" + selectList);
                        PictureSelectorBean pictureSelectorBean = new PictureSelectorBean();
                        pictureSelectorBean.setLocalMedia(selectList.get(i));
                        pictureSelectorBean.setUpDataType(i % 3);
                        pictureSelectorBeanList.add(pictureSelectorBean);
                    }
                    imgUrlList = new ArrayList<>();
                    adapter.setList(pictureSelectorBeanList);
                    adapter.notifyDataSetChanged();


                    break;
            }
        }
    }


    /**
     * 拍照-相册-弹框
     * getFeeMsg1,getMobile1, getFeeMsg2,getMobile2
     */
    public void initmPopupWindowView() {

        TextView photo_camera_btn, photo_album_btn, photo_cancel_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.photo_layout, null, false);
        photo_camera_btn = customView.findViewById(R.id.photo_camera_btn);//拍照
        photo_album_btn = customView.findViewById(R.id.photo_album_btn);//相册
        photo_cancel_btn = customView.findViewById(R.id.photo_cancel_btn);//取消


        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                return false;
            }
        });

        //拍照
        photo_camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelector.create(ApplyRefundActivity_2.this)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                popupwindow.dismiss();
            }
        });
        //相册
        photo_album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelectorHelper.with(getActivity(), PictureConfig.CHOOSE_REQUEST).maxSelectNum(maxSelectNum).selectPhoto();//选择相册，相机
                popupwindow.dismiss();
            }
        });
        //取消
        photo_cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
            }
        });


    }

    /**
     * @Description: 上传图片成功
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void uploadPhotosSuccess(int code, UpdateImgBean data) {
        sb.append(data.getShort_img() + ",");
        imgUrlList.add(sb.toString());
        Log.e("PublishCommentActivity", "==上传图片成功==imgUrlList.size===" + imgUrlList.size());
        Log.e("PublishCommentActivity", "==上传图片成功==sb===" + sb.toString());
        if (imgUrlList.size() == selectList.size()) {

            //订单ID==order_id
            //子订单ID===data.getRec_id();退款金额==shenqingJineEdit。getText（）
            //输入的退款金额shenqingJineEdit.getText().toString();
            //type==1,仅退款;type==2,退货退款;
            //退款原因 shenqiangEdit.getText().toString();
            //status//1：已经收到货;2：未收到货；

            Log.e("提交资料", "order_id===" + order_id);
            Log.e("提交资料", "refund_list===" + refund_list);
            Log.e("提交资料", "type===" + type);
            Log.e("提交资料", "status===" + status);
            Log.e("提交资料", "shenqiangEdit===" + shenqiangEdit.getText().toString());
            Log.e("提交资料", "sb===" + sb.toString());

            presenter.getAddRefundApply(order_id, refund_list, type, status, shenqiangEdit.getText().toString(), sb.toString(), getIs_refund_all);

        }

    }

    /**
     * @Description: 上传图片失败
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void uploadPhotosFailed(int code, String msg) {

    }

    /**
     * @Description: 提交退款申请--成功
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void getAddRefundApplySuccess(int code, Object data) {
//        Toast.makeText(this, "申请退款成功", Toast.LENGTH_SHORT).show();
//        ApplyRefundListActivity.startSelf(this);//进入列表页面
//        CacheActivity.finishActivity();

        if (popupwindow != null && popupwindow.isShowing()) {
            popupwindow.dismiss();
            return;
        } else {
            initmPopupWindowView_2();
            popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }


    }

    /**
     * @Description:提交成功
     * @Time:2020/11/12 20:08
     * @Author:pk
     */
    private void initmPopupWindowView_2() {
        TextView i_know_text;
        // // 获取自定义布局文件pop.xml的视图
        View customView1 = getLayoutInflater().inflate(R.layout.i_know_layout, null, false);
        i_know_text = customView1.findViewById(R.id.i_know_text);//我知道了


        // 创建PopupWindow实例,先宽度，后高度
        popupwindow = new PopupWindow(customView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // 自定义view添加触摸事件
//        customView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (popupwindow != null && popupwindow.isShowing()) {
//                    popupwindow.dismiss();
//                    popupwindow = null;
//                }
//                return false;
//            }
//        });

        //我知道了
        i_know_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                ApplyRefundListActivity.startSelf(ApplyRefundActivity_2.this);//进入列表页面
                CacheActivity.finishActivity();
            }
        });
    }

    /**
     * @Description: 提交退款申请--失败
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void getAddRefundApplyFail(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 下载退款详情--成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getRefundInfoSuccess(int code, RefundInfoBean data) {

        refundInfoBean = data;
        Matrix matrix = new Matrix();           //创建一个单位矩阵
        matrix.setTranslate(0, 0);          //平移x和y各100单位
        matrix.preRotate(0);                   //顺时针旋转30度
        shouhouImg1.setScaleType(ImageView.ScaleType.MATRIX);
        shouhouImg1.setImageMatrix(matrix);
        ImageLoader.image(this, shouhouImg1, data.getGoods_list().get(0).getImage());

        shouhouName.setText(data.getGoods_list().get(0).getGoods_name());
        shouhouType.setText(data.getGoods_list().get(0).getGoods_attr_name());
        shouhouNum.setText("x" + data.getGoods_list().get(0).getGoods_number());
        shenqingJineFuhaoText.setText(Html.fromHtml("&yen"));
        shenqingJineText.setText(data.getGoods_list().get(0).getGoods_price());
        getGoods_price = Float.parseFloat(data.getGoods_list().get(0).getGoods_price());


        if (getStatus.equals("2")) {//待发货
            shenqingJineText.setFocusable(false);
            shenqingJineText.setFocusableInTouchMode(false);
            shenqingJineEdit.setHint("不可修改，最多可退" + Html.fromHtml("&yen") + data.getGoods_list().get(0).getGoods_price() + "元，(包含运费:" + data.getPost_price() + "元)");
        } else {
            shenqingJineText.setFocusable(true);
            shenqingJineText.setFocusableInTouchMode(true);
            shenqingJineEdit.setHint("可修改，最多可退" + Html.fromHtml("&yen") + data.getGoods_list().get(0).getGoods_price() + "元，(包含运费:" + data.getPost_price() + "元)");
        }
//        data.getGoods_list().get(0).setNew_goods_price(data.getGoods_list().get(0).getGoods_price());


    }

    /**
     * 下载退款详情--失败
     *
     * @param code
     * @param
     */
    @Override
    public void getRefundInfoFail(int code, String msg) {

    }
}
