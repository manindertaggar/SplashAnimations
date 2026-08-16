package com.goldducks.splashAnimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Three dots bounce up and down in a staggered sequence, like a loading indicator,
 * then the overlay fades out.
 */
class BounceDotsAnimation extends BaseSplashAnimation {

    private static final int BOUNCE_COUNT = 2;
    private static final long BOUNCE_DURATION = 300;
    private static final long STAGGER_DELAY = 120;
    private static final float BOUNCE_HEIGHT = -30f;
    private static final long FADE_OUT_DURATION = 300;

    BounceDotsAnimation(Context context) {
        super(context, R.layout.layout_splash_bounce_dots);
    }

    @Override
    void start() {
        View dotOne = findViewById(R.id.dotOne);
        View dotTwo = findViewById(R.id.dotTwo);
        View dotThree = findViewById(R.id.dotThree);

        AnimatorSet bounceSet = new AnimatorSet();
        bounceSet.playTogether(
                bounceAnimatorFor(dotOne, 0),
                bounceAnimatorFor(dotTwo, STAGGER_DELAY),
                bounceAnimatorFor(dotThree, STAGGER_DELAY * 2)
        );
        bounceSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                contentView.animate()
                        .alpha(0f)
                        .setDuration(FADE_OUT_DURATION)
                        .withEndAction(BounceDotsAnimation.this::finish)
                        .start();
            }
        });
        bounceSet.start();
    }

    private ObjectAnimator bounceAnimatorFor(View dot, long startDelay) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(dot, View.TRANSLATION_Y, 0f, BOUNCE_HEIGHT, 0f);
        animator.setDuration(BOUNCE_DURATION);
        animator.setStartDelay(startDelay);
        animator.setRepeatCount(BOUNCE_COUNT - 1);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        return animator;
    }
}
