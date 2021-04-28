package com.vinnlook.www.utils;

import android.text.TextUtils;
import android.util.Log;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 2018/4/10.
 */

public class StringUtils {

    /**
     * 修改部分text内容
     *
     * @param format
     * @param args
     * @return
     */
    public static String format(String format, Object... args) {
        return new Formatter().format(format, args).toString();
    }

    /**
     * 判断两个字符串是否一致
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) {
            return true;
        }
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }


    /**
     * 检查目标字符串是否在指定的字符串数组里有相同的值
     *
     * @param a     目标文本
     * @param group 字符串数组
     */
    public static boolean inGroup(CharSequence a, String... group) {
        if (!TextUtils.isEmpty(a) && !EmptyUtils.isEmpty(group)) {
            for (String string : group) {
                if (TextUtils.equals(a, string)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 验证字符串是否包含一个电话号码（手机或座机）
     */
    public static boolean isMobile(String phone) {
        if (EmptyUtils.isEmpty(phone)) {
            return false;
        }
//        boolean result = phone.matches("1\\d{10}");
        boolean result = phone.matches("^[1][3456789][0-9]{9}$");
        boolean lengthMatch = phone.length() >= 7 && phone.length() <= 11;
        return lengthMatch && result;
    }


    /**
     * 检查密码强度有效性，规则：八位、小写字母、大写字母、数字、符号
     */
    public static boolean checkPwdSecurity(String pwd) {
        if (EmptyUtils.isEmpty(pwd)) {
            return false;
        }
        return pwd.matches("^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,16}$");
    }


    public static boolean isRightPhone(String phoneNum) {
        if (EmptyUtils.isEmpty(phoneNum)) {
            return false;
        }
//        String phoneReg = "1\\d{10}";
        String phoneReg = "^[1][3456789][0-9]{9}$";
        return phoneNum.matches(phoneReg);
    }


    /**
     * URL编码
     */
    public static String toURLEncoded(String paramString) {
        if (paramString == null || "".equals(paramString)) {
            return "";
        }

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 含数字和字母的密码验证 不小于6位数，不大于20位数，不包含空格
     */
    public static boolean pswStringNum(String psw) {
        boolean flag = false;

        try {
            // 限定特殊字符
            //(?!^\\d+$)不能全是数字
            //(?!^[a-z]+$)不能全是小写字母
            //(?!^[A-Z]+$)不能全是大写字母
            //((?!^[_#@]+$)不能全是符号
            // String reg = "^(?![\\d]+$)(?![a-zA-Z]+$)(?![!#$%^&*]+$)[\\da-zA-Z!#$%^&*]{6,20}$";
            String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
//            String reg = "^[^\\s]{6,18}$";
            //            String reg = "(?!^[0-9]+$)(?!^[a-z]+$)(?!^[A-Z]+$)(?!^[~!@#$%^&*()_+`\\-={}\n" + ":\";'<>?,
            // .\\/]+$).{8,18}";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(psw);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }


    /**
     * 验证是否为正确的邮箱地址
     *
     * @param email 待验证邮箱地址
     * @return 正确返回true，否则false
     */
    public static boolean isRightEmail(String email) {
        if (EmptyUtils.isEmpty(email)) {
            return false;
        }
        String emailFilter = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(emailFilter);
    }


    /**
     * 验证是否为正确的密码 规则为6到16位的字母和数字组合
     *
     * @param pwd 待验证密码
     * @return 正确返回true，否则false
     */
    public static boolean isRightPwd(String pwd) {
        if (EmptyUtils.isEmpty(pwd)) {
            return false;
        }

        int len = pwd.length();

        if (len < 6 || len > 16) {
            return false;
        }

        return true;
    }


    /**
     * 验证是否为正确的临时短信密码 规则为6位数字组合
     *
     * @param smsPwd 待验证密码
     * @return 正确返回true，否则false
     */
    public static boolean isRightTempSmsPwd(String smsPwd) {
        if (EmptyUtils.isEmpty(smsPwd)) {
            return false;
        }
        String lengthReg = "\\d{6}";// 6位数字组合
        if (!smsPwd.matches(lengthReg)) {
            return false;
        }

        // Pattern letterReg = Pattern.compile("[a-zA-Z]+");// 必须含有字母
        // Pattern figureReg = Pattern.compile("[0-9]+");// 必须含有数字
        // Matcher mathcer = letterReg.matcher(pwd);
        // if (!mathcer.find()) {
        // return false;
        // }
        // mathcer.reset().usePattern(figureReg);
        // if (!mathcer.find()) {
        // return false;
        // }

        return true;
    }


    /**
     * 身份证正则表达式
     */
    private static final String IDCARD =
            "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
                    "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
                    "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";


    public static boolean isIdCard(String string) {
        if (EmptyUtils.isEmpty(string)) {
            return false;
            //        } else if (string.trim().length() == 15 || string.trim().length() == 18) {
            //            return string.matches(IDCARD);
            //        } else {
            //            return false;
        } else {
            return string.trim().length() == 15 || string.trim().length() == 18;
        }
    }

    public static String getCutStringToSign(String str, String sign) {
        str = str.substring(str.indexOf(sign) + 1);
        return str;
    }

    public static String getCutStringTolast(String str, String sign) {
        str = str.substring(str.indexOf(sign) + 1, str.length());
        return str;
    }

    public Boolean isSeriesPwd(String s) {
        boolean isSeries = false;
        List<String> list = new ArrayList<>();
        list.add("012345");
        list.add("123456");
        list.add("234567");
        list.add("345678");
        list.add("456789");
        list.add("567890");
        list.add("678901");
        list.add("789012");
        list.add("890123");
        list.add("901234");

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(s)) {
                isSeries = true;
            }
        }

        return isSeries;
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String checkPointData(Double d) {
        String str = null;

        double n, old = d;
        int tmp = (int) old;
        n = (double) tmp;
        if (0 == (n - old)) {
            Log.i("TAG", "result_is_equal");
            str = d + "";
            str = str.substring(0, str.lastIndexOf("."));
            return str;
        } else {
            Log.i("TAG", "result_not_equal");
            str = d + "";
            return str;
        }

    }

    //给手机号码中间4位换成星号
    public static String encryptPhoneNum(String str) {
        StringBuffer s = new StringBuffer();
        s.append(str.substring(0, 3));
        for (int i = 0; i < 4; i++) {
            s = s.append("*");
        }
        s.append(str.substring(7, str.length()));
        return s.toString();

    }

    //将集合数据以逗号拼接
    public static String listToString1(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

}

