package com.qunhe.hacksketch;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.qunhe.hacksketch.action.SketchAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SketchActivity extends AppCompatActivity {
    private List<BaseSketchData> mSketchData = new ArrayList<>();
    private Stack<SketchAction> mSketchActions = new Stack<>();
    private SketchBoardLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sketch);
        mFrameLayout = (SketchBoardLayout) findViewById(R.id.content);
        addTopView();
        mFrameLayout.setPenDraw(true);
        findViewById(R.id.add_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mFrameLayout.removeViewAt(mFrameLayout.getChildCount() - 1);
                TextSketchView textSketchView = new TextSketchView(SketchActivity.this);
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                textSketchView.setLayer(mFrameLayout.getChildCount());
                textSketchView.setText("测试测试", R.color.colorPrimary);
                mFrameLayout.addView(textSketchView, lp);
                mFrameLayout.setPenDraw(false);
            }
        });
        findViewById(R.id.pen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                addTopView();
                mFrameLayout.setPenDraw(true);
            }
        });
    }

    private boolean addedTopView = false;
    private void addTopView() {
        if (addedTopView) return;
        addedTopView = true;
        LineSketchView lineSketchView = new LineSketchView(SketchActivity.this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lineSketchView.setLayer(mFrameLayout.getChildCount());
        mFrameLayout.addView(lineSketchView, lp);
        lineSketchView.setColorAndStroke(android.R.color.holo_purple, 40);
        lineSketchView.setOnSketchListener(new OnSketchListener<LineSketchData>() {
            @Override
            public void onSketchComplete(final LineSketchData data) {
                mSketchData.add(data);
                addedTopView = false;
                addTopView();
            }

            @Override
            public void onAction(final SketchAction sketchAction) {
                mSketchActions.add(sketchAction);
            }

            @Override
            public void onSketchTouched(final int layer) {
                /*mFrameLayout.bringChildToFront(mFrameLayout.getChildAt(layer));
                mSketchActions.add(new LayerAction(SketchAction.LAYER_ACTION).setOldLayer(layer)
                        .setNewLayer(mFrameLayout.getChildCount() - 1));*/
            }
        });
    }

    public void undo() {
        SketchAction action = mSketchActions.pop();
        switch (action.getActionType()) {
            case SketchAction.CREATE_ACTION:

                break;
        }
    }
}
