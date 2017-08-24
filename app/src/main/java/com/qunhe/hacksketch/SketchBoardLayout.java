package com.qunhe.hacksketch;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author dq
 */
public class SketchBoardLayout extends FrameLayout {
    private boolean mIsPenDraw;

    public SketchBoardLayout(@NonNull final Context context) {
        super(context);
    }

    public SketchBoardLayout(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public SketchBoardLayout(@NonNull final Context context, @Nullable final AttributeSet attrs,
                             @AttrRes final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SketchBoardLayout(@NonNull final Context context, @Nullable final AttributeSet attrs,
                             @AttrRes final int defStyleAttr, @StyleRes final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected boolean isChildrenDrawingOrderEnabled() {
        return true;
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        return i;
    }

    public void setPenDraw(boolean isPenDraw) {
        mIsPenDraw = isPenDraw;
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent ev) {
        return mIsPenDraw;
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (!mIsPenDraw) {
            for (int i = getChildCount() - 1; i >= 0; --i) {
                if (getChildAt(i).onTouchEvent(event)) {
                    break;
                }
            }
            return true;
        }
        if (getChildCount() < 2) {
            getChildAt(0).onTouchEvent(event);
        } else {
            int i;
            for (i = getChildCount() - 2; i >= 0; --i) {
                if (getChildAt(i).onTouchEvent(event)) {
                    break;
                }
            }
            // 说明前面未拦截
            if (i < 0) {
                getChildAt(getChildCount() - 1).onTouchEvent(event);
            }
        }
        return true;
    }
}
