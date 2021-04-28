package com.vinnlook.www.utils.sp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

/**
 * 描述：  设置spannableString背景的工具类
 *
 * @author ANyu
 * @date 2019\3\16 0016
 */
public class RoundBackGroundColorSpan extends ReplacementSpan {
    private int bgColor;
    private int textColor;

    public RoundBackGroundColorSpan(int bgColor, int textColor) {
        super();
        this.bgColor = bgColor;
        this.textColor = textColor;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return ((int) paint.measureText(text, start, end) + 30);
    }


    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int color1 = paint.getColor();
        paint.setColor(this.bgColor);
        canvas.drawRoundRect(new RectF(x, top + 1, x + ((int) paint.measureText(text, start, end) + 20), bottom - 1), 10, 10, paint);
        paint.setColor(this.textColor);
        canvas.drawText(text, start, end, x + 10, y, paint);
        paint.setColor(color1);
    }
}
