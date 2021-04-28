package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.event.BatchRefundEvent;
import com.vinnlook.www.http.model.OrderDetailsBean;
import com.vinnlook.www.surface.GridImageAdapter_1;
import com.vinnlook.www.surface.PictureSelectorBean;
import com.vinnlook.www.surface.adapter.RefundSelectApply_Adapter;
import com.vinnlook.www.surface.bean.RefundInfoBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.presenter.ApplyRefundPresenter;
import com.vinnlook.www.surface.mvp.view.ApplyRefundView;
import com.vinnlook.www.utils.CacheActivity;
import com.vinnlook.www.utils.FullyGridLayoutManager;
import com.vinnlook.www.utils.PictureSelectorHelper;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;
import com.yanzhenjie.permission.Permission;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:多个商品申请退款
 * @Time:2020/11/13$
 * @Author:pk$
 */
public class ApplyRefundSelectActivity_1 extends BaseActivity<ApplyRefundPresenter> implements ApplyRefundView {

    static List<OrderDetailsBean.ShopListBean> shopListBean;
    static String order_id;
    static String type;
    static String getIs_refund_all;
    static String getStatus;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.select_recyclerv)
    RecyclerView selectRecyclerv;
    @BindView(R.id.shenqing_huowu_zhuangtai_text)
    TextView shenqingHuowuZhuangtaiText;//状态
    @BindView(R.id.shenqing_huowu_zhuangtai_layout)
    RelativeLayout shenqingHuowuZhuangtaiLayout;//选择状态
    @BindView(R.id.shenqing_jine_text)
    TextView shenqingJineText;//填写金额
    @BindView(R.id.shenqiang_edit)
    EditText shenqiangEdit;//问题描述
    @BindView(R.id.textView1)
    TextView textView1;//字数
    @BindView(R.id.rclPicture)
    RecyclerView rclPicture;//上传图片
    @BindView(R.id.shenqing_btn)
    TextView shenqingBtn;//提交
    @BindView(R.id.shenqing_jine_edit)
    TextView shenqingJineEdit;
    @BindView(R.id.shenqing_jine_layout_btn)
    LinearLayout shenqingJineLayoutBtn;

    static StringBuilder sbRec_id = new StringBuilder();

    RefundSelectApply_Adapter applyAdapter;
    GridImageAdapter_1 adapter;
    public PopupWindow popupwindow;

    private List<PictureSelectorBean> pictureSelectorBeanList = new ArrayList<>();
    private List<LocalMedia> selectList;
    private List<String> imgUrlList;
    int maxSelectNum = 3;
    StringBuilder sb = new StringBuilder();//图片
    StringBuilder sbRec_pric = new StringBuilder();//rec_id与price拼接的字符串
    String status = "";//1：已经收到货;2：未收到货；

    RefundInfoBean refundInfoBean;


    public static void startSelf(Context context, List<OrderDetailsBean.ShopListBean> datas, String order_ids, String types, String getIs_refund_alls, String getStatuss) {
        Intent intent = new Intent(context, ApplyRefundSelectActivity_1.class);
        context.startActivity(intent);
        shopListBean = datas;
        order_id = order_ids;
        type = types;
        getIs_refund_all = getIs_refund_alls;
        getStatus = getStatuss;
        Log.e("startSelf", "datas===" + datas.size());
        sbRec_id = new StringBuilder();
        for (int i = 0; i < datas.size(); i++) {
            sbRec_id.append(datas.get(i).getRec_id() + ",");
        }
        Log.e("startSelf", "拼接字符串===" + sbRec_id);
        Log.e("startSelf", "order_id===" + order_id);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_refund_select_1;
    }

    @Override
    protected ApplyRefundPresenter initPresenter() {
        return new ApplyRefundPresenter();
    }

    @Override

    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        CacheActivity.addActivity(this);
        actionBar.getTvTitle().setText("申请退款");
        applyAdapter = new RefundSelectApply_Adapter(this);
        final GridLayoutManager manager2 = new GridLayoutManager(this, 1);
        manager2.setOrientation(GridLayoutManager.HORIZONTAL);
        selectRecyclerv.setLayoutManager(manager2);
        selectRecyclerv.setAdapter(applyAdapter);

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
                    Toast.makeText(ApplyRefundSelectActivity_1.this, "上限了，亲", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if (getStatus.equals("2")) {//待发货
            shenqingHuowuZhuangtaiText.setText("未收到货");

        }

        //选择货物状态
        shenqingHuowuZhuangtaiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {

                    initmPopupWindowView_1();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }


            }
        });

        //选择退款金额
        shenqingJineLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BatchRefundAmountSelectionActivity.startSelf(ApplyRefundSelectActivity_1.this, refundInfoBean);


            }
        });

        //申请退款
        shenqingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < refundInfoBean.getGoods_list().size(); i++) {
                    sbRec_pric.append(refundInfoBean.getGoods_list().get(i).getRec_id() + ":");
                    sbRec_pric.append(refundInfoBean.getGoods_list().get(i).getGoods_price() + ",");

                }
                Log.e("申请退款", "拼接的REC与PRIC===" + sbRec_pric.toString());
                if (!TextUtils.isEmpty(shenqingJineText.getText().toString())) {
                    if (Float.parseFloat(shenqingJineText.getText().toString()) > 0) {
                        if (!status.equals("")) {
                            if (!shenqiangEdit.getText().toString().equals("")) {
                                if (selectList == null || selectList.size() <= 0) {
                                    Log.e("申请退款", "order_id===" + order_id);
                                    Log.e("申请退款", "sbRec_pric===" + sbRec_pric.toString());
                                    Log.e("申请退款", "type===" + type);
                                    Log.e("申请退款", "status===" + status);
                                    Log.e("申请退款", "shenqiangEdit===" + shenqiangEdit.getText().toString());
                                    Log.e("申请退款", "sb===" + sb.toString());
                                    presenter.getAddRefundApply(order_id, sbRec_pric.toString(), type, status, shenqiangEdit.getText().toString(), sb.toString(), getIs_refund_all);
                                } else {
                                    for (int i = 0; i < selectList.size(); i++) {
                                        presenter.postUploadPhotos(selectList.get(i).getPath());
                                    }
                                }
                            } else {
                                Toast.makeText(ApplyRefundSelectActivity_1.this, "问题描述不能为空", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ApplyRefundSelectActivity_1.this, "货物状态不能为空", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(ApplyRefundSelectActivity_1.this, "金额不能为0", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ApplyRefundSelectActivity_1.this, "金额不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BatchRefundEvent event) {
        Log.e("接收消息", "BatchRefundEvent==" + event.getPrice());
        shenqingJineText.setText(event.getPrice());
    }

    @Override
    protected void loadData() {
        presenter.getRefundInfo(order_id, sbRec_id.toString());
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
            Log.e("提交资料", "sbRec_pric===" + sbRec_pric.toString());
            Log.e("提交资料", "type===" + type);
            Log.e("提交资料", "status===" + status);
            Log.e("提交资料", "shenqiangEdit===" + shenqiangEdit.getText().toString());
            Log.e("提交资料", "sb===" + sb.toString());

            presenter.getAddRefundApply(order_id, sbRec_pric.toString(), type, status, shenqiangEdit.getText().toString(), sb.toString(), getIs_refund_all);

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


        if (popupwindow != null && popupwindow.isShowing()) {
            popupwindow.dismiss();
            return;
        } else {
            initmPopupWindowView_2();
            popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }


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
     * 下载退款详情--成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getRefundInfoSuccess(int code, RefundInfoBean data) {

        refundInfoBean = data;
        shenqingJineText.setText(data.getPrice());


        if (getStatus.equals("2")) {//待发货
            shenqingJineText.setFocusable(false);
            shenqingJineText.setFocusableInTouchMode(false);
            shenqingJineEdit.setHint("不可修改，最多可退" + Html.fromHtml("&yen") + data.getPrice() + "元，(包含运费:" + data.getPost_price() + "元)");
        } else {
            shenqingJineText.setFocusable(true);
            shenqingJineText.setFocusableInTouchMode(true);
            shenqingJineEdit.setHint("可修改，最多可退" + Html.fromHtml("&yen") + data.getPrice() + "元，(包含运费:" + data.getPost_price() + "元)");
        }
        applyAdapter.setData(data.getGoods_list());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
                PictureSelector.create(ApplyRefundSelectActivity_1.this)
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
     * @Description:选择货物状态
     * @Time:2020/11/12 20:08
     * @Author:pk
     */
    private void initmPopupWindowView_1() {
        RelativeLayout huowu_zhuangtai_xuanze_closs, yes_huowu_layout;
        LinearLayout huowu_zhuangtai_no, huowu_zhuangtai_yes;
        ImageView huowu_zhuangtai_no_img, huowu_zhuangtai_yes_img;
        TextView huowu_zhuangtai_btn;
        View waill_line;
        // // 获取自定义布局文件pop.xml的视图
        View customView1 = getLayoutInflater().inflate(R.layout.huowu_layout, null, false);
        huowu_zhuangtai_xuanze_closs = customView1.findViewById(R.id.huowu_zhuangtai_xuanze_closs);//关闭
        huowu_zhuangtai_no = customView1.findViewById(R.id.huowu_zhuangtai_no);//未收到货物btn
        huowu_zhuangtai_no_img = customView1.findViewById(R.id.huowu_zhuangtai_no_img);//未收到货物图标
        huowu_zhuangtai_yes = customView1.findViewById(R.id.huowu_zhuangtai_yes);//已收到货物btn
        huowu_zhuangtai_yes_img = customView1.findViewById(R.id.huowu_zhuangtai_yes_img);//已收到货物图标
        huowu_zhuangtai_btn = customView1.findViewById(R.id.huowu_zhuangtai_btn);//确定
        waill_line = customView1.findViewById(R.id.waill_line);
        yes_huowu_layout = customView1.findViewById(R.id.yes_huowu_layout);


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

        if (getStatus.equals("2")) {//待发货
            waill_line.setVisibility(View.GONE);
            yes_huowu_layout.setVisibility(View.GONE);
        }
        //关闭
        huowu_zhuangtai_xuanze_closs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }

            }
        });
        //选择未收到
        huowu_zhuangtai_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                huowu_zhuangtai_no_img.setBackground(getResources().getDrawable(R.mipmap.apply_quan_yes));
                huowu_zhuangtai_yes_img.setBackground(getResources().getDrawable(R.mipmap.apply_quan_no));
                status = "2";


            }
        });
        //选择已收到
        huowu_zhuangtai_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                huowu_zhuangtai_no_img.setBackground(getResources().getDrawable(R.mipmap.apply_quan_no));
                huowu_zhuangtai_yes_img.setBackground(getResources().getDrawable(R.mipmap.apply_quan_yes));
                status = "1";

            }
        });
        //确定
        huowu_zhuangtai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                if (status.equals("1")) {
                    shenqingHuowuZhuangtaiText.setText("已收到货");
                } else if (status.equals("2")) {
                    shenqingHuowuZhuangtaiText.setText("未收到货");
                }

            }
        });
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

                ApplyRefundListActivity.startSelf(ApplyRefundSelectActivity_1.this);//进入列表页面
                CacheActivity.finishActivity();
            }
        });
    }

}
