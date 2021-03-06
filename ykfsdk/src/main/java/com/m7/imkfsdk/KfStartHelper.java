package com.m7.imkfsdk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.m7.imkfsdk.chat.ChatActivity;
import com.m7.imkfsdk.chat.LoadingFragmentDialog;
import com.m7.imkfsdk.constant.Constants;
import com.m7.imkfsdk.utils.FaceConversionUtil;
import com.m7.imkfsdk.utils.ToastUtils;
import com.moor.imkf.IMChatManager;
import com.moor.imkf.listener.GetGlobleConfigListen;
import com.moor.imkf.listener.GetPeersListener;
import com.moor.imkf.listener.InitListener;
import com.moor.imkf.model.entity.CardInfo;
import com.moor.imkf.model.entity.NewCardInfo;
import com.moor.imkf.model.entity.Peer;
import com.moor.imkf.model.entity.ScheduleConfig;
import com.moor.imkf.utils.LogUtils;
import com.moor.imkf.utils.MoorUtils;

import java.util.List;

/**
 * Created by pangw on 2018/7/9.
 */

public class KfStartHelper {
    private LoadingFragmentDialog loadingDialog;
    private CardInfo card;
    private AppCompatActivity mActivity;
    private Context context;
//    private String receiverAction = "com.m7.imkf.KEFU_NEW_MSG";
    private String receiverAction = "com.m7.imkfsdk.KEFU_NEW_MSG";


    private String accessId;
    private String userName;
    private String userId;
    private NewCardInfo newCardInfo;
    private SharedPreferences spData;
    private SharedPreferences.Editor edit;

    public void setCard(CardInfo card) {
        this.card = card;
        IMChatManager.getInstance().cardInfo = card;
    }

    public void setNewCardInfo(NewCardInfo newCardInfo) {
        this.newCardInfo = newCardInfo;
        IMChatManager.getInstance().newCardInfo = newCardInfo;
    }

    public KfStartHelper(AppCompatActivity activity) {
        mActivity = activity;
        context = activity.getApplicationContext();
        loadingDialog = new LoadingFragmentDialog();
        spData = context.getSharedPreferences("moordata", 0);
        edit = spData.edit();
        MoorUtils.init(activity.getApplication());
        initFaceUtils();
    }

    /**
     * ?????????????????? ???????????? ????????????????????? ??????
     */
    public void setChatActivityLeftText(String left_Text) {
        if (spData == null) {
            return;
        }
        if (!TextUtils.isEmpty(left_Text)) {
            edit.putString(Constants.CHATACTIVITYLEFTTEXT, left_Text).apply();
        }
        return;

    }


    /**
     * ??????OrderId???headUrl
     */

    public void setOrderHead(String orderId, String headUrl) {
        if (spData == null) {
            return;
        }
        edit.putString(Constants.ORDERID, orderId).apply();
        edit.putString(Constants.HEADURL, headUrl).apply();
        return;
    }

    /**
     * ???????????? emoji?????? ?????????
     */
    public void setChatActivityEmoji(boolean show) {
        if (spData == null) {
            return;
        }
        edit.putBoolean(Constants.CHATACTIVITYEMOJI, show).apply();
    }

    /**
     * ???????????????
     */
    public void initFaceUtils() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (FaceConversionUtil.getInstace().emojis == null || FaceConversionUtil.getInstace().emojis.size() == 0) {
                    com.m7.imkfsdk.utils.FaceConversionUtil.getInstace().getFileText(
                            context);
                }
            }
        }).start();
    }

    public void initSdkChat(String accessId, String userName,
                            String userId) {
        this.accessId = accessId;
        this.userName = userName;
        this.userId = userId;

        if (!MoorUtils.isNetWorkConnected(context)) {
            Toast.makeText(context, R.string.notnetwork, Toast.LENGTH_SHORT).show();
            return;
        }
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show(mActivity.getSupportFragmentManager(), "");
        startKFService();
    }

    /**
     * ?????????????????????????????????
     */
    private void getIsGoSchedule() {
        IMChatManager.getInstance().getWebchatScheduleConfig(new GetGlobleConfigListen() {
            @Override
            public void getSchedule(ScheduleConfig sc) {
                loadingDialog.dismiss();
                LogUtils.aTag("MainActivity", "??????");
                if (!sc.getScheduleId().equals("") &&
                        !sc.getProcessId().equals("") &&
                        sc.getEntranceNode() != null
                        && sc.getLeavemsgNodes() != null) {
                    if (sc.getEntranceNode().getEntrances().size() > 0) {
                        if (sc.getEntranceNode().getEntrances().size() == 1) {
                            ScheduleConfig.EntranceNodeBean.EntrancesBean bean = sc.getEntranceNode().getEntrances().get(0);
                            // TODO: 2019/12/24 ?????????Builder??????
                            new ChatActivity.Builder()
                                    .setType(Constants.TYPE_SCHEDULE)
                                    .setScheduleId(sc.getScheduleId())
                                    .setProcessId(sc.getProcessId())
                                    .setCurrentNodeId(bean.getProcessTo())
                                    .setProcessType(bean.getProcessType())
                                    .setEntranceId(bean.get_id())
                                    .setCardInfo(card)
                                    .setNewCardInfo(newCardInfo)
                                    .build(context);

//                            ChatActivity.startActivity(context, Constants.TYPE_SCHEDULE,
//                                    sc.getScheduleId(), sc.getProcessId(), bean.getProcessTo(),
//                                    bean.getProcessType(), bean.get_id(), card);
                        } else {
                            startScheduleDialog(sc.getEntranceNode().getEntrances(), sc.getScheduleId(), sc.getProcessId());
                        }

                    } else {
                        ToastUtils.showShort(context, R.string.sorryconfigurationiswrong);
                    }
                } else {
                    ToastUtils.showShort(context, R.string.sorryconfigurationiswrong);
                }
            }

            @Override
            public void getPeers() {
                LogUtils.aTag("start?????????", "?????????");
                startChatActivityForPeer();
            }
        });
    }

    private void startScheduleDialog(final List<ScheduleConfig.EntranceNodeBean.EntrancesBean> entrances, final String scheduleId, final String processId) {
        final String[] items = new String[entrances.size()];
        for (int i = 0; i < entrances.size(); i++) {
            items[i] = entrances.get(i).getName();
        }

        AlertDialog dialog = new AlertDialog.Builder(mActivity)
                .setTitle(context.getString(R.string.ykf_select_scu))
                .setCancelable(false)
                .setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IMChatManager.getInstance().quitSDk();
                    }
                })
                // ???????????????????????????????????????????????????????????????builder.setMessage()?????????????????????????????????
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ScheduleConfig.EntranceNodeBean.EntrancesBean bean = entrances.get(which);
                        LogUtils.aTag("???????????????", bean.getName());
                        new ChatActivity.Builder()
                                .setType(Constants.TYPE_SCHEDULE)
                                .setScheduleId(scheduleId)
                                .setProcessId(processId)
                                .setCurrentNodeId(bean.getProcessTo())
                                .setProcessType(bean.getProcessType())
                                .setEntranceId(bean.get_id())
                                .setCardInfo(card)
                                .setNewCardInfo(newCardInfo)
                                .build(context);
