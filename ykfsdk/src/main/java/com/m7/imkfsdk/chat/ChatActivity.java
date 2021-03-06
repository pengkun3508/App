package com.m7.imkfsdk.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dm.lib.utils.StatusBarUtils;
import com.m7.imkfsdk.MoorWebCenter;
import com.m7.imkfsdk.R;
import com.m7.imkfsdk.chat.adapter.ChatAdapter;
import com.m7.imkfsdk.chat.adapter.ChatTagLabelsAdapter;
import com.m7.imkfsdk.chat.listener.ChatListClickListener;
import com.m7.imkfsdk.chat.model.MsgTaskBean;
import com.m7.imkfsdk.chat.model.MsgTaskItemBean;
import com.m7.imkfsdk.chat.model.OrderBaseBean;
import com.m7.imkfsdk.chat.model.OrderInfoBean;
import com.m7.imkfsdk.chat.model.OrderInfoParams;
import com.m7.imkfsdk.constant.Constants;
import com.m7.imkfsdk.recordbutton.AudioRecorderButton;
import com.m7.imkfsdk.utils.AntiShake;
import com.m7.imkfsdk.utils.FaceConversionUtil;
import com.m7.imkfsdk.utils.FileUtils;
import com.m7.imkfsdk.utils.PickUtils;
import com.m7.imkfsdk.utils.ToastUtils;
import com.m7.imkfsdk.view.ActionSheetDialog;
import com.m7.imkfsdk.view.BottomSheetLogisticsInfoDialog;
import com.m7.imkfsdk.view.BottomSheetLogisticsProgressDialog;
import com.m7.imkfsdk.view.ChatListView;
import com.m7.imkfsdk.view.TcpExitDiaglog;
import com.moor.imkf.IMChat;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.IMMessage;
import com.moor.imkf.db.dao.GlobalSetDao;
import com.moor.imkf.db.dao.InfoDao;
import com.moor.imkf.db.dao.MessageDao;
import com.moor.imkf.eventbus.EventBus;
import com.moor.imkf.gson.Gson;
import com.moor.imkf.gson.reflect.TypeToken;
import com.moor.imkf.http.HttpManager;
import com.moor.imkf.http.HttpResponseListener;
import com.moor.imkf.listener.AcceptOtherAgentListener;
import com.moor.imkf.listener.ChatListener;
import com.moor.imkf.listener.GetGlobleConfigListen;
import com.moor.imkf.listener.GetPeersListener;
import com.moor.imkf.listener.OnConvertManualListener;
import com.moor.imkf.listener.onResponseListener;
import com.moor.imkf.model.construct.JsonBuild;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.model.entity.ChatEmoji;
import com.moor.imkf.model.entity.ChatMore;
import com.moor.imkf.model.entity.FlowBean;
import com.moor.imkf.model.entity.FromToMessage;
import com.moor.imkf.model.entity.GlobalSet;
import com.moor.imkf.model.entity.NewCardInfo;
import com.moor.imkf.model.entity.Peer;
import com.moor.imkf.model.entity.ScheduleConfig;
import com.moor.imkf.model.parser.HttpParser;
import com.moor.imkf.tcpservice.event.MsgEvent;
import com.moor.imkf.tcpservice.event.MsgunReadToReadEvent;
import com.moor.imkf.tcpservice.event.QuestionEvent;
import com.moor.imkf.tcpservice.event.ReSendMessage;
import com.moor.imkf.tcpservice.event.TcpBreakEvent;
import com.moor.imkf.tcpservice.event.TransferAgent;
import com.moor.imkf.tcpservice.event.UnAssignEvent;
import com.moor.imkf.tcpservice.event.VoiceToTextEvent;
import com.moor.imkf.tcpservice.service.TcpManager;
import com.moor.imkf.utils.LogUtils;
import com.moor.imkf.utils.MoorUtils;
import com.moor.imkf.utils.NullUtil;
import com.moor.imkf.websocket.WebSocketHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ????????????
 *
 * @author LongWei
 */
