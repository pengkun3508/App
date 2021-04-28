package com.vinnlook.www.http;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dm.lib.core.receiver.ForceOfflineReceiver;
import com.dm.lib.utils.ResUtils;
import com.dm.lib.utils.SPUtils;
import com.dm.lib.utils.ToastMaker;
import com.google.gson.Gson;
import com.vinnlook.www.R;
import com.vinnlook.www.common.Constant;
import com.vinnlook.www.event.GuangGaoEvent;
import com.vinnlook.www.http.model.SignBean;
import com.vinnlook.www.receiver.AppUpdateReceiver;
import com.vinnlook.www.utils.SPAppUpdateUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import per.goweii.rxhttp.request.RxRequest;
import per.goweii.rxhttp.request.exception.ExceptionHandle;

/**
 * 描述：网络请求的基类，封装公用方法
 *
 * @author Cuizhen
 * @author Cuizhen
 * @date 2018/9/9
 */
public class BaseRequest {

    protected static <T> Disposable requestWithSign(@NonNull RequestCallback<T> observable, @NonNull RequestBackListener<T> callback) {
        //公共请求时间接口
        return request(ProjectApi.api().getSign()
                .flatMap(new Function<ResponseBean<SignBean>, ObservableSource<ResponseBean<T>>>() {
                    @Override
                    public Observable<ResponseBean<T>> apply(ResponseBean<SignBean> bean) {
                        PublicHeadersInterceptor.updateTime(bean.getData().getTime() + "");
                        Log.e("updateTime", "updateTime===" + bean.getData().getTime() + "");
                        PublicHeadersInterceptor.updateToken(bean.getData().getToken());
//                        if (bean.getData().getVersion() != null && !TextUtils.isEmpty(bean.getData().getVersion().getUrl())) {
//                        if (bean.getData().getVersion().getMust() == 2) {//1：最新版本；2：强制更新；3：提示更新；4：手动更新
//                            AppUpdateReceiver.send(bean.getData().getVersion());
//                        } else if (SPAppUpdateUtils.instance().isShouldUpdate1()) {//大于一天时间
//                            Log.e("updateTime", "updateTime===" + "大于一天时间了");
//                            if (bean.getData().getVersion().getMust() == 3) {//1：最新版本；2：强制更新；3：提示更新；4：手动更新
//                                AppUpdateReceiver.send(bean.getData().getVersion());
//                            } else {
//                                new GuangGaoEvent(true).post();
//                            }
//
//                        }

//                        }
                        String userInfoJson = new Gson().toJson(bean.getData());
                        SPUtils.getInstance().save(Constant.KEY_SIGN_BEAN, userInfoJson);
//                        }
//                        if (bean.getData().getVersion() != null && !TextUtils.isEmpty(bean.getData().getVersion().getUrl())) {
//                            //在这里判断产品是否需要更新？？？？
//                            Log.e("版本是否更新", "==getCode==" + Integer.valueOf(bean.getData().getVersion().getCode()));
////                            if (SPAppUpdateUtils.instance().isShouldUpdate(Integer.valueOf(bean.getData().getVersion().getCode()))) {
//                            if (SPAppUpdateUtils.instance().isShouldUpdate(Integer.valueOf(bean.getData().getVersion().getCode()))) {
//                                AppUpdateReceiver.send(bean.getData().getVersion());
//                            }
//                            if (SPAppUpdateUtils.instance().isNew(Integer.valueOf(bean.getData().getVersion().getCode()))) {
//                                return observable.request(Integer.valueOf(bean.getData().getVersion().getMust()) == 1).subscribeOn(Schedulers.io());
//                            }
//                        }
                        return observable.request(false).subscribeOn(Schedulers.io());
                    }
                }), callback);
    }

    protected static <T> Disposable request(@NonNull Observable<ResponseBean<T>> observable, @NonNull RequestBackListener<T> callback) {

        return RxRequest.create(observable)
                .listener(new RxRequest.RequestListener() {
                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

                    @Override
                    public void onError(ExceptionHandle handle) {
                        handle.getException().printStackTrace();
                        Log.e("onFailed", "handle.getCode()==" + handle.getCode());
                        if (handle.getCode() == ExceptionHandle.Code.NET) {
                            ToastMaker.showShort(R.string.http_no_net);
                            callback.onNoNet();
                            callback.onFailed(ProjectApi.ApiCode.ERROR_NET, ResUtils.getString(R.string.http_no_net));
                        } else {
                            callback.onError(handle.getException());
                            callback.onFailed(ProjectApi.ApiCode.ERROR, ResUtils.getString(R.string.http_error));
                        }


                    }

                    @Override
                    public void onFinish() {
                        callback.onFinish();
                    }
                })
                .request(new RxRequest.ResultCallback<T>() {
                    @Override
                    public void onSuccess(int code, T dats) {
                        callback.onSuccess(code, dats);
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        Log.e("onFailed", "pk==code==" + code);
                        Log.e("onFailed", "pk==msg==" + msg);
//                        code == ProjectApi.ApiCode.ACCOUNT_NOT_EXIST ||
                        if (code == ProjectApi.ApiCode.ACCOUNT_EXCEPTION ||
                                code == ProjectApi.ApiCode.ACCOUNT_FROZEN ||
                                code == ProjectApi.ApiCode.ACCOUNT_DELETED) {
                            ForceOfflineReceiver.send(code, msg);
                        }
                        callback.onFailed(code, msg);
                    }
                });
    }

    protected interface RequestCallback<T> {
        Observable<ResponseBean<T>> request(boolean isAppForceUpdate);
    }
}
