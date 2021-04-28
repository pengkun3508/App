package com.vinnlook.www.surface.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.GridImageAdapter;
import com.vinnlook.www.surface.PictureSelectorBean;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.presenter.ProblemFeedbackPresenter;
import com.vinnlook.www.surface.mvp.view.ProblemFeedbackView;
import com.vinnlook.www.utils.FullyGridLayoutManager;
import com.vinnlook.www.utils.PictureSelectorHelper;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:问题反馈
 * @Time:2020/4/3$
 * @Author:pk$
 */
public class ProblemFeedbackActivity extends BaseActivity<ProblemFeedbackPresenter> implements ProblemFeedbackView {

    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.add_ok)
    RoundTextView addOk;
    @BindView(R.id.rclPicture)
    RecyclerView rclPicture;

    GridImageAdapter adapter;
    public PopupWindow popupwindow;
    private List<PictureSelectorBean> pictureSelectorBeanList = new ArrayList<>();
    private List<LocalMedia> selectList;
    private List<String> imgUrlList;
    int maxSelectNum = 6;
    StringBuilder sb = new StringBuilder();


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, ProblemFeedbackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected ProblemFeedbackPresenter initPresenter() {
        return new ProblemFeedbackPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_problem_feedback;
    }


    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        editText1.addTextChangedListener(new TextWatcher() {
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
                    Toast.makeText(ProblemFeedbackActivity.this, "上限了，亲", Toast.LENGTH_SHORT).show();
                }
//                editStart = editText1.getSelectionStart();
//
//                editEnd = editText1.getSelectionEnd();
//
//                textView1.setText(String.valueOf(temp.length()));//此处需要进行强制类型转换
//
//                if (temp.length() > 500) {//条件判断可以实现其他功能
//
//                    s.delete(editStart - 1, editEnd);
//
//                    int tempSelection = editStart;
//
//                    ed_content.setText(s);
//
//                    ed_content.setSelection(tempSelection);
//
//                    Toast.makeText(MainActivity.this, "你输入的字数已经超过了！", Toast.LENGTH_SHORT).show();
//
//                }
//            }

            }
        });


        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        rclPicture.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                Log.e("ProblemFeedbackActivity", "点击上传照片");
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
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
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


    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_ok)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(editText1.getText().toString().trim())) {
            if (editText1.getText().toString().length() > 300) {
                Toast.makeText(ProblemFeedbackActivity.this, "上限了，亲", Toast.LENGTH_SHORT).show();
            } else {
                //先上传图片
                if (selectList == null || selectList.size() <= 0) {
                    presenter.postFeedBack(editText1.getText().toString().trim(), "");//上传数据
                } else {
                    for (int i = 0; i < selectList.size(); i++) {
                        presenter.postUploadPhotos(selectList.get(i).getPath());
                    }
                }
            }

        } else {
            Toast.makeText(this, "请先填写反馈内容", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 提交成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getFeedBackSuccess(int code, Object data) {
        Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void getFeedBackFail(int code, String msg) {

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
                PictureSelector.create(ProblemFeedbackActivity.this)
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
     * @Description: 上传图片成功
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void uploadPhotosSuccess(int code, UpdateImgBean data) {
        Log.e("问题反馈", "==上传图片成功==" + data);
        sb.append(data.getShort_img() + ",");
        imgUrlList.add(sb.toString());
        Log.e("问题反馈", "==上传图片成功==imgUrlList.size===" + imgUrlList.size());
        Log.e("问题反馈", "==上传图片成功==sb===" + sb.toString());
        if (imgUrlList.size() == selectList.size()) {
            presenter.postFeedBack(editText1.getText().toString().trim(), sb.toString());//上传数据
//            presenter.multigraph(sb.toString(), order_id, rec_id, public_edit.getText().toString().trim(), total_score, describe_score, logistics_score, server_score, is_show);
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


}
