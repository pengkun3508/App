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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.StatusBarUtils;
import com.dm.lib.utils.ToastMaker;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.GridImageAdapter;
import com.vinnlook.www.surface.PictureSelectorBean;
import com.vinnlook.www.surface.bean.PublishComment;
import com.vinnlook.www.surface.bean.UpdateImgBean;
import com.vinnlook.www.surface.mvp.presenter.PublishCommentPresenter;
import com.vinnlook.www.surface.mvp.view.PublishCommentView;
import com.vinnlook.www.utils.FullyGridLayoutManager;
import com.vinnlook.www.utils.ImageLoader;
import com.vinnlook.www.utils.PictureSelectorHelper;
import com.vinnlook.www.widgat.StarBarView;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description:发表评论
 * @Time:2020/5/15$
 * @Author:pk$
 */
public class PublishCommentActivity extends BaseActivity<PublishCommentPresenter> implements PublishCommentView {

    private static Context context;
    private static String goods_id;
    private static String search_attr;
    private static String order_id;
    private static String rec_id;
    @BindView(R.id.public_item_image)
    ImageView public_item_image;
    @BindView(R.id.public_item_title)
    TextView public_item_title;
    @BindView(R.id.public_item_type)
    TextView public_item_type;
    @BindView(R.id.starBar1)
    StarBarView starBar1;
    @BindView(R.id.public_edit)
    EditText public_edit;
    @BindView(R.id.public_photo_layout)
    RelativeLayout public_photo_layout;
    @BindView(R.id.public_photo_img)
    ImageView public_photo_img;
    @BindView(R.id.public_niming_layout)
    LinearLayout public_niming_layout;
    @BindView(R.id.public_check_circle)
    ImageView public_check_circle;
    @BindView(R.id.starBar2)
    StarBarView starBar2;
    @BindView(R.id.starBar3)
    StarBarView starBar3;
    @BindView(R.id.starBar4)
    StarBarView starBar4;
    @BindView(R.id.public__btn)
    TextView public__btn;
    @BindView(R.id.rclPicture)
    RecyclerView rclPicture;
    @BindView(R.id.pingjia_text_1)
    TextView pingjia_text_1;
    @BindView(R.id.pingjia_text_2)
    TextView pingjia_text_2;
    @BindView(R.id.pingjia_text_3)
    TextView pingjia_text_3;
    @BindView(R.id.pingjia_text_4)
    TextView pingjia_text_4;


    String total_score;
    String describe_score;
    String logistics_score;
    String server_score;

    private static final int BAIDU_READ_PHONE_STATE = 100;
    int maxSelectNum = 9;
    String is_show = "0";
    GridImageAdapter adapter;
    private List<LocalMedia> selectList;
    private List<String> imgUrlList;
    private List<PictureSelectorBean> pictureSelectorBeanList = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public PopupWindow popupwindow;


    public static void startSelf(Context contexts, String goods_ids, String search_attrs, String order_ids, String rec_ids) {
        Intent intent = new Intent(contexts, PublishCommentActivity.class);
        contexts.startActivity(intent);
        context = contexts;
        goods_id = goods_ids;
        search_attr = search_attrs;
        order_id = order_ids;
        rec_id = rec_ids;

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_comment;
    }

    @Override
    protected PublishCommentPresenter initPresenter() {
        return new PublishCommentPresenter();
    }

