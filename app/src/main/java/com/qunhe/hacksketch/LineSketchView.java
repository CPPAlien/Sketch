package com.qunhe.hacksketch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author dq
 */
public class LineSketchView extends BaseSketchView<LineSketchData> {
    private final Path mPath = new Path();
    private float mX;
    private float mY;
    private boolean mIsFirstCreated = true;
    private LineSketchData mLineSketchData = new LineSketchData();

    public LineSketchView(final Context context) {
        super(context);
        initPaint();
    }

    public LineSketchView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void setColorAndStroke(@ColorRes int color, float strokeWidth) {
        mPaint.setColor(ContextCompat.getColor(getContext(), color));
        mPaint.setStrokeWidth(strokeWidth);
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        // 第一次创建，并且未点击在其他sketch的区域上
        if (mIsFirstCreated) {
            float x = event.getRawX();
            float y = event.getRawY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPath.reset();
                    mPath.moveTo(x, y);
                    mX = x;
                    mY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    mPath.quadTo(mX, mY, x, y);
                    mX = x;
                    mY = y;
                    break;
                case MotionEvent.ACTION_UP:
                    mIsFirstCreated = false;
                    if (mOnSketchListener != null) {
                        mOnSketchListener.onSketchComplete(mLineSketchData);
                    }
                    RectF rectF = new RectF();
                    mPath.computeBounds(rectF, false);
                    break;
                default:
                    break;
            }
            invalidate();
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }

    @Override
    public boolean isValidArea(final MotionEvent event, int left, int top) {
        int rX = (int) event.getX() - left;
        int rY = (int) event.getY() - top;
        Bitmap bitmap = getBitmap();
        if (rX < 0 || rX > bitmap.getWidth() || rY < 0 || rY > bitmap.getHeight()) {
            return false;
        }
        int color = bitmap.getPixel(rX, rY);
        return color < 0;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
        mLineSketchData.setPaint(mPaint);
        mLineSketchData.setPath(mPath);
    }

    private Bitmap getBitmap() {
        int width = getRight() - getLeft();
        int height = getBottom() - getTop();
        final boolean opaque = getDrawingCacheBackgroundColor() != 0 || isOpaque();
        Bitmap.Config quality;
        if (!opaque) {
            switch (getDrawingCacheQuality()) {
                case DRAWING_CACHE_QUALITY_AUTO:
                case DRAWING_CACHE_QUALITY_LOW:
                case DRAWING_CACHE_QUALITY_HIGH:
                default:
                    quality = Bitmap.Config.ARGB_8888;
                    break;
            }
        } else {
            quality = Bitmap.Config.RGB_565;
        }
        Bitmap bitmap = Bitmap.createBitmap(getResources().getDisplayMetrics(), width, height, quality);
        if (opaque) {
            bitmap.setHasAlpha(false);
        }
        bitmap.setDensity(getResources().getDisplayMetrics().densityDpi);
        boolean clear = getDrawingCacheBackgroundColor() != 0;
        Canvas canvas = new Canvas(bitmap);
        if (clear) {
            bitmap.eraseColor(getDrawingCacheBackgroundColor());
        }
        computeScroll();
        final int restoreCount = canvas.save();
        canvas.translate(-getScrollX(), -getScrollY());
        draw(canvas);
        canvas.restoreToCount(restoreCount);
        canvas.setBitmap(null);
        return bitmap;
    }
}
