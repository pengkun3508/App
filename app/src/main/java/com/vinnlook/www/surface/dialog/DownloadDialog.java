package com.vinnlook.www.surface.dialog;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.lib.core.permission.PermissionHelper;
import com.dm.lib.utils.DownloadUtils;
import com.dm.lib.utils.FileUtils;
import com.dm.lib.utils.ResUtils;
import com.vinnlook.www.R;

import java.io.File;

import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;


/**
 * 版本更新弹窗--下载
 *
 * @author Cuizhen
 * @date 2018/8/6-上午9:17
 */
public class DownloadDialog {
    private static final long ANIM_DURATION = 200;

    private final boolean isForce;
    private final String mUrl;
    private final Activity mActivity;
    private AnyLayer mAnyLayer = null;

    private ProgressBar progressBar;
    private TextView tvProgress;
    private TextView tvApkSize;
    private TextView tvState;
    private File mApk;

    private DownloadDialog(Activity activity, boolean isForce, String url) {
        this.mActivity = activity;
        this.isForce = isForce;
        this.mUrl = url;
        PermissionHelper.with(mActivity)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new PermissionHelper.PermissionListener() {
                    @Override
                    public void onSuccess() {
                        Log.e("onSuccess","成功");
                        showDialog();
                        DownloadUtils.download(mUrl, new DownloadUtils.DownloadListener() {
                            @Override
                            public void onPreExecute() {
                                preDownload();
                            }

                            @Override
                            public void onDownloadLength(int length) {
                                if (tvApkSize != null) {
                                    mActivity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvApkSize.setText(FileUtils.formatSize(length));
                                        }
                                    });
                                }
                            }
                            @Override
                            public void onProgressUpdate(int progress) {
                                setProgress(progress);
                            }

                            @Override
                            public void onPostExecute(File apk) {
                                mApk = apk;
                                if (tvState != null) {
                                    tvState.performClick();
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailed() {
                        activity.finish();
                    }




                });
    }

    public static DownloadDialog with(Activity activity, boolean isForce, String url) {
        return new DownloadDialog(activity, isForce, url);
    }

    private void showDialog() {
        mAnyLayer = AnyLayer.with(mActivity)
                .contentView(R.layout.dialog_download)
                .cancelableOnTouchOutside(false)
                .cancelableOnClickKeyBack(false)
                .bindData(new AnyLayer.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyDialog) {
                        progressBar = anyDialog.getView(R.id.pb_dialog_download);
                        tvProgress = anyDialog.getView(R.id.tv_dialog_download_progress);
                        tvApkSize = anyDialog.getView(R.id.tv_dialog_download_apk_size);
                        tvState = anyDialog.getView(R.id.tv_dialog_download_state);
                    }
                })
                .backgroundColorRes(R.color.dialog_bg)
                .gravity(Gravity.CENTER)
                .contentAnim(new AnyLayer.IAnim() {
                    @Override
                    public Animator inAnim(View content) {
                        return AnimHelper.createBottomAlphaInAnim(content).setDuration(ANIM_DURATION);
                    }

                    @Override
                    public Animator outAnim(View content) {
                        return AnimHelper.createBottomAlphaOutAnim(content).setDuration(ANIM_DURATION);
                    }
                })
                .onClick(R.id.tv_dialog_download_state, new AnyLayer.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View view) {
                        if (mApk == null) {
                            return;
                        }
                        if (!isForce) {
                            dismiss();
                        }
                        DownloadUtils.installApk(mActivity, mApk);
                    }
                });
        mAnyLayer.show();
    }

    private void preDownload() {
        if (progressBar != null) {
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }
        if (tvApkSize != null) {
            tvApkSize.setText("0B");
        }
        if (tvProgress != null) {
            tvProgress.setText("0");
        }
        if (tvState != null) {
            tvState.setText(R.string.download_dialog_state_downloading);
        }
    }

    private void setProgress(int progress) {
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
        if (tvProgress != null) {
            tvProgress.setText("" + progress);
        }
        if (progress >= 100) {
            if (tvState != null) {
                tvState.setText(R.string.download_dialog_state_install);
                tvState.setTextColor(ResUtils.getColor(R.color.them));
            }
        }
    }

    private void dismiss() {
        if (mAnyLayer != null) {
            mAnyLayer.dismiss();
        }
    }
}
