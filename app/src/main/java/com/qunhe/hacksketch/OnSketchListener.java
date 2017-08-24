package com.qunhe.hacksketch;

import com.qunhe.hacksketch.action.SketchAction;

/**
 * @author dq
 */
public interface OnSketchListener<T extends BaseSketchData> {
     void onSketchComplete(T data);
     void onAction(SketchAction sketchAction);
     void onSketchTouched(int layer);
}
