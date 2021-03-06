package com.vinnlook.www.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:文字变色工具类
 * @Time:2021/1/20$
 * @Author:pk$
 */
public class KeywordUtil {
    /**
     * 关键字高亮变色
     *
     * @param color   变化的色值
     * @param text    文字
     * @param keyword 文字中的关键字
     * @return 结果SpannableString
     */
//    public static SpannableString matcherSearchTitle(int color, String text, String keyword) {
//        SpannableString s = new SpannableString(text);
//        keyword = escapeExprSpecialWord(keyword);
//        text = escapeExprSpecialWord(text);
//        if (text.contains(keyword) && !TextUtils.isEmpty(keyword)) {
//            try {
//                Pattern p = Pattern.compile(keyword);
//                Matcher m = p.matcher(s);
//                while (m.find()) {
//                    int start = m.start();
//                    int end = m.end();
//                    s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                    Log.e("关键字高亮变色", "===" + s);
//                }
//            } catch (Exception e) {
//                Log.e("关键字高亮变色", "===" + e.toString());
//            }
//        }
//        return s;
//    }

    public static SpannableString matcherSearchTitle(int color, String text, String keyword) {
        String string = text.toLowerCase();
        String key = keyword.toLowerCase();

        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(string);

        SpannableString ss = new SpannableString(text);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            Log.e("关键字高亮变色", "==ss===" + ss);
        }
        return ss;
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return keyword
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (!TextUtils.isEmpty(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
