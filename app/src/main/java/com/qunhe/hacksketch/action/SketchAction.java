package com.qunhe.hacksketch.action;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

/**
 * @author dq
 */
public class SketchAction {
    public static final int CREATE_ACTION = 0;
    public static final int DELETE_ACTION = 1;
    public static final int LAYER_ACTION = 2;

    @Retention(SOURCE)
    @IntDef({CREATE_ACTION, DELETE_ACTION, LAYER_ACTION})
    public @interface ActionType {
    }

    private
    @ActionType
    int mActionType;

    public SketchAction(@ActionType int type) {
        mActionType = type;
    }

    public
    @SketchAction.ActionType
    int getActionType() {
        return mActionType;
    }
}
