package com.vinnlook.www.surface.bean;

import java.io.Serializable;

public class BindingBankCardBean implements Serializable {


    //    /**
//     * code : 1
//     * message : success
//     * data : [{"id":"22","bankCode":"CCB","bankName":"建设银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"0255B066C01D46139BF29F2E5C22DADD","createTime":"2017-05-04 13:01:24"},{"id":"23","bankCode":"ABC","bankName":"农业银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"9EF7C417F47C4A53B25A5F7A45F0ACB2","createTime":"2017-05-04 13:01:48"},{"id":"24","bankCode":"BOC","bankName":"中国银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"3B594143B5FE42CA80B08095CC668A66","createTime":"2017-05-04 13:02:02"},{"id":"25","bankCode":"CITIC","bankName":"中信银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"B3524DD2B0B14BC6ABDC989AEB5A8677","createTime":"2017-05-04 13:02:15"},{"id":"26","bankCode":"CEB","bankName":"光大银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"B56DA5AF54FD4DD2A021FC366728D4EE","createTime":"2017-05-04 13:02:28"},{"id":"27","bankCode":"CIB","bankName":"兴业银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"A97769044B4B4C44A35D873A1325C1C8","createTime":"2017-05-04 13:02:41"},{"id":"28","bankCode":"SPDB","bankName":"浦发银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"7C4A6CC1B44948F88BBED2184D027617","createTime":"2017-05-04 13:02:51"},{"id":"30","bankCode":"CMBC","bankName":"民生银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"BB60BAC34E924619895299AAD83C68CD","createTime":"2017-05-04 13:12:21"},{"id":"31","bankCode":"GDB","bankName":"广发银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"7F2BE3BEF4344256BC85AF6968BDEAF5","createTime":"2017-05-04 13:12:33"},{"id":"32","bankCode":"SPAB","bankName":"平安银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"1D584BCD62B54C7FB2EC73164E498597","createTime":"2017-05-04 13:12:46"},{"id":"33","bankCode":"CMB","bankName":"招商银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"4B9D1DE0C71F4CD2BE6868EB5094788F","createTime":"2017-05-04 13:12:59"},{"id":"34","bankCode":"COMM","bankName":"交通银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"E2DDFCCE91D641389F260DFC9DA48B41","createTime":"2017-05-04 13:13:11"},{"id":"35","bankCode":"ICBC","bankName":"工商银行","isUsed":"1","quickBillCu":"sdfds","rgb":"sd","uuid":"9B0ED42A835B41AFB1F1084C0C867D4A","createTime":"2017-05-07 11:45:26"}]
//     */
//
//    private int code;
//    private String message;
//    private List<DataBean> data;

    //
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }

    //
//    public static class DataBean implements Serializable {
    /**
     * id : 22
     * bankCode : CCB
     * bankName : 建设银行
     * isUsed : 1
     * quickBillCu : sdfds
     * rgb : sd
     * uuid : 0255B066C01D46139BF29F2E5C22DADD
     * createTime : 2017-05-04 13:01:24
     */

    private String id;
    private String bankCode;
    private String bankName;
    private String isUsed;
    private String quickBillCu;
    private String rgb;
    private String uuid;
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getQuickBillCu() {
        return quickBillCu;
    }

    public void setQuickBillCu(String quickBillCu) {
        this.quickBillCu = quickBillCu;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
//}
