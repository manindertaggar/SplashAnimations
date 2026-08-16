package com.goldducks.splashAnimations;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Two panels hold together at the center, then slide apart horizontally like
 * theater curtains opening, revealing the app content behind them.
 */
class CurtainAnimation extends BaseSplashAnimation {

    private static final long HOLD_DURATION = 550;
    private static final long OPEN_DURATION = 450;

    private View parentView;
    private View leftPanel;
    private View rightPanel;
    private View centerLine;

    CurtainAnimation(Context context) {
        super(context, R.layout.layout_splash_curtain);
    }

    @Override
    void start() {
        parentView = findViewById(R.id.parentView);
        leftPanel = findViewById(R.id.leftPanel);
        rightPanel = findViewById(R.id.rightPanel);
        centerLine = findViewById(R.id.centerLine);

        parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                parentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                openAfterHold();
            }
        });
    }

    private void openAfterHold() {
        leftPanel.postDelayed(() -> {
            centerLine.animate().alpha(0f).setDuration(150).start();
            int halfWidth = parentView.getWidth() / 2;

            leftPanel.animate()
                    .translationX(-halfWidth)
                    .setDuration(OPEN_DURATION)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .start();

            rightPanel.animate()
                    .translationX(halfWidth)
                    .setDuration(OPEN_DURATION)
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .withEndAction(this::finish)
                    .start();
        }, HOLD_DURATION);
    }
}
