package com.goldducks.splashAnimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

/**
 * A colored overlay shrinks away using a circular reveal centered on the screen,
 * uncovering the app content underneath.
 */
class CircularRevealAnimation extends BaseSplashAnimation {

    private static final long HOLD_DURATION = 500;
    private static final long REVEAL_DURATION = 500;

    private View parentView;
    private View revealOverlay;

    CircularRevealAnimation(Context context) {
        super(context, R.layout.layout_splash_circular_reveal);
    }

    @Override
    void start() {
        parentView = findViewById(R.id.parentView);
        revealOverlay = findViewById(R.id.revealOverlay);

        parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                parentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                revealOverlay.postDelayed(CircularRevealAnimation.this::reveal, HOLD_DURATION);
            }
        });
    }

    private void reveal() {
        int centerX = revealOverlay.getWidth() / 2;
        int centerY = revealOverlay.getHeight() / 2;
        float startRadius = (float) Math.hypot(centerX, centerY);

        Animator animator = ViewAnimationUtils.createCircularReveal(
                revealOverlay, centerX, centerY, startRadius, 0f);
        animator.setDuration(REVEAL_DURATION);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }
        });
        animator.start();
    }
}
