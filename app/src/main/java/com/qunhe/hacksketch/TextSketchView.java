package com.qunhe.hacksketch;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

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

    }

    public void setText(String text, @ColorRes int color) {

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        if (mText != null) {
            canvas.drawText(mText, 200, 200, mPaint);
        }
    }
}