public class ChatActivity extends MyBaseActivity implements OnClickListener,
        OnItemClickListener, ChatListView.OnRefreshListener, AudioRecorderButton.RecorderFinishListener {
    private boolean isFront = false;//?????????????????????????????????
    private ChatListView mChatList;
    private Button mChatSend, mChatMore, mChatSetModeVoice,
            mChatSetModeKeyboard;
    TextView chat_tv_convert;
    LinearLayout chat_tv_back;
    private EditText mChatInput;
    private ChatAdapter chatAdapter;
    private RelativeLayout mChatEdittextLayout,
            mChatMoreContainer;
    private LinearLayout mMore;
    private AudioRecorderButton mRecorderButton;
    //	private SpeechRecorderButton mRecorderButton;
    private RelativeLayout mChatFaceContainer, rl_emoji_layout;
    private ImageView mChatEmojiNormal, mChatEmojiChecked;
    private InputMethodManager manager;
    private TextView mOtherName;
    private TextView other_name_static;
    private OnCorpusSelectedListener mListener;
    private ViewPager mChatEmojiVPager, mChatMoreVPager;
    private ArrayList<View> facePageViews;
    private ArrayList<View> morePageViews;
    private LinearLayout mChatIvImageMore, mChatIvImageFace;
    private ArrayList<ImageView> pointViewsFace, pointViewsMore;
    private List<List<ChatEmoji>> emojis;
    private List<FaceAdapter> faceAdapters;
    private List<MoreAdapter> moreAdapters;
    private int current = 0;
    private ArrayList<ChatMore> moreList;
    private TcpExitDiaglog singleButtonDialog;
    private TcpExitDiaglog.Builder builder;
    // ???????????????????????????
    public List<List<ChatMore>> moreLists = new ArrayList<List<ChatMore>>();
    private List<FromToMessage> fromToMessage;
    private Boolean JZflag = true;
    private boolean isZXResply = false;//??????????????????????????????????????????????????????tcp????????????true????????????????????????true ???
    private View header;
    private int pageSize = 2;
    private int height;
    private List<FromToMessage> descFromToMessage = new ArrayList<FromToMessage>();
    private static final String tag = "ChatActivity";
    private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
    private static final int PICK_FILE_ACTIVITY_REQUEST_CODE = 300;
    private String picFileFullName;
    MsgReceiver msgReceiver;
    KeFuStatusReceiver keFuStatusReceiver;
    private String peerId = "";
    LinearLayout chat_queue_ll;
    TextView chat_queue_tv;
    LinearLayout bar_bottom;
    private LoadingFragmentDialog loadingDialog;
    private static final int HANDLER_MSG = 1;
    private static final int HANDLER_MSG_MORE = 2;
    private static final int HANDLER_ROBOT = 0x111;
    private static final int HANDLER_ONLINE = 0x222;
    private static final int HANDLER_OFFNLINE = 0x333;
    private static final int HANDLER_INVESTIGATE = 0x444;
    private static final int HANDLER_QUEUENUM = 0x555;
    private static final int HANDLER_CLIAM = 0x666;
    private static final int HANDLER_FINISH = 0x777;
    private static final int HANDLER_BREAK = 0x888;
    private static final int HANDLER_BREAK_TIP = 0x999;
    private static final int HANDLER_VIPASSIGNFAIL = 0x1000;
    private static final int HANDLER_LEAVEMSG = 0x1100;
    private static final int HANDLER_WRITING = 0x1200;
    private static final int HANDLER_NO_WRITING = 0x1300;
    private String left_text;//??????????????????
    private boolean show_emoji = true;//????????????emoji??????
    private boolean isRobot = false;
    private String type = "";
    private String scheduleId = "";
    private String processId = "";
    private String currentNodeId = "";
    private String schedule_id = "";
    private String schedule_topeer = "";
    private String processType = "";
    private String titleName = "";
    private String entranceId = "";

    //    ???????????????????????????//false ?????? true??????
    private boolean NotAllowCustomerPushCsr = false;
    //   ????????????  ???????????????????????????//false???   true?????????
    private boolean NotAllowCustomerCloseCsr = false;
    //?????????????????????????????????
    private Boolean robotEvaluationFinish = false;

    //?????????????????????????????????
    private boolean hasSendRobotMsg = false;
    //?????????????????????????????????
    private boolean hasSendPersonMsg = false;

    ChatMore chatMore2, chatMore3, chatMore4, chatMore5;

    private int permisssionType;
    private boolean isQueue = false;

    private InvestigateDialog dialog;
    private String id;
    private boolean isInvestigate = true;//???????????????????????? true????????????????????????false??????????????????
    private boolean convesationIsLive = true;// ????????????????????????????????? true???????????????false???????????????
    private boolean hasSet = true;//??????????????????????????????
    private boolean conversationOver = false;//pc???????????????????????? true?????????
    private boolean INVITATION_INVESTIGATE = false;//??????????????????;true????????????

    private LinearLayout ll_hintView;
    private RecyclerView rvTagLabel;
    private ChatTagLabelsAdapter tagLabeAdapter;
    private List<FlowBean> flowBeanList = new ArrayList<>();
    private ChatHandler handler;
    private AntiShake shake = new AntiShake();
    private SharedPreferences spData;

    Timer break_timer;
    Timer break_tip_timer;
    long breakTime = 0;
    long breakTipTime = 0;
    String break_tips;
    BreakTimerTask breakTimerTask;
    BreakTipTimerTask breakTipTimerTask;
    private BottomSheetLogisticsInfoDialog moreOrderInfoDialog;
    private Set<String> mHashSet = new HashSet<String>();//???????????????????????????_id??????onDestroy()?????? showOrderInfo ?????????????????????2???

    private CountDownTimer mCountDownTimer;


    private static String orderId;

    JSONObject j1 = new JSONObject();
    //???j1 ?????????JSONObject j2???????????? j2?????????????????????????????????
    //?????????????????????j2 ??????key value????????????????????????
    JSONObject j2 = new JSONObject();

    private static void setOrderId(String orderIds) {
        orderId = orderIds;
    }


    /**
     * Handler?????????????????????
     */
    private static class ChatHandler extends Handler {
        private final WeakReference<ChatActivity> mActivty;
        StringBuilder fullResult = new StringBuilder();

        public void clearResult() {
            this.fullResult = new StringBuilder();
        }

        ChatHandler(ChatActivity Activty) {
            this.mActivty = new WeakReference<>(Activty);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChatActivity chatActivity = mActivty.get();
            chatActivity.handleMessage(msg, fullResult);
        }
    }

    /**
     * ??????????????????
     */
    public void handleMessage(Message msg, StringBuilder fullResult) {
        Log.e("handleMessage", "==?????????==" + msg.what);
        if (msg.what == HANDLER_MSG) {
            updateMessage();
        } else if (msg.what == HANDLER_MSG_MORE) {
            // ?????????????????????
            JZMoreMessage();
        } else if (msg.what == HANDLER_ROBOT) {
            other_name_static.setText("????????????");
            //??????????????????
            ToastUtils.showShort(this, R.string.now_robit);
            if (IMChatManager.getInstance().isShowTransferBtn()) {
                chat_tv_convert.setVisibility(View.VISIBLE);
                LogUtils.dTag("handleMessage==", "??????????????????-????????????");
            } else {
                chat_tv_convert.setVisibility(View.GONE);
                LogUtils.dTag("handleMessage==", "??????????????????-????????????");
            }
            bar_bottom.setVisibility(View.VISIBLE);
            isRobot = true;
            // ?????????
            setChatMoreList();
        } else if (msg.what == HANDLER_ONLINE) {
            other_name_static.setText("????????????");
            //???????????????
            chat_tv_convert.setVisibility(View.GONE);
        } else if (msg.what == HANDLER_WRITING) {
            //????????????????????????
            mOtherName.setText(R.string.other_writing);
            other_name_static.setText("????????????");
        } else if (msg.what == HANDLER_NO_WRITING) {
            mOtherName.setText(titleName);
            other_name_static.setText("????????????");
        } else if (msg.what == HANDLER_OFFNLINE) {
            other_name_static.setText("????????????");
            ToastUtils.showShort(this, R.string.people_not_online);
            if (IMChatManager.getInstance().isShowTransferBtn()) {
                chat_tv_convert.setVisibility(View.VISIBLE);
            } else {
                chat_tv_convert.setVisibility(View.GONE);
            }
            if (isRobot) {
                bar_bottom.setVisibility(View.VISIBLE);
            } else {
                bar_bottom.setVisibility(View.VISIBLE);
            }
            showOffLineDialog();
        } else if (msg.what == HANDLER_INVESTIGATE) {
            other_name_static.setText("????????????");
            //????????????????????????
            INVITATION_INVESTIGATE = true;
            openInvestigateDialog(false);
        } else if (msg.what == HANDLER_QUEUENUM) {
            other_name_static.setText("????????????");
            String queueNem = (String) msg.obj;
            showQueueNumLabel(queueNem);
            isQueue = true;
            //??????
            setChatMoreList();
        } else if (msg.what == HANDLER_CLIAM) {
            other_name_static.setText("????????????");
            chat_queue_ll.setVisibility(View.GONE);
            chat_tv_convert.setVisibility(View.GONE);
            bar_bottom.setVisibility(View.VISIBLE);
            isRobot = false;
            isQueue = false;
            //????????????????????????
            checkConverstaion();
//                setChatMoreList();
            Toast.makeText(getApplicationContext(), R.string.people_now, Toast.LENGTH_SHORT).show();
            IMChatManager.getInstance().setIsShowBottomList(false);
            rvTagLabel.setVisibility(View.GONE);
        } else if (msg.what == HANDLER_FINISH) {
            // ??????????????????????????????
            if (IMChatManager.getInstance().isFinishWhenReConnect) {
            } else {
                bar_bottom.setVisibility(View.GONE);
            }
            mOtherName.setText(R.string.people_isleave);
            titleName = getString(R.string.people_isleave);
            other_name_static.setText("????????????");
            chat_tv_convert.setVisibility(View.GONE);
            conversationOver = true;
            //?????????????????????????????????????????????
        } else if (msg.what == HANDLER_LEAVEMSG) {
            //?????????
            Intent intent = new Intent(ChatActivity.this, ScheduleOfflineMessageActivity.class);
            intent.putExtra("LeavemsgNodeId", schedule_id);
            intent.putExtra("ToPeer", schedule_topeer);
            startActivity(intent);
            finish();
        } else if (msg.what == HANDLER_BREAK) {
            LogUtils.dTag("BreakTimer", "HANDLER_BREAK===????????????");
            //????????????
            IMChatManager.getInstance().quitSDk();
            finish();
        } else if (msg.what == HANDLER_BREAK_TIP) {
            LogUtils.dTag("BreakTimer", "HANDLER_BREAK_TIP===?????????????????????");
            //?????????????????????
            IMChat.getInstance().createBreakTipMsg(break_tips);
            updateMessage();
        } else if (msg.what == HANDLER_VIPASSIGNFAIL) {
            //?????????????????????
            showVipAssignFailDialog();
        } else if (getString(R.string.chat_img).equals(msg.obj)) {
            //????????????
            checkPermissoion(0);
        } else if (getString(R.string.chat_evaluate).equals(msg.obj)) {
            openInvestigateDialog(true);
        } else if (getString(R.string.chat_question).equals(msg.obj)) {
            startActivity(new Intent(ChatActivity.this, CommonQuestionsActivity.class));
        } else if (getString(R.string.chat_file).equals(msg.obj)) {
            //????????????
            checkPermissoion(1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtils.setStatusBarMode(this, true);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.kf_activity_chat);
        getIntentData(getIntent());
        handler = new ChatHandler(this);
        spData = this.getSharedPreferences("moordata", 0);
        //???timestamp???????????????
        SharedPreferences.Editor edit = spData.edit();
        edit.putString("SERVERTIMESTAMP", "").apply();
        left_text = spData.getString(Constants.CHATACTIVITYLEFTTEXT, "");
        show_emoji = spData.getBoolean(Constants.CHATACTIVITYEMOJI, true);
        titleName = getString(R.string.wait_link);
        registerRec();
        EventBus.getDefault().register(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initPermission();
        }


        //??? ???????????????????????????????????? ??????????????? ???????????????,  ?????????????????????????????????????????????????????????????????????
        MessageDao.getInstance().updateAllisShowVT();
        //  ??????client?????????client????????????????????????????????????????????????transcriber


        IMChatManager.getInstance().handleSSLHandshake();//??????https?????????????????????
        initBottomList();
        registerListener();
        initEmojiViewPager();
        initEmojiPoint();
        initEmojiData();
        initMoreViewPager();
        initMorePoint();
        initMoreData();
        updateMessage();
//        //????????????????????????
        if (IMChatManager.getInstance().getInvestigate().size() > 0) {
            hasSet = true;
        } else {
            hasSet = false;
        }
        loadingDialog = new LoadingFragmentDialog();
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show(this.getSupportFragmentManager(), "");
        if (type.equals("peedId")) {
            beginSession(peerId);
        }
        if (type.equals("schedule")) {
            beginScheduleSession(scheduleId, processId, currentNodeId, entranceId);
        }
        builder = new TcpExitDiaglog.Builder(this);
        //??????????????????
        setGlobalConfig();


    }

    //????????????
    private void registerRec() {
        IntentFilter intentFilter = new IntentFilter("com.m7.imkfsdk.msgreceiver");
        msgReceiver = new MsgReceiver();
        registerReceiver(msgReceiver, intentFilter);

        IntentFilter kefuIntentFilter = new IntentFilter();
        kefuIntentFilter.addAction(IMChatManager.ROBOT_ACTION);
        kefuIntentFilter.addAction(IMChatManager.ONLINE_ACTION);
        kefuIntentFilter.addAction(IMChatManager.OFFLINE_ACTION);
        kefuIntentFilter.addAction(IMChatManager.CLIAM_ACTION);
        kefuIntentFilter.addAction(IMChatManager.INVESTIGATE_ACTION);
        kefuIntentFilter.addAction(IMChatManager.QUEUENUM_ACTION);
        kefuIntentFilter.addAction(IMChatManager.LEAVEMSG_ACTION);
        kefuIntentFilter.addAction(IMChatManager.FINISH_ACTION);
        kefuIntentFilter.addAction(IMChatManager.USERINFO_ACTION);
        kefuIntentFilter.addAction(IMChatManager.VIPASSIGNFAIL_ACTION);
        kefuIntentFilter.addAction(IMChatManager.CANCEL_ROBOT_ACCESS_ACTION);
        kefuIntentFilter.addAction(IMChatManager.WITHDRAW_ACTION);
        kefuIntentFilter.addAction(IMChatManager.WRITING_ACTION);
        kefuIntentFilter.addAction(IMChatManager.ROBOT_SWITCH_ACTION);
        kefuIntentFilter.addAction(IMChatManager.TCP_ACTION);
        kefuIntentFilter.addAction(IMChatManager.ZXMSG_ACTION);

        kefuIntentFilter.addAction(IMChatManager.VIDEO_INVITED_ACTION);
        kefuIntentFilter.addAction(IMChatManager.CANCEL_VIDEO_ACTION);
        keFuStatusReceiver = new KeFuStatusReceiver();
        registerReceiver(keFuStatusReceiver, kefuIntentFilter);
    }

    /**
     * ????????????????????????
     */
    private void checkPermissoion(int type) {
        permisssionType = type;
        //??????
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PickUtils.PermissionUtils.hasAlwaysDeniedPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                PickUtils.PermissionUtils.requestPermissions(this, 0x11, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PickUtils.PermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        if (permisssionType == 0) {
                            openAlbum();
                        } else if (permisssionType == 1) {
                            openFile();
                        }
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        Toast.makeText(ChatActivity.this, R.string.notpermession, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        } else {
            if (permisssionType == 0) {
                openAlbum();
            } else if (permisssionType == 1) {
                openFile();
            }
        }
    }


    /**
     * ??????????????????
     */
    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//????????????
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, PICK_FILE_ACTIVITY_REQUEST_CODE);
    }

    private void showVipAssignFailDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.warm_prompt)
                .setMessage(R.string.doyouneedother)
                .setPositiveButton(R.string.need, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        IMChatManager.getInstance().acceptOtherAgent(peerId, new AcceptOtherAgentListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(ChatActivity.this, getString(R.string.ykf_notify_otheragent), Toast.LENGTH_SHORT).show();
                                //??????????????????????????????????????????
                                if (type.equals("peedId")) {
                                    HttpManager.beginNewVipOfflineSession(InfoDao.getInstance().getConnectionId()
                                            , IMChatManager.getInstance().getIsNewVisitor()
                                            , peerId
                                            , ""
                                            , getResponseListener(true));
                                }
                                if (type.equals("schedule")) {
                                    HttpManager.beginNewVipOfflineScheduleChatSession(InfoDao.getInstance().getConnectionId()
                                            , IMChatManager.getInstance().getIsNewVisitor()
                                            , scheduleId
                                            , processId
                                            , currentNodeId
                                            , entranceId
                                            , ""
                                            , getResponseListener(false));
                                }

                            }

                            @Override
                            public void onFailed() {
                                Toast.makeText(ChatActivity.this, getString(R.string.ykf_notify_otheragent_fail), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton(R.string.noneed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        IMChatManager.getInstance().quitSDk();
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();

    }

    /**
     * ???????????????
     */
    private void showQueueNumLabel(String queueNum) {
        if (Integer.parseInt(queueNum) > 0) {
            chat_queue_ll.setVisibility(View.VISIBLE);
            try {
                String queueNumText = GlobalSetDao.getInstance().getGlobalSet().queueNumText;
                int beginIndex = queueNumText.indexOf("{");
                int endIndex = queueNumText.indexOf("}");
                String newString = queueNumText.replace(queueNumText.substring(beginIndex, endIndex + 1), queueNum);
                SpannableString ss = new SpannableString(newString);
                ss.setSpan(new ForegroundColorSpan(Color.parseColor("#21b38a")), beginIndex,
                        beginIndex + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                chat_queue_tv.setText(ss);
            } catch (Exception e) {

                chat_queue_tv.setText(getResources().getText(R.string.numbers01) + queueNum + getResources().getText(R.string.number02));
            }
        } else {
            chat_queue_ll.setVisibility(View.GONE);
        }
    }


    private void initPermission() {
        if (PickUtils.PermissionUtils.hasAlwaysDeniedPermission(this, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PickUtils.PermissionUtils.requestPermissions(this, 0x11, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PickUtils.PermissionUtils.OnPermissionListener() {
                @Override
                public void onPermissionGranted() {
                }

                @Override
                public void onPermissionDenied(String[] deniedPermissions) {
                    Toast.makeText(ChatActivity.this, R.string.notpermession, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    /**
     * ?????????????????????
     */
    private void setGlobalConfig() {
        GlobalSet globalSet = GlobalSetDao.getInstance().getGlobalSet();
        if (globalSet != null) {
            String break_len = globalSet.break_len;
            String break_tips_len = globalSet.break_tips_len;
            break_tips = globalSet.break_tips;
            try {
                breakTime = Integer.parseInt(break_len) * 60 * 1000;
                LogUtils.dTag("BreakTimer", "breakTime===" + break_len);
            } catch (Exception e) {
            }
            try {
                breakTipTime = breakTime - Integer.parseInt(break_tips_len) * 60 * 1000;
                LogUtils.dTag("BreakTimer", "breakTipTime===" + break_tips_len);
            } catch (Exception e) {
            }
            if (breakTime > 0) {
                break_timer = new Timer();
                breakTimerTask = new BreakTimerTask();
                break_timer.schedule(breakTimerTask, breakTime);
            }
            if (breakTipTime > 0) {
                break_tip_timer = new Timer();
                breakTipTimerTask = new BreakTipTimerTask();
                break_tip_timer.schedule(breakTipTimerTask, breakTipTime);
            }
        }
    }


    /**
     * xbot??????????????????
     *
     * @param msgStr
     */
    public void sendXbotTextMsg(String msgStr) {

        if (!isRobot) {
            ToastUtils.showShort(this, getString(R.string.ykf_not_robot_send));
            return;
        }
        //?????????????????????????????????????????? 2?????????????????????????????????????????????
        if (isRobot && !hasSendRobotMsg) {
            hasSendRobotMsg = true;
            setChatMoreList();
        }
        if (!isRobot && !hasSendPersonMsg) {
            hasSendPersonMsg = true;
            setChatMoreList();
        }
        LogUtils.aTag("send", msgStr);
        FromToMessage fromToMessage = IMMessage.createTxtMessage(msgStr);

        //????????????
        sendSingleMessage(fromToMessage);
    }

    /**
     * ??????????????????
     *
     * @param msgStr
     */
    public void sendTextMsg(String msgStr) {
        //????????????????????????????????????????????????????????????
        if (conversationOver) {
            return;
        }

        //?????????????????????????????????????????? 2 ?????????????????????????????????????????????
        if (isRobot && !hasSendRobotMsg) {
            hasSendRobotMsg = true;
            setChatMoreList();
        }
        if (!isRobot && !hasSendPersonMsg) {
            hasSendPersonMsg = true;
            setChatMoreList();
        }
        LogUtils.aTag("send", msgStr);
        FromToMessage fromToMessage = IMMessage.createTxtMessage(msgStr);

        //????????????
        sendSingleMessage(fromToMessage);
    }

    /**
     * ?????????????????????
     */
    class BreakTimerTask extends TimerTask {
        @Override
        public void run() {
            handler.sendEmptyMessage(HANDLER_BREAK);
            break_timer.cancel();
        }
    }

    /**
     * ??????????????????????????????
     */
    class BreakTipTimerTask extends TimerTask {
        @Override
        public void run() {
            handler.sendEmptyMessage(HANDLER_BREAK_TIP);
            break_tip_timer.cancel();
        }
    }

    /**
     * ???????????????????????????
     */
    private void resetBreakTimer() {
        if (break_timer != null) {
            break_timer.cancel();
            break_timer = null;
        }
        if (break_tip_timer != null) {
            break_tip_timer.cancel();
            break_tip_timer = null;
        }

        if (breakTimerTask != null) {
            breakTimerTask.cancel();
        }
        if (breakTipTimerTask != null) {
            breakTipTimerTask.cancel();
        }

        if (breakTime > 0) {
            break_timer = new Timer();
            breakTimerTask = new BreakTimerTask();
            break_timer.schedule(breakTimerTask, breakTime);
        }
        if (breakTipTime > 0) {
            break_tip_timer = new Timer();
            breakTipTimerTask = new BreakTipTimerTask();
            break_tip_timer.schedule(breakTipTimerTask, breakTipTime);
        }
        LogUtils.dTag("BreakTimer", "resetBreakTimer===???????????????????????????");
    }

    /**
     * ???????????????????????????
     */
    public void updateMessage() {
        fromToMessage = IMChatManager.getInstance().getMessages(1);
        descFromToMessage.clear();
        for (int i = fromToMessage.size() - 1; i >= 0; i--) {
            descFromToMessage.add(fromToMessage.get(i));
        }
        // ???????????????
        if (IMChatManager.getInstance().isReachEndMessage(descFromToMessage.size())) {
            mChatList.dismiss();
        }
        chatAdapter = new ChatAdapter(ChatActivity.this, descFromToMessage);
        mChatList.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
        mChatList.setSelection(fromToMessage.size() + 1);

        //????????????????????????
        IMChatManager.getInstance().resetMsgUnReadCount();
        //????????????
        mOtherName.setText(titleName);
        if (handler.hasMessages(HANDLER_NO_WRITING)) {
            handler.removeMessages(HANDLER_NO_WRITING);
        }
    }

    // ???????????????????????????????????????????????????????????????
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        updateMessage();
        //?????????????????????????????????
        robotEvaluationFinish = false;
        conversationOver = false;

        //?????????????????????????????????
        hasSendRobotMsg = false;
        hasSendPersonMsg = false;
        isQueue = false;
        JZflag = true;


        resetBreakTimer();

        getIntentData(intent);
        /*???????????????*/
        if (type.equals("peedId")) {
            LogUtils.aTag("beginSession", "ChatActivity690?????????");
            beginSession(peerId);
        }
        if (type.equals("schedule")) {
            beginScheduleSession(scheduleId, processId, currentNodeId, entranceId);
        }

    }

    public void getIntentData(Intent intent) {
        //???????????????id
        if (intent.getStringExtra("PeerId") != null) {
            peerId = intent.getStringExtra("PeerId");
        }
        if (intent.getStringExtra("type") != null) {
            type = intent.getStringExtra("type");
        }
        if (intent.getStringExtra("scheduleId") != null) {
            scheduleId = intent.getStringExtra("scheduleId");
        }
        if (intent.getStringExtra("processId") != null) {
            processId = intent.getStringExtra("processId");
        }
        if (intent.getStringExtra("currentNodeId") != null) {
            currentNodeId = intent.getStringExtra("currentNodeId");
        }
        if (intent.getStringExtra("entranceId") != null) {
            entranceId = intent.getStringExtra("entranceId");
        }
        if (intent.getStringExtra("processType") != null) {
            processType = intent.getStringExtra("processType");
        }
        //??????????????????????????????????????????????????????????????????
        MoorUtils.initForUnread(ChatActivity.this);
        IMChatManager.getInstance().isFinishWhenReConnect = false;
    }

    // ??????????????????
    public void JZMoreMessage() {
        fromToMessage = IMChatManager.getInstance().getMessages(pageSize);

        descFromToMessage.clear();
        for (int i = fromToMessage.size() - 1; i >= 0; i--) {
            descFromToMessage.add(fromToMessage.get(i));
        }

        chatAdapter.notifyDataSetChanged();

        if (mChatList.getHeaderViewsCount() > 0) {
            mChatList.removeHeaderView(header);
        }

        // ???????????????
        if (IMChatManager.getInstance().isReachEndMessage(
                descFromToMessage.size())) {
            mChatList.setSelectionFromTop(fromToMessage.size() - (pageSize - 1) * 15,
                    height);
            mChatList.dismiss();
        } else {
            mChatList.setSelectionFromTop(fromToMessage.size() - (pageSize - 1) * 15
                    + 1, height);
        }

        mChatList.onRefreshFinished();
        JZflag = true;
        pageSize++;
    }

    // ?????????bottomList
    @SuppressLint("ClickableViewAccessibility")
    public void initBottomList() {


        // ???????????????????????????????????????????????????
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //??????????????????
        rvTagLabel = findViewById(R.id.rv_tag_label);
        rvTagLabel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rl_emoji_layout = findViewById(R.id.rl_emoji_layout);//emoji??????????????????
        List<FlowBean> datas = new ArrayList<>();
        tagLabeAdapter = new ChatTagLabelsAdapter(datas);
        rvTagLabel.setAdapter(tagLabeAdapter);
        tagLabeAdapter.setOnItemClickListener(new ChatTagLabelsAdapter.onItemClickListener() {
            @Override
            public void OnItemClick(FlowBean flowBean) {
                sendXbotTextMsg(flowBean.getText());
            }
        });
        rvTagLabel.setVisibility(View.GONE);//?????????????????????list

        mChatSend = (Button) this.findViewById(R.id.chat_send);
        chat_tv_back = (LinearLayout) this.findViewById(R.id.chat_tv_back);
        mRecorderButton = (AudioRecorderButton) findViewById(R.id.chat_press_to_speak);
        mRecorderButton.setRecordFinishListener(this);
        mChatInput = (EditText) this.findViewById(R.id.chat_input);
        ll_hintView = (LinearLayout) this.findViewById(R.id.ll_hintView);
        mChatEdittextLayout = (RelativeLayout) this
                .findViewById(R.id.chat_edittext_layout);
        mMore = (LinearLayout) this.findViewById(R.id.more);
        mChatEmojiNormal = (ImageView) this
                .findViewById(R.id.chat_emoji_normal);
        mChatEmojiChecked = (ImageView) this
                .findViewById(R.id.chat_emoji_checked);
        mChatFaceContainer = (RelativeLayout) this
                .findViewById(R.id.chat_face_container);
        mChatMoreContainer = (RelativeLayout) this
                .findViewById(R.id.chat_more_container);
        mChatMore = (Button) this.findViewById(R.id.chat_more);

        mChatSetModeVoice = (Button) this
                .findViewById(R.id.chat_set_mode_voice);
        mChatSetModeKeyboard = (Button) this
                .findViewById(R.id.chat_set_mode_keyboard);

        //????????????????????????????????????????????????
        chat_tv_convert = (TextView) this.findViewById(R.id.chat_tv_convert);

        if (type.equals("schedule") && !processType.equals("robot")) {
            chat_tv_convert.setVisibility(View.GONE);
        }
        //?????????????????????
        if (!IMChatManager.getInstance().isShowTransferBtn()) {
            chat_tv_convert.setVisibility(View.GONE);
            LogUtils.dTag("handleMessage==", "?????????????????????-????????????");
        }

//        if (TextUtils.isEmpty(left_text)) {
//            chat_tv_back.setText(getString(R.string.logout));
//        } else {
//            chat_tv_back.setText(left_text);
//        }

        if (show_emoji) {
            rl_emoji_layout.setVisibility(View.VISIBLE);
        } else {
            rl_emoji_layout.setVisibility(View.GONE);
        }

        mOtherName = (TextView) this.findViewById(R.id.other_name);
        other_name_static = this.findViewById(R.id.other_name_static);
        mChatInput.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    mChatEdittextLayout
//                            .setBackgroundResource(R.drawable.kf_input_bar_bg_active);
//                } else {
//                    mChatEdittextLayout
//                            .setBackgroundResource(R.drawable.kf_input_bar_bg_normal);
//                }

            }
        });
        mChatInput.requestFocus();
        mChatInput.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (IMChatManager.USE_TCP) {
                    if (!MoorUtils.isNetWorkConnected(IMChatManager.getInstance().getAppContext()) &&
                            TcpManager.TcpStatus.BREAK.equals(TcpManager.getInstance(IMChatManager.getInstance().
                                    getAppContext()).getTcpStatus())) {
//                    Toast.makeText(getApplicationContext(), "???????????????????????????~", Toast.LENGTH_SHORT).show();
                        LogUtils.aTag("???????????????break");
                        TcpManager.getInstance(IMChatManager.getInstance().getAppContext()).setTcpStatus(TcpManager.TcpStatus.NONET);
                        startReStartDialog3();
                        return;
                    }
                } else {
                    if (!MoorUtils.isNetWorkConnected(IMChatManager.getInstance().getAppContext()) &&
                            !WebSocketHandler.getDefault().isConnect()) {
//                    Toast.makeText(getApplicationContext(), "???????????????????????????~", Toast.LENGTH_SHORT).show();
                        LogUtils.aTag("???????????????break");
                        startReStartDialog3();
                        return;
                    }
                }


                if (IMChatManager.getInstance().isFinishWhenReConnect) {
                    // beginSession();
                    startReStartDialog();
                } else {
//                    mChatEdittextLayout
//                            .setBackgroundResource(R.drawable.kf_input_bar_bg_active);
                    mChatEmojiNormal.setVisibility(View.VISIBLE);
                    mChatEmojiChecked.setVisibility(View.GONE);

                    mMore.setVisibility(View.GONE);
                    mChatFaceContainer.setVisibility(View.GONE);
                    mChatMoreContainer.setVisibility(View.GONE);
                }


            }
        });

        // ???????????????
        mChatInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!TextUtils.isEmpty(s)) {
                    mChatMore.setVisibility(View.GONE);
                    mChatSend.setVisibility(View.VISIBLE);
                } else {
                    mChatMore.setVisibility(View.VISIBLE);
                    mChatSend.setVisibility(View.GONE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: 2019-12-02 ???????????????????????????????????????
                //????????????????????????,xbot?????????
                if (!TextUtils.isEmpty(s)) {
                    if (IMChat.getInstance().getLianXiangOn())
                        HttpManager.queryLianXiangData(InfoDao.getInstance().getConnectionId(), IMChat.getInstance().getRobotType(), s.toString(), new GetLianXiangDataResponeHandler());
                } else {
                    ll_hintView.setVisibility(View.GONE);
                }
            }
        });

        mChatList = (ChatListView) this.findViewById(R.id.chat_list);
        header = View.inflate(this, R.layout.kf_chatlist_header, null);
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        header.measure(w, h);
        height = header.getMeasuredHeight();

        mChatList.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(ChatActivity.this, v);
                mMore.setVisibility(View.GONE);
                mChatEmojiNormal.setVisibility(View.VISIBLE);
                mChatEmojiChecked.setVisibility(View.GONE);
                mChatFaceContainer.setVisibility(View.GONE);
                mChatMoreContainer.setVisibility(View.GONE);
                return false;
            }
        });

        emojis = FaceConversionUtil.getInstace().emojiLists;
        moreList = new ArrayList<ChatMore>();
        chatMore2 = new ChatMore(2, R.drawable.kf_icon_chat_pic + "",
                getString(R.string.chat_img));
        chatMore3 = new ChatMore(3, R.drawable.kf_icon_chat_file + "",
                getString(R.string.chat_file));
        chatMore4 = new ChatMore(4, R.drawable.kf_icon_chat_investigate + "",
                getString(R.string.chat_evaluate));
        chatMore5 = new ChatMore(5, R.drawable.kf_icon_chat_question + "",
                getString(R.string.chat_question));


        moreList.add(chatMore2);
        moreList.add(chatMore3);
