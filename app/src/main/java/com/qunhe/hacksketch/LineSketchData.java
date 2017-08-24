package com.qunhe.hacksketch;

import android.graphics.Path;

/**
 * @author dq
 */
public class LineSketchData extends BaseSketchData {
    private Path mPath;

    public Path getPath() {
        return mPath;
    }

    public LineSketchData setPath(final Path path) {
        mPath = path;
        return this;
    }
}
