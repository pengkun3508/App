package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.vinnlook.www.event.LogisticsEvent;
import com.vinnlook.www.event.PostWaybillEvent;
import com.vinnlook.www.surface.GridImageAdapter_1;
import com.vinnlook.www.surface.PictureSelectorBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.presenter.AddWuLiuDataPresenter;
import com.vinnlook.www.surface.mvp.view.AddWuLiuDataView;
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
 * @Description:填写退货物流
 * @Time:2021/2/4$
 * @Author:pk$
 */
public class AddWuLiuDataActivity extends BaseActivity<AddWuLiuDataPresenter> implements AddWuLiuDataView {


    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.shenqing_logistics_gongsi_name)
    TextView shenqingLogisticsGongsiName;
    @BindView(R.id.shenqing_logistics_gongsi_layout)
    RelativeLayout shenqingLogisticsGongsiLayout;
    @BindView(R.id.shenqing_logistics_sn)
    EditText shenqingLogisticsSn;
    @BindView(R.id.shenqing_logistics_sn_layout)
    LinearLayout shenqingLogisticsSnLayout;
    @BindView(R.id.rclPicture)
    RecyclerView rclPicture;
    @BindView(R.id.submit_btn)
    TextView submitBtn;

    GridImageAdapter_1 adapter;
    public PopupWindow popupwindow;
    int maxSelectNum = 3;

    private List<PictureSelectorBean> pictureSelectorBeanList = new ArrayList<>();
    private List<LocalMedia> selectList=new ArrayList<>();
    private List<String> imgUrlList;
    StringBuilder sb = new StringBuilder();//图片
    String waybillId = "";
    static String iD;
    static String order_id;

    public static void startSelf(Context context, String ids, String order_ids) {
        Intent intent = new Intent(context, AddWuLiuDataActivity.class);
        context.startActivity(intent);
        iD = ids;
        order_id = order_ids;

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_wuliu;
    }

    @Override
    protected AddWuLiuDataPresenter initPresenter() {
        return new AddWuLiuDataPresenter();
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

        //选择物流公司
        shenqingLogisticsGongsiLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectLogisticsGongsiActivity.startSelf(AddWuLiuDataActivity.this);
            }
        });

        //提交物流信息
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("物流ID", "waybillId===" + waybillId);
                Log.e("物流单号", "shenqingLogisticsSn===" + shenqingLogisticsSn.getText().toString());
                Log.e("凭证图片", "selectList.size()===" + selectList.size());
                Log.e("退款ID", "iD===" + iD);
                Log.e("order_id", "order_id===" + order_id);
                if (!waybillId.equals("")) {
                    if (!TextUtils.isEmpty(shenqingLogisticsSn.getText().toString())) {
                        if (selectList == null || selectList.size() <= 0) {
                            //提交接口
                            presenter.postWaybillData(order_id, iD, waybillId, shenqingLogisticsSn.getText().toString(), "");
                        } else {
                            for (int i = 0; i < selectList.size(); i++) {
                                presenter.postUploadPhotos(selectList.get(i).getPath());
                            }
                        }

                    } else {
                        Toast.makeText(AddWuLiuDataActivity.this, "物流单号不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddWuLiuDataActivity.this, "物流公司不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void loadData() {
    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LogisticsEvent event) {
        shenqingLogisticsGongsiName.setText(event.getValue());
        waybillId = event.getId();
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
                PictureSelector.create(AddWuLiuDataActivity.this)
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
            //提交接口
            presenter.postWaybillData(order_id, iD, waybillId, shenqingLogisticsSn.getText().toString(), sb.toString());

        }

    }

    /**
     * 上传图片-失败
     *
     * @param code
     * @param
     */
    @Override
    public void uploadPhotosFailed(int code, String msg) {
        Toast.makeText(AddWuLiuDataActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 提交退款物流信息-成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getWaybillDataSuccess(int code, Object data) {
        new PostWaybillEvent("1").post();
        finish();
    }

    /**
     * 提交退款物流信息-失败
     *
     * @param code
     * @param
     */
    @Override
    public void getWaybillDataFail(int code, String msg) {
        Toast.makeText(AddWuLiuDataActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
