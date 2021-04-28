package com.vinnlook.www.surface.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dm.lib.core.adapter.rv.OnClickListener;
import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.StatusBarUtils;
import com.flyco.roundview.RoundTextView;
import com.m7.imkfsdk.KfStartHelper;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.model.entity.FromToMessage;
import com.moor.imkf.utils.MoorUtils;
import com.vinnlook.www.R;
import com.vinnlook.www.base.BaseActivity;
import com.vinnlook.www.surface.adapter.MsggingBoxAdapter;
import com.vinnlook.www.surface.bean.MsggingTypeBean;
import com.vinnlook.www.surface.mvp.presenter.MsggingBoxPresenter;
import com.vinnlook.www.surface.mvp.view.MsggingBoxView;
import com.vinnlook.www.utils.DensityUtils;
import com.vinnlook.www.utils.UserUtils;
import com.vinnlook.www.widgat.SpaceItemDecoration;
import com.vinnlook.www.widgat.SpacesItemDecoration;
import com.vinnlook.www.widgat.actionbar.ActionBarSimple;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: 我的--消息盒子
 * @Time:2021/3/2$
 * @Author:pk$
 */
public class MsggingBoxActivity extends BaseActivity<MsggingBoxPresenter> implements MsggingBoxView {


    @BindView(R.id.msg_open_btn)
    RoundTextView msgOpenBtn;
    @BindView(R.id.msg_close_btn)
    LinearLayout msgCloseBtn;
    boolean isOpened = false;
    @BindView(R.id.tips_msg_layout)
    RelativeLayout tipsMsgLayout;
    @BindView(R.id.msg_title_back)
    ImageView msgTitleBack;
    @BindView(R.id.msg_title_clear)
    LinearLayout msgTitleClear;

    MsggingBoxAdapter msgAdapter;
    @BindView(R.id.msg_recycler)
    RecyclerView msgRecycler;
    @BindView(R.id.action_bar)
    ActionBarSimple actionBar;
    @BindView(R.id.msg_kefu_layout)
    LinearLayout msgKefuLayout;
    @BindView(R.id.msg_kefu_number)
    TextView msgKefuNumber;
    @BindView(R.id.msg_kefu_content)
    TextView msgKefuContent;
    @BindView(R.id.msg_time_text)
    TextView msgTimeText;

    List<FromToMessage> fromToMessages;
    KfStartHelper helper;
    String getUser_name;
    String message;


    public PopupWindow popupwindow;