//                        ChatActivity.startActivity(context, Constants.TYPE_SCHEDULE, scheduleId, processId, bean.getProcessTo(), bean.getProcessType(), bean.get_id(), card);

                    }
                }).create();
        dialog.show();
    }

    /**
     * ???????????????ChatActivity
     */
    private void startChatActivityForPeer() {
        IMChatManager.getInstance().getPeers(new GetPeersListener() {
            @Override
            public void onSuccess(List<Peer> peers) {

                if (peers.size() > 1) {
                    startPeersDialog(peers, card);
                } else if (peers.size() == 1) {
                    // TODO: 2019-12-24 ?????????Builder??????
                    new ChatActivity.Builder()
                            .setType(Constants.TYPE_PEER)
                            .setPeerId(peers.get(0).getId())
                            .setCardInfo(card)
                            .setNewCardInfo(newCardInfo)
                            .build(context);

//                    ChatActivity.startActivity(context, Constants.TYPE_PEER, peers.get(0).getId(), card);
                } else {
                    ToastUtils.showShort(context, R.string.peer_no_number);
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailed() {
                loadingDialog.dismiss();
                ToastUtils.showShort(context, context.getString(R.string.ykf_nopeer));
            }
        });
    }


    private void startKFService() {

        new Thread() {
            @Override
            public void run() {
                IMChatManager.getInstance().setOnInitListener(new InitListener() {
                    @Override
                    public void oninitSuccess() {
                        getIsGoSchedule();
                        Log.d("MainActivity", "sdk???????????????");
                    }

                    @Override
                    public void onInitFailed() {
                        ToastUtils.showShort(context, R.string.sdkinitwrong);
                        loadingDialog.dismiss();
                        Log.d("MainActivity", "sdk???????????????, ??????????????????accessid");
                    }
                });
                IMChatManager.getInstance().init(context, receiverAction, accessId, userName, userId);
            }
        }.start();
    }

    public void startPeersDialog(final List<Peer> peers, final CardInfo mCardInfo) {
        final String[] items = new String[peers.size()];
        for (int i = 0; i < peers.size(); i++) {
            items[i] = peers.get(i).getName();
        }
        AlertDialog builder = new AlertDialog.Builder(mActivity)
                .setTitle(context.getString(R.string.ykf_select_peer))
                .setCancelable(false)
                .setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IMChatManager.getInstance().quitSDk();
                    }
                })
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
                                .setNewCardInfo(newCardInfo)
                                .build(context);
//                        ChatActivity.startActivity(context, Constants.TYPE_PEER, peer.getId(), mCardInfo);
                    }
                }).create();
        builder.show();
    }

    //??????log
    public void closeLog() {
        LogUtils.sLogSwitch = false;
    }

    //??????log
    public void openLog() {
        LogUtils.sLogSwitch = true;
    }

    /**
     * 0: ?????????userId ??????
     * 1: ?????????peedId??????????????? ?????? ??????????????????
     * 2: ?????????accessId ??????
     */
    public void setSaveMsgType(int type) {
        IMChatManager.getInstance().setSaveMsgType(type);
    }

    /**
     * ?????? receiverAction ???AndroidManifest????????????????????????action??????
     */
    public void setReceiverAction(String receiverAction) {
        this.receiverAction = receiverAction;
    }
}
