package com.goldducks.splashAnimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * A small bar spins inside a static ring for a few full rotations, then the
 * overlay fades out.
 */
class RotateLoaderAnimation extends BaseSplashAnimation {

    private static final int ROTATION_COUNT = 3;
    private static final long ROTATION_DURATION = 500;
    private static final long FADE_OUT_DURATION = 300;

    RotateLoaderAnimation(Context context) {
        super(context, R.layout.layout_splash_rotate_loader);
    }

    @Override
    void start() {
        View loaderBar = findViewById(R.id.loaderBar);

        ObjectAnimator rotate = ObjectAnimator.ofFloat(loaderBar, View.ROTATION, 0f, 360f);
        rotate.setDuration(ROTATION_DURATION);
        rotate.setRepeatCount(ROTATION_COUNT - 1);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                contentView.animate()
                        .alpha(0f)
                        .setDuration(FADE_OUT_DURATION)
                        .withEndAction(RotateLoaderAnimation.this::finish)
                        .start();
            }
        });
        rotate.start();
    }
}
