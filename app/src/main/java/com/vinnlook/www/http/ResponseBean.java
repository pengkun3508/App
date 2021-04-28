package com.vinnlook.www.http;

import per.goweii.rxhttp.request.base.BaseResponse;

/**
 * 描述：网络接口返回json格式对应的实体类
 *
 * @author Cuizhen
 * @date 2018/6/19
 */
public class ResponseBean<E> implements BaseResponse<E> {

    private int code;
    private E data;
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public E getData() {
        return data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String getMsg() {
        return message;
    }

    @Override
    public void setMsg(String message) {
        this.message = message;
    }
}
