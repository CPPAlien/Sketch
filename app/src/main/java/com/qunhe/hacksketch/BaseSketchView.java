package com.qunhe.hacksketch;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author dq
 */
public abstract class BaseSketchView<T extends BaseSketchData> extends View {
    private int lastX;
    private int lastY;
    private boolean isValid;
    protected OnSketchListener<T> mOnSketchListener;
    private int mLayer;
    private int mLeft;
    private int mTop;
    protected Paint mPaint = new Paint();
    protected Bitmap mBitmap;

    public BaseSketchView(final Context context) {
        super(context);
        initPaint();
    }

    public BaseSketchView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BaseSketchView(final Context context, @Nullable final AttributeSet attrs, final int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseSketchView(final Context context, @Nullable final AttributeSet attrs, final int
            defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    protected abstract void initPaint();

    public void setOnSketchListener(OnSketchListener<T> listener) {
        mOnSketchListener = listener;
    }

    public void setLayer(int layer) {
        mLayer = layer;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                int color = mBitmap.getPixel((int) event.getX(), (int) event.getY());
                Log.e("pengtao", "color = " + color);
                isValid = color < 0;
                Log.e("pengtao", "isValid = " + isValid);
                if (isValid) {
                    mOnSketchListener.onSketchTouched(mLayer);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

                int top = this.getTop() + dy;
                int left = this.getLeft() + dx;
                if (isValid) {
                    mTop = top;
                    mLeft = left;
                    this.layout(mLeft, mTop, mLeft + this.getWidth(), mTop + this.getHeight());
                }

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();

                break;
            case MotionEvent.ACTION_UP:
                mBitmap = getBitmap();
                break;
            default:
                break;
        }
        return isValid;
    }

    @Override
    public void layout(@Px final int l, @Px final int t, @Px final int r, @Px final int b) {
        if (mTop != 0 || mLeft != 0) {
            super.layout(mLeft, mTop, mLeft + this.getWidth(), mTop + this.getHeight());
        } else {
            super.layout(l, t, r, b);
        }
        invalidate();
    }

    public Bitmap getBitmap() {
        return null;
    }
}
