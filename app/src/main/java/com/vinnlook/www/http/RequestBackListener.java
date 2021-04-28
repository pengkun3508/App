package com.vinnlook.www.http;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/10/15
 */
public interface RequestBackListener<T> {
    void onStart();

    void onSuccess(int code, T data);

    void onFailed(int code, String msg);

    void onNoNet();

    void onError(Throwable e);

    void onFinish();
}
