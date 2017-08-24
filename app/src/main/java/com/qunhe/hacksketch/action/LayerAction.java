package com.qunhe.hacksketch.action;

/**
 * @author dq
 */
public class LayerAction extends SketchAction {
    private int mOldLayer;
    private int mNewLayer;

    public LayerAction(@ActionType final int type) {
        super(type);
    }

    public int getOldLayer() {
        return mOldLayer;
    }

    public LayerAction setOldLayer(final int oldLayer) {
        mOldLayer = oldLayer;
        return this;
    }

    public int getNewLayer() {
        return mNewLayer;
    }

    public LayerAction setNewLayer(final int newLayer) {
        mNewLayer = newLayer;
        return this;
    }
}
