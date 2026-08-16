package com.goldducks.splashAnimations;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;

/**
 * A full-screen panel holds briefly, then slides upward off-screen, revealing the
 * app content underneath, like a curtain rising.
 */
class SlideUpAnimation extends BaseSplashAnimation {

    private static final long HOLD_DURATION = 600;
    private static final long SLIDE_DURATION = 400;

    private View parentView;
    private View panel;

    SlideUpAnimation(Context context) {
        super(context, R.layout.layout_splash_slide_up);
    }

    @Override
    void start() {
        parentView = findViewById(R.id.parentView);
        panel = findViewById(R.id.panel);

        parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                parentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                slideUpAfterHold();
            }
        });
    }

    private void slideUpAfterHold() {
        panel.postDelayed(() -> panel.animate()
                .translationY(-parentView.getHeight())
                .setDuration(SLIDE_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .withEndAction(this::finish)
                .start(), HOLD_DURATION);
    }
}