    public static void startSelf(Context context) {
        Intent intent = new Intent(context, MsggingBoxActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.msg_box_activity;
    }

    @Override
    protected MsggingBoxPresenter initPresenter() {
        return new MsggingBoxPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBarMode(getActivity(), true);
        initImkf();//初始化7陌

        try {
            isOpened = NotificationManagerCompat.from(this).areNotificationsEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            isOpened = false;
        }
        if (isOpened) {
            tipsMsgLayout.setVisibility(View.GONE);
        } else {
            tipsMsgLayout.setVisibility(View.VISIBLE);
        }
        msgAdapter = new MsggingBoxAdapter(getActivity());
        final GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 1);
        msgRecycler.setLayoutManager(manager3);
        msgRecycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 5)));
        msgRecycler.addItemDecoration(new SpaceItemDecoration(0, 0));
        msgRecycler.setNestedScrollingEnabled(false);
        msgRecycler.setHasFixedSize(true);
        msgRecycler.setAdapter(msgAdapter);
        msgAdapter.addOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                MsggingListActivity.startSelf(MsggingBoxActivity.this, msgAdapter.getData().get(position).getType(), msgAdapter.getData().get(position).getName());

            }
        });

    }

    @Override
    protected void loadData() {
//        presenter.getTypeListData();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getTypeListData();
        initImkf();
    }

    @OnClick({R.id.msg_title_back, R.id.msg_title_clear, R.id.msg_open_btn, R.id.msg_close_btn, R.id.msg_kefu_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.msg_title_back://返回
                finish();
                break;
            case R.id.msg_title_clear://清除未读


                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    return;
                } else {
                    initmPopupWindowView2();
                    popupwindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }


                break;
            case R.id.msg_open_btn://打开消息通知
                gotoSet();
                break;
            case R.id.msg_close_btn://关闭提示页面
                tipsMsgLayout.setVisibility(View.GONE);
                break;
            case R.id.msg_kefu_layout://客服
                PermissionHelper.with(getContext()).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request(new PermissionHelper.PermissionListener() {
                            @Override
                            public void onSuccess() {
                                if (!UserUtils.getInstance().getUserId().equals("")) {
                                    initSdk();
                                } else {
                                    Toast.makeText(getActivity(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailed() {
                            }
                        });
                break;
        }
    }


    private void gotoSet() {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
        } else {
            // 其他
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    /**
     * 获取列表成功
     *
     * @param code
     * @param data
     */
    @Override
    public void getTypeListDataSuccess(int code, List<MsggingTypeBean> data) {
        msgAdapter.setData(data);
        int num = 0;
        for (int i = 0; i < data.size(); i++) {
            num = num + Integer.parseInt(data.get(i).getUnread_count());

        }

        if (num > 0) {
            msgTimeText.setText("您有" + num + "条未读消息");
        } else {
            msgTimeText.setText("暂时没收到新的消息");
        }

        Log.e("获取列表成功", "==num==" + num);

    }

    /**
     * 获取列表失败
     *
     * @param code
     * @param
     */
    @Override
    public void getTypeListDataFail(int code, String msg) {

    }


    /**
     * 初始化SDK
     */
    private void initSdk() {
        //设置sdk 显示语言版本
//        initLanguage("en");
        /*
          第一步:初始化help
         */
        final KfStartHelper helper = new KfStartHelper((AppCompatActivity) getActivity());
        /*
          商品信息实例，若有需要，请参照此方法；
         */
//        handleCardInfo(helper);
         /*
          新卡片类型示例，若有需要，请参照此方法；
         */
//        handleNewCardInfo(helper);
        /*
          第二步:设置参数
          初始化sdk方法，必须先调用该方法进行初始化后才能使用IM相关功能
          @param accessId       接入id（需后台配置获取）
          @param userName       用户名
          @param userId         用户id
         */
        /*
         * 修改会话页面 注销按钮 文案。建议两个 文字
         */
//        helper.setChatActivityLeftText("注销");
        /**
         * 传递orderid和头像路径
         */
        helper.setOrderHead("", UserUtils.getInstance().getUserInfo().getImg_url());

        /*
         * 修改会话页面 是否需要 emoji表情 按钮。
         */
//        helper.setChatActivityEmoji(true);

        String userID = UserUtils.getInstance().getUserInfo().getUser_id();
        if (UserUtils.getInstance().getUserInfo().getUser_id().length() < 2) {
            userID = "0" + userID;
        }
        helper.initSdkChat("97e623b0-f404-11ea-938a-2d31778d2422", UserUtils.getInstance().getUserInfo().getUser_name(), userID);

    }


    /**
     * 初始化7陌
     */
    private void initImkf() {
        //首先判断是否在此之前初始化过sdk,获取消息参数需userid等参数
        if (MoorUtils.isInitForUnread(MsggingBoxActivity.this)) {
            IMChatManager.getInstance().getMsgUnReadCountFromService(new IMChatManager.HttpUnReadListen() {
                @Override
                public void getUnRead(int acount) {
//                    Toast.makeText(MsggingBoxActivity.this, "未读消息数为：" + acount, Toast.LENGTH_SHORT).show();
                    Log.e("获取聊天记录", "==未读消息数为==" + acount);
                    Log.e("获取聊天记录", "==新消息==" + IMChatManager.getInstance().getMessages(1));
                    fromToMessages = IMChatManager.getInstance().getMessages(1);
                    for (int i = 0; i < fromToMessages.size(); i++) {
                        Log.e("fromToMessages", "==userType==i===" + i + "=====" + fromToMessages.get(i).userType);
                        Log.e("fromToMessages", "==message==i===" + i + "=====" + fromToMessages.get(i).message);
                        Log.e("fromToMessages", "==msgType==i===" + i + "=====" + fromToMessages.get(i).msgType);
                    }
                    if (fromToMessages.size() > 0) {
                        if (fromToMessages.get(0).userType.equals("0")) {//用户
                            getUser_name = UserUtils.getInstance().getUserInfo().getUser_name();
                            if (fromToMessages.get(0).msgType.equals("text")) {
                                message = fromToMessages.get(0).message;
                            } else if (fromToMessages.get(0).msgType.equals("video")) {
                                message = "[视频]";
                            } else if (fromToMessages.get(0).msgType.equals("image")) {
                                message = "[图片]";
                            } else if (fromToMessages.get(0).msgType.equals("file")) {
                                message = "[文件]";
                            } else if (fromToMessages.get(0).msgType.equals("cardInfo")) {
                                message = "[商品链接]";
                            } else {
                                message = "..........";
                            }
                        } else if (fromToMessages.get(0).userType.equals("1")) {//客服
                            getUser_name = "Vinnlook客服";
                            if (fromToMessages.get(0).msgType.equals("text")) {
                                message = fromToMessages.get(0).message;
                            } else if (fromToMessages.get(0).msgType.equals("video")) {
                                message = "[视频]";
                            } else if (fromToMessages.get(0).msgType.equals("image")) {
                                message = "[图片]";
                            } else if (fromToMessages.get(0).msgType.equals("file")) {
                                message = "[文件]";
                            } else if (fromToMessages.get(0).msgType.equals("cardInfo")) {
                                message = "[商品链接]";
                            } else {
                                message = "..........";
                            }
                        }
                        msgKefuContent.setText(getUser_name + ":" + message);
                    } else {
                        msgKefuContent.setText("暂时没收到新的消息");
                    }


                    if (acount > 0) {
                        msgKefuNumber.setText(String.valueOf(acount));
                        msgKefuNumber.setVisibility(View.VISIBLE);
                    } else {
                        msgKefuNumber.setVisibility(View.GONE);
                    }

                }
            });
        } else {
            //未初始化，消息当然为 ：0
//            Toast.makeText(MsggingBoxActivity.this, "还没初始化", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 解绑微信
     */
    private void initmPopupWindowView2() {
        TextView return_update_btn, sure_btn;
        // // 获取自定义布局文件pop.xml的视图
        View customView = getLayoutInflater().inflate(R.layout.clear_msg_layout, null, false);
        return_update_btn = customView.findViewById(R.id.return_update_btn);
        sure_btn = customView.findViewById(R.id.sure_btn);
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
        //返回
        return_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupwindow.dismiss();
            }
        });
        //确定
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupwindow != null && popupwindow.isShowing()) {
                    popupwindow.dismiss();
                    popupwindow = null;
                }
                presenter.getClearUnread();
            }
        });
    }

}
