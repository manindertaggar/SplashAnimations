package com.goldducks.splashAnimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * A circle pulses (scales up and back down) a few times, then the whole overlay fades out.
 */
class PulseAnimation extends BaseSplashAnimation {

    private static final int PULSE_COUNT = 3;
    private static final long PULSE_DURATION = 350;
    private static final long FADE_OUT_DURATION = 300;

    private View pulseCircle;

    PulseAnimation(Context context) {
        super(context, R.layout.layout_splash_pulse);
    }

    @Override
    void start() {
        pulseCircle = findViewById(R.id.pulseCircle);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(pulseCircle, View.SCALE_X, 1f, 1.35f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(pulseCircle, View.SCALE_Y, 1f, 1.35f, 1f);
        scaleX.setRepeatCount(PULSE_COUNT - 1);
        scaleY.setRepeatCount(PULSE_COUNT - 1);

        AnimatorSet pulseSet = new AnimatorSet();
        pulseSet.playTogether(scaleX, scaleY);
        pulseSet.setDuration(PULSE_DURATION);
        pulseSet.setInterpolator(new AccelerateDecelerateInterpolator());
        pulseSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                contentView.animate()
                        .alpha(0f)
                        .setDuration(FADE_OUT_DURATION)
                        .withEndAction(PulseAnimation.this::finish)
                        .start();
            }
        });
        pulseSet.start();
    }
}