//        moreList.add(chatMore5);
        //        moreList.add(chatMore4);//???????????????????????????
        int pageCount = (int) Math.ceil(moreList.size() / 8 + 0.1);
        for (int i = 0; i < pageCount; i++) {
            moreLists.add(getData(i));
        }

        mChatEmojiVPager = (ViewPager) findViewById(R.id.chat_emoji_vPager);
        mChatMoreVPager = (ViewPager) findViewById(R.id.chat_more_vPager);
        mChatInput = (EditText) findViewById(R.id.chat_input);
        mChatIvImageFace = (LinearLayout) findViewById(R.id.chat_iv_image_face);
        mChatIvImageMore = (LinearLayout) findViewById(R.id.chat_iv_image_more);
        chat_queue_ll = (LinearLayout) findViewById(R.id.chat_queue_ll);
        chat_queue_tv = (TextView) findViewById(R.id.chat_queue_tv);
        bar_bottom = (LinearLayout) findViewById(R.id.bar_bottom);
    }


    // ??????????????????
    public void registerListener() {
        mChatSend.setOnClickListener(this);
        chat_tv_back.setOnClickListener(this);
        mChatSetModeVoice.setOnClickListener(this);
        mChatSetModeKeyboard.setOnClickListener(this);
        mChatEmojiNormal.setOnClickListener(this);
        mChatEmojiChecked.setOnClickListener(this);
        mChatMore.setOnClickListener(this);
        mChatList.setOnRefreshListener(this);
        chat_tv_convert.setOnClickListener(this);

    }

    // ??????????????????
    private List<ChatMore> getData(int page) {
        int startIndex = page * 8;
        int endIndex = startIndex + 8;

        if (endIndex > moreList.size()) {
            endIndex = moreList.size();
        }
        List<ChatMore> list = new ArrayList<ChatMore>();
        list.addAll(moreList.subList(startIndex, endIndex));
        if (list.size() < 8) {
            for (int i = list.size(); i < 8; i++) {
                ChatMore object = new ChatMore();
                list.add(object);
            }
        }
        return list;
    }
