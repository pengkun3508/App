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
 * @Description:????????????????????????
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
    TextView shenqingHuowuZhuangtaiText;//??????
    @BindView(R.id.shenqing_huowu_zhuangtai_layout)
    RelativeLayout shenqingHuowuZhuangtaiLayout;//????????????
    @BindView(R.id.shenqing_jine_text)
    TextView shenqingJineText;//????????????
    @BindView(R.id.shenqiang_edit)
    EditText shenqiangEdit;//????????????
    @BindView(R.id.textView1)
    TextView textView1;//??????
    @BindView(R.id.rclPicture)
    RecyclerView rclPicture;//????????????
    @BindView(R.id.shenqing_btn)
    TextView shenqingBtn;//??????
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
    StringBuilder sb = new StringBuilder();//??????
    StringBuilder sbRec_pric = new StringBuilder();//rec_id???price??????????????????
    String status = "";//1??????????????????;2??????????????????

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
        Log.e("startSelf", "???????????????===" + sbRec_id);
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
        actionBar.getTvTitle().setText("????????????");
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
                Log.e("PublishCommentActivity", "??????????????????");
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
                        // ???????????? ???????????????????????????
                        PictureSelector.create(getActivity()).themeStyle(R.style.PictureSelectorNumStyle).openExternalPreview(position, selectList);
                        break;
                    case 2:
                        // ????????????
                        PictureSelector.create(getActivity()).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // ????????????
                        PictureSelector.create(getActivity()).externalPictureAudio(media.getPath());
                        break;
                }
            }

        });


        //????????????
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
                    Toast.makeText(ApplyRefundSelectActivity_1.this, "???????????????", Toast.LENGTH_SHORT).show();
                }
            }
        });


        if (getStatus.equals("2")) {//?????????
            shenqingHuowuZhuangtaiText.setText("????????????");

        }

        //??????????????????
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

        //??????????????????
        shenqingJineLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BatchRefundAmountSelectionActivity.startSelf(ApplyRefundSelectActivity_1.this, refundInfoBean);


            }
        });

        //????????????
        shenqingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < refundInfoBean.getGoods_list().size(); i++) {
                    sbRec_pric.append(refundInfoBean.getGoods_list().get(i).getRec_id() + ":");
                    sbRec_pric.append(refundInfoBean.getGoods_list().get(i).getGoods_price() + ",");

                }
                Log.e("????????????", "?????????REC???PRIC===" + sbRec_pric.toString());
                if (!TextUtils.isEmpty(shenqingJineText.getText().toString())) {
                    if (Float.parseFloat(shenqingJineText.getText().toString()) > 0) {
                        if (!status.equals("")) {
                            if (!shenqiangEdit.getText().toString().equals("")) {
                                if (selectList == null || selectList.size() <= 0) {
                                    Log.e("????????????", "order_id===" + order_id);
                                    Log.e("????????????", "sbRec_pric===" + sbRec_pric.toString());
                                    Log.e("????????????", "type===" + type);
                                    Log.e("????????????", "status===" + status);
                                    Log.e("????????????", "shenqiangEdit===" + shenqiangEdit.getText().toString());
                                    Log.e("????????????", "sb===" + sb.toString());
                                    presenter.getAddRefundApply(order_id, sbRec_pric.toString(), type, status, shenqiangEdit.getText().toString(), sb.toString(), getIs_refund_all);
                                } else {
                                    for (int i = 0; i < selectList.size(); i++) {
                                        presenter.postUploadPhotos(selectList.get(i).getPath());
                                    }
                                }
                            } else {
                                Toast.makeText(ApplyRefundSelectActivity_1.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ApplyRefundSelectActivity_1.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(ApplyRefundSelectActivity_1.this, "???????????????0", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ApplyRefundSelectActivity_1.this, "??????????????????", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    //????????????
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BatchRefundEvent event) {
        Log.e("????????????", "BatchRefundEvent==" + event.getPrice());
        shenqingJineText.setText(event.getPrice());
    }

    @Override
    protected void loadData() {
        presenter.getRefundInfo(order_id, sbRec_id.toString());
    }

    /**
     * @Description: ??????????????????
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void uploadPhotosSuccess(int code, UpdateImgBean data) {
        sb.append(data.getShort_img() + ",");
        imgUrlList.add(sb.toString());
        Log.e("PublishCommentActivity", "==??????????????????==imgUrlList.size===" + imgUrlList.size());
        Log.e("PublishCommentActivity", "==??????????????????==sb===" + sb.toString());
        if (imgUrlList.size() == selectList.size()) {

            //??????ID==order_id
            //?????????ID===data.getRec_id();????????????==shenqingJineEdit???getText??????
            //?????????????????????shenqingJineEdit.getText().toString();
            //type==1,?????????;type==2,????????????;
            //???????????? shenqiangEdit.getText().toString();
            //status//1??????????????????;2??????????????????

            Log.e("????????????", "order_id===" + order_id);
            Log.e("????????????", "sbRec_pric===" + sbRec_pric.toString());
            Log.e("????????????", "type===" + type);
            Log.e("????????????", "status===" + status);
            Log.e("????????????", "shenqiangEdit===" + shenqiangEdit.getText().toString());
            Log.e("????????????", "sb===" + sb.toString());

            presenter.getAddRefundApply(order_id, sbRec_pric.toString(), type, status, shenqiangEdit.getText().toString(), sb.toString(), getIs_refund_all);

        }

    }

    /**
     * @Description: ??????????????????
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void uploadPhotosFailed(int code, String msg) {

    }

    /**
     * @Description: ??????????????????--??????
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
     * @Description: ??????????????????--??????
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
                    // ????????????????????????
                    selectList = PictureSelector.obtainMultipleResult(data);
                    Log.e("PublishCommentActivity", "==????????????==" + selectList);
                    if (selectList.size() > 0) {
                        pictureSelectorBeanList.clear();
                    }
                    // ?????? LocalMedia ??????????????????path
                    // 1.media.getPath(); ?????????path
                    // 2.media.getCutPath();????????????path????????????media.isCut();?????????true
                    // 3.media.getCompressPath();????????????path????????????media.isCompressed();?????????true
                    // ????????????????????????????????????????????????????????????????????????????????????
                    for (int i = 0; i < selectList.size(); i++) {
                        Log.i("??????-----???", selectList.get(i).getPath());
                        Log.e("PublishCommentActivity", "??????-----???" + selectList);
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
     * ??????????????????--??????
     *
     * @param code
     * @param data
     */
    @Override
    public void getRefundInfoSuccess(int code, RefundInfoBean data) {

        refundInfoBean = data;
        shenqingJineText.setText(data.getPrice());


        if (getStatus.equals("2")) {//?????????
            shenqingJineText.setFocusable(false);
            shenqingJineText.setFocusableInTouchMode(false);
            shenqingJineEdit.setHint("???????????????????????????" + Html.fromHtml("&yen") + data.getPrice() + "??????(????????????:" + data.getPost_price() + "???)");
        } else {
            shenqingJineText.setFocusable(true);
            shenqingJineText.setFocusableInTouchMode(true);
            shenqingJineEdit.setHint("????????????????????????" + Html.fromHtml("&yen") + data.getPrice() + "??????(????????????:" + data.getPost_price() + "???)");
        }
        applyAdapter.setData(data.getGoods_list());
    }

    /**
     * ??????????????????--??????
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
     * ??????-??????-??????
     * getFeeMsg1,getMobile1, getFeeMsg2,getMobile2
     */
    public void initmPopupWindowView() {

        TextView photo_camera_btn, photo_album_btn, photo_cancel_btn;
        // // ???????????????????????????pop.xml?????????
        View customView = getLayoutInflater().inflate(R.layout.photo_layout, null, false);
        photo_camera_btn = customView.findViewById(R.id.photo_camera_btn);//??????
        photo_album_btn = customView.findViewById(R.id.photo_album_btn);//??????
        photo_cancel_btn = customView.findViewById(R.id.photo_cancel_btn);//??????


        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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

        //??????
        photo_camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelector.create(ApplyRefundSelectActivity_1.this)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                popupwindow.dismiss();
            }
        });
        //??????
        photo_album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelectorHelper.with(getActivity(), PictureConfig.CHOOSE_REQUEST).maxSelectNum(maxSelectNum).selectPhoto();//?????????????????????
                popupwindow.dismiss();
            }
        });
        //??????
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
     * @Description:??????????????????
     * @Time:2020/11/12 20:08
     * @Author:pk
     */
    private void initmPopupWindowView_1() {
        RelativeLayout huowu_zhuangtai_xuanze_closs, yes_huowu_layout;
        LinearLayout huowu_zhuangtai_no, huowu_zhuangtai_yes;
        ImageView huowu_zhuangtai_no_img, huowu_zhuangtai_yes_img;
        TextView huowu_zhuangtai_btn;
        View waill_line;
        // // ???????????????????????????pop.xml?????????
        View customView1 = getLayoutInflater().inflate(R.layout.huowu_layout, null, false);
        huowu_zhuangtai_xuanze_closs = customView1.findViewById(R.id.huowu_zhuangtai_xuanze_closs);//??????
        huowu_zhuangtai_no = customView1.findViewById(R.id.huowu_zhuangtai_no);//???????????????btn
        huowu_zhuangtai_no_img = customView1.findViewById(R.id.huowu_zhuangtai_no_img);//?????????????????????
        huowu_zhuangtai_yes = customView1.findViewById(R.id.huowu_zhuangtai_yes);//???????????????btn
        huowu_zhuangtai_yes_img = customView1.findViewById(R.id.huowu_zhuangtai_yes_img);//?????????????????????
        huowu_zhuangtai_btn = customView1.findViewById(R.id.huowu_zhuangtai_btn);//??????
        waill_line = customView1.findViewById(R.id.waill_line);
        yes_huowu_layout = customView1.findViewById(R.id.yes_huowu_layout);


        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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

        if (getStatus.equals("2")) {//?????????
            waill_line.setVisibility(View.GONE);
            yes_huowu_layout.setVisibility(View.GONE);
        }
        //??????
        huowu_zhuangtai_xuanze_closs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }

            }
        });
        //???????????????
        huowu_zhuangtai_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                huowu_zhuangtai_no_img.setBackground(getResources().getDrawable(R.mipmap.apply_quan_yes));
                huowu_zhuangtai_yes_img.setBackground(getResources().getDrawable(R.mipmap.apply_quan_no));
                status = "2";


            }
        });
        //???????????????
        huowu_zhuangtai_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                huowu_zhuangtai_no_img.setBackground(getResources().getDrawable(R.mipmap.apply_quan_no));
                huowu_zhuangtai_yes_img.setBackground(getResources().getDrawable(R.mipmap.apply_quan_yes));
                status = "1";

            }
        });
        //??????
        huowu_zhuangtai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                if (status.equals("1")) {
                    shenqingHuowuZhuangtaiText.setText("????????????");
                } else if (status.equals("2")) {
                    shenqingHuowuZhuangtaiText.setText("????????????");
                }

            }
        });
    }


    /**
     * @Description:????????????
     * @Time:2020/11/12 20:08
     * @Author:pk
     */
    private void initmPopupWindowView_2() {
        TextView i_know_text;
        // // ???????????????????????????pop.xml?????????
        View customView1 = getLayoutInflater().inflate(R.layout.i_know_layout, null, false);
        i_know_text = customView1.findViewById(R.id.i_know_text);//????????????


        // ??????PopupWindow??????,?????????????????????
        popupwindow = new PopupWindow(customView1, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // ?????????????????? [R.style.AnimationFade ???????????????????????????]
//        popupwindow.setAnimationStyle(R.style.AnimationFade);
        // ?????????view??????????????????
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

        //????????????
        i_know_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }

                ApplyRefundListActivity.startSelf(ApplyRefundSelectActivity_1.this);//??????????????????
                CacheActivity.finishActivity();
            }
        });
    }

}
