package com.goldducks.splashAnimations;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * The logo scales in with an overshoot bounce, holds, then scales up while fading out.
 */
class ZoomAnimation extends BaseSplashAnimation {

    private static final long SCALE_IN_DURATION = 450;
    private static final long HOLD_DURATION = 450;
    private static final long SCALE_OUT_DURATION = 300;

    private View logoCircle;

    ZoomAnimation(Context context) {
        super(context, R.layout.layout_splash_zoom);
    }

    @Override
    void start() {
        logoCircle = findViewById(R.id.logoCircle);
        logoCircle.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(SCALE_IN_DURATION)
                .setInterpolator(new OvershootInterpolator())
                .withEndAction(this::holdThenZoomOut)
                .start();
    }

    private void holdThenZoomOut() {
        logoCircle.postDelayed(() -> logoCircle.animate()
                .scaleX(1.6f)
                .scaleY(1.6f)
                .alpha(0f)
                .setDuration(SCALE_OUT_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .withEndAction(this::finish)
                .start(), HOLD_DURATION);
    }
}