// ???????????????

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        int i1 = v.getId();
        if (i1 == R.id.chat_tv_back) {//???????????????
            handleLogOutOrBackPressed();
        } else if (i1 == R.id.chat_tv_convert) {
            if (shake.check()) {
                return;
            }
            onEventMainThread(new TransferAgent());
        } else if (i1 == R.id.chat_send) {
            String txt = mChatInput.getText().toString();
            //????????????????????????????????????????????????

            if (IMChatManager.USE_TCP) {
                if (!MoorUtils.isNetWorkConnected(IMChatManager.getInstance().getAppContext()) &&
                        TcpManager.TcpStatus.BREAK.equals(TcpManager.getInstance(IMChatManager.getInstance().getAppContext()).getTcpStatus())) {
                    Toast.makeText(getApplicationContext(), getString(R.string.ykf_not_netwokr_error), Toast.LENGTH_SHORT).show();
                    LogUtils.aTag("???????????????break");
//                IMService.isRelogin = true;
                    TcpManager.getInstance(IMChatManager.getInstance().getAppContext()).setTcpStatus(TcpManager.TcpStatus.NONET);
                    startReStartDialog3();
                    return;
                }
            } else {
                if (!MoorUtils.isNetWorkConnected(IMChatManager.getInstance().getAppContext()) &&
                        !WebSocketHandler.getDefault().isConnect()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.ykf_not_netwokr_error), Toast.LENGTH_SHORT).show();
                    LogUtils.aTag("???????????????break");
//                IMService.isRelogin = true;
                    startReStartDialog3();
                    return;
                }

            }


            if (IMChatManager.getInstance().isFinishWhenReConnect) {
                startReStartDialog();//?????????????????????????????????
//            }
            } else {
                ll_hintView.setVisibility(View.GONE);
                sendTextMsg(txt);
            }
        } else if (i1 == R.id.chat_set_mode_voice) {
            if (Build.VERSION.SDK_INT < 23) {
                showVoice(v);
            } else {
                //6.0
                if (ContextCompat.checkSelfPermission(ChatActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    //?????????????????????
                    showVoice(v);
                } else {
                    //???????????????
                    ActivityCompat.requestPermissions(ChatActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 0x4444);
                }
            }


        } else if (i1 == R.id.chat_set_mode_keyboard) {
            mChatEdittextLayout.setVisibility(View.VISIBLE);
            mChatSetModeKeyboard.setVisibility(View.GONE);
            mChatSetModeVoice.setVisibility(View.VISIBLE);
            mChatInput.requestFocus();
            mRecorderButton.setVisibility(View.GONE);
            mChatFaceContainer.setVisibility(View.GONE);

            if (TextUtils.isEmpty(mChatInput.getText())) {
                mChatMore.setVisibility(View.VISIBLE);
                mChatSend.setVisibility(View.GONE);
            } else {
                mChatMore.setVisibility(View.GONE);
                mChatSend.setVisibility(View.VISIBLE);
            }


        } else if (i1 == R.id.chat_emoji_normal) {
            hideKeyboard(ChatActivity.this, v);
            mMore.setVisibility(View.VISIBLE);
            mChatEmojiNormal.setVisibility(View.GONE);
            mChatEmojiChecked.setVisibility(View.VISIBLE);
            mChatMoreContainer.setVisibility(View.GONE);
            mChatFaceContainer.setVisibility(View.VISIBLE);
            mChatMoreVPager.setVisibility(View.GONE);
            mChatEmojiVPager.setVisibility(View.VISIBLE);

        } else if (i1 == R.id.chat_emoji_checked) {
            mChatEmojiNormal.setVisibility(View.VISIBLE);
            mChatEmojiChecked.setVisibility(View.GONE);
            mChatMoreContainer.setVisibility(View.GONE);
            mChatFaceContainer.setVisibility(View.GONE);
            mMore.setVisibility(View.GONE);

        } else if (i1 == R.id.chat_more) {
            if (mMore.getVisibility() == View.VISIBLE) {
                mChatMoreVPager.setVisibility(View.GONE);
                mMore.setVisibility(View.GONE);
            } else {
                mChatMoreVPager.setVisibility(View.VISIBLE);
                mMore.setVisibility(View.VISIBLE);
                mChatEmojiNormal.setVisibility(View.VISIBLE);
                mChatEmojiChecked.setVisibility(View.GONE);
                mChatFaceContainer.setVisibility(View.GONE);
                mChatMoreContainer.setVisibility(View.VISIBLE);
                mChatEmojiVPager.setVisibility(View.GONE);
                hideKeyboard(ChatActivity.this, v);
            }

        }
    }

    /**
     * ??????????????????????????????
     */
    private void handleLogOutOrBackPressed() {
        //????????????????????????,????????????????????????????????????????????????????????????????????????,????????????????????????,pc??????????????????,?????????????????????

        NotAllowCustomerCloseCsr = spData.getBoolean("NotAllowCustomerCloseCsr", false);

        if (!isRobot && IMChatManager.getInstance().isInvestigateOn()
                && convesationIsLive && hasSendPersonMsg
                && isZXResply && isInvestigate && hasSet && !conversationOver && !NotAllowCustomerCloseCsr) {
            showInvestigateDialog(true);
        } else {
            //????????????
            IMChat.getInstance().setLianXiangOn(false);
            IMChat.getInstance().setBotsatisfaOn(false);
            IMChatManager.getInstance().quitSDk();
            finish();
        }
        IMChatManager.getInstance().setIsShowBottomList(false);
    }

    private void showVoice(View v) {
        hideKeyboard(ChatActivity.this, v);
        mChatEdittextLayout.setVisibility(View.GONE);
        mMore.setVisibility(View.GONE);
        mChatSetModeVoice.setVisibility(View.GONE);
        mChatSetModeKeyboard.setVisibility(View.VISIBLE);
        mChatSend.setVisibility(View.GONE);
        mChatMore.setVisibility(View.VISIBLE);
        mRecorderButton.setVisibility(View.VISIBLE);
        mChatEmojiNormal.setVisibility(View.VISIBLE);
        mChatEmojiChecked.setVisibility(View.GONE);
        mChatMoreContainer.setVisibility(View.VISIBLE);
        mChatFaceContainer.setVisibility(View.GONE);
    }

    // ?????????????????????
    public interface OnCorpusSelectedListener {

        void onCorpusSelected(ChatEmoji emoji);

        void onCorpusDeleted();
    }

    // ??????????????????viewpager
    private void initMoreViewPager() {

        morePageViews = new ArrayList<View>();
        // ??????????????????
        View nullView1 = new View(this);
        // ??????????????????
        nullView1.setBackgroundColor(Color.TRANSPARENT);
        morePageViews.add(nullView1);

        // ?????????????????????
        moreAdapters = new ArrayList<MoreAdapter>();
        for (int i = 0; i < moreLists.size(); i++) {
            GridView view = new GridView(this);
            MoreAdapter adapter = new MoreAdapter(this, moreLists.get(i),
                    handler);
            view.setAdapter(adapter);
            moreAdapters.add(adapter);
            view.setOnItemClickListener(this);
            view.setNumColumns(4);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setPadding(5, 0, 5, 0);
            view.setSelector(new ColorDrawable(Color.TRANSPARENT));
            view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            view.setGravity(Gravity.CENTER);
            morePageViews.add(view);

        }

        // ?????????????????????
        View nullView2 = new View(this);
        // ??????????????????
        nullView2.setBackgroundColor(Color.TRANSPARENT);
        morePageViews.add(nullView2);

    }

    // ???????????????
    private void initMorePoint() {

        pointViewsMore = new ArrayList<ImageView>();
        ImageView imageView;
        mChatIvImageMore.removeAllViews();
        for (int i = 0; i < morePageViews.size(); i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.kf_d1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            layoutParams.width = 8;
            layoutParams.height = 8;
            mChatIvImageMore.addView(imageView, layoutParams);
            if (i == 0 || i == morePageViews.size() - 1) {
                imageView.setVisibility(View.GONE);
            }
            if (i == 1) {
                imageView.setBackgroundResource(R.drawable.kf_d2);
            }
            pointViewsMore.add(imageView);
        }
    }

    // ????????????
    private void initMoreData() {
        mChatMoreVPager.setAdapter(new ViewPagerAdapter(morePageViews));

        mChatMoreVPager.setCurrentItem(1);
        current = 0;
        mChatMoreVPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                current = arg0 - 1;
                // ???????????????
                drawMorePoint(arg0);
                // ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????.
                if (arg0 == pointViewsMore.size() - 1 || arg0 == 0) {
                    if (arg0 == 0) {
                        mChatMoreVPager.setCurrentItem(arg0 + 1);// ?????????
                        // ??????????????????????????????????????????.
                        pointViewsMore.get(1).setBackgroundResource(
                                R.drawable.kf_d2);
                    } else {
                        mChatMoreVPager.setCurrentItem(arg0 - 1);// ???????????????
                        pointViewsMore.get(arg0 - 1).setBackgroundResource(
                                R.drawable.kf_d2);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    // ??????????????????
    public void drawMorePoint(int index) {
        for (int i = 1; i < pointViewsMore.size(); i++) {
            if (index == i) {
                pointViewsMore.get(i).setBackgroundResource(R.drawable.kf_d2);
            } else {
                pointViewsMore.get(i).setBackgroundResource(R.drawable.kf_d1);
            }
        }
    }

    // ????????????????????????viewpager
    private void initEmojiViewPager() {
        facePageViews = new ArrayList<View>();
        // ??????????????????
        View nullView1 = new View(this);
        // ??????????????????
        nullView1.setBackgroundColor(Color.TRANSPARENT);
        facePageViews.add(nullView1);
        // ?????????????????????
        faceAdapters = new ArrayList<FaceAdapter>();
        for (int i = 0; i < emojis.size(); i++) {
            GridView view = new GridView(this);
            FaceAdapter adapter = new FaceAdapter(this, emojis.get(i));
            view.setAdapter(adapter);
            faceAdapters.add(adapter);
            view.setOnItemClickListener(this);
            view.setNumColumns(7);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setPadding(5, 0, 5, 0);
            view.setSelector(new ColorDrawable(Color.TRANSPARENT));
            view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            view.setGravity(Gravity.CENTER);
            facePageViews.add(view);
        }

        // ?????????????????????
        View nullView2 = new View(this);
        // ??????????????????
        nullView2.setBackgroundColor(Color.TRANSPARENT);
        facePageViews.add(nullView2);

    }

    // ???????????????
    private void initEmojiPoint() {
        pointViewsFace = new ArrayList<ImageView>();
        ImageView imageView;
        for (int i = 0; i < facePageViews.size(); i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.kf_d1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            layoutParams.width = 8;
            layoutParams.height = 8;
            mChatIvImageFace.addView(imageView, layoutParams);
            if (i == 0 || i == facePageViews.size() - 1) {
                imageView.setVisibility(View.GONE);
            }
            if (i == 1) {
                imageView.setBackgroundResource(R.drawable.kf_d2);
            }
            pointViewsFace.add(imageView);
        }
    }

    // ????????????
    private void initEmojiData() {
        mChatEmojiVPager.setAdapter(new ViewPagerAdapter(facePageViews));

        mChatEmojiVPager.setCurrentItem(1);
        current = 0;
        mChatEmojiVPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                current = arg0 - 1;
                // ???????????????
                drawFacePoint(arg0);
                // ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????.
                if (arg0 == pointViewsFace.size() - 1 || arg0 == 0) {
                    if (arg0 == 0) {
                        mChatEmojiVPager.setCurrentItem(arg0 + 1);// ?????????
                        // ??????????????????????????????????????????.
                        pointViewsFace.get(1).setBackgroundResource(
                                R.drawable.kf_d2);
                    } else {
                        mChatEmojiVPager.setCurrentItem(arg0 - 1);// ???????????????
                        pointViewsFace.get(arg0 - 1).setBackgroundResource(
                                R.drawable.kf_d2);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    // ??????????????????
    public void drawFacePoint(int index) {
        for (int i = 1; i < pointViewsFace.size(); i++) {
            if (index == i) {
                pointViewsFace.get(i).setBackgroundResource(R.drawable.kf_d2);
            } else {
                pointViewsFace.get(i).setBackgroundResource(R.drawable.kf_d1);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        if (mChatFaceContainer.getVisibility() == View.VISIBLE
                && mChatMoreContainer.getVisibility() == View.GONE) {

            ChatEmoji emoji = (ChatEmoji) faceAdapters.get(current).getItem(
                    arg2);
            if (emoji.getId() == R.drawable.kf_face_del_icon) {
                int selection = mChatInput.getSelectionStart();
                String text = mChatInput.getText().toString();
                if (selection > 0) {
                    String text2 = text.substring(selection - 1);
                    if (":".equals(text2)) {
                        String str = text.substring(0, selection - 1);
                        int start = str.lastIndexOf(":");
                        int end = selection;
                        mChatInput.getText().delete(start, end);
                        return;
                    }
                    mChatInput.getText().delete(selection - 1, selection);
                }

            }
            if (!TextUtils.isEmpty(emoji.getCharacter())) {
                if (mListener != null)
                    mListener.onCorpusSelected(emoji);
                SpannableString spannableString = FaceConversionUtil
                        .getInstace().addFace(this, emoji.getId(),
                                emoji.getCharacter(), mChatInput);
                mChatInput.append(spannableString);
            }
        }
    }

    // ??????????????????
    public void openAlbum() {
        Intent intentFromGallery = new Intent(Intent.ACTION_PICK, null);
        intentFromGallery.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intentFromGallery
                , PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfos.size() != 0) {
            startActivityForResult(intentFromGallery, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            ToastUtils.showShort(this, getString(R.string.ykf_no_imagepick));
        }
    }

    /**
     * ?????????????????????
     */
    private void openInvestigateDialog(boolean isFromButton) {
        //????????????????????????
        if (isRobot) {
            openRobotInvestigateDialog();
        } else {//??????
            boolean CSRAging = spData.getBoolean("CSRAging", false);
            String timestamp = spData.getString("SERVERTIMESTAMP", "");
            if (INVITATION_INVESTIGATE && isFromButton && CSRAging && !"".equals(timestamp)) {
                checkImCsrTimeout();
            } else {
                showInvestigateDialog(false);
            }

        }
    }

    /**
     * ????????????????????????
     */
    private void checkImCsrTimeout() {
        if (loadingDialog != null) {
            loadingDialog.show(this.getSupportFragmentManager(), "");
        }
        IMChatManager.getInstance().checkImCsrTimeout(new onResponseListener() {
            @Override
            public void onSuccess() {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
                showInvestigateDialog(false);
            }

            @Override
            public void onFailed() {
                ToastUtils.showShort(ChatActivity.this, getString(R.string.ykf_httpfun_error));
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onTimeOut() {
                ToastUtils.showShort(ChatActivity.this, getString(R.string.ykf_evaluation_timeout));
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
            }
        });

    }


    /**
     * ???????????????
     * 1.?????????????????????????????????
     * 2.????????????????????????
     *
     * @param logOutOrBackPressed true????????????????????????????????????
     */
    private void showInvestigateDialog(final boolean logOutOrBackPressed) {
        if (!hasSet) {
            return;
        }
        if (dialog != null && dialog.getDialog() != null && dialog.getDialog().isShowing()) {
            return;
        }
        dialog = new InvestigateDialog(new InvestigateDialog.SubmitPingjiaListener() {
            @Override
            public void OnSubmitSuccess() {
                if (logOutOrBackPressed) {
                    //????????????
                    IMChat.getInstance().setLianXiangOn(false);
                    IMChat.getInstance().setBotsatisfaOn(false);
                    //????????????
                    IMChatManager.getInstance().quitSDk();
                    finish();
                } else {
                    //???????????????????????????????????????????????????
                    isInvestigate = false;//??????????????????
                    setChatMoreList();
                }

            }

            @Override
            public void OnSubmitCancle() {
                if (logOutOrBackPressed) {
                    IMChatManager.getInstance().quitSDk();
                    finish();
                } else if (INVITATION_INVESTIGATE) {
                    getInvestigateTime();
                }


            }

            @Override
            public void OnSubmitFailed() {
                if (logOutOrBackPressed) {
                    IMChatManager.getInstance().quitSDk();
                    finish();
                } else {
                    isInvestigate = true;
                }

            }
        });
        dialog.show(getFragmentManager(), "InvestigateDialog");
    }

    /**
     * ????????????
     * 1.????????????/????????????????????????  ??????????????????????????????,???????????????ok
     * 2.????????????????????????/????????????????????????????????????,????????????;ok
     */
    private void getInvestigateTime() {
        IMChatManager.getInstance().getServerTime();
    }

    // ??????????????????xbot???????????????
    private void openRobotInvestigateDialog() {
        final String[] items = new String[]{getString(R.string.ykf_solved_ok),
                getString(R.string.ykf_solved_fail), getString(R.string.cancel)};

        AlertDialog builder = new AlertDialog.Builder(ChatActivity.this)
                .setTitle(getString(R.string.ykf_evaluation_robot))

                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 2) {
                            return;
                        } else {
                            String solve = which == 0 ? "true" : "false";
                            GlobalSet globalSet = GlobalSetDao.getInstance().getGlobalSet();
                            if ("xbot".equals(globalSet.robotType)) {//xobot?????????????????????
                                HttpManager.getRobotCsrInfo(solve, new HttpResponseListener() {
                                    @Override
                                    public void onSuccess(String responseStr) {
                                        robotEvaluationFinish = true;
                                        ToastUtils.showLong(ChatActivity.this, getString(R.string.ykf_robot_evaluation_ok));
                                        setChatMoreList();

                                    }

                                    @Override
                                    public void onFailed() {
                                        robotEvaluationFinish = false;
                                        ToastUtils.showLong(ChatActivity.this, getString(R.string.ykf_robot_evaluation_fail));
                                    }
                                });
                            } else {
                                HttpManager.getRobotCsrInfo(IMChat.getInstance().getBotId(), solve, new HttpResponseListener() {
                                    @Override
                                    public void onSuccess(String responseStr) {
                                        robotEvaluationFinish = true;
                                        ToastUtils.showLong(ChatActivity.this, getString(R.string.ykf_robot_evaluation_ok));
                                        //???????????????????????????????????????
                                        setChatMoreList();

                                    }

                                    @Override
                                    public void onFailed() {
                                        robotEvaluationFinish = false;
                                        ToastUtils.showLong(ChatActivity.this, getString(R.string.ykf_robot_evaluation_fail));
                                    }
                                });
                            }
                        }
                    }
                }).create();
        builder.show();
    }

    // ????????????
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    String realPath = PickUtils.getPath(ChatActivity.this, uri);
                    picFileFullName = realPath;
                    Log.d("?????????????????????", "???????????????????????????" + picFileFullName);
                    //????????????????????????
                    FromToMessage fromToMessage = IMMessage.createImageMessage(picFileFullName);
                    ArrayList fromTomsgs = new ArrayList<FromToMessage>();
                    fromTomsgs.add(fromToMessage);
                    descFromToMessage.addAll(fromTomsgs);
                    chatAdapter.notifyDataSetChanged();
                    mChatList.setSelection(descFromToMessage.size());
                    resetBreakTimer();
                    IMChat.getInstance().sendMessage(fromToMessage, new ChatListener() {
                        @Override
                        public void onSuccess() {
                            updateMessage();
                        }

                        @Override
                        public void onFailed() {
                            updateMessage();
                        }

                        @Override
                        public void onProgress(int progress) {

                        }
                    });
                } else {
                    Log.e(tag, "???????????????????????????");
                }
            }
        } else if (requestCode == PICK_FILE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();//??????uri??????????????????uri?????????file????????????
            String path = PickUtils.getPath(ChatActivity.this, uri);
            if (!NullUtil.checkNULL(path)) {
                Toast.makeText(ChatActivity.this, getString(R.string.ykf_not_support_file), Toast.LENGTH_SHORT).show();
                return;
            }
            File file = new File(path);
            String fileSizeStr = "";
            if (file.exists()) {
                long fileSize = file.length();
                if ((fileSize / 1024 / 1024) > 200.0) {
                    //??????200M????????????
                    Toast.makeText(ChatActivity.this, R.string.sendfiletoobig, Toast.LENGTH_SHORT).show();
                } else {
                    fileSizeStr = FileUtils.formatFileLength(fileSize);
                    String fileName = path.substring(path.lastIndexOf("/") + 1);
                    //????????????
                    FromToMessage fromToMessage = IMMessage.createFileMessage(path, fileName, fileSizeStr);
                    ArrayList fromTomsgs = new ArrayList<FromToMessage>();
                    fromTomsgs.add(fromToMessage);
                    descFromToMessage.addAll(fromTomsgs);
                    chatAdapter.notifyDataSetChanged();
                    mChatList.setSelection(descFromToMessage.size());
                    resetBreakTimer();
                    IMChat.getInstance().sendMessage(fromToMessage, new ChatListener() {
                        @Override
                        public void onSuccess() {
                            updateMessage();
                        }

                        @Override
                        public void onFailed() {
                            updateMessage();
                        }

                        @Override
                        public void onProgress(int progress) {
                            updateMessage();
                        }
                    });
                }
            }
        }

    }

    // ?????????????????????
    @Override
    public void onBackPressed() {
        if (dialog != null && dialog.getDialog() != null && dialog.getDialog().isShowing()) {
            return;
        }
        if (mMore.getVisibility() == View.VISIBLE) {
            mMore.setVisibility(View.GONE);
            mChatEmojiNormal.setVisibility(View.VISIBLE);
            mChatEmojiChecked.setVisibility(View.INVISIBLE);
        } else {
            handleLogOutOrBackPressed();

        }
    }

    @Override
    protected void onDestroy() {

        if (mHashSet.size() > 0) {
            Iterator<String> iterator = mHashSet.iterator();
            while (iterator.hasNext()) {
                String _id = iterator.next();
                IMChatManager.getInstance().updateOrderInfo(_id, "2");
            }
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        unregisterReceiver(msgReceiver);
        unregisterReceiver(keFuStatusReceiver);
        mRecorderButton.cancelListener();
        if (break_timer != null) {
            break_timer.cancel();
            break_timer = null;
        }
        if (break_tip_timer != null) {
            break_tip_timer.cancel();
            break_tip_timer = null;
        }
        if (breakTimerTask != null) {
            breakTimerTask.cancel();
        }
        if (breakTipTimerTask != null) {
            breakTipTimerTask.cancel();
        }
        IMChat.getInstance().setCancel(true);
        //??????????????????
        MessageDao.getInstance().delecteCardMsgs();
        MessageDao.getInstance().delecteNewCardMsgs();
        EventBus.getDefault().unregister(this);

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        chatAdapter.onPause();
        isFront = false;
    }

    @Override
    public void toRefresh() {
        if (JZflag) {
            JZflag = false;
            new Thread() {
                public void run() {
                    try {
                        sleep(300);
                        handler.sendEmptyMessage(HANDLER_MSG_MORE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                ;
            }.start();
        }
    }

    /**
     * ??????????????????,??????????????????????????????
     */
    class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            handler.sendEmptyMessage(HANDLER_MSG);
        }
    }

    /**
     * ?????????????????????
     */
    class KeFuStatusReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            Log.e("?????????????????????", "==KeFuStatusReceiver==" + action);
            if (IMChatManager.ROBOT_ACTION.equals(action)) {
                //??????????????????
                handler.sendEmptyMessage(HANDLER_ROBOT);
            } else if (IMChatManager.ONLINE_ACTION.equals(action)) {
                other_name_static.setText("????????????");
                //?????????????????????
                handler.sendEmptyMessage(HANDLER_ONLINE);
            } else if (IMChatManager.OFFLINE_ACTION.equals(action)) {
                other_name_static.setText("????????????");
                //?????????????????????
                handler.sendEmptyMessage(HANDLER_OFFNLINE);
            } else if (IMChatManager.INVESTIGATE_ACTION.equals(action)) {
                //?????????????????????
                handler.sendEmptyMessage(HANDLER_INVESTIGATE);
            } else if (IMChatManager.QUEUENUM_ACTION.equals(action)) {
                //??????????????????
                if (intent.getStringExtra(IMChatManager.QUEUENUM_ACTION) != null) {
                    String queueNum = intent.getStringExtra(IMChatManager.QUEUENUM_ACTION);
                    Message queueMsg = Message.obtain();
                    queueMsg.what = HANDLER_QUEUENUM;
                    queueMsg.obj = queueNum;
                    handler.sendMessage(queueMsg);
                }
            } else if (IMChatManager.CLIAM_ACTION.equals(action)) {
                //????????????????????????????????????
                handler.sendEmptyMessage(HANDLER_CLIAM);
            } else if (IMChatManager.LEAVEMSG_ACTION.equals(action)) {
                //schedule ?????????
                schedule_id = intent.getStringExtra(IMChatManager.CONSTANT_ID);
                schedule_topeer = intent.getStringExtra(IMChatManager.CONSTANT_TOPEER);
                handler.sendEmptyMessage(HANDLER_LEAVEMSG);
            } else if (IMChatManager.FINISH_ACTION.equals(action)) {
                other_name_static.setText("????????????");
                //?????????????????????
                handler.sendEmptyMessage(HANDLER_FINISH);
            } else if (IMChatManager.USERINFO_ACTION.equals(action)) {
                other_name_static.setText("????????????");
                //????????????
                String type = intent.getStringExtra(IMChatManager.CONSTANT_TYPE);//??????
                String exten = intent.getStringExtra(IMChatManager.CONSTANT_EXTEN);//??????
                String userName = intent.getStringExtra(IMChatManager.CONSTANT_USERNAME);//????????????
                String userIcon = intent.getStringExtra(IMChatManager.CONSTANT_USERICON);//????????????

                // ?????????
                if ("claim".equals(type)) {
//                    mOtherName.setText(NullUtil.checkNull(userName) + getString(R.string.seiveceforyou));
//                    titleName = NullUtil.checkNull(userName) + getString(R.string.seiveceforyou);
                    mOtherName.setText(NullUtil.checkNull(userName));
                    titleName = NullUtil.checkNull(userName);
                }
                // ?????????????????????
                if ("activeClaim".equals(type)) {
                    mOtherName.setText(NullUtil.checkNull(userName));
                    titleName = NullUtil.checkNull(userName);
                }
                if ("redirect".equals(type)) {
                    mOtherName.setText(NullUtil.checkNull(userName));
                    titleName = NullUtil.checkNull(userName);
                }
                if ("robot".equals(type)) {
                    mOtherName.setText(NullUtil.checkNull(userName));
                    titleName = NullUtil.checkNull(userName);
                }
            } else if (IMChatManager.VIPASSIGNFAIL_ACTION.equals(action)) {
                other_name_static.setText("????????????");
                //?????????????????????
                handler.sendEmptyMessage(HANDLER_VIPASSIGNFAIL);
            } else if (IMChatManager.CANCEL_ROBOT_ACCESS_ACTION.equals(action)) {
                other_name_static.setText("????????????");
                //????????????
                Toast.makeText(ChatActivity.this, R.string.receivepeopleaction, Toast.LENGTH_SHORT).show();
            } else if (IMChatManager.WITHDRAW_ACTION.equals(action)) {
                //????????????
                String id = intent.getStringExtra(IMChatManager.WITHDEAW_ID);
                MessageDao.getInstance().updateMsgWithDrawStatus(id);
                handler.sendEmptyMessage(HANDLER_MSG);
            } else if (IMChatManager.WRITING_ACTION.equals(action)) {
                //??????????????????
                handler.sendEmptyMessage(HANDLER_WRITING);
                handler.sendEmptyMessageDelayed(HANDLER_NO_WRITING, 5000);
            } else if (IMChatManager.ROBOT_SWITCH_ACTION.equals(action)) {
                //robotSwitch
                String status = intent.getStringExtra(IMChatManager.CONSTANT_ROBOT_SWITCH);
                String sessionId = intent.getStringExtra(IMChatManager.CONSTANT_SESSIONID);
            } else if (IMChatManager.TCP_ACTION.equals(action)) {
                //tcp??????
                String tcpstatus = intent.getStringExtra(IMChatManager.TCPSTATUS);
//            } else if (IMChatManager.UNASSIGN_ACTION.equals(action)){
//                //?????? ??????????????????????????????
//                chat_tv_convert.setVisibility(View.GONE);
            } else if (IMChatManager.ZXMSG_ACTION.equals(action)) { //??????????????????
                //?????????????????????????????????????????????????????????
                if (!isZXResply) {
                    OnlycheckConverstaion();//??????????????????????????????????????????UI
//                    setChatMoreList();
                }
            }
        }
    }


    /**
     * ????????????????????????????????????
     */
    private void setChatMoreList() {
        moreList.clear();
        moreList.add(chatMore2);
        moreList.add(chatMore3);
        NotAllowCustomerPushCsr = spData.getBoolean("NotAllowCustomerPushCsr", false);
        // TODO: 2019/5/22 ????????????????????????sp??????????????????????????????????????????????????????????????????????????????????????????????????????
        //????????????????????????,????????????????????????????????????????????????????????????????????????,????????????????????????
        if (!isRobot && IMChatManager.getInstance().isInvestigateOn() && convesationIsLive && isZXResply &&
                isInvestigate && hasSet && hasSendPersonMsg && !NotAllowCustomerPushCsr) {
            //??????????????????
//            if (!moreList.contains(chatMore4)) {
//                moreList.add(chatMore4);
//            }
        }
        //?????????????????????xbot
        GlobalSet globalSet = GlobalSetDao.getInstance().getGlobalSet();
        // ??????????????????????????????????????????,???????????????????????????
        if (isRobot && !robotEvaluationFinish && hasSendRobotMsg) {
            if ("xbot".equals(globalSet.robotType)) {
                if (isRobot && !robotEvaluationFinish && hasSendRobotMsg && IMChat.getInstance().getBotsatisfaOn()) {
//                    if (!moreList.contains(chatMore4)) {
//                        moreList.add(chatMore4);
//                    }
                }
            } else {
                // ????????????????????????????????????
                if (IMChat.getInstance().getBotsatisfaOn()) {
//                    if (!moreList.contains(chatMore4)) {
//                        moreList.add(chatMore4);
//                    }
                }
            }
        }

        //???????????????
        if (isQueue) {
//            if (moreList.contains(chatMore4)) {
//                moreList.remove(chatMore4);
//            }
        }
//        moreList.add(chatMore5);


//        if ("1".equals(NullUtil.checkNull(globalSet.videoChatOn))) {
//            ChatMore chatMore6 = new ChatMore(6, R.drawable.kf_icon_chat_video + "",
//                    "??????");
//            moreList.add(chatMore6);
//        }
        moreLists.clear();
        int pageCount = (int) Math.ceil(moreList.size() / 8 + 0.1);
        for (int i = 0; i < pageCount; i++) {
            moreLists.add(getData(i));
        }


        initMoreViewPager();
        initMorePoint();
        initMoreData();
    }

    /**
     * ???????????????????????????????????????????????????????????????????????????????????????UI???
     */
    private void OnlycheckConverstaion() {
        //???????????? ????????????????????????
        HttpManager.getChatSession(new HttpResponseListener() {

            @Override
            public void onSuccess(String responseStr) {
                LogUtils.eTag("huihua", responseStr);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(responseStr);
                    JSONObject data = jsonObject.getJSONObject("data");
                    if (data != null) {//????????????
                        convesationIsLive = true;
                        id = data.getString("_id");//??????id
                        if (data.has("replyMsgCount")) {
                            if (data.getInt("replyMsgCount") > 0) {
                                //?????????????????????
                                isZXResply = true;
                            } else {
                                isZXResply = false;
                            }
                        } else {
                            isZXResply = false;
                        }

                    } else {
                        //???????????????
                        convesationIsLive = false;
                    }
                    setChatMoreList();//???????????????????????????UI
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setChatMoreList();
            }

            @Override
            public void onFailed() {
                //????????????????????????????????????????????????????????????????????????,??????????????????????????? ????????????????????????????????????????????????????????????????????????
//                convesationIsLive = true;
//                isInvestigate = false;
//                setChatMoreList();
            }
        });
    }


    /**
     * ???????????????????????????????????????????????????
     *
     * @param
     */
    private void checkConverstaion() {
        //???????????? ????????????????????????
        HttpManager.getChatSession(new HttpResponseListener() {

            @Override
            public void onSuccess(String responseStr) {
                LogUtils.eTag("huihua", responseStr);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(responseStr);
                    JSONObject data = jsonObject.getJSONObject("data");
                    if (data != null) {//????????????
                        convesationIsLive = true;
                        id = data.getString("_id");//??????id
                        if (data.has("replyMsgCount")) {
                            if (data.getInt("replyMsgCount") > 0) {
                                //?????????????????????
                                isZXResply = true;
                            } else {
                                isZXResply = false;
                            }
                        } else {
                            isZXResply = false;
                        }
                        //???????????????????????????
                        HttpManager.checkIsAppraised(id, new HttpResponseListener() {
                            @Override
                            public void onSuccess(String responseStr) {
                                try {
                                    JSONObject jsonObject1 = new JSONObject(responseStr);
                                    isInvestigate = jsonObject1.getBoolean("isInvestigate");
                                    setChatMoreList();//???????????????????????????UI
                                } catch (JSONException e) {
                                    ToastUtils.showShort(ChatActivity.this, e.toString());
                                }
                                LogUtils.eTag("piangjia", responseStr);
                            }

                            @Override
                            public void onFailed() {
                                //???????????????????????? ???????????????????????????
//                                isInvestigate = false;
                            }
                        });
                    } else {
                        //???????????????
                        convesationIsLive = false;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed() {
                //????????????????????????????????????????????????????????????????????????,??????????????????????????? ????????????????????????????????????????????????????????????????????????
//                convesationIsLive = true;
//                isInvestigate = false;
            }
        });
    }

    /**
     * ????????????????????????
     * {@link #getResponseListener }
     *
     * @param peerId ?????????Id
     */
    private void beginSession(String peerId) {
        if (IMChatManager.getInstance().getAppContext() == null) {
            return;
        }
        //JSONObject j1?????????????????????????????????????????????,
        //key???value???????????????????????????

        try {
            j1.put("orderId", spData.getString(Constants.ORDERID, ""));
            j2.put("customField", URLEncoder.encode(j1.toString()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
//            j1.put("city","beijing");
//            JSONObject j3 = new JSONObject();
//            j3.put("user_labels",j1.toString());
//
//
//            String s="{\"user_labels\":{\"vip\":\"true\",\"city\":\"beijing\"}}";
//            j2.put("customField",URLEncoder.encode(s));

        // TODO: 2019/10/30 ???????????? ???????????????????????????????????????????????????
        IMChatManager.userId = InfoDao.getInstance().getUserId();
        HttpManager.beginNewChatSession(InfoDao.getInstance().getConnectionId()
                , IMChatManager.getInstance().getIsNewVisitor()
                , peerId
                , j2.toString()
                , getResponseListener(true));

    }

    /**
     * ??????????????????
     * *{@link #getResponseListener }
     *
     * @param scheduleId    ??????Id
     * @param processId
     * @param currentNodeId
     * @param entranceId
     */
    private void beginScheduleSession(String scheduleId, String processId, String currentNodeId, String entranceId) {
        if (IMChatManager.getInstance().getAppContext() == null) {
            return;
        }
        IMChatManager.userId = InfoDao.getInstance().getUserId();
        HttpManager.beginNewScheduleChatSession(InfoDao.getInstance().getConnectionId()
                , IMChatManager.getInstance().getIsNewVisitor()
                , scheduleId
                , processId
                , currentNodeId
                , entranceId
                , j2.toString()
                , getResponseListener(false));
    }

    /**
     * ????????????????????????????????? ????????????
     *
     * @param fromBeginSession ????????????????????????????????????
     * @return HttpResponseListener
     */
    private HttpResponseListener getResponseListener(final boolean fromBeginSession) {
        return new HttpResponseListener() {
            @Override
            public void onSuccess(String responseString) {
                LogUtils.aTag("????????????", responseString);
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
                String succeed = HttpParser.getSucceed(responseString);
                if ("true".equals(succeed)) {
                    try {
                        JSONObject jsonObject = new JSONObject(responseString);
                        JSONObject config = jsonObject.getJSONObject("Config");
                        if (config.has("systemMsgLogo")) {
                            spData.edit().putString(Constants.SYSTEMMSGLOGO, config.getString("systemMsgLogo")).apply();
                            String string = spData.getString(Constants.SYSTEMMSGLOGO, "");
                            LogUtils.dTag("systemMsgLogo=", string);

                        }

                        //?????????????????????
                        IMChatManager.getInstance().setIsInvestigateOn(config.getBoolean("webchat_csr"));
                        //???????????????????????????
                        boolean showTransferBtn = config.getBoolean("showTransferBtn");

                        IMChatManager.getInstance().setIsShowTransferBtn(showTransferBtn);
                        LogUtils.dTag("handleMessage==", "getResponseListener====showTransferBtn===" + showTransferBtn);
                        chat_tv_convert.setVisibility(fromBeginSession && isRobot && showTransferBtn ? View.VISIBLE : View.GONE);
                        if (jsonObject.has("bottomList")) {//???????????????;??????????????????bottomlist??????
                            IMChatManager.getInstance().setIsShowBottomList(true);
                            JSONArray list = jsonObject.getJSONArray("bottomList");
                            IMChatManager.getInstance().setBottomList(list);
                            rvTagLabel.setVisibility(View.VISIBLE);
                            if (list != null) {
                                try {
                                    JSONArray bottomList = IMChatManager.getInstance().getBottomList();
                                    for (int j = 0; j < bottomList.length(); j++) {
                                        JSONObject jb = bottomList.getJSONObject(j);
                                        FlowBean flowBean = new FlowBean();
                                        flowBean.setButton(jb.getString("button"));
                                        flowBean.setText(jb.getString("text"));
                                        flowBeanList.add(flowBean);
                                    }

                                    tagLabeAdapter.refreshData(flowBeanList);
                                } catch (Exception e) {
                                    rvTagLabel.setVisibility(View.GONE);
                                }
                            }

                        } else {
                            rvTagLabel.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                    }
                    ToastUtils.showShort(ChatActivity.this, getString(R.string.ykf_chatbegin_fail));
                    finish();
//                    chat_tv_convert.setVisibility(View.GONE);
//                    rvTagLabel.setVisibility(View.GONE);
//                    showOffLineDialog();
//                    if (fromBeginSession) {
//                        IMChatManager.getInstance().setIsShowBottomList(false);
//                    }
                }
            }

            @Override
            public void onFailed() {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
                ToastUtils.showShort(ChatActivity.this, getString(R.string.ykf_chatbegin_fail));
                finish();
//                chat_tv_convert.setVisibility(View.GONE);
//                rvTagLabel.setVisibility(View.GONE);
//                showOffLineDialog();
//                if (fromBeginSession) {
//                    IMChatManager.getInstance().setIsShowBottomList(false);
//                }

            }
        };
    }

    private void showOffLineDialog() {

        if (type.equals("schedule")) {
            return;
        }
        GlobalSet globalSet = GlobalSetDao.getInstance().getGlobalSet();

        if (null != globalSet) {
            if (isRobot) {
                bar_bottom.setVisibility(View.VISIBLE);
            } else {
                bar_bottom.setVisibility(View.GONE);
            }

            if ("1".equals(NullUtil.checkNull(globalSet.isLeaveMsg))) {
                Intent intent = new Intent(ChatActivity.this, OfflineMessageActicity.class);
                intent.putExtra("PeerId", peerId);
                intent.putExtra("leavemsgTip", NullUtil.checkNull(globalSet.leavemsgTip));
                intent.putExtra("inviteLeavemsgTip", NullUtil.checkNull(globalSet.inviteLeavemsgTip));
                startActivity(intent);
                finish();
            } else {
                try {
                    new AlertDialog.Builder(this).setTitle(R.string.warm_prompt)
                            .setMessage(NullUtil.checkNull(globalSet.msg))
                            .setPositiveButton(R.string.iknow, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    IMChatManager.getInstance().quitSDk();
                                    finish();
                                }
                            })
                            .setCancelable(false)
                            .create()
                            .show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ChatAdapter getChatAdapter() {
        return chatAdapter;
    }

    public void resendMsg(FromToMessage msg, int position) {
        if (IMChatManager.getInstance().isFinishWhenReConnect) {
            startReStartDialog();
        } else {
            resetBreakTimer();
            IMChat.getInstance().reSendMessage(msg, new ChatListener() {
                @Override
                public void onSuccess() {
                    updateMessage();
                }

                @Override
                public void onFailed() {
                    updateMessage();
                }

                @Override
                public void onProgress(int progress) {
                    updateMessage();
                }
            });
        }

    }


    /**
     * ?????????????????????????????????????????????
     * {@link ChatListClickListener}
     *
     * @param msg
     * @param type ????????????FromToMessage.MSG_TYPE_CARDINFO
     *             ????????????FromToMessage.MSG_TYPE_NEW_CARD_INFO
     */
    public void sendCardMsg(FromToMessage msg, String type) {
        FromToMessage fromToMessage = new FromToMessage();
        fromToMessage.userType = "0";
        fromToMessage.message = "";
        fromToMessage.msgType = type;
        fromToMessage.when = System.currentTimeMillis();
        fromToMessage.sessionId = IMChat.getInstance().getSessionId();
        fromToMessage.tonotify = IMChat.getInstance().get_id();
        fromToMessage.type = "User";
        fromToMessage.from = IMChat.getInstance().get_id();
        if (msg.cardInfo != null) {
            fromToMessage.cardInfo = msg.cardInfo;
        }
        if (msg.newCardInfo != null) {
            fromToMessage.newCardInfo = msg.newCardInfo;
        }
        sendSingleMessage(fromToMessage);

    }

    /**
     * ?????????????????????????????????
     *
     * @param fromToMessage
     */
    private void sendSingleMessage(FromToMessage fromToMessage) {
        //????????????
        descFromToMessage.add(fromToMessage);
        chatAdapter.notifyDataSetChanged();
        mChatList.setSelection(descFromToMessage.size());
        mChatInput.setText("");

        resetBreakTimer();

        //????????????
        IMChat.getInstance().sendMessage(fromToMessage, new ChatListener() {
            @Override
            public void onSuccess() {
                //??????????????????
                updateMessage();
            }

            @Override
            public void onFailed() {
                //??????????????????
                updateMessage();
            }

            @Override
            public void onProgress(int progress) {

            }
        });
    }

    private void getIsGoSchedule() {
        IMChatManager.getInstance().getWebchatScheduleConfig(new GetGlobleConfigListen() {
            @Override
            public void getSchedule(ScheduleConfig sc) {
                // loadingDialog.dismiss();
                LogUtils.aTag("MainActivity", "??????");
                if (!sc.getScheduleId().equals("") && !sc.getProcessId().equals("") && sc.getEntranceNode() != null && sc.getLeavemsgNodes() != null) {
                    if (sc.getEntranceNode().getEntrances().size() > 0) {
                        if (sc.getEntranceNode().getEntrances().size() == 1) {
                            ScheduleConfig.EntranceNodeBean.EntrancesBean bean = sc.getEntranceNode().getEntrances().get(0);
                            // TODO: 2019-12-24 ?????????Builder??????
                            new ChatActivity.Builder()
                                    .setType(Constants.TYPE_SCHEDULE)
                                    .setScheduleId(scheduleId)
                                    .setProcessId(processId)
                                    .setCurrentNodeId(bean.getProcessTo())
                                    .setProcessType(bean.getProcessType())
                                    .setEntranceId(bean.get_id())
                                    .build(ChatActivity.this);
//                            ChatActivity.startActivity(ChatActivity.this, Constants.TYPE_SCHEDULE, sc.getScheduleId(), sc.getProcessId(), bean.getProcessTo(), bean.getProcessType(), bean.get_id());
                        } else {
                            startScheduleDialog(sc.getEntranceNode().getEntrances(), sc.getScheduleId(), sc.getProcessId());
                        }

                    } else {
                        ToastUtils.showShort(ChatActivity.this, R.string.sorryconfigurationiswrong);
                    }
                } else {
                    ToastUtils.showShort(ChatActivity.this, R.string.sorryconfigurationiswrong);
                }
            }

            @Override
            public void getPeers() {
                LogUtils.aTag("start", "?????????");
                startChatActivityForPeer();
            }
        });
    }

    private void startScheduleDialog(final List<ScheduleConfig.EntranceNodeBean.EntrancesBean> entrances, final String scheduleId, final String processId) {
        final String[] items = new String[entrances.size()];
        for (int i = 0; i < entrances.size(); i++) {
            items[i] = entrances.get(i).getName();
        }

        AlertDialog dialog = new AlertDialog.Builder(ChatActivity.this)
                .setTitle(getString(R.string.ykf_select_scu))
                // ???????????????????????????????????????????????????????????????builder.setMessage()?????????????????????????????????
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ScheduleConfig.EntranceNodeBean.EntrancesBean bean = entrances.get(which);
                        LogUtils.aTag("???????????????", bean.getName());
                        // TODO: 2019-12-24 ?????????Builder??????
                        new ChatActivity.Builder()
                                .setType(Constants.TYPE_SCHEDULE)
                                .setScheduleId(scheduleId)
                                .setProcessId(processId)
                                .setCurrentNodeId(bean.getProcessTo())
                                .setProcessType(bean.getProcessType())
                                .setEntranceId(bean.get_id())
                                .build(ChatActivity.this);
//                        ChatActivity.startActivity(ChatActivity.this, Constants.TYPE_SCHEDULE, scheduleId, processId, bean.getProcessTo(), bean.getProcessType(), bean.get_id());

                    }
                }).create();
        dialog.show();
    }

    private void startChatActivityForPeer() {
        IMChatManager.getInstance().getPeers(new GetPeersListener() {
            @Override
            public void onSuccess(List<Peer> peers) {

                if (peers.size() > 1) {
                    startPeersDialog(peers, IMChatManager.getInstance().cardInfo);
                } else if (peers.size() == 1) {
                    // TODO: 2019-12-24 ?????????Builder??????
                    new ChatActivity.Builder()
                            .setType(Constants.TYPE_PEER)
                            .setPeerId(peers.get(0).getId())
                            .setCardInfo(IMChatManager.getInstance().cardInfo)
                            .setNewCardInfo(IMChatManager.getInstance().newCardInfo)
                            .build(ChatActivity.this);
//                    ChatActivity.startActivity(ChatActivity.this, Constants.TYPE_PEER, peers.get(0).getId(), IMChatManager.getInstance().cardInfo);
                } else {
                    ToastUtils.showShort(ChatActivity.this, R.string.peer_no_number);
                }
                // loadingDialog.dismiss();
            }

            @Override
            public void onFailed() {
                // loadingDialog.dismiss();
            }
        });
    }

    public void startPeersDialog(final List<Peer> peers, final CardInfo mCardInfo) {
        final String[] items = new String[peers.size()];
        for (int i = 0; i < peers.size(); i++) {
            items[i] = peers.get(i).getName();
        }
        AlertDialog builder = new AlertDialog.Builder(ChatActivity.this)
                .setTitle(getString(R.string.ykf_select_peer))
                // ???????????????????????????????????????????????????????????????builder.setMessage()?????????????????????????????????
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Peer peer = peers.get(which);
                        LogUtils.aTag("??????????????????", peer.getName());
                        // TODO: 2019-12-24 ?????????Builder??????
                        new ChatActivity.Builder()
                                .setType(Constants.TYPE_PEER)
                                .setPeerId(peer.getId())
                                .setCardInfo(mCardInfo)
                                .setNewCardInfo(IMChatManager.getInstance().newCardInfo)
                                .build(ChatActivity.this);
//                        ChatActivity.startActivity(ChatActivity.this, Constants.TYPE_PEER, peer.getId(), mCardInfo);
                    }
                }).create();
        builder.show();
    }

    public void startReStartDialog() {
        //??????????????????
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setTitle(getString(R.string.ykf_chatfinish_reopen))
                .addSheetItem(
//                        this.getResources().getString(R.string.restartchat),
                        getString(R.string.ykf_chatbegin),
                        ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                getIsGoSchedule();
                            }
                        })
                .addSheetItem(
//                        this.getResources().getString(R.string.exitchat),
                        getString(R.string.back),
                        ActionSheetDialog.SheetItemColor.Blue, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                IMChatManager.getInstance().quitSDk();
                                finish();
                            }
                        }

                ).show();
    }

    public void startReStartDialog2() {
        singleButtonDialog = builder.setMessage("")
                .setMessageDetail(getString(R.string.ykf_nologin_timeout))
                .setSingleButton(getString(R.string.ykf_chatbegin_reconnect), 0xFF459ae9, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        singleButtonDialog.dismiss();
                        IMChatManager.getInstance().quitSDk();
                        finish();
                    }
                }).createSingleButtonDialog();
        if (!isFinishing())
            singleButtonDialog.show();
    }

    public void startReStartDialog3() {
        singleButtonDialog = builder.setMessage("")
                .setMessageDetail(getString(R.string.ykf_nonetwork_error))
                .setSingleButton(getString(R.string.back), 0xFF459ae9, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        singleButtonDialog.dismiss();
                        IMChatManager.getInstance().quitSDk();
                        finish();
                    }
                }).createSingleButtonDialog();
        singleButtonDialog.show();
    }

    public ChatListView getChatListView() {
        return mChatList;
    }


    @Override
    public void onRecordFinished(float mTime, String filePath, String pcmFilePath) {
        if (!FileUtils.isExists(filePath)) {
            ToastUtils.showShort(ChatActivity.this, getString(R.string.ykf_recording_error));
            return;
        }
        //???????????????????????????
        FromToMessage fromToMessage = IMMessage.createAudioMessage(mTime, filePath, "");
        descFromToMessage.add(fromToMessage);
        chatAdapter.notifyDataSetChanged();
        mChatList.setSelection(descFromToMessage.size());
        sendVoiceMsg("", fromToMessage);
    }

    /**
     * ??????????????????
     *
     * @param voiceText
     */
    private void sendVoiceMsg(String voiceText, final FromToMessage fromToMessage) {
        fromToMessage.voiceText = voiceText;
        resetBreakTimer();
        IMChat.getInstance().sendMessage(fromToMessage, new ChatListener() {
            @Override
            public void onSuccess() {
                updateMessage();
            }

            @Override
            public void onFailed() {
                updateMessage();
            }

            @Override
            public void onProgress(int progress) {

            }
        });
//        if(!IMChatManager.getInstance().isManual) {
//            //????????? ???????????? ?????? ?????????
//            fromToMessage.isRobot = true;
//            fromToMessage._id = UUID.randomUUID().toString();
//            fromToMessage.accessId = InfoDao.getInstance().getAccessId();
//            fromToMessage.userId = InfoDao.getInstance().getUserId();
//            fromToMessage.peedId = InfoDao.getInstance().getPeedId();
//        }
//
//            IMChat.getInstance().sendMessage(fromToMessage, new ChatListener() {
//                @Override
//                public void onSuccess() {
////                    ToastUtils.showShort("??????????????????");
//                    if(!IMChatManager.getInstance().isManual) {
//                        //????????? ???????????? ?????? ?????????
//                        getVoiceToText(fromToMessage);
//                    }else{
//                        updateMessage();
//                    }
//                }
//
//                @Override
//                public void onFailed() {
//                    updateMessage();
//                }
//
//                @Override
//                public void onProgress(int progress) {
//
//                }
//            });
//

    }

    /**
     * ???????????????????????????-??????????????????
     * ????????????????????????
     *
     * @param target ??????
     */
    public void handleOnClickOfLogisticsShop(String target) {
        Log.e("ChatActivity", "==handleOnClickOfLogisticsShop==" + target);
//        Intent forumIntent = new Intent(this, MoorWebCenter.class);
//        forumIntent.putExtra("OpenUrl", target);
//        forumIntent.putExtra("titleName", "??????");
//        startActivity(forumIntent);


        Uri uri = Uri.parse(target);
        Set<String> args = uri.getQueryParameterNames();
        String good_id = uri.getQueryParameter("good_id");
        String search_attr = uri.getQueryParameter("search_attr");

        Log.e("????????????===","==ci====args========="+args);
        Log.e("????????????===","==ci====good_id========="+good_id);
        Log.e("????????????===","==ci====search_attr========="+search_attr);

        Intent intent1 = new Intent();
        intent1.setAction( "cc.android.myaction.leo");
        intent1.addCategory(Intent.CATEGORY_DEFAULT);
        intent1.putExtra("good_id",good_id);
        intent1.putExtra("search_attr",search_attr);
        startActivity(intent1);




    }

    /**
     * ???????????????????????????-item????????????
     *
     * @param
     */
    public void handleOnClickOfLogisticsItem(String _id, String current, OrderInfoBean orderInfoBean) {
        mHashSet.add(_id);
        IMChatManager.getInstance().updateOrderInfo(_id, "1");
        if (moreOrderInfoDialog != null) {
            if (moreOrderInfoDialog.isShowing()) {
                moreOrderInfoDialog.dismiss();
            }
        }
      /*  "msg_task":{
            "current":"AI-4@3de59031ac5b4d40b309ba0325accd10",
            "item":{
                "target":"next",
                "params":{"orderNo":"3"}
            }
        }*/

        FromToMessage fromToMessage = new FromToMessage();
        fromToMessage.userType = "0";
        fromToMessage.message = "??????????????????";
        fromToMessage.msgType = FromToMessage.MSG_TYPE_LOGISTICS_INFO_LIST;
        fromToMessage.when = System.currentTimeMillis();
        fromToMessage.sessionId = IMChat.getInstance().getSessionId();
        fromToMessage.tonotify = IMChat.getInstance().get_id();
        fromToMessage.type = "User";
        fromToMessage.from = IMChat.getInstance().get_id();
        if (orderInfoBean != null) {
            fromToMessage.newCardInfo = new Gson().toJson(orderInfoBean);
        }
        MsgTaskBean bean = new MsgTaskBean()
                .setCurrent(current)
                .setItem(new MsgTaskItemBean()
                        .setTarget("next")
                        .setParams(new OrderInfoParams()
                                .setOrderNo(orderInfoBean.getParams().getOrderNo())));

        fromToMessage.msgTask = new Gson().toJson(bean);
        LogUtils.aTag("MsgTaskBean==", new Gson().toJson(bean));
        sendSingleMessage(fromToMessage);

    }

    /**
     * ???????????????????????????-????????????????????????
     */
    public void handleOnClickOfLogisticsMore(String current, final String _id) {
        if (loadingDialog != null) {
            loadingDialog.show(this.getSupportFragmentManager(), "");
        }
  /* "msg_task":{
            "current":"AI4@3de59031ac5b4d40b309ba0325accd10",
                    "item":{
                "target":"self",
                        "params":"",
                        "page":"all"
            }
        }*/
        MsgTaskBean bean = new MsgTaskBean()
                .setCurrent(current)
                .setItem(new MsgTaskItemBean()
                        .setTarget("self")
                        .setPage("all"));

        String msgTask = new Gson().toJson(bean);
        HttpManager.getMoreOrderInfo(msgTask, new HttpResponseListener() {
            @Override
            public void onSuccess(String responseStr) {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
                String succeed = HttpParser.getSucceed(responseStr);
                LogUtils.aTag("??????????????????===???", responseStr);
                if ("true".equals(succeed)) {
                    try {
                        JSONObject o = new JSONObject(responseStr);
                        String message = o.getString("msgTask");
                        if (NullUtil.checkNULL(message)) {
                            Type token = new TypeToken<OrderBaseBean>() {
                            }.getType();
                            final OrderBaseBean orderBaseBean = new Gson().fromJson(message, token);
                            if (orderBaseBean.getData() != null && orderBaseBean.getData().getList() != null) {
                                moreOrderInfoDialog = new BottomSheetLogisticsInfoDialog(orderBaseBean.getData().getList(), orderBaseBean.getCurrent(), _id);
                                moreOrderInfoDialog.show(getSupportFragmentManager(), "");
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtils.showShort(ChatActivity.this, getString(R.string.ykf_loadmore_fail));
                }
            }

            @Override
            public void onFailed() {
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
                ToastUtils.showShort(ChatActivity.this, getString(R.string.ykf_loadmore_fail));
            }
        });
    }

    /**
     * ???????????????????????????-????????????????????????????????????
     */
    public void handleOnClickOfLogisticsProgressMore(FromToMessage message) {

        // TODO: 2019-12-31  ????????????????????????????????????
        if (message.msgTask != null && !"".equals(message.msgTask)) {
            Type token = new TypeToken<OrderBaseBean>() {
            }.getType();
            final OrderBaseBean orderBaseBean = new Gson().fromJson(message.msgTask, token);
            if (orderBaseBean.getData() != null && orderBaseBean.getData().getList() != null) {
                BottomSheetLogisticsProgressDialog dialog = new BottomSheetLogisticsProgressDialog(orderBaseBean.getData().getList());
                dialog.show(getSupportFragmentManager(), "");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PickUtils.PermissionUtils.onRequestPermissionsResult(this, 0x11, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, grantResults);
        PickUtils.PermissionUtils.onRequestPermissionsResult(this, 0x5555, new String[]{Manifest.permission.CAMERA}, grantResults);
    }

    public void onEventMainThread(UnAssignEvent unAssignEvent) {
        chat_tv_convert.setVisibility(View.GONE);
    }

    public void onEventMainThread(ReSendMessage reSendMessage) {
        updateMessage();
    }


    public void onEventMainThread(QuestionEvent questionEvent) {
        updateMessage();
    }

    /**
     * ????????????????????????????????????tcp???????????????????????????????????????????????????
     */
    @Override
    protected void onResume() {
        super.onResume();
        isFront = true;
        onEventMainThread(new MsgEvent());//???????????????????????????????????????????????????
        LogUtils.aTag("chatActivity", "??????OnResume???" + TcpManager.getInstance(this).getTcpStatus());
        if (MoorUtils.isNetWorkConnected(this)) {
            LogUtils.aTag("onresume", "???????????????ok");

            if (IMChatManager.USE_TCP) {
                if (isServiceRunning(this, "com.moor.imkf.tcpservice.service.IMService")) {
                    if (TcpManager.TcpStatus.BREAK.equals(TcpManager.getInstance(this).getTcpStatus())) {
                        EventBus.getDefault().post(new TcpBreakEvent());//??????
                    }
                } else {
                    //????????????
                    startReStartDialog2();
                }
            } else {
                if (isServiceRunning(this, "com.moor.imkf.websocket.SocketService")) {
                    if (!WebSocketHandler.getDefault().isConnect()) {
                        EventBus.getDefault().post(new TcpBreakEvent());//??????
                    }
                } else {
                    //????????????
                    startReStartDialog2();
                }

            }


        } else {
            startReStartDialog3();
            LogUtils.aTag("onresume", "???????????????not ok");
        }
    }


    /**
     * ???????????????????????????
     */
    public static boolean isServiceRunning(Context context, String ServiceName) {
        boolean isWork = false;
        if (TextUtils.isEmpty(ServiceName)) {
            isWork = false;
            LogUtils.aTag("runService", "?????????????????????");
        }
        ActivityManager myManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningService = myManager
                .getRunningServices(Integer.MAX_VALUE);
        if (runningService.size() <= 0) {
//            isWork = false;
            LogUtils.aTag("runService", "????????????0");
        }
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().equals(ServiceName)) {
                isWork = true;
                LogUtils.aTag("runService", "???????????????" + isWork);
                break;
            }
        }
        return isWork;
    }


    /**
     * ????????????
     */
    private class GetLianXiangDataResponeHandler implements HttpResponseListener {

        @Override
        public void onFailed() {
            ll_hintView.setVisibility(View.GONE);
        }

        @Override
        public void onSuccess(String responseString) {
            System.out.println(responseString);
            String succeed = HttpParser.getSucceed(responseString);
            if ("true".equals(succeed)) {
                ll_hintView.removeAllViews();
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    final JSONArray questions = jsonObject.getJSONArray("questions");
                    if (questions.length() > 0) {
                        //????????????????????????
                        ll_hintView.setVisibility(View.VISIBLE);
                        for (int j = 0; j < questions.length(); j++) {
                            //????????????????????????View
                            View view = View.inflate(ChatActivity.this, R.layout.item_hint_view, null);
                            TextView textView = (TextView) view.findViewById(R.id.tv_hintView);
                            textView.setText(questions.getString(j));
                            final int position = j;
                            textView.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        // ?????????????????????????????????????????????????????????

                                        //???TCP?????? WS
                                        if (IMChatManager.USE_TCP) {
                                            if (!MoorUtils.isNetWorkConnected(IMChatManager.getInstance().getAppContext()) &&
                                                    TcpManager.TcpStatus.BREAK.equals(TcpManager.getInstance(IMChatManager.getInstance().getAppContext()).getTcpStatus())) {
                                                Toast.makeText(getApplicationContext(), getString(R.string.ykf_not_netwokr_error), Toast.LENGTH_SHORT).show();
                                                LogUtils.aTag("???????????????break");
                                                TcpManager.getInstance(IMChatManager.getInstance().getAppContext()).setTcpStatus(TcpManager.TcpStatus.NONET);
                                                startReStartDialog3();
                                                return;
                                            }
                                        } else {
                                            if (!MoorUtils.isNetWorkConnected(IMChatManager.getInstance().getAppContext()) &&
                                                    !WebSocketHandler.getDefault().isConnect()) {
                                                Toast.makeText(getApplicationContext(), getString(R.string.ykf_not_netwokr_error), Toast.LENGTH_SHORT).show();
                                                LogUtils.aTag("???????????????break");
                                                startReStartDialog3();
                                                return;
                                            }
                                        }

                                        if (IMChatManager.getInstance().isFinishWhenReConnect) {
                                            startReStartDialog();//?????????????????????????????????
//            }
                                        } else {
                                            sendTextMsg(questions.getString(position));
                                            mChatInput.setText("");
                                            ll_hintView.setVisibility(View.GONE);
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            ll_hintView.addView(view);
                        }


                    } else {
                        ll_hintView.setVisibility(View.GONE);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                ToastUtils.showShort(responseString);
            } else {
                //???????????????????????????
                ll_hintView.setVisibility(View.GONE);
            }
        }
    }

    //???????????????
    public void getVoiceToText(final FromToMessage fromToMessage) {
        //??????????????????,????????????????????????
        mCountDownTimer = new CountDownTimer(1000 * 60 * 3, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                VoiceToTextEvent event = new VoiceToTextEvent();
                event.id = fromToMessage._id;
                event.status_code = VoiceToTextEvent.STATUS_TIMEOUT;
                onEventMainThread(event);
            }
        }.start();
        HttpManager.sendVoiceToText(fromToMessage._id, fromToMessage.message, fromToMessage.when, new HttpResponseListener() {
            @Override
            public void onSuccess(String responseStr) {
                Log.e("???????????????", responseStr);
                try {
                    JSONObject jsonObject = new JSONObject(responseStr);
                    if (!jsonObject.optBoolean("Succeed")) {
                        stopTimer();
                        //???????????????????????????
                        if (fromToMessage.isRobot) {
                            sendVoiceAutoText(fromToMessage, "", false);
                        } else {
                            fromToMessage.isCacheShowVtoT = false;//?????????????????? ????????????????????????
                            MessageDao.getInstance().updateMsgToDao(fromToMessage);
                            chatAdapter.notifyDataSetChanged();
                            ToastUtils.showLong(ChatActivity.this, getText(R.string.voice_to_text_error) + ":" + jsonObject.optString("Message"));
                        }
                    } else {
                        //????????????????????????messageId  ????????????????????????????????? ??????????????????TCP
                        if (!TextUtils.isEmpty(jsonObject.optString("messageId"))) {
                            String text = jsonObject.optString("voiceMessage");
                            String id = jsonObject.optString("messageId");
                            VoiceToTextEvent event = new VoiceToTextEvent();
                            event.id = id;
                            event.toText = text;
                            event.status_code = VoiceToTextEvent.STATUS_OK;
                            onEventMainThread(event);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed() {
                stopTimer();
                //???????????????????????????
                fromToMessage.isCacheShowVtoT = false;//?????????????????? ????????????????????????
                MessageDao.getInstance().updateMsgToDao(fromToMessage);
                chatAdapter.notifyDataSetChanged();
                ToastUtils.showLong(ChatActivity.this, getText(R.string.voice_to_text_error));
            }
        });
    }

    //TcpMessageHandler ??? ??????????????????????????????EventBus ??????
    public void onEventMainThread(VoiceToTextEvent voiceToTextEvent) {
        stopTimer();
        if (voiceToTextEvent != null) {
            if (!"VoiceToTextEvent_nullID".equals(voiceToTextEvent.id)) {
                FromToMessage fromToMessage = MessageDao.getInstance().getMessageById(voiceToTextEvent.id);
                if (fromToMessage.isRobot) {
                    //???????????? ????????????????????????????????????text?????????????????????
                    if (VoiceToTextEvent.STATUS_OK.equals(voiceToTextEvent.status_code)) {
                        //??????
                        sendVoiceAutoText(fromToMessage, voiceToTextEvent.toText, true);
                    } else {
                        //??????
                        sendVoiceAutoText(fromToMessage, "", false);
                    }

                } else {
                    if (VoiceToTextEvent.STATUS_OK.equals(voiceToTextEvent.status_code)) {
                        fromToMessage.voiceToText = voiceToTextEvent.toText;
                        fromToMessage.isShowVtoT = true;
                    } else if (VoiceToTextEvent.STATUS_FAIL.equals(voiceToTextEvent.status_code)) {
                        ToastUtils.showLong(ChatActivity.this, getText(R.string.voice_to_text_error) + getString(R.string.ykf_autotext_fail_reclick));
                    } else if (VoiceToTextEvent.STATUS_TIMEOUT.equals(voiceToTextEvent.status_code)) {
                        ToastUtils.showLong(ChatActivity.this, getText(R.string.voice_to_text_error) + getString(R.string.ykf_autotext_fail_reclick));
                    } else if (VoiceToTextEvent.STATUS_UNDEFINED.equals(voiceToTextEvent.status_code)) {
                        ToastUtils.showLong(ChatActivity.this, getText(R.string.voice_to_text_error) + getString(R.string.ykf_autotext_fail_nocheck));
                    } else if (VoiceToTextEvent.STATUS_TOLONG.equals(voiceToTextEvent.status_code)) {
                        ToastUtils.showLong(ChatActivity.this, getString(R.string.ykf_autotext_fail_solong));
                    }
                    fromToMessage.isCacheShowVtoT = false;//?????????????????? ????????????????????????
                    MessageDao.getInstance().updateMsgToDao(fromToMessage);
                    //??????adapter?????????
                    for (int i = 0; i < descFromToMessage.size(); i++) {
                        if (!TextUtils.isEmpty(descFromToMessage.get(i)._id)) {
                            if (descFromToMessage.get(i)._id.equals(fromToMessage._id)) {
                                descFromToMessage.set(i, fromToMessage);
                            }
                        }
                    }
                }

                chatAdapter.notifyDataSetChanged();

            } else {
                ToastUtils.showShort(ChatActivity.this, getText(R.string.voice_to_text_error) + ":VoiceToTextEvent_nullID");
            }

        }
    }


    //???????????????????????????????????? ????????????//!IMChatManager.getInstance().isManual false????????????
    private void sendVoiceAutoText(FromToMessage message, String str, boolean success) {
        if (success) {
            message.message = str;
            message.msgType = FromToMessage.MSG_TYPE_TEXT;
            message.isRobot = false;
        } else {
            message.msgType = FromToMessage.MSG_TYPE_AUDIO;
            message.isRobot = false;
        }
        //????????????
        IMChat.getInstance().sendMessage(message, new ChatListener() {
            @Override
            public void onSuccess() {
                //??????????????????
                updateMessage();
            }

            @Override
            public void onFailed() {
                //??????????????????
                updateMessage();
            }

            @Override
            public void onProgress(int progress) {

            }
        });

    }

    private void stopTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }


    //?????????????????????
    public void onEventMainThread(MsgunReadToReadEvent event) {
        MessageDao.getInstance().updateUnReadToRead();
        for (int i = 0; i < descFromToMessage.size(); i++) {
            if ("false".equals(descFromToMessage.get(i).dealCustomerMsg)) {
                descFromToMessage.get(i).dealCustomerMsg = "true";
            }
        }
        chatAdapter.notifyDataSetChanged();
    }

    //???????????????????????????????????????????????????
    public void onEventMainThread(MsgEvent msgEvent) {
        if (isFront) {
            HttpManager.sdkDealImMsg(new HttpResponseListener() {
                @Override
                public void onSuccess(String responseStr) {
                }

                @Override
                public void onFailed() {
                }
            });
        }

    }


    //??????????????????
    public void onEventMainThread(TransferAgent agent) {
        //agent.peerid ?????????????????????????????????????????????xbot??????????????????????????????

        //???????????????
        IMChatManager.getInstance().convertManual(agent.peerid, new OnConvertManualListener() {
            @Override
            public void onLine() {
                if (!type.equals("schedule")) {
                    //???????????????,?????????????????????
                    chat_tv_convert.setVisibility(View.GONE);
                    bar_bottom.setVisibility(View.VISIBLE);
                    mOtherName.setText(R.string.wait_link);
                    titleName = getString(R.string.wait_link);
                    Toast.makeText(getApplicationContext(), R.string.topeoplesucceed, Toast.LENGTH_SHORT).show();
                    IMChatManager.getInstance().setIsShowBottomList(false);
                    rvTagLabel.setVisibility(View.GONE);
                }
            }

            @Override
            public void offLine() {
                //????????????????????????
                if (!type.equals("schedule")) {

                    showOffLineDialog();
                    IMChatManager.getInstance().setIsShowBottomList(false);
                    rvTagLabel.setVisibility(View.GONE);
                }

            }
        });

    }


    /**
     * Builder??????????????????
     */
    public static final class Builder {
        private String type;
        private String scheduleId;
        private String processId;
        private String currentNodeId;
        private String processType;
        private String entranceId;
        private String PeerId;
        private CardInfo cardInfo;
        private NewCardInfo newCardInfo;

        public Builder setPeerId(String peerId) {
            PeerId = peerId;
            if (PeerId != null && !"".equals(PeerId)) {
                InfoDao.getInstance().updataPeedID(peerId);
            }

            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setScheduleId(String scheduleId) {
            this.scheduleId = scheduleId;
            return this;
        }

        public Builder setProcessId(String processId) {
            this.processId = processId;
            return this;
        }

        public Builder setCurrentNodeId(String currentNodeId) {
            this.currentNodeId = currentNodeId;
            return this;
        }

        public Builder setProcessType(String processType) {
            this.processType = processType;
            return this;
        }

        public Builder setEntranceId(String entranceId) {
            this.entranceId = entranceId;
            return this;
        }

        public Builder setCardInfo(CardInfo cardInfo) {
            this.cardInfo = cardInfo;
            //??????????????? ??????????????????
            if (cardInfo != null) {
                FromToMessage cardMsg = new FromToMessage();
                cardMsg.msgType = FromToMessage.MSG_TYPE_CARD;
                cardMsg.cardInfo = JsonBuild.getCardInfo(cardInfo);
                cardMsg.userType = "0";
                cardMsg.when = System.currentTimeMillis();
                LogUtils.aTag("cardinfo==", JsonBuild.getCardInfo(cardInfo));
                MessageDao.getInstance().insertSendMsgsToDao(cardMsg);
            }
            return this;
        }

        public Builder setNewCardInfo(NewCardInfo newCardInfo) {
            this.newCardInfo = newCardInfo;
            //???????????????????????? ??????????????????
            if (newCardInfo != null) {
                FromToMessage cardMsg = new FromToMessage();
                cardMsg.msgType = FromToMessage.MSG_TYPE_NEW_CARD;
//                cardMsg.newCardInfo = JsonBuild.getOrderInfo(newCardInfo);
                cardMsg.newCardInfo = new Gson().toJson(newCardInfo);
                cardMsg.userType = "0";
                cardMsg.when = System.currentTimeMillis();
                LogUtils.aTag("newCardInfo==", new Gson().toJson(newCardInfo));
                MessageDao.getInstance().insertSendMsgsToDao(cardMsg);
            }
            return this;
        }

        public Builder() {

        }

        public Intent build(Context mContex) {
            Intent intent = new Intent(mContex, ChatActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("scheduleId", scheduleId);
            intent.putExtra("processId", processId);
            intent.putExtra("currentNodeId", currentNodeId);
            intent.putExtra("processType", processType);
            intent.putExtra("entranceId", entranceId);
            intent.putExtra("PeerId", PeerId);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContex.startActivity(intent);
            return intent;
        }
    }


}