    @Override
    protected void initView() {
        imgUrlList = new ArrayList<>();
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        is_show = "0";
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        rclPicture.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, new GridImageAdapter.onAddPicClickListener() {
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

        //匿名选择
        public_niming_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("PublishCommentActivity", "==匿名选择==" + is_show);
                if (is_show.equals("0")) {
                    is_show = "1";
                    public_check_circle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_1));
                } else if (is_show.equals("1")) {
                    is_show = "0";
                    public_check_circle.setImageDrawable(ResUtils.getDrawable(R.mipmap.shop_cat_icon_2));
                }
            }
        });

        starBar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starBar1.getStarRating() == 1) {
                    pingjia_text_1.setText("差评");
                } else if (starBar1.getStarRating() == 2) {
                    pingjia_text_1.setText("不满意");
                } else if (starBar1.getStarRating() == 3) {
                    pingjia_text_1.setText("一般");
                } else if (starBar1.getStarRating() == 4) {
                    pingjia_text_1.setText("满意");
                } else if (starBar1.getStarRating() == 5) {
                    pingjia_text_1.setText("非常满意");
                }
            }
        });
        starBar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starBar2.getStarRating() == 1) {
                    pingjia_text_2.setText("差评");
                } else if (starBar2.getStarRating() == 2) {
                    pingjia_text_2.setText("不满意");
                } else if (starBar2.getStarRating() == 3) {
                    pingjia_text_2.setText("一般");
                } else if (starBar2.getStarRating() == 4) {
                    pingjia_text_2.setText("满意");
                } else if (starBar2.getStarRating() == 5) {
                    pingjia_text_2.setText("非常满意");
                }
            }
        });
        starBar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starBar3.getStarRating() == 1) {
                    pingjia_text_3.setText("差评");
                } else if (starBar3.getStarRating() == 2) {
                    pingjia_text_3.setText("不满意");
                } else if (starBar3.getStarRating() == 3) {
                    pingjia_text_3.setText("一般");
                } else if (starBar3.getStarRating() == 4) {
                    pingjia_text_3.setText("满意");
                } else if (starBar3.getStarRating() == 5) {
                    pingjia_text_3.setText("非常满意");
                }
            }
        });
        starBar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starBar4.getStarRating() == 1) {
                    pingjia_text_4.setText("差评");
                } else if (starBar4.getStarRating() == 2) {
                    pingjia_text_4.setText("不满意");
                } else if (starBar4.getStarRating() == 3) {
                    pingjia_text_4.setText("一般");
                } else if (starBar4.getStarRating() == 4) {
                    pingjia_text_4.setText("满意");
                } else if (starBar4.getStarRating() == 5) {
                    pingjia_text_4.setText("非常满意");
                }
            }
        });

        //发表评价
        public__btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(public_edit.getText().toString().trim())) {
                    ToastMaker.showShort("请填写评价内容");
                } else {

                    if (selectList == null || selectList.size() <= 0) {
                        total_score = String.valueOf(starBar1.getStarRating());//总体评分
                        describe_score = String.valueOf(starBar2.getStarRating());//描述相符
                        logistics_score = String.valueOf(starBar3.getStarRating());//物流评分
                        server_score = String.valueOf(starBar4.getStarRating());//服务态度
                        Log.e("PublishCommentActivity", "==selectList==" + selectList);
                        Log.e("PublishCommentActivity", "==order_id==" + order_id);
                        Log.e("PublishCommentActivity", "==rec_id==" + rec_id);
                        Log.e("PublishCommentActivity", "==content==" + public_edit.getText().toString().trim());
                        Log.e("PublishCommentActivity", "==total_score==" + total_score);
                        Log.e("PublishCommentActivity", "==describe_score==" + describe_score);
                        Log.e("PublishCommentActivity", "==logistics_score==" + logistics_score);
                        Log.e("PublishCommentActivity", "==server_score==" + server_score);
                        Log.e("PublishCommentActivity", "==is_show==" + is_show);

                        //图片为空，传空字符串
                        presenter.multigraph("", order_id, rec_id, public_edit.getText().toString().trim(), total_score, describe_score, logistics_score, server_score, is_show);
                    } else {
                        Log.e("发表评价", "==selectList=123=" + selectList);
                        for (int i = 0; i < selectList.size(); i++) {
                            presenter.postUploadPhotos(selectList.get(i).getPath());
                        }


                    }
                }
            }
        });
    }

    @Override
    protected void loadData() {
        presenter.getPublicComment(goods_id, search_attr);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * @Description: 页面数据下载成功
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void getPublishCommentSuccess(int code, PublishComment data) {

        if (data.getBanner().get(0).getType() == 1) {
            ImageLoader.image(PublishCommentActivity.this, public_item_image, data.getBanner().get(0).getUrl());
        } else if (data.getBanner().get(0).getType() == 2) {
            ImageLoader.image(PublishCommentActivity.this, public_item_image, data.getBanner().get(1).getUrl());
        }
        public_item_title.setText(data.getShop_name());
        public_item_type.setText(data.getShop_attr_name());


    }

    /**
     * @Description: 页面数据下载失败
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void getPublishCommentFail(int code, String msg) {

    }

    /**
     * @Description: 发表评价成功
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void multigraphSuccess(int code, Object data) {
        Log.e("PublishCommentActivity", "==发表评价成功==");
        finish();
    }

    /**
     * @Description: 发表评价失败
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void multigraphFailed(int code, String msg) {

    }

    /**
     * @Description: 上传图片成功
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void uploadPhotosSuccess(int code, UpdateImgBean data) {
        Log.e("PublishCommentActivity", "==上传图片成功==" + data);
        sb.append(data.getShort_img() + ",");
        imgUrlList.add(sb.toString());
        Log.e("PublishCommentActivity", "==上传图片成功==imgUrlList.size===" + imgUrlList.size());
        Log.e("PublishCommentActivity", "==上传图片成功==sb===" + sb.toString());
        if (imgUrlList.size() == selectList.size()) {
            presenter.multigraph(sb.toString(), order_id, rec_id, public_edit.getText().toString().trim(), total_score, describe_score, logistics_score, server_score, is_show);
        }
//        else {
//            for (int i = 0; i < selectList.size(); i++) {
//                presenter.postUploadPhotos(selectList.get(i).getPath());
//            }
//        }


    }

    /**
     * @Description: 上传图片失败
     * @Time:2020/5/21 17:26
     * @Author:pk
     */
    @Override
    public void uploadPhotosFailed(int code, String msg) {

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
                PictureSelector.create(PublishCommentActivity.this)
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


}
