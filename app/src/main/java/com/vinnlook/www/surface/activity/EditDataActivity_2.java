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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.StatusBarUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.http.model.VersionBean;
import com.vinnlook.www.surface.bean.Pickers;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.bean.UserInfo;
import com.vinnlook.www.surface.mvp.presenter.EditDataPresenter;
import com.vinnlook.www.surface.mvp.view.EditDataView;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.PictureSelectorHelper;
import com.vinnlook.www.widgat.EmojiEditText;
import com.vinnlook.www.widgat.PickerScrollView;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditDataActivity_2 extends BaseActivity<EditDataPresenter> implements EditDataView {

    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.edit_genghuan_heat_img)
    LinearLayout editGenghuanHeatImg;
    @BindView(R.id.edit_nicheng_edit)
//    EmojiEditText editNichengEdit;//用户名
    EditText editNichengEdit;//用户名
    @BindView(R.id.edit_name_edit)
    EditText editNameEdit;
    @BindView(R.id.edit_sex_edit)
    TextView editSexEdit;
    @BindView(R.id.edit_heat_img)
    RoundedImageView editHeatImg;
    String getPath = "";
    @BindView(R.id.picker_cancel)
    TextView pickerCancel;
    @BindView(R.id.picker_sure)
    TextView pickerSure;
    @BindView(R.id.pickerscrlllview)
    PickerScrollView pickerscrlllview;
    @BindView(R.id.picker_rel)
    RelativeLayout pickerRel;
    private List<Pickers> list;//性别数据
    private String[] id;
    private String[] name;
    String education = "";//保存性别
    public PopupWindow popupwindow;

    public static void startSelf(Context context) {
        Intent intent = new Intent(context, EditDataActivity_2.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_data_2;
    }

    @Override
    protected EditDataPresenter initPresenter() {
        return new EditDataPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        pickerscrlllview.setOnSelectListener(pickerListener);
        actionBar.getTvRight().setText("保存");
        actionBar.getTvRight().setVisibility(View.VISIBLE);
        actionBar.getTvRight().setTextSize(16);
        actionBar.getTvRight().setTextColor(getResources().getColor(R.color.them));
        actionBar.getTvRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getPath.equals("")) {//图片路径不为空的时候， 先上传头像图片
                    presenter.postUploadPhotos(getPath);
                } else {
                    if (!TextUtils.isEmpty(editNichengEdit.getText().toString().trim())) {
                        presenter.postPersonalInformation("", editNichengEdit.getText().toString().trim(), editSexEdit.getText().toString().trim());

                    } else {
                        Toast.makeText(EditDataActivity_2.this, "请先填写用户名", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        editHeatImg.setScaleType(ImageView.ScaleType.FIT_XY);

    }


    @OnClick({R.id.edit_genghuan_heat_img, R.id.edit_sex_edit, R.id.picker_cancel, R.id.picker_sure, R.id.pickerscrlllview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_genghuan_heat_img://更换头像
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


                            }

                            @Override
                            public void onFailed() {

                            }
                        });
                break;
            case R.id.edit_sex_edit: //选择性别
                initDatas1();
                pickerRel.setVisibility(View.VISIBLE);
                break;
            case R.id.picker_cancel://取消
                pickerRel.setVisibility(View.GONE);
                break;
            case R.id.picker_sure://确定
                editSexEdit.setText(education);
                pickerRel.setVisibility(View.GONE);
                break;
            case R.id.pickerscrlllview:
                break;
        }
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {

        @Override
        public void onSelect(Pickers pickers) {
//            Toast.makeText(SupplementaryInformationActivity.this, "选择：" + pickers.getShowId() + "--银行："
//                    + pickers.getShowConetnt(), Toast.LENGTH_SHORT).show();

            education = pickers.getShowConetnt();
        }
    };


    /**
     * 初始化学历数据
     */
    public void initDatas1() {
        list = new ArrayList<Pickers>();
        id = new String[]{"1", "2", "3"};
        name = new String[]{"保密", "男", "女"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }

    @Override
    protected void loadData() {
        presenter.getUserInfoData();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void getAppUpdateSuccess(int code, VersionBean version) {

    }

    @Override
    public void getAppUpdateFail(int code, String msg) {

    }

    /**
     * @Description:下载数据成功
     * @Time:2020/5/27 12:15
     * @Author:pk
     */
    @Override
    public void getUserInfoSuccess(int code, UserInfo data) {
        ImageLoader.userIcon(this, editHeatImg, data.getImg_url());
        editNichengEdit.setText(data.getUser_name());
        editSexEdit.setText(data.getSex());
        editNameEdit.setText(data.getMobile());
    }

    @Override
    public void getUserInfoFail(int code, String msg) {

    }

    /**
     * @Description:上传图片路径成功
     * @Time:2020/5/27 12:15
     * @Author:pk
     */
    @Override
    public void uploadPhotosSuccess(int code, UpdateImgBean data) {

        String shorImgUrl = data.getShort_img();
        Log.e("uploadPhotosSuccess", "头像上传成功===" + shorImgUrl);
        presenter.postPersonalInformation(shorImgUrl, editNichengEdit.getText().toString().trim(), editSexEdit.getText().toString().trim());

    }

    /**
     * @Description:上传图片路径失败
     * @Time:2020/5/27 12:15
     * @Author:pk
     */
    @Override
    public void uploadPhotosFailed(int code, String msg) {

    }

    /**
     * @Description:修改个人信息成功
     * @Time:2020/5/27 12:15
     * @Author:pk
     */
    @Override
    public void uploadPersonalSuccess(int code, UserInfo data) {
        Log.e("uploadPhotosSuccess", "修改个人信息成功===" + data.getImg_url());
        Log.e("uploadPhotosSuccess", "修改个人信息成功===" + data.getUser_name());
        Log.e("uploadPhotosSuccess", "修改个人信息成功===" + data.getSex());
        Toast.makeText(this, "修改资料成功", Toast.LENGTH_SHORT).show();
        ImageLoader.userIcon(this, editHeatImg, data.getImg_url());
        editNichengEdit.setText(data.getUser_name());
        editSexEdit.setText(data.getSex());
        editNameEdit.setText(data.getMobile());
        finish();
    }

    /**
     * @Description:修改个人信息失败
     * @Time:2020/5/27 12:15
     * @Author:pk
     */
    @Override
    public void uploadPersonalFailed(int code, String msg) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if (code == 4000) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    Log.e("PublishCommentActivity", "==回调照片==" + data);
                    //图片选择结果回调

                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    for (int i = 0; i < selectList.size(); i++) {
                        getPath = selectList.get(i).getPath();

                        ImageLoader.userIcon(EditDataActivity_2.this, editHeatImg, selectList.get(i).getPath());
                    }
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
                PictureSelector.create(EditDataActivity_2.this)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                popupwindow.dismiss();
            }
        });
        //相册
        photo_album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureSelectorHelper.with(getActivity(), PictureConfig.CHOOSE_REQUEST).maxSelectNum(1).selectPhoto();//选择相册，相机
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

}
