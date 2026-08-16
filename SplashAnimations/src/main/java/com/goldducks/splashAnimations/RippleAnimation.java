package com.goldducks.splashAnimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Two rings expand outward from a center dot and fade as they grow, repeating a
 * couple of times like a ripple in water, then the overlay fades out.
 */
class RippleAnimation extends BaseSplashAnimation {

    private static final int RIPPLE_COUNT = 2;
    private static final long RIPPLE_DURATION = 700;
    private static final long RIPPLE_STAGGER = 300;
    private static final float MAX_SCALE = 3.2f;
    private static final long FADE_OUT_DURATION = 300;

    RippleAnimation(Context context) {
        super(context, R.layout.layout_splash_ripple);
    }

    @Override
    void start() {
        View rippleOne = findViewById(R.id.rippleOne);
        View rippleTwo = findViewById(R.id.rippleTwo);

        AnimatorSet rippleSet = new AnimatorSet();
        rippleSet.playTogether(
                rippleSequenceFor(rippleOne, 0),
                rippleSequenceFor(rippleTwo, RIPPLE_STAGGER)
        );
        rippleSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                contentView.animate()
                        .alpha(0f)
                        .setDuration(FADE_OUT_DURATION)
                        .withEndAction(RippleAnimation.this::finish)
                        .start();
            }
        });
        rippleSet.start();
    }

    private AnimatorSet rippleSequenceFor(View ring, long initialDelay) {
        Animator[] repeats = new Animator[RIPPLE_COUNT];
        for (int i = 0; i < RIPPLE_COUNT; i++) {
            repeats[i] = singleRippleAnimator(ring);
        }
        AnimatorSet sequence = new AnimatorSet();
        sequence.playSequentially(repeats);
        sequence.setStartDelay(initialDelay);
        return sequence;
    }

    private AnimatorSet singleRippleAnimator(final View ring) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(ring, View.SCALE_X, 1f, MAX_SCALE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ring, View.SCALE_Y, 1f, MAX_SCALE);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(ring, View.ALPHA, 1f, 0f);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX, scaleY, alpha);
        set.setDuration(RIPPLE_DURATION);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ring.setScaleX(1f);
                ring.setScaleY(1f);
                ring.setAlpha(1f);
            }
        });
        return set;
    }
}
