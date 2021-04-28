package com.dm.lib.core.permission;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.AppOpsManagerCompat;

import com.dm.lib.core.R;
import com.dm.lib.core.dialog.TipDialog;
import com.dm.lib.core.listener.SimpleCallback;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.List;

/**
 * @author Cuizhen
 * @date 2018/6/21-上午9:54
 */
public class PermissionHelper {

    private final Context mContext;
    private String[] mPermissions = null;

    private PermissionListener mListener = null;

    private IPermissionDialog mDialogRequestFirst = null;
    private IPermissionDialog mDialogRequestAgain = null;
    private IPermissionDialog mDialogGoSetting = null;

    private PermissionHelper(Context context) {
        this.mContext = context;
    }

    public static PermissionHelper with(Context context) {
        return new PermissionHelper(context);
    }

    public PermissionHelper dialogRequestFirst(IPermissionDialog dialog) {
        mDialogRequestFirst = dialog;
        return this;
    }

    public PermissionHelper dialogRequestAgain(IPermissionDialog dialog) {
        mDialogRequestAgain = dialog;
        return this;
    }

    public PermissionHelper dialogGoSetting(IPermissionDialog dialog) {
        mDialogGoSetting = dialog;
        return this;
    }

    public PermissionHelper permissions(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    public void request(@NonNull PermissionListener listener) {
        mListener = listener;
        if (AndPermission.hasPermissions(mContext, mPermissions)) {
            boolean isGranted = true;
            try {
                for (String permission : mPermissions) {
                    isGranted = checkPermission(permission);
                }
            } catch (Exception ignore) {
            }
            if (isGranted) {
                if (mListener != null) {
                    mListener.onSuccess();
                }
            } else {
                showGoSettingDialog();
            }
        } else {
            showRequestFirstDialog();
        }
    }

    private void startRequest() {
        AndPermission.with(mContext)
                .runtime()
                .permission(mPermissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (AndPermission.hasPermissions(mContext, getPermissions(data))) {
                            boolean isGranted = true;
                            try {
                                for (String permission : data) {
                                    isGranted = checkPermission(permission);
                                }
                            } catch (Exception ignore) {
                            }
                            if (isGranted) {
                                if (mListener != null) {
                                    mListener.onSuccess();
                                }
                            } else {
                                showGoSettingDialog();
                            }
                        } else {
                            showGoSettingDialog();
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(final List<String> data) {
                        if (AndPermission.hasAlwaysDeniedPermission(mContext, data)) {
                            showGoSettingDialog();
                        } else {
                            showRequestAgainDialog();
                        }
                    }
                })
                .rationale(new Rationale<List<String>>() {
                    @Override
                    public void showRationale(Context context, final List<String> data, final RequestExecutor executor) {
                        executor.execute();
                    }
                })
                .start();
    }

    private boolean checkPermission(@NonNull String permission) {
        String op = AppOpsManagerCompat.permissionToOp(permission);
        if (op == null) {
            return true;
        }
        return AppOpsManagerCompat.noteProxyOp(mContext, op, mContext.getPackageName()) == AppOpsManagerCompat.MODE_ALLOWED;
    }

    private String[] getPermissions(List<String> data) {
        return data.toArray(new String[]{});
    }

    private void showRequestFirstDialog() {
        getDialogRequestFirst().show(new IPermissionDialogCallback() {
            @Override
            public void onYes() {
                startRequest();
            }

            @Override
            public void onNo() {
                if (mListener != null) {
                    mListener.onFailed();
                }
            }
        });
    }

    private void showRequestAgainDialog() {
        getDialogRequestAgain().show(new IPermissionDialogCallback() {
            @Override
            public void onYes() {
                startRequest();
            }

            @Override
            public void onNo() {
                mListener.onFailed();
            }
        });
    }

    private void showGoSettingDialog() {
        getDialogGoSetting().show(new IPermissionDialogCallback() {
            @Override
            public void onYes() {
                AndPermission.with(mContext).runtime().setting().start();
            }

            @Override
            public void onNo() {
                mListener.onFailed();
            }
        });
    }

    public interface PermissionListener {
        void onSuccess();

        void onFailed();
    }

    private IPermissionDialog getDialogRequestFirst() {
        if (mDialogRequestFirst == null) {
            mDialogRequestFirst = new IPermissionDialog() {
                @Override
                public void show(IPermissionDialogCallback callback) {
                    TipDialog.with(mContext)
                            .title(R.string.permission_dialog_title)
                            .message(R.string.permission_dialog_msg_first)
                            .yesText(R.string.permission_dialog_btn_request)
                            .noText(R.string.permission_dialog_btn_cancel)
                            .cancelable(false)
                            .onYes(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    callback.onYes();
                                }
                            })
                            .onNo(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    callback.onNo();
                                }
                            })
                            .show();
                }

                @Override
                public void dismiss() {
                }
            };
        }
        return mDialogRequestFirst;
    }

    private IPermissionDialog getDialogRequestAgain() {
        if (mDialogRequestAgain == null) {
            mDialogRequestAgain = new IPermissionDialog() {
                @Override
                public void show(IPermissionDialogCallback callback) {
                    TipDialog.with(mContext)
                            .title(R.string.permission_dialog_title)
                            .message(R.string.permission_dialog_msg_again)
                            .yesText(R.string.permission_dialog_btn_request)
                            .noText(R.string.permission_dialog_btn_cancel)
//                            .noText("不开通")
                            .cancelable(false)
                            .onYes(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    callback.onYes();
                                }
                            })
                            .onNo(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    callback.onNo();
                                }
                            })
                            .show();
                }

                @Override
                public void dismiss() {
                    Log.e("onFailed","点击取消222");
                }
            };
        }
        return mDialogRequestAgain;
    }

    private IPermissionDialog getDialogGoSetting() {
        if (mDialogGoSetting == null) {
            mDialogGoSetting = new IPermissionDialog() {
                @Override
                public void show(IPermissionDialogCallback callback) {
                    TipDialog.with(mContext)
                            .title(R.string.permission_dialog_title)
                            .message(R.string.permission_dialog_msg_go_setting)
                            .yesText(R.string.permission_dialog_btn_go_setting)
                            .noText(R.string.permission_dialog_btn_cancel)
                            .cancelable(false)
                            .onYes(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    callback.onYes();
                                }
                            })
                            .onNo(new SimpleCallback<Void>() {
                                @Override
                                public void onResult(Void data) {
                                    callback.onNo();
                                }
                            })
                            .show();
                }

                @Override
                public void dismiss() {
                }
            };
        }
        return mDialogGoSetting;
    }
}
