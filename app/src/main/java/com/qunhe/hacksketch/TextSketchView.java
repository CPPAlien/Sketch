package com.qunhe.hacksketch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author dq
 */
public class TextSketchView extends BaseSketchView<TextSketchData> {
    private String mText;

    public TextSketchView(final Context context) {
        super(context);
    }

    public TextSketchView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initPaint() {
        mPaint.setTextSize(100);
    }

    @Override
    public boolean isValidArea(final MotionEvent event, int left, int top) {
        Rect bounds = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), bounds);
        Rect realBounds = new Rect(bounds.left + 200, bounds.top + 200, bounds.right + 200, bounds.bottom + 200);
        return realBounds.contains((int) event.getX() - left, (int) event.getY() - top);
    }

    public void setText(String text, @ColorRes int color) {
        mText = text;
        mPaint.setColor(ContextCompat.getColor(getContext(), color));
        invalidate();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        if (mText != null) {
            canvas.drawText(mText, 200, 200, mPaint);
        }
    }
}
