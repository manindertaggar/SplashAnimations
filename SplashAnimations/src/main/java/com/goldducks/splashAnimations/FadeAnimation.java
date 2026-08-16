package com.goldducks.splashAnimations;

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * A plain cross-fade: the overlay fades in, holds briefly, then fades back out.
 */
class FadeAnimation extends BaseSplashAnimation {

    private static final long FADE_DURATION = 350;
    private static final long HOLD_DURATION = 500;

    FadeAnimation(Context context) {
        super(context, R.layout.layout_splash_fade);
    }

    @Override
    void start() {
        contentView.setAlpha(0f);
        contentView.animate()
                .alpha(1f)
                .setDuration(FADE_DURATION)
                .setInterpolator(new DecelerateInterpolator())
                .withEndAction(this::holdThenFadeOut)
                .start();
    }

    private void holdThenFadeOut() {
        contentView.postDelayed(() -> contentView.animate()
                .alpha(0f)
                .setDuration(FADE_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .withEndAction(this::finish)
                .start(), HOLD_DURATION);
    }
}
