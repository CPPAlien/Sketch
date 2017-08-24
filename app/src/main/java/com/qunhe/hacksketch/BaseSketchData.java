package com.qunhe.hacksketch;

import android.graphics.Paint;

/**
 * @author dq
 */
public class BaseSketchData {
    private Paint mPaint;

    public Paint getPaint() {
        return mPaint;
    }

    public BaseSketchData setPaint(final Paint paint) {
        mPaint = paint;
        return this;
    }
}
