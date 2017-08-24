package com.qunhe.hacksketch;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author dq
 */
public class BaseSketchView extends View {
    public BaseSketchView(final Context context) {
        super(context);
    }

    public BaseSketchView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseSketchView(final Context context, @Nullable final AttributeSet attrs, final int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseSketchView(final Context context, @Nullable final AttributeSet attrs, final int
            defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
